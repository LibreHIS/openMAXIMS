package ims.domain.exceptions;

public class UnqViolationUncheckedException extends DomainRuntimeException
{
	private static final long	serialVersionUID	= 5180532621840520835L;

	public UnqViolationUncheckedException()
	{
		super();
	}

	public UnqViolationUncheckedException(String s)
	{
		super(s);
	}

	public UnqViolationUncheckedException(Throwable cause)
	{
		super(cause);
	}

	public UnqViolationUncheckedException(String s, Throwable cause)
	{
		super(s, cause);
	}
}
