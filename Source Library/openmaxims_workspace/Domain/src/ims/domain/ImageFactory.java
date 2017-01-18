package ims.domain;

import ims.framework.interfaces.IImageProvider;

public abstract class ImageFactory
{
	public abstract boolean hasImageProvider();
	public abstract IImageProvider getImageProvider();	
	
}
