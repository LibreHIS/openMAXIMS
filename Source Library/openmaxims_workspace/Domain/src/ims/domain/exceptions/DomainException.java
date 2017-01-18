/*
 * Created on 07-Jan-04
 *
 */
package ims.domain.exceptions;

/**
 * Base class for all checked exceptions in the Domain framework.
 * 
 * @author gcoghlan
 *
 */
public class DomainException extends Exception {
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public DomainException() {
	}

	/**
	 * @param s
	 */
	public DomainException(String s) {
		super(s);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}

	public DomainException(String s, Throwable cause) {
		super(s, cause);
	}
}
