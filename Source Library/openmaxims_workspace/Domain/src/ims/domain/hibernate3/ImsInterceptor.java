package ims.domain.hibernate3;

import ims.configuration.AuditInformation;
import ims.configuration.ClassConfig;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.configuration.EnvironmentConfig;
import ims.configuration.InitConfig;
import ims.configuration.PathwayEntityEvent;
import ims.configuration.RulesEngine;
import ims.domain.DomainObject;
import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.SystemInformation;
import ims.domain.SystemInformationRetainer;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.factory.PathwayEntityFactory;
import ims.domain.lookups.Lookup;
import ims.domain.lookups.LookupInstance;
import ims.framework.Context;
import ims.framework.SessionConstants;
import ims.framework.enumerations.ContextQueryItem;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.IPathwayEntityProvider;
import ims.rules.engine.RulesEngineFactory;
import ims.rules.engine.RulesRuntimeEngine;
import ims.utils.Logging;
import ims.vo.ValueObjectRef;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.StaleObjectStateException;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.collection.PersistentList;
import org.hibernate.collection.PersistentSet;
import org.hibernate.type.Type;

/**
 * @author jmacenri
 * 
 * Hibernate Interceptor for logging saves, updates and deletes to the AuditLogRecord Table
 */
public class ImsInterceptor extends EmptyInterceptor
{
	private static final long	serialVersionUID	= 1L;

	private static final Logger	LOG	= Logging.getLogger(ImsInterceptor.class);

	private final IAppUser		user;
	private final DomainSession	session;
	private final DomainFactory domainFactory;
	private HashMap	newObjects = new HashMap();
	private boolean lkpChangesLogged=false;  // WDEV-16075
	
	private final static String PREV_ATTS_START = "changed from *";
	private final static String PREV_ATTS_END = "*";
	private final static String NEW_ATTS_START = " to *";
	private final static String NEW_ATTS_END = "*";

	private static final String	UPDATE	= "UPDATE";
	private static final String	INSERT	= "INSERT";
	private static final String	DELETE	= "DELETE";
	private static final String	RIE	= "RIE";
	
	
	public ImsInterceptor(IAppUser user, DomainSession session, DomainFactory domainFactory)
	{
		this.user = user;
		this.session = session;
		this.domainFactory = domainFactory;
	}

	@SuppressWarnings("unchecked")
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException
	{
		//*************
		//  TIMESTAMP
		//*************
		
		
		int verIdx = getVersionIndex(propertyNames);
		if (verIdx >= 0)
		{
			int currVersion = ((Integer)currentState[verIdx]).intValue();
			int prevVersion = ((Integer)previousState[verIdx]).intValue();
			
			if (currVersion != prevVersion)
			{
				LOG.error("Entity called " + entity.getClass().getName() + " with id = " + id + " is stale. currentVersion = " + currVersion + ", prevVersion = " + prevVersion);
				throw new StaleObjectStateException(entity.getClass().getName(), id);
			}
		}

		//*************
		//   AUDIT
		//*************
		if (EnvironmentConfig.getAuditEnabled() && (entity instanceof DomainObject || entity instanceof LookupInstance) && this.isObjectAudited(entity.getClass()))
		{
			try
			{
				if (entity instanceof LookupInstance)
					auditLookupInstance(entity, currentState, previousState, propertyNames, UPDATE);
				else
					auditUpdate((DomainObject)entity, currentState, previousState, propertyNames);
			}
			catch (Exception e)
			{
				throw new DomainRuntimeException(e);
			}
		}
		
		
		// FWD-167 - do Dirty Check before setting System Information
		/**
		 * Sets the dirty objects and properties on this save.
		 */
		if (domainFactory.doDirtyCheck())
		{
			
			ArrayList prop=domainFactory.getDirtyEntities(); //Setting all the dirty objects.
			ArrayList dirties=domainFactory.getDirtyProperties();
			Object a,b,c=null;
			//Setting all the dirty properties
			for (int i=0;i<currentState.length;i++)
			{
				a=currentState[i];
				b=previousState[i];
				c=propertyNames[i];
				if (a!=null && b!=null)
				{
				boolean l=a.equals(b);
				if(!l)
				{
					dirties.add(c);
					prop.add(entity);
				}
				}
				else if ((a!=null && b==null)||(a==null && b!=null))
				{
					dirties.add(c);
					prop.add(entity);
				}
				
			}
		}
		
		//********************
		// System Information
		//********************
		if (entity instanceof SystemInformationRetainer)
		{
			SystemInformation sysInfo = ((SystemInformationRetainer) entity).getSystemInformation();
			sysInfo.setLastUpdateDateTime(new java.util.Date());
			if (this.user != null)
				sysInfo.setLastUpdateUser(this.user.getUsername());
			else if (this.session!=null && this.session.getUser()!=null && this.session.getUser().getUsername()!=null)
					sysInfo.setLastUpdateUser(this.session.getUser().getUsername());
				 else
					sysInfo.setLastUpdateUser("Unknown");
		}
		
		// WDEV-17644
		// WDEV-18847
		// FWD-239
		if (ConfigFlag.DOM.PATHWAY_ENTITY_EVENT_FUNCTIONALITY.getValue() || ConfigFlag.DOM.ENTITY_TARGETMETHOD_FUNCTIONALITY.getValue())
		{
			// If this entity is part of PathwaysEntity - we may need to create a new PatientEvent
			SessionData sessData = (SessionData)session.getAttribute(SessionConstants.SESSION_DATA);
			if (sessData != null)
			{
				List<PathwayEntityEvent> pathwayEntities = sessData.configurationModule.get().getPathwayEntities();
				if (pathwayEntities != null)
				{					
					// 	Check to see if this entity is marked for event instantiation
					List<PathwayEntityEvent> entityList = findEntity(pathwayEntities, entity.getClass().getSimpleName());
					//FWD-239
					if (entityList != null && entityList.size() > 0)
					{
						for (PathwayEntityEvent ent : entityList)
						{
							if (ent.getTargetMethod() == null && ent.getEvent() != null && ConfigFlag.DOM.PATHWAY_ENTITY_EVENT_FUNCTIONALITY.getValue())
							{
								LOG.info("Pathway EntityEvent event found for entity " + entity.toString());
								checkCreateEvent(ent, entity, id, previousState, propertyNames);
							}
							else if (ent.getEvent() == null && ConfigFlag.DOM.ENTITY_TARGETMETHOD_FUNCTIONALITY.getValue())
							{
								if (ent.getTargetMethod() != null) 
									LOG.info("Target Method Rule found for entity " + entity.toString());
								else
									LOG.info("Rule found for entity " + entity.toString());
								
								// retrieve previous state collections
								Object[] previousStateSnapShot = getPrevStateCollections((DomainObject)entity, currentState, previousState, propertyNames); 
								invokeTargetMethod(ent, entity, id, previousStateSnapShot, propertyNames);								
							}
						}
					}
				}
			}
		}

		if (LOG.isDebugEnabled())
			LOG.debug("ImsInterceptor onFlushDirty exit");
		
		return false;
	}
	
	// FWD-239
	private void invokeTargetMethod(PathwayEntityEvent ent, Object entity,
			Serializable id, Object[] previousState, String[] propertyNames) 
	{
		if (ent.getRule() != null)
		{
			LOG.info("Rule found for Entity - " + ent.getRule());
			if(InitConfig.getRulesEngine() != RulesEngine.NONE)
			{
				ims.rules.engine.RulesEngine rulesEngine = RulesEngineFactory.getInstance();
				
				java.util.HashMap<String,Object> prevState = new HashMap<String,Object>();
				if (propertyNames != null && previousState != null)
				{
					for (int i=0; i<propertyNames.length; i++)
					{
						prevState.put(propertyNames[i], previousState[i]);
					}
				}
				 			
				HashMap<String, Serializable> global = new HashMap<String, Serializable>();
				RulesRuntimeEngine rrEngine = new RulesRuntimeEngine(rulesEngine, this.domainFactory, session, (Integer)id, prevState);
				rrEngine.setPathwayEntitySpecificRuleName(ent.getRule());
				global.put("engine", rrEngine);
				global.put("factory", this.domainFactory);
				global.put("prevState", prevState);
				if (ent.getTargetMethod()!=null)
					global.put("targetMethod",ent.getTargetMethod());
				
				Object[] facts = new Object[] { entity };
				
				rulesEngine.fireAllRules(global, facts);
			}			
		}		
	}

	private void auditLookupInstance(Object entity, Object[] currentState, Object[] previousState, String[] propertyNames, String action) 
	{
		StringBuffer diffStr = new StringBuffer();
		
		if (currentState != null && previousState != null)
		{
			for (int i = 0; i < currentState.length; i++)
			{
				String diffValue;
				try
				{
					diffValue = getFieldDiffStr(currentState[i], previousState[i]);
				}
				catch (Exception e)
				{
					throw new DomainRuntimeException(e);
				}
				if (diffValue != null && !diffValue.equals(""))
					diffStr.append("\r\n*" + propertyNames[i] + "*  :  " + diffValue);

			}
			logChanges(entity, diffStr.toString(), action);
		}
		else
		{
			logChanges(entity, ((LookupInstance)entity).toAuditString(), action);
		}
		lkpChangesLogged=true;
		
		
	}

	@SuppressWarnings("unchecked")
	public boolean onSave(Object entity, Serializable id, Object[] newValues, String[] properties, Type[] types) throws CallbackException
	{
		if (EnvironmentConfig.getAuditEnabled() && this.isObjectAudited(entity.getClass()) )
		{
			this.newObjects.put(entity, entity);
		}

		//********************
		// System Information
		//********************
		if (entity instanceof SystemInformationRetainer)
		{
			SystemInformation sysInfo = ((SystemInformationRetainer) entity).getSystemInformation();
			sysInfo.setCreationDateTime(new java.util.Date());
			if (this.user != null)
				sysInfo.setCreationUser(this.user.getUsername());
			else
				if (this.session!=null && this.session.getUser()!=null && this.session.getUser().getUsername()!=null)
					sysInfo.setCreationUser(this.session.getUser().getUsername());
				else
					sysInfo.setCreationUser("Unknown");
		}		
		
		// WDEV-17644 
		if (!entity.getClass().getName().equals("ims.core.configuration.domain.objects.ConfigFlag") && !entity.getClass().getName().equals("ims.core.configuration.domain.objects.ConfigFlagGroup") && 
				ConfigFlag.DOM.PATHWAY_ENTITY_EVENT_FUNCTIONALITY.getValue())
		{
			// If this entity is part of PathwaysEntity - we may need to create a new PatientEvent
			SessionData sessData = (SessionData)session.getAttribute(SessionConstants.SESSION_DATA);
			if (sessData != null)
			{
				List<PathwayEntityEvent> pathwayEntities = sessData.configurationModule.get().getPathwayEntities();
				if (pathwayEntities != null)
				{
					// Check to see if this entity is marked for event instantiation
					List<PathwayEntityEvent> entityList = findEntity(pathwayEntities, entity.getClass().getSimpleName());
					if (entityList != null && entityList.size() > 0)
					{
						for (PathwayEntityEvent ent : entityList)
						{
							if (ent.getEvent() != null)
							{
								LOG.info("Pathway EntityEvent event found for entity " + entity.toString());								
								checkCreateEvent(ent, entity, id, null, null);
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * WDEV-17644
	 * Is the given entity found in the given list
	 * @param pathwayEntities
	 * @return the entity if found, else null
	 */
	private List<PathwayEntityEvent> findEntity(List<PathwayEntityEvent> pathwayEntities, String entityName) 
	{
		if (pathwayEntities == null || entityName == null)
			return null;
		
		List<PathwayEntityEvent>  foundList = new ArrayList<PathwayEntityEvent>();
		for (PathwayEntityEvent ent : pathwayEntities)
		{
			if (ent.getEntityName() != null && ent.getEntityName().equals(entityName) && ent.isActive())
				foundList.add(ent);
		}
		
		return foundList;
	}
	
	private void checkCreateEvent(PathwayEntityEvent pathwayEntity, Object entity, Serializable id, Object[] previousState, String[] propertyNames)
	{
		PathwayEntityFactory factory=new PathwayEntityFactory(session);
		if (!factory.hasPathwayEntityProvider())
		{
			LOG.error("No PathwayEntityProvider found");
		}
		
		// If a rule is associated with this EntityEvent config, then we need to execute it and check the result
		boolean createEvent=true;
		if (pathwayEntity.getRule() != null)
		{
			LOG.info("Rule found for PathwayEntityEvent - " + pathwayEntity.getRule());
			if(InitConfig.getRulesEngine() != RulesEngine.NONE)
			{
				ims.rules.engine.RulesEngine rulesEngine = RulesEngineFactory.getInstance();
				
				java.util.HashMap<String,Object> prevState = new HashMap<String,Object>();
				if (propertyNames != null && previousState != null)
				{
					for (int i=0; i<propertyNames.length; i++)
					{
						prevState.put(propertyNames[i], previousState[i]);
					}
				}
				 			
				
				
				HashMap<String, Serializable> global = new HashMap<String, Serializable>();
				RulesRuntimeEngine rrEngine = new RulesRuntimeEngine(rulesEngine, this.domainFactory, session, (Integer)id, prevState);
				rrEngine.setPathwayEntitySpecificRuleName(pathwayEntity.getRule());
				global.put("engine", rrEngine);
				global.put("factory", this.domainFactory);
				global.put("prevState", prevState);				
				
				Object[] facts = new Object[] { entity };
				
				int returnVal = rulesEngine.fireAllRules(global, facts);
				if (rrEngine.getLastRuleResult() == 0 || !rrEngine.getLastRuleName().equals(pathwayEntity.getRule()))  // Don't create event if not the rule we are interested in
					createEvent=false;
			}
			else
			{
				createEvent=false;  // FWD-241 a rule is specified but no rules engine - DO NOT CREATE EVENT
			}
		}
		
		if (createEvent)
		{
			// If ScheduledDateTime field is set, retrieve it and pass to the createEvent method
			Date scheduledDateTime=null;
			if (pathwayEntity.getScheduledDateField() != null)
			{
				scheduledDateTime=getFieldByReflection(entity, pathwayEntity.getScheduledDateField());
			}
			IPathwayEntityProvider provider = factory.getPathwayEntityProvider();
			SessionData sessData = (SessionData)session.getAttribute(SessionConstants.SESSION_DATA);
			Integer patientId = null;
			if (sessData != null && sessData.context.get() != null)
			{		
				Context ctx = sessData.context.get();
				
				ValueObjectRef voRef = (ValueObjectRef)ctx.get(ContextQueryItem.PATIENT.getKey());
				if (voRef != null)
					patientId = voRef.getBoId();
			}
			// Call the rule Providers method to create the Event
			if (scheduledDateTime != null)
				provider.createEvent(pathwayEntity.getEvent(), pathwayEntity.getEntityName(), (Integer) id, patientId, null, new ims.framework.utils.DateTime(scheduledDateTime), pathwayEntity.getDescription());
			else
				provider.createEvent(pathwayEntity.getEvent(), pathwayEntity.getEntityName(), (Integer) id, patientId, null, null, pathwayEntity.getDescription());
		}
	}

	private Date getFieldByReflection(Object entity, String scheduledDateField) 
	{
		String methodName = "get" + scheduledDateField.substring(0, 1).toUpperCase() + scheduledDateField.substring(1);
		try 
		{
			Method mthd = entity.getClass().getDeclaredMethod(methodName);
			Object value = mthd.invoke(entity);
			return (Date)value;
		} 
		catch (SecurityException e) 
		{
			LOG.error("SecurityException trying to access method " + methodName + " from entity = " + entity.toString(), e);
			return null;
		} 
		catch (NoSuchMethodException e) 
		{
			LOG.error("NoSuchMethodException trying to access method " + methodName + " from entity " + entity.toString(), e);
			return null;
		} 
		catch (IllegalArgumentException e) 
		{
			LOG.error("IllegalArgumentException trying to access method " + methodName + " from entity " + entity.toString(), e);
			return null;
		} 
		catch (IllegalAccessException e) 
		{
			LOG.error("IllegalAccessException trying to access method " + methodName + " from entity " + entity.toString(), e);
			return null;
		} 
		catch (InvocationTargetException e) 
		{
			LOG.error("IllegalAccessException trying to access method " + methodName + " from entity " + entity.toString(), e);
			return null;

		}
	}

	public void onDelete(Object entity, Serializable id, Object[] newValues, String[] properties, Type[] types) throws CallbackException
	{
		if (EnvironmentConfig.getAuditEnabled() && (entity instanceof DomainObject || entity instanceof LookupInstance) && this.isObjectAudited(entity.getClass()) )
		{
			if (entity instanceof LookupInstance)
				auditLookupInstance(entity, null, null, null, DELETE);
			else
				auditDelete((DomainObject)entity);
		}
	}


	public void postFlush(Iterator arg0) throws CallbackException
	{
		if (!EnvironmentConfig.getAuditEnabled())
			return;
		
		if (LOG.isDebugEnabled())
			LOG.debug("ImsInterceptor postFlush entry");
		// See if any of the objects in newObject are within the iterator
		// if found, we want to create the audit record for them
		// Code is in postFlush for all new entities as otherwise
		// we will not have the id of the object. Only required
		// for saves. Cannot have all in one place as we
		// need to differentiate the action ( BW 05/10/2004)
		Iterator it = newObjects.values().iterator();
		while (it.hasNext())
		{
			Object entity = it.next();
			if ((entity instanceof DomainObject || entity instanceof LookupInstance) &&  this.isObjectAudited(entity.getClass()))
			{
				if (entity instanceof LookupInstance)
					auditLookupInstance(entity, null, null, null, INSERT);
				else
					auditInsert((DomainObject)entity);
			}
		}
		this.newObjects.clear();

		if (LOG.isDebugEnabled())
			LOG.debug("ImsInterceptor postFlush exit");
	}

	public Object instantiate(String entityName, EntityMode entityMode, Serializable id)
	{
		LOG.debug("Instantiate - entityName = " + entityName + " ID = " + id);
		Boolean includeRIE = null;
		String rieBoClassName =  null;
		SessionData sessData = (SessionData)session.getAttribute(SessionConstants.SESSION_DATA);
		if (sessData != null)
		{
			includeRIE = sessData.listRIERecordsOnly.get();
			rieBoClassName = sessData.rieBoClassName.get();
		}
		if (rieBoClassName == null)
		{
			LOG.debug("Instantiate - Exit (rieClassName = null)");
			return null;
		}
				
		if (includeRIE != null && includeRIE.booleanValue() == true && !entityName.startsWith("ims.domain.lookups") && !entityName.startsWith("ims.configuration") &&
				rieBoClassName != null && rieBoClassName.length() > 0 && rieBoClassName.indexOf(entityName) >= 0)
		{
			try
			{
				LOG.debug("Instantiate - Including RIE Records");
				Class cls = Class.forName(entityName);
				Class[] parameterTypes = new Class[3];
				Object[] parameterValues = new Object[3];
				parameterTypes[0] = Integer.class;
				parameterTypes[1] = Integer.TYPE;
				parameterTypes[2] = Boolean.class;
				
				parameterValues[0] = id;
				parameterValues[1] = new Integer(0);
				parameterValues[2] = Boolean.TRUE;
				Constructor rieConstructor = cls.getConstructor(parameterTypes);
				Object entityObject = rieConstructor.newInstance(parameterValues);
				return entityObject;

			}
			catch (Exception e)
			{
				return null;
			}
		}

		LOG.debug("Instantiate - Exit (null)");
		return null;
	}

	private void auditInsert(DomainObject domObject)
	{
		if(domObject.canNeverBeAudited())
			return;
		
		logChanges(domObject, domObject.toAuditString(), INSERT);
		
	}
	private void auditDelete(DomainObject domObject)
	{
		if(domObject.canNeverBeAudited())
			return;
		
		logChanges(domObject, "Object Deleted.", DELETE);
	}

	private void auditUpdate(DomainObject entity, Object[] currentState, Object[] previousState, String[] propertyNames)
	{
		if(entity.canNeverBeAudited())
			return;
		
		String action = UPDATE;		
		StringBuffer diffStr = new StringBuffer();
		
		for (int i = 0; i < currentState.length; i++)
		{
			String diffValue;
			try
			{
				if (propertyNames[i].equals("isRIE") && currentState[i] != previousState[i])
				{
					action = RIE;
				}
				diffValue = getFieldDiffStr(currentState[i], previousState[i]);
			}
			catch (Exception e)
			{
				throw new DomainRuntimeException(e);
			}
			if (diffValue != null && !diffValue.equals(""))
				diffStr.append("\r\n*" + propertyNames[i] + "*  :  " + diffValue);

		}
		logChanges(entity, diffStr.toString(), action);
	}

	private void logChanges(Object domObject, String diffStr, String event) 
	{
		if (!EnvironmentConfig.getAuditEnabled())
			return;

		if (diffStr == null || diffStr.equals(""))
			return;
		
		String className = domObject.getClass().getName();
		AuditInformation audInfo = new AuditInformation();
		audInfo.setAuditDateTime(new java.util.Date());
		audInfo.setAuditUser(this.user == null ? "<unknown>" : this.user.getUsername());
		audInfo.setAuditHost(this.user == null ? "<unknown>" : this.user.getHostName());
		SessionData sessData = (SessionData) this.session.getAttribute(SessionConstants.SESSION_DATA);
		if (sessData != null && sessData.selectedLocation != null && sessData.selectedLocation.get() != null)
			audInfo.setAuditUserLocation(new Integer(sessData.selectedLocation.get().getID()));
		
		// wdev-5625 - role added
		if (sessData != null && sessData.role != null && sessData.role.get() != null)
			audInfo.setAuditRoleId(new Integer(sessData.role.get().getId()));

		audInfo.setAuditAction(event);
		audInfo.setDiffString(diffStr);
		if (domObject instanceof DomainObject)
			audInfo.setClassIdentifier(((DomainObject)domObject).getId());
		else if (domObject instanceof LookupInstance)
			audInfo.setClassIdentifier(((LookupInstance)domObject).getId());
			
		audInfo.setClassName(className);
		
		Integer patId = null;
		Integer contextId = null;
		Integer contactId = null;

		if (sessData != null && sessData.context.get() != null)
		{		
			Context ctx = sessData.context.get();
			
			ValueObjectRef voRef = (ValueObjectRef)ctx.get(ContextQueryItem.PATIENT.getKey());
			if (voRef != null)
				patId = voRef.getBoId();

			voRef = (ValueObjectRef)ctx.get(ContextQueryItem.CLINICAL_CONTACT.getKey());
			if (voRef != null)
				contactId = voRef.getBoId();

			voRef = (ValueObjectRef)ctx.get(ContextQueryItem.CURRENT_CARE_CONTEXT.getKey());
			if (voRef != null)
				contextId = voRef.getBoId();			
		}

		audInfo.setPatientId(patId);
		audInfo.setCareContextId(contextId);
		audInfo.setContactId(contactId);
		
		// WDEV-15061 If GC for patient is null, check if this field is instance of Patient, if it is, then use it
		if (patId == null && ConfigFlag.DOM.AUDIT_PATIENT_FROM_OBJECT.getValue())
		{
			// Get Fields within the object and check if any instance of Patient
			// if so, use this for the patient id
			try {
				Class cls = Class.forName(domObject.getClass().getName());
			
				if (cls != null)
				{
					if (domObject.getClass().getName().equals("ims.core.patient.domain.objects.Patient"))
					{
						// If this is the actual patient and the GC is null, then get the current ID
						Method m = cls.getMethod("getId", null);
						Object patIdInt = m.invoke(domObject, null);
						if (patIdInt != null)
						{
							audInfo.setPatientId((Integer) patIdInt);
							LOG.warn("Audit Patient Identifier taken from patient object and not Global Context PAT(" + patIdInt + ")");
						}
					}
					else
					{
						// If this object is a configuration object, do not continue
						Method mc = cls.getMethod("isConfigurationObject", null);
						Object confObj = mc.invoke(domObject, null);
						if (confObj != null && ((Boolean)confObj) == false)
						{
							Field fieldlist[]  = cls.getDeclaredFields();
							for (int i= 0; i < fieldlist.length; i++) 
							{
								Field fld = fieldlist[i];
								if (fld.getType().getName().equals("ims.core.patient.domain.objects.Patient") && fld.getName().equalsIgnoreCase("Patient"))
								{
									// 	find the getPatient method
									Method m = cls.getMethod("getPatient", null);
									Object pat = m.invoke(domObject, null);
									Class cl = Class.forName("ims.core.patient.domain.objects.Patient");
									if (pat != null && cl.isInstance(pat))  // this is a patient
									{
										patId = ((DomainObject)pat).getId();
										if (patId != null)
										{
											audInfo.setPatientId(patId);
											LOG.warn("Audit Patient Identifier taken from object and not Global Context PAT(" + patId + ") - " + domObject.getClass().getName());
										}
									}
									break; // Found patient, so break out of this loop now
								}
							}
						}
					}
		    	}
			}
			catch (Exception e) 
			{
				// Nothing to do if class not found, patId won't be set, but no need to log the fact
			}
		}

		
		
		//JME: 20061120: Using domainFactory used by Impl to ensure save of audit records happens within same transaction
		if (domainFactory == null)
			throw new DomainRuntimeException("DomainFactory used within ImsInterceptor cannot be null");
		try
		{
			domainFactory.saveNoFlush(audInfo);
		}
		catch (StaleObjectException e)
		{
			throw new DomainRuntimeException(e);
		}		
	}

	private String getFieldDiffStr(Object newVal, Object oldVal) throws Exception
	{
		if (newVal == null && oldVal == null)
			return null;
		
		Class c;
		if (newVal != null)
			c = newVal.getClass();
		else
			c = oldVal.getClass();
		
		if (c.equals(Integer.class) || 
			c.equals(Boolean.class) ||
			c.equals(Double.class) ||
			c.equals(Float.class) || // FWD-213
			c.equals(Date.class) ||
			c.equals(java.sql.Date.class) || //WDEV-14670 
			c.equals(String.class) || //WDEV-18054
			c.equals(java.sql.Timestamp.class))
		{
			if (newVal == null && oldVal != null)
				return new String(PREV_ATTS_START + oldVal + PREV_ATTS_END + "  " + NEW_ATTS_START + "null" + NEW_ATTS_END);
			else if (newVal != null && oldVal == null)
				return new String(PREV_ATTS_START + "null" + PREV_ATTS_END + "  " + NEW_ATTS_START + newVal + NEW_ATTS_END);
			else if (newVal != null && oldVal != null && !newVal.equals(oldVal))
				return new String(PREV_ATTS_START + oldVal + PREV_ATTS_END + "  " + NEW_ATTS_START + newVal + NEW_ATTS_END);
		}
		else if (c.equals(LookupInstance.class))
		{
			if (newVal == null && oldVal != null)
				return new String(PREV_ATTS_START  + ((LookupInstance) oldVal).getText() + PREV_ATTS_END + "  " + NEW_ATTS_START + "null" + NEW_ATTS_END);
			else if (newVal != null && oldVal == null)
				return new String(PREV_ATTS_START + "null" + PREV_ATTS_END + "  " + NEW_ATTS_START + ((LookupInstance) newVal).getText() + NEW_ATTS_END);
			else if (newVal != null && oldVal != null && ((LookupInstance) newVal).getId() != ((LookupInstance) oldVal).getId())
				return new String(PREV_ATTS_START + ((LookupInstance) oldVal).getText() + PREV_ATTS_END + "  " + NEW_ATTS_START + ((LookupInstance) newVal).getText() + NEW_ATTS_END);
		}
		else if (c.equals(Lookup.class))
		{
			if (newVal == null && oldVal != null)
				return new String(PREV_ATTS_START  + ((Lookup) oldVal).getName() + PREV_ATTS_END + "  " + NEW_ATTS_START + "null" + NEW_ATTS_END);
			else if (newVal != null && oldVal == null)
				return new String(PREV_ATTS_START + "null" + PREV_ATTS_END + "  " + NEW_ATTS_START + ((Lookup) newVal).getName() + NEW_ATTS_END);
			else if (newVal != null && oldVal != null && ((Lookup) newVal).getId() != ((Lookup) oldVal).getId())
				return new String(PREV_ATTS_START + ((Lookup) oldVal).getName() + PREV_ATTS_END + "  " + NEW_ATTS_START + ((Lookup) newVal).getName() + NEW_ATTS_END);
		}
		else if (newVal instanceof DomainObject || oldVal instanceof DomainObject)
		{
			DomainObject domObj = null;
			if (newVal != null)
				domObj = (DomainObject)newVal;
			else if (oldVal != null)
				domObj = (DomainObject)oldVal;
			
			if (domObj.getIsComponentClass())
				return generateComponentDiffMessage(newVal, oldVal);
							
			if (newVal == null && oldVal != null)
				return new String(PREV_ATTS_START + toShortClassName(oldVal) + " " + ((DomainObject) oldVal).getId().intValue() + PREV_ATTS_END + "  " + NEW_ATTS_START + "null" + NEW_ATTS_END);
			else if (newVal != null && oldVal == null)
				return new String(PREV_ATTS_START + toShortClassName(newVal) + " null" + PREV_ATTS_END + "  " + NEW_ATTS_START + ((DomainObject) newVal).getId().intValue() + NEW_ATTS_END);
			else if (newVal != null && oldVal != null && ((DomainObject) oldVal).getId().intValue() != ((DomainObject) newVal).getId().intValue())
				return new String(PREV_ATTS_START + toShortClassName(newVal) + " " + ((DomainObject) oldVal).getId().intValue() + PREV_ATTS_END + "  " + NEW_ATTS_START + ((DomainObject) newVal).getId().intValue() + NEW_ATTS_END);
		}
		/**
		 * PersistentSet and PersistentList.  If newVal is not a new collection, it will
		 * have the original values stored within the storedSnapshot.  
		 */
		else if (newVal instanceof Collection || oldVal instanceof Collection)
		{
			Iterator oldIt = null; 
			Iterator newIt = null;
			if (newVal != null)
			{
				//JME: 20061127: Force an initialisation of the collection.
				((Collection)newVal).size();
				newIt = ((Collection)newVal).iterator();
				AbstractPersistentCollection ps = (AbstractPersistentCollection)newVal;		
				//JME: 20061123: Have found that for collection fields where newVal is not Dirty, then Old Value Collection 
				//can be got from oldVal  getStoredSnapshot
				if (!ps.isDirty() && oldVal != null)
				{
					//JME: 20061127: Force an initialisation of the collection.
					((Collection)oldVal).size();
					ps = (AbstractPersistentCollection)oldVal;
				}
				
				if (newVal instanceof PersistentList)
				{
					oldIt = ((Collection)(ps.getStoredSnapshot())).iterator();					
				}
				else
				{
					oldIt = ((Map)(ps.getStoredSnapshot())).values().iterator();
				}
			}
			
			if (oldVal != null && oldIt == null)
			{
				oldIt = ((Collection)oldVal).iterator();
/*				if (oldVal instanceof PersistentList)
				{
					oldIt = ((Collection)oldVal).iterator();
				}
				else
				{
					oldIt = ((Map)oldVal).values().iterator();
				}
*/				
			}
			
			return(parseCollection(oldIt, newIt));
		}

		return null;
	}

	private String parseCollection(Iterator prevIt, Iterator newIt)
	{
		boolean prevItItems=false;
		if (prevIt != null && prevIt.hasNext())
			prevItItems=true;
		boolean newItItems=false;
		if (newIt != null && newIt.hasNext())
			newItItems=true;
		
		int prevItemsCount=0, newItemsCount=0;
		StringBuffer listStr = new StringBuffer();
		StringBuffer prevString=new StringBuffer();
		StringBuffer newString=new StringBuffer();
		String className=null;
		if (prevItItems)
		{
			while (prevIt.hasNext())
			{
				Object obj = prevIt.next();
				if (prevItemsCount == 0 && obj != null)
				{
					className=toShortClassName(obj);
				}
				else
					prevString.append(", ");
				prevString.append(getObjectDescription(obj));
				prevItemsCount++;
			}
		}
		
		else if (newItItems)  // Only interested in prev null if new has values
			prevString.append("null");
		
		if (prevString.length() > 0)
		{
			listStr.append(PREV_ATTS_START);
			if (!prevString.toString().equals("null"))
				 listStr.append("[");
			listStr.append(prevString);
			if (!prevString.toString().equals("null"))
				listStr.append("] " + prevItemsCount);
			listStr.append(PREV_ATTS_END);
		}
		
		if (newItItems)
		{
			while (newIt.hasNext())
			{
				Object obj = newIt.next();
				if (newItemsCount == 0 && obj != null)
				{
					if (className == null)
						className=toShortClassName(obj);
				}
				else
					newString.append(", ");
				newString.append(getObjectDescription(obj));
				newItemsCount++;
			}
		}
		else if (prevItItems)  // Only interested in new null if prev had values
			newString.append("null");
		
		if (newString.length() > 0)
		{
			listStr.append(NEW_ATTS_START);
			if (!newString.toString().equals("null"))
				 listStr.append("[");
			listStr.append(newString);
			if (!newString.toString().equals("null"))
				listStr.append("] " + newItemsCount);
			listStr.append(NEW_ATTS_END);
		}
		
		if (prevString != null && newString != null && !prevString.toString().equals(newString.toString()))
			return className + " " + listStr.toString();
		else
			return null;
	}

	private String getObjectDescription(Object object)
	{		
		if (object instanceof LookupInstance)
			return(((LookupInstance)object).getText());
		else if (object instanceof DomainObject && object != null)
		{
			if (((DomainObject)object).getIsComponentClass())
				return String.valueOf((((DomainObject)object).toString()));
			else
				return String.valueOf((((DomainObject)object).getId()));
		}
		else if (object != null)
			return(object.toString());
		
		return null;
	}

	private String generateComponentDiffMessage(Object newObject, Object oldObject) throws Exception
	{
		if (newObject == null && oldObject == null)
			return null;
		
		String pre = "\r\n ==> *";
		StringBuffer diffStr = new StringBuffer();
		Field[] fields;
		if (newObject != null)
		{
			fields = this.getAllFields(newObject.getClass(), null);			
		}
		else
		{
			fields = this.getAllFields(oldObject.getClass(), null);			
		}		

		for (int i = 0; i < fields.length; i++)
		{
			String diffValue;
			fields[i].setAccessible(true);
			int mod = fields[i].getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || Modifier.isTransient(mod))
			{
				continue;
			}
			String fieldName = fields[i].getName();
			if (fieldName.equals("isComponentClass") || fieldName.equals("includeRecord") || fieldName.equals("version"))
			{
				continue;
			}
			try
			{
				Object newFieldVal = newObject != null ? fields[i].get(newObject) : null;
				Object oldFieldVal = oldObject != null ? fields[i].get(oldObject) : null;
				diffValue = getFieldDiffStr(newFieldVal, oldFieldVal);
			}
			catch (Exception e)
			{
				throw new DomainRuntimeException(e);
			}
			if (diffValue != null && !diffValue.equals(""))
				diffStr.append(pre + fields[i].getName() + "*  :  " + diffValue);
		}
		if (diffStr.length() == 0) 
			return null;
		else 
			return diffStr.toString();
	}

	private String toShortClassName(Object obj)
	{
		String shortClassName = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1);
		return "(" + shortClassName + ")";
	}

	/**
	 * Returns an array of all fields used by this object from it's class and all superclasses.
	 * 
	 * @param objectClass
	 *            the class
	 * @param fields
	 *            the current field list
	 * @return an array of fields
	 */
	private Field[] getAllFields(Class objectClass, Field[] fields)
	{
		Field[] newFields = objectClass.getDeclaredFields();
		int fieldsSize = 0;
		int newFieldsSize = 0;

		if (fields != null)
		{
			fieldsSize = fields.length;
		}

		if (newFields != null)
		{
			newFieldsSize = newFields.length;
		}

		Field[] totalFields = new Field[fieldsSize + newFieldsSize];
		if (fieldsSize > 0)
		{
			System.arraycopy(fields, 0, totalFields, 0, fieldsSize);
		}
		if (newFieldsSize > 0)
		{
			System.arraycopy(newFields, 0, totalFields, fieldsSize, newFieldsSize);
		}

		Class superClass = objectClass.getSuperclass();
		Field[] finalFieldsArray;
		if (superClass != null && !superClass.getName().equals("java.lang.Object"))
		{
			finalFieldsArray = getAllFields(superClass, totalFields);
		}
		else
		{
			finalFieldsArray = totalFields;
		}
		return finalFieldsArray;
	}

	private boolean isObjectAudited(Class clazz)
	{
		return isObjectAudited(clazz.getName());
	}

	private boolean isObjectAudited(String name)
	{
		SessionData sessData = getSessionData();
		if (sessData == null)
			throw new DomainRuntimeException("SessionData in DomainSession cannot be null!!");
		Configuration cfg = sessData.configurationModule.get();
		if (cfg == null)
			return false;
		ClassConfig classCfg = cfg.getClassConfig();
		if (classCfg == null)
			return false;
		
		return classCfg.isBoAudited(name);
	}
	
	private SessionData getSessionData()
	{
		return (SessionData)this.session.getAttribute(SessionConstants.SESSION_DATA);
	}

	private int getVersionIndex(String[] propertyNames)
	{
		for (int i = 0; i < propertyNames.length; i++)
		{
			if (propertyNames[i].equalsIgnoreCase("version"))
				return i;
		}
		return -1;
	}
	
	
	/** WDEV-16075
	 * Audit lookup instance mappings
	 */
	public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException 
	{
		if (collection instanceof PersistentSet)
		{
			PersistentSet typeColl = (PersistentSet)collection;
			if (typeColl.getOwner() instanceof LookupInstance  && typeColl.getRole() != null && typeColl.getRole().equals("ims.domain.lookups.LookupInstance.mappings"))
			{
				
				auditLookupInstanceMappings((LookupInstance) typeColl.getOwner(), typeColl, UPDATE);
			}
		}

	}
	
	public void onCollectionRemove(Object collection, Serializable key) throws CallbackException 
	{
		if (collection instanceof PersistentSet)
		{
			PersistentSet typeColl = (PersistentSet)collection;
			if (typeColl.getOwner() instanceof LookupInstance  && typeColl.getRole() != null && typeColl.getRole().equals("ims.domain.lookups.LookupInstance.mappings"))
			{
				
				auditLookupInstanceMappings((LookupInstance) typeColl.getOwner(), typeColl, UPDATE);
			}
		}
	}

	public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException 
	{
		if (collection instanceof PersistentSet)
		{
			PersistentSet typeColl = (PersistentSet)collection;
			if (typeColl.getOwner() instanceof LookupInstance && typeColl.getRole() != null && typeColl.getRole().equals("ims.domain.lookups.LookupInstance.mappings"))
			{
				
				auditLookupInstanceMappings((LookupInstance) typeColl.getOwner(), typeColl, UPDATE);
			}
		}
	}
	
	/**
	 * WDEV-16075
	 * This method simply details what the mappings have been changed to
	 * @param owner
	 * @param typeColl
	 * @param updateType
	 */
	private void auditLookupInstanceMappings(LookupInstance owner, PersistentSet typeColl, String updateType) 
	{
		if (EnvironmentConfig.getAuditEnabled() && this.isObjectAudited(owner.getClass()) && !lkpChangesLogged)
		{
			StringBuffer diffStr = new StringBuffer();
			HashMap prevVal = (HashMap) typeColl.getStoredSnapshot();
			diffStr.append(parseCollection(prevVal.entrySet().iterator(), typeColl.iterator()));
			logChanges(owner, diffStr.toString(), updateType);
		}
	}

	private Object[] getPrevStateCollections(DomainObject entity, Object[] currentState, Object[] previousState, String[] propertyNames)
	{
		Object[] previousStateCpy = new Object[previousState.length];
		
		for (int i = 0; i < currentState.length; i++)
		{
			String diffValue;
			try
			{				
				previousStateCpy[i] = getprevState(currentState[i], previousState[i]);
			}
			catch (Exception e)
			{
				throw new DomainRuntimeException(e);
			}
		}
		return previousStateCpy;
		
	}
	
	private Object getprevState(Object newVal, Object oldVal) throws Exception
	{		
		/**
		 * PersistentSet and PersistentList.  If newVal is not a new collection, it will
		 * have the original values stored within the storedSnapshot.  
		 */		
		//force initalisation and copy
		Object preVal = copy(oldVal);
		if (newVal instanceof Collection || preVal instanceof Collection)
		{
			Iterator oldIt = null; 
			if (newVal != null)
			{
				((Collection)newVal).size();
				AbstractPersistentCollection ps = (AbstractPersistentCollection)preVal;		
				//old data can be got from oldVal  getStoredSnapshot
				if (!ps.isDirty() && preVal != null)
				{
					((Collection)preVal).size();
					ps = (AbstractPersistentCollection)preVal;
				}
				
				//only process collections for now
				if (preVal instanceof PersistentList)
				{
					oldIt = ((Collection)(ps.getStoredSnapshot())).iterator();	
					if (preVal != null && oldIt != null)
					{
						((Collection)preVal).clear();
						((Collection)preVal).addAll((Collection)ps.getStoredSnapshot());
					}													
				}				
				else if (preVal instanceof PersistentSet)
				{
					//this is a Map
					PersistentSet prevValues = (PersistentSet) preVal; 
					if (prevValues != null)
					{
						((Collection)preVal).clear();
						((Collection)preVal).add(((Map<?,?>) prevValues.getStoredSnapshot()).values());
					}																		
				}				
			}
		}

		return preVal;
	}

	public static Object copy(Object orig) {
        Object obj = null;
        
        
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
        	LOG.debug("ImsInterceptor onFlushDirty copy " + e.getMessage());
        }
        catch(ClassNotFoundException cnfe) {
        	LOG.debug("ImsInterceptor onFlushDirty copy " + cnfe.getMessage());
        }
        return obj;
    }
	
}
