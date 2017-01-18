package ims.scheduler;

import ims.framework.interfaces.IConfiguredScheduledJob;
import ims.framework.interfaces.IScheduledJob;

public interface IScheduledJobsProvider
{
	IScheduledJob[] getAvailableScheduledJobs();
	IConfiguredScheduledJob[] getConfiguredScheduledJobs();
	IConfiguredScheduledJob getConfiguredScheduledJob(int id);
	void saveExecutionSummaryAndTrace(IConfiguredScheduledJob job, SchedulerJobExecutionSummary summary, SchedulerJobExecutionTrace trace);
	void cleanUpConfiguredScheduledJobSettings(int id);
}
