package ims.configuration;

import ims.framework.interfaces.IAlertsAccess;
import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.IAppRight;
import ims.framework.interfaces.IAppRole;
import ims.framework.interfaces.IMenuAction;
import ims.framework.interfaces.INavigation;
import ims.framework.interfaces.IRBACBaselineJobRole;
import ims.framework.interfaces.ITopButtonConfig;

import java.io.Serializable;
import java.util.List;

public class Role implements Comparable, Serializable, IAppRole
{
	private static final long serialVersionUID = 1L;
	
	private int	id;
	private String name;
	private boolean	isActive;
	private Navigation navigation;
	private List<IAppRight> appRights;
	private ITopButtonConfig topButtonConfig; 
	private Integer sessionTimeout;
	private Integer autolockTimer;
	private List<IAlertsAccess> alertsAccess;
	private Boolean	isRequiresPDS;
	private IRBACBaselineJobRole spineRbac;

	public Integer getAutolockTimer()
	{
		return this.autolockTimer;
	}

	public void setgetAutolockTimer(Integer autolockTimer)
	{
		this.autolockTimer = autolockTimer;
	}
	
	public Integer getSessionTimeout()
	{
		return this.sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout)
	{
		this.sessionTimeout = sessionTimeout;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Role()
	{
		this.id = 0;
		this.name = "";
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	public void setActive(boolean b)
	{
		this.isActive = b;
	}

	public List<IAppRight> getAppRights()
	{
		return appRights;
	}
	
	public List<IAlertsAccess> getAlertsAccess()
	{
		return alertsAccess;
	}

	public void setAppRights(List<IAppRight> appRights)
	{
		this.appRights = appRights;
	}

	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(Role.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A Role object cannot be compared an Object of type " + obj.getClass().getName());
		}
		Role role = (Role) obj;
		return this.getName().compareTo(role.getName());
	}

	public IAppRight[] getRoleRights()
	{
		if (getAppRights() == null)
			return new IAppRight[0];
		
		IAppRight[] ret = new IAppRight[getAppRights().size()];
		getAppRights().toArray(ret);
		return ret;
	}

	public INavigation getRoleNavigation()
	{
		return navigation;
	}

	public boolean hasRight(IAppRight right)
	{
		return true;
	}

	public void setNavigation(Navigation navigation)
	{
		this.navigation = navigation;
	}
	public ITopButtonConfig getRoleTopButtonConfig() 
	{
		return topButtonConfig;
	}
	public boolean hasMenuActionRight(IAppForm form, IMenuAction menuAction)
	{
		return false;
	}

	public Integer getRoleSessionTimeout() 
	{
		return sessionTimeout;
	}

	public Integer getRoleAutolockTimer() 
	{	
		return autolockTimer;
	}

	public IAlertsAccess[] getAlertsAccessList() 
	{
		if (getAlertsAccess() == null)
			return new IAlertsAccess[0];
		
		IAlertsAccess[] ret = new IAlertsAccess[getAlertsAccess().size()];
		getAlertsAccess().toArray(ret);
		return ret;		
	}
	
	public Boolean getRequiresPDS() 
	{	
		return isRequiresPDS;
	}	
	public IRBACBaselineJobRole getSpineRbacRole() 
	{	
		return spineRbac;
	}
	public void setSpineRbacRole(IRBACBaselineJobRole value)
	{
		this.spineRbac = value;
	}
}
