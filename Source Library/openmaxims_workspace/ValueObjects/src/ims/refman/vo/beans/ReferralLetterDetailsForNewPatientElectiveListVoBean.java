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

public class ReferralLetterDetailsForNewPatientElectiveListVoBean extends ims.vo.ValueObjectBean
{
	public ReferralLetterDetailsForNewPatientElectiveListVoBean()
	{
	}
	public ReferralLetterDetailsForNewPatientElectiveListVoBean(ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.service = vo.getService() == null ? null : (ims.core.vo.beans.ServiceLiteVoBean)vo.getService().getBean();
		this.consultant = vo.getConsultant() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConsultant().getBean();
		this.dateofreferral = vo.getDateOfReferral() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOfReferral().getBean();
		this.end18ww = vo.getEnd18WW() == null ? null : (ims.framework.utils.beans.DateBean)vo.getEnd18WW().getBean();
		this.interpreterrequired = vo.getInterpreterRequired() == null ? null : (ims.vo.LookupInstanceBean)vo.getInterpreterRequired().getBean();
		this.transportrequired = vo.getTransportRequired() == null ? null : (ims.vo.LookupInstanceBean)vo.getTransportRequired().getBean();
		this.location = vo.getLocation() == null ? null : (ims.core.vo.beans.LocationLiteVoBean)vo.getLocation().getBean();
		this.referrertype = vo.getReferrerType() == null ? null : (ims.vo.LookupInstanceBean)vo.getReferrerType().getBean();
		this.transport = vo.getTransport() == null ? null : (ims.vo.LookupInstanceBean)vo.getTransport().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.service = vo.getService() == null ? null : (ims.core.vo.beans.ServiceLiteVoBean)vo.getService().getBean(map);
		this.consultant = vo.getConsultant() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConsultant().getBean(map);
		this.dateofreferral = vo.getDateOfReferral() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOfReferral().getBean();
		this.end18ww = vo.getEnd18WW() == null ? null : (ims.framework.utils.beans.DateBean)vo.getEnd18WW().getBean();
		this.interpreterrequired = vo.getInterpreterRequired() == null ? null : (ims.vo.LookupInstanceBean)vo.getInterpreterRequired().getBean();
		this.transportrequired = vo.getTransportRequired() == null ? null : (ims.vo.LookupInstanceBean)vo.getTransportRequired().getBean();
		this.location = vo.getLocation() == null ? null : (ims.core.vo.beans.LocationLiteVoBean)vo.getLocation().getBean(map);
		this.referrertype = vo.getReferrerType() == null ? null : (ims.vo.LookupInstanceBean)vo.getReferrerType().getBean();
		this.transport = vo.getTransport() == null ? null : (ims.vo.LookupInstanceBean)vo.getTransport().getBean();
	}

	public ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.ReferralLetterDetailsForNewPatientElectiveListVo();
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
	public ims.core.vo.beans.ServiceLiteVoBean getService()
	{
		return this.service;
	}
	public void setService(ims.core.vo.beans.ServiceLiteVoBean value)
	{
		this.service = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean getConsultant()
	{
		return this.consultant;
	}
	public void setConsultant(ims.core.vo.beans.HcpLiteVoBean value)
	{
		this.consultant = value;
	}
	public ims.framework.utils.beans.DateBean getDateOfReferral()
	{
		return this.dateofreferral;
	}
	public void setDateOfReferral(ims.framework.utils.beans.DateBean value)
	{
		this.dateofreferral = value;
	}
	public ims.framework.utils.beans.DateBean getEnd18WW()
	{
		return this.end18ww;
	}
	public void setEnd18WW(ims.framework.utils.beans.DateBean value)
	{
		this.end18ww = value;
	}
	public ims.vo.LookupInstanceBean getInterpreterRequired()
	{
		return this.interpreterrequired;
	}
	public void setInterpreterRequired(ims.vo.LookupInstanceBean value)
	{
		this.interpreterrequired = value;
	}
	public ims.vo.LookupInstanceBean getTransportRequired()
	{
		return this.transportrequired;
	}
	public void setTransportRequired(ims.vo.LookupInstanceBean value)
	{
		this.transportrequired = value;
	}
	public ims.core.vo.beans.LocationLiteVoBean getLocation()
	{
		return this.location;
	}
	public void setLocation(ims.core.vo.beans.LocationLiteVoBean value)
	{
		this.location = value;
	}
	public ims.vo.LookupInstanceBean getReferrerType()
	{
		return this.referrertype;
	}
	public void setReferrerType(ims.vo.LookupInstanceBean value)
	{
		this.referrertype = value;
	}
	public ims.vo.LookupInstanceBean getTransport()
	{
		return this.transport;
	}
	public void setTransport(ims.vo.LookupInstanceBean value)
	{
		this.transport = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.ServiceLiteVoBean service;
	private ims.core.vo.beans.HcpLiteVoBean consultant;
	private ims.framework.utils.beans.DateBean dateofreferral;
	private ims.framework.utils.beans.DateBean end18ww;
	private ims.vo.LookupInstanceBean interpreterrequired;
	private ims.vo.LookupInstanceBean transportrequired;
	private ims.core.vo.beans.LocationLiteVoBean location;
	private ims.vo.LookupInstanceBean referrertype;
	private ims.vo.LookupInstanceBean transport;
}
