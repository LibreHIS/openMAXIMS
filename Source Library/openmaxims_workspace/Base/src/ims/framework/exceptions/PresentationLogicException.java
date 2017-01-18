package ims.framework.exceptions;

public class PresentationLogicException extends Exception
{
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public PresentationLogicException() {
		super();
	}

	/**
	 * @param message
	 */
	public PresentationLogicException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PresentationLogicException(Throwable cause) {
		super(cause);
	}

	public PresentationLogicException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 
	 * @return
	 * @deprecated use #getMessage()
	 */
	public String getSource() {
		return getMessage();
	}
}
