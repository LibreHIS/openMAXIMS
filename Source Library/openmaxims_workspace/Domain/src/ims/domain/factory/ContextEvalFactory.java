package ims.domain.factory;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IContextEvalProvider;

import java.io.Serializable;

public class ContextEvalFactory extends ims.domain.ContextEvalFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ContextEvalFactory.class);

	
	private String className;
	private DomainSession session;
	private IContextEvalProvider ctxEvalProvider;

	public ContextEvalFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getContextEvalProviderClassName();
	}
	public boolean hasContextEvalProvider()
	{
		if(ctxEvalProvider != null)
			return true;
		
		try
		{
			ctxEvalProvider = getContextEvalProvider(true);
		}
		catch (Exception e)
		{
			LocalLogger.warn("Exception getting Context Eval Provider",e);
			return false;
		}
		
		return ctxEvalProvider != null;
	}

	public IContextEvalProvider getContextEvalProvider()
	{
		return getContextEvalProvider(false);
	}
	private IContextEvalProvider getContextEvalProvider(boolean ignoreErrors)
	{
		if(ctxEvalProvider != null)
			return ctxEvalProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IContextEvalProvider)
			{
				ctxEvalProvider = (IContextEvalProvider)instance;
				return ctxEvalProvider;
			}
			else
			{
				if(!ignoreErrors)
				{
					throw new RuntimeException("Invalid Context Evaluation Provider Class: " + className + " - It should implement IContextEvalProvider interface");
				}
			}
		} 
		catch (ClassNotFoundException e) 
		{		
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Evaluation Provider Class: " + className + " - Class was not found");
			}
		} 
		catch (InstantiationException e) 
		{
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Evaluation Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
			}
		} 
		catch (IllegalAccessException e) 
		{
			if(!ignoreErrors)
			{
				throw new RuntimeException("Invalid Context Evaluation Provider Class: " + className + " - " + e.getMessage());
			}
		}
		
		return null;
	}

}
