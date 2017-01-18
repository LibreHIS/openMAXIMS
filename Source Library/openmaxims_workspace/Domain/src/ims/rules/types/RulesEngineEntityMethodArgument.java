package ims.rules.types;

import java.io.Serializable;

import ims.framework.exceptions.CodingRuntimeException;

public class RulesEngineEntityMethodArgument implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String javaName;
	private String description;
	private RulesEngineMethodArgumentType type;	
	
	public RulesEngineEntityMethodArgument(String id, String name, String javaName, String description, RulesEngineMethodArgumentType type)
	{
		setId(id);
		setName(name);
		setJavaName(javaName);
		setDescription(description);
		setType(type);
	}
	
	public String getId()
	{
		return id;
	}
	private void setId(String id)
	{
		if(id == null || id.trim().length() == 0)
			throw new CodingRuntimeException("Invalid identifier for the rules engine entity method argument");
	
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		if(name == null)
			throw new CodingRuntimeException("Invalid rules engine entity method name argument");
		else
			this.name = name;
	}
	public String getJavaName()
	{
		return javaName;
	}
	public void setJavaName(String javaName)
	{
		if(javaName == null)
			throw new CodingRuntimeException("Invalid rules engine entity java method name");
		else
			this.javaName = javaName;
	}
	public String getDescription()
	{
		return description;
	}		
	public void setDescription(String description)
	{
		if(description == null)
			this.description = "";
		else
			this.description = description;
	}
	public RulesEngineMethodArgumentType getType()
	{
		return type;
	}
	public void setType(RulesEngineMethodArgumentType type) 
	{
		this.type = type;	
	}
		
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof RulesEngineEntityMethodArgument)
		{
			RulesEngineEntityMethodArgument cast = (RulesEngineEntityMethodArgument)obj;
			return id.equals(cast.id) && id.equals(cast.id);
		}
		return false;
	}
}
