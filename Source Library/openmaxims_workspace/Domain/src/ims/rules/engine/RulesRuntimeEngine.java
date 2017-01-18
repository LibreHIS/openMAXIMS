package ims.rules.engine;

import ims.domain.DomainFactory;
import ims.domain.DomainObject;
import ims.domain.DomainSession;
import ims.domain.SystemLog;
import ims.domain.factory.UserFactory;
import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.NotificationPriority;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IEntityNotification;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.IUserProvider;
import ims.notifications.NotificationEngine;
import ims.rules.exceptions.RulesEngineRuntimeException;

import java.io.Serializable;
import java.util.HashMap;

public class RulesRuntimeEngine implements Serializable
{
	private HashMap<String, Object> values = new HashMap<String, Object>();
	private static final long serialVersionUID = 123452345234L;
	private transient DomainSession domainSession = null;
	private transient SystemLog systemLog = null;	
	private Integer entityId;
	private transient RulesEngine rulesEngine;
	private transient DomainFactory domainFactory;
	private java.util.HashMap prevState;
	private int lastRuleResult;
	private String lastRuleName;
	private String pathwayEntitySpecificRuleName;
	
	public RulesRuntimeEngine(RulesEngine rulesEngine, DomainFactory domainFactory, DomainSession domainSession, Integer entityId)
	{
		this(rulesEngine, domainFactory, domainSession, new HashMap<String, Object>(), entityId);
	}
	public RulesRuntimeEngine(RulesEngine rulesEngine, DomainFactory domainFactory, DomainSession domainSession, Integer entityId, HashMap prevState)
	{
		this(rulesEngine, domainFactory, domainSession, new HashMap<String, Object>(), entityId, prevState);
	}
	public RulesRuntimeEngine(RulesEngine rulesEngine, DomainFactory domainFactory, DomainSession domainSession, HashMap<String, Object> values, Integer entityId) 
	{		
		this.rulesEngine = rulesEngine;
		this.domainFactory = domainFactory;
		this.domainSession = domainSession;
		this.values = values;
		this.entityId = entityId;
	}

	public RulesRuntimeEngine(RulesEngine rulesEngine, DomainFactory domainFactory, DomainSession domainSession, HashMap<String, Object> values, Integer entityId, HashMap prevState) 
	{		
		this.rulesEngine = rulesEngine;
		this.domainFactory = domainFactory;
		this.domainSession = domainSession;
		this.values = values;
		this.entityId = entityId;
		this.prevState = prevState;
	}
	
	public void fireAllRules(Object entity)
	{
		HashMap<String, Serializable> global = new HashMap<String, Serializable>();
		
		Integer thisEntityId = null;
		if(entity instanceof DomainObject)
		{
			thisEntityId = ((DomainObject)entity).getId();
		}
		global.put("engine", new RulesRuntimeEngine(rulesEngine, domainFactory, domainSession, values, thisEntityId, prevState));
		global.put("factory", domainFactory);
		rulesEngine.fireAllRules(global, new Object[] { entity });
	}
	public Integer getRuntimeEntityId()
	{
		return entityId;
	}
	public Object getValue(String key)
	{
		return values.get(key);
	}
	public void setValue(String key, Object value)
	{
		values.put(key, value);
	}
	public void clearValues()
	{
		values.clear();
	}
	public ISystemLog createSystemLogEntry(SystemLogLevel level, String message) 
	{
		if(systemLog == null) 
			systemLog = new SystemLog(domainSession);
		
		return systemLog.createSystemLogEntry(SystemLogType.RULES_ENGINE, level, message);
	}
	public void createNotification(int userId, NotificationPriority priority, String message, String source)
	{
		createNotification(userId, priority, message, source, null, null);
	}			
	public void createNotification(int[] usersId, NotificationPriority priority, String message, String source)
	{
		createNotification(usersId, priority, message, source, null, null);
	}
	public void createNotification(int userId, NotificationPriority priority, String message, String source, String entityName, Integer entityId)
	{
		createNotification(new int[] { userId }, priority, message, source, entityName, entityId);
	}	
	public void createNotification(int[] usersId, NotificationPriority priority, String message, String source, String entityName, Integer entityId)
	{
		try
		{
			IUserProvider userProvider = new UserFactory(domainSession).getUserProvider();
			for(int x = 0; x < usersId.length; x++)
			{
				NotificationEngine.getInstance().addNotification(userProvider.getAppUser(usersId[x]), priority, message, source, entityName, entityId, NotificationDelivery.ALL);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void createNotification(IEntityNotification entityNotification, NotificationPriority priority, String message, String source, String entityName, Integer entityId)
	{
		if(entityNotification != null)
		{
			createNotification(entityNotification.getNotificationUserIds(), priority, message, source, entityName, entityId);
		}
	}	
	public void throwDataValidationException(String message)
	{
		throw new RulesEngineRuntimeException(message);
	}
	public void println(String message)
	{
		System.out.println(message);
	}
	
	public int getLastRuleResult() {
		return lastRuleResult;
	}

	public void setLastRuleResult(int lastRuleResult) {
		this.lastRuleResult = lastRuleResult;
	}
	public String getLastRuleName() {
		return lastRuleName;
	}
	public void setLastRuleName(String lastRuleName) {
		this.lastRuleName = lastRuleName;
	}
	public String getPathwayEntitySpecificRuleName() {
		return pathwayEntitySpecificRuleName;
	}
	public void setPathwayEntitySpecificRuleName(
			String pathwayEntitySpecificRuleName) {
		this.pathwayEntitySpecificRuleName = pathwayEntitySpecificRuleName;
	}
}
