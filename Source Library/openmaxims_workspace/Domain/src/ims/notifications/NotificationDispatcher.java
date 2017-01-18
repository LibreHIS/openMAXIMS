package ims.notifications;

import org.quartz.JobExecutionException;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;

import ims.configuration.ConfigFlag;
import ims.configuration.EnvironmentConfig;
import ims.domain.DomainSession;
import ims.domain.factory.UserFactory;
import ims.framework.enumerations.NotificationDelivery;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.IConfiguredScheduledJob;
import ims.framework.interfaces.INotification;
import ims.framework.interfaces.INotificationsProvider;
import ims.framework.interfaces.IQueuedNotification;
import ims.framework.interfaces.IUserProvider;
import ims.utils.MailSender;
import ims.scheduler.SchedulerJob;
import ims.scheduler.SchedulerJobExecutionStatus;
import ims.scheduler.SchedulerJobExecutionSummary;

public class NotificationDispatcher extends SchedulerJob
{
	private static final org.apache.log4j.Logger	LocalLogger				 = ims.utils.Logging.getLogger(NotificationDispatcher.class);
	
	private void dispatchScreenNotification(IQueuedNotification notification) throws Exception
	{
		getNotificationProvider().createUINotification(notification);		
	}	
	private void dispatchEmailNotification(IQueuedNotification notification) throws Exception
	{		
		String eMailAddress = getEmailAddress(notification);
		if(eMailAddress != null && eMailAddress.trim().length() > 0)
		{								
			new MailSender(ConfigFlag.FW.SMTP_SERVER.getValue(), ConfigFlag.FW.SMTP_PORT.getValue(), ConfigFlag.FW.SMTP_AUTH.getValue(), ConfigFlag.FW.SMTP_SENDER.getValue()).sendMessage(eMailAddress, "IMS MAXIMS System Notification, Priority: " + notification.getINotificationPriority().toString(), notification.getINotificationMessage());
		}		
	}
	private void dispatchPushNotification(IQueuedNotification notification) throws Exception
	{
		ApnsService service = null;
		
		String[] deviceTokens = getDeviceTokens(notification);
		if(deviceTokens != null && deviceTokens.length > 0)
		{				
			service = APNS.newService().withCert(EnvironmentConfig.getBaseUri() + "Certificates/maxims_push_production.p12", "Maxims").withProductionDestination().build();					
			PayloadBuilder payload = APNS.newPayload();
			payload.sound("default");
			String message = notification.getINotificationMessage();
			payload.alertBody(message);				
			payload.badge(getBadgeCount(notification));
			payload.customField("id", String.valueOf(notification.getINotificationId()));
			while(payload.isTooLong() && message.length() > 1)
			{
				message = message.substring(0, message.length() - 1);
				payload.alertBody(message);
			}
			String payloadText = payload.build();				
			
			for(int x = 0; x < deviceTokens.length; x++)
			{						
				try
				{							
					service.push(deviceTokens[x], payloadText);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}				
		}	
	}
	private void dispatchSMSNotification(IQueuedNotification notification) throws Exception
	{
		try
		{
			SMSSenderFactory factory = new SMSSenderFactory(DomainSession.getSession());
			if(factory.hasSMSSenderProvider())
			{
				factory.getSMSSenderProvider().send(notification);
			}
			else
			{
				throw new Exception("No SMS Sender provider available");
			}				
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private String[] getDeviceTokens(INotification notification) throws Exception 
	{
		IAppUser user = getUserProvider().getAppUser(notification.getINotificationUserId());
		if(user == null)
			return new String[0];
		
		return user.getNotificationMobileDeviceTokenIds();
	}
	private String getEmailAddress(INotification notification) throws Exception 
	{
		IAppUser user = getUserProvider().getAppUser(notification.getINotificationUserId());
		if(user == null)
			return "";
		
		return user.getNotificationEmailAddress();
	}
	private int getBadgeCount(INotification notification) throws Exception  
	{
		return getNotificationProvider().getUnseenNotificationsCount(notification.getINotificationUserId());		
	}
	private INotificationsProvider getNotificationProvider() throws Exception
	{
		INotificationsProvider provider = new NotificationsFactory(DomainSession.getSession()).getNotificationsProvider();
		if(provider == null)
			throw new Exception("Notification provider is unavailable");
		return provider;
	}
	private static IUserProvider getUserProvider() throws Exception
	{
		IUserProvider provider = new UserFactory(DomainSession.getSession()).getUserProvider();
		if(provider == null)
			throw new Exception("User provider is unavailable");	
		return provider;
	}

	@Override
	protected boolean hasConfiguredScheduledJobInstance()
	{
		return false;
	}
	
	@Override
	public void cleanUpSettings(IConfiguredScheduledJob job) throws Exception 
	{
	}

	@Override
	public SchedulerJobExecutionSummary doExecute() throws JobExecutionException 
	{			
		try		
		{
			int jobId = getJobId();
			LocalLogger.info("Executing notification dispatch for job " + jobId);
			IQueuedNotification notification = getNotificationProvider().getQueuedNotification(jobId);
			if(notification == null)
			{
				LocalLogger.info("Queued notification with id " + jobId + " not found");
				return new SchedulerJobExecutionSummary(SchedulerJobExecutionStatus.FAILED, "Queued notification with id " + jobId + " not found");
			}
			
			if(notification.getIQueuedNotificationDelivery() == NotificationDelivery.UI)
			{
				LocalLogger.info("Executing screen notification dispatch for queued notification for job " + jobId);
				dispatchScreenNotification(notification);
				LocalLogger.info("Execution finished for screen notification dispatch for queued notification for job " + jobId);
			}
			else if(notification.getIQueuedNotificationDelivery() == NotificationDelivery.EMAIL)
			{
				LocalLogger.info("Executing email notification dispatch for queued notification for job " + jobId);
				dispatchEmailNotification(notification);
				LocalLogger.info("Execution finished for email notification dispatch for queued notification for job " + jobId);
			}
			else if(notification.getIQueuedNotificationDelivery() == NotificationDelivery.PUSH)
			{
				LocalLogger.info("Executing push notification dispatch for queued notification for job " + jobId);
				dispatchPushNotification(notification);
				LocalLogger.info("Execution finished for push notification dispatch for queued notification for job " + jobId);
			}
			else if(notification.getIQueuedNotificationDelivery() == NotificationDelivery.SMS)
			{
				LocalLogger.info("Executing sms dispatch for queued notification for job " + jobId);
				dispatchSMSNotification(notification);
				LocalLogger.info("Execution finished for sms dispatch for queued notification for job " + jobId);
			}
					
			LocalLogger.info("Removing queued notification for job " + jobId);
			getNotificationProvider().removeQueuedNotification(notification);
			
			return new SchedulerJobExecutionSummary(SchedulerJobExecutionStatus.SUCCEEDED);
		}
		catch(Exception e)
		{
			LocalLogger.info(e);
			e.printStackTrace();
			return new SchedulerJobExecutionSummary(SchedulerJobExecutionStatus.FAILED, e.toString());
		}
	}
}
