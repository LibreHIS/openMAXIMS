package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.impl.DomainImpl;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IPathwayEntityProvider;
import ims.framework.interfaces.IRoleProvider;
import ims.domain.DomainSession;

public class PathwayEntityFactory extends ims.domain.PathwayEntityFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(PathwayEntityFactory.class);
	private String className;
	private DomainSession session;
	private IPathwayEntityProvider pathwayEntityProvider;

	public PathwayEntityFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getPathwayEntityProviderClassName();
	}
	
	public boolean hasPathwayEntityProvider()
	{
		if(pathwayEntityProvider != null)
			return true;
		
		try
		{
			pathwayEntityProvider = getPathwayEntityProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get PathwayEntity Provider", e);
			return false;
		}
		
		return pathwayEntityProvider != null;
	}
	public IPathwayEntityProvider getPathwayEntityProvider()
	{
		if(pathwayEntityProvider != null)
			return pathwayEntityProvider;
		
		try 
		{
			Class c = Class.forName(className);
			Object instance = c.newInstance(); 
			if (instance instanceof DomainImpl)
			{
				DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
				instance = factory.create(className, session);				
			}
			if(instance instanceof IPathwayEntityProvider)
			{
				pathwayEntityProvider = (IPathwayEntityProvider)instance;
				return pathwayEntityProvider;
			}
			else
				throw new RuntimeException("Invalid Pathway Entity Provider Class: " + className + " - It should implement IPathwayEntityProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Pathway Entity Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Pathway Entity Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Pathway Entity Provider Class: " + className + " - " + e.getMessage());
		}
	}

}
