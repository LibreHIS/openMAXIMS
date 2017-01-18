package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ISecurityTokenProvider;

public final class SecurityTokenFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(SecurityTokenFactory.class);
	private String className;
	private DomainSession session;
	private ISecurityTokenProvider securityTokenProvider;
	
	public SecurityTokenFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getSecurityTokenProviderClassName();
	}	
	public boolean hasSecurityTokenProvider()
	{
		if(securityTokenProvider != null)
			return true;
		
		try
		{
			securityTokenProvider = getSecurityTokenProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Security Token Provider", e);
			return false;
		}
		
		return securityTokenProvider != null;
	}
	public ISecurityTokenProvider getSecurityTokenProvider()
	{
		if(securityTokenProvider != null)
			return securityTokenProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ISecurityTokenProvider)
			{
				securityTokenProvider = (ISecurityTokenProvider)instance;
				return securityTokenProvider;
			}
			else
				throw new RuntimeException("Invalid Security Token Provider Class: " + className + " - It should implement ISecurityTokenProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Security Token Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Security Token Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Security Token Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
