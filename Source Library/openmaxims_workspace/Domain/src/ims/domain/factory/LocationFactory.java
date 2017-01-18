package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ILocationProvider;

public final class LocationFactory extends ims.domain.LocationFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(LocationFactory.class);
	private String className;
	private DomainSession session;
	private ILocationProvider locationProvider;
	
	public LocationFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getLocationProviderClassName();
	}	
	public boolean hasLocationProvider()
	{
		if(locationProvider != null)
			return true;
		
		try
		{
			locationProvider = getLocationProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Location Provider", e);
			return false;
		}
		
		return locationProvider != null;
	}
	public ILocationProvider getLocationProvider()
	{
		if(locationProvider != null)
			return locationProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ILocationProvider)
			{
				locationProvider = (ILocationProvider)instance;
				return locationProvider;
			}
			else
				throw new RuntimeException("Invalid Location Provider Class: " + className + " - It should implement ILocationProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Location Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Location Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Location Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
