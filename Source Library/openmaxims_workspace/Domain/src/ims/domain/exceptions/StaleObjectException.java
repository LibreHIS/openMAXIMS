/*
 * Created on 05-Feb-04
 *
 */
package ims.domain.exceptions;

import ims.domain.DomainObject;

/**
 * Thrown when the timestamp check on a DomainObject failed.
 * 
 */
public class StaleObjectException extends DomainException
{
    private static final long serialVersionUID = 1L;
	private DomainObject staleObject;

	/**
	 * 
	 */
	public StaleObjectException(DomainObject staleObject)
	{
		super();
		this.staleObject = staleObject;
	}

	/**
	 * @param s
	 */
	public StaleObjectException(DomainObject staleObject, String s)
	{
		super(s);
		this.staleObject = staleObject;
	}

	/**
	 * @param cause
	 */
	public StaleObjectException(DomainObject staleObject, Throwable cause)
	{
		super(cause);
		this.staleObject = staleObject;
	}

	/**
	 * @param s
	 * @param cause
	 */
	public StaleObjectException(DomainObject staleObject, String s, Throwable cause)
	{
		super(s, cause);
		this.staleObject = staleObject;
	}

	/**
	 * Get the stale object that caused the exception.
	 * @return the refreshed object that caused the exception.
	 */
	public DomainObject getStaleObject() 
	{
		return this.staleObject;
	}
}
