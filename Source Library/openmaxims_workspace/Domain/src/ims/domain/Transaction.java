/*
 * Created on 26-Feb-04
 *
 */
package ims.domain;

import ims.domain.exceptions.StaleObjectException;

/**
 * Represents an database transaction.
 * 
 * @author gcoghlan
 *
 */
public interface Transaction
{
	/** commit this transaction */
	public void commit() throws StaleObjectException;
	/** rollback this transaction */
	public void rollback();
	/** 
	 * Only to be invoked when an exception has occured that cannot
	 * be recovered from. This method will discard/close any resource used by the 
	 * transaction.
	 */
	public void abandon();
	
	public boolean wasCommitted();
	public boolean wasRolledBack();
}
