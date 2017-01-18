package ims.domain.exceptions;

public class DTODomainInterfaceException extends DomainInterfaceException
{
	private static final long	serialVersionUID	= 1L;
	private int errorCode;
		
	public DTODomainInterfaceException(int errorCode)
	{
		this.errorCode = errorCode;
	}
	public DTODomainInterfaceException(int errorCode, String errorMessage)
	{		
		super(errorMessage);
		
		this.errorCode = errorCode;
	}
	
	public int getErrorCode()
	{
		return errorCode;
	}
}
