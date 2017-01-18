package ims.framework.interfaces;

import ims.framework.enumerations.NotificationDelivery;

public interface IQueuedNotification extends INotification
{
	NotificationDelivery getIQueuedNotificationDelivery();
}
