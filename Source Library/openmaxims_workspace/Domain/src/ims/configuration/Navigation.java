package ims.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ims.framework.FormName;
import ims.framework.enumerations.NavigationStyle;
import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.INavForm;
import ims.framework.interfaces.INavRootGroup;
import ims.framework.interfaces.INavSecondGroup;
import ims.framework.interfaces.INavigation;
import ims.vo.ImsCloneable;

public class Navigation implements INavigation, Serializable, ImsCloneable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String navigationName;
	private ArrayList<INavRootGroup> rootGroups = new ArrayList<INavRootGroup>();
	private IAppForm patSearchForm;
	private IAppForm startupForm;
	private NavigationStyle style = NavigationStyle.Image;

	public int getId()
	{
		return id;
	}
	
	public String getNavigationName()
	{
		return navigationName;
	}
	public NavigationStyle getNavigationStyle()
	{
		return style;
	}
	public void setNavigationStyle(NavigationStyle style)
	{
		this.style = style;
	}

	public INavRootGroup[] getNavRootGroups()
	{
		INavRootGroup[] ret = new INavRootGroup[rootGroups.size()];
		rootGroups.toArray(ret);
		return ret;
	}

	public IAppForm getPatSearchForm()
	{
		return patSearchForm;
	}

	public IAppForm getStartupForm()
	{
		return startupForm;
	}

	public INavForm getNavForm(FormName form)
	{
		return getNavForm(form.getID());
	}

	public INavForm getNavForm(int formId)
	{
		for (int i = 0; i < this.rootGroups.size(); i++)
		{
			RootGroup rootGrp = (RootGroup)this.rootGroups.get(i);			
			for (int j = 0; j < rootGrp.getNavGroups().length; j++)
			{
				INavSecondGroup secGrp = rootGrp.getNavGroups()[j];
				for (int k = 0; k < secGrp.getNavForms().length; k++)
				{
					if (secGrp.getNavForms()[k].getAppForm().getFormId() == formId)
						return secGrp.getNavForms()[k];
				}
			}
			for (int k = 0; k < rootGrp.getNavForms().length; k++)
			{
				if (rootGrp.getNavForms()[k].getAppForm().getFormId() == formId)
					return rootGrp.getNavForms()[k];
			}
		}
		return null;

	}
	
	public INavForm[] getAllForms()
	{
		List<INavForm> forms = new ArrayList<INavForm>();
		
		for (int i = 0; i < this.rootGroups.size(); i++)
		{
			RootGroup rootGrp = (RootGroup)this.rootGroups.get(i);			
			for (int j = 0; j < rootGrp.getNavGroups().length; j++)
			{
				INavSecondGroup secGrp = rootGrp.getNavGroups()[j];
				for (int k = 0; k < secGrp.getNavForms().length; k++)
				{
					forms.add(secGrp.getNavForms()[k]);				
				}
			}
			for (int k = 0; k < rootGrp.getNavForms().length; k++)
			{
				forms.add(rootGrp.getNavForms()[k]);						
			}
		}
		
		INavForm[] result = new INavForm[forms.size()];
		forms.toArray(result);
		return result;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setNavigationName(String navigationName)
	{
		this.navigationName = navigationName;
	}

	public void setPatSearchForm(IAppForm patSearchForm)
	{
		this.patSearchForm = patSearchForm;
	}

	public void addRootGroup(RootGroup rootGroup)
	{
		this.rootGroups.add(rootGroup);
	}

	public void setStartupForm(IAppForm startupForm)
	{
		this.startupForm = startupForm;
	}
	
	public Object clone()
	{
		Navigation clone = new Navigation();
		
		clone.rootGroups = rootGroups;
		
		return clone;
	}
}
