package ims.rules.types;


import java.io.Serializable;
import java.util.List;

public abstract class RulesEngineEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String name;
	protected String description;	
	protected String businessObjectName;
	protected List<RulesEngineField> fields;
	protected List<RulesEngineEntityMethod> actions;
	protected boolean isPublic = false;
	protected boolean supportsNotifications = false;
		
	public String getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getRuleEntityName()
	{
		return "entity" + id;
	}
	public String getDescription()
	{
		return description;
	}
	public String getBusinessObjectName()
	{
		return businessObjectName;
	}
	public List<RulesEngineField> getFields()
	{		
		return fields;
	}	
	public boolean isPublic()
	{
		return isPublic;
	}
	public boolean supportsNotifications()
	{
		return supportsNotifications;
	}
	public RulesEngineField getFieldById(String id)
	{
		if(fields == null)
			return null;
		
		for(int x = 0; x < fields.size(); x++)
		{
			if(fields.get(x).id.equals(id))
				return fields.get(x);
		}
		
		return null;
	}
	public List<RulesEngineEntityMethod> getActions()
	{
		return actions;
	}
	public RulesEngineEntityMethod getActionById(String id)
	{
		if(actions == null)
			return null;
		
		for(int x = 0; x < actions.size(); x++)
		{
			if(actions.get(x).getId().equals(id))
				return actions.get(x);
		}
		
		return null;
	}	
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof RulesEngineEntity)
			return id.equals(((RulesEngineEntity)obj).id);
		return false;
	}
}
