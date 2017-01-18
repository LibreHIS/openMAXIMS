package ims.configuration;

import ims.framework.interfaces.IGenericIdentifier;

public class GenericIdentifier implements IGenericIdentifier
{

	private String className;
	private int id;
	
	public GenericIdentifier(String classname, int id)
	{
		this.className = classname;
		this.id = id;
	}

	public String getClassName()
	{
		return className;
	}

	public int getId()
	{
		return id;
	}

}
