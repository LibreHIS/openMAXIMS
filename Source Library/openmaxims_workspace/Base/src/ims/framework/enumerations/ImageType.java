package ims.framework.enumerations;

import java.io.Serializable;

public class ImageType implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final ImageType JPG = new ImageType(1);
    public static final ImageType PNG = new ImageType(2);
    public static final ImageType BMP = new ImageType(3);
    
    private ImageType(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "jpg";
    	if (this.id == 2)
    		return "png";
    	if (this.id == 3)
    		return "bmp";
    	
    	return "jpg";
    }

    private int id; 
}
