package ims.scheduler;

import ims.framework.interfaces.IAppRoleLight;
import ims.framework.interfaces.IAppUser;
import ims.framework.utils.DateTime;

public class SchedulerUser implements IAppUser
{
	public IAppRoleLight getAppRole(int roleId)
	{
		return null;
	}
	public IAppRoleLight[] getAppRoles()
	{
		return null;
	}
	public String getClearPassword()
	{
		return null;
	}
	public Boolean getDebugMode()
	{
		return null;
	}
	public DateTime getEffectiveFrom()
	{
		return null;
	}
	public DateTime getEffectiveTo()
	{
		return null;
	}
	public String getEncodedPassword()
	{
		return null;
	}
	public Integer getHcpId()
	{
		return null;
	}
	public String getHostName()
	{
		return null;
	}
	public DateTime getLoginTime()
	{
		return null;
	}
	public Integer getMosId()
	{
		return null;
	}
	public String getNotificationEmailAddress()
	{
		return null;
	}
	public String[] getNotificationMobileDeviceTokenIds()
	{
		return null;
	}
	public String getNotificationMobilePhone()
	{
		return null;
	}
	public String getPassword()
	{
		return null;
	}
	public DateTime getPwdExpDate()
	{
		return null;
	}
	public String getTheme()
	{
		return null;
	}
	public int getUserId()
	{
		return 0;
	}
	public String[] getUserPreviousPasswords()
	{
		return null;
	}
	public String getUserRealName()
	{
		return "IMS Task Scheduler";
	}
	public String getUsername()
	{
		return "IMS Scheduler";
	}
	public boolean isHcpChecked()
	{
		return false;
	}
	public void setClearPassword(String clearPass)
	{
	}
	public void setHcpChecked(boolean value)
	{
	}
	public void setHcpId(Integer value)
	{
	}
	public void setHostName(String value)
	{
	}
	public void setLoginTime(DateTime time)
	{
	}
	public void setUserRealName(String realName)
	{
	}
	public boolean useExternalAuthentication()
	{
		return false;
	}
	public String getLDAPUsername() 
	{	
		return null;
	}
	public String getLDAPPassword() 
	{	
		return null;
	}	
	public boolean isLocked() 
	{		
		return false;
	}	
	public void setLocked(boolean value) 
	{			
	}
	public String getsdsUserId()
	{
		return null;
	}
}
