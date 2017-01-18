package ims.framework.utils;

public class ImageSize 
{
	private int height;
	private int width;
	private int bitsPerPixel;
	private int format;

	public ImageSize(int width, int height, int bitsPerPixel, int format)
	{
		this.height = height;
		this.width = width;
		this.bitsPerPixel = bitsPerPixel;
		this.format = format;
	}

	public int getHeight() 
	{
		return height;
	}

	public int getBitsPerPixel() 
	{
		return bitsPerPixel;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getFormat() 
	{
		return format;
	}
	
}
