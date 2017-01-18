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
// This code was generated by Sean Nesbitt using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2013 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;


import java.util.ArrayList;
import java.util.List;

import ims.admin.vo.ElectiveListConfigSearchCriteriaVo;
import ims.admin.vo.ServiceForElectiveListConfigVoCollection;
import ims.RefMan.domain.ElectiveListManagement;
import ims.RefMan.domain.SelectElectiveListConfiguration;
import ims.RefMan.domain.base.impl.BaseTransferElectiveListDialogImpl;
import ims.RefMan.domain.objects.PatientElectiveList;
import ims.RefMan.vo.ElectiveListConfigurationLiteVo;
import ims.RefMan.vo.ElectiveListConfigurationLiteVoCollection;
import ims.RefMan.vo.PatientElevectiveListManagementVoCollection;
import ims.RefMan.vo.domain.ElectiveListConfigurationLiteVoAssembler;
import ims.RefMan.vo.domain.PatientElevectiveListManagementVoAssembler;
import ims.core.clinical.vo.ServiceRefVo;
import ims.core.configuration.domain.objects.ElectiveListConfiguration;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.HcpLiteVoCollection;
import ims.core.vo.lookups.Specialty;
import ims.core.vo.lookups.WaitingListStatus;
import ims.domain.DomainFactory;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.exceptions.CodingRuntimeException;

public class TransferElectiveListDialogImpl extends BaseTransferElectiveListDialogImpl
{
	private static final long serialVersionUID = 1L;

	public ims.RefMan.vo.PatientElevectiveListManagementVoCollection getElectiveListEntries(ims.admin.vo.ServiceForElectiveListConfigVo service, 
			Integer electivelistId, 
			String electivelistcode, 
			ims.core.vo.HcpLiteVo consultant, 
			ims.framework.utils.Date datefrom, 
			ims.framework.utils.Date dateto, 
			ims.core.vo.lookups.WaitingListStatus status, Boolean showAdmitted, Boolean showSuspended)
 	{
		
		if (service == null && electivelistId == null && electivelistcode == null && consultant == null && datefrom == null && dateto == null && status == null)
			throw new CodingRuntimeException("At least one search criteria must be provided");

		DomainFactory factory = getDomainFactory();

		StringBuffer hqlConditions = new StringBuffer(); 
		
		StringBuffer hql = new StringBuffer("select p1_1 from PatientElectiveList as p1_1 left join p1_1.electiveList as w1_1 left join p1_1.electiveListStatus as e1_1 left join e1_1.electiveListStatus as l1_1 left join p1_1.tCIDetails as p2_1 left join p2_1.currentOutcome as t2_1 left join t2_1.outcome as l2_1");
		
		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();

		String andStr = "";
		if( service != null)
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" w1_1.service.id = :Service ");
			markers.add("Service");
			values.add(service.getID_Service());
			andStr = " and ";
		}
		if( electivelistId != null )
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" w1_1.id = :WaitingListId ");
			markers.add("WaitingListId");
			values.add(electivelistId);
			andStr = " and ";
		}
		if( electivelistcode != null )
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" UPPER(w1_1.waitingListCode) like :WaitingListCode ");
			markers.add("WaitingListCode");
			values.add(electivelistcode.toUpperCase()+"%");
			andStr = " and ";
		}
		if( consultant != null )
		{
 			hql.append(" left join p1_1.consultant as consultants ");
			hqlConditions.append(andStr);
			hqlConditions.append(" consultants.id = :consultantId");
			markers.add("consultantId");
			values.add(consultant.getID_Hcp());
			andStr = " and ";
		}		
		
		hqlConditions.append(" and (e1_1.electiveListStatus = " + WaitingListStatus.CREATED.getID() + " or e1_1.electiveListStatus = " + WaitingListStatus.REQUIRES_TCI.getID() + ")");

		
		if (hqlConditions.length() > 0)
		{
			hqlConditions.insert(0, " where (");
			hqlConditions.append(" ) ");
		}

		List<?> list = factory.find(hql.toString() + hqlConditions.toString() + "order by p1_1.dateOnList desc", markers, values, 2000);

		if (list == null || list.size() == 0)
			return null;
		
		
		return PatientElevectiveListManagementVoAssembler.createPatientElevectiveListManagementVoCollectionFromPatientElectiveList(list);		
		
	}
	
	public HcpLiteVoCollection listConsultants(String name) 
	{
		ElectiveListManagement impl = (ElectiveListManagement) getDomainImpl(ElectiveListManagementImpl.class);
		return impl.listConsultants(name);
	}

	
	public ServiceForElectiveListConfigVoCollection listServices(String name) 
	{
		ElectiveListManagement impl = (ElectiveListManagement) getDomainImpl(ElectiveListManagementImpl.class);
		return impl.listServices(name);
	}
	
	private String getHCPId(HcpLiteVo listOwner)
	{
		StringBuffer idList=new StringBuffer();
	
		if (listOwner!= null)
		{
			idList.append("(");
			idList.append(listOwner.getID_Hcp());
			idList.append(")");
		}
		else
			idList.append("()");
		
		return idList.toString();
	}

	public ElectiveListConfigurationLiteVoCollection getElectiveListConfigurations(
			ElectiveListConfigSearchCriteriaVo voFilter, Boolean other) 
	{
		SelectElectiveListConfiguration impl = (SelectElectiveListConfiguration) getDomainImpl(SelectElectiveListConfigurationImpl.class);
		
		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		DomainFactory factory = getDomainFactory();

		StringBuffer hqlConditions = new StringBuffer();
				
		String andStr = "";
		
		StringBuffer hql = new StringBuffer("select eLC from ElectiveListConfiguration as eLC left join eLC.hCPs as hcps left join hcps.hCP as hcp  left join eLC.listLocations as locations left join locations.listLocation as location ");
		
		if (voFilter.getServiceIsNotNull() && voFilter.getService().toServiceRefVo()!=null) 
		{
			hqlConditions.append(andStr);
			if (other)
				hqlConditions.append(" eLC.service.id=:Service and hcps is null");
			else
				hqlConditions.append(" eLC.service.id=:Service");
			markers.add("Service");
			values.add(voFilter.getService().toServiceRefVo().getID_Service());
			andStr = " and ";
		}
		else
		{
			hqlConditions.append(" hcps is null ");
		}		

		if (!other)
		{
			if (voFilter.getHospitalIsNotNull())
			{
				hqlConditions.append(andStr);
				hqlConditions.append("( location.id=:LocID or locations is null )");
				markers.add("LocID");
				values.add(voFilter.getHospital().getID_Location());
				andStr = " and ";
			}
		}
		
		if( voFilter.getWaitingListNameIsNotNull() )
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" UPPER(eLC.waitingListName) like :WaitingListName ");
			markers.add("WaitingListName");
			values.add(voFilter.getWaitingListName().toUpperCase()+"%");
		}
		
		/*
		StringBuffer hqlCond3= new StringBuffer();
		andStr="";
		if (specialty!=null)
		{
			hqlCond3.append(" service.specialty.id=:SpecialtyID ");
			markers.add("SpecialtyID");
			values.add(specialty.getID());
			andStr = " and ";
		}
		
		if (listIdHcp!=null)
		{
			hqlCond3.append(andStr);
			hqlCond3.append("listOwners.hCP.id in "+ listIdHcp ); 
		}
		

		StringBuffer hqlCond4= new StringBuffer();
		andStr="";
	
		if (specialty!=null)
		{
			hqlCond4.append(" service.specialty.id=:SpecialtyID ");
			markers.add("SpecialtyID");
			values.add(specialty.getID());
			andStr = " and ";
		}
		
		if (listIdHcp!=null)
		{
			hqlCond4.append(andStr);
			hqlCond4.append(" listOwners is null "); 
		}
	
		String orStr = "";
		
		if (hqlCond2.length()>0)
		{
			hqlCond2.insert(0, " ( ");
			hqlCond2.append(" ) ");
			hqlConditions.append(orStr);
			hqlConditions.append(hqlCond2);
			orStr=" OR ";
		}
		
		if (hqlCond3.length()>0)
		{
			hqlCond3.insert(0, " ( ");
			hqlCond3.append(" ) ");
			hqlConditions.append(orStr);
			hqlConditions.append(hqlCond3);
			orStr=" OR ";
		}
		
		if (hqlCond4.length()>0)
		{
			hqlCond4.insert(0, " ( ");
			hqlCond4.append(" ) ");
			hqlConditions.append(orStr);
			hqlConditions.append(hqlCond4);
			orStr=" OR ";
		}
	*/
		if (hqlConditions.length() > 0)
		{
			hqlConditions.insert(0, " where (");
			hqlConditions.append(" ) ");
		}
		
		List<?> list = factory.find(hql.toString() + hqlConditions.toString() + " order by UPPER(eLC.waitingListName) asc ",markers, values);

		if (list == null || list.size() == 0)
			return null;

		return ElectiveListConfigurationLiteVoAssembler.createElectiveListConfigurationLiteVoCollectionFromElectiveListConfiguration(list);	
	}

	public PatientElevectiveListManagementVoCollection transferToElectiveList(PatientElevectiveListManagementVoCollection sourceElEntries, ElectiveListConfigurationLiteVo targetElectiveList) throws StaleObjectException 
	{
		if (targetElectiveList == null)
		{
			throw new CodingRuntimeException("Cannot save null targetElectiveList");
		}
						
		if (sourceElEntries != null)
		{
			DomainFactory factory = getDomainFactory();
			List domainPatElectiveList = PatientElevectiveListManagementVoAssembler.extractPatientElectiveListList(factory, sourceElEntries);
			ElectiveListConfiguration domTargetEl = ElectiveListConfigurationLiteVoAssembler.extractElectiveListConfiguration(factory, targetElectiveList);

			for (int i=0; i<domainPatElectiveList.size();i++)
			{
				PatientElectiveList domel = (PatientElectiveList)domainPatElectiveList.get(i);
				//create new EL
				domel.setElectiveList(domTargetEl);
				factory.update(domel);
			}
		}
		
		return null;
	}

	public ElectiveListConfigurationLiteVoCollection listElectiveListConfiguration(
			Specialty specialty, LocationRefVo locationRef, String listIdHcp,
			String electiveListName) 	
	{
		DomainFactory factory = getDomainFactory();

		StringBuffer hqlConditions = new StringBuffer();
		
		ArrayList<String> markers = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		String andStr = "";
		
		StringBuffer hql = new StringBuffer("select eLC from ElectiveListConfiguration as eLC " +
				"left join eLC.service as services" +
				"left join eLC.hCPs as hcps left join hcps.hCP as hcp  " +
				"left join eLC.listLocations as locations " +
				"left join locations.listLocation as location ");
		
		if (specialty!=null) 
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" eLC.service.specialty.id=:Specialty");
			markers.add("Specialty");
			values.add(specialty.getID());
			andStr = " and ";
		}

		if (locationRef!=null)
		{
			hqlConditions.append(andStr);
			hqlConditions.append("( location.id=:LocID or locations is null )");
			markers.add("LocID");
			values.add(locationRef.getID_Location());
			andStr = " and ";
		}
		
		if (listIdHcp!=null && listIdHcp.length()>0)
		{	
			hqlConditions.append(andStr);
			hqlConditions.append("  hcp.id in "+ listIdHcp);
			andStr = " and ";
		}
		
		if(electiveListName != null)
		{
			hqlConditions.append(andStr);
			hqlConditions.append(" UPPER(eLC.waitingListName) like :WaitingListName ");
			markers.add("WaitingListName");
			values.add(electiveListName.toUpperCase()+"%");
			andStr = " and ";
		}

		hqlConditions.append(andStr);

		/* TODO MSSQL case - hqlConditions.append(" eLC.isActive = 1"); */
		hqlConditions.append(" eLC.isActive = TRUE");

		andStr = " and ";

		
		if (hqlConditions.length() > 0)
		{
			hqlConditions.insert(0, " where (");
			hqlConditions.append(" ) ");
		}
		
		List<?> list = factory.find(hql.toString() + hqlConditions.toString() + " order by UPPER(eLC.waitingListName) asc ",markers, values);

		if (list == null || list.size() == 0)
			return null;

		return ElectiveListConfigurationLiteVoAssembler.createElectiveListConfigurationLiteVoCollectionFromElectiveListConfiguration(list);
		
	}

	public ElectiveListConfigurationLiteVoCollection listOtherElectiveListConfiguration(ServiceRefVo serviceRef, Specialty specialty, String listIdHcp, String electiveListName)
	{
		//WDEV-19417
		if (serviceRef==null && listIdHcp==null)
		{
			return listElectiveListConfiguration(null, null, null, electiveListName);
		}
		else
		{
			SelectElectiveListConfiguration impl = (SelectElectiveListConfiguration) getDomainImpl(SelectElectiveListConfigurationImpl.class);
			return impl.listOtherElectiveListConfiguration(serviceRef, specialty, listIdHcp, electiveListName,Boolean.TRUE);
		}	
	}
}
