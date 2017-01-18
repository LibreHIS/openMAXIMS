package ims.domain.servicemanager;

import java.io.Serializable;
import ims.configuration.Configuration;
import ims.configuration.User;
import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.SessionConstants;

import java.lang.reflect.Proxy;

public class ServiceManagerImpl implements ServiceManager,Serializable
{
	private static final long serialVersionUID = 1L;

	private static final org.apache.log4j.Logger	LocalLogger			= ims.utils.Logging.getLogger(ServiceManagerImpl.class);
	private final String							CONSTRUCTON_FAILED	= "Couldn't construct class '";

	ServiceManagerImpl()
	{
		super();
	}

	public Object getServiceObject(final DomainSession domainSession, final Class requiredType)
	{
		if (LocalLogger.isDebugEnabled())
		{
			LocalLogger.debug("DomainSession =" + domainSession);
		}

		Object domain = null;
		String domainClassName = null;

		// If there is no domain session give up
		if (domainSession == null)
		{
			LocalLogger.warn("DomainSession object cannot be null");
			throw new DomainRuntimeException("DomainSession object cannot be null");
		}
		domainClassName = requiredType.getName();
		if (LocalLogger.isDebugEnabled())
		{
			LocalLogger.debug("domainClassName =" + domainClassName);
		}
		if (domainClassName != null && domainClassName.length() > 0)
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			if (LocalLogger.isDebugEnabled())
			{
				LocalLogger.debug("DomainImplFlyweightFactory instance retrieved - " + factory);
			}

			try
			{
				domain = factory.create(domainClassName, domainSession);
				if (LocalLogger.isDebugEnabled())
				{
					LocalLogger.debug("DomainImplFlyweightFactory created and domain retrieved");
				}
			}

			catch (ClassNotFoundException e)
			{
				LocalLogger.warn("Coundn't load class '" + domainClassName + "'", e);
				throw new DomainRuntimeException("Coundn't load class '" + domainClassName + "'", e);
			}
			catch (IllegalAccessException e)
			{
				LocalLogger.warn(CONSTRUCTON_FAILED + domainClassName + "' is the no arg constructor public?", e);
				throw new DomainRuntimeException(CONSTRUCTON_FAILED + domainClassName + "' is the no arg constructor public?", e);
			}
			catch (InstantiationException e)
			{
				LocalLogger.warn(CONSTRUCTON_FAILED + domainClassName + "' does it have a no arg constructor?", e);
				throw new DomainRuntimeException(CONSTRUCTON_FAILED + domainClassName + "' does it have a no arg constructor?", e);
			}
		}

		if (LocalLogger.isDebugEnabled())
		{
			LocalLogger.debug("Returning domain=" + domain);
		}

		return domain;
	}

	public Object getServiceObject(final Class requiredType)
	{

		/*
		 * This code should probably be called only once and the session object
		 * reused between calls
		 */
		User user = new User();
		user.setUsername("anonymous");
		DomainSession domainSession = new DomainSession(user);
		try
		{
			SessionData sessData = new SessionData();
			sessData.configurationModule.set(Configuration.loadConfiguration());
			domainSession.setAttribute(SessionConstants.SESSION_DATA, sessData);
		}
		catch (Exception e)
		{
			LocalLogger.warn("DomainSession creation failed!", e);
			throw new DomainRuntimeException("DomainSession creation failed!", e);
		}
		if (domainSession != null)
		{
			return getServiceObject(domainSession, requiredType);
		}
		else
		{
			return null;
		}
	}

	/*
	 * Simplified free after DomainImplProxyHandler implements
	 * ProxyInvocationHandler
	 * 
	 */
	public void free(Object service)
	{
		DomainImpl domainImpl = null;
		ProxyInvocationHandler proxyHandler = (ProxyInvocationHandler) Proxy.getInvocationHandler(service);
		domainImpl = proxyHandler.getDomainImpl();
		domainImpl.free();
	}


	// Singleton
	public static ServiceManager getInstance()
	{
		return SingletonHolder.Singleton;
	}

	private static final class SingletonHolder
	{
		private static final ServiceManager	Singleton	= new ServiceManagerImpl();
	}
}
