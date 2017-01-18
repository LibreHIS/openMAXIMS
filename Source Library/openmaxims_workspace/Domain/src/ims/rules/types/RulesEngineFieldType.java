package ims.rules.types;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class RulesEngineFieldType implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private RuleValueComparator[] comparators;
	boolean complexType = false;

	public static RulesEngineFieldType INTEGER = new RulesEngineFieldType(1, "Integer", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.GREATERTHAN, RuleValueComparator.GREATEREQUALSTHAN, RuleValueComparator.LESSTHAN, RuleValueComparator.LESSEQUALSTHAN }, false);
	public static RulesEngineFieldType STRING = new RulesEngineFieldType(2, "String", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.CONTAINS }, true);
	public static RulesEngineFieldType DECIMAL = new RulesEngineFieldType(3, "Decimal", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.GREATERTHAN, RuleValueComparator.GREATEREQUALSTHAN, RuleValueComparator.LESSTHAN, RuleValueComparator.LESSEQUALSTHAN }, false);
	public static RulesEngineFieldType DATE = new RulesEngineFieldType(4, "Date", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.GREATERTHAN, RuleValueComparator.GREATEREQUALSTHAN, RuleValueComparator.LESSTHAN, RuleValueComparator.LESSEQUALSTHAN }, true);	
	public static RulesEngineFieldType LOOKUP = new RulesEngineFieldType(5, "Lookup", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS }, false);
	public static RulesEngineFieldType ENTITY = new RulesEngineFieldType(6, "Entity", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY }, true);
	public static RulesEngineFieldType PARTIALDATE = new RulesEngineFieldType(7, "Partial Date", new RuleValueComparator[] {  RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.GREATERTHAN, RuleValueComparator.GREATEREQUALSTHAN, RuleValueComparator.LESSTHAN, RuleValueComparator.LESSEQUALSTHAN  }, true);
	public static RulesEngineFieldType BOOLEAN = new RulesEngineFieldType(8, "Boolean", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS}, false);
	public static RulesEngineFieldType TIME = new RulesEngineFieldType(9, "Time", new RuleValueComparator[] {  RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS }, true);
	public static RulesEngineFieldType DATETIME = new RulesEngineFieldType(10, "Date and Time", new RuleValueComparator[] {  RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS, RuleValueComparator.GREATERTHAN, RuleValueComparator.GREATEREQUALSTHAN, RuleValueComparator.LESSTHAN, RuleValueComparator.LESSEQUALSTHAN  }, true);	
	public static RulesEngineFieldType ENTITY_CODE = new RulesEngineFieldType(11, "Entity Code", new RuleValueComparator[] { RuleValueComparator.EMPTY, RuleValueComparator.NOTEMPTY, RuleValueComparator.EQUALS, RuleValueComparator.NOTEQUALS }, false);
	
	public static List<RulesEngineFieldType> getInstances()
	{
		List<RulesEngineFieldType> result = new ArrayList<RulesEngineFieldType>();
		
		result.add(INTEGER);
		result.add(STRING);
		result.add(DECIMAL);
		result.add(DATE);
		result.add(LOOKUP);
		result.add(ENTITY);
		result.add(PARTIALDATE);
		result.add(BOOLEAN);
		result.add(TIME);
		result.add(DATETIME);
		result.add(ENTITY_CODE);
		
		return result;
	}
	public static RulesEngineFieldType parse(int id)
	{
		List<RulesEngineFieldType> instances = getInstances();
		
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
	public RuleValueComparator[] getComparators()
	{
		return comparators;
	}
	public boolean isComplexType()
	{
		return complexType;
	}
	
	private RulesEngineFieldType(int id, String name, RuleValueComparator[] comparators, boolean complexType)
	{
		this.id = id;
		this.name = name;
		this.comparators = comparators;
		this.complexType = complexType;
	}
	public String toString()
	{
		return name;
	}
}
