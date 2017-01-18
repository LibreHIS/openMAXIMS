/*
 * Created on 12-Feb-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import ims.framework.utils.DateTime;

/**
 * @author gcoghlan
 */
public interface DTODomain
{
	/**
	 * Instantiates + initializes an instance of the provided Class.
	 * @param clazz
	 * @return
	 * @throw RuntimeException if the clazz does not support the expected
	 * initialisation methods.
	 */
	public Object getDTOInstance(Class clazz);
	
	/**
	 * Get the generic LookupService reference.
	 * @return LookupService reference
	 * @throws java.lang.UnsupportedOperationException if the implementation
	 * does not support this.
	 */
	public ims.domain.lookups.LookupService getLookupService();


	/**
	 * Get the Date from the DTO server.
	 * @author gcoghlan
	 *
	 */
	public DateTime getDateTime();
	
	/**
	 * Returns the HCP Value object with this user's username
	 * or null if user is not a HCP
	 * @return
	 */
	public Object getHcpUser();
	
	public Object getMosUser();

}
