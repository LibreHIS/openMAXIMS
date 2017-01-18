package ims.domain.servicemanager;

public class ServiceManagerFactory
{
	private static ServiceManagerImpl serviceManager;
	
	static
	{
		serviceManager = new ServiceManagerImpl();
	}
	public static ServiceManager getServiceManager()
	{
		return serviceManager;
	}

	private ServiceManagerFactory()
	{

	}

}
