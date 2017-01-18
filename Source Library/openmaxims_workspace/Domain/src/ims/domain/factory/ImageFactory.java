package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IImageProvider;

public class ImageFactory extends ims.domain.ImageFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ImageFactory.class);
	private String className;
	private DomainSession session;
	private IImageProvider imageProvider;

	public ImageFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getImageProviderClassName();
	}
	
	public boolean hasImageProvider()
	{
		if(imageProvider != null)
			return true;
		
		try
		{
			imageProvider = getImageProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Image Provider", e);
			return false;
		}
		
		return imageProvider != null;
	}
	public IImageProvider getImageProvider()
	{
		if(imageProvider != null)
			return imageProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IImageProvider)
			{
				imageProvider = (IImageProvider)instance;
				return imageProvider;
			}
			else
				throw new RuntimeException("Invalid Image Provider Class: " + className + " - It should implement IImageProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Image Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Image Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Image Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
