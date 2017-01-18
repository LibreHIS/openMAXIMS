package ims.framework.utils;

import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.NotificationPriority;
import ims.framework.interfaces.IQueuedNotification;

public class QueuedNotification implements IQueuedNotification 
{
	private NotificationDelivery delivery;
	private DateTime dateTime = new DateTime();
	private Integer entityId;
	private String entityType;
	private int id;
	private String message;
	private NotificationPriority priority = NotificationPriority.NORMAL;
	private String source;
	private Integer userId;
	
	public NotificationDelivery getIQueuedNotificationDelivery() 
	{
		return delivery;
	}
	public void setIQueuedNotificationDelivery(NotificationDelivery delivery) 
	{
		this.delivery = delivery;
	}
	public DateTime getINotificationDateTime() 
	{
		return dateTime;
	}
	public void setINotificationDateTime(DateTime dateTime) 
	{
		this.dateTime = dateTime;
	}
	public Integer getINotificationEntityId() 
	{
		return entityId;
	}
	public void setINotificationEntityId(Integer entityId) 
	{
		this.entityId = entityId;
	}
	public String getINotificationEntityType() 
	{
		return entityType;
	}
	public void setINotificationEntityType(String entityType) 
	{
		this.entityType = entityType;
	}
	public int getINotificationId() 
	{
		return id;
	}
	public void setINotificationId(int id) 
	{
		this.id = id;
	}
	public String getINotificationMessage() 
	{
		return message;
	}
	public void setINotificationMessage(String message) 
	{
		this.message = message;
	}
	public NotificationPriority getINotificationPriority() 
	{
		return priority;
	}
	public void setINotificationPriority(NotificationPriority priority) 
	{
		this.priority = priority;
	}
	public String getINotificationSource() 
	{
		return source;
	}
	public void setINotificationSource(String source) 
	{
		this.source = source;
	}
	public int getINotificationUserId() 
	{
		return userId;
	}
	public void setINotificationUserId(int userId) 
	{
		this.userId = userId;
	}
}
