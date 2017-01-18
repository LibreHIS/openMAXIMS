package ims.framework.utils;

import ims.framework.utils.beans.DateBean;
import ims.vo.ImsCloneable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable, ImsCloneable, Serializable
{
	private static final long serialVersionUID = 5959992767831068706L;

	public Date()
	{
		this(new java.util.Date());
	}
	public Date(Date date)
	{
		this(date.getDate());
	}
	public Date(String date) throws ParseException
	{
		this(new SimpleDateFormat(DateFormat.STANDARD.toString()).parse(date));
	}
	public Date(DateBean bean)
	{
		this(bean.getDate());
	}
	
	public Date(String date, DateFormat format) throws ParseException
	{
		this(new SimpleDateFormat(format.toString()).parse(date));
	}
	public Date(java.util.Date date)
	{
		this.calendar.setTime(date);
		this.calendar.set(Calendar.HOUR_OF_DAY, 0);
		this.calendar.set(Calendar.MINUTE, 0);
		this.calendar.set(Calendar.SECOND, 0);
		this.calendar.set(Calendar.MILLISECOND, 0);		
	}
	public Date(int year, int month, int day)
	{
        this.calendar.set(year, month - 1, day);
		this.calendar.set(Calendar.HOUR_OF_DAY, 0);
		this.calendar.set(Calendar.MINUTE, 0);
		this.calendar.set(Calendar.SECOND, 0);
		this.calendar.set(Calendar.MILLISECOND, 0);		
	}	

	public int getYear()
	{
		return this.calendar.get(java.util.Calendar.YEAR);
	}
	public Date setYear(int value)
	{
		this.calendar.set(java.util.Calendar.YEAR, value);
		return this;
	}
	public int getMonth()
	{
		return this.calendar.get(java.util.Calendar.MONTH) + 1;
	}
	public Date setMonth(int value)
	{
		this.calendar.set(java.util.Calendar.MONTH, value - 1);
		return this;
	}
	public int getDay()
	{
		return this.calendar.get(java.util.Calendar.DATE);
	}
	public Date setDay(int value)
	{
		this.calendar.set(java.util.Calendar.DATE, value);
		return this;
	}
	public int getNumberOfDays()
	{
		return getNumberOfDays(this.calendar.get(java.util.Calendar.YEAR),this.calendar.get(java.util.Calendar.MONTH) + 1 );
	}
	
	public static int getNumberOfDays(int year, int month)
	{
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
  		daysInMonths[1] += calendar.isLeapYear(year) ? 1 : 0;
   		return daysInMonths[month-1];
	}
	
	public long getSeconds()
	{
		return this.getDate().getTime()/1000;	
	}
	
	public Date addSeconds(long seconds)
	{
		return addSeconds((int)seconds);
	}

	public Date addSeconds(int seconds)
	{
		this.calendar.add(Calendar.SECOND, seconds);
		return this;
	}
	
	public Date addDay(int value)
	{
		this.calendar.add(java.util.Calendar.DAY_OF_MONTH, value);
		return this;
	}
	public Date addMonth(int value)
	{
		this.calendar.add(java.util.Calendar.MONTH, value);
		return this;
	}
	public Date addYear(int value)
	{
		this.calendar.add(java.util.Calendar.YEAR, value);
		return this;
	}

	public boolean equals(Object obj)
	{
		if(obj instanceof Date)
			return this.compareTo(obj) == 0;
		return false;
	}
	
	public int hashCode()
	{
		return this.getDate().hashCode();
	}
	public String toString()
	{
        return toString(DateFormat.STANDARD);
	}

    public String toString(DateFormat df)
    {
        return new SimpleDateFormat(df.toString()).format(this.calendar.getTime());
    }
    
	public java.util.Date getDate()
	{
		this.calendar.set(Calendar.HOUR_OF_DAY, 0);
		this.calendar.set(Calendar.MINUTE, 0);
		this.calendar.set(Calendar.SECOND, 0);
		this.calendar.set(Calendar.MILLISECOND, 0);		
		return this.calendar.getTime();
	}
	
	/**
	 * Calculate the difference in years between this Date and another.
	 * @see #yearDiff(Calendar)
	 * 
	 * @param otherDate
	 * @return
	 */
	public int yearDiff(Date otherDate) {
		return yearDiff(otherDate.calendar);
	}
	
	/**
	 * Calculate the difference in years between this Date and the current time.
	 * @see #yearDiff(Calendar)
	 * @return
	 */
	public int yearDiff() {
		Calendar now = new GregorianCalendar();
		return yearDiff(now);
	}
	
	/**
	 * Calculate the difference in years between this Date and another.
	 * @see #yearDiff(Calendar)
	 * 
	 * @param otherDate
	 * @return
	 */
	public int yearDiff(PartialDate otherDate) 
	{
		if (otherDate.isDate()) 
		{
			return yearDiff(otherDate.toDate());
		}
			
		return new PartialDate(this).yearDiff(otherDate);		
	}
	/**
	 * Calculate the difference in whole years between this Date and the date
	 * represented by the Calendar.
	 * The day-of-month and month aspects of the other calendar are consulted 
	 * to determine difference to the nearest whole year.
	 * For example the year difference for date 24-March-2004 and 24-March-2007 is 3 years.
	 * But for dates 24-March-2004 and 23-March-2007 the year difference is 2 years. 
	 * If the otherCalendar is a date before this date, then the result
	 * is positive. If the otherCalendar is a date after this date,
	 * then the result is negative.
	 * 
	 * @param otherCalendar
	 * @return
	 */
	public int yearDiff(Calendar otherCalendar) {
		int yearDiff = this.calendar.get(Calendar.YEAR) - otherCalendar.get(Calendar.YEAR);
		int monthDiff = this.calendar.get(Calendar.MONTH) - otherCalendar.get(Calendar.MONTH);
		int dateDiff = this.calendar.get(Calendar.DAY_OF_MONTH) - otherCalendar.get(Calendar.DAY_OF_MONTH);
		if (dateDiff < 0) {
			monthDiff--;
		}
		if (monthDiff < 0) {
			yearDiff--;
		}
		return yearDiff;
	}
	
	public DayOfWeek getDayOfWeek()
	{
		int d = this.calendar.get(Calendar.DAY_OF_WEEK);
		switch (d)
		{
		case Calendar.MONDAY:
			return DayOfWeek.MONDAY;
		case Calendar.TUESDAY:
			return DayOfWeek.TUESDAY;
		case Calendar.WEDNESDAY:
			return DayOfWeek.WEDNESDAY;
		case Calendar.THURSDAY:
			return DayOfWeek.THURSDAY;
		case Calendar.FRIDAY:
			return DayOfWeek.FRIDAY;
		case Calendar.SATURDAY:
			return DayOfWeek.SATURDAY;
		case Calendar.SUNDAY:
			return DayOfWeek.SUNDAY;
		}
		throw new RuntimeException("Date.getDayOfWeek() - Unrecognized day of week");
	}
	
	public int getNumberOfDaysInTheMonth()
	{
		return this.calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public boolean isLessThan(Date other)
	{
		return this.compareTo(other) < 0;
	}
	public boolean isLessThan(PartialDate other)
	{
		return this.compareTo(other) < 0;
	}
	public boolean isLessOrEqualThan(Date other)
	{
		return this.compareTo(other) <= 0;
	}
	public boolean isLessOrEqualThan(PartialDate other)
	{
		return this.compareTo(other) <= 0;
	}
	public boolean isGreaterThan(Date other)
	{
		return this.compareTo(other) > 0;
	}
	public boolean isGreaterThan(PartialDate other)
	{
		return this.compareTo(other) > 0;
	}	
	public boolean isGreaterOrEqualThan(Date other)
	{
		return this.compareTo(other) >= 0;
	}
	public boolean isGreaterOrEqualThan(PartialDate other)
	{
		return this.compareTo(other) >= 0;
	}
	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return 1;
		}
		else if ((obj instanceof Date))
		{			
			return this.getDate().compareTo(((Date)obj).getDate());
		}
		else if ((obj instanceof java.util.Date))
		{			
			return this.getDate().compareTo((java.util.Date)obj);
		}
		else if ((obj instanceof PartialDate))
		{
			PartialDate compareObj = (PartialDate)obj;
			if(compareObj.getYear() == null)
				return 1;
			
			if(this.getYear() != compareObj.getYear().intValue())
				return this.getYear() > compareObj.getYear().intValue() ? 1 : -1;
			
			if(compareObj.getMonth() == null)
				return 1;
			
			if(this.getMonth() != compareObj.getMonth().intValue())
				return this.getMonth() > compareObj.getMonth().intValue() ? 1 : -1;
			
			if(compareObj.getDay() == null)
				return 1;
			
			if(this.getDay() != compareObj.getDay().intValue())
				return this.getDay() > compareObj.getDay().intValue() ? 1 : -1;
				
			return 0;
		}
		
		throw new ClassCastException("A Date object cannot be compared to an Object of type " + obj.getClass().getName());		
	}
	
	public Date evalDate(DateReference dRef, Boolean bPlus, Integer nUnits, DateUnit dUnit) 
	{
		if (dRef == null)
			throw new IllegalArgumentException("No date reference specified in expression");
		
		if (bPlus == null)
		{
			//parse for Today or Formdate
			if (dRef.equals(DateReference.TODAY))
				return new Date();
			else if (dRef.equals(DateReference.FORMDATE))
				return this;
			else
				throw new IllegalArgumentException("Reference in expression not supported");
		}
		
		Date stDate;
		//parse for full expression TODAY + 10 Days
		if (dRef.equals(DateReference.TODAY))
			stDate = this;
		else if (dRef.equals(DateReference.FORMDATE))
			stDate = this;
		else
			throw new IllegalArgumentException("Reference in expression not supported");
		
		if (bPlus.booleanValue())
		{
			if (dUnit.equals(DateUnit.DAYS))
				return stDate.addDay(nUnits.intValue());
			else if (dUnit.equals(DateUnit.WEEKS))
				return stDate.addDay(nUnits.intValue() * 7);							
			else if (dUnit.equals(DateUnit.MONTHS))
				return stDate.addMonth(nUnits.intValue());
			else if (dUnit.equals(DateUnit.YEARS))
				return stDate.addYear(nUnits.intValue());
			else
				throw new IllegalArgumentException("Unit in expression not supported");
		}
		
		if (dUnit.equals(DateUnit.DAYS))
			return stDate.addDay((nUnits.intValue() - (nUnits.intValue() * 2)));
		else if (dUnit.equals(DateUnit.WEEKS))
			return stDate.addDay((nUnits.intValue() * 7) - ((nUnits.intValue() * 7) * 2));							
		else if (dUnit.equals(DateUnit.MONTHS))
			return stDate.addMonth((nUnits.intValue() - (nUnits.intValue() * 2)));
		else if (dUnit.equals(DateUnit.YEARS))
			return stDate.addYear((nUnits.intValue() - (nUnits.intValue() * 2)));
		else
			throw new IllegalArgumentException("Unit in expression not supported");
	}	
	
	public Object clone()
	{
		return new Date(this.getDate());
	}
	
	public Date copy()
	{
		return (Date)this.clone();
	}
	
	public DateBean getBean()
	{
		return new DateBean(this);
	}

	public static DateBean[] getBeanArray(Date[] val)
	{
		DateBean[] beans = new DateBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}
	
	 public static long daysBetween(Date startDate, Date endDate)
	 {
		 Calendar sDate = Calendar.getInstance();
		 sDate.setTime(startDate.getDate());
	
		 Calendar eDate = Calendar.getInstance();
		 eDate.setTime(endDate.getDate());
	
		 long mills_per_day = 1000 * 60 * 60 * 24;
		 long day_diff = (int) Math.round((double)(eDate.getTimeInMillis() - sDate.getTimeInMillis()) / mills_per_day);		 
		 
		 return day_diff;
	 }
	
	public PartialDate toPartialDate()
	{
		return new PartialDate(this); 
	}
	
	protected java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();

}
