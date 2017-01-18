package ims.domain.exceptions;

public class UniqueKeyViolationException extends DomainException
{
	private static final long	serialVersionUID	= 808278529078743940L;

	public UniqueKeyViolationException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public UniqueKeyViolationException(String s)
	{
		super(s);
	}

	public UniqueKeyViolationException(Throwable cause)
	{
		super(cause);
	}

	public UniqueKeyViolationException(String s, Throwable cause)
	{
		super(s, cause);
	}
}
