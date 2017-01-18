package ims.configuration;

import java.io.Serializable;
import java.util.ArrayList;

import ims.framework.interfaces.IAppImage;
import ims.framework.interfaces.INavForm;
import ims.framework.interfaces.INavSecondGroup;

public class SecondGroup implements INavSecondGroup, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String groupName;
	private ArrayList navForms = new ArrayList();
	private int positionIndex;
	private IAppImage image;
	
	public int getId()
	{
		return id;
	}

	public String getGroupName()
	{
		return groupName;
	}

	@SuppressWarnings("unchecked")
	public INavForm[] getNavForms()
	{ 
		INavForm[] ret = new INavForm[navForms.size()];
		navForms.toArray(ret);
		return ret;
	}

	public int getPositionIndex()
	{
		return positionIndex;
	}
	
		public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public void addNavForm(NavForm navForm)
	{
		this.navForms.add(navForm);
	}

	public void setPositionIndex(int positionIndex)
	{
		this.positionIndex = positionIndex;
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
