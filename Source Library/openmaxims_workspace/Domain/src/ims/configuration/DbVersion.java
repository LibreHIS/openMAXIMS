package ims.configuration;

import ims.domain.DomainObject;

import java.io.Serializable;
import java.util.HashMap;

public class DbVersion extends DomainObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String schemaVersionLabel;
	private java.util.Date appliedDateTime;	
	
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

	public Class getRealDomainClass()
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

	public java.util.Date getAppliedDateTime()
	{
		return appliedDateTime;
	}

	public void setAppliedDateTime(java.util.Date appliedDateTime)
	{
		this.appliedDateTime = appliedDateTime;
	}

	public String getSchemaVersionLabel()
	{
		return schemaVersionLabel;
	}

	public void setSchemaVersionLabel(String schemaVersionLabel)
	{
		this.schemaVersionLabel = schemaVersionLabel;
	}

}
