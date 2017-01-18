package ims.rules.types;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class RuleActionNotificationElementType implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	public static RuleActionNotificationElementType TEXT = new RuleActionNotificationElementType(1, "Text");
	public static RuleActionNotificationElementType ENTITY_FIELD = new RuleActionNotificationElementType(2, "Entity Field");
	public static RuleActionNotificationElementType LINE_SEPARATOR = new RuleActionNotificationElementType(3, "Line Separator");
		
	public static List<RuleActionNotificationElementType> getInstances()
	{
		List<RuleActionNotificationElementType> result = new ArrayList<RuleActionNotificationElementType>();
		
		result.add(TEXT);
		result.add(ENTITY_FIELD);		
		result.add(LINE_SEPARATOR);
		
		return result;
	}
	public static RuleActionNotificationElementType parse(int id)
	{
		List<RuleActionNotificationElementType> instances = getInstances();
		
		for(int x = 0; x < instances.size(); x++)
		{
			if(instances.get(x).id == id)
				return instances.get(x);
		}
		
		return null;
	}
	
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	
	private RuleActionNotificationElementType(int id, String name)
	{
		this.id = id;
		this.name = name;		
	}
	public String toString()
	{
		return name;
	}
}
