package ims.framework.utils;

public class TimeSpan
{
	public TimeSpan(long difference)
	{
		this.difference = difference;
	}
	public static TimeSpan getTimeSpan(Date date1, Date date2)
	{
		return new TimeSpan(date1.calendar.getTimeInMillis() - date2.calendar.getTimeInMillis()); 
	}
	public static TimeSpan getTimeSpan(Time time1, Time time2)
	{
		return new TimeSpan(((time1.hour - time2.hour) * 60 + (time1.minute - time2.minute)) * 60 * 1000); 
	}
	
	public int getDays()
	{
		return (int)(this.difference / (1000 * 60 * 60 * 24));
	}
	public int getMinutes()
	{
		return (int)(this.difference / (1000 * 60));
	}
	
	private long difference;
}
