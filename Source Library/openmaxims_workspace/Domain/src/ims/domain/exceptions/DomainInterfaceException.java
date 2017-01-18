/*
 * Created on 07-Jan-04
 *
 */
package ims.domain.exceptions;

import ims.framework.ErrorCode;

/**
 * Base class for all exceptions in the Domain framework propagated at the Domain interface level. It represents a situation that the client of the interface (i.e. the UI logic) could deal with and anticipate. For example an id is given to do a search but it doesn’t match any records. Since the UI Logic provide the id it should be able to deal with an exception that occurs because the id is wrong.
 * 
 * @author gcoghlan
 * 
 */
public class DomainInterfaceException extends DomainException
{
	private static final long	serialVersionUID	= 1L;
	private ErrorCode[] errors;
	
	public DomainInterfaceException()
	{
	}

	public DomainInterfaceException(String s)
	{
		super(s);
	}

	public DomainInterfaceException(Throwable cause)
	{
		super(cause);
	}

	public DomainInterfaceException(String s, Throwable cause)
	{
		super(s, cause);
	}

	public DomainInterfaceException(ErrorCode error)
	{
		this(new ErrorCode[]{error});
	}

	public DomainInterfaceException(ErrorCode[] errs)
	{
		this.errors = errs;
	}
	
	public ErrorCode[] getErrors()
	{
		return errors;
	}
	
	public String getMessage()
	{
		if (errors != null && errors.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			String prep = "";
			for (int i = 0; i < errors.length; i++)
			{				
				sb.append(prep + errors[i].getMessage());
				if (i == 0)
					prep = "\n";
			}
			return sb.toString();
		}
		else
		{
			return super.getMessage();
		}
	}
}
