package ims.framework.interfaces;

import java.io.Serializable;

import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;

public interface ISystemLogWriter extends Serializable
{
	ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String message);
}
