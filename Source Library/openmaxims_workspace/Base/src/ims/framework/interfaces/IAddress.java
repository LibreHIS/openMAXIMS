package ims.framework.interfaces;

public interface IAddress
{
	//Setters 
	public void setAddressBuildingName(String buildingName);
	public void setAddressBuildingNumber(String buildingNumber); 
	public void setAddressLocality(String locality); 
	public void setAddressStreet(String street); 
	public void setAddressPostTown(String postTown); 
	public void setAddressCounty(String county); 
	public void setAddressPostCode(String postCode);
	public void setPCTcode(String pctCode);
	public void setAddressSearchText(String addressSearchText);
	public void setAddressOrganisation(String orgCode);

	//getters 
	public String getAddressBuildingName(); 
	public String getAddressBuildingNumber(); 
	public String getAddressLocality(); 
	public String getAddressStreet(); 
	public String getAddressPostTown(); 
	public String getAddressCounty(); 
	public String getAddressPostCode(); 
	public String getAddressSearchText();
	public String getPhone();
	public String getPctCode();
	public String getAddressOrganisation();
}
