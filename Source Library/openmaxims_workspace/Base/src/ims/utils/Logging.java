/*
 * Created on 07-Jan-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.utils;

import ims.configuration.EnvironmentConfig;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

/**
 * @author gcoghlan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Logging 
{
	private static ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();
	private static final String log4jproperties = "/log4j.properties"; 
	
	public static Logger getLogger(Class<?> clazz) 
	{
		Logger logger = Logger.getLogger(clazz);
		logger.setLevel(getLoggingLevel());
		loggers.putIfAbsent(clazz.getName(), logger);
		return logger;
	}
	
	public static Logger getXMLLogger(String logName) 
	{
		Logger logger = Logger.getLogger(logName);
		logger.setLevel(getLoggingLevel());
		loggers.put(logName, logger);
		return logger;
	}
	
	public static Level getLoggingLevel()
	{
		String levStr = EnvironmentConfig.GetLoggingLevel();
		return Level.toLevel(levStr);
	}

	public static void setLoggingLevel(String levStr)
	{
		Level lev = Level.toLevel(levStr);
		for (Logger l : loggers.values())
		{
			l.setLevel(lev);
		}
	}
	
	/**
	 * sets the file name for the RollingFileAppender maxims if it exists.
	 * @param fileName
	 */
	public static void setMaximsAppenderFile(String fileName)
	{
		Properties props = new Properties();
		try
		{
			InputStream istream = Logging.class.getResourceAsStream(log4jproperties);
			props.load(istream);
			istream.close();
		}
		catch (Exception e) {
			System.out.println("Could not read configuration file ["+log4jproperties+"].");
			e.printStackTrace();
			return;
		}

		PropertyConfigurator.configure(props);
		setLoggingLevel(EnvironmentConfig.GetLoggingLevel());

		
		
		if (0=="STDOUT".compareToIgnoreCase(fileName))
		{
			Logger root = Logger.getRootLogger();
			root.removeAppender("maxims");
		}
		else
		{
			Logger root = Logger.getRootLogger();
			root.removeAppender("stdout");
			File logFile = new File(fileName);
			if (!logFile.isAbsolute())
			{
				// if it is not an absolute lets stick it in CATALINA_HOME/logs
				// if set otherwise we are in the current directory
				String TOMCAT_HOME = System.getProperty("catalina.home");
				if (TOMCAT_HOME != null)
					fileName = TOMCAT_HOME + "/logs/" + fileName;
			}
			logFile = new File(fileName); // Get the File again

			// Lets checks it exist and we can write to it
			try
			{
				logFile.createNewFile(); // Create the file if it does not exist
				if (logFile.canWrite())
				{
					Logger l = Logger.getRootLogger();
					RollingFileAppender appndr= (RollingFileAppender) l.getAppender("maxims");
					appndr.setFile(fileName);
					appndr.activateOptions();
				}
			}
			catch (Exception e)
			{
				System.out.println("Problem setting log file");
				e.printStackTrace();
			}
		}


	}

	
// This almost works but there are 2 problems 
// when the file is modified and the webapplication is reloadable 
// 	there is tomcat hang.
// The settings made to the log level and the log file name are overwritten
	
	
//	public static void reloadLog4jProperties()
//	{
//		URL url = Logging.class.getResource("/log4j.properties");
//		PropertyConfigurator.configure(url);
//	}
	// Reload configuration from log4j.properties
	
	
	
	
	
	
	
}
