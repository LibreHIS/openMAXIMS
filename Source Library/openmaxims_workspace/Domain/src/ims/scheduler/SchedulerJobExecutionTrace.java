package ims.scheduler;

import ims.framework.interfaces.ISchedulerJobExecutionTrace;
import ims.framework.interfaces.ITraceRecord;
import ims.framework.utils.DateTime;

import java.util.ArrayList;
import java.util.List;

public final class SchedulerJobExecutionTrace implements ISchedulerJobExecutionTrace
{
	private static final long serialVersionUID = 1L;
	private List<ITraceRecord> list = new ArrayList<ITraceRecord>();
	
	public class TraceRecord implements ITraceRecord
	{
		private DateTime dateTime;
		private String message;
		
		public DateTime getDateTime()
		{
			return dateTime;
		}
		public String getMessage()
		{
			return message;
		}
		
		public TraceRecord(String messsage)
		{
			this.dateTime = new DateTime();
			this.message = messsage;
		}
		
		@Override
		public String toString()
		{
			return dateTime.toString() + " - " + message;
		}
	}
	
	public SchedulerJobExecutionTrace()
	{		
	}
	
	public List<ITraceRecord> getTrace()
	{
		return list;
	}
	
	public void add(String message)
	{
		list.add(new TraceRecord(message));
	}
}
