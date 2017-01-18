package ims.domain.impl;

import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.domain.DbType;
import ims.domain.DomainFactory;
import ims.domain.DomainInterface;
import ims.domain.DomainObject;
import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.Transaction;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.factory.PrintersFactory;
import ims.domain.hibernate3.Registry;
import ims.domain.lookups.LookupInstance;
import ims.domain.lookups.LookupService;
import ims.framework.FormName;
import ims.framework.SessionConstants;
import ims.framework.enumerations.PrinterScope;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ILocation;
import ims.framework.interfaces.IPrinter;
import ims.framework.interfaces.ISystemLog;
import ims.framework.utils.Image;
import ims.reports.ReportEngine;
import ims.reports.ReportEngineFactory;
import ims.utils.Logging;
import ims.vo.LookupInstVo;
import ims.vo.ValueObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.hibernate.cache.Cache;
import org.hibernate.impl.SessionFactoryImpl;


/**
 * Base class for all implementations of domain-interfaces.
 * 
 * @author gcoghlan
 * 
 */
public class DomainImpl implements DomainInterface, Transactional, Serializable
{	 
	private static final long serialVersionUID = 1L;
	
	private static final Logger					LOG					= Logging.getLogger(DomainImpl.class);
	private static final String					DEFAULT_FACTORY		= "ims.domain.hibernate3.DomainFactory";

	protected final boolean						usesLookupService;
	private DomainSession						domainSession;
	private transient DomainFactory				factory				= null;
	protected transient LookupService			lookupService		= null;
	private HashMap								extraImpls			= new HashMap();
	private Class								lookupServiceClass	= null;
	private Logger								sessLogger			= null;

	public DomainImpl()
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info("Constructing new DomainImpl - concrete class:" + getClass());
		}
		this.usesLookupService = usesLookupService();
	}

	protected void setContext(DomainSession domainSession)
	{
		setContext(domainSession, ims.domain.lookups.impl.LookupServiceImpl.class);
	}

	/**
	 * Initialize the context for the implementation. This retains the reference to the domainSession, which is released when free() is called. Any existing DomainFactory is updated with the domainSession. If the implementation #usesLookupService then the reference to the LookupService is obtained. Subclasses may override this method but should extend rather than replace the inherited method i.e. call super.setContext(...) as the first action in the implementation.
	 * 
	 * @param domainSession
	 */
	protected void setContext(DomainSession domainSession, Class lookupServiceClass)
	{
		if (LOG.isInfoEnabled())
			LOG.info("setContext(DomainSession=" + domainSession + ")");
		this.domainSession = domainSession;
		this.getDomainFactory();
		if (null != this.factory)
		{
			if (LOG.isDebugEnabled())
				LOG.debug("DomainImpl has a factory, setting domain session on factory.");
			this.factory.setDomainSession(domainSession);
		}
		this.lookupServiceClass = lookupServiceClass;
		SessionData sessData = (SessionData)domainSession.getAttribute(SessionConstants.SESSION_DATA);
		if (sessData != null)
		{
			this.sessLogger = sessData.xmlLogger.get();			
		}
	}

	/**
	 * If the implementation #usesLookupService, then lookupService reference is freed via the DomainImplFlyweightFactory. The reference to the domainSession is released. Any DomainFactory is closed. Subclasses may override this method but should extend rather than replace the inherited method i.e. call super.free() as the last action in the implementation.
	 */
	public void free()
	{
		if (LOG.isInfoEnabled())
			LOG.info("free()");
		if (usesLookupService() && this.lookupService != null)
		{
			DomainImplFlyweightFactory.getInstance().free(this.lookupService);
			this.lookupService = null;
		}
		if (null != this.factory)
		{
			this.factory.close();
		}
		this.domainSession = null;
		this.sessLogger = null;
	}

	protected Image getImage(Integer imageId)
	{
		if (imageId == null)
			return null;
		
		SessionData sessData = (SessionData)domainSession.getAttribute(SessionConstants.SESSION_DATA);
	
		Configuration cfg = sessData.configurationModule.get();
		if (cfg == null)
		{
			throw new DomainRuntimeException("No Configuration found!");
		}
		Map images = cfg.getRegisteredImages();
		if (images == null)
		{
			throw new DomainRuntimeException("No Images Registered!");
		}
		Image image = (Image) images.get(imageId);
		return image;
	}

	/**
	 * Obtain the current DomainSession.
	 * 
	 * @return the current DomainSession.
	 */
	public final DomainSession getSession()
	{
		if (LOG.isDebugEnabled())
			LOG.debug("getSession()=" + this.domainSession);
		return this.domainSession;
	}

	/**
	 * Obtain the initialized DomainFactory
	 * 
	 * @throws DomainException
	 */
	protected ims.domain.DomainFactory getDomainFactory()
	{
		if (LOG.isDebugEnabled())
			LOG.debug("getDomainFactory()");
		if (null == this.factory)
		{
			if (LOG.isInfoEnabled())
				LOG.info("Creating a new DomainFactory.");
			this.factory = createFactory();
			this.factory.setDomainSession(getSession());
		}
		return this.factory;
	}

	public final void setDomainFactory(DomainFactory factory)
	{
		this.factory = factory;
	}

	/**
	 * Subclass overrides this to indicate whether it uses the ims.domain.lookups.LookupService. The default behaviour is to return true. This means that the impl uses the LookupService. This method is called from the constructor. Any overriding implementation must return a value that can be calculated correctly from the constructor.
	 * 
	 * @return true
	 */
	protected boolean usesLookupService()
	{
		if (LOG.isDebugEnabled())
			LOG.debug("usesLookupService() = true");
		return true;
	}

	/**
	 * Get the LookupService. This throws an UnsupportedOperationException if the implementation does not support using the LookupService i.e. #usesLookupService() returned false when the implementation was constructed.
	 */
	public final ims.domain.lookups.LookupService getLookupService()
	{
		if (usesLookupService())
		{
			if (this.lookupService == null)
			{
				try
				{
					this.lookupService = (ims.domain.lookups.LookupService) DomainImplFlyweightFactory.getInstance().create(this.lookupServiceClass, this.domainSession, this.getDomainFactory());
				}
				catch (RuntimeException e)
				{
					LOG.error("Failed to initialize ims.domain.lookups.LookupService", e);
					throw e;
				}
				catch (Exception e)
				{
					// InstantiationException, IllegalAccessException
					final String error = "Failed to initialize ims.domain.lookups.LookupService";
					LOG.error(error, e);
					throw new DomainRuntimeException(error, e);
				}
			}
			return this.lookupService;
		}

		final String message = "getLookupService() not supported for " + this.getClass().getName() + ". It must override method usesLookupService() to return true.";
		LOG.error(message);

		throw new UnsupportedOperationException(message);
	}

	/**
	 * Returns a HCP Domain Object with this user's username or null if this user is not a HCP
	 * 
	 * @param username
	 * @return
	 */
	public Object getHcpUser()
	{
		getMosUser(); // Forces the 3 DomainSession attributes ("AppUserMosVo", "AppUserHcpVo", "AppUserHcpLiteVo") to be set up if not already.
		return this.domainSession.getAttribute("AppUserHcpVo");
	}
	
	public Object getHcpLiteUser()
	{
		getMosUser(); // Forces the 3 DomainSession attributes ("AppUserMosVo", "AppUserHcpVo", "AppUserHcpLiteVo") to be set up if not already.
		return this.domainSession.getAttribute("AppUserHcpLiteVo");
	}

	public final Object getMosUser()
	{
		Object voObj = null;
		try
		{
			IAppUser user = this.domainSession.getUser();
			if (user == null)
				return null;
			if (user.getMosId() == null)
				return null;

			if (user.isHcpChecked() == false)
			{
				Object mos = getMosFromUser(user);
				this.domainSession.setAttribute("AppUserMosVo", mos);
				if (mos == null)
				{
					user.setHcpChecked(true);
					return null;
				}

				Object hcp = getHcpFromMos(mos, user);
				this.domainSession.setAttribute("AppUserHcpVo", hcp);
				
				Object hcpLite = getHcpLiteFromMos(mos, user);
				this.domainSession.setAttribute("AppUserHcpLiteVo", hcpLite);
				
				user.setHcpChecked(true);
			}
			return this.domainSession.getAttribute("AppUserMosVo");
		}
		catch (Exception e)
		{
			LOG.warn("DomainImpl:getMosUser failed." + e.getMessage(), e);
		}
		return voObj;
	}

	private Object getMosFromUser(IAppUser user) throws Exception
	{
		if (user.getMosId() == null)
			return null;

		this.factory = this.getDomainFactory();
		Class domClass = Class.forName("ims.core.resource.people.domain.objects.MemberOfStaff");
		DomainObject domMos = this.factory.getDomainObject(domClass, user.getMosId().intValue());
		if (domMos == null)
		{
			return null;
		}
		Method method = domClass.getMethod("getName", (Class[]) null);
		Object mosName = method.invoke(domMos, (Object[]) null);
		user.setUserRealName("" + mosName.toString());

		Class assembler = Class.forName("ims.core.vo.domain.MemberOfStaffShortVoAssembler");
		Method assemblerMethod = assembler.getMethod("create", new Class[]{domClass});

		Object mosVo =  assemblerMethod.invoke(null, new Object[]{domMos});
		// We now need to set the appuser record to mosVo
		Class mosClass = Class.forName("ims.core.vo.MemberOfStaffShortVo");
		Class userClass = Class.forName("ims.admin.vo.AppUserShortVo");

		Method userMethod = mosClass.getMethod("setAppUser", new Class[]{userClass});
		userMethod.invoke(mosVo, new Object[]{user});
		Method isUserMethod = mosClass.getMethod("setIsAppUser", new Class[]{Boolean.class});
		isUserMethod.invoke(mosVo, new Object[]{Boolean.TRUE});
		return mosVo;
	}

	private Object getHcpLiteFromMos(Object mos, IAppUser user) throws Exception
	{
		Class domClass = Class.forName("ims.core.resource.people.domain.objects.Hcp");
		
		DomainObject domHcp = getHcpObject(mos, user, domClass);
		if (domHcp == null)
			return null;

		Class assembler = Class.forName("ims.core.vo.domain.HcpLiteVoAssembler");
		// Have to call this again to get the correct type
		domHcp = this.factory.getDomainObject(domClass, user.getHcpId().intValue());
		Method assemblerMethod = assembler.getMethod("create", new Class[]{domClass});
		return assemblerMethod.invoke(null, new Object[]{domHcp});

	}
	
	private DomainObject getHcpObject(Object mos, IAppUser user, Class domClass) throws Exception
	{
		Class clazz = mos.getClass();
		Method method = clazz.getMethod("getHcp", (Class[]) null);
		Object hcp = method.invoke(mos, (Object[]) null);
		if (hcp == null)
		{
			return null;
		}

		method = hcp.getClass().getMethod("getID_Hcp", (Class[]) null);
		Integer ret = (Integer) method.invoke(hcp, (Object[]) null);
		if (ret == null)
		{
			return null;
		}
		user.setHcpId(ret);

		return (this.factory.getDomainObject(domClass, user.getHcpId().intValue()));
	}
	
	private Object getHcpFromMos(Object mos, IAppUser user) throws Exception
	{
		Class domClass = Class.forName("ims.core.resource.people.domain.objects.Hcp");
	
		DomainObject domHcp = getHcpObject(mos, user, domClass);
		if (domHcp == null)
			return domHcp;
		
		Class assembler = null;
		// Get the classID and then the classId for the different types
		int classId = domHcp.getClassId();

		if (getClassId("ims.core.resource.people.domain.objects.Nurse") == classId)
		{
			assembler = Class.forName("ims.core.vo.domain.NurseVoAssembler");
			domClass = Class.forName("ims.core.resource.people.domain.objects.Nurse");

		}
		else if (getClassId("ims.core.resource.people.domain.objects.Therapist") == classId)
		{
			assembler = Class.forName("ims.core.vo.domain.TherapistVoAssembler");
			domClass = Class.forName("ims.core.resource.people.domain.objects.Therapist");
		}
		else if (getClassId("ims.core.resource.people.domain.objects.Medic") == classId)
		{
			assembler = Class.forName("ims.core.vo.domain.MedicVoAssembler");
			domClass = Class.forName("ims.core.resource.people.domain.objects.Medic");
		}
		else
		{
			assembler = Class.forName("ims.core.vo.domain.HcpAssembler");
		}
		// Have to call this again to get the correct type
		domHcp = this.factory.getDomainObject(domClass, user.getHcpId().intValue());
		Method assemblerMethod = assembler.getMethod("create", new Class[]{domClass});
		return assemblerMethod.invoke(null, new Object[]{domHcp});
	}

	public final Object getMosUser(String username)
	{
		if (username == null)
			return null;

		try
		{
			this.factory = this.getDomainFactory();
			List l = this.factory.find("from AppUser u where u.username='" + username.trim() + "'");
			if (l == null || l.size() == 0)
				return null;
			
			Class domClass = Class.forName("ims.core.configuration.domain.objects.AppUser");
			Class assembler = Class.forName("ims.admin.vo.domain.AppUserVoAssembler");
			Method assemblerMethod = assembler.getMethod("create", new Class[]{domClass});
			IAppUser appUserVo =  (IAppUser) assemblerMethod.invoke(null, new Object[]{l.get(0)});

			
			Object domMos = getMosFromUser(appUserVo);
			return domMos;
		}
		catch (Exception e)
		{
			LOG.warn("DomainImpl:getMosUser(username) failed." + e.getMessage(), e);
			throw new DomainRuntimeException("DomainImpl:getMosUser(username) failed." + e.getMessage(), e);
		}
	}

	private int getClassId(String classType)
	{
		Class nrseCls;
		try
		{
			nrseCls = Class.forName(classType);
			Field fld = nrseCls.getField("CLASSID");
			return (fld.getInt(null));
		}
		catch (Exception e)
		{
			return -1; // Return -1 as hcp assembler will then be used
		}

	}

	@SuppressWarnings("unchecked")
	protected final Object getDomainImpl(Class domainImplClass)
	{
		Object domain = null;
		try
		{
			domain = this.extraImpls.get(domainImplClass);
			if (domain != null)
			{
				return domain;
			}
			domain = DomainImplFlyweightFactory.getInstance().create(domainImplClass, getSession(), this.getDomainFactory());
			this.extraImpls.put(domainImplClass, domain);
			return domain;
		}
		catch (Exception ex)
		{
			throw new DomainRuntimeException("Failed to instantiate Domain Impl class " + domainImplClass.getName(), ex);
		}

	}
	
	// FWD-159
	protected final Object getDomainImpl(Class domainImplClass, ims.domain.DomainSession session)
	{
		setContext(session);
		return (getDomainImpl(domainImplClass));
	}

	private DomainFactory createFactory()
	{
		return createDefaultFactory();
	}

	private DomainFactory createFactory(String factoryName)
	{
		if (LOG.isDebugEnabled())
			LOG.debug("Creating DomainFactory:" + factoryName);
		try
		{
			return (DomainFactory) Class.forName(factoryName).newInstance();
		}
		catch (ClassNotFoundException e)
		{
			LOG.error("Could not create domain factory", e);
			throw new DomainRuntimeException(e);
		}
		catch (IllegalAccessException e)
		{
			LOG.error("Could not create domain factory", e);
			throw new DomainRuntimeException(e);
		}
		catch (InstantiationException e)
		{
			LOG.error("Could not create domain factory", e);
			throw new DomainRuntimeException(e);
		}
	}

	private DomainFactory createDefaultFactory()
	{
		return createFactory(DEFAULT_FACTORY);
	}

	public final HashMap getExtraImpls()
	{
		return this.extraImpls;
	}

	protected final LookupInstance getDomLookup(LookupInstVo voInstance)
	{
		if (voInstance == null) return null;
		
		factory = getDomainFactory();
		return factory.getLookupInstance(voInstance.getID());
	}

	public final String getServletContext()
	{
		return ((String) this.domainSession.getAttribute(SessionConstants.APPLICATION_CONTEXT));
	}

	public final java.util.List listDomainClasses()
	{
		factory = getDomainFactory();
		return factory.getDomainClasses();
	}
	
	protected final Connection getDatasourceConnection(String dataSource)
	{
		// Datasource e.g. "java:comp/env/jdbc/ntpf_ptr_rep"

		try
		{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dataSource);
			return ds.getConnection();
		}
		catch (NamingException e)
		{
			throw new DomainRuntimeException("NamingException occurred getting DatasourceConnection (" + dataSource + ") - " + e.getMessage(), e);
		}
		catch (SQLException e)
		{
			throw new DomainRuntimeException("SQLException occurred getting DatasourceConnection for (" + dataSource + ") - " + e.getMessage(), e);
		}
	}

	protected final void raiseAlert(String msg)
	{
		if (this.getSession() != null)
		{
			this.getSession().showMessage(msg);
		}
	}

	protected final void setShowSql(boolean showSql)
	{
		this.getDomainFactory().setShowSql(showSql);
	}
	
	protected final void setHibernateStatisticsEnabled(boolean value)
	{
		this.getDomainFactory().setHibernateStatisticsEnabled(value);
	}
	
	protected final DbType getDbType()
	{
		return DbType.getDbType(ConfigFlag.DBTYPE.getValue());
	}
		
	public final Logger getSessLogger()
	{
		return this.sessLogger;
	}
	
	public boolean isMethodTransactional(String methodName)
	{
		if (methodName == null) return false;
		return false;
	}

	/**
	 * markAsRie
	 * <p> Method which will mark all business objects associated with
	 * this value object as RecordedInError.  It makes a call to the Domain
	 * Factory method to mark each RIE value
	 * @param vo - ValueObject containing Business Objects to Mark
	 * @param form - The Form this request was initiated from
	 * @param patId - Identifier of the Patient (may be null)
	 * @param contactId - Identifier of the Clinical Contact (may be null)
	 * @param comment - Reason for marking record as RIE
	 * @throws StaleObjectException
	 * </p>
	 */
	public final void markAsRie(ValueObject vo, FormName form, Integer patId, Integer contactId, Integer careContextId, String comment) throws StaleObjectException 
	{
		try 
		{
			// Get the className and classId from the vo before proceeding
			factory = getDomainFactory();

			Class cls = Class.forName(vo.getBoClassName());
			if (vo == null || vo.getBoId() == null)
				throw new CodingRuntimeException("Cannot mark record as RIE due to inconsistent data");
			factory.markAsRie(cls,vo.getBoId().intValue(), form, patId, contactId, careContextId, comment);
		} 
		catch (ClassNotFoundException e) 
		{
			throw new DomainRuntimeException("ClassNotFoundException occurred marking record as RIE - " + e.getMessage(), e);
		}
	}

	public final int countRIERecords(FormName form, Integer patId, Integer contactId, Integer careContextId) throws DomainInterfaceException
	{
		// Form and Patient are Mandatory
		if (patId == null || form == null)
			throw new DomainInterfaceException("Both Patient and Form are mandatory items for countRIERecords query");
		
		StringBuffer hql = new StringBuffer();
		String[] params = new String[4];
		Object[] paramTypes = new Object[4];
		factory = getDomainFactory();
		
		hql.append("select count(*) from RecordedInError r where r.formId = :formID and r.patientId = :patID");
		params[0] = "formID";
		paramTypes[0] = new Integer(form.getID());
		params[1] = "patID";
		paramTypes[1] = patId; 
		
		if (contactId != null)
		{
			hql.append(" and r.clinContactId=:clinContactID");
			params[2] = "clinContactID";
			paramTypes[2] = contactId;
		}
		if (careContextId != null)
		{
			hql.append(" and r.careContextId=:careContextID");
			params[3] = "careContextID";
			paramTypes[3] = careContextId;
		}
		List lst = factory.find(hql.toString(), params, paramTypes);
		// There will be only one item returned in the list - the record count
		Integer recCount = (Integer) lst.get(0);
		return recCount.intValue();
	}

	public final ReportEngine getReportEngine()
	{
		return ReportEngineFactory.createReportEngine();		
	}

	public final ILocation getCurrentLocation()
	{
		SessionData sessData = (SessionData)(getSession().getAttribute(SessionConstants.SESSION_DATA));
		if (sessData == null)
			throw new DomainRuntimeException("Session Data in Domain Session cannot be null.");
		
		return sessData.selectedLocation.get();
	}

	public IPrinter[] getPrintersForLocation(ILocation location)
	{
		PrintersFactory factory = new PrintersFactory(getDomainFactory().getDomainSession());
		if(factory.hasPrintersProvider())
		{
			return factory.getProvider().getPrintersForLocation(location);
		}
		return null;
	}
	public IPrinter getPrinterByLocationAndScope(ILocation location, PrinterScope scope)
	{
		PrintersFactory factory = new PrintersFactory(getDomainFactory().getDomainSession());
		if(factory.hasPrintersProvider())
		{
			return factory.getProvider().getPrinterByLocationAndScope(location, scope);
		}
		return null;
	}	
	public IPrinter[] getAllPrinters()
	{
		PrintersFactory factory = new PrintersFactory(getDomainFactory().getDomainSession());
		if(factory.hasPrintersProvider())
		{
			return factory.getProvider().getAllPrinters();
		}
		return null;
	}
	
	protected final void clearMosAndHcp()
	{
		this.domainSession.setAttribute("AppUserHcpLiteVo", null);
		this.domainSession.setAttribute("AppUserHcpVo", null);
		this.domainSession.setAttribute("AppUserMosVo", null);
		IAppUser user = this.domainSession.getUser();
		if (user != null)
		{
			user.setHcpChecked(false);			
		}
	}
	
	protected final void reloadConfiguration() throws Exception
	{
		SessionData sessData = (SessionData)domainSession.getAttribute(SessionConstants.SESSION_DATA);
		Configuration cfg = sessData.configurationModule.get();
		cfg.reload();
//		this.getLookupService().clearAllCaches();
	}
	
	protected void endTransaction() throws StaleObjectException
	{
		String methodName="endTransaction";
		
		Transaction transaction = factory.getTransaction();
		if (transaction != null)
		{
			transaction.commit();
			boolean wasRolledBack = transaction.wasRolledBack();
			boolean wasCommitted = transaction.wasCommitted();
			// commit
			if ( !wasRolledBack && ! wasCommitted ) {
				if (LOG.isDebugEnabled())
					LOG.debug("commited method "+methodName);
				if (getSessLogger() != null)
				{
					getSessLogger().warn("commited method "+methodName);
				}
			}
			else {
				if (LOG.isDebugEnabled())
					LOG.debug("method "+methodName+", wasRolledBack="+wasRolledBack+", wasCommitted="+wasCommitted);
				if (getSessLogger() != null)
				{
					getSessLogger().warn("method "+methodName+", wasRolledBack="+wasRolledBack+", wasCommitted="+wasCommitted);
				}
			}	
			factory.setTransaction(null);
		}

	}
	
	@SuppressWarnings("unchecked")
	protected final void clearCacheEntries()
	{
		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) Registry.getInstance().getSessionFactory();
		Map cacheRegionsMap = sessionFactoryImpl.getAllSecondLevelCacheRegions();
		Collection<Cache> cacheRegions = cacheRegionsMap.values();
		for (Cache cache : cacheRegions)
		{
			cache.clear();
		}
		Registry.getInstance().getSessionFactory().getStatistics().clear();
	}	

	protected final void clearCacheEntries(String className)
	{
		if (className == null)
			return;
		
		try
		{
			String[] collFields = (String[])Configuration.loadConfiguration().getClassConfig().getMappedRootClasses().get(className);
			Registry.getInstance().getSessionFactory().evict(Class.forName(className));
			if (collFields == null)
				return;
			
			for (int i = 0; i < collFields.length; i++)
			{
				// FWD-104 - need full class name of the collection fields
				String collField = className + "." + collFields[i];
				Registry.getInstance().getSessionFactory().evictCollection(collField);
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception occurred in clearCacheEntries - " + e.getMessage());
		}
	}
	
	protected final void EnableStats()
	{
		Registry.getInstance().getSessionFactory().getStatistics().setStatisticsEnabled(true);
	}
	
	protected final long getSecondLevelCacheHitCount()
	{
		return Registry.getInstance().getSessionFactory().getStatistics().getSecondLevelCacheHitCount(); 
	}
	protected final long getSecondLevelCacheMissCount()
	{
		return Registry.getInstance().getSessionFactory().getStatistics().getSecondLevelCacheMissCount();
	}
	protected final long getSecondLevelCachePutCount()
	{
		return Registry.getInstance().getSessionFactory().getStatistics().getSecondLevelCachePutCount();
	}
	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String message)
	{
		if(domainSession == null)
			return null;
		return domainSession.createSystemLogEntry(type, level, message);
	}
	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String username, String computer, String message)
	{
		if(domainSession == null)
			return null;
		return domainSession.createSystemLogEntry(type, level, username, computer, message);
	}
	public IAppUser getLoggedInUser() 
	{		
		if(domainSession == null)
			return null;
		return domainSession.getLoggedInUser();
	}		
}