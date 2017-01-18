package ims.framework.interfaces;

import ims.framework.enumerations.SystemLogType;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.utils.DateTime;

public interface ISystemLogProvider
{	
	ISystemLog save(DateTime dateTime, SystemLogType type, SystemLogLevel level, String server, String user, String source, String computer, String userAgent, String sessionId, String message);	
}
