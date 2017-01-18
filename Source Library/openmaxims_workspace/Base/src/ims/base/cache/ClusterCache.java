package ims.base.cache;

import ims.configuration.EnvironmentConfig;
import ims.framework.interfaces.ICache;

public class ClusterCache
{
	private static ICache clusterCache = null;
	public static ICache getClusterCache()
	{
		if (!EnvironmentConfig.getClusterNodes().equals("") && clusterCache == null)
		{
			clusterCache = new PojoCacheImpl();			
		}
		return clusterCache;		
	}
}
