package ims.domain;

import ims.framework.interfaces.ILocationProvider;

public abstract class LocationFactory 
{	
	public abstract boolean hasLocationProvider();
	public abstract ILocationProvider getLocationProvider();	
}
