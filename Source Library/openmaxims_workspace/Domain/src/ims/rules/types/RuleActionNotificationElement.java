package ims.rules.types;

import java.io.Serializable;

public class RuleActionNotificationElement implements Serializable
{
	private static final long serialVersionUID = 1L;	
	
	private String text;
	private RuleActionNotificationElementType type;
	private RuleValueConditionEntry entityField;
	
	public RuleActionNotificationElement()
	{		
		this.type = RuleActionNotificationElementType.LINE_SEPARATOR;
	}
	public RuleActionNotificationElement(String text)
	{
		this.text = text;
		this.type = RuleActionNotificationElementType.TEXT;
	}
	public RuleActionNotificationElement(RuleValueConditionEntry entityField)
	{
		this.entityField = entityField;
		this.type = RuleActionNotificationElementType.ENTITY_FIELD;
	}
	
	public RuleActionNotificationElementType getType()
	{
		return type;
	}
	public String getText()
	{
		return text;
	}
	public RuleValueConditionEntry getEntityField()
	{
		return entityField;
	}
}
