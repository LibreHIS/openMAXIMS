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
// This code was generated by Daniel Laffan using IMS Development Environment (version 1.65 build 3225.30788)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.domain.impl;

import ims.RefMan.domain.base.impl.BaseBookSpecimenAppointmentDialogImpl;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.domain.DomainFactory;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.exceptions.CodingRuntimeException;
import ims.ocrr.orderingresults.domain.objects.SpecimenWorkListItem;
import ims.ocrr.vo.SpecimenWorkListItemLiteVoCollection;
import ims.ocrr.vo.domain.SpecimenWorkListItemLiteVoAssembler;
import ims.scheduling.domain.DirectoryOfServiceAdmin;
import ims.scheduling.domain.SessionAdmin;
import ims.scheduling.domain.impl.DirectoryOfServiceAdminImpl;
import ims.scheduling.domain.impl.SessionAdminImpl;

import java.util.Iterator;
import java.util.List;

public class BookSpecimenAppointmentDialogImpl extends BaseBookSpecimenAppointmentDialogImpl
{
	private static final long serialVersionUID = 1L;

	/**
	* listLocationLite
	*/
	public ims.core.vo.LocationLiteVoCollection listLocationLite()
	{
		DirectoryOfServiceAdmin impl = (DirectoryOfServiceAdmin) getDomainImpl(DirectoryOfServiceAdminImpl.class);
		return impl.listLocationLiteByName(null);
	}

	public void saveWorkListItems(SpecimenWorkListItemLiteVoCollection workListItems) throws StaleObjectException
	{
		if(workListItems == null)
			throw new CodingRuntimeException("workListItems is null in method saveWorkListItems");
		
		DomainFactory factory = getDomainFactory();
		
		List doWorkListItems = SpecimenWorkListItemLiteVoAssembler.extractSpecimenWorkListItemList(factory, workListItems);
		if(doWorkListItems != null && doWorkListItems.size() > 0)
		{
			Iterator it = doWorkListItems.iterator();
			while(it.hasNext())
			{
				SpecimenWorkListItem doWorkListItem =   (SpecimenWorkListItem)it.next();
				factory.save(doWorkListItem);
			}
		}
	}

	public void updateCatsReferralAdditionalInvStatus(CatsReferralRefVo catsReferral) throws StaleObjectException
	{
		SessionAdmin impl = (SessionAdmin) getDomainImpl(SessionAdminImpl.class);
		impl.updateCatsReferralAdditionalInvStatus(catsReferral,null);
	}
}
