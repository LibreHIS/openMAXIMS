/*
 * Created on 04-May-2005
 *
 */
package ims.framework.utils.beans;

import ims.framework.utils.Time;

/**
 * @author jmacenri
 *
 */
public class TimeBean 
{
	private int hour;
	private int minute;
	private int second;
	
	public TimeBean()
	{
		
	}
	
	public TimeBean(Time val)
	{
		this.hour = val.getHour();
		this.minute = val.getMinute();
		this.second = val.getSecond();
	}
	
	public Time buildTime()
	{
		return new Time(this.hour, this.minute, this.second);
	}
	public static Time[] buildTimeArray(TimeBean[] beans)
	{
		Time[] ret = new Time[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildTime();			
		}
		return ret;		
	}

	public int getHour() 
	{
		return this.hour;
	}
	public void setHour(int hour) 
	{
		this.hour = hour;
	}
	public int getMinute() 
	{
		return this.minute;
	}
	public void setMinute(int minute) 
	{
		this.minute = minute;
	}
	public int getSecond() 
	{
		return this.second;
	}
	public void setSecond(int second) 
	{
		this.second = second;
	}
}
