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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.54 build 2685.26610)
// Copyright (C) 1995-2007 IMS MAXIMS plc. All rights reserved.

package ims.clinical.domain.impl;

import ims.admin.domain.GPAdmin;
import ims.admin.domain.MosAdmin;
import ims.admin.domain.OrganisationAndLocation;
import ims.admin.domain.PracticeSearch;
import ims.admin.domain.impl.GPAdminImpl;
import ims.admin.domain.impl.MosAdminImpl;
import ims.admin.domain.impl.OrganisationAndLocationImpl;
import ims.admin.domain.impl.PracticeSearchImpl;
import ims.clinical.domain.base.impl.BasePreceedingReferralImpl;
import ims.core.resource.people.vo.GpRefVo;
import ims.core.vo.GP;
import ims.core.vo.GpShortVoCollection;

public class PreceedingReferralImpl extends BasePreceedingReferralImpl
{

	private static final long serialVersionUID = 1L;

	/**
	* List Organisations for given search criteria
	*/
	public ims.core.vo.OrganisationVoCollection listOrganisation(ims.core.vo.OrganisationVo organisation)
	{
		OrganisationAndLocation org = (OrganisationAndLocation) getDomainImpl(OrganisationAndLocationImpl.class);
		return org.listOrganisation(organisation, Boolean.TRUE, Boolean.TRUE);
	}

	/**
	* list Hcps by a HcpFilter and then a subtype(e.g) MedicType - The class will be detemined by the hcptype populated in the HcpFilter
	*/
	public ims.core.vo.HcpCollection listHcps(ims.core.vo.HcpFilter filter, ims.core.vo.lookups.HcpDisType subType)
	{
		MosAdmin impl = (MosAdmin) getDomainImpl(MosAdminImpl.class);
		return impl.listHcps(filter);
	}

	/**
	* Returns a MosList based on the search criteria in the passed in filter
	*/
	public ims.core.vo.MemberOfStaffShortVoCollection listMembersOfStaff(ims.core.vo.MemberOfStaffShortVo filter)
	{
		MosAdmin mosAdminIml = (MosAdmin)getDomainImpl(MosAdminImpl.class);
		return mosAdminIml.listMembersOfStaff(filter);
	}

	public ims.core.vo.OrganisationWithSitesVoCollection listPractice(ims.admin.vo.PracticeSearchCriteriaVo filter)
	{
		PracticeSearch prac = (PracticeSearch) getDomainImpl(PracticeSearchImpl.class);
		return prac.listPractice(filter);
	}

	public GpShortVoCollection listGPsBySurname(String surname) 
	{
		GPAdmin gps = (GPAdmin) getDomainImpl(GPAdminImpl.class);
		return gps.listGPsBySurname(surname);
	}

	public GP getGP(GpRefVo gpRefVo) 
	{
		GPAdmin gps = (GPAdmin) getDomainImpl(GPAdminImpl.class);
		return gps.getGP(gpRefVo.getID_Gp());
	}
}