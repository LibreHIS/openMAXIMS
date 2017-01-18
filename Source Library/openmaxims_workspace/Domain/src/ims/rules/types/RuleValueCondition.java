package ims.rules.types;

import ims.rules.interfaces.IRuleCondition;

public final class RuleValueCondition implements IRuleCondition
{
	private static final long serialVersionUID = 1L;
	
	private RuleValueConditionEntry entry;
	private RuleValueComparator comparator;
	private String comparedValue;
	
	public RuleValueConditionEntry getEntry()
	{
		return entry;
	}
	public void setEntry(RuleValueConditionEntry entry)
	{
		this.entry = entry;
	}
	
	public RuleValueComparator getComparator()
	{
		return comparator;
	}
	public void setComparator(RuleValueComparator comparator)
	{
		this.comparator = comparator;
	}
	
	public String getComparedValue()
	{
		return comparedValue;
	}
	public void setComparedValue(String comparedValue)
	{
		this.comparedValue = comparedValue;
	}
	
	public RuleValueCondition()
	{		
	}	
}
