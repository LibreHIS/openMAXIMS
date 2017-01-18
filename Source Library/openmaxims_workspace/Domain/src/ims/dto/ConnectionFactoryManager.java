/*
 * Created on May 24, 2004
 *
 */
package ims.dto;

import ims.configuration.ConfigFlag;
import ims.domain.DomainSession;
import ims.dto.config.ConnectionConfiguration;
import ims.dto.pooling.SynchListConnectionFactory;

/**
 * The ConnectionFactoryManager determines what kind of ConnectionFactory to use
 * to provide Connections.
 * The implemenation uses #ims.dt.config.PropertiesConfiguration to determine if
 * connection pooling is in use.
 * If connection pooling is in use then the ConnectionFactory will be an instance of
 * #ims.dto.pooling.ConnectionFactory.
 * The pooling factory delegates to a concrete ConnectionFactory to use to fills its
 * pool of connections.
 * The concrete ConnectionFactory is an instance of #ims.dto.ConnectionFactoryImpl.
 * This is used directly if pooling is switched off.
 * @author gcoghlan
 *
 */
public class ConnectionFactoryManager {
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(ConnectionFactoryManager.class);
	
	private ConnectionFactory factory;

	/**
	 * 
	 */
	private ConnectionFactoryManager() {
		LOG.info("Construction of ConnectionFactoryManager");
		ConnectionConfiguration config =
			new ims.dto.config.PropertiesConfiguration();
		ims.dto.ConnectionFactory concreteFactory =
			new ConnectionFactoryImpl(config);
		if (config.isConnectionPooling()) 
		{
			if (LOG.isDebugEnabled())
				LOG.debug("Connection Pooling is ON. Shared Login is ON");
			if (ConfigFlag.DTO.CONNECTION_POOL_TYPE.getValue().equals("SYNCHLIST"))
			{
				factory = new SynchListConnectionFactory(config, concreteFactory);				
			}
			else
			{
				factory = new ims.dto.pooling.ConnectionFactory(config, concreteFactory);				
			}
		}
		else if (config.isSharedLogin()){
			if (LOG.isDebugEnabled())
				LOG.debug("Connection Pooling is OFF. Shared Login is ON");
			factory = concreteFactory;
		}
		else {
			if (LOG.isDebugEnabled())
				LOG.debug("Connection Pooling is OFF. Shared Login is OFF");
			factory = concreteFactory;	
		}
	}

	public static ConnectionFactory getFactory() {
		if (LOG.isDebugEnabled())
			LOG.debug(">>getFactory()");
		return SingletonHolder.instance.factory;
	}
	
	public static Connection getConnection(String username, String password,DomainSession domainsession) throws ResultException {
		if (LOG.isDebugEnabled())
			LOG.debug(">>getConnection(u,p)");
		
		return getFactory().getConnection(username, password,domainsession);
	}
	
	private static class SingletonHolder {
		private static ConnectionFactoryManager instance = new ConnectionFactoryManager();
	}
}
