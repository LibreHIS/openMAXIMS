package ims.base.cache;

import ims.framework.interfaces.ICache;

import org.jboss.cache.CacheException;
import org.jboss.cache.Fqn;
import org.jboss.cache.PropertyConfigurator;
import org.jboss.cache.aop.PojoCache;

public class PojoCacheImpl implements ICache
{
	private PojoCache cache;
	
	public PojoCacheImpl()
	{
		try
		{
			cache = new PojoCache();
			PropertyConfigurator config = new PropertyConfigurator();
			config.configure( cache, "pojocache.xml" );
			cache.start();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to start cluster cache.",e);
		}
	}
	
	public Object get(String key)
	{
		Fqn fqn = new Fqn(key);
		try
		{
			return cache.getObject(fqn);
		}
		catch (CacheException e)
		{
			throw new RuntimeException(e);
		}
	}

	public Object put(String key, Object value)
	{
		Fqn fqn = new Fqn(key);
		try
		{
			return cache.putObject(fqn, value);
		}
		catch (CacheException e)
		{
			throw new RuntimeException(e);
		}
	}
	public Object remove(String key)
	{
		Fqn fqn = new Fqn(key);
		try
		{
			return cache.removeObject(fqn);
		}
		catch (CacheException e)
		{
			throw new RuntimeException(e);
		}

	}
}

