package ims.notifications;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.INotificationsProvider;

public final class NotificationsFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;	
	private String className;
	private DomainSession session;	
	
	public NotificationsFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getNotificationsProviderClassName();
	}	
	public boolean hasNotificationsProvider()
	{
		INotificationsProvider notificationsProvider = null;
		
		try
		{
			notificationsProvider = getNotificationsProvider();
		}
		catch (Exception e)
		{			
			return false;
		}
		
		return notificationsProvider != null;
	}
	public INotificationsProvider getNotificationsProvider()
	{
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof INotificationsProvider)
			{
				return (INotificationsProvider)instance;								
			}
			else
				throw new RuntimeException("Invalid Notifications Provider Class: " + className + " - It should implement INotificationsProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Notifications Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Notifications Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Notifications Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
