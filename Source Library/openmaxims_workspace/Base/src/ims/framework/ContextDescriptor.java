package ims.framework;

public class ContextDescriptor
{
	private String key;
	private String type;
	private String value;
	private double size;
		
	public String getKey()
	{
		return key;
	}
	public String getType()
	{
		return type;
	}
	public String getValue()
	{
		return value;
	}
	public double getSize()
	{
		return size;
	}
	
	public ContextDescriptor(String key, String type)
	{
		this(key, type, "", 0);
	}
	public ContextDescriptor(String key, String type, String value, double size)
	{
		this.key = key;
		this.type = type;
		this.value = value;
		this.size = size;
	} 
}
