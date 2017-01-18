package ims.scheduler;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.domain.DomainSession;
import ims.scheduler.IScheduledJobsProvider;

public class ScheduledJobsFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ScheduledJobsFactory.class);
	private String className;
	private DomainSession session;
	private IScheduledJobsProvider scheduledJobsProvider;

	public ScheduledJobsFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getScheduledJobsProviderClassName();
	}
	
	public boolean hasScheduledJobsProvider()
	{
		if(scheduledJobsProvider != null)
			return true;
		
		try
		{
			scheduledJobsProvider = getScheduledJobsProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get the Scheduled Jobs Provider", e);
			return false;
		}
		
		return scheduledJobsProvider != null;
	}
	public IScheduledJobsProvider getScheduledJobsProvider()
	{
		if(scheduledJobsProvider != null)
			return scheduledJobsProvider;
		
		try 
		{
			Class c = Class.forName(className);
			Object instance = c.newInstance(); 
			if (instance instanceof DomainImpl)
			{
				DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
				instance = factory.create(className, session);				
			}
			if(instance instanceof IScheduledJobsProvider)
			{
				scheduledJobsProvider = (IScheduledJobsProvider)instance;
				return scheduledJobsProvider;
			}
			else
				throw new RuntimeException("Invalid Scheluded Jobs Provider Class: " + className + " - It should implement IScheduledJobsProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Scheluded Jobs Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Scheluded Jobs Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Scheluded Jobs Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
