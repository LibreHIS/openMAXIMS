/*
 * Created on 03-Feb-2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ims.dto.pooling;

import java.util.Calendar;
import java.util.HashMap;

import ims.crypto.EncryptionType;
import ims.dto.Connection;
import ims.dto.Result;
import ims.dto.ServerCompatibility;

/**
 * @author jmacenri
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SynchPoolConnWrapper implements Connection 
{
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(SynchPoolConnWrapper.class);
	private Connection conn;
	private boolean connected = false;
	private SynchListConnectionFactory factory;

	public SynchPoolConnWrapper(Connection conn, SynchListConnectionFactory factory)
	{
		this.conn = conn;
		this.factory = factory;
	}
	
	public void open(ims.domain.DomainSession domainsession) 
	{
		if (LOG.isInfoEnabled())
			LOG.info("open()");		

		conn.open(domainsession);
		connected = true;
		
		if (LOG.isInfoEnabled())
			LOG.info("EXIT open()");
	}

	public void close() 
	{
		if (LOG.isInfoEnabled())
			LOG.info("close()");
		if (connected)
		{
			factory.returnConnection(this);
			connected = false;
		}
		if (LOG.isInfoEnabled())
			LOG.info("EXIT close()");
	}

	public void setServerConnection(String serverAddress) 
	{
		conn.setServerConnection(serverAddress);
	}

	public void setServerConnection(String serverName, int serverPort) 
	{
		conn.setServerConnection(serverName, serverPort);
	}

	public Result login(String username, String password) 
	{
		return conn.login(username, password);
	}

	public Result reLogin() 
	{
		return conn.reLogin();
	}

	public Result logout() 
	{
		return conn.logout();
	}

	public String getValueAt(int position) 
	{
		return conn.getValueAt(position);
	}

	public String[] getResponseItems(String message) 
	{
		return conn.getResponseItems(message);
	}

	public int countResponseItems(String message) 
	{
		return conn.countResponseItems(message);
	}

	public String[] splitResponseItem(String message) 
	{
		return conn.splitResponseItem(message);
	}

	public String getValueFor(String[] items, String attributeName) 
	{
		return conn.getValueFor(items, attributeName);
	}

	public String getAttributeName(String pair) 
	{
		return conn.getAttributeName(pair);
	}

	public String getAttributeValue(String pair) 
	{
		return conn.getAttributeValue(pair);
	}

	public Result list(String service, String message) 
	{
		return conn.list(service, message);
	}

	public Result nextList(String service) 
	{
		return conn.nextList(service);
	}

	public Result stopList(String service) 
	{
		return conn.stopList(service);
	}

	public Result executeAction(String service, String message, String action) 
	{
		return conn.executeAction(service, message, action);
	}

	public Result transferData(String service, String message, String action) 
	{
		return conn.transferData(service, message, action);
	}

	public Result get(String service, String message) 
	{
		return conn.get(service, message);
	}

	public Result getLast(String service, String message) 
	{
		return conn.getLast(service, message);
	}

	public Result getForUpdate(String service, String message) 
	{
		return conn.getForUpdate(service, message);
	}

	public Result insert(String service, String message) 
	{
		return conn.insert(service, message);
	}

	public Result update(String service, String message) 
	{
		return conn.update(service, message);
	}

	public int count(String service, String message) 
	{
		return conn.count(service, message);
	}

	public Calendar getDateTime() 
	{
		return conn.getDateTime();
	}

	public String getAttributeValueFromReturnMessage(String attributeName) 
	{
		return conn.getAttributeValueFromReturnMessage(attributeName);
	}

	public int getUserID() 
	{
		return conn.getUserID();
	}

	public String getUsername() 
	{
		return conn.getUsername();
	}

	public String getServerName() 
	{
		return conn.getServerName();
	}

	public int getServerPort() 
	{
		return conn.getServerPort();
	}

	public String getDebugServerRequest() 
	{
		return conn.getDebugServerRequest();
	}

	public String getDebugServerResponse() 
	{
		return conn.getDebugServerResponse();
	}

	public EncryptionType getEncryptionType() 
	{
		return conn.getEncryptionType();
	}

	public void setEncryptionType(EncryptionType encryptionType) 
	{
		conn.setEncryptionType(encryptionType);
	}

	public Result getLastError() 
	{
		return conn.getLastError();
	}

	public int getListSize() 
	{
		return conn.getListSize();
	}

	public void setListSize(int listSize) 
	{
		conn.setListSize(listSize);
	}

	public void resetListSize() 
	{
		conn.resetListSize();
	}

	public String encodeFieldValue(String attributeValue) 
	{
		return conn.encodeFieldValue(attributeValue);
	}

	public String decodeFieldValue(String attributeValue) 
	{
		return conn.decodeFieldValue(attributeValue);
	}

	public ServerCompatibility getServerCompatibility() 
	{
		return conn.getServerCompatibility();
	}

	public void setServerCompatibility(ServerCompatibility compatibility) 
	{
		conn.setServerCompatibility(compatibility);
	}

	public HashMap splitResponseItemToMap(String message) {
		// Not Required
		return null;
	}

	public String getValueFor(HashMap items, String attributeName) {
		// Not Required
		return null;
	}
}
