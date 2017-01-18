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
 * Linked to RefMan.CatsReferral business object (ID: 1004100035).
 */
public class CATSReferralForRTTMaintainanceVo extends ims.RefMan.vo.CatsReferralRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public CATSReferralForRTTMaintainanceVo()
	{
	}
	public CATSReferralForRTTMaintainanceVo(Integer id, int version)
	{
		super(id, version);
	}
	public CATSReferralForRTTMaintainanceVo(ims.RefMan.vo.beans.CATSReferralForRTTMaintainanceVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.referraldetails = bean.getReferralDetails() == null ? null : bean.getReferralDetails().buildVo();
		this.journey = bean.getJourney() == null ? null : bean.getJourney().buildVo();
		this.currentrttstatus = bean.getCurrentRTTStatus() == null ? null : bean.getCurrentRTTStatus().buildVo();
		this.urgency = bean.getUrgency() == null ? null : ims.RefMan.vo.lookups.ReferralUrgency.buildLookup(bean.getUrgency());
		this.rttclockimpact = bean.getRTTClockImpact();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.CATSReferralForRTTMaintainanceVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.referraldetails = bean.getReferralDetails() == null ? null : bean.getReferralDetails().buildVo(map);
		this.journey = bean.getJourney() == null ? null : bean.getJourney().buildVo(map);
		this.currentrttstatus = bean.getCurrentRTTStatus() == null ? null : bean.getCurrentRTTStatus().buildVo(map);
		this.urgency = bean.getUrgency() == null ? null : ims.RefMan.vo.lookups.ReferralUrgency.buildLookup(bean.getUrgency());
		this.rttclockimpact = bean.getRTTClockImpact();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.CATSReferralForRTTMaintainanceVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.CATSReferralForRTTMaintainanceVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.CATSReferralForRTTMaintainanceVoBean();
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
		if(fieldName.equals("PATIENT"))
			return getPatient();
		if(fieldName.equals("REFERRALDETAILS"))
			return getReferralDetails();
		if(fieldName.equals("JOURNEY"))
			return getJourney();
		if(fieldName.equals("CURRENTRTTSTATUS"))
			return getCurrentRTTStatus();
		if(fieldName.equals("URGENCY"))
			return getUrgency();
		if(fieldName.equals("RTTCLOCKIMPACT"))
			return getRTTClockImpact();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getPatientIsNotNull()
	{
		return this.patient != null;
	}
	public ims.core.patient.vo.PatientRefVo getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.patient.vo.PatientRefVo value)
	{
		this.isValidated = false;
		this.patient = value;
	}
	public boolean getReferralDetailsIsNotNull()
	{
		return this.referraldetails != null;
	}
	public ims.RefMan.vo.CATSReferralDetailsForRTTMaintainanceVo getReferralDetails()
	{
		return this.referraldetails;
	}
	public void setReferralDetails(ims.RefMan.vo.CATSReferralDetailsForRTTMaintainanceVo value)
	{
		this.isValidated = false;
		this.referraldetails = value;
	}
	public boolean getJourneyIsNotNull()
	{
		return this.journey != null;
	}
	public ims.pathways.vo.Journey_ClockImpactVo getJourney()
	{
		return this.journey;
	}
	public void setJourney(ims.pathways.vo.Journey_ClockImpactVo value)
	{
		this.isValidated = false;
		this.journey = value;
	}
	public boolean getCurrentRTTStatusIsNotNull()
	{
		return this.currentrttstatus != null;
	}
	public ims.pathways.vo.PathwayRTTStatusVo getCurrentRTTStatus()
	{
		return this.currentrttstatus;
	}
	public void setCurrentRTTStatus(ims.pathways.vo.PathwayRTTStatusVo value)
	{
		this.isValidated = false;
		this.currentrttstatus = value;
	}
	public boolean getUrgencyIsNotNull()
	{
		return this.urgency != null;
	}
	public ims.RefMan.vo.lookups.ReferralUrgency getUrgency()
	{
		return this.urgency;
	}
	public void setUrgency(ims.RefMan.vo.lookups.ReferralUrgency value)
	{
		this.isValidated = false;
		this.urgency = value;
	}
	public boolean getRTTClockImpactIsNotNull()
	{
		return this.rttclockimpact != null;
	}
	public Boolean getRTTClockImpact()
	{
		return this.rttclockimpact;
	}
	public void setRTTClockImpact(Boolean value)
	{
		this.isValidated = false;
		this.rttclockimpact = value;
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
		if(this.referraldetails != null)
		{
			if(!this.referraldetails.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.journey != null)
		{
			if(!this.journey.isValidated())
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
		if(this.patient == null)
			listOfErrors.add("Patient is mandatory");
		if(this.referraldetails != null)
		{
			String[] listOfOtherErrors = this.referraldetails.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.journey != null)
		{
			String[] listOfOtherErrors = this.journey.validate();
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
	
		CATSReferralForRTTMaintainanceVo clone = new CATSReferralForRTTMaintainanceVo(this.id, this.version);
		
		clone.patient = this.patient;
		if(this.referraldetails == null)
			clone.referraldetails = null;
		else
			clone.referraldetails = (ims.RefMan.vo.CATSReferralDetailsForRTTMaintainanceVo)this.referraldetails.clone();
		if(this.journey == null)
			clone.journey = null;
		else
			clone.journey = (ims.pathways.vo.Journey_ClockImpactVo)this.journey.clone();
		if(this.currentrttstatus == null)
			clone.currentrttstatus = null;
		else
			clone.currentrttstatus = (ims.pathways.vo.PathwayRTTStatusVo)this.currentrttstatus.clone();
		if(this.urgency == null)
			clone.urgency = null;
		else
			clone.urgency = (ims.RefMan.vo.lookups.ReferralUrgency)this.urgency.clone();
		clone.rttclockimpact = this.rttclockimpact;
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
		if (!(CATSReferralForRTTMaintainanceVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A CATSReferralForRTTMaintainanceVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((CATSReferralForRTTMaintainanceVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((CATSReferralForRTTMaintainanceVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.patient != null)
			count++;
		if(this.referraldetails != null)
			count++;
		if(this.journey != null)
			count++;
		if(this.currentrttstatus != null)
			count++;
		if(this.urgency != null)
			count++;
		if(this.rttclockimpact != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 6;
	}
	protected ims.core.patient.vo.PatientRefVo patient;
	protected ims.RefMan.vo.CATSReferralDetailsForRTTMaintainanceVo referraldetails;
	protected ims.pathways.vo.Journey_ClockImpactVo journey;
	protected ims.pathways.vo.PathwayRTTStatusVo currentrttstatus;
	protected ims.RefMan.vo.lookups.ReferralUrgency urgency;
	protected Boolean rttclockimpact;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
