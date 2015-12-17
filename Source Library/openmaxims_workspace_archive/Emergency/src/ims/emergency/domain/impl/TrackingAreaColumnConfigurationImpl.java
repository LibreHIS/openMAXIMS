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
// This code was generated by Bogdan Tofei using IMS Development Environment (version 1.80 build 4342.23748)
// Copyright (C) 1995-2012 IMS MAXIMS. All rights reserved.

package ims.emergency.domain.impl;

import java.util.Iterator;
import java.util.Set;

import ims.domain.DomainFactory;
import ims.domain.lookups.LookupMapping;
import ims.emergency.domain.base.impl.BaseTrackingAreaColumnConfigurationDialogImpl;
import ims.vo.LookupMappingVo;
import ims.vo.LookupMappingVoCollection;

public class TrackingAreaColumnConfigurationImpl extends BaseTrackingAreaColumnConfigurationDialogImpl
{

	private static final long serialVersionUID = 1L;

	public ims.vo.LookupInstVo getMappings(ims.vo.LookupInstVo instance)
	{
		DomainFactory factory = getDomainFactory();
		ims.domain.lookups.LookupInstance doInst = factory.getLookupInstance(instance.getId());
		Set mappings = doInst.getMappings();
		LookupMappingVoCollection mapColl = new LookupMappingVoCollection();
		Iterator iter = mappings.iterator();
		LookupMapping doMapping;
		while (iter.hasNext())
		{
			doMapping = (LookupMapping) iter.next();
			mapColl.add(new LookupMappingVo(doMapping.getExtSystem(), doMapping.getExtCode()));
		}
		mapColl.sort();
		instance.setMappings(mapColl);
		return instance;
	}
}