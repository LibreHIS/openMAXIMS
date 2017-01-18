package ims.framework.interfaces;

public interface INavRootGroup
{	
	public int getId();	
	public String getGroupName();
	public INavForm[] getNavForms();
	public INavSecondGroup[] getNavGroups();
	public IAppImage getNavGroupImage();
}
