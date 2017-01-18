package ims.scheduler;

import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import ims.framework.enumerations.SchedulerJobFrequency;
import ims.framework.utils.DateTime;
import ims.framework.utils.Time;
import ims.framework.utils.TimeFormat;

public class SchedulerTrigger
{
	private SchedulerJobFrequency frequency;
	private DateTime startDateTime;
	private DateTime stopDateTime;
	private Integer days;
	private Integer hours;
	private Integer minutes;	
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean sun;
	private Integer	dayOfMonth;
	private Time timeOfMonth;
	
	public SchedulerTrigger(String xml) throws Exception
	{
		initFromXML(xml);		
	}
	
	public Integer getDayOfMonth()
	{
		return dayOfMonth;
	}
	
	public Time getTimeOfMonth()
	{
		return timeOfMonth;
	}
	
	public Integer getDays()
	{
		return days;
	}	
	
	public Integer getHours()
	{
		return hours;
	}
	
	public Integer getMinutes()
	{
		return minutes;
	}
	
	public boolean getMon()
	{
		return mon;
	}
	
	public boolean getTue()
	{
		return thu;
	}
	
	public boolean getWed()
	{
		return wed;
	}
	
	public boolean getThu()
	{
		return thu;
	}
	
	public boolean getFri()
	{
		return fri;
	}
	
	public boolean getSat()
	{
		return sat;
	}
	
	public boolean getSun()
	{
		return sun;
	}
	
	public SchedulerJobFrequency getFrequency()
	{
		return frequency;
	}	
	public DateTime getStartDateTime()
	{
		return startDateTime;
	}	
	public DateTime getStopDateTime()
	{
		return stopDateTime;
	}	
	public Object getRepeatInterval()
	{
		Object repeatInterval = null;
		
		if (days != null)
		{			
			repeatInterval = days * 24L * 60L * 60L * 1000L;				
			
			if (hours != null)
			{	
				repeatInterval = days * (24L * 60L * 60L * 1000L) +  hours * (60L * 60L * 1000L);				
				
				if (minutes != null)
				{	
					repeatInterval = days * (24L * 60L * 60L * 1000L) +  hours * (60L * 60L * 1000L) + minutes * (60L * 1000L);								
				}
			}
		}
		else if (hours != null)
		{
			repeatInterval = hours * (60L * 60L * 1000L);
			
			if (minutes != null)
			{	
				repeatInterval = hours * (60L * 60L * 1000L) + minutes * (60L * 1000L);								
			}
		}
		else if (minutes != null)
		{
			repeatInterval = minutes * (60L * 1000L);						
		}
		
		return repeatInterval;
	}
	public String getCronString()
	{
		StringBuffer cronString = new StringBuffer();		
		java.util.Calendar cal = Calendar.getInstance();
		
		if(frequency == SchedulerJobFrequency.DAILY)
		{
			cal.setTime(startDateTime.getJavaDate());

			cronString.append(cal.get(Calendar.SECOND)); //second
			cronString.append(" ");
			cronString.append(cal.get(Calendar.MINUTE)); //minute
			cronString.append(" ");
			cronString.append(cal.get(Calendar.HOUR_OF_DAY)); //hour
			cronString.append(" ");
			cronString.append("*"); //Day of month
			cronString.append("/");
			cronString.append(days);
			cronString.append(" *"); //Month
			cronString.append(" ?"); //Day of week
		}
		else if(frequency == SchedulerJobFrequency.WEEKLY)
		{
			cal.setTime(startDateTime.getJavaDate());
			
			String comma = "";
			StringBuffer weekDays = new StringBuffer();
			
			if(mon == true)
			{
				weekDays.append("Mon");
				comma = ",";
			}
			if(tue == true)
			{
				weekDays.append(comma);
				weekDays.append("Tue");
				comma = ",";
			}
			if(wed == true)
			{
				weekDays.append(comma);
				weekDays.append("Wed");
				comma = ",";
			}
			if(thu == true)
			{
				weekDays.append(comma);
				weekDays.append("Thu");
				comma = ",";
			}
			if(fri == true)
			{
				weekDays.append(comma);
				weekDays.append("Fri");
				comma = ",";
			}
			if(sat == true)
			{
				weekDays.append(comma);
				weekDays.append("Sat");
				comma = ",";
			}
			if(sun == true)
			{
				weekDays.append(comma);
				weekDays.append("Sun");
				comma = ",";
			}
			
			cronString.append(cal.get(Calendar.SECOND)); //second
			cronString.append(" ");
			cronString.append(cal.get(Calendar.MINUTE)); //minute
			cronString.append(" ");
			cronString.append(cal.get(Calendar.HOUR_OF_DAY)); //hour
			cronString.append(" ?"); //Day of month
			cronString.append(" *"); //Month
			cronString.append(" "); 
			cronString.append(weekDays.toString()); //Day of week
		}
		else if(frequency == SchedulerJobFrequency.MONTHLY)
		{
			cronString.append(timeOfMonth.getSecond()); //second
			cronString.append(" ");
			cronString.append(timeOfMonth.getMinute()); //minute
			cronString.append(" ");
			cronString.append(timeOfMonth.getHour()); //hour
			cronString.append(" ");
			cronString.append(dayOfMonth); //Day of month
			cronString.append(" *"); //Month
			cronString.append(" ?"); //Day of week
		}

		return cronString.toString();
	}
	
	private void initFromXML(String xmlTrigger) throws Exception
	{
		Document maindoc = getXmlDocument(xmlTrigger);
	
		Node node = maindoc.selectSingleNode("trigger/frequency");
		if(node != null)
			frequency = parseFrequency(node.getStringValue());	
		node = maindoc.selectSingleNode("trigger/startdatetime");		
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			startDateTime = new DateTime(node.getStringValue());
		node = maindoc.selectSingleNode("trigger/stopdatetime");		
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			stopDateTime = new DateTime(node.getStringValue());
		node = maindoc.selectSingleNode("trigger/days");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			days = new Integer(Integer.parseInt(node.getStringValue()));
		node = maindoc.selectSingleNode("trigger/hours");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			hours = new Integer(Integer.parseInt(node.getStringValue()));
		node = maindoc.selectSingleNode("trigger/minutes");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			minutes = new Integer(Integer.parseInt(node.getStringValue()));
		node = maindoc.selectSingleNode("trigger/mon");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			mon = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/tue");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			tue = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/wed");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			wed = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/thu");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			thu = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/fri");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			fri = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/sat");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			sat = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		node = maindoc.selectSingleNode("trigger/sun");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			sun = node.getStringValue().equalsIgnoreCase("true") ? true : false;
		
		node = maindoc.selectSingleNode("trigger/dayofmonth");
		if (node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			dayOfMonth = new Integer(Integer.parseInt(node.getStringValue()));
		node = maindoc.selectSingleNode("trigger/timeofmonth");
		if(node != null && node.getStringValue() != null && node.getStringValue().length() > 0)
			timeOfMonth = new Time(node.getStringValue(), TimeFormat.FLAT4);
	}
	private SchedulerJobFrequency parseFrequency(String stringValue) throws Exception
	{
		if(stringValue.toLowerCase().equals("daily"))
			return SchedulerJobFrequency.DAILY;
		else if(stringValue.toLowerCase().equals("weekly"))
			return SchedulerJobFrequency.WEEKLY;
		else if(stringValue.toLowerCase().equals("monthly"))
			return SchedulerJobFrequency.MONTHLY;
		else if(stringValue.toLowerCase().equals("runonce"))
			return SchedulerJobFrequency.ONCE;
		
		throw new Exception("Unkown job frequency");
	}

	private Document getXmlDocument(String xmlBuffer) throws DocumentException
	{
		return DocumentHelper.parseText(xmlBuffer);
	}
}
