/*
 * Created on 27-Feb-04
 *
 */
package ims.domain.hibernate3;

import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;

import org.hibernate.HibernateException;
import org.hibernate.StaleObjectStateException;

/**
 * @author gcoghlan
 *
 */
public class Transaction implements ims.domain.Transaction
{
	private final org.hibernate.Transaction hibernateTransaction;
	private final DomainFactory domainFactory;
	
	/**
	 * 
	 */
	public Transaction(DomainFactory domainFactory) 
	{
		this.domainFactory = domainFactory;
		this.hibernateTransaction = domainFactory.getSession().beginTransaction();
	}
	
	/**
	 * Commits the transaction
	 * @throws StaleObjectException. 
	 * If the commit fails because of a timestamp mismatch, then
	 * the StaleObjectException is created with the 
	 * domain-object that caused the exception refreshed
	 * from the persistence store.
	 */
	public void commit() throws StaleObjectException {
		try {
			this.hibernateTransaction.commit();	
		}
		catch(HibernateException e) {
			if ( e instanceof StaleObjectStateException) {
				StaleObjectStateException staleException =
					(StaleObjectStateException) e;
				Integer id = (Integer) staleException.getIdentifier();
				String className = staleException.getEntityName();
				//Class domainClass = staleException.getPersistentClass();
				ims.domain.DomainObject domainObject = null;
				try {
					Class domainClass = Class.forName(className);
					domainObject = this.domainFactory.getDomainObject(domainClass, id);
					this.domainFactory.refresh(domainObject);
				}
				catch(Exception secondary) {
					// suppress secondary exception
				}
				//Use this whether domainObject is null or not.
				//If domainObject is null, will indicate object no longer exists.
				throw new StaleObjectException(domainObject, e);	
			}
			else {
				throw new DomainRuntimeException(e);
			}			
		}
	}
	
	public void rollback() {
		try 
		{
			// If session is already closed following
			// a previous rollback, we do not want to continue
			if (this.domainFactory.isSessionDiscarded())
				return;
				
			this.hibernateTransaction.rollback();
		}
		catch(HibernateException e) {
			throw new DomainRuntimeException(e);
		}
	}
	
	public boolean wasCommitted() {
		try {
			return this.hibernateTransaction.wasCommitted();
		}
		catch(HibernateException e) {
			throw new DomainRuntimeException(e);
		}
	}
	
	public boolean wasRolledBack() {
		try {
			return this.hibernateTransaction.wasRolledBack();
		}
		catch(HibernateException e) {
			throw new DomainRuntimeException(e);
		}
	}
	
	public void abandon() {
		this.domainFactory.discardSession();
	}
}
