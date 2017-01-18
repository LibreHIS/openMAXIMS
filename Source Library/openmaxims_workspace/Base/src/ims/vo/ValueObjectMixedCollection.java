package ims.vo;

import ims.framework.exceptions.CodingRuntimeException;

import java.io.Serializable;
import java.util.ArrayList;

public final class ValueObjectMixedCollection extends ValueObjectCollection implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Object> col = new ArrayList<Object>();
	
	public ValueObjectMixedCollection()
	{		
	}
	public ValueObjectMixedCollection(ArrayList<Object> col)
	{
		if(col == null)
			throw new CodingRuntimeException("Invalid value object collection");
		this.col = col;
	}	
	public boolean add(ValueObject value)
	{
		if(this.col.indexOf(value) < 0)
		{
			this.col.add(value);
			return true;
		}
		return false;
	}
	public boolean add(int index, ValueObject value)
	{
		if(this.col.indexOf(value) < 0)
		{
			this.col.add(index, value);
			return true;
		}
		return false;
	}
	public void add(ValueObjectCollection collection)
	{
		if(collection == null)
			return;
		
		ValueObjectMixedCollection mixedCollection = collection.getValueObjectMixedCollection();
		for(int x = 0; x < mixedCollection.size(); x++)
		{
			add(mixedCollection.get(x));
		}
	}
	public void clear()
	{
		this.col.clear();
	}
	public void remove(int index)
	{
		this.col.remove(index);
	}
	public int size()
	{
		return this.col.size();
	}
	public int indexOf(ValueObject instance)
	{
		return col.indexOf(instance);
	}
	public ValueObject get(int index)
	{
		return (ValueObject)this.col.get(index);
	}
	public void set(int index, ValueObject value)
	{
		this.col.set(index, value);
	}
	public void remove(ValueObject instance)
	{
		remove(indexOf(instance));
	}
	public boolean contains(ValueObject instance)
	{
		return indexOf(instance) >= 0;
	}
	public Object clone() 
	{		
		ValueObjectMixedCollection clone = new ValueObjectMixedCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add(((ValueObject)this.col.get(x)).clone());
			else
				clone.col.add(null);
		}
		
		return clone;
	}
	public boolean isValidated() 
	{
		for(int x = 0; x < col.size(); x++)
			if(!((ValueObject)this.col.get(x)).isValidated())
				return false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	@SuppressWarnings("unchecked")
	public String[] validate(String[] existingErrors)
	{
		if(col.size() == 0)
			return null;
		java.util.ArrayList listOfErrors = new java.util.ArrayList();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		for(int x = 0; x < col.size(); x++)
		{
			String[] listOfOtherErrors = ((ValueObject)this.col.get(x)).validate();
			if(listOfOtherErrors != null)
			{
				for(int y = 0; y < listOfOtherErrors.length; y++)
				{
					listOfErrors.add(listOfOtherErrors[y]);
				}
			}
		}
		
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
			return null;
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		return result;
	}
	public String getBoClassName() 
	{
		return null;
	}
	@Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
}
