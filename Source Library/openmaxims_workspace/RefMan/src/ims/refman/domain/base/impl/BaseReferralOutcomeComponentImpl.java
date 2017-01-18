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

package ims.RefMan.domain.base.impl;

import ims.domain.impl.DomainImpl;

public abstract class BaseReferralOutcomeComponentImpl extends DomainImpl implements ims.RefMan.domain.ReferralOutcomeComponent, ims.domain.impl.Transactional
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void validatelistHotlistProcedureShort(String procedureName, ims.core.vo.lookups.Specialty specialty)
	{
	}

	@SuppressWarnings("unused")
	public void validategetCatsReferralService(ims.RefMan.vo.CatsReferralRefVo voCatsReferralRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveReferralOutcome(ims.RefMan.vo.ReferralOutcomeVo voReferralOutcome)
	{
	}

	@SuppressWarnings("unused")
	public void validategetReferralOutcome(ims.RefMan.vo.CatsReferralRefVo voCATSRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetCatsReferral(ims.RefMan.vo.CatsReferralRefVo voCatsRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveCatsReferral(ims.RefMan.vo.CatsReferralWizardVo record)
	{
	}

	@SuppressWarnings("unused")
	public void validatecreateOrUpdateMedication(ims.core.vo.MedicationVo value)
	{
	}

	@SuppressWarnings("unused")
	public void validateupdateReferralOutcome(ims.RefMan.vo.CatsReferralWizardVo record)
	{
		if(record == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'record' of type 'ims.RefMan.vo.CatsReferralWizardVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetCatsReferralStatus(ims.RefMan.vo.CatsReferralRefVo catsRefVo)
	{
		if(catsRefVo == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'catsRefVo' of type 'ims.RefMan.vo.CatsReferralRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetOutcome(ims.RefMan.vo.ReferralOutcomeRefVo outcome)
	{
		if(outcome == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'outcome' of type 'ims.RefMan.vo.ReferralOutcomeRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetCatsReferralReports(ims.RefMan.vo.CatsReferralRefVo voCatsRef)
	{
		if(voCatsRef == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'voCatsRef' of type 'ims.RefMan.vo.CatsReferralRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validatesaveCatsReferralReport(ims.RefMan.vo.CatsReferralReportsVo recvord)
	{
		if(recvord == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'recvord' of type 'ims.RefMan.vo.CatsReferralReportsVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetInvestigation(ims.RefMan.vo.CatsReferralRefVo referral)
	{
	}

	@SuppressWarnings("unused")
	public void validategetDiagnosis(ims.RefMan.vo.CatsReferralRefVo referral)
	{
	}
}
