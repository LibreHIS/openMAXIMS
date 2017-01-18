package ims.notifications;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.ISMSSenderProvider;

public final class SMSSenderFactory implements Serializable
{	
	private static final long serialVersionUID = 1L;	
	private String className;
	private DomainSession session;	
	
	public SMSSenderFactory(DomainSession session)
	{				
		this.session = session;
		this.className = InitConfig.getSMSSenderProviderClassName();
	}	
	public boolean hasSMSSenderProvider()
	{
		ISMSSenderProvider smsSenderProvider = null;
		
		try
		{
			smsSenderProvider = getSMSSenderProvider();
		}
		catch (Exception e)
		{			
			return false;
		}
		
		return smsSenderProvider != null;
	}
	public ISMSSenderProvider getSMSSenderProvider()
	{
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof ISMSSenderProvider)
			{
				return (ISMSSenderProvider)instance;								
			}
			else
				throw new RuntimeException("Invalid SMS Sender Provider Class: " + className + " - It should implement ISMSSenderProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid SMS Sender Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid SMS Sender Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid SMS Sender Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
