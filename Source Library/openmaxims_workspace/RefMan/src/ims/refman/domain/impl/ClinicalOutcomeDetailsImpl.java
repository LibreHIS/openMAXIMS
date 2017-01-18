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
// This code was generated by George Cristian Josan using IMS Development Environment (version 1.80 build 4091.21781)
// Copyright (C) 1995-2011 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;

import ims.RefMan.domain.base.impl.BaseClinicalOutcomeDetailsImpl;
import ims.RefMan.domain.objects.ClinicalOutcome;
import ims.RefMan.domain.objects.ClinicalOutcomeCategoryProcedureRequiredConfig;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.ClinicalOutcomeCategoryProcedureRequiredConfigVo;
import ims.RefMan.vo.ClinicalOutcomeProcedureVoCollection;
import ims.RefMan.vo.ClinicalOutcomeVo;
import ims.RefMan.vo.domain.ClinicalOutcomeCategoryProcedureRequiredConfigVoAssembler;
import ims.RefMan.vo.domain.ClinicalOutcomeProcedureVoAssembler;
import ims.RefMan.vo.domain.ClinicalOutcomeVoAssembler;
import ims.RefMan.vo.lookups.ClinicalOutcomeContext;
import ims.RefMan.vo.lookups.ClinicalOutcomeContextCollection;
import ims.clinical.vo.lookups.ClinicalOutcomeCategory;
import ims.clinicaladmin.vo.ClinicalOutcomeConfigLiteVoCollection;
import ims.clinicaladmin.vo.domain.ClinicalOutcomeConfigLiteVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.domain.lookups.LookupInstance;

import java.util.ArrayList;
import java.util.List;

public class ClinicalOutcomeDetailsImpl extends BaseClinicalOutcomeDetailsImpl
{
	private static final long serialVersionUID = 1L;

	/**
	 * Function used to list procedures for a given referral
	 */
	public ClinicalOutcomeProcedureVoCollection listProcedures(CatsReferralRefVo referral)
	{
		if (referral == null || !referral.getID_CatsReferralIsNotNull())
			throw new DomainRuntimeException("Expected referral is null in domain.");
		
		String query = "select operative from IntraOperativeCareRecord as operative left join operative.careContext as context1, CatsReferral as cats left join cats.careContext as context2 where context1.id = context2.id and cats.id = :ID";
		
		ArrayList<String> paramNames = new ArrayList<String>();
		ArrayList<Object> paramValues = new ArrayList<Object>();
		
		paramNames.add("ID");
		paramValues.add(referral.getID_CatsReferral());
		
		return ClinicalOutcomeProcedureVoAssembler.createClinicalOutcomeProcedureVoCollectionFromIntraOperativeCareRecord(getDomainFactory().find(query, paramNames, paramValues));
	}

	/**
	* Function used to list ClinicalOutcomeConfig based on category or on speciality (if category is null)
	*/
	public ClinicalOutcomeConfigLiteVoCollection listClinicalOutcomeConfig(ClinicalOutcomeCategory category, CatsReferralRefVo referral)
	{
		if (referral == null || !referral.getID_CatsReferralIsNotNull())
			throw new DomainRuntimeException("Expected referral is null in domain.");
		
		StringBuilder query = new StringBuilder();
		query.append("select distinct outcome from ClinicalOutcomeConfig as outcome left join outcome.category as category left join outcome.specialty as outcomespec");
		
		ArrayList<String> paramNames = new ArrayList<String>();
		ArrayList<Object> paramValues = new ArrayList<Object>();
		
		if (category != null)
		{
			query.append(" where category.id = :CAT_ID");
			
			paramNames.add("CAT_ID");
			paramValues.add(category.getID());
		}
		else
		{
			query.append(", CatsReferral as referral left join referral.referralDetails as details left join details.service as service left join service.specialty as refspecialty");
			query.append(" where referral.id = :REF_ID and outcomespec.id = refspecialty.id");
			
			paramNames.add("REF_ID");
			paramValues.add(referral.getID_CatsReferral());
		}

		/* TODO MSSQL case - query.append(" and outcome.isActive = 1"); */
		query.append(" and outcome.isActive = true");

		query.append(" order by outcome.clinicalOutcome asc");

		return ClinicalOutcomeConfigLiteVoAssembler.createClinicalOutcomeConfigLiteVoCollectionFromClinicalOutcomeConfig(getDomainFactory().find(query.toString(), paramNames, paramValues));
	}

	/**
	* Function used to save in data base a ClinicalOutcome record
	*/
	public ClinicalOutcomeVo saveClinicalOutcome(ClinicalOutcomeVo clinicalOutcome) throws DomainInterfaceException, StaleObjectException, ForeignKeyViolationException, UniqueKeyViolationException
	{
		if (clinicalOutcome == null)
			throw new DomainRuntimeException("Can not save null ClinicalOutcome record");
		
		if (!clinicalOutcome.isValidated())
			throw new DomainRuntimeException("ClinicalOutcome record to save is not validated");

		// Get domain factory
		DomainFactory factory = getDomainFactory();
		
		// Create domain object
		ClinicalOutcome domClinicalOutcome = ClinicalOutcomeVoAssembler.extractClinicalOutcome(factory, clinicalOutcome);
		// Commit domain object to data base
		factory.save(domClinicalOutcome);
		
		return ClinicalOutcomeVoAssembler.create(domClinicalOutcome);
	}

	/**
	 * Function used to retrieve the ClinicalOutcomeCategory configuration
	 */
	public ClinicalOutcomeCategoryProcedureRequiredConfigVo getClinicalOutcomeCategoryConfig(ClinicalOutcomeCategory outcomeCategory)
	{
		if (outcomeCategory == null)
			return null;
		
		String query = "select outcome from ClinicalOutcomeCategoryProcedureRequiredConfig as outcome where outcome.category.id = " + outcomeCategory.getID() + " and outcome.isRIE is null";
		
		return ClinicalOutcomeCategoryProcedureRequiredConfigVoAssembler.create((ClinicalOutcomeCategoryProcedureRequiredConfig) getDomainFactory().findFirst(query));
	}

	public Boolean isProcedureNotMandatoryForClinicalOutcomeContext(ClinicalOutcomeContext context)
	{
		/* TODO MSSQL case - String query ="select co from ClinicalOutcomeContextProcedureRequiredConfig as context left join context.context as lkpcontext where lkpcontext.id = :IdContext and context.procedureNotMandatory=1"; */
		String query ="select co from ClinicalOutcomeContextProcedureRequiredConfig as context left join context.context as lkpcontext where lkpcontext.id = :IdContext and context.procedureNotMandatory = TRUE";

		long countWithHQL = getDomainFactory().countWithHQL(query, new String[]{"IdContext"}, new Object[]{context.getId()});
		return countWithHQL>0;
	}

	public ClinicalOutcomeContextCollection listClinicalOutcomeContextWithNotMandatoryProcedure()
	{
		/* TODO MSSQL case - String query ="select lkpcontext from ClinicalOutcomeContextProcedureRequiredConfig as context left join context.context as lkpcontext where context.procedureNotMandatory=1"; */
		String query ="select lkpcontext from ClinicalOutcomeContextProcedureRequiredConfig as context left join context.context as lkpcontext where context.procedureNotMandatory = TRUE";

		List find = getDomainFactory().find(query, new String[]{}, new Object[]{});
		if (find == null || find.size() == 0)
			 return null;
		ClinicalOutcomeContextCollection result = new ClinicalOutcomeContextCollection();
		for (Object inst:find)
		{
			if (inst instanceof LookupInstance)
			{
				LookupInstance lkp = (LookupInstance) inst;
				result.add(new ClinicalOutcomeContext(lkp.getId(),lkp.getText(),lkp.isActive(),null,lkp.getImage(),lkp.getColor(),lkp.getOrder()));
			}
		}
		return result;
	}
}
