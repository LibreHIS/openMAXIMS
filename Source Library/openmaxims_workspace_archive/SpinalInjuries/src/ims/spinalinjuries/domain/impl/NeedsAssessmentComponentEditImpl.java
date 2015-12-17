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
// This code was generated by Marius Mihalec using IMS Development Environment (version 1.42 build 2221.20939)
// Copyright (C) 1995-2006 IMS MAXIMS plc. All rights reserved.

package ims.spinalinjuries.domain.impl;

import ims.assessment.configuration.domain.objects.UserDefinedObjectComponent;
import ims.assessment.instantiation.domain.objects.PatientAssessment;
import ims.assessment.instantiation.vo.PatientAssessmentRefVo;
import ims.assessment.vo.PatientAssessmentVo;
import ims.assessment.vo.domain.PatientAssessmentVoAssembler;
import ims.assessment.vo.domain.UserDefinedObjectComponentVoAssembler;
import ims.clinical.domain.objects.NeedsAssessment;
import ims.core.vo.NeedsAssessmentComponentEditVo;
import ims.core.vo.domain.NeedsAssessmentComponentEditVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.spinalinjuries.domain.base.impl.BaseNeedsAssessmentComponentEditImpl;

public class NeedsAssessmentComponentEditImpl extends BaseNeedsAssessmentComponentEditImpl
{
	private static final long	serialVersionUID	= 1L;
	
		public ims.core.vo.NeedsAssessmentComponentEditVo get(ims.clinical.vo.NeedsAssessmentRefVo record)
	{
		if(record == null || record.getID_NeedsAssessment() == null)
			throw new DomainRuntimeException("Invalid need assessment record to get");
		
		return NeedsAssessmentComponentEditVoAssembler.create((NeedsAssessment)getDomainFactory().getDomainObject(NeedsAssessment.class, record.getID_NeedsAssessment()));
	}	
	public ims.assessment.vo.UserDefinedObjectComponentVo getComponent(ims.assessment.configuration.vo.UserDefinedObjectComponentRefVo ref)
	{
		if(ref == null || ref.getID_UserDefinedObjectComponent() == null)
			throw new DomainRuntimeException("Invalid component");
				
		return UserDefinedObjectComponentVoAssembler.create((UserDefinedObjectComponent)getDomainFactory().getDomainObject(UserDefinedObjectComponent.class, ref.getID_UserDefinedObjectComponent().intValue()));
	}
	public PatientAssessmentVo getPatientAssessment(PatientAssessmentRefVo ref)
	{
		if (ref == null || !ref.getID_PatientAssessmentIsNotNull())
			throw new DomainRuntimeException("Invalid Patient Assessment record to get");
		
		return PatientAssessmentVoAssembler.create((PatientAssessment) getDomainFactory().getDomainObject(PatientAssessment.class, ref.getID_PatientAssessment()));
	}
	public NeedsAssessmentComponentEditVo save(NeedsAssessmentComponentEditVo record, PatientAssessmentVo assessment) throws StaleObjectException
	{
		if(record == null)
			throw new DomainRuntimeException("Invalid need assessment record to save");		
		if(!record.isValidated())
			throw new DomainRuntimeException("Needs assessment record not validated");
		
		DomainFactory factory = getDomainFactory();
		
		NeedsAssessment domainRecord = NeedsAssessmentComponentEditVoAssembler.extractNeedsAssessment(factory, record);
		PatientAssessment patientAssessment = PatientAssessmentVoAssembler.extractPatientAssessment(factory,assessment);
		factory.save(domainRecord);	
		factory.save(patientAssessment);
		return NeedsAssessmentComponentEditVoAssembler.create(domainRecord);
		
	}
}