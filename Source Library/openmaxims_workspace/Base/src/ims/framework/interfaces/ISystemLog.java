package ims.framework.interfaces;

import ims.framework.enumerations.SystemLogType;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.utils.DateTime;

public interface ISystemLog
{
	int getSystemLogEventId();
	DateTime getSystemLogEventDateTime();
	SystemLogType getSystemLogEventType();	
	SystemLogLevel getSystemLogEventLevel();
	String getSystemLogEventUser();
	String getSystemLogEventSource();
	String getSystemLogEventComputer();
	String getSystemLogEventUserAgent();
	String getSystemLogEventSessionId();
	String getSystemLogEventMessage();
	String getSystemLogEventApplicationServer();
}
