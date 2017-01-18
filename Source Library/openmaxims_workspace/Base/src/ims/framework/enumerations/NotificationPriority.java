package ims.framework.enumerations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class NotificationPriority implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private int id;	
	private String text;
	
	public static NotificationPriority CRITICAL = new NotificationPriority(1, "Critical");
	public static NotificationPriority HIGH = new NotificationPriority(2, "High");	
	public static NotificationPriority NORMAL = new NotificationPriority(3, "Normal");
	public static NotificationPriority LOW = new NotificationPriority(4, "Low");
	
	public static List<NotificationPriority> getAll()
	{
		List<NotificationPriority> result = new ArrayList<NotificationPriority>();
		
		result.add(CRITICAL);
		result.add(HIGH);
		result.add(NORMAL);
		result.add(LOW);
		
		return result;
	}
		
	private NotificationPriority(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	
	public int getId()
	{
		return id;
	}
	public String getText()
	{
		return text;
	}		
	public String toString()
	{
		return text;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof NotificationPriority)
			return ((NotificationPriority)obj).id == id;
		return false;
	}
	public static NotificationPriority parse(int id)
	{
		if(id == CRITICAL.id)
			return CRITICAL;
		else if(id == HIGH.id)
			return HIGH;		
		else if(id == NORMAL.id)
			return NORMAL;
		else if(id == LOW.id)
			return LOW;
		
		throw new RuntimeException("Unable to find NotificationPriority matching id " + id);
	}	
}
