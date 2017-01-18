package ims.domain.factory;

import java.io.Serializable;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImplFlyweightFactory;
import ims.framework.interfaces.IFormProvider;

public class FormFactory extends ims.domain.FormFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(FormFactory.class);
	private String className;
	private DomainSession session;
	private IFormProvider formProvider;

	public FormFactory(DomainSession session)
	{
		this.session = session;
		this.className = InitConfig.getFormProviderClassName();
	}
	
	public boolean hasFormProvider()
	{
		if(formProvider != null)
			return true;
		
		try
		{
			formProvider = getFormProvider();
		}
		catch (Exception e)
		{
			LocalLogger.warn("Failed to get Form Provider", e);
			return false;
		}
		
		return formProvider != null;
	}
	public IFormProvider getFormProvider()
	{
		if(formProvider != null)
			return formProvider;
		
		try 
		{
			DomainImplFlyweightFactory factory = DomainImplFlyweightFactory.getInstance();
			Object instance = factory.create(className, session);
			if(instance instanceof IFormProvider)
			{
				formProvider = (IFormProvider)instance;
				return formProvider;
			}
			else
				throw new RuntimeException("Invalid Form Provider Class: " + className + " - It should implement IFormProvider interface");
		} 
		catch (ClassNotFoundException e) 
		{		
			throw new RuntimeException("Invalid Form Provider Class: " + className + " - Class was not found");
		} 
		catch (InstantiationException e) 
		{
			throw new RuntimeException("Invalid Form Provider Class: " + className + " - Class cannot be instantiated: " + e.getMessage());
		} 
		catch (IllegalAccessException e) 
		{
			throw new RuntimeException("Invalid Form Provider Class: " + className + " - " + e.getMessage());
		}
	}	
}
