/*
 * Created on 26-Apr-2005
 *
 */
package ims.domain.impl;

import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.factory.SystemLogFactory;
import ims.framework.SessionConstants;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.ISystemLogProvider;
import ims.framework.utils.DateTime;
import ims.framework.utils.StringUtils;
import ims.configuration.BuildInfo;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;

import org.apache.axis.MessageContext;
import org.apache.axis.session.Session;

/**
 * @author jmacenri
 *
 */
public class DomainWebService 
{
	private boolean loggedIn = false;
	
	public DomainWebService() 
	{
		super();
	}

	private ISystemLogProvider getSystemLogProvider() throws Exception
	{
		SystemLogFactory systemLogFactory = new SystemLogFactory(getDomainSession());
		if(systemLogFactory.hasSystemLogProvider())
			return systemLogFactory.getProvider();
		return null;
	}
	private ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String username, String computer, String message) throws Exception
	{
		ISystemLogProvider provider = getSystemLogProvider();
		if(provider == null)
			return null;		
		
		String source = "";
		BuildInfo buildInfo = Configuration.getBuildInfo();
		
		if(buildInfo != null)
		{
			source = buildInfo.getName();
			source += " version ";
			source += buildInfo.getAppVersion() + " (" + buildInfo.getAppTimestamp() + ")";
		}
		
		String userAgent = "";
		return provider.save(new DateTime(), type, level, ConfigFlag.HOST_NAME.getValue(), username, source, computer, userAgent, getSessionId(), message);
	}			
	public IAppUser getLoggedInUser() throws Exception
    {
		return this.getDomainSession().getUser();		
    }
	private String getSessionId() throws Exception
    {
		SessionData sessData = (SessionData)getDomainSession().getAttribute(SessionConstants.SESSION_DATA);		
		if(sessData == null)
			return null;
    	String id = sessData.sessionId.get();
    	return id == null ? "" : id;
    }
	private DomainSession getDomainSession() throws Exception
	{
		MessageContext ctx = MessageContext.getCurrentContext();
		Session sess = ctx.getSession();
		return ims.domain.axis.DomainSession.getSession(sess);
	}
	
	public String login(String username, String password) throws Exception
	{
		try
		{
			Configuration configuration = Configuration.loadConfiguration();
			MessageContext ctx = MessageContext.getCurrentContext();
			String sessionId = StringUtils.generateUniqueString();
			ctx.setProperty("sessionId", sessionId);
			Session sess = ctx.getSession();
			DomainSession domainSession = ims.domain.axis.DomainSession.getSession(sess);			
			IAppUser user = configuration.login(username, password, domainSession);
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.INFORMATION, username, "", "Login successful");
			if (sess != null)
			{
				sess.set(SessionConstants.USER, user);
				domainSession.setUser(user);
				
				//FIXME: Temporary solution until we fix handleLogin method in CNHost 
				SessionData sessData = (SessionData)getDomainSession().getAttribute(SessionConstants.SESSION_DATA);
				sessData.securityTokenLaunchUsername.set(username);				
				sessData.securityTokenLaunchPassword.set(password);
			}
			else
			{
				throw new Exception("Login method is invalid when session is not being maintained");			
			}
			this.loggedIn = true;
			
			return sessionId;
		}
		catch(Exception ex)
		{
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.WARNING, username, "", "Login failed");
			throw ex;
		}		
	}
		
	public void logout()
	{
		MessageContext ctx = MessageContext.getCurrentContext();
		
		try
		{
			DomainSession domainSession = getDomainSession();					
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.INFORMATION, domainSession.getUser().getUsername(), "", "Logout successful");
		}
		catch(Exception ex)
		{
			// nothing to do
		}
		
		ctx.getSession().invalidate();
		
		this.loggedIn = false;		
	}
	
	public void logout(String sessionToken)
	{
		MessageContext ctx = MessageContext.getCurrentContext();
		
		try
		{
			DomainSession domainSession = getDomainSession();					
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.INFORMATION, domainSession.getUser().getUsername(), "", "Logout successful");
		}
		catch(Exception ex)
		{
			// nothing to do
		}
		
		ctx.getSession().invalidate();
		
		this.loggedIn = false;		
	}

	protected Object getDomainImpl(String className) throws Exception
	{
		try
		{
			Class clazz = Class.forName(className);
			return getDomainImpl(clazz);			
		}
		catch(Exception ex)
		{
			DomainSession domainSession = getDomainSession();				
			String username = "";
			if(domainSession.getUser() != null)
				username = domainSession.getUser().getUsername();
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.ERROR, username, "", ex.toString());
			throw ex;
		}
	}
	
	protected Object getDomainImpl(Class implClass) throws Exception
	{
		try
		{
			MessageContext ctx = MessageContext.getCurrentContext();
			DomainSession domainSession = null;
			Session sess = ctx.getSession();
			if (sess != null)
			{
				domainSession = ims.domain.axis.DomainSession.getSession(ctx.getSession());
			}
			else
			{
				domainSession = new ims.domain.axis.DomainSession();
			}
			
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			return factory.create(implClass, domainSession);		
		}
		catch(Exception ex)
		{
			DomainSession domainSession = getDomainSession();				
			String username = "";
			if(domainSession.getUser() != null)
				username = domainSession.getUser().getUsername();
			createSystemLogEntry(SystemLogType.WEB_SERVICE, SystemLogLevel.ERROR, username, "", ex.toString());
			throw ex;
		}
	}
		
	protected boolean loggedIn()
	{
		return this.loggedIn;
	}	
}
