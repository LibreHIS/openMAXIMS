package ims.framework.utils;

import ims.framework.IEnhancedItem;

public class DateUnit implements IEnhancedItem
{
	public static final DateUnit DAYS = new DateUnit(1, Color.Black);
	public static final DateUnit WEEKS = new DateUnit(2, Color.Red);
	public static final DateUnit MONTHS = new DateUnit(3, Color.Blue);
	public static final DateUnit YEARS = new DateUnit(4, Color.Green);
	
	public static DateUnit getInstance(int id)
	{
		switch(id)
		{
			case 1:
				return DateUnit.DAYS;
			case 2:
				return DateUnit.WEEKS;
			case 3:
				return DateUnit.MONTHS;
			case 4:
				return DateUnit.YEARS;
			default:
				return null;
		}
	}
	
	private DateUnit(int id, Color textColor)
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
	public Color getIItemTextColor() 
	{
		return this.textColor;
	}
	public String toString()
	{
		if(this.equals(DateUnit.DAYS))
			return "Days";
		if(this.equals(DateUnit.WEEKS))
			return "Weeks";
		if(this.equals(DateUnit.MONTHS))
			return "Months";
		if(this.equals(DateUnit.YEARS))
			return "Years";
		return "";
	}
	public String getIItemText()
	{
		return toString();
	}
	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof DateUnit)
			return ((DateUnit)obj).id == this.id;
		return false;
	}
	public int hashCode()
	{
		return this.id;
	}
	
	public static DateUnit[] getInstances()
	{
		DateUnit[] instances = new DateUnit[4];
		instances[0] = DateUnit.DAYS;
		instances[1] = DateUnit.WEEKS;
		instances[2] = DateUnit.MONTHS;
		instances[3] = DateUnit.YEARS;
		return instances;		
	}
	
	private int id;
	private Color textColor;
}
