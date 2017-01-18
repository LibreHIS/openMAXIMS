package ims.configuration;

import ims.domain.DomainObject;

import java.io.Serializable;
import java.util.HashMap;

public class BusinessObjectReference extends DomainObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int CLASSID = 1111;

	private int businessObjectId;
	private int namespaceId;
	private String businessObjectName;
	private String tableName;
	private Boolean configuration;
	
	public boolean shouldCapQuery()
	{
		return false;
	}
	public int getClassId()
	{
		return CLASSID;
	}
	public String getClassVersion()
	{		return "" + CLASSID;
	}
	public String toAuditString()
	{
		return "";
	}
	public String toXMLString()
	{
		return "";
	}
	public String toXMLString(HashMap domMap)
	{
		return "";
	}
	public Class getRealDomainClass()
	{
		return BusinessObjectReference.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}
	public int getBusinessObjectId()
	{
		return businessObjectId;
	}
	public void setBusinessObjectId(int businessObjectId)
	{
		this.businessObjectId = businessObjectId;
	}
	public String getBusinessObjectName()
	{
		return businessObjectName;
	}
	public void setBusinessObjectName(String businessObjectName)
	{
		this.businessObjectName = businessObjectName;
	}
	public int getNamespaceId()
	{
		return namespaceId;
	}
	public void setNamespaceId(int namespaceId)
	{
		this.namespaceId = namespaceId;
	}
	public String getTableName()
	{
		return tableName;
	}
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
	public Boolean getConfiguration()
	{
		return configuration;
	}
	public void setConfiguration(Boolean configuration)
	{
		this.configuration = configuration;
	}
}
