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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.65 build 3169.24390)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.domain.impl;

import ims.admin.domain.MosAdmin;
import ims.admin.domain.impl.MosAdminImpl;
import ims.RefMan.domain.ReferralDetailsComponent;
import ims.RefMan.domain.base.impl.BaseChangeServiceDialogImpl;
import ims.RefMan.domain.objects.CatsReferral;
import ims.RefMan.domain.objects.ChangeOfService;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.CatsReferralVo;
import ims.RefMan.vo.ChangeOfServiceVo;
import ims.RefMan.vo.ReferralServiceFullVo;
import ims.RefMan.vo.domain.CatsReferralVoAssembler;
import ims.RefMan.vo.domain.ChangeOfServiceVoAssembler;
import ims.core.clinical.domain.objects.Service;
import ims.core.configuration.domain.objects.ContractServiceLocationsConfig;
import ims.core.vo.MemberOfStaffShortVo;
import ims.core.vo.MemberOfStaffShortVoCollection;
import ims.core.vo.ServiceShortVoCollection;
import ims.core.vo.domain.ServiceShortVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;

import java.util.ArrayList;
import java.util.List;

public class ChangeServiceDialogImpl extends BaseChangeServiceDialogImpl
{

	private static final long serialVersionUID = 1L;

	public ReferralServiceFullVo getReferralServices() 
	{
		ReferralDetailsComponent doServices = (ReferralDetailsComponent)getDomainImpl(ReferralDetailsComponentImpl.class);
		return doServices.getReferralServices();
	}

	public void saveChangeOfServiceVo(ChangeOfServiceVo voCOS) throws DomainInterfaceException, StaleObjectException 
	{
		if(voCOS == null)
			throw new DomainRuntimeException("Invalid ChangeOfServiceVo to save");
		if(!voCOS.isValidated())
			throw new DomainRuntimeException("Record not validated before save");

		DomainFactory factory = getDomainFactory();
		ChangeOfService doCOS = ChangeOfServiceVoAssembler.extractChangeOfService(factory, voCOS);		
		factory.save(doCOS);
		
		CatsReferralVo voCats = CatsReferralVoAssembler.create((CatsReferral)getDomainFactory().getDomainObject(CatsReferral.class, voCOS.getCatsReferral().getID_CatsReferral()));
		voCats.getReferralDetails().setService(voCOS.getNewService());
		CatsReferral doCats = CatsReferralVoAssembler.extractCatsReferral(factory, voCats);		
		factory.save(doCats);
	}

	public MemberOfStaffShortVoCollection listMembersOfStaff(MemberOfStaffShortVo filter) 
	{
       MosAdmin mosAdminIml = (MosAdmin)getDomainImpl(MosAdminImpl.class);
       return mosAdminIml.listMembersOfStaff(filter);
	}

	//WDEV-20556
	public ServiceShortVoCollection getServicesForCatsReferral(CatsReferralRefVo catsRefVo)
	{
		if (catsRefVo == null || !catsRefVo.getID_CatsReferralIsNotNull())
			return null;
		
		CatsReferral catsReferral = (CatsReferral) getDomainFactory().getDomainObject(CatsReferral.class, catsRefVo.getID_CatsReferral());
		if (catsReferral != null && catsReferral.getContract() != null && catsReferral.getContract().getServiceLocations() != null)
		{
			List <?> lstServices = catsReferral.getContract().getServiceLocations();
	
			List <Service> listServices = new ArrayList<Service>();
			for (Object object : lstServices)
			{
				ContractServiceLocationsConfig contractServiceLocConfig = (ContractServiceLocationsConfig) object;
				Service service = contractServiceLocConfig.getService();
				listServices.add(service);
			}
			if(listServices != null && listServices.size() > 0)
			{
				return ServiceShortVoAssembler.createServiceShortVoCollectionFromService(listServices).sort();
			}
			return null;
		}
		return null;
	}

}
