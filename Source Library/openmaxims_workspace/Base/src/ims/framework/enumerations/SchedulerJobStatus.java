package ims.framework.enumerations;

public final class SchedulerJobStatus 
{
	private static final long serialVersionUID = 1L;
	
	public static final SchedulerJobStatus UNKNOWN = new SchedulerJobStatus(1, "Unknown");   	
    public static final SchedulerJobStatus IDLE = new SchedulerJobStatus(2, "Idle");
    public static final SchedulerJobStatus ERROR = new SchedulerJobStatus(3, "Error");
    public static final SchedulerJobStatus COMPLETE = new SchedulerJobStatus(4, "Complete");
    public static final SchedulerJobStatus PAUSED = new SchedulerJobStatus(5, "Paused");
    public static final SchedulerJobStatus BLOCKED = new SchedulerJobStatus(6, "Running");
	
	private int id; 
	private String text;
    
	public int getId()
	{
		return id;
	}
	public String getText()
	{
		return text;
	}
	
    private SchedulerJobStatus(int value, String text)
    {
        this.id = value;
        this.text = text;
    }    
    
    public String toString()
    {    	
    	return text;
    }    
}
