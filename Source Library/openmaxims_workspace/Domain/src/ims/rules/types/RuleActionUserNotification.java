package ims.rules.types;

import java.util.ArrayList;
import java.util.List;

import ims.framework.enumerations.NotificationPriority;
import ims.rules.interfaces.IRuleAction;

public class RuleActionUserNotification implements IRuleAction
{
	private static final long serialVersionUID = 1L;	
	
	private int[] userIds;	
	private List<RuleActionNotificationElement> text = new ArrayList<RuleActionNotificationElement>();
	private NotificationPriority priority = NotificationPriority.NORMAL;
	private boolean sendEmail = true;
	private boolean sendSMS = false;

	public int[] getUserIds()
	{
		return userIds;
	}
	public void setUserIds(int[] userIds)
	{
		this.userIds = userIds;
	}
	public List<RuleActionNotificationElement> getText()
	{
		return text;
	}
	public void setText(List<RuleActionNotificationElement> text)
	{
		if(text == null)
			text = new ArrayList<RuleActionNotificationElement>();
		this.text = text;
	}
	public boolean getSendEmail()
	{
		return sendEmail;
	}
	public void setSendEmail(boolean value)
	{
		sendEmail = value;
	}
	public boolean getSendSMS()
	{
		return sendSMS;
	}
	public void setSendSMS(boolean value)
	{
		sendSMS = value;
	}
	public NotificationPriority getPriority()
	{
		return priority;
	}
	public void setPriority(NotificationPriority priority)
	{
		this.priority = priority;
	}
	
	public RuleActionUserNotification(int[] userIds, List<RuleActionNotificationElement> text)
	{
		this(userIds, text, NotificationPriority.NORMAL, true, false);
	}
	public RuleActionUserNotification(int[] userIds, List<RuleActionNotificationElement> text, NotificationPriority priority)
	{
		this(userIds, text, priority, true, false);
	}
	public RuleActionUserNotification(int[] userIds, List<RuleActionNotificationElement> text, NotificationPriority priority, boolean sendEmail, boolean sendSMS)
	{
		setUserIds(userIds);
		setText(text);
		setPriority(priority);
		setSendEmail(sendEmail);
		setSendSMS(sendSMS);
	}
}

