package ims.framework.utils;

import ims.framework.IEnhancedItem;

public class DateReference implements IEnhancedItem 
{
	public static final DateReference TODAY = new DateReference(1, Color.Black);
	public static final DateReference FORMDATE = new DateReference(2, Color.Blue);
	
	public static DateReference getInstance(int id)
	{
		switch(id)
		{
			case 1:
				return DateReference.TODAY;
			case 2:
				return DateReference.FORMDATE;
			default:
				return null;
		}
	}
	
	private DateReference(int id, Color textColor)
	{
		this.id = id;
		this.textColor = textColor;
	}
	public int getID()
	{
		return this.id;
	}
	public Image getIItemImage() 
	{
		return null;
	}
	public Color getTextColor() 
	{
		return this.textColor;
	}
	public Color getIItemTextColor() 
	{
		return this.textColor;
	}
	public String toString()
	{
		if(this.equals(DateReference.TODAY))
			return "Today";
		if(this.equals(DateReference.FORMDATE))
			return "Form Date";
		return "";
	}
	public String getIItemText()
	{
		return toString();
	}
	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof DateReference)
			return ((DateReference)obj).id == this.id;
		return false;
	}
	public int hashCode()
	{
		return this.id;
	}
	public static DateReference[] getInstances()
	{
		DateReference[] instances = new DateReference[2];
		instances[0] = DateReference.TODAY;
		instances[1] = DateReference.FORMDATE;
		return instances;		
	}
	
	private int id;	
	private Color textColor;
}
