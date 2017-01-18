package ims.vo;

import java.io.Serializable;

import ims.configuration.EnvironmentConfig;

public class LookupMappingVo implements ims.vo.ImsCloneable, Comparable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String extSystem;
	private String extCode;

	public LookupMappingVo()
	{
		
	}
	
	public LookupMappingVo(String  extsystem, String  extcode)
	{
		this.extCode = extcode;
		this.extSystem = extsystem;
	}

	public String getExtCode() {
		return extCode;
	}

	public String getExtSystem() {
		return extSystem;
	}

	public boolean equals(Object obj)
	{
		boolean caseSense = EnvironmentConfig.getCaseSensitiveDatabase();
		
		if(obj == null)
			return false;
		if(!(obj instanceof LookupMappingVo))
			return false;
		LookupMappingVo compareObj = (LookupMappingVo)obj;
		if(this.getExtSystem() == null && compareObj.getExtSystem() != null)
			return false;
		if(this.getExtSystem() != null && compareObj.getExtSystem() == null)
			return false;
		if(this.getExtSystem() != null && compareObj.getExtSystem() != null)
		{
			if (caseSense)
			{
				if(!this.getExtSystem().equals(compareObj.getExtSystem()))
					return false;				
			}
			else
			{
				if(!this.getExtSystem().equalsIgnoreCase(compareObj.getExtSystem()))
					return false;								
			}			
		}
		if(this.getExtCode() == null && compareObj.getExtCode() != null)
			return false;
		if(this.getExtCode() != null && compareObj.getExtCode() == null)
			return false;
		if(this.getExtCode() != null && compareObj.getExtCode() != null)
		{
			if (caseSense)
			{
				return this.getExtCode().equals(compareObj.getExtCode());
			}
			
			return this.getExtCode().equalsIgnoreCase(compareObj.getExtCode());			
		}
		return super.equals(obj);
	}

	public int hashCode() 
	{
		if (EnvironmentConfig.getCaseSensitiveDatabase())
		{
			return this.extSystem.hashCode() * this.extCode.hashCode();					
		}
		
		return this.extSystem.toLowerCase().hashCode() * this.extCode.toLowerCase().hashCode();				
	}

	public Object clone()
	{
		LookupMappingVo clone = new LookupMappingVo();		
		clone.extSystem = this.extSystem;
		clone.extCode = this.extCode;
		return clone;
	}

	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(LookupMappingVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A LookupMappingVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		LookupMappingVo compareObj = (LookupMappingVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getExtSystem() != null && compareObj.getExtSystem() != null)
			{
				if(caseInsensitive)
					retVal = this.getExtSystem().toLowerCase().compareTo(compareObj.getExtSystem().toLowerCase());
				else
					retVal = this.getExtSystem().compareTo(compareObj.getExtSystem());
			}
		}
		return retVal;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public void setExtSystem(String extSystem) {
		this.extSystem = extSystem;
	}
}
