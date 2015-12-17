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

package ims.emergency.vo.beans;

public class EmergencyAttendanceForDischargeLetterVoBean extends ims.vo.ValueObjectBean
{
	public EmergencyAttendanceForDischargeLetterVoBean()
	{
	}
	public EmergencyAttendanceForDischargeLetterVoBean(ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.attendancedischargecomment = vo.getAttendanceDischargeComment();
		this.attendancesupplementarycomment = vo.getAttendanceSupplementaryComment();
		this.isdischargeletterrequired = vo.getIsDischargeLetterRequired();
		this.dischargeletterstatus = vo.getDischargeLetterStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getDischargeLetterStatus().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.attendancedischargecomment = vo.getAttendanceDischargeComment();
		this.attendancesupplementarycomment = vo.getAttendanceSupplementaryComment();
		this.isdischargeletterrequired = vo.getIsDischargeLetterRequired();
		this.dischargeletterstatus = vo.getDischargeLetterStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getDischargeLetterStatus().getBean();
	}

	public ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo vo = null;
		if(map != null)
			vo = (ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.emergency.vo.EmergencyAttendanceForDischargeLetterVo();
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
	public String getAttendanceDischargeComment()
	{
		return this.attendancedischargecomment;
	}
	public void setAttendanceDischargeComment(String value)
	{
		this.attendancedischargecomment = value;
	}
	public String getAttendanceSupplementaryComment()
	{
		return this.attendancesupplementarycomment;
	}
	public void setAttendanceSupplementaryComment(String value)
	{
		this.attendancesupplementarycomment = value;
	}
	public Boolean getIsDischargeLetterRequired()
	{
		return this.isdischargeletterrequired;
	}
	public void setIsDischargeLetterRequired(Boolean value)
	{
		this.isdischargeletterrequired = value;
	}
	public ims.vo.LookupInstanceBean getDischargeLetterStatus()
	{
		return this.dischargeletterstatus;
	}
	public void setDischargeLetterStatus(ims.vo.LookupInstanceBean value)
	{
		this.dischargeletterstatus = value;
	}

	private Integer id;
	private int version;
	private String attendancedischargecomment;
	private String attendancesupplementarycomment;
	private Boolean isdischargeletterrequired;
	private ims.vo.LookupInstanceBean dischargeletterstatus;
}