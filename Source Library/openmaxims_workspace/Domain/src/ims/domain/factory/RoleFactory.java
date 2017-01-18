package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IRoleProvider;
import ims.domain.DomainSession;

public class RoleFactory extends ims.domain.RoleFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(RoleFactory.class);
	private String className;
	private DomainSession session;
	private IRoleProvider roleProvider;

	public RoleFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getRoleProviderClassName();
	}
	
	public boolean hasRoleProvider()
	{
		if(roleProvider != null)
			return true;
		
		try
		{
			roleProvider = getRoleProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Role Provider", e);
			return false;
		}
		
		return roleProvider != null;
	}
	public IRoleProvider getRoleProvider()
	{
		if(roleProvider != null)
			return roleProvider;
		
		try 
		{
			Class c = Class.forName(className);
			Object instance = c.newInstance(); 
			if (instance instanceof DomainImpl)
			{
				DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
				instance = factory.create(className, session);				
			}
			if(instance instanceof IRoleProvider)
			{
				roleProvider = (IRoleProvider)instance;
				return roleProvider;
			}
			else
				throw new RuntimeException("Invalid Role Provider Class: " + className + " - It should implement IRoleProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Role Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Role Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Role Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
