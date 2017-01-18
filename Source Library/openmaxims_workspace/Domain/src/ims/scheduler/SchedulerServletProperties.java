package ims.scheduler;

import java.util.Properties;
import ims.configuration.EnvironmentConfig;

public class SchedulerServletProperties
{
	public static Properties getProperties() throws Exception
	{		
		String driverDB   = EnvironmentConfig.getQuartzDbDriver();
		String urlDB 	  = EnvironmentConfig.getQuartzDbUrl();
		String userDB 	  = EnvironmentConfig.getQuartzDbUsername();
		String passwordDB = EnvironmentConfig.getQuartzDbPassword();
		
		if(driverDB == null)
			throw new Exception("Unknown jdbc driver");
		if(urlDB == null)
			throw new Exception("Unknown connection url");
		if(userDB == null)
			throw new Exception("Unknown connection username");
		if(passwordDB == null)
			passwordDB = "";
		
		Properties props = new Properties();
					
		// Configure Main Scheduler Properties
		props.setProperty("org.quartz.scheduler.instanceName", "QuartzCluster");
		props.setProperty("org.quartz.scheduler.instanceId", "AUTO");
		
		// Configure ThreadPool
		props.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.setProperty("org.quartz.threadPool.threadCount", "1");
		props.setProperty("org.quartz.threadPool.threadPriority", "5");
		props.setProperty("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
		
		//Skip update check
		props.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
		
		// Configure JobStore
		props.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
		props.setProperty("org.quartz.jobStore.isClustered", "true");
		props.setProperty("org.quartz.jobStore.clusterCheckinInterval", "120000");
		props.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		props.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		props.setProperty("org.quartz.jobStore.dataSource", "myDS");
		props.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.setProperty("org.quartz.jobStore.useDBLocks", "true");
		
		if (driverDB.equals("oracle.jdbc.driver.OracleDriver")) 
		{			
			props.setProperty("org.quartz.jobStore.selectWithLockSQL", "select * from {0}locks updlock where lock_name=?");
		}
		else 
		{
			props.setProperty("org.quartz.jobStore.selectWithLockSQL", "select lock_name from qrtz_locks with (updlock,rowlock) where lock_name=?");
		}
		
		// Configure Datasources
		props.setProperty("org.quartz.dataSource.myDS.driver", driverDB);
		props.setProperty("org.quartz.dataSource.myDS.URL", urlDB);
		props.setProperty("org.quartz.dataSource.myDS.user", userDB);
		props.setProperty("org.quartz.dataSource.myDS.password", passwordDB);
		props.setProperty("org.quartz.dataSource.myDS.maxConnections", "5");
		
		return props;
	}
}
