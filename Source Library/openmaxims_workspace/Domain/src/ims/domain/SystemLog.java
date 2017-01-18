package ims.domain;

import ims.configuration.BuildInfo;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.factory.SystemLogFactory;
import ims.framework.SessionConstants;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.ISystemLogProvider;
import ims.framework.interfaces.ISystemLogWriter;
import ims.framework.utils.DateTime;

public class SystemLog implements ISystemLogWriter 
{
	private static final long serialVersionUID = 1L;
	
	private DomainSession domainSession = null;
	private ISystemLogProvider provider = null;
	private static String source = null;
	
	public SystemLog(DomainSession domainSession)
	{
		if(domainSession == null)
			throw new DomainRuntimeException("Invalid domain session.");
		
		this.domainSession = domainSession;
		
		SystemLogFactory factory = new SystemLogFactory(domainSession);
		if(factory.hasSystemLogProvider())
		{			
			provider = factory.getProvider();
		}
	}
	
	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String message) 
	{
		if(provider != null)
		{
			String server = ConfigFlag.HOST_NAME.getValue();
			String user = "";
			String source = "";
			String computer = "";
			String userAgent = "";
			String sessionId = getSessionId();
			
			source = getBuildInfo();
			
			if(domainSession.getUser() != null)
			{
				user = domainSession.getUser().getUsername();
				computer = domainSession.getUser().getHostName();
			}
						
			return provider.save(new DateTime(), type, level, server, user, source, computer, userAgent, sessionId, message);
		}
		
		return null;
	}

	private static String getBuildInfo() 
	{
		if(source != null)
			return source;
		
		source = "";
		BuildInfo buildInfo = Configuration.getBuildInfo();			
		if(buildInfo != null)
		{
			source = buildInfo.getName();
			source += " version ";
			source += buildInfo.getAppVersion() + " (" + buildInfo.getAppTimestamp() + ")";
		}
	
		return source;
	}
	private String getSessionId()
    {
		SessionData sessData = (SessionData)domainSession.getAttribute(SessionConstants.SESSION_DATA);		
		if(sessData == null)
			return null;
    	String id = sessData.sessionId.get();
    	return id == null ? "" : id;
    }
}
