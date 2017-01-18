package ims.domain.impl;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;

import ims.configuration.EnvironmentConfig;
import ims.domain.DomainFactory;
import ims.domain.DomainSession;
import ims.domain.servicemanager.ProxyFactory;
import ims.domain.servicemanager.ProxyInvocationHandler;

/**
 * The class uses the Singleton Idiom to provide a single static instance of the class.
 * Pools instantiations of DomainImplementations.
 * These pools grow according to demand.
 * There is no configuration.
 * 
 * @author gcoghlan
 *
 */
public class DomainImplFlyweightFactory {
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(DomainImplFlyweightFactory.class);
	
	/**
	 * Create & initialize the named implementation-class.
	 * 
	 * @param domainImplClassName
	 * @param domainSession
	 * @return initialized DomainImpl subclass
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public  Object create(String domainImplClassName, DomainSession domainSession)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (LOG.isDebugEnabled())
			LOG.debug("create(domainImplClassName:"+domainImplClassName+", domainSession:"+domainSession);
		Class domainImplementationClass = Class.forName(domainImplClassName);
		return create(domainImplementationClass, domainSession);
	}

	
	/**
	 * Create & initalizes and instance of the domainImplementationClass.
	 * 
	 * @param domainImplementationClass
	 * @param domainSession
	 * @return initialized DomainImpl subclass
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object create(Class domainImplementationClass, DomainSession domainSession)
		throws InstantiationException, IllegalAccessException
	{
		return create(domainImplementationClass, domainSession, (DomainFactory)null);
	}
		
	public  Object create(Class domainImplementationClass, DomainSession domainSession, DomainFactory factory)
		throws InstantiationException, IllegalAccessException
	{
		Object retVal = null;
		if (LOG.isDebugEnabled())
			LOG.debug("create(domainImplementationClass:"+domainImplementationClass+", domainSession:"+domainSession);
		DomainImpl impl = create(domainImplementationClass);
		impl.setContext(domainSession);  
		// create the dynamic proxy, only for Transactional implementations
		if ( factory == null)
		{
			if (LOG.isDebugEnabled())
				LOG.debug("Creating dymanic proxy for domain implementation.");
			retVal = DomainImplProxyHandler.newInstance(impl);

			if(EnvironmentConfig.getUseServiceManagerMonitoring())
			{
				retVal = ProxyFactory.monitor(retVal, ((ProxyInvocationHandler) Proxy.getInvocationHandler(retVal)).getDomainImpl());
			}
			if(EnvironmentConfig.getUseServiceManagerLogging())
			{
				retVal = ProxyFactory.log(retVal, domainImplementationClass, ((ProxyInvocationHandler) Proxy.getInvocationHandler(retVal)).getDomainImpl());
			}
		}
		else 
		{
			if (!DomainImplProxyHandler.ProxyStartTrans)
			{
				impl.setDomainFactory(factory);				
			}
			retVal = impl;
			if(EnvironmentConfig.getUseServiceManagerMonitoring())
			{
				retVal = ProxyFactory.monitor(retVal, impl);
			}
			if(EnvironmentConfig.getUseServiceManagerLogging())
			{
				retVal = ProxyFactory.log(retVal, domainImplementationClass, impl);
			}
		}
		
		
		return retVal;
	}

	private DomainImpl create(Class domainClass)
		throws InstantiationException, IllegalAccessException 
	{
		return (DomainImpl)domainClass.newInstance();
	}
	
	/**
	 * Invokes free() on the domain implementation and returns it to the pool.
	 * 
	 * @param domainImplProxy
	 */
	public void free(Object domainImplProxy) {
		DomainImpl domainImpl = null;
		// If the object is Transactional, then it is a proxy
		if (domainImplProxy instanceof DomainImpl)
		{
			domainImpl = (DomainImpl) domainImplProxy;
		}
		else if ( domainImplProxy instanceof Transactional) 
		{
			// retrieve the implementation from the proxy handler
			ProxyInvocationHandler domainProxyHandler =
				(ProxyInvocationHandler) Proxy.getInvocationHandler(domainImplProxy);
			domainImpl = domainProxyHandler.getDomainImpl();
		}
		else 
		{
			throw new RuntimeException("Object being freed must be either a DomainImpl or a Proxy.");
		}
		domainImpl.free();

		HashMap arr = domainImpl.getExtraImpls();
		Object obj;
		Iterator iter = arr.values().iterator();
		while (iter.hasNext())
		{
			obj = iter.next();
			//Recursive call to free Impl's used within an Impl
			free(obj);			
		}
		arr.clear();
	}
	
	// Singleton
	private DomainImplFlyweightFactory()
	{
	}
	public static DomainImplFlyweightFactory getInstance()
	{
		return SingletonHolder.Singleton;
	}
	private static final class SingletonHolder
	{
		static final DomainImplFlyweightFactory Singleton = new DomainImplFlyweightFactory();
	}
	
}