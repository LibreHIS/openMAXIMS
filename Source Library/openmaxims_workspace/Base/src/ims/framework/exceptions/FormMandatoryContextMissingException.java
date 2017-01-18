package ims.framework.exceptions;

/**
 * @author mmihalec
 */
public class FormMandatoryContextMissingException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    public FormMandatoryContextMissingException() 
	{
	}

	public FormMandatoryContextMissingException(String s) 
	{
		super(s);
	}

	public FormMandatoryContextMissingException(Throwable cause) 
	{
		super(cause);
	}

	public FormMandatoryContextMissingException(String s, Throwable cause) 
	{
		super(s, cause);
	}
}
