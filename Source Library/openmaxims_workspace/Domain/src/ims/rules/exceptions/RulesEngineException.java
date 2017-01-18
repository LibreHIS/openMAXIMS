package ims.rules.exceptions;

public class RulesEngineException extends Exception
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public RulesEngineException() 
    {
		super();
	}
	public RulesEngineException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public RulesEngineException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public RulesEngineException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
