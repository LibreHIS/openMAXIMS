/*
 * Created on 04-May-2005
 *
 */
package ims.framework.utils.beans;

import ims.framework.utils.Date;

/**
 * @author jmacenri
 *
 */
public class DateBean 
{
	private java.util.Date date;
	
	public DateBean()
	{
		
	}
	public DateBean(Date val)
	{
		this.date = val.getDate();
	}

	public Date buildDate()
	{
		return new Date(this);
	}
	public static Date[] buildDateArray(DateBean[] beans)
	{
		Date[] ret = new Date[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildDate();			
		}
		return ret;		
	}

	public java.util.Date getDate() 
	{
		return this.date;
	}
	public void setDate(java.util.Date date) 
	{
		this.date = date;
	}
}
