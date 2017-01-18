/*
 * Created on May 28, 2004
 *
 */
package ims.vo;

import java.util.Map;

/**
 * Represents the ValueObjectBeans created from ValueObjects
 * Used in construction of recursive ValueObjects.
 *
 */
public class ValueObjectBeanMap 
{
	/**
	 * Map of ValueObjects and their respective ValueObjectBeans
	 */
	private Map<String, ValueObjectBean> valueObjectBeanMap;
	/**
	 * Map of ValueObjectBeans and their respective ValueObjects
	 */
	private Map<ValueObjectBean, ValueObject> valueObjectMap;

	/**
	 * 
	 */
	public ValueObjectBeanMap() 
	{
	}
	
	public void addValueObjectBean(ValueObject vo, ValueObjectBean valueObjectBean) 
	{
		ValueObjectBean voBean = getValueObjectBean(vo, true);
		if (voBean == null)
			this.valueObjectBeanMap.put(vo.getMapKey(), valueObjectBean);
	}
	
	public ValueObjectBean getValueObjectBean(ValueObject vo) 
	{
		return getValueObjectBean(vo, false);
	}
	
	private ValueObjectBean getValueObjectBean(ValueObject vo, boolean create) 
	{
		if ( null == this.valueObjectBeanMap ) 
		{
			if ( !create ) 
			{
				return null;
			}
			else 
			{
				this.valueObjectBeanMap = new java.util.HashMap<String, ValueObjectBean>();
			}
		}
		ValueObjectBean valueObjectBean = this.valueObjectBeanMap.get(vo.getMapKey());
		return valueObjectBean;
	}
	
	// Bean to Vo
	
	public void addValueObject(ValueObjectBean voBean, ValueObject valueObject) 
	{
		ValueObject vo = getValueObject(voBean, true);
		if (vo == null)
			this.valueObjectMap.put(voBean, valueObject);	
	}
	
	public ValueObject getValueObject(ValueObjectBean voBean) 
	{
		return getValueObject(voBean, false);
	}
	
	private ValueObject getValueObject(ValueObjectBean voBean, boolean create) 
	{
		if ( null == this.valueObjectMap ) 
		{
			if ( !create ) 
			{
				return null;
			}
			else 
			{
				this.valueObjectMap = new java.util.HashMap<ValueObjectBean, ValueObject>();
			}
		}
		
		ValueObject valueObject = this.valueObjectMap.get(voBean);
		return valueObject;
	}
}
