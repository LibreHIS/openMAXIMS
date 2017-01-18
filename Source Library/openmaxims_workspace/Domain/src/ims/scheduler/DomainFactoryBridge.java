package ims.scheduler;

import java.sql.Connection;
import java.util.List;

import ims.domain.DomainObject;
import ims.domain.IDomainCollectionGetter;
import ims.domain.IDomainGetter;
import ims.domain.ILightweightDomainFactory;
import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.lookups.Lookup;
import ims.domain.lookups.LookupInstance;

public final class DomainFactoryBridge implements ILightweightDomainFactory
{ 
	private static final long serialVersionUID = 1L;
	private ims.domain.DomainFactory domainFactory;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(DomainFactoryBridge.class);
	
	public DomainFactoryBridge(ims.domain.DomainSession domainSession)
	{
		log("Creating domain factory");
		domainFactory = new ims.domain.hibernate3.DomainFactory();
		domainFactory.setDomainSession(domainSession);
		log("Domain factory created");
	}
	
	public void beginTransaction()
	{
		if(!hasTransaction())
		{
			log("Starting transaction");
			domainFactory.beginTransaction();
			log("Transaction started");
		}
	}	
	private boolean hasTransaction()
	{
		return domainFactory.getTransaction() != null;
	}	
	public void commitTransaction() throws StaleObjectException
	{
		if(hasTransaction())
		{
			log("Commiting transaction");
			domainFactory.getTransaction().commit();
			domainFactory.setTransaction(null);
			log("Transaction commited");
		}
	}	
	public void rollbackTransaction()
	{
		if(hasTransaction())
		{
			log("Rolling back transaction");
			domainFactory.getTransaction().rollback();
			domainFactory.setTransaction(null);
			log("Transaction rolled back");
		}
	}	
	public List find(String hqlString)
	{
		return domainFactory.find(hqlString);
	}
	public List find(String query, String[] paramNames, Object[] paramValues)
	{
		return domainFactory.find(query, paramNames, paramValues);
	}
	public List find(String query, String[] paramNames, Object[] paramValues, int maxRec)
	{
		return domainFactory.find(query, paramNames, paramValues, maxRec);
	}
	public int count(String hql, final String[] paramNames, final Object[] paramValues)
	{
		return domainFactory.count(hql, paramNames, paramValues);
	}
	public DomainObject getDomainObject(Class domainClass, int id)
	{
		return domainFactory.getDomainObject(domainClass, id);
	}	
	public DomainObject getDomainObject(IDomainGetter getter)
	{
		return domainFactory.getDomainObject(getter);
	}
	public List<DomainObject> getDomainObjects(IDomainCollectionGetter getter)
	{
		return domainFactory.getDomainObjects(getter);
	}	
	public void save(DomainObject obj) throws StaleObjectException
	{
		log("Saving domain object");
		beginTransaction();
		domainFactory.save(obj);
		log("Domain object saved");
	}	
	public void delete(DomainObject obj) throws ForeignKeyViolationException
	{
		domainFactory.delete(obj);
	}
	public int delete(String queryString) throws ForeignKeyViolationException
	{
		return domainFactory.delete(queryString);
	}
	
	/*public void close() throws StaleObjectException
	{
		log("Closing domain factory");
		commitTransaction();
		domainFactory.close();
		log("Domain factory closed");
	}*/	
	private void log(String message)
	{
		LocalLogger.warn(message);
	}

	public Lookup getLookup(int id) 
	{
		return domainFactory.getLookup(id);
	}

	public LookupInstance getLookupInstance(int instanceId) 
	{
		return domainFactory.getLookupInstance(instanceId);
	}
	
	public Connection getjdbcConnection()
	{
		if (domainFactory == null)
			return null;
		
		return domainFactory.getJdbcConnection();
	}
}
