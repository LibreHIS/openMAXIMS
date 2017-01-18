package ims.scheduler;

import ims.framework.interfaces.ISchedulerJobExecutionSummary;
import ims.framework.utils.DateTime;

public final class SchedulerJobExecutionSummary implements ISchedulerJobExecutionSummary
{
	private static final long serialVersionUID = 1L;
	
	private SchedulerJobExecutionStatus status;
	private String message;	
	private DateTime startDateTime;
	private DateTime endDateTime;
	
	
	public SchedulerJobExecutionStatus getStatus()
	{
		return status;
	}
	public String getMessage()
	{
		return message;
	}
	public DateTime getStartDateTime()
	{
		return startDateTime;
	}
	public void setStartDateTime(DateTime value)
	{
		startDateTime = value;
	}
	public DateTime getEndDateTime()
	{
		if(endDateTime == null)
			return new DateTime();
		return endDateTime;
	}
	public void setEndDateTime(DateTime value)
	{
		endDateTime = value;
	}
	
	public SchedulerJobExecutionSummary(SchedulerJobExecutionStatus status)	
	{
		this(status, "");
	}
	public SchedulerJobExecutionSummary(SchedulerJobExecutionStatus status, String message)
	{
		this.status = status;
		this.message = message;
		this.startDateTime = new DateTime();		
	}
}
