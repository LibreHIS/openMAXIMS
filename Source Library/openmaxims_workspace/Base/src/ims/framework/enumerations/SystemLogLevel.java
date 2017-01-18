package ims.framework.enumerations;

import java.io.Serializable;

public class SystemLogLevel implements Serializable
{
	private static final long serialVersionUID = 1L;
	 
	private int id;
	private String text;
	
	public static SystemLogLevel INFORMATION = new SystemLogLevel(1, "Information");
	public static SystemLogLevel WARNING = new SystemLogLevel(2, "Warning");
	public static SystemLogLevel ERROR = new SystemLogLevel(3, "Error");
	public static SystemLogLevel FATALERROR = new SystemLogLevel(4, "Fatal Error");
			
	public static SystemLogLevel[] ALL = new SystemLogLevel[] { INFORMATION, WARNING, ERROR, FATALERROR }; 
	
	private SystemLogLevel(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	public int getId()
	{
		return id;
	}
	public String getText()
	{
		return text;
	}
	public String toString()
	{
		return text;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof SystemLogLevel)
			return ((SystemLogLevel)obj).id == id;
		return false;
	}
	public static SystemLogLevel getInstance(int id)
	{
		for(int x = 0; x < ALL.length; x++)
		{
			if(ALL[x].id == id)
				return ALL[x];
		}
		
		return null;
	}
}
