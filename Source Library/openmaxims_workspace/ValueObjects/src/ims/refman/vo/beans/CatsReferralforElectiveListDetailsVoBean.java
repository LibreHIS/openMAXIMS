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

package ims.RefMan.vo.beans;

public class CatsReferralforElectiveListDetailsVoBean extends ims.vo.ValueObjectBean
{
	public CatsReferralforElectiveListDetailsVoBean()
	{
	}
	public CatsReferralforElectiveListDetailsVoBean(ims.RefMan.vo.CatsReferralforElectiveListDetailsVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.referraldetails = vo.getReferralDetails() == null ? null : (ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean)vo.getReferralDetails().getBean();
		this.carecontext = vo.getCareContext() == null ? null : (ims.core.vo.beans.CareContextMinVoBean)vo.getCareContext().getBean();
		this.patient = vo.getPatient() == null ? null : new ims.vo.RefVoBean(vo.getPatient().getBoId(), vo.getPatient().getBoVersion());
		this.isfitforsurgery = vo.getIsFitForSurgery();
		this.urgency = vo.getUrgency() == null ? null : (ims.vo.LookupInstanceBean)vo.getUrgency().getBean();
		this.journey = vo.getJourney() == null ? null : (ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean)vo.getJourney().getBean();
		this.cancertype = vo.getCancerType() == null ? null : (ims.vo.LookupInstanceBean)vo.getCancerType().getBean();
		this.patientcategory = vo.getPatientCategory() == null ? null : (ims.vo.LookupInstanceBean)vo.getPatientCategory().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.CatsReferralforElectiveListDetailsVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.referraldetails = vo.getReferralDetails() == null ? null : (ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean)vo.getReferralDetails().getBean(map);
		this.carecontext = vo.getCareContext() == null ? null : (ims.core.vo.beans.CareContextMinVoBean)vo.getCareContext().getBean(map);
		this.patient = vo.getPatient() == null ? null : new ims.vo.RefVoBean(vo.getPatient().getBoId(), vo.getPatient().getBoVersion());
		this.isfitforsurgery = vo.getIsFitForSurgery();
		this.urgency = vo.getUrgency() == null ? null : (ims.vo.LookupInstanceBean)vo.getUrgency().getBean();
		this.journey = vo.getJourney() == null ? null : (ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean)vo.getJourney().getBean(map);
		this.cancertype = vo.getCancerType() == null ? null : (ims.vo.LookupInstanceBean)vo.getCancerType().getBean();
		this.patientcategory = vo.getPatientCategory() == null ? null : (ims.vo.LookupInstanceBean)vo.getPatientCategory().getBean();
	}

	public ims.RefMan.vo.CatsReferralforElectiveListDetailsVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.CatsReferralforElectiveListDetailsVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.CatsReferralforElectiveListDetailsVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.CatsReferralforElectiveListDetailsVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.CatsReferralforElectiveListDetailsVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public Integer getId()
	{
		return this.id;
	}
	public void setId(Integer value)
	{
		this.id = value;
	}
	public int getVersion()
	{
		return this.version;
	}
	public void setVersion(int value)
	{
		this.version = value;
	}
	public ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean getReferralDetails()
	{
		return this.referraldetails;
	}
	public void setReferralDetails(ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean value)
	{
		this.referraldetails = value;
	}
	public ims.core.vo.beans.CareContextMinVoBean getCareContext()
	{
		return this.carecontext;
	}
	public void setCareContext(ims.core.vo.beans.CareContextMinVoBean value)
	{
		this.carecontext = value;
	}
	public ims.vo.RefVoBean getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.vo.RefVoBean value)
	{
		this.patient = value;
	}
	public Boolean getIsFitForSurgery()
	{
		return this.isfitforsurgery;
	}
	public void setIsFitForSurgery(Boolean value)
	{
		this.isfitforsurgery = value;
	}
	public ims.vo.LookupInstanceBean getUrgency()
	{
		return this.urgency;
	}
	public void setUrgency(ims.vo.LookupInstanceBean value)
	{
		this.urgency = value;
	}
	public ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean getJourney()
	{
		return this.journey;
	}
	public void setJourney(ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean value)
	{
		this.journey = value;
	}
	public ims.vo.LookupInstanceBean getCancerType()
	{
		return this.cancertype;
	}
	public void setCancerType(ims.vo.LookupInstanceBean value)
	{
		this.cancertype = value;
	}
	public ims.vo.LookupInstanceBean getPatientCategory()
	{
		return this.patientcategory;
	}
	public void setPatientCategory(ims.vo.LookupInstanceBean value)
	{
		this.patientcategory = value;
	}

	private Integer id;
	private int version;
	private ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean referraldetails;
	private ims.core.vo.beans.CareContextMinVoBean carecontext;
	private ims.vo.RefVoBean patient;
	private Boolean isfitforsurgery;
	private ims.vo.LookupInstanceBean urgency;
	private ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean journey;
	private ims.vo.LookupInstanceBean cancertype;
	private ims.vo.LookupInstanceBean patientcategory;
}
