/*
 * Created on 04-May-2005
 *
 */
package ims.framework.utils.beans;

import ims.framework.utils.Image;
import ims.framework.utils.ImagePath;

/**
 * @author jmacenri
 *
 */
public class ImageBean 
{
	private int id;
	private String imagePath;

	
	public ImageBean()
	{
		
	}
	public ImageBean(Image val)
	{
		this.id = val.getImageId();
		this.imagePath = val.getImagePath();
	}
	
	public Image buildImage()
	{
		return new ImagePath(this.id, imagePath);
	}
	public int getId() 
	{
		return this.id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getImagePath() 
	{
		return this.imagePath;		
	}

	public void setImagePath(String imagePath) 
	{
		this.imagePath = imagePath;
	}

}
