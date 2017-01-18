package ims.configuration;

import ims.framework.enumerations.SortOrder;
import ims.framework.interfaces.IAppRoleLight;
import ims.framework.interfaces.IAppUser;
import ims.framework.utils.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User implements Serializable, IAppUser
{ 
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				username;
	private String				clearPassword;
	private String				encodedPassword;
	private String				password;
	private String				ldapUsername;
	private String				ldapPassword;
	private String				realName;
	private String				hostName;
	private DateTime			pwdExpiryDate;
	private DateTime			effectiveFrom;
	private DateTime			effectiveTo;
	private Set					roles;
	private String				theme;
	private Integer				hcpId;
	private Integer				mosId;
	private boolean				hcpChecked;
	private boolean				isActive;
	private DateTime			loginTime;
	private Integer				autoLockScreenInterval;
	private Boolean				debugMode;
	private Boolean				useExternalAuthentication;
	private String 				notificationEmailAddress;	
	private String 				notificationMobilePhone;
	private String[] 			notificationMobileDeviceTokenIds;
	private boolean				isLocked;
	private String				sdsUserId;

	public String getUsername()
	{
		return this.username;
	}	
	public void setUsername(String login)
	{
		this.username = login;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String hash)
	{
		this.password = hash;
	}

	public String getEncodedPassword()
	{
		return this.encodedPassword;
	}
	
	public void setEncodedPassword(String value)
	{
		this.encodedPassword = value;
	}
	
	public String getUserRealName()
	{
		return this.realName;
	}

	public void setUserRealName(String realName)
	{
		this.realName = realName;
	}

	public DateTime getPwdExpDate()
	{
		return this.pwdExpiryDate;
	}

	public void setPwdExpDate(DateTime value)
	{
		this.pwdExpiryDate = value;
	}

	public Set getRoles()
	{
		return this.roles;
	}

	public void setRoles(Set roles)
	{
		this.roles = roles;
	}

	public String getTheme()
	{
		return this.theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public int getUserId()
	{
		return this.id;
	}

	public void setUserId(int id)
	{
		this.id = id;
	}

	public String getClearPassword()
	{
		return this.clearPassword;
	}

	public void setClearPassword(String string)
	{
		this.clearPassword = string;
	}
	public String getNotificationEmailAddress()
	{
		return this.notificationEmailAddress;
	}
	public void setNotificationEmailAddress(String value)
	{
		this.notificationEmailAddress = value;
	}
	public String getNotificationMobilePhone()
	{
		return this.notificationMobilePhone;
	}
	public void setNotificationMobilePhone(String value)
	{
		this.notificationMobilePhone = value;
	}
	public String[] getNotificationMobileDeviceTokenIds()
	{
		return this.notificationMobileDeviceTokenIds;
	}
	public void setNotificationMobileDeviceTokenIds(String[] value)
	{
		this.notificationMobileDeviceTokenIds = value;
	}
	public Integer getHcpId()
	{
		return this.hcpId;
	}

	public void setHcpId(Integer i)
	{
		this.hcpId = i;
	}

	public boolean isHcpChecked()
	{
		return this.hcpChecked;
	}

	public void setHcpChecked(boolean b)
	{
		this.hcpChecked = b;
	}

	public DateTime getEffectiveFrom()
	{
		return this.effectiveFrom;
	}

	public DateTime getEffectiveTo()
	{
		return this.effectiveTo;
	}

	public void setEffectiveFrom(DateTime time)
	{
		this.effectiveFrom = time;
	}

	public void setEffectiveTo(DateTime time)
	{
		this.effectiveTo = time;
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	public void setActive(boolean b)
	{
		this.isActive = b;
	}

	public Integer getAutoLockScreenInterval()
	{
		return autoLockScreenInterval;
	}

	public void setAutoLockScreenInterval(Integer value)
	{
		autoLockScreenInterval = value;
	}

	public Integer getMosId()
	{
		return this.mosId;
	}

	public void setMosId(Integer integer)
	{
		this.mosId = integer;
	}

	public DateTime getLoginTime()
	{
		return this.loginTime;
	}

	public void setLoginTime(DateTime date)
	{
		this.loginTime = date;
	}

	public void setDebugMode(Boolean value)
	{
		debugMode = value;
	}

	public Boolean getDebugMode()
	{
		return debugMode;
	}
	
	public void setUseExternalAuthentication(Boolean value)
	{
		useExternalAuthentication = value;
	}

	public boolean useExternalAuthentication()
	{
		return useExternalAuthentication == null ? false : useExternalAuthentication;
	}

	public User()
	{
		this.id = -1;
		this.username = "";
		this.password = "";
		this.realName = "";
		this.theme = "";
		this.clearPassword = "";
		this.hcpId = null;
		this.hcpChecked = false;
	}

	public User copy()
	{
		User user = new User();
		user.setUserId(this.getUserId());
		user.setUsername(this.getUsername());
		user.setEncodedPassword(this.getEncodedPassword());
		user.setClearPassword(this.getClearPassword());
		user.setPassword(this.getPassword());
		user.setUserRealName(this.getUserRealName());
		user.setPwdExpDate(this.getPwdExpDate());
		user.setEffectiveFrom(this.getEffectiveFrom());
		user.setEffectiveTo(this.getEffectiveTo());
		user.setTheme(this.getTheme());
		user.setHcpId(this.getHcpId());
		user.setMosId(this.getMosId());
		user.setHcpChecked(this.isHcpChecked());
		user.setActive(this.isActive());
		user.setRoles(this.getRoles());
		user.setDebugMode(this.getDebugMode());		
		user.setNotificationEmailAddress(this.getNotificationEmailAddress());
		user.setNotificationMobilePhone(this.getNotificationMobilePhone());
		return user;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}

	@SuppressWarnings("unchecked")
	private List getSortedRoles()
	{
		if (this.roles == null)
			return null;

		ArrayList sortedRoles = new ArrayList(this.roles);
		Collections.sort(sortedRoles, new RoleComparator());
		return sortedRoles;
	}

	private class RoleComparator implements Comparator
	{
		private int	direction	= 1;

		public RoleComparator()
		{
			this(SortOrder.ASCENDING);
		}

		public RoleComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				this.direction = -1;
			}
		}

		public int compare(Object obj1, Object obj2)
		{
			Role voObj1 = (Role) obj1;
			Role voObj2 = (Role) obj2;
			return this.direction * (voObj1.compareTo(voObj2));
		}
	}

	@SuppressWarnings("unchecked")
	public IAppRoleLight[] getAppRoles()
	{
		List sortedRoles = getSortedRoles();
		if (sortedRoles == null)
			return new IAppRoleLight[0];

		IAppRoleLight[] ret = new IAppRoleLight[sortedRoles.size()];
		sortedRoles.toArray(ret);
		return ret;
	}

	public IAppRoleLight getAppRole(int roleId)
	{
		Iterator iter = this.getRoles().iterator();
		while (iter.hasNext())
		{
			IAppRoleLight role = (IAppRoleLight) iter.next();
			if (role.getId() == roleId)
				return role;
		}
		return null;
	}

	public String[] getUserPreviousPasswords()
	{
		return new String[0];
	}
	public String getLDAPUsername() 
	{
		return this.ldapUsername;
	}
	public String getLDAPPassword() 
	{
		return this.ldapPassword;
	}	
	public boolean isLocked() 
	{
		return this.isLocked;
	}
	public void setLocked(boolean b)
	{
		this.isLocked = b;
	}
	public String getsdsUserId() 
	{
		return this.sdsUserId;
	}
	public void setsdsUserId(String value)
	{
		this.sdsUserId = value;
	}
}
