/*
 * Created on 07-Jan-04
 *
 */
package ims.domain.exceptions;

/**
 * Base class for all unchecked exceptions in the Domain framework.
 * This exception type will be thrown to indicate that an exception
 * has occured in a method and the caller cannot reasonably be expected
 * to handle it.
 * For example, in a method wrapping access to the network if a problem
 * occurs with the network, the caller is unlikely to be aware of how to
 * deal with that.
 * 
 * @author gcoghlan
 *
 */
public class DomainRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public DomainRuntimeException() {
	}

	/**
	 * @param s
	 */
	public DomainRuntimeException(String s) {
		super(s);
	}

	public DomainRuntimeException(Throwable cause) {
		super(cause);
	}

	public DomainRuntimeException(String s, Throwable cause) {
		super(s, cause);
	}
}
