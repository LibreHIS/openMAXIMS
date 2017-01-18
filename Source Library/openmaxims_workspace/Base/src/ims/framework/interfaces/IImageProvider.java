package ims.framework.interfaces;

import ims.framework.utils.Image;

public interface IImageProvider
{
	public Image[] getAllImages();
	public Image getImage(int imageId);
	public void setImageWidthHeight(Image img, int width, int height);

}
