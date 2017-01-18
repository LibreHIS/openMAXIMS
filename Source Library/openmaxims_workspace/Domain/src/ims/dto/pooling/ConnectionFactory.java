/*
 * Created on May 24, 2004
 *
 */
package ims.dto.pooling;

import org.apache.commons.pool.impl.GenericObjectPool;

import ims.domain.DomainSession;
import ims.dto.Connection;
import ims.dto.config.ConnectionConfiguration;

/**
 * @author gcoghlan
 *
 */
public class ConnectionFactory implements ims.dto.ConnectionFactory {
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(ConnectionFactory.class);
	private org.apache.commons.pool.ObjectPool objectPool;
	
	/**
	 * 
	 */
	public ConnectionFactory(ConnectionConfiguration config, ims.dto.ConnectionFactory concreteFactory) {
		if (LOG.isInfoEnabled())
			LOG.info("Constructor ConnectinFactory(config="+config+", concreteFactory="+concreteFactory+")");
		PoolableConnectionFactory poolableConnectionFactory = 
			new PoolableConnectionFactory(concreteFactory);
		// pool configuration
		GenericObjectPool.Config poolConfig = poolConfiguration(config);		
		objectPool = new org.apache.commons.pool.impl.GenericObjectPool(
			poolableConnectionFactory, poolConfig);
		if (LOG.isInfoEnabled())
			LOG.info("EXIT Constructor ConnectinFactory(config="+config+", concreteFactory="+concreteFactory+")");

	}

	/**
	 * Derive the GenericObjectPool.Config configuration from the dto configuration
	 * @param config
	 * @return
	 */
	private GenericObjectPool.Config poolConfiguration(ConnectionConfiguration config) {
		GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
		Integer maxActive = config.getMaxActive();
		if ( null != maxActive )
			poolConfig.maxActive = maxActive.intValue();
		Integer maxIdle = config.getMaxIdle();
		if ( null != maxIdle )
			poolConfig.maxIdle = maxIdle.intValue();
		String whenExhaustedAction = config.getWhenExhaustedAction();
		if ( null != whenExhaustedAction ) {
			if ( ConnectionConfiguration.BLOCK_ACTION.equals(whenExhaustedAction)) {
				// block
				poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
			}
			else if ( ConnectionConfiguration.FAIL_ACTION.equals(whenExhaustedAction)) {
				// fail
				poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
			}
			else if ( ConnectionConfiguration.GROW_ACTION.equals(whenExhaustedAction)) {
				// grow
				poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_GROW;
			}
		}
		Integer maxWait = config.getMaxWait();
		if ( null != maxWait )
			poolConfig.maxWait = maxWait.intValue();
		if (LOG.isInfoEnabled())
			LOG.info("Pool Configuration: "+poolConfig);
		return poolConfig;
	}

	/**
	 * @see ims.dto.ConnectionFactory#getConnection()
	 */
	public Connection getConnection(DomainSession domainsession) {
		if (LOG.isInfoEnabled())
			LOG.info("Getting new PooledConnectionWrapper to access objectPool");
		return new PooledConnectionWrapper(objectPool);
	}

	public Connection getConnection(String username, String password, DomainSession domainsession) {
		return getConnection(domainsession);
	}

}
