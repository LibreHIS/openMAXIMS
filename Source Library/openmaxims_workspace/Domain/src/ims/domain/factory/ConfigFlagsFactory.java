package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IConfigFlagsProvider;

public final class ConfigFlagsFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final String emptyProviderClass = "ims.domain.providers.EmptyConfigFlagsProvider";	
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ConfigFlagsFactory.class);
	private String className;
	private IConfigFlagsProvider provider;
	
	public ConfigFlagsFactory()
	{				
		if(InitConfig.getConfigFlagsProviderClassName().length() == 0)
		{
			this.className =  emptyProviderClass;
		}
		else
		{
			this.className =  InitConfig.getConfigFlagsProviderClassName();
		}	
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
	public IConfigFlagsProvider getProvider()
	{
		if(provider != null)
			return provider;
		
		if(InitConfig.getConfigFlagsProviderClassName().length() == 0)
		{
			LocalLogger.warn("The config flag provider was not set, using the empty provider. WARNING: the empty provider will only return default flag values and not persist any value.");
		}
		
		try 
		{
			Object instance = null;
			
			try
			{			
				Class c = Class.forName(className);
				
				instance = c.newInstance();		
				if(instance instanceof DomainImpl)
				{
					instance = null;
				}
			}
			catch(Exception e)
			{
				instance = null;
			}
			
			if(instance == null)
			{
				DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
				instance = factory.create(className, ims.domain.DomainSession.getSession());
			}
			
			if(instance instanceof IConfigFlagsProvider)
			{
				provider = (IConfigFlagsProvider)instance;
				return provider;
			}
			else
				throw new RuntimeException("Invalid Config Flags Provider Class: " + className + " - It should implement IConfigFlagsProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Config Flags Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Config Flags Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Config Flags Provider Class: " + className + " - " + e.getMessage());
		}
		catch (Exception e) 
		{
			throw new RuntimeException("An error occured while trying to initilize the config flags provider", e);
		}
	}	
}
