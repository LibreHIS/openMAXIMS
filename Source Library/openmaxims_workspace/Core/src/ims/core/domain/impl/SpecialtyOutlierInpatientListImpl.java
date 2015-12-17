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
// This code was generated by Gabriel Maxim using IMS Development Environment (version 1.80 build 5360.17707)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.

package ims.core.domain.impl;

import ims.core.admin.pas.vo.InpatientEpisodeRefVo;
import ims.core.admin.pas.vo.PASEventRefVo;
import ims.core.admin.pas.vo.PendingTransfersRefVo;
import ims.core.domain.WardView;
import ims.core.domain.WardViewPatientList;
import ims.core.domain.base.impl.BaseSpecialtyOutlierInpatientListImpl;
import ims.core.layout.vo.BedSpaceRefVo;
import ims.core.patient.vo.PatientRefVo;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.BedSpaceStateLiteVo;
import ims.core.vo.CareContextShortVo;
import ims.core.vo.InpatientEpisodeLiteVo;
import ims.core.vo.PatientShort;
import ims.core.vo.PendingTransfersLiteVo;
import ims.core.vo.domain.SpecialtyOutlierInpatientEpisodeListVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;

import java.util.List;

public class SpecialtyOutlierInpatientListImpl extends BaseSpecialtyOutlierInpatientListImpl
{

	private static final long serialVersionUID = 1L;

	public ims.core.vo.SpecialtyOutlierInpatientEpisodeListVoCollection listInpatientEpisodes(LocationRefVo wardRef)
	{
		if (wardRef == null)
			return null;

		StringBuilder hql = new StringBuilder("SELECT inpat ");
		hql.append(" FROM PendingTransfers AS pendTrans RIGHT JOIN pendTrans.inpatientEpisode AS inpat ");
		hql.append(" LEFT JOIN inpat.pasEvent AS pas LEFT JOIN pas.patient AS patient ");
		hql.append(" LEFT JOIN pas.specialty AS inpatSpecialty ");
		hql.append(" LEFT JOIN inpat.bed AS bed ");

		hql.append(" WHERE pendTrans.id is null ");
		hql.append(" AND inpat.pasEvent.location.id = :WARD_ID");
		hql.append(" AND (inpat.isReadyToLeave is null OR inpat.isReadyToLeave = 0)");
		hql.append(" AND (inpatSpecialty is null OR inpatSpecialty.id NOT IN (");

		hql.append("SELECT wardSpecialty.id FROM WardBayConfig AS wardConfig LEFT JOIN wardConfig.ward AS ward LEFT JOIN wardConfig.specialties AS spec LEFT JOIN spec.instance AS wardSpecialty WHERE ward.id = :WARD_ID ");

		hql.append(")");
		hql.append(")");

		DomainFactory factory = getDomainFactory();

		List<?> results = factory.find(hql.toString(), new String[] {"WARD_ID"}, new Object[] {wardRef.getID_Location()});

		if (results == null || results.isEmpty())
			return null;

		return SpecialtyOutlierInpatientEpisodeListVoAssembler.createSpecialtyOutlierInpatientEpisodeListVoCollectionFromInpatientEpisode(results);
	}

	
	public InpatientEpisodeLiteVo getInpatientEpisodeLite(InpatientEpisodeRefVo inpatRef)
	{
		WardViewPatientList impl = (WardViewPatientList)getDomainImpl(WardViewPatientListImpl.class);
		return impl.getInpatientEpisodeLite(inpatRef);
	}
	
	public PendingTransfersLiteVo getPendingTranfer(PendingTransfersRefVo pendingTransferRefVo) 
	{
		WardViewPatientList impl = (WardViewPatientList)getDomainImpl(WardViewPatientListImpl.class);
		return impl.getPendingTransferLite(pendingTransferRefVo);
	}

	public void cancelTransfer(PendingTransfersLiteVo voPending, LocationRefVo wardRef) throws StaleObjectException, ForeignKeyViolationException
	{
		WardViewPatientList impl = (WardViewPatientList)getDomainImpl(WardViewPatientListImpl.class);
		 impl.cancelTransfer(voPending, wardRef);
		
	}

	public BedSpaceStateLiteVo getSelectedBedSpaceState(BedSpaceRefVo bedSpaceRef)
	{
		WardView impl = (WardView) getDomainImpl(WardViewImpl.class);
		return impl.getBedSpaceState(bedSpaceRef);
	}

	
	public PatientShort getPatientShort(PatientRefVo patientRef)
	{
		WardViewPatientList impl = (WardViewPatientList) getDomainImpl(WardViewPatientListImpl.class);
		return impl.getPatientShort(patientRef);
	}
	
	public CareContextShortVo getCareContextByPasEvent(PASEventRefVo pasEventRef)
	{
		WardViewPatientList impl = (WardViewPatientList) getDomainImpl(WardViewPatientListImpl.class);
		return impl.getCareContextForPasEvent(pasEventRef);
	}	
}