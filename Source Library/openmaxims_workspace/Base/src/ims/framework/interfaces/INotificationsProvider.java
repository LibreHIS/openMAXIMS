package ims.framework.interfaces;

import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.NotificationPriority;
import ims.framework.utils.Date;

public interface INotificationsProvider
{
	boolean hasUnseenNotifications(int userId);
	int getUnseenNotificationsCount(int userId);
	IUINotification[] getUnseenNotifications(int userId);
	void markAsSeen(IUINotification notification);
	void markAllAsSeen(int userId);
	IUINotification[] getNotifications(int userId, boolean seen, int maxNumberOfNotifications);
	IUINotification[] getNotifications(int userId, Date dateFrom, Date dateTo, String message, String source, NotificationPriority priority, boolean seen);	
		
	IQueuedNotification[] queueNotification(int userId, NotificationPriority priority, String message, String source, String entityName, Integer entityId, NotificationDelivery[] delivery) throws Exception;
	IQueuedNotification getQueuedNotification(int queuedNotificationId);
	void removeQueuedNotification(IQueuedNotification notification) throws Exception;
	
	IUINotification createUINotification(IQueuedNotification notification) throws Exception;
}
