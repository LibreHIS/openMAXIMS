package ims.framework.interfaces;

import ims.framework.enumerations.SchedulerJobFrequency;
import ims.framework.utils.DateTime;

public interface IConfiguredScheduledJob 
{
	int getConfiguredScheduledJobID();
	String getConfiguredScheduledJobDescription();
	String getConfiguredScheduledJobName();
	String getConfiguredScheduledJobCronExpression();
	String getConfiguredScheduledJobCronExpressionText();
	boolean isConfiguredScheduledJobCronTrigger();
	SchedulerJobFrequency getConfiguredScheduledJobFrequency();
	DateTime getConfiguredScheduledStartDateTime();
	DateTime getConfiguredScheduledEndDateTime();	
	IQueuedNotification[] getNotificationOnSuccess();
	IQueuedNotification[] getNotificationOnFailure();
	
	IScheduledJob getScheduledJob();
}
