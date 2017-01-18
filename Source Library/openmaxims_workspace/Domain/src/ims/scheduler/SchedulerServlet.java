package ims.scheduler;

import ims.configuration.EnvironmentConfig;
import ims.framework.enumerations.SchedulerJobFrequency;
import ims.framework.enumerations.SchedulerJobStatus;
import ims.framework.interfaces.IConfiguredScheduledJob;
import ims.framework.interfaces.IQueuedNotification;
import ims.framework.interfaces.ISchedulerJob;
import ims.framework.interfaces.ISchedulerServlet;
import ims.framework.utils.DateTime;
import ims.notifications.NotificationDispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
 
public class SchedulerServlet extends HttpServlet implements ISchedulerServlet
{ 
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(SchedulerServlet.class);
	private static final String	IMS_CRON_TRIGGER_NAME_PREFIX = "ImsCronTrigger_";
	private static final String	IMS_SIMPLE_TRIGGER_NAME_PREFIX = "ImsSimpleTrigger_";
	private static final String	IMS_SIMPLE_NOTIFICATION_TRIGGER_NAME_PREFIX = "ImsSimpleNotificationTrigger_";
	private static final String	IMS_JOB_NAME_PREFIX	= "ImsJob_";
	private static final String	IMS_NOTIFICATION_JOB_NAME_PREFIX = "ImsNotificationJob_";
	private static final long serialVersionUID = 1L;
	private static final String BAD_PARAMETERS = "Bad parameters. Usage: /QuartzInitializer?action=start|stop";
    public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
    private boolean performShutdown = true;
    private Scheduler scheduler = null;
    
    public void init(ServletConfig cfg) throws javax.servlet.ServletException 
    {
    	LocalLogger.warn("Quartz Scheduler: Start loading");
    	LocalLogger.warn("Quartz Scheduler: Check environment config ScheduledJobs");
    	
    	if (EnvironmentConfig.getScheduledJobs() != null) {
			LocalLogger.warn("Quartz Scheduler: Environment config ScheduledJobs is set to " + Boolean.valueOf(EnvironmentConfig.getScheduledJobs()).toString());
    		if (Boolean.FALSE.equals(EnvironmentConfig.getScheduledJobs())) {
    			LocalLogger.warn("Quartz Scheduler: Servlet will not be loaded");
    			LocalLogger.warn("Quartz Scheduler: Exit");
    			return;
    		} else {
    			LocalLogger.warn("Quartz Scheduler: Proceed to loading");
    		}
		}
    	else {
    		LocalLogger.warn("Quartz Scheduler: Environment config ScheduledJobs is not set");
    		LocalLogger.warn("Quartz Scheduler: Proceed to loading");
    	}
    	
        super.init(cfg);

        log("Quartz Initializer Servlet loaded, initializing Scheduler...");

        StdSchedulerFactory factory;
        try 
        {
            String shutdownPref = cfg.getInitParameter("shutdown-on-unload");

            if (shutdownPref != null) 
            {
                performShutdown = Boolean.valueOf(shutdownPref).booleanValue();
            }
            
            log("Loading Properties...");
            Properties properties = SchedulerServletProperties.getProperties();
			if( properties == null)
			{	
				log("No Properties found...");
				factory = new StdSchedulerFactory();
			}
			else
			{
				log("Properties found...");

				log("Driver: " + properties.getProperty("org.quartz.dataSource.myDS.driver"));
				log("URL: " + properties.getProperty("org.quartz.dataSource.myDS.URL"));
				log("User: " + properties.getProperty("org.quartz.dataSource.myDS.user"));
				log("Password: " + properties.getProperty("org.quartz.dataSource.myDS.password"));
				
				factory = new StdSchedulerFactory(properties);
			}
            
            // Always want to get the scheduler, even if it isn't starting, 
            // to make sure it is both initialized and registered.
            scheduler = factory.getScheduler();
            
            // Should the Scheduler being started now or later
            String startOnLoad = cfg.getInitParameter("start-scheduler-on-load");

            int startDelay = 0;
            String startDelayS = cfg.getInitParameter("start-delay-seconds");
            try 
            {
                if(startDelayS != null && startDelayS.trim().length() > 0)
                {
                    startDelay = Integer.parseInt(startDelayS);
                }
            } 
            catch(Exception e) 
            {
                log("Cannot parse value of 'start-delay	-seconds' to an integer: " + startDelayS + ", defaulting to 5 seconds.", e);
                startDelay = 5;
            }
            
            /*
             * If the "start-scheduler-on-load" init-parameter is not specified,
             * the scheduler will be started. This is to maintain backwards
             * compatability.
             */
            if (startOnLoad == null || (Boolean.valueOf(startOnLoad).booleanValue())) 
            {
                if(startDelay <= 0) 
                {
                    // Start now
                    scheduler.start();
                    log("Scheduler has been started...");
                }
                else 
                {
                    // Start delayed
                    scheduler.startDelayed(startDelay);
                    log("Scheduler will start in " + startDelay + " seconds.");
                }
            } 
            else 
            {
                log("Scheduler has not been started. Use scheduler.start()");
            }

            String factoryKey = cfg.getInitParameter("servlet-context-factory-key");
            if (factoryKey == null) 
            {
                factoryKey = QUARTZ_FACTORY_KEY;
            }
            
            log("Storing the Quartz Scheduler Factory in the servlet context at key: " + factoryKey);
            cfg.getServletContext().setAttribute(factoryKey, factory);
        	ims.configuration.JNDI.setTaskSchedulerServlet(this);
        } 
        catch (Exception e) 
        {
            log("Quartz Scheduler failed to initialize: " + e.toString());
            throw new ServletException(e);
        }                
    }
    private String startQuartz()
	{
		log("Quartz Initializer Servlet loaded, initializing Scheduler...");

		ServletContext ctx = this.getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory)ctx.getAttribute(QUARTZ_FACTORY_KEY);
		
		try
		{
			if(factory == null)
				factory = new StdSchedulerFactory();
			
			scheduler = factory.getScheduler();
			scheduler.start();
			log("Scheduler has been started...");

			log("Storing the Quartz Scheduler Factory in the servlet context at key: " + QUARTZ_FACTORY_KEY);
			this.getServletContext().setAttribute(QUARTZ_FACTORY_KEY, factory);
		}
		catch (SchedulerException e)
		{
			String err = "Quartz Scheduler failed to initialize: " + e.toString();
			log(err);
			return err;
		}
		
		return "OK";
	}
	private String stopQuartz()
	{
		try
		{
			ServletContext ctx = this.getServletContext();
			StdSchedulerFactory factory = (StdSchedulerFactory)ctx.getAttribute(QUARTZ_FACTORY_KEY);
			
			if(factory != null)
			{ 
				Iterator schedulerIter = factory.getAllSchedulers().iterator();
				Scheduler scheduler = null;
				while (schedulerIter.hasNext())
				{
					scheduler = (Scheduler) schedulerIter.next();
					if (scheduler != null)
					{
						scheduler.shutdown();
					}
				}
			}
		}
		catch (Exception e)
		{
			String err = "Quartz Scheduler failed to shutdown cleanly: " + e.toString();
			log(err);
			e.printStackTrace();
			
			return err;
		}

		log("Quartz Scheduler successful shutdown.");
		return "OK";
	}
	private String reStartQuartz()
	{
		String result = "";
		
		stopQuartz();
		result = startQuartz();

		return result;
	}
    public void destroy() 
    {
        if (!performShutdown) 
        {
            return;
        }

        try 
        {
            if (scheduler != null) 
            {
                scheduler.shutdown();
            }
        } 
        catch (Exception e) 
        {
            log("Quartz Scheduler failed to shutdown cleanly: " + e.toString());
            e.printStackTrace();
        }

        log("Quartz Scheduler successful shutdown.");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	doGet(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String[] val = request.getParameterValues("action");		
		if(val == null || val.length == 0)
		{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, BAD_PARAMETERS);
			return;
		}
		
		String action = val[0];		
		if(action.equalsIgnoreCase("ping"))
		{
			PrintWriter out = response.getWriter();
			out.print("OK");
			return;
		}
		else if(action.equalsIgnoreCase("start"))
		{
			PrintWriter out = response.getWriter();
			String status = startQuartz();
			
			if(status.equals("OK"))
			{
				out.print(status);
				return;
			}
			else
			{
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, status);
				return;
			}
		}
		else if(action.equalsIgnoreCase("stop"))
		{
			PrintWriter out = response.getWriter();
			String status = stopQuartz();

			if(status.equals("OK"))
			{
				out.print(status);
				return;
			}
			else
			{
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, status);
				return;
			}
		}		
	}    
	private Scheduler getCurrentScheduler() throws SchedulerException 
	{
		ServletContext ctx = this.getServletContext();		
		StdSchedulerFactory factory = (StdSchedulerFactory)ctx.getAttribute(QUARTZ_FACTORY_KEY);
		
		if(factory == null || factory.getAllSchedulers() == null || factory.getAllSchedulers().iterator() == null)
			reStartQuartz();
		
		factory = (StdSchedulerFactory)ctx.getAttribute(QUARTZ_FACTORY_KEY);
		
		Iterator schedulerIter = factory.getAllSchedulers().iterator();
		
		while (schedulerIter.hasNext())
		{
			return (Scheduler) schedulerIter.next();
		}
		
		return null;		
	}
    public IConfiguredScheduledJob getConfiguredScheduleJobById(int id) throws Exception
    {
    	return new ScheduledJobsFactory(ims.domain.DomainSession.getSession()).getScheduledJobsProvider().getConfiguredScheduledJob(id);
    }    
	public void add(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		IConfiguredScheduledJob job = getConfiguredScheduleJobById(id);
		
		String className = "ims.jobs." + job.getScheduledJob().getScheduledJobClassName().replace(".java", "");
		Class clazz = Class.forName(className);		
		if (clazz != null)
		{			
			if(SchedulerJob.class.isAssignableFrom(clazz))
			{
				JobDetail jobDetail = new JobDetail(IMS_JOB_NAME_PREFIX + String.valueOf(id), Scheduler.DEFAULT_GROUP, clazz);
				jobDetail.getJobDataMap().put(SchedulerJob.KEY_JOB_ID, id);
				jobDetail.setDescription(job.getConfiguredScheduledJobDescription());
				jobDetail.setRequestsRecovery(true);
				
				SchedulerTrigger imsTrigger = new SchedulerTrigger(job.getConfiguredScheduledJobCronExpression());					
				CronTrigger trigger = null;
				SimpleTrigger simpleTrigger = null;
					
				if (imsTrigger.getFrequency() == SchedulerJobFrequency.DAILY)
				{											
					simpleTrigger = new SimpleTrigger(IMS_SIMPLE_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ? imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, SimpleTrigger.REPEAT_INDEFINITELY, Long.parseLong(imsTrigger.getRepeatInterval() != null ?imsTrigger.getRepeatInterval().toString() : "0"));						
					simpleTrigger.setDescription(job.getConfiguredScheduledJobDescription());
					simpleTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
				}
				else if (imsTrigger.getFrequency() == SchedulerJobFrequency.ONCE)
				{	
					simpleTrigger = new SimpleTrigger(IMS_SIMPLE_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ?  imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, SimpleTrigger.REPEAT_INDEFINITELY, 36500 * 24L * 60L * 60L * 1000L);						
					simpleTrigger.setDescription(job.getConfiguredScheduledJobDescription());
					simpleTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);					
				}
				else
				{
					trigger = new CronTrigger(IMS_CRON_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ?  imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, imsTrigger.getCronString());						
					trigger.setDescription(job.getConfiguredScheduledJobDescription());
					trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
				}
											
				scheduler.deleteJob(IMS_JOB_NAME_PREFIX + String.valueOf(id), null);
				scheduler.scheduleJob(jobDetail, trigger != null ?  trigger : simpleTrigger);
			}
			else
			{
				throw new Exception("Class '" + className + "' is not a SchedulerJob class");
			}
		}
		else
			throw new Exception("Class '" + className + "' not found");
	}
	public void update(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		IConfiguredScheduledJob job = getConfiguredScheduleJobById(id);
		
		String className = "ims.jobs." + job.getScheduledJob().getScheduledJobClassName().replace(".java", "");
		Class clazz = Class.forName(className);		
		if (clazz != null)
		{			
			if(SchedulerJob.class.isAssignableFrom(clazz))
			{
				JobDetail jobDetail = new JobDetail(IMS_JOB_NAME_PREFIX + String.valueOf(id), Scheduler.DEFAULT_GROUP, clazz);
				jobDetail.getJobDataMap().put(SchedulerJob.KEY_JOB_ID, id);
				jobDetail.setDescription(job.getConfiguredScheduledJobDescription());
				jobDetail.setRequestsRecovery(true);
				
				SchedulerTrigger imsTrigger = new SchedulerTrigger(job.getConfiguredScheduledJobCronExpression());					
				CronTrigger trigger = null;
				SimpleTrigger simpleTrigger = null;
					
				if (imsTrigger.getFrequency() == SchedulerJobFrequency.DAILY)
				{											
					simpleTrigger = new SimpleTrigger(IMS_SIMPLE_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ? imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, SimpleTrigger.REPEAT_INDEFINITELY, Long.parseLong(imsTrigger.getRepeatInterval() != null ?imsTrigger.getRepeatInterval().toString() : "0"));						
					simpleTrigger.setDescription(job.getConfiguredScheduledJobDescription());
					simpleTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
				}
				else if (imsTrigger.getFrequency() == SchedulerJobFrequency.ONCE)
				{	
					simpleTrigger = new SimpleTrigger(IMS_SIMPLE_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ?  imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, SimpleTrigger.REPEAT_INDEFINITELY, 36500 * 24L * 60L * 60L * 1000L);						
					simpleTrigger.setDescription(job.getConfiguredScheduledJobDescription());
					simpleTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);					
				}
				else
				{
					trigger = new CronTrigger(IMS_CRON_TRIGGER_NAME_PREFIX + String.valueOf(id), null, IMS_JOB_NAME_PREFIX + String.valueOf(id), null, imsTrigger.getStartDateTime() != null ?  imsTrigger.getStartDateTime().getJavaDate() : null, imsTrigger.getStopDateTime() != null ? imsTrigger.getStopDateTime().getJavaDate() : null, imsTrigger.getCronString());						
					trigger.setDescription(job.getConfiguredScheduledJobDescription());
					trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
				}
											
				scheduler.addJob(jobDetail, true);				
			}
			else
			{
				throw new Exception("Class '" + className + "' is not a SchedulerJob class");
			}
		}
		else
			throw new Exception("Class '" + className + "' not found");
	}
	public void delete(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		myScheduler.deleteJob(IMS_JOB_NAME_PREFIX + String.valueOf(id), null);		
	}	
	public boolean exist(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return false;
		
		IConfiguredScheduledJob job = getConfiguredScheduleJobById(id);
		if(job == null)
			return false;		
		
		String jobs[] = myScheduler.getJobNames(Scheduler.DEFAULT_GROUP);
		for(int x = 0; x < jobs.length; x++)
		{
			if(jobs[x].equals(IMS_JOB_NAME_PREFIX + String.valueOf(id)))
				return true;
		}
		
		return false;
	}
	public void pause(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		myScheduler.pauseJob(IMS_JOB_NAME_PREFIX + String.valueOf(id), null);
	}
	public void resume(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		myScheduler.resumeJob(IMS_JOB_NAME_PREFIX + String.valueOf(id), null);
	}
	public void run(int id) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		myScheduler.triggerJob(IMS_JOB_NAME_PREFIX + String.valueOf(id), null);
	}
	public ims.framework.enumerations.SchedulerJobStatus getStatus(int jobId) throws Exception
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return SchedulerJobStatus.UNKNOWN;
		
		int state = -1;
		
		Trigger[] triggers = myScheduler.getTriggersOfJob(IMS_JOB_NAME_PREFIX + String.valueOf(jobId),Scheduler.DEFAULT_GROUP);
		
		if (triggers.length == 0)
			throw new Exception("No Trigger found for jobId = " + jobId);
		
		
		if (triggers[0] instanceof SimpleTrigger)
		{
			state = myScheduler.getTriggerState(IMS_SIMPLE_TRIGGER_NAME_PREFIX + String.valueOf(jobId), null);
		}
		else if (triggers[0] instanceof CronTrigger)
		{
			state = myScheduler.getTriggerState(IMS_CRON_TRIGGER_NAME_PREFIX + String.valueOf(jobId), null);
		}		
						
		switch(state) 
		{ 
			case Trigger.STATE_BLOCKED: 
				return SchedulerJobStatus.BLOCKED;
			case Trigger.STATE_COMPLETE: 
				return SchedulerJobStatus.COMPLETE;
			case Trigger.STATE_ERROR: 
				return SchedulerJobStatus.ERROR; 
			case Trigger.STATE_NONE: 
				return SchedulerJobStatus.UNKNOWN;
			case Trigger.STATE_NORMAL: 
				return SchedulerJobStatus.IDLE;
			case Trigger.STATE_PAUSED: 
				return SchedulerJobStatus.PAUSED;			
			default:
				return SchedulerJobStatus.UNKNOWN;
		} 
	}
	public void addNotification(IQueuedNotification queuedNotification, DateTime dateTime) throws Exception 
	{
		Scheduler myScheduler = getCurrentScheduler();		
		if (myScheduler == null)
			return;
		
		String jobId = IMS_NOTIFICATION_JOB_NAME_PREFIX + String.valueOf(queuedNotification.getINotificationId());
		String triggerId = IMS_SIMPLE_NOTIFICATION_TRIGGER_NAME_PREFIX + String.valueOf(queuedNotification.getINotificationId());
		String jobDescription = queuedNotification.getIQueuedNotificationDelivery().toString();
		
		JobDetail jobDetail = new JobDetail(jobId, Scheduler.DEFAULT_GROUP, NotificationDispatcher.class);
		jobDetail.getJobDataMap().put(SchedulerJob.KEY_JOB_ID, queuedNotification.getINotificationId());
		jobDetail.setDescription(jobDescription);
		jobDetail.setRequestsRecovery(true);
					
		if(dateTime.isLessThan(new DateTime()))
			dateTime = new DateTime();
		
		SimpleTrigger simpleTrigger = new SimpleTrigger(triggerId, null, jobId, null, dateTime.getJavaDate(), null, 0, 0L);						
		simpleTrigger.setDescription(jobDescription);
		simpleTrigger.setVolatility(true);
		simpleTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
		
		scheduler.scheduleJob(jobDetail, simpleTrigger);
	}
	public ISchedulerJob getJob(int jobId)
	{
		Scheduler myScheduler;
		try 
		{
			myScheduler = getCurrentScheduler();
			List executingJobs = myScheduler.getCurrentlyExecutingJobs();
			for (Object executingJob : executingJobs) 
			{
				if (executingJob instanceof JobExecutionContext)
				{
					if (((JobExecutionContext)executingJob).getJobDetail().getName().equals(IMS_JOB_NAME_PREFIX + String.valueOf(jobId)))
					{
						if (((JobExecutionContext)executingJob).getJobInstance() instanceof ISchedulerJob)
						{
							return (ISchedulerJob)((JobExecutionContext)executingJob).getJobInstance();
						}						
					}
				}
			}		
		} 
		catch (SchedulerException e) 
		{
			log("SchedulerException: " + e.getMessage());            
			e.printStackTrace();
		}		
		
		return null;
	}
}
