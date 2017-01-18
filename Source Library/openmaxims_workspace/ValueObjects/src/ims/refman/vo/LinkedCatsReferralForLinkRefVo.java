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

package ims.RefMan.vo;

/**
 * Linked to RefMan.LinkedCatsReferral business object (ID: 1096100076).
 */
public class LinkedCatsReferralForLinkRefVo extends ims.RefMan.vo.LinkedCatsReferralRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public LinkedCatsReferralForLinkRefVo()
	{
	}
	public LinkedCatsReferralForLinkRefVo(Integer id, int version)
	{
		super(id, version);
	}
	public LinkedCatsReferralForLinkRefVo(ims.RefMan.vo.beans.LinkedCatsReferralForLinkRefVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.referral = bean.getReferral() == null ? null : bean.getReferral().buildVo();
		this.referralrelationtype = bean.getReferralRelationType() == null ? null : ims.RefMan.vo.lookups.ReferralRelationType.buildLookup(bean.getReferralRelationType());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.LinkedCatsReferralForLinkRefVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.referral = bean.getReferral() == null ? null : bean.getReferral().buildVo(map);
		this.referralrelationtype = bean.getReferralRelationType() == null ? null : ims.RefMan.vo.lookups.ReferralRelationType.buildLookup(bean.getReferralRelationType());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.LinkedCatsReferralForLinkRefVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.LinkedCatsReferralForLinkRefVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.LinkedCatsReferralForLinkRefVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
	}
	public Object getFieldValueByFieldName(String fieldName)
	{
		if(fieldName == null)
			throw new ims.framework.exceptions.CodingRuntimeException("Invalid field name");
		fieldName = fieldName.toUpperCase();
		if(fieldName.equals("REFERRAL"))
			return getReferral();
		if(fieldName.equals("REFERRALRELATIONTYPE"))
			return getReferralRelationType();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getReferralIsNotNull()
	{
		return this.referral != null;
	}
	public ims.RefMan.vo.CatsReferralForLinkRefVo getReferral()
	{
		return this.referral;
	}
	public void setReferral(ims.RefMan.vo.CatsReferralForLinkRefVo value)
	{
		this.isValidated = false;
		this.referral = value;
	}
	public boolean getReferralRelationTypeIsNotNull()
	{
		return this.referralrelationtype != null;
	}
	public ims.RefMan.vo.lookups.ReferralRelationType getReferralRelationType()
	{
		return this.referralrelationtype;
	}
	public void setReferralRelationType(ims.RefMan.vo.lookups.ReferralRelationType value)
	{
		this.isValidated = false;
		this.referralrelationtype = value;
	}
	public boolean isValidated()
	{
		if(this.isBusy)
			return true;
		this.isBusy = true;
	
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		if(this.referral != null)
		{
			if(!this.referral.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		this.isBusy = false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(this.isBusy)
			return null;
		this.isBusy = true;
	
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		if(this.referral == null)
			listOfErrors.add("Referral is mandatory");
		if(this.referral != null)
		{
			String[] listOfOtherErrors = this.referral.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.referralrelationtype == null)
			listOfErrors.add("ReferralRelationType is mandatory");
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		this.isBusy = false;
		this.isValidated = false;
		return result;
	}
	public void clearIDAndVersion()
	{
		this.id = null;
		this.version = 0;
	}
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		LinkedCatsReferralForLinkRefVo clone = new LinkedCatsReferralForLinkRefVo(this.id, this.version);
		
		if(this.referral == null)
			clone.referral = null;
		else
			clone.referral = (ims.RefMan.vo.CatsReferralForLinkRefVo)this.referral.clone();
		if(this.referralrelationtype == null)
			clone.referralrelationtype = null;
		else
			clone.referralrelationtype = (ims.RefMan.vo.lookups.ReferralRelationType)this.referralrelationtype.clone();
		clone.isValidated = this.isValidated;
		
		this.isBusy = false;
		return clone;
	}
	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if(caseInsensitive); // this is to avoid eclipse warning only.
		if (!(LinkedCatsReferralForLinkRefVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A LinkedCatsReferralForLinkRefVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((LinkedCatsReferralForLinkRefVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((LinkedCatsReferralForLinkRefVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.referral != null)
			count++;
		if(this.referralrelationtype != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 2;
	}
	protected ims.RefMan.vo.CatsReferralForLinkRefVo referral;
	protected ims.RefMan.vo.lookups.ReferralRelationType referralrelationtype;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
