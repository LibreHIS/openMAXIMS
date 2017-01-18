package ims.vo;

import ims.framework.interfaces.IReportObject;

public abstract class ValueObjectRef extends ValueObject implements IReportObject
{
	private static final long serialVersionUID = 1L;	
	
	public ValueObjectRef()
	{		
	}
	public ValueObjectRef(Integer id, int version)
	{
		this.id = id;
		if(id != null && version > 0)
			this.version = version;
	}
	public final Integer getBoId() 
	{
		return this.id;
	}
	public final int getBoVersion() 
	{
		return this.version;
	}
	public final Integer getDomainId()
	{
		return this.id;
	}
	public abstract String getBoClassName();
	
	public Object getFieldValueByFieldName(String fieldName)
	{
		return null;
	}
	
	protected Integer id;
	protected int version;
}
