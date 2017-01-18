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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.onwardreferralrejection;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.RefMan.domain.OnwardReferralRejection.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.RefMan.domain.OnwardReferralRejection domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	protected final void oncmbRejectedByValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.cmbRejectedBy().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.RefMan.vo.lookups.OnwardReferralRejectedBy existingInstance = (ims.RefMan.vo.lookups.OnwardReferralRejectedBy)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbRejectedByLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.RefMan.vo.lookups.OnwardReferralRejectedBy)
		{
			ims.RefMan.vo.lookups.OnwardReferralRejectedBy instance = (ims.RefMan.vo.lookups.OnwardReferralRejectedBy)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbRejectedByLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.RefMan.vo.lookups.OnwardReferralRejectedBy existingInstance = (ims.RefMan.vo.lookups.OnwardReferralRejectedBy)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.cmbRejectedBy().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbRejectedByLookup()
	{
		this.form.cmbRejectedBy().clear();
		ims.RefMan.vo.lookups.OnwardReferralRejectedByCollection lookupCollection = ims.RefMan.vo.lookups.LookupHelper.getOnwardReferralRejectedBy(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.cmbRejectedBy().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbRejectedByLookupValue(int id)
	{
		ims.RefMan.vo.lookups.OnwardReferralRejectedBy instance = ims.RefMan.vo.lookups.LookupHelper.getOnwardReferralRejectedByInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.cmbRejectedBy().setValue(instance);
	}
	protected final void defaultcmbRejectedByLookupValue()
	{
		this.form.cmbRejectedBy().setValue((ims.RefMan.vo.lookups.OnwardReferralRejectedBy)domain.getLookupService().getDefaultInstance(ims.RefMan.vo.lookups.OnwardReferralRejectedBy.class, engine.getFormName().getID(), ims.RefMan.vo.lookups.OnwardReferralRejectedBy.TYPE_ID));
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.RefMan.domain.OnwardReferralRejection domain;
}
