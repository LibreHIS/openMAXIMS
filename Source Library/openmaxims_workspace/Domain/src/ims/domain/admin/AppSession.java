package ims.domain.admin;

import ims.framework.utils.DateTime;

public abstract class AppSession
{	
	public abstract String getUserName();	
	public abstract String getRealName();
	public abstract DateTime getStartTime();
	public abstract String getRoleName();	
	public abstract String getCurrentForm();
	public abstract String getSessionId();	
	public abstract long getIdleTime();	
	public abstract int getSessionTimeOut();
	public abstract void setSessionTimeOut(int timeout);	
	public abstract boolean loggingEnabled();
	public abstract boolean enableSessionLogging();
	public abstract void disableSessionLogging();
	public abstract String getLogFileContent(int tailSize);
	public abstract String getLogFileName();
	public abstract String getLogFileLocation();
	public abstract int getLogFileSize();	
	public abstract String getRemoteHost();
	public abstract String getRemoteAddress();
	public abstract void endSession();
	public abstract void joinCcowContext();
	public abstract void leaveCcowContext();
}
