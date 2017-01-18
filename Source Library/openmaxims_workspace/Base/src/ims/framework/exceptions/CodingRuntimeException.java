package ims.framework.exceptions;

public class CodingRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
	public CodingRuntimeException() 
	{
	}

	public CodingRuntimeException(String s) 
	{
		super(s);
	}

	public CodingRuntimeException(Throwable cause) 
	{
		super(cause);
	}

	public CodingRuntimeException(String s, Throwable cause) 
	{
		super(s, cause);
	}
}
