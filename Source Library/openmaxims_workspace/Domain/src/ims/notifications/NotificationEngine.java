package ims.notifications;

import ims.configuration.JNDI;
import ims.domain.DomainSession;
import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.NotificationPriority;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.INotificationEngine;
import ims.framework.interfaces.INotificationsProvider;
import ims.framework.interfaces.IQueuedNotification;
import ims.framework.interfaces.ISchedulerServlet;
import ims.framework.utils.DateTime;

public class NotificationEngine implements INotificationEngine
{
	private static NotificationEngine instance = new NotificationEngine();
	
	public static INotificationEngine getInstance()
	{
		return instance;
	}
	
	private NotificationEngine()
	{		
	}
	public void addNotification(IAppUser user, NotificationPriority priority, String message, String source, NotificationDelivery[] delivery) throws Exception
	{
		addNotification(user, priority, message, source, null, null, delivery);
	}
	public void addNotification(IAppUser user, NotificationPriority priority, String message, String source, String entityName, Integer entityId, NotificationDelivery[] delivery) throws Exception
	{
		scheduleNotification(new DateTime().addSeconds(5), user, priority, message, source, delivery);
	}
	public void scheduleNotification(DateTime dateTime, IAppUser user, NotificationPriority priority, String message, String source, NotificationDelivery[] delivery) throws Exception
	{
		scheduleNotification(dateTime, user, priority, message, source, null, null, delivery);
	}
	public void scheduleNotification(DateTime dateTime, IAppUser user, NotificationPriority priority, String message, String source, String entityName, Integer entityId, NotificationDelivery[] delivery) throws Exception
	{
		String deliveryMessage = "";
		for(int x = 0; x < delivery.length; x++)
		{
			if(deliveryMessage.length() > 0)
				deliveryMessage += ", ";
			deliveryMessage += delivery[x].toString();
		}
		DomainSession domainSession = DomainSession.getSession();
		String logMessage = priority.toString() + " priority notification added for username '" + user.getUsername() + "' from source '" + source + "' for date " + dateTime.toString() + " with " + deliveryMessage + " delivery";
		domainSession.createSystemLogEntry(SystemLogType.NOTIFICATIONS_ENGINE, SystemLogLevel.INFORMATION, logMessage);
		
		INotificationsProvider notificationsProvider = new NotificationsFactory(domainSession).getNotificationsProvider();
		IQueuedNotification[] queuedNotifications = notificationsProvider.queueNotification(user.getUserId(), priority, message, source, entityName, entityId, delivery);		
		if(queuedNotifications == null)
			throw new Exception("Invalid queued notification(s)");
				
		for(int x = 0; x < queuedNotifications.length; x++)
		{
			dispatchNotification(queuedNotifications[x], dateTime);
		}
	}
	
	private void dispatchNotification(IQueuedNotification queuedNotification, DateTime dateTime) throws Exception 
	{
		ISchedulerServlet scheduler = JNDI.getTaskSchedulerServlet();
		if(scheduler == null)
			throw new Exception("Unable to dispatch notification");
		
		scheduler.addNotification(queuedNotification, dateTime);
	}
}
