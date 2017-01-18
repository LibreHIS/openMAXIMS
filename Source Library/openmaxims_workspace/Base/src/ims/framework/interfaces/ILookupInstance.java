package ims.framework.interfaces;

import ims.framework.utils.Color;
import ims.framework.utils.Image;

public interface ILookupInstance
{
	public int getId();
	public String getText();
	public boolean isActive();
	public ILookupInstance getParentInstance();
	public Color getColor();
	public Image getImage();
	public int getOrder();
	public ILookupInstanceMapping[] getInstanceMappings();
	public ILookupType getLookupType();
}
