package ims.framework.interfaces;

public interface INavSecondGroup
{
	public int getId();	
	public String getGroupName();
	public INavForm[] getNavForms();
	public int getPositionIndex();
	public IAppImage getNavGroupImage();
}
