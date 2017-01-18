package ims.configuration;

import ims.domain.DomainObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ImportedObject extends DomainObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int CLASSID = 1111;

	private int externalId;
	private String externalSource;
	private String className;
	private int localId;
	private Date importDate;
	private Date updateDate;
	private DomainObject domainObject;
	
	public String getClassName()
	{
		return className;
	}
	public void setClassName(String className)
	{
		this.className = className;
	}
	public Date getImportDate()
	{
		return importDate;
	}
	public void setImportDate(Date importDate)
	{
		this.importDate = importDate;
	}
	public Date getUpdateDate()
	{
		return updateDate;
	}
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	public int getExternalId()
	{
		return externalId;
	}
	public void setExternalId(int externalId)
	{
		this.externalId = externalId;
	}
	public String getExternalSource()
	{
		return externalSource;
	}
	public void setExternalSource(String externalSource)
	{
		this.externalSource = externalSource;
	}
	public int getLocalId()
	{
		return localId;
	}
	public void setLocalId(int localId)
	{
		this.localId = localId;
	}
	public int getClassId()
	{
		return CLASSID;
	}
	public String getClassVersion()
	{
		return "" + CLASSID;
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
	public DomainObject getDomainObject()
	{
		return domainObject;
	}
	public void setDomainObject(DomainObject domainObject)
	{
		this.domainObject = domainObject;
	}
	public Class getRealDomainClass()
	{
		return ImportedObject.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}

}
