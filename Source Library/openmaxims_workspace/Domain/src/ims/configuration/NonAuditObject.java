package ims.configuration;

import ims.domain.DomainObject;

import java.io.Serializable;
import java.util.HashMap;

public class NonAuditObject extends DomainObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String boClassName;

	public NonAuditObject()
	{
	}
	public NonAuditObject(String className)
	{
		this.boClassName = className;
	}
	public String getBoClassName()
	{
		return boClassName;
	}
	public void setBoClassName(String boClassName)
	{
		this.boClassName = boClassName;
	}
	public int getClassId()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	public String getClassVersion()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String toAuditString()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String toXMLString(HashMap domMap)
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String toXMLString()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public Class getRealDomainClass()
	{
		return NonAuditObject.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}
}
