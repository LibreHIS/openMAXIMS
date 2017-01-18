package ims.rules.types;

import ims.rules.interfaces.IRuleCondition;

import java.util.ArrayList;
import java.util.List;

public final class RuleLogicalCondition implements IRuleCondition
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private List<IRuleCondition> conditions = new ArrayList<IRuleCondition>();
	
	public enum RuleLogicalConditionType
	{
		ALLTRUE,
		ONETRUE,		
	}
	
	public boolean isOfType(RuleLogicalConditionType type)
	{
		if(type == RuleLogicalConditionType.ALLTRUE)
			return id == 1;
		else if(type == RuleLogicalConditionType.ONETRUE)
			return id == 3;
		return false;
	}
	public static RuleLogicalCondition getInstance(RuleLogicalConditionType type)
	{
		if(type == RuleLogicalConditionType.ALLTRUE)
			return new RuleLogicalCondition(1, "All are true"); 
		if(type == RuleLogicalConditionType.ONETRUE)
			return new RuleLogicalCondition(3, "At least one is true");
		
		return null;
	}
	public static List<RuleLogicalCondition> getAll()
	{
		List<RuleLogicalCondition> result = new ArrayList<RuleLogicalCondition>();
		
		result.add(getInstance(RuleLogicalConditionType.ALLTRUE));
		result.add(getInstance(RuleLogicalConditionType.ONETRUE));
		
		return result;
	}
	public static RuleLogicalCondition parse(int id)
	{
		List<RuleLogicalCondition> all = getAll();
		for(int x = 0; x < all.size(); x++)
		{
			if(all.get(x).id == id)
				return all.get(x);
		}
		
		return null;
	}
	
	private RuleLogicalCondition(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	public String toString()
	{
		return name;
	}
	public List<IRuleCondition> getConditions()
	{
		return conditions;
	}
	public void setConditions(List<IRuleCondition> conditions)
	{
		this.conditions = conditions;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof RuleLogicalCondition)
			return ((RuleLogicalCondition)obj).id == id;
		return false;
	}		
}
