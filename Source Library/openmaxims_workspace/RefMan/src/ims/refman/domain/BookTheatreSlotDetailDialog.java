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
public interface BookTheatreSlotDetailDialog extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	/**
	* saveBooking
	*/
	public ims.scheduling.vo.Sch_BookingTheatreVo saveTheatreBooking(ims.scheduling.vo.Sch_BookingTheatreVo booking, ims.RefMan.vo.CatsReferralRefVo catsReferral, ims.scheduling.vo.SessionParentChildSlotRefVoCollection voCollChildSlots, ims.scheduling.vo.SessionParentChildSlotVo voSessParentChildSlot, ims.scheduling.vo.lookups.TheatreType theatreType, ims.RefMan.vo.ReferralERODForBookTheatreVo regerralEROD, ims.RefMan.vo.PatientElectiveListVo patientElectiveListToSave, ims.scheduling.vo.PendingEmergencyTheatreVo pendingEmergencyTheatre, ims.framework.utils.Date previousEarliestDateOffered) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	/**
	* getBookingTheatreAppointment
	*/
	public ims.scheduling.vo.BookingAppointmentTheatreVo getBookingTheatreAppointment(ims.scheduling.vo.Booking_AppointmentRefVo appt);

	// Generated from form domain interface definition
	/**
	* getPatientICP
	*/
	public ims.icp.vo.PatientICPFullVo getPatientICP(ims.core.admin.vo.CareContextRefVo careContext);

	// Generated from form domain interface definition
	/**
	* savePatientICP
	*/
	public ims.icp.vo.PatientICPFullVo savePatientICP(ims.icp.vo.PatientICPFullVo icp) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	public Boolean hasTheatreEROD(ims.RefMan.vo.CatsReferralRefVo catsReferral);

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralERODVo getReferralERODByAppointment(ims.scheduling.vo.Booking_AppointmentRefVo apptRef);

	// Generated from form domain interface definition
	public ims.RefMan.vo.CatsReferralForNewElectivListEntryVo getCatsReferral(ims.RefMan.vo.CatsReferralRefVo catsReferralRef);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVoCollection listWardsForCurrentLocation(ims.framework.interfaces.ILocation location);

	// Generated from form domain interface definition
	public ims.RefMan.vo.PatientElectiveListVoCollection getPatientElectiveList(ims.core.patient.vo.PatientRefVo patientRef, ims.RefMan.vo.CatsReferralRefVo catsRef, ims.core.clinical.vo.ServiceRefVo serviceRef, ims.core.clinical.vo.ProcedureRefVo procedureRef, String listIdHcp, ims.RefMan.vo.CatsReferralForNewElectivListEntryVo catsReferralWithPathwayClock, Boolean nonDiagnosticELE);

	// Generated from form domain interface definition
	public ims.core.vo.HcpMinVo getHCPMin(ims.core.resource.people.vo.HcpRefVo hcpRef);

	// Generated from form domain interface definition
	public ims.core.vo.HcpLiteVo getHCPLiteVo(ims.core.resource.people.vo.HcpRefVo hcpRef);

	// Generated from form domain interface definition
	public ims.RefMan.vo.PatientElectiveListVo getPatientElectiveListsOnFormOpen(ims.RefMan.vo.CatsReferralRefVo catsRef, ims.core.clinical.vo.ServiceRefVo serviceRef, String listIdHcp, Boolean nonDiagnosticELE, ims.RefMan.vo.CatsReferralForNewElectivListEntryVo getPatientElectiveList);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection getElectiveListConfigurationOnFormOpen(ims.core.clinical.vo.ServiceRefVo serviceRef, ims.core.resource.place.vo.LocationRefVo locationRef, String listIdHcp);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVo getParentHospitalLocation(ims.core.resource.place.vo.LocationRefVo wardLoc);

	// Generated from form domain interface definition
	public Boolean hasNonDiagnosticPatientElectiveList(ims.RefMan.vo.CatsReferralRefVo catsReferral);

	// Generated from form domain interface definition
	public ims.RefMan.vo.PatientElectiveListVo getPatientElectiveListByRefId(ims.RefMan.vo.PatientElectiveListRefVo patientElectiveListRefVo);

	// Generated from form domain interface definition
	public void createCaseNoteRequests(Integer tciId, ims.core.vo.LocationLiteVo caseNoteFolderLoc) throws ims.domain.exceptions.DomainInterfaceException;

	// Generated from form domain interface definition
	public ims.core.vo.ProcedureLiteVoCollection listProcedures(String filter);

	// Generated from form domain interface definition
	public ims.scheduling.vo.PendingEmergencyTheatreVo getPendingEmergencyTheatre(ims.scheduling.vo.PendingEmergencyTheatreRefVo pendingEmergencyTheatre);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVoCollection listHospitals();

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralERODForBookTheatreVo getEROD(ims.RefMan.vo.ReferralERODRefVo erod);

	// Generated from form domain interface definition
	public ims.scheduling.vo.SessionTheatreProceduresRemainingVo getSessionTheatreProcedureRemaining(ims.scheduling.vo.SessionTheatreProceduresRemaniningRefVo sessionTheatreProcedureRemaining);
}
