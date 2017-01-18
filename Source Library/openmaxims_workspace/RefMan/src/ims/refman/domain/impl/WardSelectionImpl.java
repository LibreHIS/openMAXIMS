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
// This code was generated by Cristian Belciug using IMS Development Environment (version 1.80 build 5332.26009)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;

import ims.admin.domain.OrganisationAndLocation;
import ims.admin.domain.impl.OrganisationAndLocationImpl;
import ims.RefMan.domain.InpatientClinicalCodingWorklist;
import ims.RefMan.domain.base.impl.BaseWardSelectionImpl;
import ims.core.resource.place.domain.objects.Location;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.domain.LocationLiteVoAssembler;
import ims.core.vo.lookups.LocationType;
import ims.domain.DomainFactory;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.interfaces.ILocation;

import java.util.ArrayList;
import java.util.List;

public class WardSelectionImpl extends BaseWardSelectionImpl
{
	private static final long serialVersionUID = 1L;

	public ims.core.vo.LocationLiteVoCollection listLocations(LocationRefVo locationRef, LocationType locationType, String searchText)
	{
		if (locationRef == null || locationRef.getID_Location() == null)
			throw new CodingRuntimeException("Cannot list wards for a null Hospital Id.");

		if(LocationType.CASE_NOTE_FOLDER_LOCATION.equals(locationType)) //WDEV-20395
		{
			return listActiveLocationThatAreCaseNoteFolderLocations(locationRef, searchText);//WDEV-22088
		}
		return listActiveWards(locationRef, searchText);
	}

	//WDEV-20395
	private LocationLiteVoCollection listActiveLocationThatAreCaseNoteFolderLocations(LocationRefVo locationRef, String searchText)
	{
		OrganisationAndLocation orgLoc = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listLocationsByTheParentLocation(null, locationRef, Boolean.TRUE, null, null,Boolean.TRUE, searchText);
	}

	private LocationLiteVoCollection listActiveWards(LocationRefVo locationRef, String searchText)
	{
		InpatientClinicalCodingWorklist impl = (InpatientClinicalCodingWorklist) getDomainImpl(InpatientClinicalCodingWorklistImpl.class);
		return  impl.listWards(locationRef, searchText);
	}

	private LocationLiteVoCollection listCaseNoteFolderLocations(LocationRefVo hospital, String searchText)
	{
		StringBuilder query = new StringBuilder("SELECT location FROM Location AS location LEFT JOIN location.parentLocation AS hospital WHERE ");
		query.append(" hospital.id = :HOSP_ID ");

		/* TODO MSSQL case - query.append(" AND location.isActive = 1 AND location.caseNoteFolderLocation = 1 "); */
		query.append(" AND location.isActive = TRUE AND location.caseNoteFolderLocation = TRUE ");
		
		ArrayList<String> paramNames = new ArrayList<String>();
		ArrayList<Object> paramValues = new ArrayList<Object>();
		
		paramNames.add("HOSP_ID");
		paramValues.add(hospital.getID_Location());
		
		if(searchText != null && searchText.length() > 0)
		{
			query.append(" and location.upperName like :LocationName ");
			paramNames.add("LocationName");
			paramValues.add(searchText.toUpperCase() + "%");
		}
		
		query.append(" order by location.upperName asc ");
		
		return LocationLiteVoAssembler.createLocationLiteVoCollectionFromLocation(getDomainFactory().find(query.toString(), paramNames, paramValues));
	}

	public LocationLiteVoCollection listBuildings(LocationRefVo locationRef, LocationType locationType, String searchText)
	{
		OrganisationAndLocation orgLoc = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listLocationsByTheParentLocation(LocationType.BUILDING, locationRef, Boolean.TRUE, null, null,null, searchText);
	}

	@Override
	public LocationLiteVoCollection listWards(LocationRefVo hospital, String wardName)
	{
		OrganisationAndLocation orgLoc = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listActiveWardsForHospitalByNameLite(hospital, wardName);
	}

	@Override
	public LocationLiteVoCollection listHospitals()
	{
		return listLocations(null, LocationType.HOSP);
	}
	
	private LocationLiteVoCollection listLocations(String name, LocationType locType)
	{
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		String hql = "from Location loc where (loc.type.id = :typeId";
		names.add("typeId");
		values.add(new Integer(locType.getId()));
		
		if (name != null)
		{
			hql += " and loc.upperName like :name ";
			names.add("name");
			values.add(name.toUpperCase() + "%");
		}

		/* TODO MSSQL case - hql += (" and loc.isVirtual = :virtual and loc.isActive = 1) order by loc.upperName asc"); */
		hql += (" and loc.isVirtual = :virtual and loc.isActive = TRUE) order by loc.upperName asc");

		names.add("virtual");
		values.add(Boolean.FALSE);
		
		List<?> l = this.getDomainFactory().find(hql, names, values);
		return LocationLiteVoAssembler.createLocationLiteVoCollectionFromLocation(l);
	}
	
	public LocationLiteVo getCurrentHospital(ILocation location)
	{
		if(location == null)
			return null;
		
		DomainFactory factory = getDomainFactory();
		
		Location currentHospital = getHospital((Location) factory.getDomainObject(Location.class, location.getID()));
		
		if(currentHospital instanceof Location)
			return LocationLiteVoAssembler.create((Location) currentHospital);
		
		return null;
	}
	
	//WDEV-23039
	private Location getHospital(Location doLocation)
	{
		if(doLocation == null)
			return null;
		
		if(doLocation instanceof Location && doLocation.getType().equals(getDomLookup(LocationType.HOSP)))
			return doLocation;
	
		while(doLocation.getParentLocation() != null) 
		{
			doLocation = doLocation.getParentLocation();
			if(doLocation instanceof Location && doLocation.getType().equals(getDomLookup(LocationType.HOSP)))
				return doLocation;
		}
		
		return null;
	}
	
}
