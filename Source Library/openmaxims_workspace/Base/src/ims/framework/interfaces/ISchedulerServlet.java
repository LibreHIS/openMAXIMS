package ims.framework.interfaces;

import ims.framework.enumerations.SchedulerJobStatus;
import ims.framework.utils.DateTime;

public interface ISchedulerServlet
{
	boolean exist(int jobId) throws Exception;
	void add(int jobId) throws Exception;
	void update(int jobId) throws Exception;
	void delete(int jobId) throws Exception;
	void run(int jobId) throws Exception;
	void pause(int jobId) throws Exception;
	void resume(int jobId) throws Exception;
	SchedulerJobStatus getStatus(int jobId) throws Exception;
	ISchedulerJob getJob(int jobId);
	void addNotification(IQueuedNotification queuedNotification, DateTime dateTime) throws Exception; 
}
