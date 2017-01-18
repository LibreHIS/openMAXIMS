package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.EnvironmentConfig;
import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IUserProvider;

public class UserFactory extends ims.domain.UserFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(UserFactory.class);
	private String className;
	private DomainSession session;
	
	public UserFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getUserProviderClassName();
		if (EnvironmentConfig.getUserProviderFlag()!=null
				&&EnvironmentConfig.getUserProviderFlag().length()>2)
		{
			this.className=EnvironmentConfig.getUserProviderFlag();
		}
	}
	
	public boolean hasUserProvider()
	{
		IUserProvider userProvider = null;
		
		try
		{
			userProvider = getUserProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get User Provider", e);
			return false;
		}
		
		return userProvider != null;
	}
	public IUserProvider getUserProvider()
	{
		IUserProvider userProvider = null;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IUserProvider)
			{
				userProvider = (IUserProvider)instance;
				return userProvider;
			}
			else
				throw new RuntimeException("Invalid User Provider Class: " + className + " - It should implement IUserProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid User Provider Class: " + className + " - Class was not found", e);
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid User Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage(), e);
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid User Provider Class: " + className + " - " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Invalid User Provider Class: " + className + " - " + e.getMessage(), e);
		}
	}	
}
