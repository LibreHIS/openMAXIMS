/*
 * Created on May 14, 2004
 *
 */
package ims.domain;

import ims.domain.exceptions.DomainInterfaceException;
import ims.framework.FormName;
import ims.framework.enumerations.PrinterScope;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ILocation;
import ims.framework.interfaces.IPrinter;
import ims.framework.interfaces.ISystemLogWriter;
import ims.reports.ReportEngine;
 


/**
 * Base interface for all domain-interfaces.
 * 
 * @author gcoghlan
 *
 */
public interface DomainInterface extends ISystemLogWriter 
{
	/**
	 * Get the generic LookupService reference.
	 * @return LookupService reference
	 * @throws java.lang.UnsupportedOperationException if the implementation
	 * does not support this.
	 */
	public ims.domain.lookups.LookupService getLookupService();
	
	/**
	 * Returns the HCP Value object with this user's username
	 * or null if user is not a HCP
	 * @return
	 */
	public Object getHcpUser();
	
	/**
	 * Returns the HCP Lite form of the Hcp Lite Domain Object with user's
	 * username or null if the user is not a HCP
	 * @return
	 */
	public Object getHcpLiteUser();
	

	public Object getMosUser();
	public Object getMosUser(String username);
		
	public void markAsRie(ims.vo.ValueObject vo, ims.framework.FormName form, Integer patId, Integer contactId, Integer careContextId, String comment) throws ims.domain.exceptions.StaleObjectException;
	public int countRIERecords(FormName form, Integer patId, Integer contactId, Integer careContextId) throws DomainInterfaceException;
		
	public ReportEngine getReportEngine();
	
	public ILocation getCurrentLocation();
	public IPrinter[] getPrintersForLocation(ILocation location);	
	public IPrinter getPrinterByLocationAndScope(ILocation location, PrinterScope scope);
	public IPrinter[] getAllPrinters();
	
	public IAppUser getLoggedInUser();
}
