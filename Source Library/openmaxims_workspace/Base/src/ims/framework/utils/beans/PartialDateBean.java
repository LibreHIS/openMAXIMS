/*
 * Created on 04-May-2005
 *
 */
package ims.framework.utils.beans;

import ims.framework.utils.PartialDate;

/**
 * @author jmacenri
 *
 */
public class PartialDateBean 
{
	private Integer year;
	private Integer month;
	private Integer day;
	
	public PartialDateBean()
	{
		
	}
	public PartialDateBean(PartialDate val)
	{
		this.year = val.getYear();
		this.month = val.getMonth();
		this.day = val.getDay();
	}
	public PartialDate buildPartialDate()
	{
		return new PartialDate(this);
	}
	public static PartialDate[] buildPartialDateArray(PartialDateBean[] beans)
	{
		PartialDate[] ret = new PartialDate[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildPartialDate();			
		}
		return ret;		
	}

	public Integer getDay() 
	{
		return this.day;
	}
	public void setDay(Integer day) 
	{
		this.day = day;
	}
	public Integer getMonth() 
	{
		return this.month;
	}
	public void setMonth(Integer month) 
	{
		this.month = month;
	}
	public Integer getYear() 
	{
		return this.year;
	}
	public void setYear(Integer year) 
	{
		this.year = year;
	}
}
