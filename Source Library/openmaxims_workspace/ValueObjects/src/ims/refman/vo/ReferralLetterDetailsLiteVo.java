//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo;

/**
 * Linked to core.clinical.ReferralLetterDetails business object (ID: 1003100093).
 */
public class ReferralLetterDetailsLiteVo extends ims.core.clinical.vo.ReferralLetterDetailsRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public ReferralLetterDetailsLiteVo()
	{
	}
	public ReferralLetterDetailsLiteVo(Integer id, int version)
	{
		super(id, version);
	}
	public ReferralLetterDetailsLiteVo(ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.service = bean.getService() == null ? null : bean.getService().buildVo();
		this.referrertype = bean.getReferrerType() == null ? null : ims.core.vo.lookups.SourceOfReferral.buildLookup(bean.getReferrerType());
		this.alliedhealthprofname = bean.getAlliedHealthProfName();
		this.dateofreferral = bean.getDateOfReferral() == null ? null : bean.getDateOfReferral().buildDate();
		this.datereferralreceived = bean.getDateReferralReceived() == null ? null : bean.getDateReferralReceived().buildDate();
		this.transport = bean.getTransport() == null ? null : ims.scheduling.vo.lookups.ApptTransportType.buildLookup(bean.getTransport());
		this.specialrequirements = bean.getSpecialRequirements();
		this.isdadreferral = bean.getIsDADReferral();
		this.location = bean.getLocation() == null ? null : bean.getLocation().buildVo();
		this.enddatekpi = bean.getEndDateKPI() == null ? null : bean.getEndDateKPI().buildDate();
		this.enddateemailkpi = bean.getEndDateEmailKPI() == null ? null : bean.getEndDateEmailKPI().buildDate();
		this.enddatepaperkpi = bean.getEndDatePaperKPI() == null ? null : bean.getEndDatePaperKPI().buildDate();
		this.end18ww = bean.getEnd18WW() == null ? null : bean.getEnd18WW().buildDate();
		this.consultant = bean.getConsultant() == null ? null : bean.getConsultant().buildVo();
		this.function = bean.getFunction() == null ? null : bean.getFunction().buildVo();
		this.originaldateofreferral = bean.getOriginalDateOfReferral() == null ? null : bean.getOriginalDateOfReferral().buildDate();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.service = bean.getService() == null ? null : bean.getService().buildVo(map);
		this.referrertype = bean.getReferrerType() == null ? null : ims.core.vo.lookups.SourceOfReferral.buildLookup(bean.getReferrerType());
		this.alliedhealthprofname = bean.getAlliedHealthProfName();
		this.dateofreferral = bean.getDateOfReferral() == null ? null : bean.getDateOfReferral().buildDate();
		this.datereferralreceived = bean.getDateReferralReceived() == null ? null : bean.getDateReferralReceived().buildDate();
		this.transport = bean.getTransport() == null ? null : ims.scheduling.vo.lookups.ApptTransportType.buildLookup(bean.getTransport());
		this.specialrequirements = bean.getSpecialRequirements();
		this.isdadreferral = bean.getIsDADReferral();
		this.location = bean.getLocation() == null ? null : bean.getLocation().buildVo(map);
		this.enddatekpi = bean.getEndDateKPI() == null ? null : bean.getEndDateKPI().buildDate();
		this.enddateemailkpi = bean.getEndDateEmailKPI() == null ? null : bean.getEndDateEmailKPI().buildDate();
		this.enddatepaperkpi = bean.getEndDatePaperKPI() == null ? null : bean.getEndDatePaperKPI().buildDate();
		this.end18ww = bean.getEnd18WW() == null ? null : bean.getEnd18WW().buildDate();
		this.consultant = bean.getConsultant() == null ? null : bean.getConsultant().buildVo(map);
		this.function = bean.getFunction() == null ? null : bean.getFunction().buildVo(map);
		this.originaldateofreferral = bean.getOriginalDateOfReferral() == null ? null : bean.getOriginalDateOfReferral().buildDate();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
	}
	public Object getFieldValueByFieldName(String fieldName)
	{
		if(fieldName == null)
			throw new ims.framework.exceptions.CodingRuntimeException("Invalid field name");
		fieldName = fieldName.toUpperCase();
		if(fieldName.equals("SERVICE"))
			return getService();
		if(fieldName.equals("REFERRERTYPE"))
			return getReferrerType();
		if(fieldName.equals("ALLIEDHEALTHPROFNAME"))
			return getAlliedHealthProfName();
		if(fieldName.equals("DATEOFREFERRAL"))
			return getDateOfReferral();
		if(fieldName.equals("DATEREFERRALRECEIVED"))
			return getDateReferralReceived();
		if(fieldName.equals("TRANSPORT"))
			return getTransport();
		if(fieldName.equals("SPECIALREQUIREMENTS"))
			return getSpecialRequirements();
		if(fieldName.equals("ISDADREFERRAL"))
			return getIsDADReferral();
		if(fieldName.equals("LOCATION"))
			return getLocation();
		if(fieldName.equals("ENDDATEKPI"))
			return getEndDateKPI();
		if(fieldName.equals("ENDDATEEMAILKPI"))
			return getEndDateEmailKPI();
		if(fieldName.equals("ENDDATEPAPERKPI"))
			return getEndDatePaperKPI();
		if(fieldName.equals("END18WW"))
			return getEnd18WW();
		if(fieldName.equals("CONSULTANT"))
			return getConsultant();
		if(fieldName.equals("FUNCTION"))
			return getFunction();
		if(fieldName.equals("ORIGINALDATEOFREFERRAL"))
			return getOriginalDateOfReferral();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getServiceIsNotNull()
	{
		return this.service != null;
	}
	public ims.core.vo.ServiceLiteVo getService()
	{
		return this.service;
	}
	public void setService(ims.core.vo.ServiceLiteVo value)
	{
		this.isValidated = false;
		this.service = value;
	}
	public boolean getReferrerTypeIsNotNull()
	{
		return this.referrertype != null;
	}
	public ims.core.vo.lookups.SourceOfReferral getReferrerType()
	{
		return this.referrertype;
	}
	public void setReferrerType(ims.core.vo.lookups.SourceOfReferral value)
	{
		this.isValidated = false;
		this.referrertype = value;
	}
	public boolean getAlliedHealthProfNameIsNotNull()
	{
		return this.alliedhealthprofname != null;
	}
	public String getAlliedHealthProfName()
	{
		return this.alliedhealthprofname;
	}
	public static int getAlliedHealthProfNameMaxLength()
	{
		return 150;
	}
	public void setAlliedHealthProfName(String value)
	{
		this.isValidated = false;
		this.alliedhealthprofname = value;
	}
	public boolean getDateOfReferralIsNotNull()
	{
		return this.dateofreferral != null;
	}
	public ims.framework.utils.Date getDateOfReferral()
	{
		return this.dateofreferral;
	}
	public void setDateOfReferral(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.dateofreferral = value;
	}
	public boolean getDateReferralReceivedIsNotNull()
	{
		return this.datereferralreceived != null;
	}
	public ims.framework.utils.Date getDateReferralReceived()
	{
		return this.datereferralreceived;
	}
	public void setDateReferralReceived(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.datereferralreceived = value;
	}
	public boolean getTransportIsNotNull()
	{
		return this.transport != null;
	}
	public ims.scheduling.vo.lookups.ApptTransportType getTransport()
	{
		return this.transport;
	}
	public void setTransport(ims.scheduling.vo.lookups.ApptTransportType value)
	{
		this.isValidated = false;
		this.transport = value;
	}
	public boolean getSpecialRequirementsIsNotNull()
	{
		return this.specialrequirements != null;
	}
	public String getSpecialRequirements()
	{
		return this.specialrequirements;
	}
	public static int getSpecialRequirementsMaxLength()
	{
		return 255;
	}
	public void setSpecialRequirements(String value)
	{
		this.isValidated = false;
		this.specialrequirements = value;
	}
	public boolean getIsDADReferralIsNotNull()
	{
		return this.isdadreferral != null;
	}
	public Boolean getIsDADReferral()
	{
		return this.isdadreferral;
	}
	public void setIsDADReferral(Boolean value)
	{
		this.isValidated = false;
		this.isdadreferral = value;
	}
	public boolean getLocationIsNotNull()
	{
		return this.location != null;
	}
	public ims.core.vo.LocationLiteVo getLocation()
	{
		return this.location;
	}
	public void setLocation(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.location = value;
	}
	public boolean getEndDateKPIIsNotNull()
	{
		return this.enddatekpi != null;
	}
	public ims.framework.utils.Date getEndDateKPI()
	{
		return this.enddatekpi;
	}
	public void setEndDateKPI(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.enddatekpi = value;
	}
	public boolean getEndDateEmailKPIIsNotNull()
	{
		return this.enddateemailkpi != null;
	}
	public ims.framework.utils.Date getEndDateEmailKPI()
	{
		return this.enddateemailkpi;
	}
	public void setEndDateEmailKPI(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.enddateemailkpi = value;
	}
	public boolean getEndDatePaperKPIIsNotNull()
	{
		return this.enddatepaperkpi != null;
	}
	public ims.framework.utils.Date getEndDatePaperKPI()
	{
		return this.enddatepaperkpi;
	}
	public void setEndDatePaperKPI(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.enddatepaperkpi = value;
	}
	public boolean getEnd18WWIsNotNull()
	{
		return this.end18ww != null;
	}
	public ims.framework.utils.Date getEnd18WW()
	{
		return this.end18ww;
	}
	public void setEnd18WW(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.end18ww = value;
	}
	public boolean getConsultantIsNotNull()
	{
		return this.consultant != null;
	}
	public ims.core.vo.HcpMinVo getConsultant()
	{
		return this.consultant;
	}
	public void setConsultant(ims.core.vo.HcpMinVo value)
	{
		this.isValidated = false;
		this.consultant = value;
	}
	public boolean getFunctionIsNotNull()
	{
		return this.function != null;
	}
	public ims.core.vo.ServiceFunctionLiteVo getFunction()
	{
		return this.function;
	}
	public void setFunction(ims.core.vo.ServiceFunctionLiteVo value)
	{
		this.isValidated = false;
		this.function = value;
	}
	public boolean getOriginalDateOfReferralIsNotNull()
	{
		return this.originaldateofreferral != null;
	}
	public ims.framework.utils.Date getOriginalDateOfReferral()
	{
		return this.originaldateofreferral;
	}
	public void setOriginalDateOfReferral(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.originaldateofreferral = value;
	}
	public boolean isValidated()
	{
		if(this.isBusy)
			return true;
		this.isBusy = true;
	
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		if(this.consultant != null)
		{
			if(!this.consultant.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		this.isBusy = false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(this.isBusy)
			return null;
		this.isBusy = true;
	
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		if(this.service == null)
			listOfErrors.add("Service is mandatory");
		if(this.alliedhealthprofname != null)
			if(this.alliedhealthprofname.length() > 150)
				listOfErrors.add("The length of the field [alliedhealthprofname] in the value object [ims.RefMan.vo.ReferralLetterDetailsLiteVo] is too big. It should be less or equal to 150");
		if(this.dateofreferral == null)
			listOfErrors.add("DateOfReferral is mandatory");
		if(this.specialrequirements != null)
			if(this.specialrequirements.length() > 255)
				listOfErrors.add("The length of the field [specialrequirements] in the value object [ims.RefMan.vo.ReferralLetterDetailsLiteVo] is too big. It should be less or equal to 255");
		if(this.consultant != null)
		{
			String[] listOfOtherErrors = this.consultant.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		this.isBusy = false;
		this.isValidated = false;
		return result;
	}
	public void clearIDAndVersion()
	{
		this.id = null;
		this.version = 0;
	}
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		ReferralLetterDetailsLiteVo clone = new ReferralLetterDetailsLiteVo(this.id, this.version);
		
		if(this.service == null)
			clone.service = null;
		else
			clone.service = (ims.core.vo.ServiceLiteVo)this.service.clone();
		if(this.referrertype == null)
			clone.referrertype = null;
		else
			clone.referrertype = (ims.core.vo.lookups.SourceOfReferral)this.referrertype.clone();
		clone.alliedhealthprofname = this.alliedhealthprofname;
		if(this.dateofreferral == null)
			clone.dateofreferral = null;
		else
			clone.dateofreferral = (ims.framework.utils.Date)this.dateofreferral.clone();
		if(this.datereferralreceived == null)
			clone.datereferralreceived = null;
		else
			clone.datereferralreceived = (ims.framework.utils.Date)this.datereferralreceived.clone();
		if(this.transport == null)
			clone.transport = null;
		else
			clone.transport = (ims.scheduling.vo.lookups.ApptTransportType)this.transport.clone();
		clone.specialrequirements = this.specialrequirements;
		clone.isdadreferral = this.isdadreferral;
		if(this.location == null)
			clone.location = null;
		else
			clone.location = (ims.core.vo.LocationLiteVo)this.location.clone();
		if(this.enddatekpi == null)
			clone.enddatekpi = null;
		else
			clone.enddatekpi = (ims.framework.utils.Date)this.enddatekpi.clone();
		if(this.enddateemailkpi == null)
			clone.enddateemailkpi = null;
		else
			clone.enddateemailkpi = (ims.framework.utils.Date)this.enddateemailkpi.clone();
		if(this.enddatepaperkpi == null)
			clone.enddatepaperkpi = null;
		else
			clone.enddatepaperkpi = (ims.framework.utils.Date)this.enddatepaperkpi.clone();
		if(this.end18ww == null)
			clone.end18ww = null;
		else
			clone.end18ww = (ims.framework.utils.Date)this.end18ww.clone();
		if(this.consultant == null)
			clone.consultant = null;
		else
			clone.consultant = (ims.core.vo.HcpMinVo)this.consultant.clone();
		if(this.function == null)
			clone.function = null;
		else
			clone.function = (ims.core.vo.ServiceFunctionLiteVo)this.function.clone();
		if(this.originaldateofreferral == null)
			clone.originaldateofreferral = null;
		else
			clone.originaldateofreferral = (ims.framework.utils.Date)this.originaldateofreferral.clone();
		clone.isValidated = this.isValidated;
		
		this.isBusy = false;
		return clone;
	}
	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if(caseInsensitive); // this is to avoid eclipse warning only.
		if (!(ReferralLetterDetailsLiteVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A ReferralLetterDetailsLiteVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		ReferralLetterDetailsLiteVo compareObj = (ReferralLetterDetailsLiteVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getDateOfReferral() == null && compareObj.getDateOfReferral() != null)
				return -1;
			if(this.getDateOfReferral() != null && compareObj.getDateOfReferral() == null)
				return 1;
			if(this.getDateOfReferral() != null && compareObj.getDateOfReferral() != null)
				retVal = this.getDateOfReferral().compareTo(compareObj.getDateOfReferral());
		}
		return retVal;
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.service != null)
			count++;
		if(this.referrertype != null)
			count++;
		if(this.alliedhealthprofname != null)
			count++;
		if(this.dateofreferral != null)
			count++;
		if(this.datereferralreceived != null)
			count++;
		if(this.transport != null)
			count++;
		if(this.specialrequirements != null)
			count++;
		if(this.isdadreferral != null)
			count++;
		if(this.location != null)
			count++;
		if(this.enddatekpi != null)
			count++;
		if(this.enddateemailkpi != null)
			count++;
		if(this.enddatepaperkpi != null)
			count++;
		if(this.end18ww != null)
			count++;
		if(this.consultant != null)
			count++;
		if(this.function != null)
			count++;
		if(this.originaldateofreferral != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 16;
	}
	protected ims.core.vo.ServiceLiteVo service;
	protected ims.core.vo.lookups.SourceOfReferral referrertype;
	protected String alliedhealthprofname;
	protected ims.framework.utils.Date dateofreferral;
	protected ims.framework.utils.Date datereferralreceived;
	protected ims.scheduling.vo.lookups.ApptTransportType transport;
	protected String specialrequirements;
	protected Boolean isdadreferral;
	protected ims.core.vo.LocationLiteVo location;
	protected ims.framework.utils.Date enddatekpi;
	protected ims.framework.utils.Date enddateemailkpi;
	protected ims.framework.utils.Date enddatepaperkpi;
	protected ims.framework.utils.Date end18ww;
	protected ims.core.vo.HcpMinVo consultant;
	protected ims.core.vo.ServiceFunctionLiteVo function;
	protected ims.framework.utils.Date originaldateofreferral;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
