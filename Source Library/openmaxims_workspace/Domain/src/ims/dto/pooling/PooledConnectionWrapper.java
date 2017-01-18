/*
 * Created on May 24, 2004
 *
 */
package ims.dto.pooling;

import ims.crypto.EncryptionType;
import ims.domain.DomainSession;
import ims.domain.exceptions.DomainRuntimeException;
import ims.dto.BaseConnectionImpl;
import ims.dto.Connection;
import ims.dto.Result;
import ims.dto.ServerCompatibility;
import org.apache.commons.pool.ObjectPool;

import java.util.Calendar;

/**
 * Wraps access to a ims.dto.Connection object retrieved from a pool.
 * The implementation delegates to a real Connection retrieved from a pool.
 * All methods are delegated to this instance.
 * The <code>close()</code> method returns the instance to the pool.
 * Once <code>close()</code> has been called on a PooledConnectionWrapper it
 * can no longer be used.
 * @author gcoghlan
 *
 */
public class PooledConnectionWrapper extends BaseConnectionImpl implements Connection
{
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(PooledConnectionWrapper.class);
	private final ObjectPool pool;
	private Connection pooledConnection;

	/**
	 * 
	 */
	public PooledConnectionWrapper(ObjectPool pool) {
		if (LOG.isInfoEnabled())
			LOG.info("PooledConnectionWrapper(pool="+pool+")");
		this.pool = pool;
		pooledConnection = borrowConnection();
		if (LOG.isInfoEnabled())
			LOG.info("EXIT PooledConnectionWrapper(pool="+pool+")");

	}
	
	/**
	 * @see ims.dto.Connection#open()
	 */
	public void open(DomainSession domainsession) {
		if (LOG.isInfoEnabled())
			LOG.info("open()");
		if ( null == pooledConnection) {
			pooledConnection = borrowConnection();
		}
		if (LOG.isInfoEnabled())
			LOG.info("EXIT open()");
	}

	/**
	 * @see ims.dto.Connection#close()
	 */
	public void close() {
		if (LOG.isInfoEnabled())
			LOG.info("close()");
		if ( null != pooledConnection) {
			returnConnection();
		}
		else {
			//Connection must have already been closed.
			//Not sure if it is valid to ignore this situation.
			final String message = "Connection from pool should not be null. Already closed.";
			LOG.error(message);
			//throw new DomainRuntimeException(message);
			
		}
		if (LOG.isInfoEnabled())
			LOG.info("EXIT close()");
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public int count(String service, String message) {
		return pooledConnection.count(service, message);
	}

	/**
	 * @param message
	 * @return
	 */
	public int countResponseItems(String message) {
		return pooledConnection.countResponseItems(message);
	}

	/**
	 * @param attributeValue
	 * @return
	 */
	public String decodeFieldValue(String attributeValue) {
		return pooledConnection.decodeFieldValue(attributeValue);
	}

	/**
	 * @param attributeValue
	 * @return
	 */
	public String encodeFieldValue(String attributeValue) {
		return pooledConnection.encodeFieldValue(attributeValue);
	}

	/**
	 * @param service
	 * @param message
	 * @param action
	 * @return
	 */
	public Result executeAction(String service, String message, String action) {
		return pooledConnection.executeAction(service, message, action);
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result get(String service, String message) {
		return pooledConnection.get(service, message);
	}

	/**
	 * @param pair
	 * @return
	 */
	public String getAttributeName(String pair) {
		return pooledConnection.getAttributeName(pair);
	}

	/**
	 * @param pair
	 * @return
	 */
	public String getAttributeValue(String pair) {
		return pooledConnection.getAttributeValue(pair);
	}

	/**
	 * @param attributeName
	 * @return
	 */
	public String getAttributeValueFromReturnMessage(String attributeName) {
		return pooledConnection.getAttributeValueFromReturnMessage(attributeName);
	}

	/**
	 * @return
	 */
	public Calendar getDateTime() {
		return pooledConnection.getDateTime();
	}

	/**
	 * @return
	 */
	public String getDebugServerRequest() {
		return pooledConnection.getDebugServerRequest();
	}

	/**
	 * @return
	 */
	public String getDebugServerResponse() {
		return pooledConnection.getDebugServerResponse();
	}

	/**
	 * @return
	 */
	public EncryptionType getEncryptionType() {
		return pooledConnection.getEncryptionType();
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result getForUpdate(String service, String message) {
		return pooledConnection.getForUpdate(service, message);
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result getLast(String service, String message) {
		return pooledConnection.getLast(service, message);
	}

	/**
	 * @return
	 */
	public Result getLastError() {
		
		if (pooledConnection == null) return null;  // maxgen-308
		
		return pooledConnection.getLastError();
	}

	/**
	 * @return
	 */
	public int getListSize() {
		return pooledConnection.getListSize();
	}

	/**
	 * @param message
	 * @return
	 */
	public String[] getResponseItems(String message) {
		return pooledConnection.getResponseItems(message);
	}

	/**
	 * @return
	 */
	public ServerCompatibility getServerCompatibility() {
		return pooledConnection.getServerCompatibility();
	}

	/**
	 * @return
	 */
	public String getServerName() {
		return pooledConnection.getServerName();
	}

	/**
	 * @return
	 */
	public int getServerPort() {
		return pooledConnection.getServerPort();
	}

	/**
	 * @return
	 */
	public int getUserID() {
		return pooledConnection.getUserID();
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return pooledConnection.getUsername();
	}

	/**
	 * @param position
	 * @return
	 */
	public String getValueAt(int position) {
		return pooledConnection.getValueAt(position);
	}

	/**
	 * @param items
	 * @param attributeName
	 * @return
	 */
	public String getValueFor(String[] items, String attributeName) {
		return pooledConnection.getValueFor(items, attributeName);
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result insert(String service, String message) {
		return pooledConnection.insert(service, message);
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result list(String service, String message) {
		return pooledConnection.list(service, message);
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public Result login(String username, String password) {
		return pooledConnection.login(username, password);
	}

	/**
	 * @return
	 */
	public Result logout() {
		if (pooledConnection == null) return null; // maxgen-308
		return pooledConnection.logout();
	}

	/**
	 * @param service
	 * @return
	 */
	public Result nextList(String service) {
		return pooledConnection.nextList(service);
	}

	/**
	 * @return
	 */
	public Result reLogin() {
		if (pooledConnection == null) return null; // maxgen-308
		return pooledConnection.reLogin();
	}

	/**
	 * 
	 */
	public void resetListSize() {
		pooledConnection.resetListSize();
	}

	/**
	 * @param encryptionType
	 */
	public void setEncryptionType(EncryptionType encryptionType) {
		pooledConnection.setEncryptionType(encryptionType);
	}

	/**
	 * @param listSize
	 */
	public void setListSize(int listSize) {
		pooledConnection.setListSize(listSize);
	}

	/**
	 * @param compatibility
	 */
	public void setServerCompatibility(ServerCompatibility compatibility) {
		pooledConnection.setServerCompatibility(compatibility);
	}

	/**
	 * @param serverAddress
	 */
	public void setServerConnection(String serverAddress) {
		pooledConnection.setServerConnection(serverAddress);
	}

	/**
	 * @param serverName
	 * @param serverPort
	 */
	public void setServerConnection(String serverName, int serverPort) {
		pooledConnection.setServerConnection(serverName, serverPort);
	}

	/**
	 * @param message
	 * @return
	 */
	public String[] splitResponseItem(String message) {
		return pooledConnection.splitResponseItem(message);
	}

	/**
	 * @param service
	 * @return
	 */
	public Result stopList(String service) {
		return pooledConnection.stopList(service);
	}

	/**
	 * @param service
	 * @param message
	 * @param action
	 * @return
	 */
	public Result transferData(String service, String message, String action) {
		return pooledConnection.transferData(service, message, action);
	}

	/**
	 * @param service
	 * @param message
	 * @return
	 */
	public Result update(String service, String message) {
		return pooledConnection.update(service, message);
	}

	private Connection borrowConnection() {
		if (LOG.isInfoEnabled())
			LOG.info("borrowConnection()");
		try {
			Connection conn =(Connection) pool.borrowObject();
			if (LOG.isInfoEnabled())
				LOG.info("EXIT borrowConnection() - conn (" + conn + ")"); 
			return conn;
		}
		catch(Exception e) {
			LOG.error("Failed to borrow connection from pool.", e);
			throw new DomainRuntimeException(e);
		}
	}		
	
	private void returnConnection() {
		if (LOG.isInfoEnabled())
			LOG.info("returnConnection:"+pooledConnection);
		try {
			pool.returnObject(pooledConnection);
		}
		catch(Exception e) {
			LOG.error("Failed to return connection to pool.", e);
			throw new DomainRuntimeException(e);
		}
		finally {
			pooledConnection = null;
		}
		if (LOG.isInfoEnabled())
			LOG.info("EXIT returnConnection:"+pooledConnection);
	}

}
