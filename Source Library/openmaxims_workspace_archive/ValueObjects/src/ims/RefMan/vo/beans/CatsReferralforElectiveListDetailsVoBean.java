// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
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

	private Integer id;
	private int version;
	private ims.RefMan.vo.beans.ReferralLetterDetailsForElectiveListDetailsVoBean referraldetails;
	private ims.core.vo.beans.CareContextMinVoBean carecontext;
	private ims.vo.RefVoBean patient;
	private Boolean isfitforsurgery;
	private ims.vo.LookupInstanceBean urgency;
	private ims.RefMan.vo.beans.PatientPathwayJourneyForERODVoBean journey;
}