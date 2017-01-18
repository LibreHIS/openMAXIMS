/*
 * Created on 04-May-2005
 *
 */
package ims.framework.utils.beans;

import ims.framework.utils.Color;

/**
 * @author jmacenri
 *
 */
public class ColorBean 
{
	private String value;
	private String name;
	
	public ColorBean()
	{
		
	}
	public ColorBean(Color val)
	{
		this.value = val.getValue();
		this.name = val.getName();
	}
	
	public Color buildColor()
	{
		return new Color(this);
	}

	public static Color[] buildColorArray(ColorBean[] beans)
	{
		Color[] ret = new Color[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildColor();			
		}
		return ret;		
	}
	
	
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getValue() 
	{
		return this.value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}
}
