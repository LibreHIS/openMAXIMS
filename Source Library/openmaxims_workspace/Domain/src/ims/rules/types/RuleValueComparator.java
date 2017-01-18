package ims.rules.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class RuleValueComparator implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;	
	
	public static RuleValueComparator EQUALS = new RuleValueComparator(1, "Equals");
	public static RuleValueComparator NOTEQUALS = new RuleValueComparator(2, "Not equals");
	public static RuleValueComparator GREATERTHAN = new RuleValueComparator(3, "Greater than");
	public static RuleValueComparator GREATEREQUALSTHAN = new RuleValueComparator(4, "Greater or equals than");
	public static RuleValueComparator LESSTHAN = new RuleValueComparator(5, "Less than");
	public static RuleValueComparator LESSEQUALSTHAN = new RuleValueComparator(6, "Less or equals than");
	public static RuleValueComparator CONTAINS = new RuleValueComparator(7, "Contains");
	public static RuleValueComparator EMPTY = new RuleValueComparator(8, "Empty");
	public static RuleValueComparator NOTEMPTY = new RuleValueComparator(9, "Not Empty");
	
	public static List<RuleValueComparator> getAll()
	{
		List<RuleValueComparator> result = new ArrayList<RuleValueComparator>();
		
		result.add(EQUALS);
		result.add(NOTEQUALS);
		result.add(GREATERTHAN);
		result.add(GREATEREQUALSTHAN);
		result.add(LESSTHAN);
		result.add(LESSEQUALSTHAN);
		result.add(CONTAINS);
		result.add(EMPTY);
		result.add(NOTEMPTY);
		
		return result;
	}
	public static RuleValueComparator parse(int id)
	{
		List<RuleValueComparator> all = getAll();
		
		for(int x = 0; x < all.size(); x++)
		{
			if(all.get(x).id == id)
				return all.get(x);
		}
		
		return null;
	}
	
	private RuleValueComparator(int id, String name)
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
	public boolean equals(Object obj)
	{
		if(obj instanceof RuleValueComparator)
			return ((RuleValueComparator)obj).id == id;
		return false;
	}	
}
