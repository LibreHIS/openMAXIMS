package ims.rules.exceptions;

public class RulesEngineDeserializationException extends Exception
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public RulesEngineDeserializationException() 
    {
		super();
	}
	public RulesEngineDeserializationException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public RulesEngineDeserializationException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public RulesEngineDeserializationException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
