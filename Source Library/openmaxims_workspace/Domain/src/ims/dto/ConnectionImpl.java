package ims.dto;

import ims.configuration.BuildInfo;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.crypto.Encryption;
import ims.crypto.EncryptionType;
import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.factory.SystemLogFactory;
import ims.framework.SessionConstants;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.ISystemLogProvider;
import ims.framework.utils.DateTime;
import ims.utils.Logging;

import java.net.Socket;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Marius Mihalec (mmihalec@imsmaxims.com)
 * @version 1.0
 * @throws ims.dto.Exception
 */
public class ConnectionImpl extends BaseConnectionImpl implements Connection, java.io.Serializable
{	
	//FWD-233
	private static final org.apache.log4j.Logger LOG = Logging.getLogger(ConnectionImpl.class);
	private DomainSession domainSession = null;
	
	/**
	 * Creates a new empty connection.
	 */
	public ConnectionImpl()
	{
	}
	/**
	 * Creates a new connection.
	 * @param serverAddress - The address of the server in the format nas://server:port
	 */
	public ConnectionImpl(String serverAddress)
	{
		setServerConnection(serverAddress);
	}
	/**
	 * Creates a new connection.
	 * @param serverName - Server Name
	 * @param serverPort - Server Port
	 */
	public ConnectionImpl(String serverName, int serverPort, DomainSession domainsession)
	{
		setServerConnection(serverName, serverPort);
		this.domainSession = domainsession;
	}
	
	/**
	 * Sets the connection address.
	 * @param serverAddress - The address of the server in the format nas://server:port
	 */
	public void setServerConnection(String serverAddress)
	{
		this.serverName = getServerName(serverAddress);
		this.serverPort = getServerPort(serverAddress);
	}
	/**
	 * Sets the connection address.
	 * @param serverName - The server name or IP address
	 * @param serverPort - The server port
	 */
	public void setServerConnection(String serverName, int serverPort)
	{
		this.serverName = serverName;
		this.serverPort = serverPort;
	}
	/**
	 * Performs login against the current connection. If the result returned is not null an error occured.
	 * @param username - The username used to connect
	 * @param password - The password used to connect
	 * @return - ims.dto.Result - If the result returned is not null an error occured
	 */
	public Result login(String username, String password)
	{
		if (LOG.isInfoEnabled())
			LOG.info("login(username="+username+", password="+password+")");
		this.username = username;
		this.password = password;
		
		if (ConfigFlag.DTO.DTO_CONNECTION_TYPE.getValue().equals("HEARTS"))  // FWD-157 Hearts needs unencrypted password on login
			return login(username, password, encryptionType, false);
		else
			return login(username, password, encryptionType, true);
	}
	/**
	 * Performs re-login against the current connection. This method is called by the client class and is not recommended to be called directly.
	 * @return - ims.dto.Result - If the result returned is not null an error occured
	 */
	public Result reLogin()
	{
		if(loggedIn)
			return null;
		//if(firstTimeLogin)
		//return new Result("Not logged in", "DTO.Connection.ReLogin");
		
		Result result = login(username, password, encryptionType, true);
		if (result != null) return result;
		return null;
		//return new Result("Not implemented", "DTO.Connection.Login");
	}
	/**
	 * Performs logout against the current connection. If the result returned is not null an error occured.
	 * @return - ims.dto.Result - If the result returned is not null an error occured 
	 */
	public Result logout()
	{
		if(!connected)
			return new Result("Not connected", "DTO.Connection.Logout");
		if(!loggedIn)
			return new Result("Not logged in", "DTO.Connection.Logout");
		
		String message = NASMessageCodes.SEPARATOR + String.valueOf(NASMessageCodes.SOCK_DISCONNECT).length() +
		NASMessageCodes.SEPARATOR + String.valueOf(NASMessageCodes.SOCK_DISCONNECT) + 
		NASMessageCodes.SEPARATOR;
		
		Result result = send(message);
		if(result != null)
			return result;
		
		StringBuffer messageBuffer = new StringBuffer();
		result = receive(messageBuffer);
		message = messageBuffer.toString();
		if(result != null)
			return result;	

		lastServerMessage = getValueAt(6);
		loggedIn = false;
		lastServerID = -1;		

		result = disconnect();	
		if(result != null)
			return result;	
		
		logoutClean();
		return null;
	}
	
	private Result connect()
	{
		//Result result = disconnect();
		//if(result != null)
		//	return new Result(result.getMessage(), "DTO.Connection.Connect");
		disconnect();
		
		try
		{	
			socket = new Socket(serverName, serverPort);
			input = socket.getInputStream();
			output = socket.getOutputStream();
		}
		catch(IOException ex)
		{
			try {
				
				createSystemLogEntry(SystemLogType.APPLICATION, SystemLogLevel.ERROR, username, "", "Unable to connect to " + serverName + " on port " + serverPort);
			} catch (java.lang.Exception e) {
				//ignore and continue anyway
			}
			return new Result("Unable to connect to " + serverName + " on port " + serverPort, "DTO.Connection.Connect");
		}	
		
		connected = true;
		return null;
	}
	private Result disconnect()
	{
		if(socket != null)
		{	
			try
			{
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();
				connected = false;
				this.loggedIn = false;
				//this.firstTimeLogin = false;
			}
			catch(IOException ex)
			{
				try {
					createSystemLogEntry(SystemLogType.APPLICATION, SystemLogLevel.ERROR, username, "", ex.getMessage());
				} catch (java.lang.Exception e) {
					//ignore and continue anyway
				}
				return new Result(ex.getMessage(), "DTO.Connection.Disconnect.IOException");
			}
		}
		
		return null;
	}
	private void logoutClean()
	{
		listSize = 50;
		userID = 0;
//		username = "";
//		password = "";
		serverRequest = "";
		serverResponse = "";
		connected = false;
		loggedIn = false;
		//firstTimeLogin = true;
	}
	private Result request(String messageBody)
	{
		return request(messageBody, true);
	}
	
	private Result request(String messageBody, boolean allowReLogin)
	{
		Result result = null;
		listResponseMessage = null;

		//FWD-233
		
		if(!connected || !loggedIn)
		{			
			result = reLogin();
			if(result != null)
				return result;
			
			return request(messageBody, false);
		}	

		String message = NASMessageCodes.SEPARATOR + String.valueOf(NASMessageCodes.SOCK_MESSAGE).length() +
					NASMessageCodes.SEPARATOR + NASMessageCodes.SOCK_MESSAGE +  
					NASMessageCodes.SEPARATOR + String.valueOf(messageBody.length() + 2) +
					NASMessageCodes.SEPARATOR + NASMessageCodes.TERMINATOR + messageBody + NASMessageCodes.TERMINATOR +
					NASMessageCodes.SEPARATOR;

		if (LOG.isInfoEnabled() || LOG.isTraceEnabled())
		{
			logDTO(new Result("DTO in: " + message),SystemLogType.APPLICATION,SystemLogLevel.INFORMATION);
			LOG.info("DTO in: " + message);
		}			
		
		result = send(message);
		if(result != null)
		{
			logDTO(result,SystemLogType.APPLICATION,SystemLogLevel.ERROR);
			
			if(!allowReLogin)
				return result;

			if(!loggedIn)
				return result;

			logout();
			
			if (LOG.isTraceEnabled())
				LOG.info("DTO: Login 2" + result.toString());
			
			this.loggedIn = false;
			//this.firstTimeLogin = false;
			this.connect();
			result = reLogin();
					
			if(result != null)
				return result;

			return request(messageBody, false);
		}

		StringBuffer messageBuffer = new StringBuffer();
		result = receive(messageBuffer);
		
		if (LOG.isInfoEnabled() || LOG.isTraceEnabled())
		{
			logDTO(new Result("DTO out: " + messageBuffer),SystemLogType.APPLICATION,SystemLogLevel.INFORMATION);
			LOG.info("DTO out: " + messageBuffer);
		}
		
		message = messageBuffer.toString();
		if(result != null)
		{
			logDTO(result,SystemLogType.APPLICATION,SystemLogLevel.ERROR);
			
			if(!allowReLogin)
				return result;

			if(!loggedIn)
				return result;

			logout();
			this.loggedIn = false;
			//this.firstTimeLogin = false;
			this.connect();
			result = reLogin();
					
			if(result != null)
				return result;

			return request(messageBody, false);
		}

		if(message.length() == 0)
		{
			lastError = new Result("Unknown server response", "DTO.Connection.Request");
			logDTO(lastError,SystemLogType.APPLICATION,SystemLogLevel.ERROR);
			return lastError;
		}

		try
		{
			lastServerID  = Integer.valueOf(getValueAt(2)).intValue();
			lastServerMessage = getValueAt(4);
		}
		catch(NumberFormatException ex)
		{			
			lastError = new Result("Unknown server response", "DTO.Connection.Request");
			logDTO(lastError,SystemLogType.APPLICATION,SystemLogLevel.ERROR);
			return lastError;
		}	

		if(lastServerID < 0)
		{			
			lastError = new Result(lastServerID, lastServerMessage, "DTO.Connection.Request");
			if (lastError.getId() != -3 && lastError.getId() != -2)
				logDTO(lastError,SystemLogType.APPLICATION,SystemLogLevel.ERROR);
			return lastError;
		}		
		return null;
		
		//FWD-233
	}

	//FWD-233
	private void logDTO(Result result, SystemLogType logtype, SystemLogLevel level) 
	{
		if (result != null)
		{	
			LOG.info(result.getMessage());
			try {
				createSystemLogEntry(logtype, level, username, "",result.getMessage());
			} catch (java.lang.Exception e) {
				LOG.error("DTO error logging system log: " + e.getMessage());
			}
		}		
	}
	
	private DomainSession getDomainSession() throws java.lang.Exception
	{		
		return this.domainSession; 
	}

	
	private ISystemLogProvider getSystemLogProvider() throws java.lang.Exception
	{
		SystemLogFactory systemLogFactory = new SystemLogFactory(getDomainSession());
		if(systemLogFactory.hasSystemLogProvider())
			return systemLogFactory.getProvider();
		return null;
	}
	
	private ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String username, String computer, String message) throws java.lang.Exception
	{
		ISystemLogProvider provider = getSystemLogProvider();
		if(provider == null)
			return null;		
		
		String source = "";
		BuildInfo buildInfo = Configuration.getBuildInfo();
		
		if(buildInfo != null)
		{
			source = buildInfo.getName();
			source += " version ";
			source += buildInfo.getAppVersion() + " (" + buildInfo.getAppTimestamp() + ")";
		}
		
		String userAgent = "";
		return provider.save(new DateTime(), type, level, ConfigFlag.HOST_NAME.getValue(), username, source, computer, userAgent, getSessionId(), message);
	}			

	private String getSessionId() throws java.lang.Exception
    {
		SessionData sessData = (SessionData)getDomainSession().getAttribute(SessionConstants.SESSION_DATA);		
		if(sessData == null)
			return null;
    	String id = sessData.sessionId.get();
    	return id == null ? "" : id;
    }

	//FWD-233
	private Result receive(StringBuffer message)
	{
		serverResponse = "";
		String messageString = "";
		int messageLength = 0;

		try
		{
			byte[] length = new byte[NASMessageCodes.LEN_CHARS];
			int count = input.read(length, 0, NASMessageCodes.LEN_CHARS);
			if(count <= 0)
				return new Result("Invalid server response", "DTO.Connection.Receive");

			try
			{ 
				messageLength = Integer.parseInt(new String(length));
			}
			catch(NumberFormatException ex)
			{
				return new Result("Invalid server response", "DTO.Connection.Receive");
			}

			byte[] messageReceived = new byte[messageLength];
			count = input.read(messageReceived, 0, messageLength);
			if(count <= 0)
				return new Result("Invalid server response", "DTO.Connection.Receive");
	
			messageString = new String(messageReceived, 0, count);
			message.append(messageString);
	
			int overallCount = count;
			if(overallCount < messageLength - NASMessageCodes.LEN_CHARS)
				for(int x = 0; x < count; x++) 
					messageReceived[x] = 0;
			
			while(overallCount < messageLength - NASMessageCodes.LEN_CHARS)
			{
				count = input.read(messageReceived);
				overallCount += count;
				
				messageString = new String(messageReceived, 0, count);
				message.append(messageString);
			}
			
			serverResponse = message.toString();
		}
		catch (SocketException se)
		{
			return new Result("SocketException");
		}
		catch(IOException ex)
		{
			return new Result("Receive error: " + ex.getMessage(), "DTO.Connection.Receive");
		}
		return null;
	}
	private void splitResponseMessage()
	{
		listResponseMessage = serverResponse.split(String.valueOf(NASMessageCodes.SEPARATOR));
	}
	public String getValueAt(int position)
	{
		if(listResponseMessage == null)
				splitResponseMessage();
		if(listResponseMessage.length < position + 1)
			return "";
		return listResponseMessage[position];		
	}
	public String[] getResponseItems(String message)
	{
		if(message.length() <= 1)
			return null;
		message = message.substring(1);
		return message.split(String.valueOf(NASMessageCodes.TERMINATOR));
	}
	public int countResponseItems(String message)
	{
		if(message.length() <= 1)
			return 0;
		message = message.substring(1);
		return message.split(String.valueOf(NASMessageCodes.TERMINATOR)).length;
	}
	
	private String getAttributeValueFromResponseMessage(String[] items, String attributeName)
	{
		
		for(int x = 0; x < items.length; x++)
		{
			String[] item = items[x].split(String.valueOf(NASMessageCodes.ATTRIBUTEVALUESEPARATOR));
			String attName = item[0];
			attName = attName.replaceAll(String.valueOf(NASMessageCodes.TERMINATOR), null);
			if(attName == attributeName)
					return item[1];
		}
		return null;
	}
	public String getValueFor(String[] items, String attributeName)
	{
		for(int x = 0; x < items.length; x++)
		{
			String[] item = items[x].split(String.valueOf(NASMessageCodes.ATTRIBUTEVALUESEPARATOR));
			if(item[0].equals(attributeName))
			{	
				if(item.length == 1)	
					return "";
				else
					return item[1];
			}
		}
		return "";
	}
	

	public String getAttributeName(String pair)
	{
		String[] items = pair.split(String.valueOf(NASMessageCodes.ATTRIBUTEVALUESEPARATOR));
		if(items.length == 0)
			return "";
		return items[0];				
	}
	public String getAttributeValue(String pair)
	{
		String[] items = pair.split(String.valueOf(NASMessageCodes.ATTRIBUTEVALUESEPARATOR));
		if(items.length < 2)
			return "";
		return items[1];				
	}
	public Result list(String service, String message)
	{
		LOG.info("ConnectionImpl list service:" + service + " message:" + message);
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.List");
				
		String initialMessage = String.valueOf(NASMessageCodes.PAIRSEPARATOR);
		if(message.length() > 0)
			initialMessage += message + NASMessageCodes.PAIRSEPARATOR;

		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "LIST" + NASMessageCodes.PAIRSEPARATOR +
					"MAXNO" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + String.valueOf(listSize) + 
					initialMessage;

		return request(message);
	}
	public Result nextList(String service)
	{
		
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.NextList");
				
		String message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
						service + NASMessageCodes.PAIRSEPARATOR + 
						getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "NEXTLIST" + NASMessageCodes.PAIRSEPARATOR +
						"MAXNO" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + String.valueOf(listSize) + 
						NASMessageCodes.PAIRSEPARATOR; 
						

		Result result = request(message);
		if(result != null)
		{	
			lastError = result.cloneObject();
			if(result.getId() == -3)
				stopList(service);
		}
		return result;
	}
	public Result stopList(String service)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.StopList");
					
		String message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "STOPLIST" + NASMessageCodes.PAIRSEPARATOR;

		request(message);
		//JME: 20040519: Ignore any error returned from a STOPLIST call
		//if(result != null)
		//	return result;
		return null;
	}
	public Result executeAction(String service, String message, String action)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.ExecuteAction");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + action.toUpperCase() + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result transferData(String service, String message, String action)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.TransferData");

		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + action.toUpperCase() + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result get(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.Get");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "GET" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result getLast(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.GetLast");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "GETLAST" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result getForUpdate(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.GetForUpdate");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "GETFORUPD" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result insert(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.Insert");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "INSERT" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public Result update(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
			return new Result("Invalid service name.", "DTO.Connection.Update");
				
		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "UPDATE" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		return request(message);
	}
	public int count(String service, String message)
	{
		service = service.toUpperCase();
		if(service.trim().length() == 0)
		{
			lastError = new Result("Invalid service name.", "DTO.Connection.Count");
			return -1;
		}

		message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					service + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "COUNT" + NASMessageCodes.PAIRSEPARATOR +
					message + NASMessageCodes.PAIRSEPARATOR;

		Result result = request(message);
		if(result != null)
		{
			lastError = result.cloneObject();
			return -1;
		}

		try
		{
			return Integer.valueOf(getValueAt(2)).intValue();
		}
		catch(NumberFormatException ex)
		{
			lastError = new Result("Invalid server response.", "DTO.Connection.Count");
	
			return -1;
		}
	}
	
	public java.util.Calendar getDateTime()
	{
		String message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
						"DATIME" + NASMessageCodes.PAIRSEPARATOR + 
						getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "GET" + NASMessageCodes.PAIRSEPARATOR;
					
		Result result = request(message);
		if(result != null)
		{
			lastError = result;	
			return new GregorianCalendar();
		}	

		try
		{
			String[] items = splitResponseItem(getValueAt(6));

			String date = getAttributeValue(items[0]);
			String time = getAttributeValue(items[1]);

			int year = Integer.valueOf(date.substring(0, 4)).intValue();
			// Month value is 0-based. e.g., 0 for January.
			int month = Integer.valueOf(date.substring(4).substring(0, 2)).intValue() - 1;
			int day = Integer.valueOf(date.substring(6)).intValue();

			int hour = Integer.valueOf(time.substring(0, 2)).intValue();
			int minute = Integer.valueOf(time.substring(2).substring(0, 2)).intValue();
			int second = Integer.valueOf(time.substring(4)).intValue();
	
			GregorianCalendar calendar = new GregorianCalendar(year, month, day, hour, minute, second);			
			return calendar;
		}
		catch(NumberFormatException ex)
		{
			return new GregorianCalendar();
		}
	}
	public String getAttributeValueFromReturnMessage(String attributeName)
	{
		if(serverResponse.length() == 0)
			return null;
		String[] response = splitResponseItem(getValueAt(6));
		return getAttributeValueFromResponseMessage(response, attributeName);
	}
	private String getSendStringLength(String message)
	{
		String padString = "0000000000000000000000000";
		int length = message.length() + NASMessageCodes.LEN_CHARS;
		return padString.substring(0, NASMessageCodes.LEN_CHARS - String.valueOf(length).length()) + String.valueOf(length);
	}
	private Result send(String message)
	{
		serverRequest = "";
		if(message.length() == 0)
			return new Result("Invalid message", "DTO.Connection.Send");

		if(output == null)
			return new Result("Invalid connection", "DTO.Connection.Send");
		
		message = getSendStringLength(message) + message;
		serverRequest = message;
		
		byte[] messageToSend = message.getBytes();
		
		try
		{
			output.write(messageToSend);
		}
		catch(IOException ex)
		{
			return new Result("Send error: " + ex.getMessage(), "DTO.Connection.Send");
		}	
		
		return null;		
	}
	private Result readLoginDetails()
	{
		String message = "*SERVICE" + NASMessageCodes.ATTRIBUTEVALUESEPARATOR +
					"LOGIN_DETAILS" + NASMessageCodes.PAIRSEPARATOR + 
					getAction() + NASMessageCodes.ATTRIBUTEVALUESEPARATOR + "GET" + NASMessageCodes.PAIRSEPARATOR;

		Result result = request(message);
		if(result != null)
			return result;
                
		//try
		//{
			//String[] response = splitResponseItem(getValueAt(6));

		//}
		/*catch(Exception exception)
		{
			return new Result(exception.getMessage(), "DTO.Connection.ReadLoginDetails");
		}*/
	
		return null;
	}
	private String getServerName(String server)
	{
		if(server == null)
			return "";

		String upperServer = server.toUpperCase();
		if(upperServer.indexOf("NAS://") != 0)
			return "";

		upperServer = server.substring(6);

		int index = upperServer.indexOf(":", 0);
		if(index < 0)
		{
			index = upperServer.indexOf(",", 0);
			if(index < 0)
				return upperServer;
		}
				
		return upperServer.substring(0, index);
	}
	private int getServerPort(String server)
	{
		if(server == null)
			return -1;

		String upperServer = server.toUpperCase();
		if(upperServer.indexOf("NAS://") != 0)
			return -1;

		upperServer = server.substring(6);

		int index = upperServer.indexOf(":");
		if(index < 0)
		{
			index = upperServer.indexOf(",", 0);
			if(index < 0)
					return -1;
		}
				
		try
		{
			return Integer.valueOf(upperServer.substring(index + 1)).intValue();
		}
		catch(NumberFormatException ex)
		{
			return -1;
		}
	}
	private Result login(String username, String password, EncryptionType encryptionType, boolean encryptPassword)
	{
		Result result = null;
		serverRequest = "";
		serverResponse = "";
		listResponseMessage = null;

		if(!connected)
		{
			result = connect();
			if(result != null)
				return result;
		}

		if(loggedIn)
			return new Result("Already logged in", "DTO.Connection.Login");

		if(username.toLowerCase() != "stlogin")
		{
			if(encryptPassword)
				password = Encryption.encrypt(password, encryptionType);
		}

		String message = NASMessageCodes.SEPARATOR + String.valueOf(NASMessageCodes.SOCK_CONNECT).length() +
		NASMessageCodes.SEPARATOR + NASMessageCodes.SOCK_CONNECT +   
		NASMessageCodes.SEPARATOR + username.length() +  
		NASMessageCodes.SEPARATOR + username + 
		NASMessageCodes.SEPARATOR + password.length() + 
		NASMessageCodes.SEPARATOR + password + 
		NASMessageCodes.SEPARATOR;
		
		result = send(message);
		if(result != null)
		{
			logoutClean();
			return result;
		}

		StringBuffer messageBuffer = new StringBuffer();
		result = receive(messageBuffer);
		message = messageBuffer.toString();
		if(result != null)
		{
			logoutClean();
			return result;
		}

		try
		{
			lastServerID = Integer.valueOf(getValueAt(2)).intValue();
			userID = (lastServerID >= 0)?lastServerID:-1;
		}
		catch(NumberFormatException ex)
		{
			logoutClean();
			return new Result("Unable to get the user id", "DTO.Connection.Login");
		}

		if(lastServerID <= 0) 
		{
			if (ConfigFlag.DTO.DTO_CONNECTION_TYPE.getValue().equals("HEARTS") && lastServerID == 0);  // FWD-157
			else
			{
				lastServerMessage = getValueAt(4);
				int backupLastServerID = lastServerID;
				logoutClean();
				return new Result(backupLastServerID, lastServerMessage, "DTO.Connection.Login");
			}
		}
		else
			lastServerMessage = getValueAt(6);

		loggedIn = true;

		if(username == "stlogin")
			return readLoginDetails();

		return result;
	}
	
	private final int defaultListSize = 50;
	private int listSize = defaultListSize;
	private int userID = 0;
	private int serverPort = 0;
	private int lastServerID = 0;
	private boolean loggedIn = false;
	private boolean connected = false;
	private String username = "";
	private String password = "";
	private String serverName = "";
	private String serverRequest = "";
	private String serverResponse = "";
	private String lastServerMessage = "";	
	private String[] listResponseMessage = null;
	private EncryptionType encryptionType = EncryptionType.PRIME;
	private ServerCompatibility serverCompatibility = ServerCompatibility.JAVA;
	private Socket socket = null;
	private Result lastError = null;
	private InputStream	input = null;
	private OutputStream output = null;
	
	/**
	 * @return Returns the User ID.
	 */
	public int getUserID() 
	{
		return userID;
	}
	/**
	 * @return Returns the Username.
	 */
	public String getUsername() 
	{
		return username;
	}
	/**
	 * @return Returns the Server Name.
	 */
	public String getServerName() 
	{
		return serverName;
	}
	/**
	 * @return Returns the Server Port Number.
	 */
	public int getServerPort() 
	{
		return serverPort;
	}
	/**
	 * @return Returns the Server Request.
	 */
	public String getDebugServerRequest() 
	{
		return serverRequest;
	}
	/**
	 * @return Returns the Server Response.
	 */
	public String getDebugServerResponse() 
	{
		return serverResponse;
	}

	/**
	 * @return Returns the Encryption Type.
	 */
	public EncryptionType getEncryptionType() 
	{
		return encryptionType;
	}

	/**
	 * @param encryptionType - The Encryption Type to set.
	 */
	public void setEncryptionType(EncryptionType encryptionType) 
	{
		this.encryptionType = encryptionType;
	}

	/**
	 * @return Returns the Last Error.
	 */
	public Result getLastError() 
	{
		return lastError;
	}

	public static final ConnectionImpl DefaultConnection = new ConnectionImpl();
	/**
	 * @return Returns the List Size.
	 */
	public int getListSize() 
	{
		return listSize;
	}
	/**
	 * Sets the List Size.
	 * @param listSize The List Size to set.
	 */
	public void setListSize(int listSize) 
	{
		if(listSize < 1)
			listSize = defaultListSize;
		this.listSize = listSize;
	}
	/**
	 * Resets the List Size to the original value.
	 */ 
	public void resetListSize() 
	{
		this.listSize = defaultListSize;
	}
	public String encodeFieldValue(String attributeValue)
	{
		if(attributeValue == null || attributeValue.length() == 0)
		{	
			if(serverCompatibility == ServerCompatibility.JAVA)
					return "<null>";
			if(serverCompatibility == ServerCompatibility.C)
				return "";
		}
		return attributeValue;
	}
	public String decodeFieldValue(String attributeValue)
	{
		if(attributeValue == null)
			return "";
		if(attributeValue.equalsIgnoreCase("<null>") && serverCompatibility == ServerCompatibility.JAVA)
			return "";
		return attributeValue;
	}
	/**
	 * @return
	 */
	public ServerCompatibility getServerCompatibility()
	{
		return serverCompatibility;
	}

	/**
	 * @param compatibility
	 */
	public void setServerCompatibility(ServerCompatibility compatibility)
	{
		serverCompatibility = compatibility;
	}

	/**
	 * @see ims.dto.Connection#close()
	 */
	public void close() {
		if (LOG.isInfoEnabled())
			LOG.info("close()");
		disconnect();
		if (LOG.isInfoEnabled())
			LOG.info("EXIT close()");

	}

	/**
	 * @see ims.dto.Connection#open()
	 */
	public void open(DomainSession domainsession) {
		
		this.domainSession = domainsession;
		
		if (connected && loggedIn)
			return;
		
		connect();
	}

	private String getAction() // FWD-162
	{
		if (ConfigFlag.DTO.DTO_CONNECTION_TYPE.getValue().equals("HEARTS")) 
			return "*ACTION";
		else
			return "ACTION";
	}
}
