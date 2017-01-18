package ims.dto;

import ims.crypto.EncryptionType;
import ims.domain.DomainSession;

import java.util.HashMap;

/**
 * @author gcoghlan
 */
public interface Connection 
{
	/**
	 * Open the connection ; this may be called after #close()
	 *
	 */
	public void open(DomainSession domainsession);
	
	/**
	 * Disconnect the connection : release any resources
	 * held by the connection.
	 * The connection can no longer be used unless #open() is called.
	 */
	public void close();
	
	/**
	 * Sets the connection address.
	 * @param serverAddress - The address of the server in the format nas://server:port
	 */
	public void setServerConnection(String serverAddress);
	
	/**
	 * Sets the connection address.
	 * @param serverName - The server name or IP address
	 * @param serverPort - The server port
	 */
	public void setServerConnection(String serverName, int serverPort);
	
	/**
	 * Performs login against the current connection. If the result returned is not null an error occured.
	 * @param username - The username used to connect
	 * @param password - The password used to connect
	 * @return - ims.dto.Result - If the result returned is not null an error occured
	 */
	public Result login(String username, String password);
	
	/**
	 * Performs re-login against the current connection. This method is called by the client class and is not recommended to be called directly.
	 * @return - ims.dto.Result - If the result returned is not null an error occured
	 */
	public Result reLogin();
	
	/**
	 * Performs logout against the current connection. If the result returned is not null an error occured.
	 * @return - ims.dto.Result - If the result returned is not null an error occured 
	 */
	public Result logout();	
	
	public String getValueAt(int position);
	
	public String[] getResponseItems(String message);
	
	public int countResponseItems(String message);
	
	public String[] splitResponseItem(String message);

	public HashMap splitResponseItemToMap(String message);
	
	public String getValueFor(String[] items, String attributeName);
	public String getValueFor(HashMap items, String attributeName);
	
	public String getAttributeName(String pair);
	
	public String getAttributeValue(String pair);
	
	public Result list(String service, String message);
	
	public Result nextList(String service);
	
	public Result stopList(String service);
	
	public Result executeAction(String service, String message, String action);
	
	public Result transferData(String service, String message, String action);
	
	public Result get(String service, String message);
	
	public Result getLast(String service, String message);
	
	public Result getForUpdate(String service, String message);
	
	public Result insert(String service, String message);
	
	public Result update(String service, String message);
	
	public int count(String service, String message);
		
	public java.util.Calendar getDateTime();
	
	public String getAttributeValueFromReturnMessage(String attributeName);
	
	/**
	 * @return Returns the User ID.
	 */
	public int getUserID(); 
	
	/**
	 * @return Returns the Username.
	 */
	public String getUsername();
	
	/**
	 * @return Returns the Server Name.
	 */
	public String getServerName(); 
	
	/**
	 * @return Returns the Server Port Number.
	 */
	public int getServerPort(); 
	
	/**
	 * @return Returns the Server Request.
	 */
	public String getDebugServerRequest(); 
	
	/**
	 * @return Returns the Server Response.
	 */
	public String getDebugServerResponse(); 
	
	/**
	 * @return Returns the Encryption Type.
	 */
	public EncryptionType getEncryptionType();

	/**
	 * @param encryptionType - The Encryption Type to set.
	 */
	public void setEncryptionType(EncryptionType encryptionType);

	/**
	 * @return Returns the Last Error.
	 */
	public Result getLastError(); 
	
	/**
	 * @return Returns the List Size.
	 */
	public int getListSize();
	
	/**
	 * Sets the List Size.
	 * @param listSize The List Size to set.
	 */
	public void setListSize(int listSize);
	
	/**
	 * Resets the List Size to the original value.
	 */ 
	public void resetListSize(); 
	
	public String encodeFieldValue(String attributeValue);
	
	public String decodeFieldValue(String attributeValue);
	
	/**
	 * @return
	 */
	public ServerCompatibility getServerCompatibility();
	
	/**
	 * @param compatibility
	 */
	public void setServerCompatibility(ServerCompatibility compatibility);
}
