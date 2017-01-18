/*
 * Created on 04-May-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.utils.beans;

import ims.framework.utils.DateTime;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DateTimeBean 
{
	private java.util.Date date;
	
	public DateTimeBean()
	{
		
	}
	public DateTimeBean(DateTime val)
	{
		this.date = val.getJavaDate();
	}

	public java.util.Date getDate() 
	{
		return this.date;
	}
	public void setDate(java.util.Date date) 
	{
		this.date = date;
	}
	public DateTime buildDateTime()
	{
		return new ims.framework.utils.DateTime(this);
	}
	public static DateTime[] buildDateTimeArray(DateTimeBean[] beans)
	{
		DateTime[] ret = new DateTime[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildDateTime();			
		}
		return ret;		
	}

}
