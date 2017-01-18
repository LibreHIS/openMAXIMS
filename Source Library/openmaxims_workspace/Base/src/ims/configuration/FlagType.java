/*
 * Created on 09-Sep-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.configuration;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FlagType 
{
	//public static final FlagType ENUM = new FlagType(0);
	public static final FlagType STRING = new FlagType(1);
	public static final FlagType INT = new FlagType(2);
	public static final FlagType DECIMAL = new FlagType(3);
	public static final FlagType BOOL = new FlagType(4);
	public static final FlagType COLOUR = new FlagType(5);
	public static final FlagType DATE = new FlagType(6);
	public static final FlagType TIME = new FlagType(7);
	public static final FlagType DATETIME = new FlagType(8);
	public static final FlagType FORM = new FlagType(9);
	public static final FlagType IMAGE = new FlagType(10);
	public static final FlagType LKUP_INST = new FlagType(11);
	public static final FlagType LKUP_TYPE = new FlagType(12);
	
	private static String[] names = new String[]{"ENUM","STRING","INT","DECIMAL","BOOL","COLOUR","DATE","TIME","DATETIME","FORM","IMAGE","LKUP_INST","LKUP_TYPE"};
	public final int id;
		
	public FlagType(int type)
	{
		this.id = type;
	}
	
	public String toString()
	{
		return names[this.id];
	}
	
	public boolean equals(Object o)
	{
		if (o == null) return false;
		if (!(o instanceof FlagType)) return false;
		FlagType otype = (FlagType)o;
		return (otype.id == this.id);
	}
	
	public int hashCode()
	{
		return FlagType.names[this.id].hashCode();
	}
	
	public static FlagType getFlagType(String name)
	{
		if (name == null) return FlagType.STRING;
		for (int i = 0; i < names.length; i++)
		{
			if (names[i].equalsIgnoreCase(name))
			{
				return new FlagType(i);
			}
		}
		return null;
	}
}
