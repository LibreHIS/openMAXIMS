package ims.framework.utils;

import ims.framework.utils.beans.ImageBean;

public interface Image
{
	public int getImageId();
	public String getImagePath();
	public int getImageWidth();
	public int getImageHeight();
	public ImageBean getImageBean();
	public ImageInfo getImageInfo();
	public String toXMLString();
	public boolean isActive();
}
