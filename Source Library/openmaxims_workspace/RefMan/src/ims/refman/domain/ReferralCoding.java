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

package ims.RefMan.domain;

// Generated from form domain impl
public interface ReferralCoding extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	/**
	* listProcedureByClinicalContact
	*/
	public ims.core.vo.PatientProcedureShortWithCareIntraOperativeVoCollection listProcedureForReferral(ims.core.admin.vo.CareContextRefVo careContextlRefVo);

	// Generated from form domain interface definition
	/**
	* Diagnosis / Complications for a Care Context
	*/
	public ims.core.vo.PatientDiagnosisListVoCollection listDiagnosisComplicationsForReferral(ims.core.admin.vo.CareContextRefVo careContextRefVo);

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralCodingVo getReferralCoding(ims.RefMan.vo.CatsReferralRefVo catsRefVo, ims.core.admin.pas.vo.PASEventRefVo pasEventRefVo);

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralCodingVo saveReferralCoding(ims.RefMan.vo.ReferralCodingVo referralCodingVo, ims.core.admin.pas.vo.DischargedEpisodeRefVo dischargedEpisodeRef) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException, ims.domain.exceptions.UniqueKeyViolationException;

	// Generated from form domain interface definition
	public ims.core.vo.CareContextLiteVo getCareContext(ims.RefMan.vo.CatsReferralRefVo catsRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.DiagnosisVo getDiagnosisWithTaxonomyMappings(ims.core.clinical.vo.DiagnosisRefVo diagRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.ProcedureVo getProcedureWithTaxonomyMappings(ims.core.clinical.vo.ProcedureRefVo procRefVo);

	// Generated from form domain interface definition
	public ims.RefMan.vo.DischargeAndPASEventForReferralCodingVoCollection listPASEvents(ims.RefMan.vo.CatsReferralRefVo voCatsReferralRef);

	// Generated from form domain interface definition
	/**
	* listProcedureByClinicalContact
	*/
	public ims.core.vo.PatientProcedureShortWithCareIntraOperativeVoCollection listNonSFSProcedures(ims.core.admin.vo.CareContextRefVo careContextlRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.PatientListVo getPatientRef(ims.RefMan.vo.CatsReferralRefVo catsReferralRef);

	// Generated from form domain interface definition
	public ims.scheduling.vo.BookingAppointmentOutpatientCodingVoCollection getSeenAppointmentsForReferral(ims.RefMan.vo.CatsReferralRefVo referralRef);

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralCodingVo getReferralCodingForAppointment(ims.scheduling.vo.Booking_AppointmentRefVo appointmentRef);

	// Generated from form domain interface definition
	public ims.ocrr.vo.InvestigationIndexVo getInvestigationWithTaxonomyMappings(ims.ocrr.configuration.vo.InvestigationIndexRefVo investigationRef);

	// Generated from form domain interface definition
	public ims.ocrr.vo.OrderInvestigationLiteVoCollection listResultedRadiologyInvForReferral(ims.RefMan.vo.CatsReferralRefVo referralRef, ims.RefMan.vo.ReferralCodingRefVo referralCodingRef);

	// Generated from form domain interface definition
	public Boolean procedureAlreadyAdded(ims.RefMan.vo.CatsReferralRefVo referralRef, ims.core.clinical.vo.PatientProcedureRefVo procedureRef);

	// Generated from form domain interface definition
	public ims.core.vo.PatientProcedureShortWithCareIntraOperativeVoCollection listOutpatientCodingProceduresForReferral(ims.RefMan.vo.CatsReferralRefVo catsReferralRef, ims.core.admin.vo.CareContextRefVo careContextRef);

	// Generated from form domain interface definition
	public java.util.List getAlreadyAddedProceduresIDs(ims.RefMan.vo.CatsReferralRefVo catsReferralRef);

	// Generated from form domain interface definition
	public java.util.List getAlreadyAddedInvestigationsIDs(ims.RefMan.vo.CatsReferralRefVo referralRef);

	// Generated from form domain interface definition
	public Boolean isInpatientNonSuitableForSurgery(ims.RefMan.vo.CatsReferralRefVo referralRef);

	// Generated from form domain interface definition
	public Boolean hasPatientMoreReferralCodings(ims.core.patient.vo.PatientRefVo patientRef, ims.RefMan.vo.ReferralCodingVo refCodingToBeExcluded);
}
