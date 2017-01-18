package ims.scheduler;

public final class SchedulerJobExecutionStatus 
{
	private static final long serialVersionUID = 1L;
	
   	public static final SchedulerJobExecutionStatus SUCCEEDED = new SchedulerJobExecutionStatus(1);
    public static final SchedulerJobExecutionStatus FAILED = new SchedulerJobExecutionStatus(2);
	
	protected int id; 
    
	public int getId()
	{
		return id;
	}
	
    private SchedulerJobExecutionStatus(int value)
    {
        this.id = value;
    }    
    
    public String toString()
    {
    	if(id == SUCCEEDED.id)
    		return "Succeeded";
    	else if(id == FAILED.id)
    		return "Failed";
    	return "Unknown";
    }
    public static SchedulerJobExecutionStatus parse(int id)
    {
    	if(id == SUCCEEDED.id)
    		return SUCCEEDED;
    	else if(id == FAILED.id)
    		return FAILED;
    	return null;
    }
}
