package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ILocalSettingsProvider;

public final class LocalSettingsFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(LocalSettingsFactory.class);
	private String className;
	private DomainSession session;
	private ILocalSettingsProvider provider;
	
	public LocalSettingsFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getLocalSettingsProviderClassName();
	}	
	public boolean hasLocalSettingsProvider()
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
	public ILocalSettingsProvider getProvider()
	{
		if(provider != null)
			return provider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ILocalSettingsProvider)
			{
				provider = (ILocalSettingsProvider)instance;
				return provider;
			}
			else
				throw new RuntimeException("Invalid Local Settings Provider Class: " + className + " - It should implement ILocalSettingsProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Local Settings Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Local Settings Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Local Settings Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
