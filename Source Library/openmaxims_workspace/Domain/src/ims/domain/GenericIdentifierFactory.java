package ims.domain;

import java.lang.reflect.Constructor;

import ims.framework.interfaces.IGenericIdentifier;
import ims.vo.ValueObjectRef;

public class GenericIdentifierFactory
{
	public static Object instantiate(IGenericIdentifier value)
	{		
		if(value == null)
			throw new RuntimeException("Invalid generic identifier");
		
		Object instance = null;
		
		try
		{
			Class classForName = Class.forName(value.getClassName());
			if(ValueObjectRef.class.isAssignableFrom(classForName))
			{
				Constructor cstr = classForName.getConstructor(new Class[] { Integer.class, Integer.TYPE } );
				instance = cstr.newInstance(new Object[] { new Integer(value.getId()), new Integer(0) } );			
			}
			else 
			{
				throw new RuntimeException("Unknown or unsupported generic identifier class: " + value.getClassName());
			}
				
		}
		catch (Exception e)
		{
			throw new RuntimeException("Exception when instantiating class for generic identifier with class '" + value.getClassName() + "'", e);
		}		
		
		return instance;
	}
	public static Object[] instantiate(IGenericIdentifier[] value)
	{
		if(value == null)
			return new Object[0];
		
		Object[] instances = new Object[value.length];
		
		for(int x = 0; x < value.length; x++)
		{
			instances[x] = instantiate(value[x]);
		}
		
		return instances;
	}
}
