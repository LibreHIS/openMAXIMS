package ims.framework;

import ims.framework.utils.Image;

public abstract class InternalAlert extends Alert
{
	private static final long serialVersionUID = 1L;	
	
	InternalAlert(Image icon, Object data, String tooltip) 
	{
		super(icon, data, tooltip);
	}			
}
