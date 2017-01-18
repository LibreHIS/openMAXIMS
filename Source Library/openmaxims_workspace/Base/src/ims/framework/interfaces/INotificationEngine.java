package ims.framework.interfaces;

import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.NotificationPriority;
import ims.framework.utils.DateTime;

public interface INotificationEngine
{
	void addNotification(IAppUser user, NotificationPriority priority, String message, String source, NotificationDelivery[] delivery) throws Exception;
	void addNotification(IAppUser user, NotificationPriority priority, String message, String source, String entityName, Integer entityId, NotificationDelivery[] delivery) throws Exception;
	void scheduleNotification(DateTime dateTime, IAppUser user, NotificationPriority priority, String message, String source, NotificationDelivery[] delivery) throws Exception;
	void scheduleNotification(DateTime dateTime, IAppUser user, NotificationPriority priority, String message, String source, String entityName, Integer entityId, NotificationDelivery[] delivery) throws Exception;
}
