package ims.scheduler;

import ims.configuration.Configuration;
import ims.domain.SessionData;
import ims.framework.SessionConstants;

public class DomainSession extends ims.domain.DomainSession 
{
	private static final long serialVersionUID = 1L;	
	
	public final boolean queryCappingDisabled()
	{
		return true;
	}
	
	public DomainSession() throws Exception
	{
		setUser(new SchedulerUser());
		SessionData sessData = new SessionData();
		sessData.configurationModule.set(Configuration.loadConfiguration());
		setAttribute(SessionConstants.SESSION_DATA, sessData);
	}	
}
