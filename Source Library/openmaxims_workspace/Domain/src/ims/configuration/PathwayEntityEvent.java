package ims.configuration;

import ims.domain.DomainObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * WDEV-17644
 * BusinessObjectEvent - this object will hold the mapping between a business
 * object and a pathways event that is to be instantiated under certain criteria
 * It is defined in the framework as the ImsInterceptor will have to access it.
 * The actual events are Pathways.Event and Pathways.PatientEvent - but will have to be 
 * stored as type DomainObjects at this level.
 * @author bworwood
 *
 */
public class PathwayEntityEvent extends DomainObject implements Serializable
{
    private static final long serialVersionUID = 1L;
	public static final int CLASSID = 1204120002;
	
	private String entityName;  // The name of the entity (DomainObject) to check in the interceptor
	private Integer event;  // The event type to be instantiated for this Entity/Business object
	private boolean active;  // Could be configured but no longer required
	private String scheduledDateField; // Name of  the field in the entity that will be the scheduled date field
	private String rule;  // Name of the rule to execute prior to instantiating the event
	private String description; // Description that can be output as part of the event
	private String targetMethod; // Name of BO target method to invoke
	
	public PathwayEntityEvent (Integer id, int ver)
    {
    	super(id, ver);
    }
    public PathwayEntityEvent ()
    {
    	super();
    }
    public PathwayEntityEvent (Integer id, int ver, Boolean includeRecord)
    {
    	super(id, ver, includeRecord);
    }
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Integer getEvent() {
		return event;
	}
	public void setEvent(Integer event) {
		this.event = event;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
		public String getScheduledDateField() {
		return scheduledDateField;
	}
	public void setScheduledDateField(String scheduledDateField) {
		this.scheduledDateField = scheduledDateField;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getClassId()
	{
		return CLASSID;
	}
	public String getClassVersion()
	{		return "" + CLASSID;
	}
	public String toAuditString()
	{
		return "";
	}
	public String toXMLString()
	{
		return "";
	}
	public String toXMLString(HashMap domMap)
	{
		return "";
	}
	public Class getRealDomainClass()
	{
		return PathwayEntityEvent.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}
	public void setTargetMethod(String targetMethod) 
	{
		this.targetMethod = targetMethod;
	}
	public String getTargetMethod() 
	{
		return targetMethod;
	}

}
