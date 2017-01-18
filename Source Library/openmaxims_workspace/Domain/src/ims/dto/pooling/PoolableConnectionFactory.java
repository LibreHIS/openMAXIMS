/*
 * Created on May 24, 2004
 *
 */
package ims.dto.pooling;

import ims.dto.Connection;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * @author gcoghlan
 *
 */
public class PoolableConnectionFactory implements PoolableObjectFactory {
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(PoolableConnectionFactory.class);
	private final ims.dto.ConnectionFactory connectionFactory;
	
	/**
	 * 
	 */
	public PoolableConnectionFactory(ims.dto.ConnectionFactory connectionFactory) {
		if (LOG.isDebugEnabled())
			LOG.debug("PoolableConnectionFactory(connectionFactory="+connectionFactory+")");
		this.connectionFactory = connectionFactory;
	}

	/**
	 * @see org.apache.commons.pool.PoolableObjectFactory#makeObject()
	 */
	public Object makeObject() throws Exception {
		if (LOG.isInfoEnabled())
			LOG.info("makeObject()");
		return connectionFactory.getConnection(null);
	}

	/**
	 * @see org.apache.commons.pool.PoolableObjectFactory#destroyObject(java.lang.Object)
	 */
	public void destroyObject(Object obj) throws Exception {
		if (LOG.isInfoEnabled())
			LOG.info("destroyObject(obj="+obj+")");
		Connection connection = (Connection) obj;
		connection.close();
		if (LOG.isInfoEnabled())
			LOG.info("EXIT destroyObject");
	}

	/**
	 * @see org.apache.commons.pool.PoolableObjectFactory#validateObject(java.lang.Object)
	 */
	public boolean validateObject(Object obj) {
		if (LOG.isDebugEnabled())
			LOG.debug("validateObject(obj="+obj+")");
		return true;
	}

	/**
	 * @see org.apache.commons.pool.PoolableObjectFactory#activateObject(java.lang.Object)
	 */
	public void activateObject(Object obj) throws Exception {
		if (LOG.isDebugEnabled())
			LOG.debug("activateObject(obj="+obj+")");
	}

	/**
	 * @see org.apache.commons.pool.PoolableObjectFactory#passivateObject(java.lang.Object)
	 */
	public void passivateObject(Object obj) throws Exception {
		if (LOG.isDebugEnabled())
			LOG.debug("passivateObject(obj="+obj+")");
	}
}
