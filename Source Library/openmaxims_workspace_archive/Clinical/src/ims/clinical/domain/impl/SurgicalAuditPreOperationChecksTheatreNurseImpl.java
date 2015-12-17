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
// This code was generated by Cornel Ventuneac using IMS Development Environment (version 1.80 build 4535.14223)
// Copyright (C) 1995-2012 IMS MAXIMS. All rights reserved.

package ims.clinical.domain.impl;

import ims.clinical.domain.base.impl.BaseSurgicalAuditPreOperationChecksTheatreNurseImpl;
import ims.clinical.domain.objects.SurgicalAuditPreOpChecksTheatreNurse;
import ims.clinical.helper.IESurgicalAuditHelper;
import ims.clinical.helper.SurgicalAuditHelper;
import ims.clinical.vo.PatientProcedureForSurgicalAuditPreOpChecksVoCollection;
import ims.clinical.vo.SurgicalAuditOperationDetailRefVo;
import ims.clinical.vo.SurgicalAuditOperationDetailVo;
import ims.clinical.vo.SurgicalAuditPreOpChecksRefVo;
import ims.clinical.vo.SurgicalAuditPreOpChecksTheatreNurseVo;
import ims.clinical.vo.domain.PatientProcedureForSurgicalAuditPreOpChecksVoAssembler;
import ims.clinical.vo.domain.SurgicalAuditPreOpChecksTheatreNurseVoAssembler;
import ims.core.resource.people.vo.HcpRefVo;
import ims.core.resource.place.domain.objects.Location;
import ims.core.resource.place.vo.LocSiteRefVo;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.Hcp;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.domain.HcpLiteVoAssembler;
import ims.core.vo.domain.LocationLiteVoAssembler;
import ims.core.vo.lookups.LocationType;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.framework.exceptions.CodingRuntimeException;

import java.util.List;


public class SurgicalAuditPreOperationChecksTheatreNurseImpl extends BaseSurgicalAuditPreOperationChecksTheatreNurseImpl
{

	private static final long serialVersionUID = 1L;

	
	public ims.core.vo.LocationLiteVoCollection getTheatre(ims.core.resource.place.vo.LocationRefVo locRef)
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.getTheatre(locRef);
	}

	
	public SurgicalAuditOperationDetailVo saveSurgicalAuditOperationDetailsVo( SurgicalAuditOperationDetailVo record) throws StaleObjectException, UniqueKeyViolationException 
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.saveSurgicalAuditOperationDetail(record);
	}

	
	public SurgicalAuditOperationDetailVo getSurgicalAuditOperationDetailsVo(SurgicalAuditOperationDetailRefVo surgicalAuditOperationDetailRef) 
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.getSurgicalAuditOperationDetail(surgicalAuditOperationDetailRef);
	}

	
	public LocationLiteVo getLocationLiteVo(LocSiteRefVo locRef) 
	{
		if( locRef == null)
			throw new DomainRuntimeException("Location cannot be null.");
		
		
		DomainFactory factory = getDomainFactory();
		Location doLocation = (Location) factory.getDomainObject(Location.class, locRef.getID_Location());
		return LocationLiteVoAssembler.create(doLocation);
	}


	
	public PatientProcedureForSurgicalAuditPreOpChecksVoCollection listProcedures( SurgicalAuditPreOpChecksRefVo surgicalAuditPreOpChecksRef) 
	{
		if (surgicalAuditPreOpChecksRef == null || surgicalAuditPreOpChecksRef.getID_SurgicalAuditPreOpChecks() == null)
		{
			throw new CodingRuntimeException("Cannot get PatientProcedureForSurgicalAuditPreOpChecksVoCollection on null Id for surgicalAuditPreOpChecksRef ");
		}

		DomainFactory factory = getDomainFactory();

		StringBuffer hql = new StringBuffer();
		hql.append("select procedures from SurgicalAuditPreOpChecks as surgAuditPreOP left join surgAuditPreOP.plannedProcedures as procedures where surgAuditPreOP.id = :surgPreOpId order by procedures.procedureDescription asc ");

		List<?> list = factory.find(hql.toString(), new String[] { "surgPreOpId" }, new Object[] { surgicalAuditPreOpChecksRef.getID_SurgicalAuditPreOpChecks()});

		return PatientProcedureForSurgicalAuditPreOpChecksVoAssembler.createPatientProcedureForSurgicalAuditPreOpChecksVoCollectionFromPatientProcedure(list);
	
	}

	public SurgicalAuditPreOpChecksTheatreNurseVo savePreOpChecksTheatreNurse(SurgicalAuditPreOpChecksTheatreNurseVo preOpChecksTheatreNurseToSave) throws StaleObjectException
	{
		if (preOpChecksTheatreNurseToSave == null )
		{
			throw new CodingRuntimeException("Cannot get SurgicalAuditPreOpChecksTheatreNurseVo on null Id ");
		}

		DomainFactory factory = getDomainFactory();
		
		 SurgicalAuditPreOpChecksTheatreNurse domainSurgAuditPreOpChecksTheatreNurse = SurgicalAuditPreOpChecksTheatreNurseVoAssembler.extractSurgicalAuditPreOpChecksTheatreNurse(factory, preOpChecksTheatreNurseToSave);
		factory.save(domainSurgAuditPreOpChecksTheatreNurse);
		return SurgicalAuditPreOpChecksTheatreNurseVoAssembler.create(domainSurgAuditPreOpChecksTheatreNurse);
	}

	public Boolean isStale(SurgicalAuditOperationDetailVo surgAudit)
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.isStale(surgAudit);
	}

	//WDEV-15675
	public LocationLiteVoCollection listLocationsByParentLocation(LocationRefVo locationRef, ims.core.vo.lookups.LocationType locType)
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.listLocationsByParentLocation(locationRef, locType);	
	}

	//WDEV-15675
	public LocationLiteVoCollection listActiveLocationsAtTheSameLevelWithLocation(LocationRefVo locationRef, ims.core.vo.lookups.LocationType locType)
	{
		IESurgicalAuditHelper impl = (IESurgicalAuditHelper)getDomainImpl(SurgicalAuditHelper.class);
		return impl.listActiveLocationsAtTheSameLevelWithLocation(locationRef, locType);
	}


	//wdev-15717
	public HcpLiteVo getHcpLiteVo(HcpRefVo hcpRef) 
	{
		if( hcpRef == null)
			throw new DomainRuntimeException("Hcp cannot be null.");
		
		
		DomainFactory factory = getDomainFactory();
		ims.core.resource.people.domain.objects.Hcp doHcp = (ims.core.resource.people.domain.objects.Hcp) factory.getDomainObject(ims.core.resource.people.domain.objects.Hcp.class, hcpRef.getID_Hcp());
		return HcpLiteVoAssembler.create(doHcp);
		
	
	}


	//wdev-15717
	public LocationLiteVo getLocationLite(LocationRefVo locRef) 
	{
		DomainFactory factory = getDomainFactory();
		Location doLocation = (Location)factory.getDomainObject(Location.class, locRef.getID_Location());
		return getHospital(doLocation);
	}
	private LocationLiteVo getHospital(Location doLocation)
	{
		if( doLocation.getType().equals(getDomLookup(LocationType.HOSP)))
			return LocationLiteVoAssembler.create(doLocation);

		while(doLocation.getParentLocation() != null)
		{
			doLocation = doLocation.getParentLocation();
			if( doLocation.getType().equals(getDomLookup(LocationType.HOSP)))
				return LocationLiteVoAssembler.create(doLocation);
		}

		return null;
	}
	//------------
	
}