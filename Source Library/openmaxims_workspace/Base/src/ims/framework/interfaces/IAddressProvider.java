package ims.framework.interfaces;

import java.util.HashMap;

import ims.framework.enumerations.LatitudeLogitudeFormat;
import ims.framework.exceptions.PresentationLogicException;

public interface IAddressProvider 
{
	public IAddress[] getAddress(IAddress searchCriteria,String searchTypeText,String listItem,String searchStatus) throws PresentationLogicException; //return a list of addresses for a given criteria
	public HashMap<String, String> computeDistances(IAddress searchCriteria,String units) throws PresentationLogicException; //return a list of IAddresses with distance from search criteria in units
	public String[] getLatitudeLogitude(IAddress searchCriteria,LatitudeLogitudeFormat latlongFormat) throws PresentationLogicException;
	public String[] getLatitudeLogitude(IAddress searchCriteria,LatitudeLogitudeFormat[] latlongFormat) throws PresentationLogicException;
	public double[] getLatitudeLogitude(IAddress searchCriteria) throws PresentationLogicException;
	public String validPostcode(String pCode) throws PresentationLogicException;
}