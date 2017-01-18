/*
 * Created on 13-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.exceptions;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FormOpenException extends PresentationLogicException
{
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public FormOpenException() {
		super();
	}

	/**
	 * @param message
	 */
	public FormOpenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FormOpenException(Throwable cause) {
		super(cause);
	}

	public FormOpenException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
