//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
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
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.assessment.vo.beans;

public class PatientUserDefinedObjectVoBean extends ims.vo.ValueObjectBean
{
	public PatientUserDefinedObjectVoBean()
	{
	}
	public PatientUserDefinedObjectVoBean(ims.assessment.vo.PatientUserDefinedObjectVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.userdefinedobject = vo.getUserDefinedObject() == null ? null : (ims.assessment.vo.beans.UserDefinedObjectVoBean)vo.getUserDefinedObject().getBean();
		this.patientassessments = vo.getPatientAssessments() == null ? null : vo.getPatientAssessments().getBeanCollection();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.assessment.vo.PatientUserDefinedObjectVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.userdefinedobject = vo.getUserDefinedObject() == null ? null : (ims.assessment.vo.beans.UserDefinedObjectVoBean)vo.getUserDefinedObject().getBean(map);
		this.patientassessments = vo.getPatientAssessments() == null ? null : vo.getPatientAssessments().getBeanCollection();
	}

	public ims.assessment.vo.PatientUserDefinedObjectVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.assessment.vo.PatientUserDefinedObjectVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.assessment.vo.PatientUserDefinedObjectVo vo = null;
		if(map != null)
			vo = (ims.assessment.vo.PatientUserDefinedObjectVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.assessment.vo.PatientUserDefinedObjectVo();
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
	public ims.assessment.vo.beans.UserDefinedObjectVoBean getUserDefinedObject()
	{
		return this.userdefinedobject;
	}
	public void setUserDefinedObject(ims.assessment.vo.beans.UserDefinedObjectVoBean value)
	{
		this.userdefinedobject = value;
	}
	public ims.assessment.vo.beans.PatientAssessmentVoBean[] getPatientAssessments()
	{
		return this.patientassessments;
	}
	public void setPatientAssessments(ims.assessment.vo.beans.PatientAssessmentVoBean[] value)
	{
		this.patientassessments = value;
	}

	private Integer id;
	private int version;
	private ims.assessment.vo.beans.UserDefinedObjectVoBean userdefinedobject;
	private ims.assessment.vo.beans.PatientAssessmentVoBean[] patientassessments;
}