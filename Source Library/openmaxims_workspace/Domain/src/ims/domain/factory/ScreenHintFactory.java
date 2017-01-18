package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IScreenHintProvider;

public final class ScreenHintFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ScreenHintFactory.class);
	private String className;
	private DomainSession session;
	private IScreenHintProvider provider;
	
	public ScreenHintFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getScreenHintProviderClassName();
	}	
	public boolean hasProvider()
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
	public IScreenHintProvider getProvider()
	{
		if(provider != null)
			return provider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IScreenHintProvider)
			{
				provider = (IScreenHintProvider)instance;
				return provider;
			}
			else
				throw new RuntimeException("Invalid Screen Hints Provider Class: " + className + " - It should implement IPrintersProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Screen Hints Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Screen Hints Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Screen Hints Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
