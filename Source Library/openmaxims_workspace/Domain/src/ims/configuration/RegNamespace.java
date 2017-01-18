package ims.configuration;

import java.io.Serializable;

public class RegNamespace implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int namespaceID;
	private String name;
	private String map;
	private boolean	isRIE;
	private int		version;

	public int getNamespaceID()
	{
		return this.namespaceID;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getMap()
	{
		return this.map;
	}
	public void setMap(String map)
	{
		this.map = map;
	}
	
	public void setNamespaceID(int namespaceID)
	{
		this.namespaceID = namespaceID;
	}
	public boolean isRIE()
	{
		return isRIE;
	}
	public void setRIE(boolean isRIE)
	{
		this.isRIE = isRIE;
	}
	public int getVersion()
	{
		return version;
	}
	public void setVersion(int version)
	{
		this.version = version;
	}
}
