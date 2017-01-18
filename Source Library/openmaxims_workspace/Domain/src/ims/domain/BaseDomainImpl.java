package ims.domain;

import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.lookups.LookupService;
import ims.framework.FormName;
import ims.framework.enumerations.PrinterScope;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ILocation;
import ims.framework.interfaces.IPrinter;
import ims.framework.interfaces.ISystemLog;
import ims.reports.ReportEngine;
import ims.vo.ValueObject;

public abstract class BaseDomainImpl implements DomainInterface 
{
	private static final long	serialVersionUID	= 1L;
	
	DomainInterface domain;
	 
	public BaseDomainImpl(DomainInterface domain)
	{
		this.domain = domain;
	}
	public ISystemLog createSystemLogEntry(SystemLogType type, SystemLogLevel level, String message)
	{
		return this.createSystemLogEntry(type, level, message);
	}
	public int countRIERecords(FormName form, Integer patId, Integer contactId, Integer careContextId) throws DomainInterfaceException 
	{
		return domain.countRIERecords(form, patId, contactId, careContextId);
	}
	public ILocation getCurrentLocation() 
	{
		return domain.getCurrentLocation();
	}
	public IPrinter[] getPrintersForLocation(ILocation location) 
	{
		return domain.getPrintersForLocation(location);
	}
	public IPrinter getPrinterByLocationAndScope(ILocation location, PrinterScope scope)
	{
		return domain.getPrinterByLocationAndScope(location, scope);
	}
	public IPrinter[] getAllPrinters()
	{
		return domain.getAllPrinters();
	}
	public Object getHcpLiteUser() 
	{
		return domain.getHcpLiteUser();
	}
	public Object getHcpUser() 
	{
		return domain.getHcpUser();
	}
	public LookupService getLookupService() 
	{
		return domain.getLookupService();
	}
	public Object getMosUser() 
	{
		return domain.getMosUser();
	}
	public Object getMosUser(String username) 
	{
		return domain.getMosUser(username);
	}
	public ReportEngine getReportEngine() 
	{
		return domain.getReportEngine();
	}
	public void markAsRie(ValueObject vo, FormName form, Integer patId, Integer contactId, Integer careContextId, String comment) throws StaleObjectException 
	{
		domain.markAsRie(vo, form, patId, contactId, careContextId, comment);
	}
	public IAppUser getLoggedInUser()
	{
		return domain.getLoggedInUser();
	}
}
