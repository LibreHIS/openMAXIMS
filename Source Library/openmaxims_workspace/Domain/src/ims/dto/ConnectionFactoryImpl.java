/*
 * Created on 16-Feb-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import ims.crypto.EncryptionType;
import ims.domain.DomainSession;
import ims.dto.config.ConnectionConfiguration;

/**
 * Abstract class for creation of Connection objects.
 * 
 * @author gcoghlan
 *
 */

public class ConnectionFactoryImpl implements ConnectionFactory {
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(ConnectionFactoryImpl.class);
	
	protected ConnectionConfiguration config;

	public ConnectionFactoryImpl(ConnectionConfiguration config) {
		if ( LOG.isDebugEnabled() )
			LOG.debug("Constructor called: ConnectionFactoryImpl(ConnectionConfiguration="+config);
		this.config = config;
	}

	/**
	 * Get a connection.
	 */
	public Connection getConnection(DomainSession domainsession) {
		String username = config.getUsername();
		String password = config.getPassword();
		return getConnection(username, password, domainsession);
	}
	
	/**
	 * Get a connection.
	 */
	public Connection getConnection(String username, String password, DomainSession domainsession) throws ResultException {
		
		if (config.isSharedLogin()) {
			username = config.getUsername();
			password = config.getPassword();
		}
		
		if (LOG.isInfoEnabled())
			LOG.info(">>getConnection()");
		Connection connection = new ConnectionImpl(config.getServerName(), config.getServerPort(),domainsession);

		EncryptionType encryptionType = config.getEncryptionType();
		ServerCompatibility serverCompatibility = config.getServerCompatibility();
		if (null != encryptionType)
			connection.setEncryptionType(encryptionType);
		if (null != serverCompatibility)
			connection.setServerCompatibility(serverCompatibility);
		Result loginResult = connection.login(username, password);
		
		// FWD-157 - If the code returned is 0, then no exception
		if (null != loginResult && loginResult.getId() != 0) 
		{
			// deal with login problem
			LOG.error("Connection Login Problem: connection.login(...) return Result:"+loginResult);
			throw new ResultException(loginResult);
		}
		if (LOG.isInfoEnabled())
			LOG.info("EXIT connection="+connection);
		return connection;
	}
}
