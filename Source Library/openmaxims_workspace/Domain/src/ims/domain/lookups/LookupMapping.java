/*
 * Created on May 26, 2004
 *
 */
package ims.domain.lookups;

import ims.configuration.EnvironmentConfig;

import java.io.Serializable;

/**
 * Represents the mapping of a LookupInstance to an external system. This class is always obtained from a LookupInstance Within the set of these mappings for a particular LookupInstance, each will be unique by systemName + extCode.
 * 
 * @author gcoghlan
 * 
 */
public class LookupMapping implements Serializable
{
	/** The LookupInstance this maps */
	private static final long	serialVersionUID	= 1L;
	private LookupInstance		lookupInstance;
	private String				extSystem;
	private String				extCode;					// Length 30

	public LookupMapping()
	{
	}

	LookupMapping(LookupInstance lookupInstance, String extSystem, String extCode)
	{
		this.lookupInstance = lookupInstance;
		this.extSystem = extSystem;
		this.extCode = extCode;
	}

	public boolean equals(Object obj)
	{
		if ((null == obj) || !(obj instanceof LookupMapping))
		{
			return false;
		}		
		
		LookupMapping mapping = (LookupMapping) obj;
		int specimenTypeId = 1161029;
		int specimenSiteId = 1161028;
		int thisTypeId = this.getLookupInstance().getType().getId();
		int thatTypeId = mapping.getLookupInstance().getType().getId();
		boolean isSpecTypeInvolved = (thisTypeId == specimenTypeId || thisTypeId == specimenSiteId || thatTypeId == specimenSiteId || thatTypeId == specimenTypeId); 
		
		if (EnvironmentConfig.getCaseSensitiveDatabase())
		{
			if (isSpecTypeInvolved)
				return (mapping.getExtCode().equals(getExtCode())) && (mapping.getExtSystem().equals(getExtSystem()));	
			else
				return (mapping.getLookupInstance().getType().equals(getLookupInstance().getType()) && mapping.getExtCode().equals(getExtCode())) && (mapping.getExtSystem().equals(getExtSystem()));				
		}
		else
		{
			if (isSpecTypeInvolved)
				return (mapping.getExtCode().equalsIgnoreCase(getExtCode())) && (mapping.getExtSystem().equalsIgnoreCase(getExtSystem()));
			else				
				return (mapping.getLookupInstance().getType().equals(getLookupInstance().getType()) && mapping.getExtCode().equalsIgnoreCase(getExtCode())) && (mapping.getExtSystem().equalsIgnoreCase(getExtSystem()));
		}
	}

	/**
	 * The equals test is true for matching extCode && systemName so the hashCode is based on extCode && systemName
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		if (EnvironmentConfig.getCaseSensitiveDatabase())
		{
			return this.extSystem.hashCode() * this.extCode.hashCode();
		}
		else
		{
			return this.extSystem.toLowerCase().hashCode() * this.extCode.toLowerCase().hashCode();
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(" lookupInstance.id : ").append(this.lookupInstance.getId());
		buffer.append(" systemName : ").append(this.extSystem);
		buffer.append(" extCode : ").append(this.extCode);
		return buffer.toString();
	}

	public String getExtCode()
	{
		return this.extCode;
	}

	public LookupInstance getLookupInstance()
	{
		return this.lookupInstance;
	}

	public String getExtSystem()
	{
		return this.extSystem;
	}

	/**
	 * Declared to conform with hibernate mapping. <parent> element in composite-element does not allow access to be specified.
	 * 
	 * @param lookupInstance
	 */
	protected void setLookupInstance(LookupInstance lookupInstance)
	{
		this.lookupInstance = lookupInstance;
	}

	public String toXMLString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<mapping>");
		sb.append("<extSystem>");
		sb.append(this.getExtSystem());
		sb.append("</extSystem>");
		sb.append("<extCode>");
		sb.append(this.getExtCode());
		sb.append("</extCode>");
		sb.append("</mapping>");
		return sb.toString();
	}
}
