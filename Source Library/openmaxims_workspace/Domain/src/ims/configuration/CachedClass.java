package ims.configuration;

import ims.domain.DomainObject;
import ims.domain.lookups.LookupInstance;

import java.io.Serializable;
import java.util.HashMap;

public class CachedClass extends DomainObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String boClassName;
	private LookupInstance cacheUsage;
	private boolean isCollection = false;

	public CachedClass()
	{
	}
	public CachedClass(String className)
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
	public LookupInstance getCacheUsage()
	{
		return cacheUsage;
	}
	public void setCacheUsage(LookupInstance cacheUsage)
	{
		this.cacheUsage = cacheUsage;
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
		return CachedClass.class;
	}
	public boolean isCollection()
	{
		return isCollection;
	}
	public void setCollection(boolean isCollection)
	{
		this.isCollection = isCollection;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}
}
