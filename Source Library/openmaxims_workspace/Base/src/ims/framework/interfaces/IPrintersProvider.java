package ims.framework.interfaces;

import ims.framework.enumerations.PrinterScope;

public interface IPrintersProvider
{
	IPrinter[] getAllPrinters();	
	IPrinter[] getPrintersForLocation(ILocation location);	
	IPrinter getPrinterByLocationAndScope(ILocation location, PrinterScope scope);	
}
