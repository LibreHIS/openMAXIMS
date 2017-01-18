package ims.rules.exceptions;

public class RulesEngineRuntimeException extends RuntimeException
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public RulesEngineRuntimeException() 
    {
		super();
	}
	public RulesEngineRuntimeException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public RulesEngineRuntimeException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public RulesEngineRuntimeException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
