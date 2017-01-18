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
 * Linked to RefMan.ReferralUrgencyUpdates business object (ID: 1096100080).
 */
public class ReferralUrgencyUpdatesVo extends ims.RefMan.vo.ReferralUrgencyUpdatesRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public ReferralUrgencyUpdatesVo()
	{
	}
	public ReferralUrgencyUpdatesVo(Integer id, int version)
	{
		super(id, version);
	}
	public ReferralUrgencyUpdatesVo(ims.RefMan.vo.beans.ReferralUrgencyUpdatesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.urgency = bean.getUrgency() == null ? null : ims.RefMan.vo.lookups.ReferralUrgency.buildLookup(bean.getUrgency());
		this.recordinguser = bean.getRecordingUser() == null ? null : bean.getRecordingUser().buildVo();
		this.recordingdatetime = bean.getRecordingDateTime() == null ? null : bean.getRecordingDateTime().buildDateTime();
		this.reason = bean.getReason();
		this.reasonforchangeurgency = bean.getReasonForChangeUrgency() == null ? null : ims.RefMan.vo.lookups.ReasonForChangeUrgency.buildLookup(bean.getReasonForChangeUrgency());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.ReferralUrgencyUpdatesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.urgency = bean.getUrgency() == null ? null : ims.RefMan.vo.lookups.ReferralUrgency.buildLookup(bean.getUrgency());
		this.recordinguser = bean.getRecordingUser() == null ? null : bean.getRecordingUser().buildVo(map);
		this.recordingdatetime = bean.getRecordingDateTime() == null ? null : bean.getRecordingDateTime().buildDateTime();
		this.reason = bean.getReason();
		this.reasonforchangeurgency = bean.getReasonForChangeUrgency() == null ? null : ims.RefMan.vo.lookups.ReasonForChangeUrgency.buildLookup(bean.getReasonForChangeUrgency());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.ReferralUrgencyUpdatesVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.ReferralUrgencyUpdatesVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.ReferralUrgencyUpdatesVoBean();
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
		if(fieldName.equals("URGENCY"))
			return getUrgency();
		if(fieldName.equals("RECORDINGUSER"))
			return getRecordingUser();
		if(fieldName.equals("RECORDINGDATETIME"))
			return getRecordingDateTime();
		if(fieldName.equals("REASON"))
			return getReason();
		if(fieldName.equals("REASONFORCHANGEURGENCY"))
			return getReasonForChangeUrgency();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getUrgencyIsNotNull()
	{
		return this.urgency != null;
	}
	public ims.RefMan.vo.lookups.ReferralUrgency getUrgency()
	{
		return this.urgency;
	}
	public void setUrgency(ims.RefMan.vo.lookups.ReferralUrgency value)
	{
		this.isValidated = false;
		this.urgency = value;
	}
	public boolean getRecordingUserIsNotNull()
	{
		return this.recordinguser != null;
	}
	public ims.core.vo.MemberOfStaffLiteVo getRecordingUser()
	{
		return this.recordinguser;
	}
	public void setRecordingUser(ims.core.vo.MemberOfStaffLiteVo value)
	{
		this.isValidated = false;
		this.recordinguser = value;
	}
	public boolean getRecordingDateTimeIsNotNull()
	{
		return this.recordingdatetime != null;
	}
	public ims.framework.utils.DateTime getRecordingDateTime()
	{
		return this.recordingdatetime;
	}
	public void setRecordingDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.recordingdatetime = value;
	}
	public boolean getReasonIsNotNull()
	{
		return this.reason != null;
	}
	public String getReason()
	{
		return this.reason;
	}
	public static int getReasonMaxLength()
	{
		return 500;
	}
	public void setReason(String value)
	{
		this.isValidated = false;
		this.reason = value;
	}
	public boolean getReasonForChangeUrgencyIsNotNull()
	{
		return this.reasonforchangeurgency != null;
	}
	public ims.RefMan.vo.lookups.ReasonForChangeUrgency getReasonForChangeUrgency()
	{
		return this.reasonforchangeurgency;
	}
	public void setReasonForChangeUrgency(ims.RefMan.vo.lookups.ReasonForChangeUrgency value)
	{
		this.isValidated = false;
		this.reasonforchangeurgency = value;
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
		if(this.urgency == null)
			listOfErrors.add("Urgency is mandatory");
		if(this.recordingdatetime == null)
			listOfErrors.add("RecordingDateTime is mandatory");
		if(this.reason != null)
			if(this.reason.length() > 500)
				listOfErrors.add("The length of the field [reason] in the value object [ims.RefMan.vo.ReferralUrgencyUpdatesVo] is too big. It should be less or equal to 500");
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
	
		ReferralUrgencyUpdatesVo clone = new ReferralUrgencyUpdatesVo(this.id, this.version);
		
		if(this.urgency == null)
			clone.urgency = null;
		else
			clone.urgency = (ims.RefMan.vo.lookups.ReferralUrgency)this.urgency.clone();
		if(this.recordinguser == null)
			clone.recordinguser = null;
		else
			clone.recordinguser = (ims.core.vo.MemberOfStaffLiteVo)this.recordinguser.clone();
		if(this.recordingdatetime == null)
			clone.recordingdatetime = null;
		else
			clone.recordingdatetime = (ims.framework.utils.DateTime)this.recordingdatetime.clone();
		clone.reason = this.reason;
		if(this.reasonforchangeurgency == null)
			clone.reasonforchangeurgency = null;
		else
			clone.reasonforchangeurgency = (ims.RefMan.vo.lookups.ReasonForChangeUrgency)this.reasonforchangeurgency.clone();
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
		if (!(ReferralUrgencyUpdatesVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A ReferralUrgencyUpdatesVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		ReferralUrgencyUpdatesVo compareObj = (ReferralUrgencyUpdatesVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getRecordingDateTime() == null && compareObj.getRecordingDateTime() != null)
				return -1;
			if(this.getRecordingDateTime() != null && compareObj.getRecordingDateTime() == null)
				return 1;
			if(this.getRecordingDateTime() != null && compareObj.getRecordingDateTime() != null)
				retVal = this.getRecordingDateTime().compareTo(compareObj.getRecordingDateTime());
		}
		return retVal;
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.urgency != null)
			count++;
		if(this.recordinguser != null)
			count++;
		if(this.recordingdatetime != null)
			count++;
		if(this.reason != null)
			count++;
		if(this.reasonforchangeurgency != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 5;
	}
	protected ims.RefMan.vo.lookups.ReferralUrgency urgency;
	protected ims.core.vo.MemberOfStaffLiteVo recordinguser;
	protected ims.framework.utils.DateTime recordingdatetime;
	protected String reason;
	protected ims.RefMan.vo.lookups.ReasonForChangeUrgency reasonforchangeurgency;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
