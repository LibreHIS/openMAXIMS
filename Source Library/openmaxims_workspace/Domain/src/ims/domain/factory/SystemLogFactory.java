package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ISystemLogProvider;

public final class SystemLogFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(SystemLogFactory.class);
	private String className;
	private DomainSession session;
	private ISystemLogProvider provider;
	
	public SystemLogFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getSystemLogProviderClassName();
	}	
	public boolean hasSystemLogProvider()
	{
		if(className == null || className.length() == 0)
			return false;
		
		if(provider != null)
			return true;
				
		try
		{
			provider = getProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Provider", e);
			return false;
		}
		
		return provider != null;
	}
	public ISystemLogProvider getProvider()
	{
		if(provider != null)
			return provider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ISystemLogProvider)
			{
				provider = (ISystemLogProvider)instance;
				return provider;
			}
			else
				throw new RuntimeException("Invalid System Log Provider Class: " + className + " - It should implement ISystemLogProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid System Log Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid System Log Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid System Log Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
