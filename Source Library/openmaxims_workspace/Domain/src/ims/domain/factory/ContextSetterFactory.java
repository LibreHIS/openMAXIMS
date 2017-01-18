package ims.domain.factory;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.Context;
import ims.framework.interfaces.IContextSetter;

public class ContextSetterFactory
{
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ContextSetterFactory.class);
	private Context ctx;
	private DomainSession session;
	private IContextSetter setter;
	private String className;
	
	public ContextSetterFactory(DomainSession sess, Context ctx)
	{
		this.ctx = ctx;
		this.session = sess;
		this.className = InitConfig.getContextSetterClassName();
	}
	
	public boolean hasContextSetter()
	{
		if(setter != null)
			return true;
		
		try
		{
			setter = getContextSetter(true);
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Context Setter", e);
			return false;
		}
		
		return setter != null;
	}
	
	public IContextSetter getContextSetter()
	{
		return getContextSetter(false);
	}
	private IContextSetter getContextSetter(boolean ignoreErrors)
	{
		if(setter != null)
			return setter;
		
		try 
		{
			Class c = Class.forName(className);
			Object instance = c.newInstance();
			if (instance instanceof DomainImpl)
			{
				DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
				instance = factory.create(className, session);				
			}
			if(instance instanceof IContextSetter)
			{
				setter = (IContextSetter)instance;
				setter.setContext(ctx);
				return setter;
			}
			else
			{
				if(!ignoreErrors)
				{
					throw new RuntimeException("Invalid Context Setter Class: " + className + " - It should implement IContextSetter interface");
				}
			}
		} 
		catch (ClassNotFoundException e) 
		{		
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Setter Class: " + className + " - Class was not found");
			}
		} 
		catch (InstantiationException e) 
		{
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Setter Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
			}
		} 
		catch (IllegalAccessException e) 
		{
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Setter Class: " + className + " - " + e.getMessage());
			}	
		}
		
		return null;
	}
}
