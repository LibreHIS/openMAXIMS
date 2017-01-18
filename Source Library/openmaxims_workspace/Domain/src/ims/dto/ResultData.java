package ims.dto;

import java.util.HashMap;

/**
 * @author mmihalec
 */
public final class ResultData implements Cloneable
{
	public ResultData()
	{
	}
	private ResultData(HashMap collection)
	{
		this.collection = collection;
	}
	public synchronized void clear()
	{
		this.collection.clear();		
	}
	@SuppressWarnings("unchecked")
	public synchronized void add(String attributeName, String attributeValue)
	{ 
		this.collection.put(attributeName.toUpperCase(), attributeValue);
	}
	public synchronized boolean containsAttribute(String attributeName)
	{
		return this.collection.containsKey(attributeName.toUpperCase());
	}
	public synchronized String getValue(String attributeName)
	{
		return (String)this.collection.get(attributeName.toUpperCase());
	}
	public synchronized String[] getAttributeNames()
	{
		Object[] keys = this.collection.keySet().toArray();
		String[] atrributeNames = new String[keys.length];
		
		for(int x = 0; x < keys.length; x++)
		{
			atrributeNames[x] = (String)keys[x];
		}
		
		return atrributeNames;
	}
	
	public Object clone()
	{
		return new ResultData((HashMap)this.collection.clone()); 
	}
	
	private HashMap collection = new HashMap();
}
