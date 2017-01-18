package ims.domain.http;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.sentillion.sdkweb.webcontextor.WebContextor;
import com.sentillion.sdkweb.webcontextor.WebContextorException;

import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.domain.SessionData;
import ims.domain.admin.AppSession;
import ims.framework.SessionConstants;
import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.IAppRole;
import ims.framework.interfaces.IAppUser;
import ims.framework.utils.DateTime;

public class HttpAppSession extends AppSession
{
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(HttpAppSession.class);
	//private static final int TIMEOUT = 1000 * 60 * 15;
	private transient HttpSession httpSession;
	private String logFileName = null;

	public HttpAppSession(HttpSession session)
	{
		this.httpSession = session;
	}

	public String getUserName()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		if (sessData == null) return null;
		IAppUser user = sessData.user.get();
		if (user == null) return null;
		return user.getUsername();
	}
	
	public String getRealName()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		if (sessData == null) return null;
		IAppUser user = sessData.user.get();
		if (user == null) return null;
		return user.getUserRealName();
	}
	
	public DateTime getStartTime()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		if (sessData == null) return null;
		IAppUser user = sessData.user.get();
		if (user == null) return null;
		return user.getLoginTime();
	}
	
	public String getRoleName()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		if (sessData == null) return null;
		IAppRole role = sessData.role.get();
		if (role == null) return null;
		return role.getName();
	}
	
	public String getCurrentForm()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		if (sessData == null) return null;
		Configuration cfg = sessData.configurationModule.get();
		if (cfg == null)
		{
			//TODO: Need to figure out why this can be null ever
			return null;
		}
		Map map = cfg.getRegisteredForms();
		IAppForm registeredForm = (IAppForm) map.get(sessData.currentFormID.get());
		if (registeredForm != null)
		{
			return registeredForm.getName();
		}
		return null;
	}
	
	public String getSessionId()
	{
		try
		{
			return this.httpSession.getId();
		}
		catch (IllegalStateException e)
		{
			return null;
		}
	}
	
	public long getIdleTime()
	{
		try
		{
			return (new java.util.Date().getTime() - httpSession.getLastAccessedTime());
		}
		catch (IllegalStateException e)
		{
			return 0;
		}
	}
	
	public int getSessionTimeOut()
	{
		try
		{
			return httpSession.getMaxInactiveInterval()/60;
		}
		catch (IllegalStateException e)
		{
			return 0;
		}
	}

	public void setSessionTimeOut(int timeout)
	{
		try
		{
			//Value passed in is minutes. HttpSession expects seconds
			httpSession.setMaxInactiveInterval(timeout * 60);
		}
		catch (IllegalStateException e)
		{
		}
	}
		
	public boolean loggingEnabled()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return false;
		}
		if (sessData == null) return false;
	    Logger logger = sessData.xmlLogger.get();
	    return (logger != null);		
	}
	
	public boolean enableSessionLogging()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return false;
		}
		if (sessData == null) return false;

		if (loggingEnabled()) return true;

		String fileLocation = ConfigFlag.GEN.SESSION_LOG_LOCATION.getValue();
		if (fileLocation.equals("")) return false;
		
		logFileName = "Ims_" + this.getUserName() + "_" + this.getSessionId() + ".log";
		fileLocation += ("/" + logFileName);
		Logger logger = ims.utils.Logging.getXMLLogger(logFileName);
		File f = new File(fileLocation);
		try
		{
			if (!f.exists()) f.createNewFile();
			
			FileAppender fap = new FileAppender(new PatternLayout("%d{ISO8601} - %m\n"), fileLocation);
			logger.addAppender(fap);
			sessData.xmlLogger.set(logger);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void disableSessionLogging()
	{
		SessionData sessData;
		try
		{
			sessData = ((SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA));			
		}
		catch (IllegalStateException e)
		{
			return;
		}
		if (sessData == null) return;

		if (!loggingEnabled()) return;
	    Logger logger = sessData.xmlLogger.get();
	    logger.removeAllAppenders();
	    sessData.xmlLogger.remove();	  
	}

	public String getLogFileContent(int tailSize)
	{
		if (logFileName == null || logFileName.equals(""))
		{
			return "Log File Not Found.";
		}
		try
		{
			byte[] buffer = null;

			String fileLocation = ConfigFlag.GEN.SESSION_LOG_LOCATION.getValue();
			if (fileLocation.equals("")) return "No Session Log file location has been configured.";
			
			fileLocation += ("/" + logFileName);
			File f = new File(fileLocation);
			FileInputStream fi = new FileInputStream(f);
			int len = (int)f.length();

			//tailSize sent in in KB. Need to convert to bytes
			tailSize = tailSize * 1024;
			int bytesToRead = Math.min(len,tailSize);
			if (tailSize <= 0) bytesToRead = len;

			buffer = new byte[bytesToRead];
			fi.skip((long)len-bytesToRead);
			fi.read(buffer);
			fi.close();

			return new String(buffer);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Exception occurred reading log file. " + e.getMessage();
		}		
	}
	
	public String getRemoteHost()
	{
		try
		{
			return (String)httpSession.getAttribute(SessionConstants.REMOTE_HOST);
		}
		catch (IllegalStateException e)
		{
			return null;
		}
	}
	public String getRemoteAddress()
	{
		try
		{
			return (String)httpSession.getAttribute(SessionConstants.REMOTE_ADDR);
		}
		catch (IllegalStateException e)
		{
			return null;
		}
	}

	public void endSession()
	{
		try
		{
			httpSession.invalidate();		
		}
		catch (IllegalStateException e)
		{
			return;
		}
	}

	public String getLogFileName()
	{
		return this.logFileName;
	}

	public String getLogFileLocation()
	{
		return ConfigFlag.GEN.SESSION_LOG_LOCATION.getValue();
	}

	public int getLogFileSize()
	{
		if (logFileName == null || logFileName.equals(""))
		{
			return -1;
		}
		String fileLocation = ConfigFlag.GEN.SESSION_LOG_LOCATION.getValue();
		if (fileLocation.equals("")) return -1;
			
		fileLocation += ("/" + logFileName);
		File f = new File(fileLocation);
		if (!f.exists()) return -1;
		return (int)(f.length()/1024);
	}

	public void joinCcowContext()
	{
		if (ConfigFlag.FW.ENABLE_CCOW.getValue())
		{
			try
			{
				//String cmUrl = getContextManagerUrlFromLocator("localhost", 2116, se.getSession().getId());
	
				//TODO: Find out the host and port number during CNHost init, and set a config flag with the value.
				String cmUrl = "http://" + "localhost:8080/" +  ConfigFlag.APP_CONTEXT_NAME.getValue() + "/CcowCM?JSessionId=" + httpSession.getId();			
				String particUrl = "http://" + "localhost:8080/" + ConfigFlag.APP_CONTEXT_NAME.getValue()+ "/CcowCP?JSessionId=" + httpSession.getId();
				String appName = ConfigFlag.APP_CONTEXT_NAME.getValue();
	
				WebContextor webCtor = new WebContextor();
				String[] ctxBlob = new String[1];
				webCtor.run(ctxBlob, cmUrl, particUrl, appName, "", false);

				httpSession.setAttribute("ccow.contextorBlob",ctxBlob[0]);
			}
			catch (Exception e)
			{
				LocalLogger.error("Could not set up CCOW context participator", e);
			}
		}
	}
	
	public void leaveCcowContext()
	{
		if (ConfigFlag.FW.ENABLE_CCOW.getValue())
		{
			String[] ctxBlob = new String[1];
			ctxBlob[0] = (String) httpSession.getAttribute("ccow.contextorBlob");
			if (ctxBlob[0] != null && ctxBlob[0].length() > 0)
			{
				WebContextor theWebContextor = new WebContextor();
				try
				{
					theWebContextor.stop(ctxBlob);
				}
				catch (WebContextorException e)
				{
					LocalLogger.error("Could not stop CCOW context participator", e);
				}				
			}
		}
	}

}
