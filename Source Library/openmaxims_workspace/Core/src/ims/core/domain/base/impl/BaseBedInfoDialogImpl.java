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

package ims.core.domain.base.impl;

import ims.domain.impl.DomainImpl;

public abstract class BaseBedInfoDialogImpl extends DomainImpl implements ims.core.domain.BedInfoDialog, ims.domain.impl.Transactional
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void validategetPatient(ims.core.patient.vo.PatientRefVo patient)
	{
	}

	@SuppressWarnings("unused")
	public void validategetInpatientEpisode(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpatientEpisode)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistActiveWardsForHospitalLite(ims.core.resource.place.vo.LocationRefVo hospital)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveTransferOut(ims.core.vo.PendingTransfersLiteVo pendingTransfer)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveDischarge(ims.core.vo.DischargedEpisodeADTVo dischargedEpisode, ims.core.vo.BedSpaceStateLiteVo voBedSpacState)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveDischargeElectiveList(ims.core.vo.DischargedEpisodeADTVo dischargedEpisode, ims.core.vo.BedSpaceStateLiteVo voBedSpacState, ims.careuk.vo.PatientElectiveListBedAdmissionVo electiveList, ims.careuk.vo.PatientElectiveListBedAdmissionVoCollection cancelledElectiveListToBeRemoved)
	{
	}

	@SuppressWarnings("unused")
	public void validategetInpatConsultantTransfer(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpat)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveInpatConsultantTransfer(ims.core.vo.InpatConsultantTransferVo inpatConsTramsfer)
	{
	}

	@SuppressWarnings("unused")
	public void validategetParentLocation(ims.core.resource.place.vo.LocationRefVo childLoc)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveCloseBed(ims.core.vo.BedSpaceStateLiteVo bedSpaceState, String user, ims.core.vo.lookups.ReasonForBedClosure reason, ims.framework.utils.DateTime estReOpen)
	{
	}

	@SuppressWarnings("unused")
	public void validategetBedSpaceStateStatus(ims.core.admin.pas.vo.BedSpaceStateStatusRefVo bedSpaceState)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveReOpenOrAssignBed(ims.core.vo.BedSpaceStateLiteVo bedSpaceState)
	{
	}

	@SuppressWarnings("unused")
	public void validatecancelTransfer(ims.core.admin.pas.vo.PendingTransfersRefVo voTransfer, ims.core.resource.place.vo.LocationRefVo voCancellingfromWard)
	{
		if(voCancellingfromWard == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'voCancellingfromWard' of type 'ims.core.resource.place.vo.LocationRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validatesaveEstimatedDischarge(ims.core.vo.InpatientEpisodeLiteVo inpatEpisode)
	{
	}

	@SuppressWarnings("unused")
	public void validategetRtpStatAndPlBlk(ims.core.vo.PatientId patId)
	{
		if(patId == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'patId' of type 'ims.core.vo.PatientId' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validatelistInpatientEpisodeByWard(ims.core.resource.place.vo.LocationRefVo ward)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveInternalTransfer(ims.core.vo.InpatientEpisodeLiteVo inpatEpis, ims.core.vo.BedSpaceStateLiteVo bedSpaceState)
	{
		if(inpatEpis == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'inpatEpis' of type 'ims.core.vo.InpatientEpisodeLiteVo' cannot be null.");
		if(bedSpaceState == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'bedSpaceState' of type 'ims.core.vo.BedSpaceStateLiteVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validatelistInfantsForSelectedPatient(ims.core.patient.vo.PatientRefVo patient)
	{
		if(patient == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'patient' of type 'ims.core.patient.vo.PatientRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetBedSpaceStateByInpatientEpisode(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpat)
	{
		if(inpat == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'inpat' of type 'ims.core.admin.pas.vo.InpatientEpisodeRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validatelistSpecialtiesFromConSpc(String consultantMapping)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPasMappingFromConsultant(ims.vo.interfaces.IMos mos)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPendingTransferForInpatient(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpatEpis)
	{
		if(inpatEpis == null)
			throw new ims.domain.exceptions.DomainRuntimeException("The parameter 'inpatEpis' of type 'ims.core.admin.pas.vo.InpatientEpisodeRefVo' cannot be null.");
	}

	@SuppressWarnings("unused")
	public void validategetAdmissionDetailByPasEvent(ims.core.admin.pas.vo.PASEventRefVo pasEvent)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveTransferIn(ims.core.vo.BedSpaceStateLiteVo voOldBedSpaceStateLite, ims.core.vo.InPatientEpisodeADTVo inpatientEpisode, ims.core.vo.PendingTransfersLiteVo voTransfer, ims.core.vo.HomeLeaveVo voHL)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistCCOSpecialtiesFromConSpc(String consultantMapping)
	{
	}

	@SuppressWarnings("unused")
	public void validategetSpecialtyMappingFromPASSpecialty(String szMapping)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPASSpecialtyMappingFromSpecialty(String szMapping)
	{
	}

	@SuppressWarnings("unused")
	public void validategetCCOSpecialtyMappingFromPASSpecialty(ims.core.vo.lookups.PASSpecialty pasSpecialty)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPASSpecialtyMappingFromSpecialty(ims.core.vo.lookups.Specialty specialty)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveHomeLeave(ims.core.vo.BedSpaceStateLiteVo voOldBedSpaceStateLite, ims.core.vo.InPatientEpisodeADTVo inpatientEpisode, ims.core.vo.HomeLeaveVo homeLeaveVo)
	{
	}

	@SuppressWarnings("unused")
	public void validategetVTERiskAssessmentWorklistById(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpatEpRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetInpatEpisodeForVTERiskAssessment(ims.core.admin.pas.vo.InpatientEpisodeRefVo inpatEpRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetVTERiskAssessmentShortVoBYId(ims.core.clinical.vo.VTERiskAssessmentRefVo vTERef)
	{
	}

	@SuppressWarnings("unused")
	public void validategeInpatientEpisodeLiteVoById(ims.core.admin.pas.vo.InpatientEpisodeRefVo inparEpRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPIDDiagnosisInfo(ims.core.admin.vo.CareContextRefVo careContextRefV, ims.core.admin.vo.EpisodeOfCareRefVo episodeRefVo)
	{
	}

	@SuppressWarnings("unused")
	public void validategetVTEByCareContext(ims.core.admin.vo.CareContextRefVo careContextRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetDischargedEpisodeADT(ims.core.admin.pas.vo.PASEventRefVo pasEventRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetBedSpaceStateStatusByBedId(ims.core.admin.pas.vo.BedSpaceStateRefVo bedSpaceRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetDischargedEpisodeForVTERiskAssessmentWorklistVo(ims.core.admin.pas.vo.DischargedEpisodeRefVo dischargeepisodeRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetCurrentHospital(ims.framework.interfaces.ILocation currentLocation)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPatientElectiveListForDischarge(ims.core.admin.pas.vo.PASEventRefVo pasEvent)
	{
	}

	@SuppressWarnings("unused")
	public void validatehasCancelledElectiveListsToRemove(ims.core.patient.vo.PatientRefVo patient, ims.careuk.vo.PatientElectiveListRefVo electiveList, ims.core.clinical.vo.ServiceRefVo service)
	{
	}

	@SuppressWarnings("unused")
	public void validategetCancelledElectiveListsToRemove(ims.core.patient.vo.PatientRefVo patient, ims.careuk.vo.PatientElectiveListRefVo electiveList, ims.core.clinical.vo.ServiceRefVo service)
	{
	}

	@SuppressWarnings("unused")
	public void validatehasElectiveListsToRemove(ims.core.patient.vo.PatientRefVo patientRef, ims.careuk.vo.PatientElectiveListRefVo electiveListRef, ims.core.vo.lookups.Specialty specialty)
	{
	}

	@SuppressWarnings("unused")
	public void validategetElectiveListsToRemove(ims.core.patient.vo.PatientRefVo patientRef, ims.careuk.vo.PatientElectiveListRefVo electiveListRef, ims.core.vo.lookups.Specialty specialty)
	{
	}
}