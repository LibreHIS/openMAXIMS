package ims.framework.interfaces;

import ims.framework.utils.DateTime;

public interface IUINotification extends INotification
{
	boolean wasIUINotificationSeen();	
	DateTime getIUINotificationSeenDateTime();		
}
