package ims.framework.utils;

import java.text.ParseException;

import ims.framework.utils.beans.PartialDateBean;
import ims.vo.ImsCloneable;

public class PartialDate implements Comparable, ImsCloneable, java.io.Serializable
{
	private static final long serialVersionUID = 7742190136291855799L;
	public PartialDate()
	{
		this.year = null;
		this.month = null;
		this.day = null;
	}
	public PartialDate(Date date)
	{
		this.year = new Integer(date.getYear());
		this.month = new Integer(date.getMonth());
		this.day = new Integer(date.getDay());
	}
	public PartialDate(Integer year, Integer month, Integer day) 
	{
		if (year == null && month != null) 
			throw new RuntimeException("Year cannot be null if month is supplied");
		if (month == null && day != null) 
			throw new RuntimeException("Month cannot be null if day is supplied");
		if (year == null && day != null) 
			throw new RuntimeException("Year cannot be null if day is supplied");
		
		if(year != null && year.intValue() <= 0)
			throw new RuntimeException("Invalid year value");
		if(month != null && month.intValue() <= 0)
			throw new RuntimeException("Invalid month value");
		if(day != null && day.intValue() <= 0)
			throw new RuntimeException("Invalid day value");
		
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public PartialDate(Integer dateVal)
	{
		if (dateVal == null) throw new RuntimeException("Integer dateVal cannot be null in PartialDate constructor");
		parseDate(dateVal);
	}
	public PartialDate(String dateStr)
	{
		if (dateStr == null) throw new RuntimeException("String dateStr cannot be null in PartialDate constructor");
		parseDate(dateStr);
	}
	public PartialDate(PartialDateBean bean)
	{
		this(bean.getYear(), bean.getMonth(), bean.getDay());
	}
	public boolean isDate()
	{
		return this.year != null && this.month != null && this.month.intValue() > 0 && this.day != null && this.day.intValue() > 0;
	}
	public Date toDate()
	{
		return this.isDate() ? new Date(this.year.intValue(), this.month.intValue(), this.day.intValue()) : null;
	}
	public Integer getDay()
	{
		return this.day;
	}
	public Integer getMonth()
	{
		return this.month;
	}
	public Integer getYear()
	{
		return this.year;
	}
	public Integer toInteger()
	{
		int y,m,d;
		if (this.year == null) 
			return null;		
		y = this.year.intValue() * 10000;
		
		if (this.month == null) 
			m = 0;
		else 
			m = this.month.intValue() * 100;
		
		if (this.day == null) 
			d = 0;
		else 
			d = this.day.intValue();
		
		return new Integer(y + m + d);	
	}
	
	public boolean isLessThan(PartialDate other)
	{
		return compareTo(other) < 0;
	}
	public boolean isLessThan(Date other)
	{
		return compareTo(other) < 0;
	}
	public boolean isLessOrEqualThan(PartialDate other)
	{
		return compareTo(other) <= 0;
	}
	public boolean isLessOrEqualThan(Date other)
	{
		return compareTo(other) <= 0;
	}
	public boolean isGreaterThan(PartialDate other)
	{
		return compareTo(other) > 0;
	}
	public boolean isGreaterThan(Date other)
	{
		return compareTo(other) > 0;
	}
	public boolean isGreaterOrEqualThan(PartialDate other)
	{
		return compareTo(other) >= 0;
	}
	public boolean isGreaterOrEqualThan(Date other)
	{
		return compareTo(other) >= 0;
	}		
	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			if(this.day == null && this.month == null && this.year == null)
				return 0;
			
			return 1;
		}		
		else if(obj instanceof PartialDate)
		{
			PartialDate compareObj = (PartialDate)obj;
			if (this.isDate() && compareObj.isDate()) 
			{
				return this.toDate().compareTo(compareObj.toDate());
			}
			else if(this.toInteger() != null && compareObj.toInteger() != null)
			{
				return this.toInteger().compareTo(compareObj.toInteger());
			}
			else
			{
				if (this.toInteger() == null && compareObj.toInteger() == null) 
					return 0;
				
				return compareObj.toInteger() == null ? 1 : -1;
			}	
		}
		else if(obj instanceof Date)
		{
			Date compareObj = (Date)obj;
			
			if(this.isDate())
			{
				return this.toDate().compareTo((compareObj));
			}
			
			if(this.year == null)
				return -1;
			
			if(this.year.intValue() != compareObj.getYear())
				return this.year.intValue() > compareObj.getYear() ? 1 : -1;
				
			if(this.month == null)
				return -1;
			
			if(this.month.intValue() != compareObj.getMonth())
				return this.month.intValue() > compareObj.getMonth() ? 1 : -1;
				
			if(this.day == null)
				return -1;
			
			if(this.day.intValue() != compareObj.getDay())
				return this.day.intValue() > compareObj.getDay() ? 1 : -1;
				
			return 0;			
		}
		
		throw new ClassCastException("A PartialDate object cannot be compared an Object of type " + obj.getClass().getName());		
	}
	
	public boolean equals(Object obj)
	{
		return compareTo(obj) == 0;
	}	
	
	public int hashCode()
	{
		int y,m,d;
		if (year == null) 
			y = 0;
		else 
			y = year.intValue();
		if (month == null) 
			m = 0;
		else 
			m = month.intValue();
		if (day == null) 
			d = 0;
		else 
			d = day.intValue();		
		return y*10000 + m * 100 + d;
	}
	
	public String toString()
	{
		if (this.year == null || this.year.intValue() < 1) return "__/__/____";
		if (this.month == null || this.month.intValue() < 1) return "__/__/" + this.year.toString();
		if (this.day == null || this.day.intValue() < 1) return "__/" + (this.month.intValue() < 10 ? "0" : "") + this.month.toString() + "/" + this.year.toString();		
		return (this.day.intValue() < 10 ? "0" : "") + this.day.toString() + "/" + (this.month.intValue() < 10 ? "0" : "") + this.month.toString() + "/" + this.year.toString();
	}
	public String toString(DateFormat df)
	{
		if (this.year == null) 
			return null;
		if (this.isDate()) 
			return this.toDate().toString(df);
		if (df.equals(DateFormat.ISO))
		{
			int nullDay = this.day == null ? 0 : this.day.intValue();
			int nullMonth = this.month == null ? 0 : this.month.intValue();
			int fullDate = this.year.intValue()*10000 + nullMonth*100 + nullDay;
			return String.valueOf(fullDate);			
		}

		return this.toString();
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
		if (this.isDate() && otherDate.isDate())
			return this.toDate().yearDiff(otherDate.toDate());
		
		if (this.year == null) 
			throw new RuntimeException("year cannot be null when calling yearDiff");
		
		return this.year.intValue() - otherDate.getYear().intValue();		
	}

	public int yearDiff() 
	{
		if (this.isDate()) 
			return this.toDate().yearDiff();
		
		if (this.year == null) 
			throw new RuntimeException("year cannot be null when calling yearDiff");
		
		Date now = new Date();
		return this.year.intValue() - now.getYear();		
	}

	public int yearDiff(Date otherDate) 
	{
		if (this.isDate()) 
			return this.toDate().yearDiff(otherDate);
		
		if (this.year == null) 
			throw new RuntimeException("year cannot be null when calling yearDiff");
			
		return this.year.intValue() - otherDate.getYear();		
	}
	private void parseDate(String dateStr)
	{
		if (dateStr.length() <= 4) 
		{
			this.year = new Integer(dateStr);
		}
		else if (dateStr.length() > 4 && dateStr.length() < 7) 
		{
			this.year = new Integer(dateStr.substring(0,4));
			this.month = new Integer(dateStr.substring(4));
		}
		else 
		{
			this.year = new Integer(dateStr.substring(0,4));
			this.month = new Integer(dateStr.substring(4,6));
			this.day = new Integer(dateStr.substring(6,8));				
		}
		if (month != null && month.intValue() == 0) month = null;
		if (day != null && day.intValue() == 0) day = null;
	}
	private void parseDate(Integer dateVal)
	{
		String dateStr = dateVal.toString();
		parseDate(dateStr);
	}	
	
	public Object clone()
	{
		return new PartialDate(this.year, this.month, this.day);
	}
	
	public PartialDate copy()
	{
		return (PartialDate)clone();
	}
	
	public PartialDateBean getBean()
	{
		return new PartialDateBean(this);
	}
	
	public static PartialDateBean[] getBeanArray(PartialDate[] val)
	{
		PartialDateBean[] beans = new PartialDateBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}

	/**
	 * toSearchRange
	 * Method which returns two integers
	 * that should be used when searching
	 * for a partial date on a database.
	 * e.g. If value is 1942, the values returned
	 * are 19420000 and 19429999 so that a between
	 * statement will return all records for the
	 * year 1942.
	 * @return
	 */
	private Integer[] toSearchRange(boolean properDate)
	{
	    int y,m,d;
	    int toY, toM, toD;
		if (this.year == null)
		{
			y=1800;
			toY=2525;
		}
		else
		{
		    y = toY = this.year.intValue();
		}
		
		if (this.month == null || this.month.intValue() == 0)
		{
			if (properDate) m = 1;
			else m = 0;
		    toM=12;
		}
		else 
		{
		    m = toM = this.month.intValue();
		}
		
		if (this.day == null || this.day.intValue() == 0)
		{
			if (properDate) d = 1;
			else d = 0;
		    toD = Date.getNumberOfDays(toY, toM);
		}
		else 
		{
		    d = toD = this.day.intValue();
		}
		
		Integer fromValue = new Integer( (y*10000) + (m*100) + d );
		Integer toValue = new Integer( (toY*10000) + (toM*100) + toD );
		Integer[] retValue = {fromValue, toValue};
		return retValue;
	}
	public Integer[] toSearchRange()
	{
		return toSearchRange(false);
	}

	public Date[] toDateRange() throws ParseException
	{
		Integer[] intRange = toSearchRange(true);
		Date[] retValue = {new Date(intRange[0].toString(), DateFormat.ISO), new Date(intRange[1].toString(), DateFormat.ISO)};
		return retValue;		
	}
	
	public boolean isEmpty()
	{
		return (this.year == null && this.month == null && this.day == null);
	}

	private Integer year;
	private Integer month;
	private Integer day;
}
