package ims.framework.exceptions;

public class PasswordExpiredException extends Exception
{
    private static final long serialVersionUID = 1L;
	public PasswordExpiredException(String cause)
	{
		super(cause);
	}
}
