package ims.configuration;

import java.io.Serializable;
import java.util.ArrayList;

import ims.framework.interfaces.IAppImage;
import ims.framework.interfaces.INavForm;
import ims.framework.interfaces.INavRootGroup;
import ims.framework.interfaces.INavSecondGroup;

public class RootGroup implements INavRootGroup, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String groupName;
	private ArrayList<NavForm> navForms = new ArrayList<NavForm>();
	private ArrayList<SecondGroup> secGroups = new ArrayList<SecondGroup>();
	private IAppImage image;
	
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void addNavForm(NavForm navForm)
	{
		this.navForms.add(navForm);
	}

	public void addSecGroup(SecondGroup secGroup)
	{
		this.secGroups.add(secGroup);
	}

	public int getId()
	{
		return id;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public INavForm[] getNavForms()
	{
		INavForm[] ret = new INavForm[navForms.size()];
		navForms.toArray(ret);
		return ret;
	}

	public INavSecondGroup[] getNavGroups()
	{
		INavSecondGroup[] ret = new INavSecondGroup[secGroups.size()];
		secGroups.toArray(ret);
		return ret;
	}

	public IAppImage getNavGroupImage()
	{
		return image;
	}
	public void setNavGroupImage(IAppImage image)
	{
		this.image = image;
	}
}
