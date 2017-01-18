package ims.framework.interfaces;

import ims.framework.utils.DateTime;

public interface ISchedulerJobExecutionSummary
{
	String getMessage();
	DateTime getStartDateTime();
	DateTime getEndDateTime();
}
