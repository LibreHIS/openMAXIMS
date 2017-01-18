package ims.framework.interfaces;

public interface IAppRole extends IAppRoleLight
{
	public IAppRight[] getRoleRights();	
	public INavigation getRoleNavigation();	
	public boolean hasRight(IAppRight right);
	public ITopButtonConfig getRoleTopButtonConfig();
	public boolean hasMenuActionRight(IAppForm form, IMenuAction menuAction);
	public Integer getRoleSessionTimeout();
	public Integer getRoleAutolockTimer();
	public IAlertsAccess[] getAlertsAccessList();
	public IRBACBaselineJobRole getSpineRbacRole();
}
