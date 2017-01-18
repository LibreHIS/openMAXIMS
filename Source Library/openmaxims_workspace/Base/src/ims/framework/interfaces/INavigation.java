package ims.framework.interfaces;

import ims.framework.FormName;
import ims.framework.enumerations.NavigationStyle;

public interface INavigation
{
	public int getId();
	public String getNavigationName();
	public INavRootGroup[] getNavRootGroups();
	public IAppForm getPatSearchForm();
	public IAppForm getStartupForm();
	public INavForm getNavForm(FormName form);
	public INavForm getNavForm(int formId);
	public INavForm[] getAllForms();
	public NavigationStyle getNavigationStyle();
}
