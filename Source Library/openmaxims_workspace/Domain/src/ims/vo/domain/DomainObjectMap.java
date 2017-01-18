/*
 * Created on May 28, 2004
 *
 */
package ims.vo.domain;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ims.domain.DomainObject;

/**
 * Represents the ValueObjects created from DomainObjects
 * Used in construction of recursive ValueObjects.
 * A DomainObject can be used to create more than one kind (Class)
 * of ValueObject.
 * 
 * @author gcoghlan
 *
 */
public class DomainObjectMap {
	/** Map of DomainObject (key) to Map (value).
	 * The value is a Map of Class (key) to Object (value).
	 * The Class identifies the type of ValueObject and the
	 * Object is the existing instance of that type created for the DomainObject.
	 */
	private Map domainObjectMap;

	/**
	 * 
	 */
	public DomainObjectMap() 
	{
		super();
	}
	
	public Object getValueObject(DomainObject domainObject, Class valueObjectClass) 
	{
		Map valueObjects = getValueObjects(domainObject);
		if ( null != valueObjects) 
		{
			Object o = valueObjects.get(valueObjectClass);
			if (o == null)
			{
				Set keys = valueObjects.keySet();
				Iterator iter = keys.iterator();
				while (iter.hasNext())
				{
					Class c = (Class)iter.next();
					if (valueObjectClass.equals(c)) 
						return valueObjects.get(c);
				}
			}
			return o;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void addValueObject(DomainObject domainObject, Object valueObject) 
	{
		Map valueObjects = getValueObjects(domainObject, true);
		Class valueObjectClass = valueObject.getClass();
		valueObjects.put(valueObjectClass, valueObject);	
	}
	
	private Map getValueObjects(DomainObject domainObject) 
	{
		return getValueObjects(domainObject, false);
	}
	
	@SuppressWarnings("unchecked")
	private Map getValueObjects(DomainObject domainObject, boolean create) 
	{
		if ( null == this.domainObjectMap ) 
		{
			if ( !create ) 
			{
				return null;
			}
			else 
			{
				this.domainObjectMap = new java.util.HashMap();
			}
		}
		Map valueObjects = (Map) this.domainObjectMap.get(domainObject);
		if ( null == valueObjects && create) 
		{
			valueObjects = new java.util.HashMap();
			this.domainObjectMap.put(domainObject, valueObjects);
		}
		return valueObjects;
	}
}
