package ims.rules.exceptions;

public class RulesEngineCompilationException extends Exception
{
	private String message = ""; 
    private static final long serialVersionUID = 1L;
	
    public RulesEngineCompilationException() 
    {
		super();
	}
	public RulesEngineCompilationException(String message) 
	{		
		super(message);
		
		this.message = message;
	}
	public RulesEngineCompilationException(Throwable cause) 
	{		
		super(cause);
		
		this.message = cause.getMessage();
	}
	public RulesEngineCompilationException(String message, Throwable cause)
	{		
		super(message, cause);
		
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
