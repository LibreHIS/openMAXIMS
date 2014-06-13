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

package ims.correspondence.domain;

// Generated from form domain impl
public interface AttendancesContacts extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	/**
	* listPASContacts
	*/
	public ims.correspondence.vo.PasContactVoCollection listPASContacts(ims.core.vo.PatientShort patient);

	// Generated from form domain interface definition
	public ims.core.vo.PasEventVo getPasEvent(ims.core.admin.pas.vo.PASEventRefVo voPasEventRef);

	// Generated from form domain interface definition
	public ims.correspondence.vo.CorrespondenceDetailsVo getCorrespondenceDetails(ims.correspondence.vo.CorrespondenceDetailsRefVo voCorrespDetRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.Patient getPatient(ims.core.vo.PatientShort voFilter) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	/**
	* updateDocumentStatus
	*/
	public ims.correspondence.vo.CorrespondenceDetailsVo saveCorrespondenceDetails(ims.correspondence.vo.CorrespondenceDetailsVo voCorrespDet, ims.vo.LookupInstVo pasEventType) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException, ims.domain.exceptions.UniqueKeyViolationException;

	// Generated from form domain interface definition
	public ims.correspondence.vo.CorrespondenceDetailsVo getCorrespondenceDetailsByPasEvent(ims.core.admin.pas.vo.PASEventRefVo voPasEventRef);

	// Generated from form domain interface definition
	public ims.core.vo.PasEventVo getPasEvent(ims.core.patient.vo.PatientRefVo voPatienRef, String pasEventId);

	// Generated from form domain interface definition
	public ims.core.vo.PasEventVo createPasEvent(ims.framework.utils.DateTime eventDateTime, ims.core.patient.vo.PatientRefVo voRefPatient, ims.correspondence.vo.PasContactVo voPasContact) throws ims.domain.exceptions.StaleObjectException, ims.domain.exceptions.UniqueKeyViolationException;

	// Generated from form domain interface definition
	public ims.correspondence.vo.UserAccessFullVo getFullUserAccessForUser(Integer appUserID);
}