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

package ims.admin.domain.base.impl;

import ims.domain.impl.DomainImpl;

public abstract class BaseLookupTreeImpl extends DomainImpl implements ims.admin.domain.LookupTree, ims.domain.impl.Transactional
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void validaterefreshCache(ims.vo.LookupTypeVo type)
	{
	}

	@SuppressWarnings("unused")
	public void validategetLookupInstances(ims.vo.LookupTypeVo type)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveLookupInstance(ims.vo.LookupTypeVo type, ims.vo.LookupInstVo instance)
	{
	}

	@SuppressWarnings("unused")
	public void validategetMappings(ims.vo.LookupInstVo instance)
	{
	}

	@SuppressWarnings("unused")
	public void validategetFormLookups(ims.core.configuration.vo.AppFormRefVo formRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatedeactivateInstance(ims.vo.LookupTypeVo type, ims.vo.LookupInstVo voInstance)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveInstanceOrder(ims.vo.LookupTypeVo type, ims.vo.LookupInstanceCollection instances)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveLookupType(ims.vo.LookupTypeVo typeVo)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveDefaultInstance(ims.core.configuration.vo.AppFormRefVo formId, ims.admin.vo.FormLookupVo defInst)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistForms(String nameFilter)
	{
	}

	@SuppressWarnings("unused")
	public void validategetTypes(String filter, Boolean activeOnly, Boolean userTypesOnly, Boolean systemTypes)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveInstanceDataFromHL7(ims.core.vo.IfLookupVo lookupData)
	{
	}
}