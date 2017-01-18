package ims.domain;

import ims.framework.interfaces.IPathwayEntityProvider;


public abstract class PathwayEntityFactory
{
	public abstract boolean hasPathwayEntityProvider();
	public abstract IPathwayEntityProvider getPathwayEntityProvider();	
}
