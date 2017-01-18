package ims.scheduler;

import ims.configuration.ConfigFlag;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.factory.UserFactory;
import ims.framework.enumerations.NotificationDelivery;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.IConfiguredScheduledJob;
import ims.framework.interfaces.IQueuedNotification;
import ims.framework.interfaces.ISchedulerJob;
import ims.framework.utils.DateTime;
import ims.notifications.NotificationEngine;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

public abstract class SchedulerJob implements StatefulJob, ISchedulerJob
{	
	private static final long serialVersionUID = 1L;
	public static final String KEY_JOB_ID = "JobId";
	private SchedulerJobExecutionSummary executionSummary;
	private SchedulerJobExecutionTrace executionTrace;
	private JobExecutionContext context;
	private DomainFactoryBridge domainFactory;	
	private ims.scheduler.DomainSession domainSession;	
	private IConfiguredScheduledJob job;
	private DateTime startDate;
	private DateTime endDate;
		
	private ims.scheduler.DomainSession getDomainSession() throws Exception
	{
		if(domainSession == null)
			domainSession = new DomainSession();
		return domainSession;
	}	
	protected DomainFactoryBridge getDomainFactory()
	{
		try
		{
			if(domainFactory == null)
				domainFactory = new DomainFactoryBridge(getDomainSession());
			return domainFactory;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	protected JobExecutionContext getContext()
	{
		return context;
	}	
	protected IConfiguredScheduledJob getConfiguredJob()
	{
		return job;
	}
	protected boolean hasConfiguredScheduledJobInstance()
	{
		return true;
	}
	protected int getJobId()
	{
		return context.getJobDetail().getJobDataMap().getInt(KEY_JOB_ID);
	}
	
	public final void execute(JobExecutionContext context) throws JobExecutionException
	{	
		if(context == null)
			throw new JobExecutionException("Invalid job execution context");
		
		this.context = context;
		
		if(hasConfiguredScheduledJobInstance())
		{
			try
			{
				// Finding the configured job
				int jobId = getJobId();
				job = getProvider().getConfiguredScheduledJob(jobId);
				if(job == null)
				{
					ims.configuration.JNDI.getTaskSchedulerServlet().delete(jobId);
					throw new Exception("Scheduled job was unable to find the configured job Id. This configured job has been removed from Quartz.");
				}
			}
			catch (Exception e)
			{
				logException(e);
				throw new JobExecutionException(e);
			}
		}
		
		logStart();
		startDate = new DateTime();
						
		// Executing the job
		try
		{		
			executionTrace = new SchedulerJobExecutionTrace();
			executionSummary = doExecute();			
			if(executionSummary == null)
				throw new JobExecutionException("Scheduled task finished with unknown execution state.");
			commitCurrentTransaction();
			logExecution();
			fireSuccessNotifications();
		}
		catch(Exception e)
		{				
			logException(e);			
			executionSummary = new SchedulerJobExecutionSummary(SchedulerJobExecutionStatus.FAILED, e.toString());
			fireFailureNotifications();
			throw new JobExecutionException(e);
		}			
		finally
		{			
			if(hasConfiguredScheduledJobInstance())
			{
				endDate = new DateTime();
				reportExecutionSummary();
			}
			// Closing the domain factory
			//  closeDomainFactory();							
		}
	}
	
	public abstract SchedulerJobExecutionSummary doExecute() throws JobExecutionException;
	public abstract void cleanUpSettings(IConfiguredScheduledJob job) throws Exception;
	
	protected final void trace(String message)
	{
		System.out.println(message);
		if(executionTrace != null)
		{
			executionTrace.add(message);			
		}
	}
	protected final void debug(String message)
	{
		if(ConfigFlag.GEN.RELEASE_MODE.getValue() && executionTrace != null)
		{
			System.out.println(message);
			executionTrace.add(message);
		}
	}	
	
	public final DateTime getJobStartDateTime()
	{
		return startDate;
	}
	public final SchedulerJobExecutionSummary getExecutionSummary()
	{
		return executionSummary;
	}
	
	public final SchedulerJobExecutionTrace getExecutionTrace()
	{
		return executionTrace;
	}
	
	protected String getJobDetailsText()
	{
		if(job == null)
		{
			if(context == null)
				return "Unknown scheduled job";
			return context.getJobDetail().getDescription() + " (Id: " + getJobId() + ")";
		}
		if(job.getConfiguredScheduledJobDescription() != null)
			return job.getConfiguredScheduledJobDescription();
		return "Scheduled job, Id: " + job.getConfiguredScheduledJobID();
	}
	
	private void logStart()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("The scheduled job '");
		sb.append(getJobDetailsText());
		sb.append("' was started");
		createSystemLogEntry(SystemLogLevel.INFORMATION, sb.toString());
	}	
	private void logExecution()
	{
		SystemLogLevel logLevel = SystemLogLevel.INFORMATION;
		if(executionSummary.getStatus() == SchedulerJobExecutionStatus.FAILED)
			logLevel = SystemLogLevel.WARNING;
		
		StringBuilder sb = new StringBuilder();
		sb.append("The scheduled job '");
		sb.append(getJobDetailsText());
		sb.append("' ");
		sb.append(executionSummary.getStatus().toString().toLowerCase());
		
		if(executionSummary.getStatus() == SchedulerJobExecutionStatus.FAILED && executionSummary.getMessage() != null && executionSummary.getMessage().trim().length() > 0)
		{
			sb.append(" with error: ");
			sb.append(executionSummary.getMessage());
		}
		createSystemLogEntry(logLevel, sb.toString());				
	}	
	private void logException(Exception ex) 
	{
		StringBuilder sb = new StringBuilder();
		sb = new StringBuilder();
		sb.append("The scheduled job '");
		sb.append(getJobDetailsText());
		sb.append("' failed with the error: ");
		sb.append(ex.toString());
		
		createSystemLogEntry(SystemLogLevel.FATALERROR, sb.toString());
	}	
	private boolean reportExecutionSummary() 
	{
		if(executionSummary == null)
			return false;
		
		try 
		{			
			IScheduledJobsProvider provider = getProvider();
			if(provider != null)
			{
				executionSummary.setStartDateTime(startDate);
				executionSummary.setEndDateTime(endDate);
				provider.saveExecutionSummaryAndTrace(job, executionSummary, executionTrace);
				return true;
			}
		} 
		catch (Exception e) 
		{
			logException(e);
		}	
		
		return false;
	}		
	private IScheduledJobsProvider getProvider() throws Exception
	{
		return new ScheduledJobsFactory(ims.domain.DomainSession.getSession()).getScheduledJobsProvider();
	}	
	private void createSystemLogEntry(SystemLogLevel logLevel, String message)
	{		
		try
		{
			ims.domain.DomainSession.getSession().createSystemLogEntry(SystemLogType.QUARTZ_JOB, logLevel, message);
		}
		catch(Exception e)
		{	
			e.printStackTrace();
		}
	}
	private void commitCurrentTransaction() throws StaleObjectException
	{
		if(domainFactory != null)
		{
			domainFactory.commitTransaction();
		}
	}	
	private void fireSuccessNotifications() 
	{
		if(hasConfiguredScheduledJobInstance() && job != null && job.getNotificationOnSuccess() != null)
		{
			fireNotifications(job.getNotificationOnSuccess());			
		}
	}	
	private void fireFailureNotifications()
	{
		if(hasConfiguredScheduledJobInstance() && job != null && job.getNotificationOnFailure() != null)
		{
			fireNotifications(job.getNotificationOnFailure());			
		}
	}
	private void fireNotifications(IQueuedNotification[] notifications) 
	{
		for(int x = 0; x < notifications.length; x++)
		{
			IQueuedNotification notification = notifications[x];
			IAppUser user = getAppUser(notification.getINotificationUserId());
			if(user != null)
			{
				try
				{
					NotificationEngine.getInstance().addNotification(user, notification.getINotificationPriority(), notification.getINotificationMessage(), getJobDetailsText(), new NotificationDelivery[] { notification.getIQueuedNotificationDelivery() });
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}		
	}	
	private IAppUser getAppUser(int notificationUserId)
	{
		try
		{
			return new UserFactory(ims.domain.DomainSession.getSession()).getUserProvider().getAppUser(notificationUserId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/*private void closeDomainFactory()
	{
		if(domainFactory != null)
		{
			try 
			{
				domainFactory.close();
			} 
			catch (StaleObjectException e) 
			{
				e.printStackTrace();
			}
		}
	}*/
}
