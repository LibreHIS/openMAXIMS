/*
 * Created on 16-Feb-04
 *
 */
package ims.dto;

import ims.domain.exceptions.DomainRuntimeException;

/**
 * An exception due to a bad result on a dto connection.
 * 
 * @author gcoghlan
 *
 */
public class ResultException extends DomainRuntimeException
{
	private static final long serialVersionUID = 1L;
	
	private Result result;
	
	/**
	 * 
	 */
	public ResultException(Result result)
	{
		super();
		this.result = result;
	}
	
	
	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage()
	{
		return result.toString();
	}

	public Result getResult()
	{
		return result;
	}
}
