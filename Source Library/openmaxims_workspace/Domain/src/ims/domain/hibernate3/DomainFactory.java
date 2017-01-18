/*
 * Created on 07-Jan-04
 *
 */
package ims.domain.hibernate3;


import ims.configuration.EnvironmentConfig;
import ims.configuration.ImportedObject;
import ims.configuration.InitConfig;
import ims.configuration.RecordedInError;
import ims.configuration.RulesEngine;
import ims.domain.DomainObject;
import ims.domain.DomainSession;
import ims.domain.IDomainCollectionGetter;
import ims.domain.IDomainGetter;
import ims.domain.Transaction;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UnqViolationUncheckedException;
import ims.domain.impl.DomainImplProxyHandler;
import ims.domain.lookups.Lookup;
import ims.domain.lookups.LookupInstance;
import ims.framework.FormName;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.rules.engine.RulesEngineFactory;
import ims.rules.engine.RulesRuntimeEngine;
import ims.rules.exceptions.RulesEngineRuntimeException;
import ims.utils.Logging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.type.Type;

/**
 * @author gcoghlan
 *
 */
public class DomainFactory implements ims.domain.DomainFactory, Serializable 
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logging.getLogger(DomainFactory.class);
	public static boolean checkDirty=false;
	public ArrayList dirtyProperties=new ArrayList();
	public ArrayList dirtyEntities=new ArrayList();
	private Transaction transaction = null;	
	private DomainSession domainSession;
	private Session hibSession;

	/**
	 * Tries to create the Hibernate SessionFactory.
	 * @return
	 * @throws DomainRuntimeException if the factory cannot be configured or built.
	 */
	public SessionFactory getSessionFactory()
	{
		return Registry.getInstance().getSessionFactory();
	}

	public DomainFactory()
	{
	}
	
	public DomainObject getDomainObject(IDomainGetter getter)
	{
		if (getter == null) 
			throw new DomainRuntimeException("IDomainGetter parameter to getDomainObject in DomainFactory cannot be null.");
		
		return getDomainObject(getter.getDomainClass(), getter.getDomainId());
	}
	public List<DomainObject> getDomainObjects(IDomainCollectionGetter getter)
	{
		if (getter == null) 
			throw new DomainRuntimeException("IDomainCollectionGetter parameter to getDomainObjects in DomainFactory cannot be null.");
		
		List<DomainObject> result = new ArrayList<DomainObject>();
		
		for(int x = 0; x < getter.getIDomainGetterItems().length; x++)
		{
			IDomainGetter item = getter.getIDomainGetterItems()[x]; 
			result.add(getDomainObject(item));
		}
		
		return result;
	}
	/**
	 * 
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, java.lang.Integer)
	 */
	public DomainObject getDomainObject(Class domainClass, Integer id)
	{
		if (id == null) 
			throw new DomainRuntimeException("Integer id parameter to getDomainObject in DomainFactory cannot be null.");
		
		if (domainClass == null)
			throw new DomainRuntimeException("Class parameter to getDomainObject in DomainFactory cannot be null.");
		
		LOG.info(">getDomainObject(Integer=" + id + ", Class=" + domainClass.getName() + ")");		
		try
		{
			DomainObject domainObject = (DomainObject) getObject(domainClass, id);
			LOG.debug(">getDomainObject Exit");
			return domainObject;
		}
		catch (HibernateException e)
		{
			LOG.error("Could not get DomainObject for id:" + id, e);
			throw new DomainRuntimeException(e);
		}
	}

	/**
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, int)
	 */
	public DomainObject getDomainObject(Class domainClass, int id)
	{
		return getDomainObject(domainClass, new Integer(id));
	}

	/**
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, java.lang.String)
	 */
	public List listDomainObjects(Class domainClass, String queryString)
	{
		String hibernateQueryString = getClassQueryString(domainClass, queryString);
		return find(hibernateQueryString);		
	}

	private String getClassQueryString(Class domainClass, String queryString) {
		String domainClassName = domainClass.getName();
		String hibernateQueryString = "from "+ domainClassName + " " +queryString;
		return hibernateQueryString;
	}
	
	/** 
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class)
	 */
	public List listDomainObjects(Class domainClass) {
		return listDomainObjects(domainClass, "");
	}
		
	/**
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, java.lang.String, java.lang.Object, int)
	 */
	public List listDomainObjects(Class domainClass, String queryString, Object value, int type) {
		String hibernateQueryString = getClassQueryString(domainClass, queryString);
		return find(hibernateQueryString, value, type);
	}

	/**
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, java.lang.String, java.lang.Object[], int[])
	 */
	public List listDomainObjects(Class domainClass, String queryString, Object[] values, int[] types) {
		String hibernateQueryString = getClassQueryString(domainClass, queryString);
		return find(hibernateQueryString, values, types);
	}

	/**
	 * Get the Lookup for a given id or null if no lookup for that
	 * id exists.
	 * @see ims.domain.DomainFactory#getLookup(int)
	 */
	public Lookup getLookup(int id)
	{
		LOG.info(">getLookup(int=" + id + ")");
		try
		{
			Lookup lookup = (Lookup) getObject(Lookup.class, id);
			LOG.debug(">getLookup Exit");
			return lookup;
		}
		catch (HibernateException e)
		{
			LOG.error("Could not getLookup for id:" + id, e);
			throw new DomainRuntimeException(e);
		}
	}	
	
	private Object getObject(Class objectClass, int id) 
	{
		return getObject(objectClass, new Integer(id));
	}

	private Object getObject(Class objectClass, Integer id)
	{
		if (id == null) return null;
		LOG.debug(">getObject(int=" + id + ", Class=" + objectClass.getName() + ")");
		Session session = getSession();
		Object object = session.get(objectClass, id);
		LOG.debug(">getObject Exit");
		return object;
	}

	/**
	 * @see ims.domain.DomainFactory#getDomainObject(java.lang.Class, java.util.Properties)
	 */
	public List listDomainObjects(Class domainClass, Properties properties)
	{
		LOG.info(">listDomainObjects(Class=" + domainClass.getName() + ", Properties=" + properties + ")");
		Session session = getSession();
		Criteria criteria = session.createCriteria(domainClass);
		Set keySet = properties.keySet();
		Iterator iterator = keySet.iterator();
		while (iterator.hasNext())
		{
			String propertyName = (String) iterator.next();
			Object value = properties.get(propertyName);
			criteria.add(Restrictions.eq(propertyName, value));
		}
		List list = criteria.list();

		LOG.debug(">listDomainObjects ListExit");
		return list;
	}

	private void saveObject(Object object) throws StaleObjectException
	{
		saveObject(object, true);
	}

	private int applyRules(Object entity, Integer entityId)
	{
		if(InitConfig.getRulesEngine() == RulesEngine.NONE)
			return -1;
		
		ims.rules.engine.RulesEngine rulesEngine = null;

		rulesEngine = RulesEngineFactory.getInstance();
		if (rulesEngine == null)
			return -1;
				
		HashMap<String, Serializable> global = new HashMap<String, Serializable>();
		global.put("engine", new RulesRuntimeEngine(rulesEngine, this, domainSession, entityId));
		global.put("factory", this);
		Object[] facts = new Object[] { entity };
		
		return rulesEngine.fireAllRules(global, facts);
	}
	
	private void saveObject(Object object, boolean flushSession) throws StaleObjectException 
	{
		LOG.debug(">saveObject");
		
		checkTrans();
		
		Session session = getSession();
		Integer entityId = null;
		boolean shouldFireRules = true;
				
		if(object instanceof DomainObject)
		{
			DomainObject domainObject = (DomainObject)object; 
			entityId = domainObject.getId();
			shouldFireRules = domainObject.shouldFireRules();
		}
		
		try
		{
			session.save(object);
			
			if (flushSession)
				session.flush();			
		}
		catch (HibernateException e)
		{
			ReportException(e);
			
			if ( e instanceof StaleObjectStateException) 
			{
				StaleObjectStateException staleException =
					(StaleObjectStateException) e;
				
				Integer id = (Integer) staleException.getIdentifier();
				String className = staleException.getEntityName();
				ims.domain.DomainObject domainObject = null;
				try 
				{
					Class domainClass = Class.forName(className);
					domainObject = getDomainObject(domainClass, id);
					refresh(domainObject);
				}
				catch (Exception secondary) 
				{
					// Suppress secondary exception
				}

				// Use this whether domainObject is null or not.
				// If domainObject is null, will indicate object no longer exists.
				throw new StaleObjectException(domainObject, e);
			}
			else if (e instanceof ConstraintViolationException || e instanceof SQLGrammarException) // Not sure if this needs to work on getCause()
			{
				int errorCode;
				if (e instanceof ConstraintViolationException)
					errorCode = ((ConstraintViolationException)e).getErrorCode();
				else
					errorCode = ((SQLGrammarException)e).getErrorCode();
				
				if (errorCode == ORACLE_UNQ_VIOLATION_ERROR || errorCode == SYBASE_UNQ_VIOLATION_ERROR || errorCode == SQLSERVER_UNQ_VIOLATION_ERROR)
				{
					// JME: 20051128: Want to handle the discarding of the transaction here, so
					// Impl code can catch the UnqConstraintViolationException, and execute further
					// database calls within the catch block to ascertain more details of the error situation.
					if (transaction != null)
					{
						try 
						{
							transaction.rollback();
						}
						finally 
						{
							transaction.abandon();				
						}										
					}
					setTransaction(null);
					throw new UnqViolationUncheckedException(e);
				}
			}

			String message = "Error during save.";
			LOG.error(message, e);
			throw e;
		}
		
		// We apply the rules before attempting to save
		try
		{
			if(shouldFireRules)
			{
				applyRules(object, entityId);
			}
		}
		catch (RulesEngineRuntimeException ce)
		{
			if (ce.getCause()!=null&&ce.getCause().getCause()!=null&&ce.getCause().getCause().getCause()!=null)
			{
				Throwable e = ce.getCause().getCause().getCause();
				if (e instanceof StaleObjectException)
					throw (StaleObjectException)e;
				else if ( e instanceof StaleObjectStateException) 
				{
					StaleObjectStateException staleException =
						(StaleObjectStateException) e;
					Integer id = (Integer) staleException.getIdentifier();
					String className = staleException.getEntityName();
					ims.domain.DomainObject domainObject = null;
					try {
						Class<?> domainClass = Class.forName(className);
						domainObject = getDomainObject(domainClass, id);
						refresh(domainObject);
					}
					catch(Exception secondary) {
						// suppress secondary exception
					}
					//Use this whether domainObject is null or not.
					//If domainObject is null, will indicate object no longer exists.
					throw new StaleObjectException(domainObject, e);
				}
				else if (e instanceof UnqViolationUncheckedException || e instanceof ConstraintViolationException || e instanceof SQLGrammarException) //Not sure if this needs to work on getCause()
				{
					int errorCode=0;
					
					
					if (e instanceof ConstraintViolationException)
						errorCode = ((ConstraintViolationException)e).getErrorCode();
					else if(e instanceof SQLGrammarException)
						errorCode=((SQLGrammarException)e).getErrorCode();
					
					if (e instanceof UnqViolationUncheckedException||errorCode == ORACLE_UNQ_VIOLATION_ERROR || errorCode == SYBASE_UNQ_VIOLATION_ERROR || errorCode == SQLSERVER_UNQ_VIOLATION_ERROR)
					{
						//JME: 20051128: Want to handle the discarding of the transaction here, so
						//Impl code can catch the UnqConstraintViolationException, and execute further
						//database calls within the catch block to ascertain more details of the error situation.
						if (transaction != null)
						{
							try 
							{
								transaction.rollback();
							}
							finally 
							{
								transaction.abandon();				
							}										
						}
						setTransaction(null);
						throw new UnqViolationUncheckedException(e);
					}
				}
			}
			LOG.error( ce);
			throw new RulesEngineRuntimeException(ce);
		}
		catch (Exception ex)
		{			
			throw new RulesEngineRuntimeException(ex);				
		}
		LOG.debug(">saveObject Exit");
	}

	private void ReportException(Exception e) 
	{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(bo);
		e.printStackTrace(ps);
		getDomainSession().createSystemLogEntry(SystemLogType.APPLICATION, SystemLogLevel.WARNING, bo.toString());
	}
	
	/**
	 * saveNoFlush - called from within the ImsInterceptor, it will call the saveObject
	 * method passing in a parameter indicating that a flush is not required.
	 * 
	 * @param domainObject
	 * @throws StaleObjectException
	 */
	public void saveNoFlush(DomainObject domainObject) throws StaleObjectException
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">save(DomainObject=" + domainObject + ")");
		saveObject(domainObject, false);
	}
	
	/**
	 * 
	 * @see ims.domain.DomainFactory#save(ims.domain.DomainObject)
	 */
	public void save(DomainObject domainObject) throws StaleObjectException
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">save(DomainObject=" + domainObject + ")");
		saveObject(domainObject);
	}

	public void save(ims.domain.lookups.LookupInstance instance) throws StaleObjectException
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">save(LookupInstance=" + instance + ")");
		saveObject(instance);
	}
	
	/**
	 * 
	 * @see ims.domain.DomainFactory#save(ims.domain.lookups.Lookup)
	 */
	public void save(ims.domain.lookups.Lookup lookupType) throws StaleObjectException
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">save(Lookup=" + lookupType + ")");
		saveObject(lookupType);
	}

	/**
	 * 
	 * @see ims.domain.DomainFactory#update(ims.domain.DomainObject)
	 */
	public void update(DomainObject domainObject)
	{
		LOG.debug(">update Class=" + domainObject.getClass().getName());
		checkTrans();

		if (LOG.isDebugEnabled())
			LOG.debug(">update(DomainObject=" + domainObject + ")");
		Session session = getSession();		
		session.update(domainObject);
		LOG.debug(">update Exit");
	}

	/**
	 * 
	 * @see ims.domain.DomainFactory#refresh(ims.domain.DomainObject)
	 */
	public void refresh(DomainObject domainObject)
	{
		try
		{
			LOG.debug(">refresh Class=" + domainObject.getClass().getName());
			
			Session session = getSession();
			session.refresh(domainObject);
			
			LOG.debug(">refresh Exit");
			
		}
		catch (HibernateException e)
		{
			String message = "Error during refresh.";
			LOG.error(message, e);
			throw new DomainRuntimeException(message, e);
		}
	}

	/**
	 * @see ims.domain.DomainFactory#refresh(ims.domain.lookups.Lookup)
	 */
	public void refresh(Lookup lookup)
	{
		LOG.debug(">refreshLookup");
		Session session = getSession();
		session.refresh(lookup);
		LOG.debug(">refreshLookup Exit");
	}

	/**
	 * Get the hibernate session from the current DomainSession.
	 * If create is true then create one if it doesn't already exist.
	 * @param create true if a new hibernate session should be created if one does not exist.
	 * @return the hibernate session from the current DomainSession.
	 */
	protected Session getSession(boolean create)
	{
		DomainSession domainSession = getDomainSession();
		if (null == this.hibSession && create)
		{
			LOG.debug("getSession - hibSession is null - create new one");
			IAppUser user = domainSession.getUser();
			ImsInterceptor interceptor = new ImsInterceptor(user, domainSession, this);
			LOG.debug("Interceptor created");
			try
			{			
				this.hibSession = getSessionFactory().openSession(interceptor);
			}
			catch(HibernateException e)
			{
				throw new DomainRuntimeException("Could not open hibernate session.", e);
			}
		}
		
		LOG.debug("getSession - returning");
		return this.hibSession;
	}
	
	/**
	 * Get the hibernate session from the current DomainSession.
	 * @return the hibernate session from the current DomainSession
	 * @throws DomainRuntimeException if the session cannot be openned or reconnected.
	 */
	protected Session getSession()
	{
		return getSession(true);
	}

	/**
	 * @see ims.domain.DomainFactory#loadDomainObject(java.lang.Class, int)
	 */
	public DomainObject loadDomainObject(Class domainClass, int id) 
	{
		LOG.debug(">loadDomainObject(int=" + id + ", Class=" + domainClass.getName() + ")");
		try
		{
			return (DomainObject) loadObject(domainClass, id);
		}
		catch (HibernateException e)
		{
			throw new DomainRuntimeException("Could not load domain object.", e);
		}
	}
	
	/**
	 * Close the resources used by the factory.
	 * This disconnects the hibernate session, but does not close or
	 * remove the reference to it from the DomainSession.
	 * If an exception occurs while disconnecting then the hibernate session will be
	 * closed + discarded.
	 * The factory discards its reference to the current DomainSession.
	 * After calling close the factory cannot be used again unless it is
	 * provided with a domain session via a call to @see setDomainSession
	 * @throws DomainRuntimeException if an exception occurs disconnecting the session.
	 */
	public void close()
	{
		Session hibernateSession = getSession(false);
		if (null != hibernateSession && hibernateSession.isConnected())
		{
			try
			{
				hibernateSession.disconnect();
			}
			catch(HibernateException e)
			{
				discardSession();
				throw new DomainRuntimeException(e);
			}
			finally
			{
				if (DomainImplProxyHandler.isProxyStartTrans())
				{
					setDomainSession(null);					
				}
				else
				{
					//TODO					
					//Not sure if need to set domain session to null here. Causes problems if do
					//Need to check through if causes memory leak.
				}
			}
		}
	}

	private Object loadObject(Class objectClass, int id)
	{
		LOG.debug("loadObject Class = " + objectClass.getName() + " ID = " + id);
		Session session = getSession();
		Object object = session.load(objectClass, new Integer(id));
		LOG.debug("loadObject Exit");
		return object;		
	}
	
	/**
	 * A transaction must be started before calling this.
	 * If a problem occurs then the transaction must be rolled back
	 * + the current session must be abandoned.
	 * This is the responsibility of the DomainImpl object.
	 * @see ims.domain.DomainFactory#delete(ims.domain.DomainObject)
	 */
	public void delete(DomainObject obj) throws ForeignKeyViolationException
	{	
		deleteObject(obj);
	}

	private void deleteObject(Object obj) throws ForeignKeyViolationException
	{
		checkTrans();

		Session session = getSession();
		try
		{
			session.delete(obj);
			this.getSession().flush(); // Force a flush so that impl's can catch any FK constraint possible errors
		}
		catch(HibernateException e)
		{
			if(e instanceof ConstraintViolationException)
			{
				throw new ForeignKeyViolationException(e);
			}
			else if(e.getCause() != null && e.getCause() instanceof ConstraintViolationException)
			{
				throw new ForeignKeyViolationException(e);
			}
			else if (e.getCause() != null && e.getCause() instanceof GenericJDBCException )
			{
				// For Oracle, instead of a ConstraingViolationException, it is throwing
				// a GenericJDBCException when using the DataDirect driver.
				// We then need to check for the error code for Integrity constraints
				GenericJDBCException exc = (GenericJDBCException) e.getCause();
				if (exc.getErrorCode() == ORACLE_FK_VIOLATION_ERROR)  // Oracle Integrity Constraint Code
					throw new ForeignKeyViolationException(e);
			}
			throw (e);				
		}
	}
	
	private void checkTrans() 
	{		
		if (transaction == null) beginTransaction();
	}

	/**
	 * 
	 * @see ims.domain.DomainFactory#delete(ims.domain.lookups.Lookup)
	 */
	public void delete(Lookup lookupType) throws ForeignKeyViolationException 
	{
		deleteObject(lookupType);
	}

	/**
	 * @see ims.domain.DomainFactory#setDomainSession(ims.domain.DomainSession)
	 */
	public void setDomainSession(DomainSession domainSession)
	{
		this.domainSession = domainSession;
	}
	
	public DomainSession getDomainSession()
	{
		if (null == this.domainSession)
			throw new DomainRuntimeException("DomainSession is not set.");
		return this.domainSession;
	}

	/**
	 * Close + discard the hibernate session.
	 * @throws DomainRuntimeException if the session could not be closed.
	 */
	public void discardSession()
	{
		try
		{
			Session hibernateSession = getSession(false);
			if ( null != hibernateSession)
				hibernateSession.close();
		}
		catch(HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}
		finally
		{
			this.hibSession = null;
		}
	}

	public boolean isSessionDiscarded()
	{
		return (hibSession == null);
	}	

	/**
	 * Uses hibernate SessionFactory to get list of all
	 * Domain class objects loaded.
	 * @see ims.domain.DomainFactory#getDomainClasses()
	 * @throws DomainRuntimeException if problem occurs
	 * accessing Hibernate details.
	 */
	@SuppressWarnings("unchecked")
	public List getDomainClasses()
	{
		try
		{
			java.util.Map allclassmetadata =
				getSessionFactory().getAllClassMetadata();
			java.util.Set keySet = allclassmetadata.keySet();
			List domainClasses = new java.util.ArrayList(keySet.size());
			domainClasses.addAll(keySet);
			return domainClasses;
		}
		catch(HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}
	}
	/**
	 * @see ims.domain.DomainFactory#beginTransaction()
	 */
	public ims.domain.Transaction beginTransaction()
	{
		LOG.info(">beginTransaction");
		try
		{
			this.transaction = null;
			this.transaction = new ims.domain.hibernate3.Transaction(this);
			LOG.debug(">beginTransaction Exit");
			return this.transaction;
		}
		catch(HibernateException e) 
		{
			throw new DomainRuntimeException(e);
		}
	}

	public LookupInstance getLookupInstance(int instanceId)  
	{
		LOG.info(">getLookupInstance(instId=" + instanceId + ")");
		try
		{
			LookupInstance instance = (LookupInstance) getObject(LookupInstance.class, instanceId);
			LOG.debug(">getLookupInstance Exit");
			return instance;
		}
		catch (HibernateException e)
		{
			LOG.error("Could not getLookupInstance for instId:" + instanceId, e);
			throw new DomainRuntimeException(e);
		}
	}

	/**
	 * @see ims.domain.DomainFactory#getLookupInstance(int, int)
	 */
	public LookupInstance getLookupInstance(int id, int instanceId) 
	{
		Lookup lookup = getLookup(id);
		if ( null == lookup) {
			throw new DomainRuntimeException("No lookup-type for id:"+id);
		}
		return lookup.getInstance(instanceId);
	}

	///////////////////////////////////
	///// All the find methods ////////
	///////////////////////////////////
	
	public List find(String hqlString)
	{
		return this.find(hqlString, EnvironmentConfig.getResultSetDefaultMax());		
	}

	public DomainObject findFirst(String hqlString)
	{
		List lst = find(hqlString, new String[0], new Object[0], EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);  // As want first record only
		
		return null;
	}
	
	public List find(String hqlString, int maxRec) 
	{
		return this.find(hqlString, new String[0], new Object[0], maxRec);
	}
	
	public DomainObject findFirst(String query, Object value, int typeCode) 
	{
		List lst = find(query, new Object[]{value}, new int[]{typeCode}, EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}
	
	public List find(String query, Object value, int typeCode) 
	{
		return find(query, value, typeCode, EnvironmentConfig.getResultSetDefaultMax());
	}
	

	public List find(String query, Object value, int typeCode, int maxRec) 
	{
		return find(query, new Object[]{value}, new int[]{typeCode}, maxRec);
	}
	
	public DomainObject findFirst(final String query, final Object[] values, final int[] typeCodes) 
	{
		List lst = find(query, values, typeCodes, EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}	

	public List find(final String query, final Object[] values, final int[] typeCodes) 
	{
		return find(query, values, typeCodes, EnvironmentConfig.getResultSetDefaultMax());
	}	

	public DomainObject findFirst(String query, String paramName, Object paramValue)
	{
		List lst = find(query, new String[]{paramName}, new Object[]{paramValue}, EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}
	
	public List find(String query, String paramName, Object paramValue)
	{
		return find(query, paramName, paramValue, EnvironmentConfig.getResultSetDefaultMax());
	}

	public List find(String query, String paramName, Object paramValue, int maxRec) 
	{
		return find(query, new String[]{paramName}, new Object[]{paramValue}, maxRec);
	}
	
	public DomainObject findFirst(String query, String[] paramNames, Object[] paramValues)
	{	
		List lst = find (query, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}
	public List find(String query, String[] paramNames, Object[] paramValues)
	{	
		return find (query, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax());
	}

	public DomainObject findFirst(Set collection, String filterString)
	{
		List lst = find(collection, filterString, new String[0], new Object[0], EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}
	
	public List find(Set collection, String filterString)
	{
		return find(collection, filterString, EnvironmentConfig.getResultSetDefaultMax());
		
	}
	public List find(Set collection, String filterString, int maxRec) 
	{
		return find(collection, filterString, new String[0], new Object[0], maxRec);
	}

	
	public DomainObject findFirst(Set collection, String filterString, String[] paramNames, Object[] paramValues)
	{
		List lst= find(collection, filterString, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}

	public List find(Set collection, String filterString, String[] paramNames, Object[] paramValues)
	{
		return find(collection, filterString, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax());
	}

	public List find(String query, LookupInstance paramValue)
	{
		return find(query, paramValue, EnvironmentConfig.getResultSetDefaultMax());
	}

	public List find(String query, Object[] values, int[] typeCodes, int maxRec)
	{
		return find(query, values, typeCodes, maxRec, false);
	}
	
	public List find(String query, Object[] values, @SuppressWarnings("unused") int[] typeCodes, int maxRec, boolean firstOnly) 
	{ 
		LOG.debug(">find method 1 - " + query);
		Date start = new Date();
		Session session = getSession();
		try 
		{
			Query q = session.createQuery(query);
			for (int i = 0; i < values.length; i++)
			{
				q.setParameter(i, values[i]);
			}
			
			List l = processListOnQuery(q, maxRec, firstOnly);
			
			if (LOG.isDebugEnabled())
				LOG.debug("Factory Find took " + (new Date().getTime() - start.getTime()) + " mS." );
			LOG.debug(">find method exit");
			
			return l;			
		}
		catch(HibernateException e) 
		{
			throw new DomainRuntimeException(e);
		}
	}

	public List find(String query, String[] paramNames, Object[] paramValues, int maxRec)
	{
		return find(query, paramNames, paramValues, maxRec, false);
	}
	
	public List find(String query, String[] paramNames, Object[] paramValues, int maxRec, boolean firstOnly)
	{		
		LOG.debug(">find method 2 - " + query);
		
		Date start = new Date();
		try 
		{
			Session session = getSession();
			Query q = session.createQuery(query);
			for (int i = 0; i < paramNames.length; i++)
			{
				q.setParameter(paramNames[i], paramValues[i]);	
			}	
			
			List l = processListOnQuery(q, maxRec, firstOnly);
			
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Factory Find took " + (new Date().getTime() - start.getTime()) + " mS." );
			}
			LOG.debug(">find method Exit");
			
			return l;
		}
		catch(HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}				
	}

	private List processListOnQuery(Query q, int maxRec, boolean firstOnly)
	{
		if(q == null)
			throw new DomainRuntimeException("Invalid query");
		
		boolean queryCapped = true;
		
		if (firstOnly)
		{
			q.setMaxResults(1);
		}
		else
		{
			queryCapped = shouldCapQuery(q);			
			if(queryCapped)
			{
				capQuery(q, maxRec);
			}
		}
		
		List l = q.list();
		
		if(queryCapped)
		{
			checkResSize(l, maxRec);
		}
		
		return l;
	}

	public List find(Set collection, String filterString, String[] paramNames, Object[] paramValues, int maxRec)
	{
		return find(collection, filterString, paramNames, paramValues, maxRec, false);
	}
	
	public List find(Set collection, String filterString, String[] paramNames, Object[] paramValues, int maxRec, boolean firstOnly)
	{
		LOG.debug(">find method 3 ");
		
		Date start = new Date();
		try 
		{
			Session session = getSession();
			Query q = session.createFilter(collection, filterString);
			for (int i = 0; i < paramNames.length; i++)
			{
				q.setParameter(paramNames[i], paramValues[i]);	
			}
		
			List l = processListOnQuery(q, maxRec, firstOnly);
			
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Factory Find took " + (new Date().getTime() - start.getTime()) + " mS." );
			}
			LOG.debug(">find method Exit");
			
			return l;
		}
		catch(HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}				
	}

	public List find(String query, LookupInstance paramValue, int maxRec) 
	{
		LOG.debug(">find method 4 - " + query);
		
		Date start = new Date();
		try 
		{
			Query q = getSession().createQuery(query);
			q.setProperties(paramValue);
			
			List l = processListOnQuery(q, maxRec, false);
			
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Factory Find took " + (new Date().getTime() - start.getTime()) + " mS." );
			}
			LOG.debug(">find method Exit");
			
			return l;			
		}
		catch(HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}		
	}

	
	/**
	 * Converts from IMS type code defined on #ims.domain.DomainFactory to
	 * Hibernate types.
	 * @param typeCode
	 * @return
	 */
	protected Type getType(int typeCode) {
		switch(typeCode) {
			case TYPE_BOOLEAN :
				return Hibernate.BOOLEAN;
			case TYPE_DATE :
				return Hibernate.DATE;
			case TYPE_DATETIME :
				return Hibernate.TIMESTAMP;
			case TYPE_DECIMAL :
				return Hibernate.DOUBLE;
			case TYPE_INTEGER :
				return Hibernate.INTEGER;
			case TYPE_STRING :
				return Hibernate.STRING;
			case TYPE_TIME :
				// TODO TIME convert IMS value to Hibernate value
				return Hibernate.TIME;
			default :
				return null;
		}
	}

	/**
	 * @see ims.domain.DomainFactory#getLookupInstance(Lookup, String, String)
	 */
	public LookupInstance getLookupInstance(Lookup type, String externalSystem, String externalCode)
	{
		LOG.debug(">getLookupInstance - Type=" + type.getId() + " externalSystem = " + externalSystem + " Code = " + externalCode);
		
		final String hquery = "from LookupInstance instance"+
			" join instance.mappings as mapping"+
			" where mapping.extCode = ? and mapping.extSystem = ? and instance.type.id = ?";
		Object[] values = {externalCode, externalSystem, new Integer(type.getId())};
		int[] types = { TYPE_STRING, TYPE_STRING, TYPE_INTEGER };
		List result = find(hquery, values, types);
		int size = result.size();
		if ( 1 == size) {
			LookupInstance instance = (LookupInstance) result.get(0);
			return instance;
		}
		if ( 1 < size ) {
			StringBuffer message = 
				new StringBuffer("Size of result set is ");
			message.append(size);
			message.append(" for LookupInstance. Type=" + type.getId());
			message.append(" mapping externalSystem=").append(externalSystem);
			message.append(" and externalCode=").append(externalCode);
			message.append(". LookupInstance ids:");

			for (Iterator iter = result.iterator(); iter.hasNext();) {
				LookupInstance element = (LookupInstance) iter.next();
				message.append(element.getId());
				if ( iter.hasNext() ) {
					message.append(", ");
				}
				else {
					message.append(".");
				}
			}			
			throw new DomainRuntimeException(message.toString());
		}		
		return null;
	}

	/**
	 * @see ims.domain.DomainFactory#getLookupInstance(int, java.lang.String, java.lang.String)
	 */
	public LookupInstance getLookupInstance(int typeId, String externalSystem, String externalCode)
	{
		Lookup type = getLookup(typeId);
		if ( null == type ) {
			throw new DomainRuntimeException("Lookup type does not exist for type-id="+typeId);
		}
		return getLookupInstance(type, externalSystem, externalCode);
	}

	public int delete(String queryString) throws ForeignKeyViolationException 
	{
		checkTrans();
		Iterator iter = this.find(queryString).iterator();
		int count = 0;
		while (iter.hasNext())
		{
			deleteObject(iter.next());
			count++;
		}
		return count;			
	}

	public void initialize(DomainObject obj)
	{
		LOG.info(">initialize(obj=" + obj.getId() + ")");
		try
		{
			Hibernate.initialize(obj);
		}
		catch (HibernateException e)
		{
			String message = "Error during initialize.";
			LOG.error(message, e);
			throw new DomainRuntimeException(message, e);
		}
	}

	@SuppressWarnings("unchecked")
	public DomainObject findFirst(String query, ArrayList paramNames, ArrayList paramValues)
	{
		String[] names = new String[paramNames.size()];
		paramNames.toArray(names);
		List lst = find(query, names, paramValues.toArray(), EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}

	public List find(String query, ArrayList paramNames, ArrayList paramValues)
	{
		return find(query, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax());
	}
	@SuppressWarnings("unchecked")
	public List find(String query, ArrayList paramNames, ArrayList paramValues, int maxRec) 
	{
		String[] names = new String[paramNames.size()];
		paramNames.toArray(names);
		return find(query, names, paramValues.toArray(), maxRec);
	}

	@SuppressWarnings("unchecked")
	public DomainObject findFirst(Set collection, String filterString, ArrayList paramNames, ArrayList paramValues)
	{
		String[] names = new String[paramNames.size()];
		paramNames.toArray(names);
		List lst= find(collection, filterString, names, paramValues.toArray(), EnvironmentConfig.getResultSetDefaultMax(), true);
		if (lst != null && lst.size() > 0)
			return (DomainObject) lst.get(0);
		return null;
	}

	public List find(Set collection, String filterString, ArrayList paramNames, ArrayList paramValues)
	{
		return find(collection, filterString, paramNames, paramValues, EnvironmentConfig.getResultSetDefaultMax());
	}
	@SuppressWarnings("unchecked")
	public List find(Set collection, String filterString, ArrayList paramNames, ArrayList paramValues, int maxRec) 
	{
		String[] names = new String[paramNames.size()];
		paramNames.toArray(names);
		return find(collection, filterString, names, paramValues.toArray(), maxRec);
	}

	public Connection getJdbcConnection()  
	{
		try 
		{
			return this.getSession().connection();
		}
		catch (HibernateException e) 
		{
			throw new DomainRuntimeException("Failed to get JDBC Connection from Hibernate session. ", e);
		}
	}

	public Transaction getTransaction() 
	{
		if (LOG.isDebugEnabled())
		LOG.debug("getTransaction() = transaction");
		return this.transaction;
	}

	public void setTransaction(Transaction trans) 
	{
		if (LOG.isDebugEnabled())
			LOG.debug("setTransaction(transaction="+transaction+")");
		this.transaction = trans;
	}

	private int checkMax(int maxRec) 
	{
		return Math.min(maxRec, EnvironmentConfig.getResultSetTopMax());
	}

	private String maxRecMessage(int maxRec) 
	{
		String maxStr = "" + maxRec;
		String msg = ims.domain.DomainFactory.OVERFLOW_MESSAGE.replaceFirst("\\$NUM\\$", maxStr);
		return msg;
	}

	private void capQuery(Query query, int maxRec) 
	{
		if(!shouldCapQuery(query))
		{
			Type[] types = query.getReturnTypes();
			String typesString = "";
			
			for(int x = 0; x < types.length; x++)
			{
				if(typesString.length() > 0)
					typesString += ", ";
				typesString += types[x].getReturnedClass().toString();
			}				
		}
		else
		{
			query.setMaxResults(checkMax(maxRec) + 1);
		}
	}
		
	private boolean shouldCapQuery(Query query)
	{
		if(query == null)
			throw new DomainRuntimeException("Invalid query");
		
		if(!RESULTSET_CAPPED)
			return false;
		if(domainSession.queryCappingDisabled())
			return false;		
		
		Type[] arrEntities = query.getReturnTypes();		
		if(arrEntities == null)
			return false;
		
		for(int i=0; i<arrEntities.length; i++)
		{
			try
			{
				Class doClass = arrEntities[i].getReturnedClass();
				
				//failing to instantiate type Long so this is a catch for any other instantiation errors and will return false
				Object instance = null;
				try 
				{
					instance = doClass.newInstance();
				}
				catch (InstantiationException e)
				{					
					return true;  // FWD-212 - by default all queries should be capped, even if results are primitive types
				}
				
				if(instance != null && instance instanceof DomainObject)
				{
					if(!((DomainObject)instance).shouldCapQuery())
					{
						LOG.info("Query capping is disabled for " + doClass);
						return false;
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
		}
		
		return true;
	}

	private void checkResSize(List l, int maxRec) 
	{
		if (l.size() > checkMax(maxRec))
		{
			Object obj = l.get(0);
			String className = obj.getClass().getName();
			getDomainSession().createSystemLogEntry(SystemLogType.APPLICATION, SystemLogLevel.WARNING, "Max Record Count returned for object - " + className);
			this.getDomainSession().showMessage(maxRecMessage(maxRec));
			l.remove(l.size()-1);
		}
	}

	public void setShowSql(boolean showSql)
	{
		Registry.getInstance().setShowSql(showSql);		
	}
	
	public void setHibernateUseSQLComments(boolean hibernateUseSQLComments)
	{
		Registry.getInstance().setHibernateUseSQLComments(hibernateUseSQLComments);		
	}
	
	public void setHibernateStatisticsEnabled (boolean value)
	{
		Registry.getInstance().getSessionFactory().getStatistics().setStatisticsEnabled(value);
		Registry.getInstance().getSessionFactory().getStatistics().clear();
	}

	public void setFormatSql(boolean formatSql)
	{
		Registry.getInstance().setFormatSql(formatSql);		
	}


	public void markAsRie(Class domainClass, int id, FormName form, Integer patId, Integer contactId, Integer careContextId, String comment) throws StaleObjectException 
	{
		LOG.info(">markAsRie(Integer=" + id + ", Class=" + domainClass.getName() + ")");
		checkTrans();
		try
		{
			DomainObject domainObject = (DomainObject) getObject(domainClass, id);
			// Set the isRIE value to true and create the RecordedInError record
			RecordedInError rec = new RecordedInError();
			rec.setBoClassId(id);
			rec.setClassName(domainClass.getName());
			rec.setClinContactId(contactId);
			rec.setCareContextId(careContextId);
			rec.setComment(comment);
			if (form != null)  // FWD-227
				rec.setFormId(form.getID());
			rec.setPatientId(patId);
			save(rec);
				
			domainObject.setIsRIE(true);
			save(domainObject);
		}
		catch (HibernateException e)
		{
			LOG.error("Could not mark record as RecordedInError");
			throw new DomainRuntimeException(e);
		}
		
	}
	
	public long countWithHQL(String hql, String[] paramNames, Object[] paramValues)
	{
		List<?> l = find(hql, paramNames,paramValues);
		long count=0;
		if(l!=null&&!l.isEmpty()&&l.get(0)!=null)
		{
			count=(Long)l.get(0);
		}
		return count;
	}

	public int count(String hql, String[] paramNames, Object[] paramValues)
	{
		List l = this.find(hql, paramNames, paramValues);
		return l.size();
	}

	public void setDatasource(String datasourceName)
	{
		Registry.getInstance().setDatasourceName(datasourceName);
	}

	private ImportedObject getImportedObject(Class domainClass, String externalSource, Integer externalId)
	{
		String hql = "from ImportedObject io where io.externalId = :externalId and io.externalSource = :externalSource and io.className = :className";
		List l = this.find(hql, new String[]{"externalId","externalSource","className"}, new Object[]{externalId, externalSource, domainClass.getName()});
		if (l.size() == 0)
			return null;
		if (l.size() > 1)
			throw new DomainRuntimeException("More than 1 Imported Object found for combination of externalId=" + externalId + ", externalSource=" + externalSource + ", className=" + domainClass.getName());
		return (ImportedObject)l.get(0);		
	}
	public DomainObject getImportedDomainObject(Class domainClass, String externalSource, Integer externalId)
	{
		ImportedObject impObj = this.getImportedObject(domainClass, externalSource, externalId);
		if (impObj == null)
			return null;
		
		int localId = impObj.getLocalId();
		return getDomainObject(domainClass, localId);
	}

	public DomainObject getImportedDomainObject(Class domainClass, String externalSource, int externalId)
	{
		return getImportedDomainObject(domainClass, externalSource, new Integer(externalId));
	}

	public void saveImport(DomainObject obj, String externalSource, int externalId) throws StaleObjectException
	{
		//this.save(obj);
		ImportedObject impObj = this.getImportedObject(obj.getRealDomainClass(), externalSource, new Integer(externalId));
		if (impObj == null)
		{
			impObj = new ImportedObject();
			impObj.setClassName(obj.getRealDomainClass().getName());
			impObj.setExternalId(externalId);
			impObj.setExternalSource(externalSource);
			impObj.setImportDate(new Date());
			impObj.setLocalId(obj.getId().intValue());
		}
		else
		{
			impObj.setUpdateDate(new Date());
			impObj.setLocalId(obj.getId().intValue());			
		}
		this.save(impObj);
	}
	
	public void validateFind(final String hql, final String[] paramNames, final Object[] paramValues)
	{
		LOG.debug(">validateFind - " + hql);
		Date start = new Date();
		Session session = getSession();
		Query q = session.createQuery(hql);
		for (int i = 0; i < paramNames.length; i++)
		{
			q.setParameter(paramNames[i], paramValues[i]);
		}
		q.setMaxResults(0);
		q.list();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Factory validateFind took " + (new Date().getTime() - start.getTime()) + " mS.");
		}
		LOG.debug(">validateFind Exit");
	}
	
	public void validateFindSQL(final String sql, final String[] paramNames, final Object[] paramValues, int maxRec)
	{
		LOG.debug(">validateFind - " + sql);
		Date start = new Date();
		Session session = getSession();
		Query q = session.createSQLQuery(sql);
		for (int i = 0; i < paramNames.length; i++)
		{
			q.setParameter(paramNames[i], paramValues[i]);
		}
		if (maxRec>=0)
		{
			q.setMaxResults(maxRec);
		}
		q.list();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Factory validateFind took " + (new Date().getTime() - start.getTime()) + " mS.");
		}
		LOG.debug(">validateFind Exit");
	}
	

	
	/**
	 * Checks if a DomainObject or a property is dirty or not
	 */
	public boolean isDirty(Object o) 
	{
		boolean dirty=false;
		
		if (o instanceof DomainObject)
		{
			for (int i=0;i<dirtyEntities.size();i++)
			{
				if (o.equals(dirtyEntities.get(i)))
					dirty=true;
			}
		}
		else if (o instanceof String)
		{
			
			for (int i=0;i<dirtyProperties.size();i++)
			{
				if (o.equals(dirtyProperties.get(i)))
					dirty=true;
			}
		}
		return dirty;
	}
	
	/**
	 * Checks if we want to do the dirty check.
	 */
	public boolean doDirtyCheck()
	{
		return checkDirty;
	}
	/**
	 * Sets the DirtyCheck variable to true or false. If true, the interceptor will check your 
	 * domain object or property.
	 */
	@SuppressWarnings("static-access")
	public void setDirtyCheck(boolean checkDirty)
	{		
		this.checkDirty=checkDirty;
		if (!checkDirty)
			this.resetDirtyProperties();
	}
	/**
	 * Gets the list of dirty entities that was filled by the interceptor. 
	 */
	public ArrayList getDirtyEntities()
	{
		return this.dirtyEntities;	
	}
	/**
	 * Gets the list of dirty properties that was filled by the interceptor. 
	 */
	public ArrayList getDirtyProperties()
	{
		return this.dirtyProperties;
	}
	/**
	 * Resets all the dirty objects and properties.
	 */
	public void resetDirtyProperties()
	{
		this.dirtyProperties=new ArrayList();
		this.dirtyEntities=new ArrayList();
	}
	
	/**
	 * FWD-178
	 * Evict this object from the session
	 * so that it will not be inserted/saved etc..
	 */
	public void evict(DomainObject o)
	{
		Session sess = getSession(false);
		if (sess != null)
			sess.evict(o);
	}
}

