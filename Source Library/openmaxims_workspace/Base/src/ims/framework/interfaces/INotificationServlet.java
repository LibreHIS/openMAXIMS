package ims.framework.interfaces;

import ims.framework.enumerations.NotificationDelivery;

public interface INotificationServlet
{	
	public void addNotification(INotification notification, NotificationDelivery[] deliveryMethods) throws Exception;
}
