package ims.framework.exceptions;

public class EngineException extends Exception
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public EngineException() 
    {
		super();
	}
	public EngineException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public EngineException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public EngineException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
