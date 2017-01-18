package ims.domain.factory;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ISecurityTokenHandlerProvider;

import java.io.Serializable;

public class SecurityTokenHandlerFactory implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(SecurityTokenHandlerFactory.class);
	private String className;
	private DomainSession session;
	private ISecurityTokenHandlerProvider securityTokenHandlerProvider;
	
	public SecurityTokenHandlerFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getSecurityTokenHandlerProviderClassName();
	}	
	public boolean hasSecurityTokenHandlerProvider()
	{
		if(securityTokenHandlerProvider != null)
			return true;
		
		try
		{
			securityTokenHandlerProvider = getSecurityTokenHandlerProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Exception getting Security Token Handler Provider", e);
			return false;
		}
		
		return securityTokenHandlerProvider != null;
	}
	public ISecurityTokenHandlerProvider getSecurityTokenHandlerProvider()
	{
		if(securityTokenHandlerProvider != null)
			return securityTokenHandlerProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ISecurityTokenHandlerProvider)
			{
				securityTokenHandlerProvider = (ISecurityTokenHandlerProvider)instance;
				return securityTokenHandlerProvider;
			}
			else
				throw new RuntimeException("Invalid Security Token Handler Provider Class: " + className + " - It should implement ISecurityTokenHandlerProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			LocalLogger.warn("Invalid Security Token Handler Provider Class: " + className + " - Class was not found", e);
			throw new RuntimeException("Invalid Security Token Handler Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			LocalLogger.warn("Invalid Security Token Handler Provider Class: " + className + " - Class cannot be instantiated.",e);
			throw new RuntimeException("Invalid Security Token Handler Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			LocalLogger.warn("Invalid Security Token Handler Provider Class: " + className, e);
			throw new RuntimeException("Invalid Security Token Handler Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
