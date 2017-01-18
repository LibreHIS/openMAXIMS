package ims.framework.interfaces;

import ims.framework.utils.DateTime;

public interface ISchedulerJob
{
	DateTime getJobStartDateTime();
	ISchedulerJobExecutionSummary getExecutionSummary();
	ISchedulerJobExecutionTrace getExecutionTrace();
}
