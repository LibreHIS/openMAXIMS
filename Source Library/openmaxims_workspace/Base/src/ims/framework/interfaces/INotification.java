package ims.framework.interfaces;

import ims.framework.enumerations.NotificationPriority;
import ims.framework.utils.DateTime;

public interface INotification
{
	int getINotificationId();
	int getINotificationUserId();
	DateTime getINotificationDateTime();
	NotificationPriority getINotificationPriority();
	String getINotificationMessage();
	String getINotificationEntityType();
	Integer getINotificationEntityId();
	String getINotificationSource();	
}
