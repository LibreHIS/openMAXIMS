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
// This code was generated by Daniel Laffan using IMS Development Environment (version 1.80 build 4198.17562)
// Copyright (C) 1995-2011 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;

import ims.admin.domain.OrganisationAndLocation;
import ims.admin.domain.impl.OrganisationAndLocationImpl;
import ims.RefMan.domain.BookTheatre;
import ims.RefMan.domain.base.impl.BaseBookTheatreSlotImpl;
import ims.RefMan.domain.objects.PatientElectiveList;
import ims.RefMan.vo.BookTheatreSearchCriteriaVo;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.PatientElectiveListBookTheatreVo;
import ims.RefMan.vo.PatientElectiveListRefVo;
import ims.RefMan.vo.ReferralERODVo;
import ims.RefMan.vo.SuitableForSurgeryAssessmentLiteVo;
import ims.RefMan.vo.SuitableForSurgeryAssessmentLiteVoCollection;
import ims.RefMan.vo.SuitableForSurgeryAssessmentRefVo;
import ims.RefMan.vo.domain.PatientElectiveListBookTheatreVoAssembler;
import ims.RefMan.vo.domain.SuitableForSurgeryAssessmentLiteVoAssembler;
import ims.clinicaladmin.domain.CCIAdmin;
import ims.clinicaladmin.domain.impl.CCIAdminImpl;
import ims.clinicaladmin.vo.enums.CciType;
import ims.core.clinical.vo.ServiceRefVo;
import ims.core.resource.people.domain.objects.Medic;
import ims.core.resource.people.vo.HcpRefVo;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.MedicGradeVo;
import ims.core.vo.domain.MedicGradeVoAssembler;
import ims.core.vo.domain.MedicLiteVoAssembler;
import ims.core.vo.lookups.LateralityLRB;
import ims.core.vo.lookups.LocationType;
import ims.core.vo.lookups.MedicGrade;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.exceptions.CodingRuntimeException;
import ims.scheduling.domain.MoveAppointmentDialog;
import ims.scheduling.domain.impl.MoveAppointmentDialogImpl;
import ims.scheduling.domain.objects.Booking_Appointment;
import ims.scheduling.domain.objects.Sch_Session;
import ims.scheduling.domain.objects.SessionParentChildSlot;
import ims.scheduling.vo.BookingAppointmentTheatreLiteVo;
import ims.scheduling.vo.Booking_AppointmentRefVo;
import ims.scheduling.vo.Sch_SessionRefVo;
import ims.scheduling.vo.SessionParentChildSlotVo;
import ims.scheduling.vo.SessionParentChildSlotVoCollection;
import ims.scheduling.vo.SessionTheatreVo;
import ims.scheduling.vo.domain.BookingAppointmentTheatreLiteVoAssembler;
import ims.scheduling.vo.domain.SessionParentChildSlotVoAssembler;
import ims.scheduling.vo.lookups.Status_Reason;
import ims.vo.interfaces.IMos;

import java.util.ArrayList;
import java.util.List;

public class BookTheatreSlotImpl extends BaseBookTheatreSlotImpl
{

	private static final long serialVersionUID = 1L;

	/**
	* listActiveCanBeScheduledService
	*/
	public ims.core.vo.ServiceLiteVoCollection listActiveCanBeScheduledService()
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.listActiveCanBeScheduledService();
	}

	/**
	* list sessions for scheduling
	*/
	public ims.scheduling.vo.SessionTheatreVoCollection listSession(BookTheatreSearchCriteriaVo searchCriteria)
	{
		MoveAppointmentDialog impl = (MoveAppointmentDialog) getDomainImpl(MoveAppointmentDialogImpl.class);
		return impl.listSession(searchCriteria);
	}

	/**
	* list Booking appointments for a session
	*/
	public ims.scheduling.vo.BookingAppointmentTheatreVoCollection listBookingAppointment(ims.scheduling.vo.Sch_SessionRefVo session)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.listBookingAppointment(session);
	}

	/**
	* check Booking Rights for this Role and Session
	*/
	public Boolean hasBookingRights(ims.framework.interfaces.IAppRole role, ims.scheduling.vo.Sch_SessionRefVo session)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.hasBookingRights(role, session);
	}

	/**
	* listLocationLite
	*/
	public ims.core.vo.LocationLiteVoCollection listLocationLite()
	{
		OrganisationAndLocation impl = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return impl.listActiveHospitalsLite();
	}

	/**
	* getReferralDetail
	*/
	public ims.RefMan.vo.ReferralBookingVo getReferralDetail(ims.RefMan.vo.CatsReferralRefVo catsReferral)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.getReferralDetail(catsReferral);
	}

	/**
	* for rebooking used same Sch_booking
	*/
	public ims.scheduling.vo.Sch_BookingTheatreVo getSch_BookingByAppt(ims.scheduling.vo.Booking_AppointmentRefVo appt)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.getSch_BookingByAppt(appt);
	}

	/**
	* getBookingAppointment
	*/
	public ims.scheduling.vo.BookingAppointmentTheatreVo getBookingAppointment(ims.scheduling.vo.Booking_AppointmentRefVo appt)
	{
		// TODO: Add your code here and change the return value.
		return null;
	}

	/**
	* Runs a search based on a given filter string, CciType and Boolean. Returns the results as a collection of IGenericItem interfaces
	*/
	public ims.vo.interfaces.IGenericItem[] listProcedures(String filter)
	{
		CCIAdmin impl = (CCIAdmin) getDomainImpl(CCIAdminImpl.class);
		return impl.listIGenericItems(filter, CciType.PROCEDURE, true);
	}

	public ims.icp.vo.PatientICPFullVo getPatientICP(ims.core.admin.vo.CareContextRefVo careCOntextRefVo)
	{
		// TODO: Add your code here and change the return value.
		return null;
	}

	public ims.icp.vo.PatientICPFullVo savePatientICP(ims.icp.vo.PatientICPFullVo pateintICPVo) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException
	{
		// TODO: Add your code here and change the return value.
		return null;
	}

	/**
	* getSuitableForSurgeryAssessmentByCareContext
	*/
	public ims.RefMan.vo.SuitableForSurgeryAssessmentMinVo getSuitableForSurgeryAssessmentByCareContext(ims.core.admin.vo.CareContextRefVo careContext)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.getSuitableForSurgeryAssessmentByCareContext(careContext);
	}

	public ims.RefMan.vo.FitForSurgeryAssessmentLiteVo getLatestFitForSurgeryAssessment(ims.RefMan.vo.CatsReferralRefVo referral)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.getLatestFitForSurgeryAssessment(referral);
	}

	/**
	* countNonCancelledAppointmentsForSession
	*/
	public Integer countNonCancelledAppointmentsForSession(ims.scheduling.vo.Sch_SessionRefVo session)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.countNonCancelledAppointmentsForSession(session);
	}

	public void addSlotToSession(SessionTheatreVo voSession, SessionParentChildSlotVo voSlot) throws StaleObjectException
	{
		if (voSession == null || voSlot == null)
			throw new CodingRuntimeException("voSession or voSlot is null in method addSlotToSession");

		DomainFactory factory = getDomainFactory();
		
		Sch_Session doSession = (Sch_Session) factory.getDomainObject(voSession);
		
		SessionParentChildSlot doSlot = SessionParentChildSlotVoAssembler.extractSessionParentChildSlot(factory, voSlot);
		doSlot.setStatus(getDomLookup(Status_Reason.SLOTOPENED));
		doSlot.setSession(doSession);
		doSlot.setSessDateTime(doSession.getSessionDate());
		
		doSession.getParentChildSlots().add(doSlot);
		factory.save(doSession);
	}

	//wdev-13814
	public BookingAppointmentTheatreLiteVo getBookingAppointmentTheatre(Booking_AppointmentRefVo recordRef) 
	{
		if( recordRef == null || recordRef.getID_Booking_Appointment() == null)
			return null;
		
		return BookingAppointmentTheatreLiteVoAssembler.create( (Booking_Appointment) getDomainFactory().getDomainObject(Booking_Appointment.class,recordRef.getID_Booking_Appointment()));
	
	}

	public ReferralERODVo getLastTheatreEROD(CatsReferralRefVo catsReferral)
	{
		BookTheatre impl = (BookTheatre) getDomainImpl(BookTheatreImpl.class);
		return impl.getLastTheatreEROD(catsReferral);
	}

	//wdev-18673
	public LateralityLRB getLateralitySuitableForSurgegeryAssessmentProcedure(SuitableForSurgeryAssessmentRefVo suitableRef)
	{
		if( suitableRef == null)
			return null;
		
		DomainFactory factory = getDomainFactory();
			
		
		String hql = "select s1_1 from SuitableForSurgeryAssessment as s1_1 left join s1_1.procedure as p1_1	where (s1_1.id = :suitableRef )";
		List list =  factory.find(hql, new String[]{"suitableRef"}, new Object[]{suitableRef.getID_SuitableForSurgeryAssessment()});
		if(list != null && list.size() > 0)
		{
			SuitableForSurgeryAssessmentLiteVoCollection tempColl = SuitableForSurgeryAssessmentLiteVoAssembler.createSuitableForSurgeryAssessmentLiteVoCollectionFromSuitableForSurgeryAssessment(list);
			if( tempColl != null && tempColl.size() > 0)
			{
				SuitableForSurgeryAssessmentLiteVo tempVo = tempColl.get(0);
				if( tempVo != null && tempVo.getProcedureIsNotNull())
					return tempVo.getProcedure().getProcLaterality();
			}
		}
		
		
		
		return null;
	}

	public String getBookingCommentForAppt(Booking_AppointmentRefVo appointment)
	{
		if (appointment == null)
			return null;
		
		Object obj = getDomainFactory().find("select sch.bookingComments from Sch_Booking as sch left join sch.appointments as appts where appts.id = :APPTID", new String[]{"APPTID"},new Object[]{appointment.getID_Booking_Appointment()}).get(0);

		return 	obj != null && obj instanceof String ? (String)obj : null;
	}

	public PatientElectiveListBookTheatreVo getPatientElectiveList(PatientElectiveListRefVo electiveList)
	{
		if( electiveList == null || electiveList.getID_PatientElectiveList() == null)
			return null;
		
		return PatientElectiveListBookTheatreVoAssembler.create( (PatientElectiveList) getDomainFactory().getDomainObject(PatientElectiveList.class, electiveList.getID_PatientElectiveList()));
	}

	public IMos getMedic(HcpRefVo hcp, ServiceRefVo service)
	{
		if(service == null || service.getID_Service() == null || hcp == null || hcp.getID_Hcp() == null)
			return null;
		
		StringBuffer hqlConditions = new StringBuffer();

		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();

		/* TODO MSSQL case - String hql = "select medic from Medic as medic left join medic.serviceFunction as servFunc left join servFunc.service as serv where medic.isActive = 1 and servFunc.isActive = 1 and medic.isHCPaResponsibleHCP = 1 "; */
		String hql = "select medic from Medic as medic left join medic.serviceFunction as servFunc left join servFunc.service as serv where medic.isActive = true and servFunc.isActive = true and medic.isHCPaResponsibleHCP = true ";
		
		
		if (hcp != null)
		{
			hqlConditions.append(" and medic.id = :medicID");
			markers.add("medicID");
			values.add(hcp.getID_Hcp());
		}
		
		if( service != null)
		{
			hqlConditions.append(" and serv.id = :serviceID");
			markers.add("serviceID");
			values.add(service.getID_Service());
		}
		
		DomainFactory factory = getDomainFactory();
		List<?> list = factory.find((hql + hqlConditions.toString()).toString(), markers, values,400);
		 
		if (list != null && list.size() > 0)
			return (MedicLiteVoAssembler.createMedicLiteVoCollectionFromMedic(list)).toIMosArray()[0];
			
		return null;
	}

	public IMos[] getMedics(String name, MedicGrade medicGrade, ServiceRefVo service, Boolean isResponsibleHCP, Boolean isEndoscopist)
	{
		if(name == null || (name != null && name.length() == 0))
			throw new DomainRuntimeException("Can not search on null name.");
		
		StringBuffer hqlConditions = new StringBuffer();

		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		String andStr = "";

		/* TODO MSSQL case - String hql = "select medic from Medic as medic left join medic.serviceFunction as servFunct left join servFunct.service as serv where medic.isActive = 1 and (medic.mos.name.upperSurname like :hcpSname or medic.mos.name.upperForename like :hcpFname)"; */
		String hql = "select medic from Medic as medic left join medic.serviceFunction as servFunct left join servFunct.service as serv where medic.isActive = true and (medic.mos.name.upperSurname like :hcpSname or medic.mos.name.upperForename like :hcpFname)";

		markers.add("hcpSname");
		values.add(name.toUpperCase() + "%");
		markers.add("hcpFname");
		values.add(name.toUpperCase() + "%");
		andStr = " and ";
		
		if (medicGrade != null)
		{
			hqlConditions.append(andStr+ " medic.grade.id = :idMedicGrade");
			markers.add("idMedicGrade");
			values.add(medicGrade.getID());
			andStr = " and ";
		}
		
		if (service != null)
		{
			hqlConditions.append(andStr+ " serv.id = :serviceID");
			markers.add("serviceID");
			values.add(service.getID_Service());
			andStr = " and ";
		}
		
		if(Boolean.TRUE.equals(isResponsibleHCP))
		{
			hqlConditions.append(" and medic.isHCPaResponsibleHCP = :isRespHcp");
			markers.add("isRespHcp");
			values.add(isResponsibleHCP);
			andStr = " and ";
		}
		
		if(Boolean.TRUE.equals(isEndoscopist))
		{
			hqlConditions.append(" and medic.isHCPaEndoscopist = :isHcpEndoscopist");
			markers.add("isHcpEndoscopist");
			values.add(isEndoscopist);
		}
		
		DomainFactory factory = getDomainFactory();
		List<?> list = factory.find((hql + hqlConditions.toString() + " order by medic.mos.name.upperSurname , medic.mos.name.upperForename asc").toString(), markers, values,400);
		 
		if (list != null && list.size() > 0)
			return (MedicLiteVoAssembler.createMedicLiteVoCollectionFromMedic(list)).toIMosArray(); //wdev-15687
			
		return null;
	}

	public SessionParentChildSlotVoCollection getParentChildSlotsForSession(Sch_SessionRefVo session)
	{
		if(session == null || session.getID_Sch_Session() == null)
			return null;

		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();

		String hql = "select slots from Sch_Session as session left join session.parentChildSlots as slots where session.id = :sessionID ";
		
		markers.add("sessionID");
		values.add(session.getID_Sch_Session());
		
		DomainFactory factory = getDomainFactory();
		List<?> list = factory.find(hql.toString(), markers, values);
		 
		if (list != null && list.size() > 0)
			return SessionParentChildSlotVoAssembler.createSessionParentChildSlotVoCollectionFromSessionParentChildSlot(list);
			
		return null;
	}

	public MedicGradeVo getMedicGrade(Integer hcpID)
	{
		if(hcpID == null)
			return null;
		
		return MedicGradeVoAssembler.create((Medic) getDomainFactory().getDomainObject(Medic.class, hcpID));
	
	}

	@Override
	public LocationLiteVoCollection listLocationsByParentLocation(LocationRefVo parentLocation) 
	{
		if (parentLocation == null)
			return null;
		
		OrganisationAndLocation orgLoc = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listLocationsByTheParentLocation(LocationType.THEATRE, parentLocation, Boolean.TRUE, null, null,null, null).sort();
	}
}
