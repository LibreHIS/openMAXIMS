package ims.rules.types;

import ims.framework.exceptions.CodingRuntimeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RulesEngineEntityMethod implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String javaName;
	private String description;
	private List<RulesEngineEntityMethodArgument> arguments = new ArrayList<RulesEngineEntityMethodArgument>();
	
	public RulesEngineEntityMethod(String id, String name, String javaName, String description)
	{
		this(id, name, javaName, description, null);
	}
	public RulesEngineEntityMethod(String id, String name, String javaName, String description, List<RulesEngineEntityMethodArgument> arguments)
	{
		setId(id);
		setName(name);
		setJavaName(javaName);
		setDescription(description);
		setArguments(arguments);		
	}
	
	public String getId()
	{
		return id;
	}
	private void setId(String id)
	{
		if(id == null || id.trim().length() == 0)
			throw new CodingRuntimeException("Invalid identifier for the rules engine entity method");
	
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		if(name == null)
			throw new CodingRuntimeException("Invalid rules engine entity method name");
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
	public List<RulesEngineEntityMethodArgument> getArguments()
	{
		return arguments;
	}
	public void setArguments(List<RulesEngineEntityMethodArgument> arguments)
	{
		if(arguments == null)
			this.arguments.clear();
		else
			this.arguments = arguments;
	}
	
	public RulesEngineEntityMethodArgument getArgumentById(String id)
	{
		if(arguments == null)
			return null;
		
		for(int x = 0; x < arguments.size(); x++)
		{
			if(arguments.get(x).getId().equals(id))
				return arguments.get(x);
		}
		
		return null;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof RulesEngineEntityMethod)
		{
			RulesEngineEntityMethod cast = (RulesEngineEntityMethod)obj;
			return id.equals(cast.id) && id.equals(cast.id);
		}
		return false;
	}
}
