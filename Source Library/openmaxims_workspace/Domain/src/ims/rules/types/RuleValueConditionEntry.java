package ims.rules.types;

import java.io.Serializable;

public class RuleValueConditionEntry implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private RulesEngineEntity entity;
	private RulesEngineField field;
	private RuleValueConditionEntry child;
	
	public void setEntity(RulesEngineEntity entity)
	{
		this.entity = entity;
	}
	public RulesEngineEntity getEntity()
	{
		return entity;
	}
	public void setField(RulesEngineField field)
	{
		this.field = field;
	}
	public RulesEngineField getField()
	{
		return field;
	}
	public RuleValueConditionEntry getChild()
	{
		return child;
	}
	public void setChild(RuleValueConditionEntry child)
	{
		this.child = child;
	}	
	public RuleValueConditionEntry()
	{
		this(null, null);
	}
	public RuleValueConditionEntry(RulesEngineEntity entity)
	{
		this(entity, null);
	}	
	public RuleValueConditionEntry(RulesEngineEntity entity, RulesEngineField field)
	{
		this.entity = entity;
		this.field = field;
	}
	public RulesEngineFieldType getComparedFieldType()
	{
		if(child != null)
			return child.getComparedFieldType();
		if(field != null)
			return field.getType();
				
		return null;
	}
	public RulesEngineField getComparedField()
	{
		if(child != null)
			return child.getComparedField();
		if(field != null)
			return field;
				
		return null;
	}
	public boolean containsCollectionComparison()
	{
		if(field != null && field.isCollection())
			return true;
		if(child != null)
			return child.containsCollectionComparison();		
				
		return false;
	}
	
	public RulesEngineField getCollectionField()
	{
		if(field != null && field.isCollection)
			return field;
		if(child != null)
			return child.getCollectionField();		
				
		return null;
	}
	public String getFullFieldGetterForCollection()
	{
		return getFullFieldGetterForCollection("");
	}
	private String getFullFieldGetterForCollection(String prepend)
	{
		if(field != null)
		{
			prepend += ".";
			prepend += field.getFieldGetter();
			
			if(field.isCollection())
				return prepend;
		}
		
		if(child != null)
			return child.getFullFieldGetterForCollection(prepend);
		
		return prepend;
	}
	public String getFullFieldGetter()
	{
		return getFullFieldGetter("");
	}
	private String getFullFieldGetter(String prepend)
	{
		if(field != null)
		{
			prepend += ".";
			prepend += field.getFieldGetter();					
		}
		
		if(child != null)
			return child.getFullFieldGetter(prepend);
		
		return prepend;
	}
}
