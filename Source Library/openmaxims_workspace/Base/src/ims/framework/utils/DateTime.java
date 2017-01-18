/*
 * Created on 17-Feb-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.utils;

import ims.framework.utils.beans.DateTimeBean;
import ims.vo.ImsCloneable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author JMACENRI
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DateTime implements Comparable, ImsCloneable,Serializable
{
	private static final long serialVersionUID = 6592512864328867535L;
	protected java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
	
	public DateTime()
	{
		this.calendar.setTime(new java.util.Date());
	}

	public DateTime(Date d)
	{
		this(d,null);
	}
	
	public DateTime(DateTime dt)
	{
		if (dt == null)
		{
			dt = new DateTime();
		}
		
		Date d = dt.getDate();				
		if (d == null)
		{
			d = new Date();
		}
		long seconds = d.getSeconds();
		Time t = dt.getTime();
		if (t != null)
		{
			seconds += t.getTotalSeconds();
		}
		this.calendar.setTimeInMillis(seconds * 1000);
	}
	
	public DateTime(Date d, Time t)
	{
		if (d == null)
		{
			d = new Date();
		}
		long seconds = d.getSeconds();
		if (t != null)
		{
			seconds += t.getTotalSeconds();
		}
		this.calendar.setTimeInMillis(seconds * 1000);
	}

	public DateTime(DateTimeBean bean)
	{
		this(bean.getDate());
	}
	public DateTime(java.util.Date jdate)
	{	
		this.calendar.setTime(jdate);	
	}

	/**
	 * Constructor takes both date and time in ISO format
	 * @param dt
	 * @throws ParseException
	 */
	
	public DateTime(String dtString) throws ParseException
	{
		// Date is the first 8 characters
		// A separate formatted string is passed to the SimpleDateFormat
		// depending on the length of this string
		String formatString;
		if (dtString.length() == 8)
			formatString="yyyyMMdd";
		else if (dtString.length() == 12)
			formatString="yyyyMMddHHmm";
		else if (dtString.length() == 14)
			formatString="yyyyMMddHHmmss";
		else if (dtString.contains("."))
		{
			dtString=dtString.substring(0,dtString.indexOf('.'));
			formatString="yyyyMMddHHmmss";
		}
		else if (dtString.length() == 17)
			formatString="yyyyMMddHHmmssSSS";
		else
			throw new ParseException("Invalid DateTime parameter specified", 0);
		
		java.util.Date dt = new SimpleDateFormat(formatString).parse(dtString);
		this.calendar.setTimeInMillis(dt.getTime());
	}
	
	public DateTime(String d, String t)  throws ParseException
	{
		this(d+t);
	}

	public DateTime(long seconds) 
	{
		this.calendar.setTimeInMillis(seconds * 1000);
	}
	
	public void setDateTime(Date d, Time t)
	{
		if (d == null)
		{
			d = new Date();
		}
		long seconds = d.getSeconds();
		if (t != null)
		{
			seconds += t.getTotalSeconds();
		}
		this.calendar.setTimeInMillis(seconds * 1000);
	}
	
	public Date getDate()
	{
		return new Date(this.calendar.getTime());
	}
	
	public Time getTime()
	{
		return new Time(this.calendar.get(Calendar.HOUR_OF_DAY), this.calendar.get(Calendar.MINUTE), this.calendar.get(Calendar.SECOND));
	}
	
	public boolean isLessThan(DateTime other)
	{
		return this.compareTo(other) < 0;
	}
	public boolean isLessOrEqualThan(DateTime other)
	{
		return this.compareTo(other) <= 0;
	}
	public boolean isGreaterThan(DateTime other)
	{
		return this.compareTo(other) > 0;
	}
	public boolean isGreaterOrEqualThan(DateTime other)
	{
		return this.compareTo(other) >= 0;
	}
	public boolean equals(Object obj)
	{
		if (obj instanceof DateTime)
		{
			DateTime dt = (DateTime)obj;
			return (dt.getJavaDate().equals(this.getJavaDate()));
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return this.getDate().hashCode();
	}

	public String toString(boolean includeTime)
	{
		return toString(DateTimeFormat.STANDARD, includeTime);		
	}
	public String toString()
	{
		return toString(true);
	}
	
	public String toString(DateTimeFormat format, boolean includeTime)
	{
		if (includeTime)
			return new SimpleDateFormat(format.toString()).format(this.calendar.getTime());
		
		if (format.equals(DateTimeFormat.ISO))
			return new SimpleDateFormat(DateFormat.ISO.toString()).format(this.calendar.getTime());
		
		return new SimpleDateFormat(DateFormat.STANDARD.toString()).format(this.calendar.getTime());		
	}
	public String toString(DateTimeFormat format)
	{
		return toString(format, true);
	}

	private long getMillis()
	{
		return this.calendar.getTimeInMillis();
	}

	public long getSeconds()
	{
		return this.getMillis()/1000;
	}
	
	public DateTime addSeconds(long seconds)
	{
		return addSeconds((int)seconds);
	}

	public DateTime addSeconds(int seconds)
	{
		this.calendar.add(Calendar.SECOND, seconds);
		return this;
	}
	
	public DateTime addMinutes(int minutes)
	{
		this.calendar.add(Calendar.MINUTE, minutes);
		return this;
	}

	public DateTime addHours(int hours)
	{
		this.calendar.add(Calendar.HOUR, hours);
		return this;
	}

	public DateTime addDays(int days)
	{
		this.calendar.add(Calendar.DATE, days);
		return this;
	}

	public DateTime addMonth(int value)
	{
		this.calendar.add(java.util.Calendar.MONTH, value);
		return this;
	}
	
	public DateTime addYear(int value)
	{
		this.calendar.add(java.util.Calendar.YEAR, value);
		return this;
	}

	public java.util.Date getJavaDate()
	{
		return this.calendar.getTime();
	}

	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(obj instanceof DateTime))
		{
			throw new ClassCastException("A DateTime object cannot be compared an Object of type " + obj.getClass().getName());
		}
		DateTime compareObj = (DateTime)obj;
		return this.getJavaDate().compareTo(compareObj.getJavaDate());
	}

	public Object clone()
	{		
		return new DateTime((java.util.Date)this.getJavaDate().clone());
	}
	
	public DateTime copy()
	{
		return (DateTime)this.clone();
	}
	
	public DateTimeBean getBean()
	{
		return new DateTimeBean(this);
	}

	public static DateTimeBean[] getBeanArray(DateTime[] val)
	{
		DateTimeBean[] beans = new DateTimeBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}

	/**
	 * Calculate the difference in days between start date and end date.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int daysDiff(DateTime startDate, DateTime endDate)
	{
		if (startDate == null)
			throw new IllegalArgumentException("No start date specified");
		if (endDate == null)
			throw new IllegalArgumentException("No end date specified");
		
		GregorianCalendar start = new GregorianCalendar((startDate.getDate()).getYear(), (startDate.getDate()).getMonth() - 1, (startDate.getDate()).getDay(), startDate.getTime().getHour(), startDate.getTime().getMinute(), startDate.getTime().getSecond());
		GregorianCalendar end = new GregorianCalendar(endDate.getDate().getYear(), endDate.getDate().getMonth() - 1, endDate.getDate().getDay(), endDate.getTime().getHour(), endDate.getTime().getMinute(), endDate.getTime().getSecond());
		
		long endInMillis = end.getTimeInMillis() + end.getTimeZone().getOffset( end.getTimeInMillis() );
		long startInMillis = start.getTimeInMillis() + start.getTimeZone().getOffset( start.getTimeInMillis() );
		
		return (int)((((endInMillis < startInMillis ? startInMillis - endInMillis : endInMillis - startInMillis)/1000)/60)/60)/24;
	}
	
	/**
	 * Calculate the difference in minutes between start date and end date.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int minutesDiff(DateTime startDate, DateTime endDate)
	{
		if (startDate == null)
			throw new IllegalArgumentException("No start date specified");
		if (endDate == null)
			throw new IllegalArgumentException("No end date specified");
		
		GregorianCalendar start = new GregorianCalendar((startDate.getDate()).getYear(), (startDate.getDate()).getMonth() - 1, (startDate.getDate()).getDay(), startDate.getTime().getHour(), startDate.getTime().getMinute(), startDate.getTime().getSecond());
		GregorianCalendar end = new GregorianCalendar(endDate.getDate().getYear(), endDate.getDate().getMonth() - 1, endDate.getDate().getDay(), endDate.getTime().getHour(), endDate.getTime().getMinute(), endDate.getTime().getSecond());
		
		long endInMillis = end.getTimeInMillis() + end.getTimeZone().getOffset( end.getTimeInMillis() );
		long startInMillis = start.getTimeInMillis() + start.getTimeZone().getOffset( start.getTimeInMillis() );
		
		return (int)(((endInMillis < startInMillis ? startInMillis - endInMillis : endInMillis - startInMillis)/1000)/60);
	}	
}
