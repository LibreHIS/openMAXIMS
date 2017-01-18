/*
 * Created on 03-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ims.dto.pooling;

import java.util.ArrayList;
import java.util.List;

import ims.domain.DomainSession;
import ims.dto.Connection;
import ims.dto.ConnectionFactory;
import ims.dto.config.ConnectionConfiguration;

/**
 * @author jmacenri
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SynchListConnectionFactory implements ConnectionFactory 
{ 
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(SynchListConnectionFactory.class);
	private ims.dto.ConnectionFactory concreteFactory;
	private List pool;
	

	@SuppressWarnings("unchecked")
	public SynchListConnectionFactory(ConnectionConfiguration config, ims.dto.ConnectionFactory concreteFactory) 
	{
		if ( LOG.isDebugEnabled() )
			LOG.debug("Constructor called: SynchListConnectionFactory(ConnectionConfiguration="+config);
		this.concreteFactory = concreteFactory;
		pool = java.util.Collections.synchronizedList(new ArrayList());
	}

	public Connection getConnection(String username, String password, DomainSession domainsession) 
	{
		return getConnection(domainsession);
	}
	
	public synchronized Connection getConnection(DomainSession domainsession) 
	{
		if (LOG.isInfoEnabled())
			LOG.info("Getting new SynchPoolConnWrapper to access connection Pool");
		Connection conn;
		if (pool.size() == 0)
		{
			conn = concreteFactory.getConnection(domainsession);
			return new SynchPoolConnWrapper(conn, this);
		}
		else return (Connection)(pool.remove(pool.size()-1));
	}

	@SuppressWarnings("unchecked")
	public synchronized void returnConnection(Connection conn)
	{
		if (!pool.contains(conn))
		{
			pool.add(conn);			
		}
		if (LOG.isInfoEnabled())
			LOG.info("SynchPool size now = " + pool.size());
	}
}
