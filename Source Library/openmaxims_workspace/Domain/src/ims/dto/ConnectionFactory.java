/*
 * Created on 16-Feb-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import ims.domain.DomainSession;

/**
 * creation of Connection objects.
 * 
 * @author gcoghlan
 *
 */
public interface ConnectionFactory {

	/**
	 * Get a connection.
	 */
	public Connection getConnection(DomainSession domainsession);

	public Connection getConnection(String username, String password, DomainSession domainsession) throws ResultException;
}
