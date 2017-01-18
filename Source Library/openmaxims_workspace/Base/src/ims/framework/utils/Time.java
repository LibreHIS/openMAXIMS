package ims.framework.utils;

import ims.framework.utils.beans.TimeBean;
import ims.vo.ImsCloneable;

import java.util.Calendar;

public class Time implements Comparable, ImsCloneable, java.io.Serializable
{
	private static final long serialVersionUID = -3836323866602711164L;
	public Time()
	{
		Calendar cal = Calendar.getInstance();
		this.hour = cal.get(Calendar.HOUR_OF_DAY);
		this.minute = cal.get(Calendar.MINUTE);
		this.second = cal.get(Calendar.SECOND);
	}
	public Time(java.util.Date jdate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(jdate);
		this.hour = cal.get(Calendar.HOUR_OF_DAY);
		this.minute = cal.get(Calendar.MINUTE);
		this.second = cal.get(Calendar.SECOND);
	}
	public Time(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = 0;
	}
	public Time(int hour, int minute, int second)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public Time(String value) 
	{
		this.construct(value);
	}
	public Time(String value, TimeFormat format)
	{
		if (format.equals(TimeFormat.DEFAULT))
			this.construct(value);
		else 
		{
			int time = Integer.parseInt(value);
			if (format.equals(TimeFormat.FLAT4))
			{
				this.hour = time / 100;
				this.minute = time % 100;
				this.second = 0;
			}
			else if (format.equals(TimeFormat.FLAT6))
			{
				this.hour = time / 10000;
				time %= 10000;
				this.minute = time / 100;
				this.second =  time % 100;
			}
			else
				throw new RuntimeException("Time format is not implemented.");
		}
	}	
	public int getHour()
	{
		return this.hour;
	}
	public void setHour(int value) 
	{
		if (value < 0 || value > 23)
			throw new IndexOutOfBoundsException();
		this.hour = value;
	}
	public int getMinute()
	{
		return this.minute;
	}
	public void setMinute(int value) 
	{
		if (value < 0 || value > 59)
			throw new IndexOutOfBoundsException();
		this.minute = value;
	}	
	public int getSecond() 
	{
		return this.second;
	}
	public void setSecond(int sec) 
	{
		if (sec < 0 || sec > 59)
			throw new IndexOutOfBoundsException();
		this.second = sec;
	}
	public void addHours(int value)
	{
		this.hour = (this.hour + value) % 24;
	}
	public void addMinutes(int value)
	{
		int tmp = this.hour * 60 + this.minute + value;
		while (tmp < 0)
			tmp += 1440; //24 * 60;
		this.minute = tmp % 60;
		tmp -= this.minute;
		tmp /= 60;
		this.hour = tmp % 24;
	}		
	public void addSeconds(long value)
	{
		addSeconds((int)value);
	}
	public void addSeconds(int value)
	{
		addMinutes(value/60);
		int tmp = this.second + (value%60);
		addMinutes(tmp/60);
		this.second = value%60;
	}	
	public boolean equals(Object obj) 
	{
		if (obj == null)
			return false;
		if (obj instanceof Time)
		{
			Time t = (Time)obj;
			return (t.hour == this.hour && t.minute == this.minute);
		}
		throw new RuntimeException("Time can be compared only with time");
	}	
	public int hashCode()
	{
		return hour*10000 + minute*100 + second;
	}	
	public boolean isLessThan(Time other) 
	{
		if (other == null)
			return false;
	
		if(this.hour < other.hour)
			return true;
		else
		if(this.hour > other.hour)
			return false;
		else
		{
			//equal hours
			if(this.minute < other.minute)
				return true;
			
			return false;
		}
	}
	public boolean isLessOrEqualThan(Time other) 
	{
		return this.equals(other) || this.isLessThan(other);
	}
	public boolean isGreaterThan(Time other) 
	{
		if (other == null)
			return false;
		
		if(this.hour > other.hour)
			return true;
		else
			if(this.hour < other.hour)
				return false;
			else
			{
				//equal hours
				if(this.minute > other.minute)
					return true;
				
				return false;
			}
	}
	public boolean isGreaterOrEqualThan(Time other) 
	{
		return this.equals(other) || this.isGreaterThan(other);
	}	
	public String toString()
	{
		String hour = String.valueOf(this.hour);
		String minute = String.valueOf(this.minute);
		
        return (this.hour < 10 ? "0" + hour : hour) + ":" + (this.minute < 10 ? "0" + minute : minute);
	}
	public String toString(TimeFormat format)
	{
		if (format.equals(TimeFormat.DEFAULT))
		return this.toString();
		String h = String.valueOf(this.hour);
		String m = String.valueOf(this.minute);
		String s = String.valueOf(this.second);
		if (format.equals(TimeFormat.FLAT4))
			return (this.hour < 10 ? "0" + h : h) + (this.minute < 10 ? "0" + m : m);
		else if (format.equals(TimeFormat.FLAT6))
			return (this.hour < 10 ? "0" + h : h) + (this.minute < 10 ? "0" + m : m) +(this.second < 10 ? "0" + s : s);
		else if (format.equals(TimeFormat.FSEC))
			return (this.hour < 10 ? "0" + h : h) + "h " + (this.minute < 10 ? "0" + m : m) + "m " + (this.second < 10 ? "0" + s : s) + "s";

		throw new RuntimeException("Time format is not implemented.");
	}
	private void construct(String value)
	{
		int i = value.indexOf(':');
		
		if (i == -1)
		{
			// FWB-343 (allow for time values without : i.e. JavaNas format
			if (!isInteger(value))
				throw new RuntimeException("Time parse error");
			
			// In old format, we can have second included sometimes, but not always, so we have to cater for that
			switch (value.length())
			{
			case 3:
				setSecond(0);
				setMinute(Integer.parseInt(value.substring(1)));
				setHour(Integer.parseInt(value.substring(0,1)));
				break;
			case 4:
				setSecond(0);
				setMinute(Integer.parseInt(value.substring(2)));
				setHour(Integer.parseInt(value.substring(0,2)));
				break;
			case 5:
				setSecond(Integer.parseInt(value.substring(3)));
				setMinute(Integer.parseInt(value.substring(1,3)));
				setHour(Integer.parseInt(value.substring(0,1)));
				break;
			case 6:
				setSecond(Integer.parseInt(value.substring(4)));
				setMinute(Integer.parseInt(value.substring(2,4)));
				setHour(Integer.parseInt(value.substring(0,2)));
				break;
			default:
				throw new RuntimeException("Time parse error");
			}
		}
		else
		{
			setHour(Integer.parseInt(value.substring(0, i)));
			int j;
			if ((j = value.indexOf(':', i+1)) == -1)
			{
				setMinute(Integer.parseInt(value.substring(i+1)));
			}
			else
			{
				setMinute(Integer.parseInt(value.substring(i+1, j)));
				setSecond(Integer.parseInt(value.substring(j+1)));
			}
		}
	}	
	
	private boolean isInteger( String input )   
	{   
	   try  
	   {   
	      Integer.parseInt( input );   
	      return true;   
	   }   
	   catch( NumberFormatException e )   
	   {   
	      return false;   
	   }   
	}  

	
	
	public int getTotalMinutes()
	{
		return (this.hour * 60) + this.minute;
	}
	public int getTotalSeconds()
	{
		return (this.hour * 60 * 60) + this.minute * 60 + this.second;
	}
	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(obj instanceof Time))
		{
			throw new ClassCastException("A Time object cannot be compared an Object of type " + obj.getClass().getName());
		}
		Time compareObj = (Time)obj;
		return new Integer(this.getTotalMinutes()).compareTo(new Integer(compareObj.getTotalMinutes()));		
	}
	public Object clone()
	{
		return new Time(this.hour, this.minute, this.second);
	}	
	public Time copy()
	{
		return (Time)this.clone();
	}	
	public TimeBean getBean()
	{
		return new TimeBean(this);
	}	
	public static TimeBean[] getBeanArray(Time[] val)
	{
		TimeBean[] beans = new TimeBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}	
	public static String getFormattedTime(long milli)
	{
		int hours = (int)(milli / (1000 * 60 * 60));
		int mins = (int)((milli - (hours * 60 * 60 * 1000))/(1000 * 60));
		int seconds = (int)((milli - (hours * 60 * 60 * 1000) - (mins * 60 * 1000))/1000);
		return new Time(hours,mins,seconds).toString(TimeFormat.FSEC);
	}
	
	protected int hour;
	protected int minute;
	protected int second;
}
