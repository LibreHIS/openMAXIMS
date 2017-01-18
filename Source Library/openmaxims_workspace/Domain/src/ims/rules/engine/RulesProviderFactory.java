package ims.rules.engine;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.rules.interfaces.IRulesProvider;

public class RulesProviderFactory extends ims.domain.RulesFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(RulesProviderFactory.class);
	private String className;
	private DomainSession session;
	private IRulesProvider rulesProvider;

	public RulesProviderFactory (DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getRulesProviderClassName();
	}
	
	public boolean hasRulesProvider()
	{
		if(rulesProvider != null)
			return true;
		
		try
		{
			rulesProvider = getRulesProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Image Provider", e);
			return false;
		}
		
		return rulesProvider != null;
	}
	public IRulesProvider getRulesProvider()
	{
		if(rulesProvider != null)
			return rulesProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IRulesProvider)
			{
				rulesProvider = (IRulesProvider)instance;
				return rulesProvider;
			}
			else
				throw new RuntimeException("Invalid Rules Provider Class: " + className + " - It should implement IRulesProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Rules Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Rules Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Rules Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
