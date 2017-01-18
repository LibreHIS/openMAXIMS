package ims.framework.interfaces;

import ims.framework.utils.DateTime;

public interface IAppUser
{
	public int getUserId();
	public String getUsername();
	public String getPassword();
	public String getEncodedPassword();
	public String getLDAPUsername();
	public String getLDAPPassword();
	public String getTheme();
	public DateTime getPwdExpDate();
	public DateTime getEffectiveFrom();
	public DateTime getEffectiveTo();
	public IAppRoleLight[] getAppRoles();
	public IAppRoleLight getAppRole(int roleId);
	public Integer getMosId();
	public Boolean getDebugMode();
	public boolean useExternalAuthentication();
	public String getNotificationEmailAddress();
	public String getNotificationMobilePhone();
	public String[] getNotificationMobileDeviceTokenIds();
	public boolean isLocked();
	public String getsdsUserId();
	
	//Methods to handle non-persistent fields
	public String getClearPassword();
	public String getUserRealName();
	public DateTime getLoginTime();
	public String getHostName();
	public boolean isHcpChecked();
	public Integer getHcpId();

	public void setClearPassword(String clearPass);
	public void setUserRealName(String realName);
	public void setLoginTime(DateTime time);
	public void setHcpChecked(boolean value);
	public void setHcpId(Integer value);
	public void setHostName(String value);
	public void setLocked(boolean value);

	public String[] getUserPreviousPasswords();
}
