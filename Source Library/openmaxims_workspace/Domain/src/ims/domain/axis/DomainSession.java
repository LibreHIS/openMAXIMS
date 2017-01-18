/*
 * Created on 27-Apr-2005
 *
 */
package ims.domain.axis;

import ims.configuration.Configuration;
import ims.domain.SessionData;
import ims.framework.SessionConstants;
import ims.framework.interfaces.IAppUser;
import ims.utils.Logging;
import org.apache.axis.session.Session;
import org.apache.log4j.Logger;

/**
 * @author jmacenri
 *
 */
public class DomainSession extends ims.domain.DomainSession 
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logging.getLogger(DomainSession.class);

	/**
	 * Create the DomainSession corresponding to the HttpSession.
	 * There must be a valid User object in the HttpSession.
	 * @param httpSession
	 * @return the DomainSession that is in the httpSession. This will be added if
	 * one did not already exist before this method is called.
	 * @throws Exception 
	 */	
	public static ims.domain.DomainSession getSession(Session axisSession) throws Exception
	{
		ims.domain.DomainSession domainSession = (ims.domain.DomainSession)axisSession.get(SessionConstants.DOMAIN_SESSION);
		if (null == domainSession)
		{			
			domainSession = new ims.domain.DomainSession();
			IAppUser user = (IAppUser) axisSession.get(SessionConstants.USER);
			domainSession.setUser(user);
			SessionData sessData = new SessionData();
			sessData.configurationModule.set(Configuration.loadConfiguration());
			domainSession.setAttribute(SessionConstants.SESSION_DATA, sessData);
			
			axisSession.set(SessionConstants.DOMAIN_SESSION, domainSession);
		}
		if (LOG.isDebugEnabled())
			LOG.debug("getSession exit after setting axisSession atts");

		return domainSession;
	}
	

}
