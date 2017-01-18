package ims.framework.enumerations;

import java.io.Serializable;

public class NotificationDelivery implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final NotificationDelivery UI = new NotificationDelivery(1);
	public static final NotificationDelivery EMAIL = new NotificationDelivery(2);
    public static final NotificationDelivery PUSH = new NotificationDelivery(3);
    public static final NotificationDelivery SMS = new NotificationDelivery(4);
    
    public static NotificationDelivery[] ALL = new NotificationDelivery[] { UI, EMAIL, PUSH, SMS }; 
    
    private NotificationDelivery(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
    	return id;
    }
    
    public String toString()
    {
    	if (this.id == UI.id)
    		return "On Screen Notification";
    	else if (this.id == EMAIL.id)
    		return "E-Mail Notification";
    	else if (this.id == PUSH.id)
    		return "Push Notification";
    	else if (this.id == SMS.id)
    		return "SMS Notification";
    	
    	return "Unknown";
    }
    
    public static NotificationDelivery parse(int id)
	{
		if(id == UI.id)
			return UI;
		else if(id == EMAIL.id)
			return EMAIL;
		else if(id == PUSH.id)
			return PUSH;		
		else if(id == SMS.id)
			return SMS;		
		
		throw new RuntimeException("Unable to find NotificationDelivery matching id " + id);
	}	

    private int id; 
}
