package ims.configuration;

import java.io.Serializable;

public final class ConfigType implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	
	public static final ConfigType HIB = new ConfigType(0);
	public static final ConfigType XML = new ConfigType(1);
	public static final ConfigType DTO = new ConfigType(2);
	
	private ConfigType(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}	
}
