package ims.framework.utils;

import java.io.Serializable;

public class SizeInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected int width;
	protected int height;
	
	public SizeInfo(int width, int height)
	{
		this.width  = width;
		this.height = height;
	}
	
	public final int getWidth()
	{
		return width;
	}	
	public final int getHeight()
	{
		return height;
	}
	public final String toString()
	{
		return width + ", " + height;
	}
	public final boolean equals(Object obj)
	{
		if(obj instanceof SizeInfo)
		{
			SizeInfo compare = (SizeInfo)obj;
			return compare.width == width && compare.height == height;
		}
		
		return false;
	}
	public final int hashCode()
	{
		return super.hashCode();
	}	
}
