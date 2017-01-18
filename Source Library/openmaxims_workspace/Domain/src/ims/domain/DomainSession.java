/*
 * Created on 15-Jan-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain;

import ims.configuration.BuildInfo;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.factory.SystemLogFactory;
import ims.framework.SessionConstants;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.ISystemLogProvider;
import ims.framework.utils.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * Holds all session data required to be maintained in the domain layer.
 *  
 * @author gcoghlan
 *
 */
public class DomainSession implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(DomainSession.class);
	
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private IAppUser user;	
	
	public boolean queryCappingDisabled()
	{
		return false;
	}
		
	public static ims.domain.DomainSession getSession() throws Exception
	{
		ims.domain.DomainSession domSess = new ims.domain.DomainSession();
		
		SessionData sessData = new SessionData();
		sessData.domainSession.set(domSess); // wdev-4752
		sessData.configurationModule.set(Configuration.loadConfiguration());
		domSess.setAttribute(SessionConstants.SESSION_DATA, sessData);
		return domSess;
	}

	/**
	 * Create the DomainSession corresponding to the HttpSession.
	 * There must be a valid User object in the HttpSession.
	 * @param httpSession
	 * @return the DomainSession that is in the httpSession. This will be added if
	 * one did not already exist before this method is called.
	 */	
	public static ims.domain.DomainSession getSession(HttpSession httpSession) 
	{
	    SessionData sessData = (SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA);
	    
		try
		{
			sessData.configurationModule.set(Configuration.loadConfiguration(httpSession));
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}

	    ims.domain.DomainSession domainSession = sessData.domainSession.get();
		if (null == domainSession)
		{		
			domainSession = new ims.domain.DomainSession();
			
			IAppUser user = sessData.user.get();
			domainSession.setUser(user);
			domainSession.setAttribute(SessionConstants.APPLICATION_CONTEXT, httpSession.getServletContext().getServletContextName());
			domainSession.setAttribute(SessionConstants.SESSION_DATA, sessData);
			
			if (LOG.isDebugEnabled())
				LOG.debug("getSession has set all domainSession Atts, now setting httpSession attribute");			
			sessData.domainSession.set(domainSession);			
		}
		if (LOG.isDebugEnabled())
			LOG.debug("getSession exit after setting httpSession atts");

		return domainSession;		
	}

	public DomainSession() {
		if (LOG.isInfoEnabled())
			LOG.info("new DomainSession");
	}
	
	public DomainSession(IAppUser user) {
		if (LOG.isDebugEnabled())
			LOG.debug("new DomainSession(user="+user+")");
		this.user = user;
	}
	
	public Object getAttribute(String name)
	{
		if (LOG.isDebugEnabled()) {
			LOG.debug("getAttribute(name:"+name+")");
		}
		return this.attributes.get(name);	
	}
	
	public void setAttribute(String name, Object value)
	{
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAttribute(name:"+name+", value:"+value+")");
		}
		this.attributes.put(name, value);
	}
	
	public IAppUser getUser() {
		return this.user;
	}
	
	public void setUser(IAppUser user) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setUser(User:"+user+")");
		}
		this.user = user;
	}
	
	public void showMessage(String value)
	{
		SessionData sessData = (SessionData)getAttribute(SessionConstants.SESSION_DATA);
		if (sessData == null)
			return;
		ArrayList<String> messages = sessData.domainMessageBox.get();
		if (messages == null) 
		{
			messages = new ArrayList<String>();			
			sessData.domainMessageBox.set(messages);
		}
        value = value.replace('\"', '\'');
        messages.add(value);
	}

	public void clearMessages()
	{
		SessionData sessData = (SessionData)getAttribute(SessionConstants.SESSION_DATA);
		if (sessData == null)
			return;
		ArrayList messages = sessData.domainMessageBox.get();
		if (messages != null) 
		{
			messages.clear();		
		}
	}

	public void removeAttribute(String name)
	{
		if (LOG.isDebugEnabled()) 
		{
			LOG.debug("removeAttribute(name:"+name+")");
		}
		this.attributes.remove(name);
	}

	public Iterator<String> getAttributeNames()
	{
		return this.attributes.keySet().iterator();
	}

//	public Integer getCurrentPatientId()
//	{
//		return this.patientId;
//	}
//	
//	public void setCurrentPatientId(Integer patientId)
//	{
//		this.patientId = patientId;
//	}
//	
//	public Integer getCurrentClinicalContactId()
//	{
//		return this.clinicalContactId;
//	}
//	
//	public void setCurrentClinicalContactId(Integer clinicalContactId)
//	{
//		this.clinicalContactId = clinicalContactId;
//	}
//		
//	public Integer getCurrentCareContextId()
//	{
//		return careContextId;
//	}
//
//	public void setCurrentCareContextId(Integer careContextId)
//	{
//		this.careContextId = careContextId;
//	}

	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String message)
	{
		ISystemLogProvider provider = getSystemLogProvider();
		if(provider == null)
			return null;
		
		IAppUser user = getLoggedInUser();
		String computer = "";
		String username = "";		
		
		if(user != null)
		{
			username = user.getUsername();
			computer = user.getHostName();			
		}
		
		return createSystemLogEntry(type, level, username, computer, message);
	}
	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String username, String computer, String message)
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
	private ISystemLogProvider getSystemLogProvider()
	{
		SystemLogFactory systemLogFactory = new SystemLogFactory(this);
		if(systemLogFactory.hasSystemLogProvider())
			return systemLogFactory.getProvider();
		return null;
	}
	public IAppUser getLoggedInUser()
    {
		return this.getUser();		
    }
	public String getSessionId()
    {
		SessionData sessData = (SessionData)this.getAttribute(SessionConstants.SESSION_DATA);		
		if(sessData == null)
			return null;
    	String id = sessData.sessionId.get();
    	return id == null ? "" : id;
    }
}
