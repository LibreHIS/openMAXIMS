package ims.rules.exceptions;

public class RulesEngineSerializationException extends Exception
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public RulesEngineSerializationException() 
    {
		super();
	}
	public RulesEngineSerializationException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public RulesEngineSerializationException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public RulesEngineSerializationException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
