package ims.framework.utils;

import java.io.Serializable;

import ims.base.interfaces.IModifiable;

public class Size extends SizeInfo implements IModifiable, Serializable
{
	private static final long serialVersionUID = 1L;
	boolean wasChanged;
	
	public Size()
	{
		super(0, 0);
	}
	public Size(int width, int height)
	{
		super(width, height);
	}
	
	public final void setWidth(int value)
	{
		if(width != value)
		{
			wasChanged = true;		
			width = value;
		}
	}
	public final void setHeight(int value)
	{
		if(height != value)
		{
			wasChanged = true;		
			height = value;
		}
	}		
	public final boolean wasChanged() 
	{
		return wasChanged;
	}
	public final void markUnchanged() 
	{
		wasChanged = false;
	}
}
