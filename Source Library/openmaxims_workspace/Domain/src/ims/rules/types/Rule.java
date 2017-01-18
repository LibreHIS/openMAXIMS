package ims.rules.types;

import ims.framework.utils.Date;
import ims.rules.interfaces.IRule;
import ims.rules.interfaces.IRuleAction;
import ims.rules.interfaces.IRuleCondition;

import java.util.ArrayList;
import java.util.List;

public final class Rule implements IRule
{
	private static final long serialVersionUID = 1L;
	 
	private String name = "";
	private String description = "";
	private int priority;
	private Date validFrom = null;
	private Date validTo = null;
	private RulesEngineEntity rootEntity = null;
	private List<IRuleCondition> conditions = new ArrayList<IRuleCondition>();
	private List<IRuleAction> actions = new ArrayList<IRuleAction>();
	private boolean userRule = false;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	public Date getValidFrom()
	{
		return validFrom;
	}
	public void setValidFrom(Date validFrom)
	{
		this.validFrom = validFrom;
	}
	public Date getValidTo()
	{
		return validTo;
	}
	public void setValidTo(Date validTo)
	{
		this.validTo = validTo;
	}
	public RulesEngineEntity getRootEntity()
	{
		return rootEntity;		
	}
	public void setRootEntity(RulesEngineEntity rootEntity)
	{
		this.rootEntity = rootEntity;
	}
	public List<IRuleCondition> getConditions()
	{
		return conditions;
	}
	public void setConditions(List<IRuleCondition> conditions)
	{
		this.conditions = conditions;
	}
	public List<IRuleAction> getActions()
	{
		return actions;
	}
	public void setActions(List<IRuleAction> actions)
	{
		this.actions = actions;
	}		
	public String[] validate()
	{
		return null;
	}
	public List<RulesEngineEntity> getAllEntities()
	{
		List<RulesEngineEntity> entitiesList = new ArrayList<RulesEngineEntity>();
		
		for(int x = 0; x < this.getConditions().size(); x++)
		{
			IRuleCondition condition = this.getConditions().get(x);
			
			if(condition instanceof RuleLogicalCondition)
			{
				getEntities(entitiesList, (RuleLogicalCondition)condition);
			}
			else if(condition instanceof RuleValueCondition)
			{
				getEntities(entitiesList, ((RuleValueCondition)condition).getEntry());
			}
		}
		
		return entitiesList;
	}	
	private void getEntities(List<RulesEngineEntity> entitiesList, RuleLogicalCondition condition) 
	{
		for(int x = 0; x < condition.getConditions().size(); x++)
		{
			IRuleCondition localCondition = condition.getConditions().get(x);
			
			if(localCondition instanceof RuleLogicalCondition)
			{
				getEntities(entitiesList, (RuleLogicalCondition)localCondition);
			}
			else if(localCondition instanceof RuleValueCondition)
			{
				getEntities(entitiesList, ((RuleValueCondition)localCondition).getEntry());
			}
		}
	}

	private void getEntities(List<RulesEngineEntity> entitiesList, RuleValueConditionEntry entry) 
	{
		if(entry != null)
		{
			if(entry.getEntity() != null)
			{
				if(!entitiesList.contains(entry.getEntity()))
				{
					entitiesList.add(entry.getEntity());
				}
			}
			if(entry.getChild() != null)
			{
				getEntities(entitiesList, entry.getChild());
			}
		}
	}	
	public void markAsUserRule() 
	{
		if(userRule)
			return;
		
		userRule = true;
		this.name = this.name + " (User Rule)";
	}
	public boolean isUserRule()
	{
		return userRule;
	}
}