package ims.rules.types;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class RulesEngineMethodArgumentType implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public static RulesEngineMethodArgumentType INTEGER = new RulesEngineMethodArgumentType(1, "Integer");
	public static RulesEngineMethodArgumentType STRING = new RulesEngineMethodArgumentType(2, "String");
	public static RulesEngineMethodArgumentType DECIMAL = new RulesEngineMethodArgumentType(3, "Decimal");
	public static RulesEngineMethodArgumentType DATE = new RulesEngineMethodArgumentType(4, "Date");	
	public static RulesEngineMethodArgumentType PARTIALDATE = new RulesEngineMethodArgumentType(7, "Partial Date");
	public static RulesEngineMethodArgumentType BOOLEAN = new RulesEngineMethodArgumentType(8, "Boolean");
	public static RulesEngineMethodArgumentType TIME = new RulesEngineMethodArgumentType(9, "Time");
	public static RulesEngineMethodArgumentType DATETIME = new RulesEngineMethodArgumentType(10, "Date and Time");		
	
	public static List<RulesEngineMethodArgumentType> getInstances()
	{
		List<RulesEngineMethodArgumentType> result = new ArrayList<RulesEngineMethodArgumentType>();
		
		result.add(INTEGER);
		result.add(STRING);
		result.add(DECIMAL);
		result.add(DATE);
		result.add(PARTIALDATE);
		result.add(BOOLEAN);
		result.add(TIME);
		result.add(DATETIME);		
		
		return result;
	}
	public static RulesEngineMethodArgumentType parse(int id)
	{
		List<RulesEngineMethodArgumentType> instances = getInstances();
		
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
	
	private RulesEngineMethodArgumentType(int id, String name)
	{
		this.id = id;
		this.name = name;	
	}
	public String toString()
	{
		return name;
	}
}
