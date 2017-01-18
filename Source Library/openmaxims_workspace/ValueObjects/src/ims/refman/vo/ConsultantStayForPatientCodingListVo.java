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
 * Linked to core.admin.pas.Consultant Stay business object (ID: 1014100005).
 */
public class ConsultantStayForPatientCodingListVo extends ims.core.admin.pas.vo.ConsultantStayRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public ConsultantStayForPatientCodingListVo()
	{
	}
	public ConsultantStayForPatientCodingListVo(Integer id, int version)
	{
		super(id, version);
	}
	public ConsultantStayForPatientCodingListVo(ims.RefMan.vo.beans.ConsultantStayForPatientCodingListVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.consultant = bean.getConsultant() == null ? null : bean.getConsultant().buildVo();
		this.transferdatetime = bean.getTransferDateTime() == null ? null : bean.getTransferDateTime().buildDateTime();
		this.enddatetime = bean.getEndDateTime() == null ? null : bean.getEndDateTime().buildDateTime();
		this.specialty = bean.getSpecialty() == null ? null : ims.core.vo.lookups.Specialty.buildLookup(bean.getSpecialty());
		this.codingstatus = bean.getCodingStatus() == null ? null : ims.core.vo.lookups.CodingStatus.buildLookup(bean.getCodingStatus());
		this.histologystatus = bean.getHistologyStatus() == null ? null : ims.clinical.vo.lookups.HistologyStatus.buildLookup(bean.getHistologyStatus());
		this.codingcomment = ims.clinical.vo.CodingCommentVoCollection.buildFromBeanCollection(bean.getCodingComment());
		this.service = bean.getService() == null ? null : bean.getService().buildVo();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.ConsultantStayForPatientCodingListVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.consultant = bean.getConsultant() == null ? null : bean.getConsultant().buildVo(map);
		this.transferdatetime = bean.getTransferDateTime() == null ? null : bean.getTransferDateTime().buildDateTime();
		this.enddatetime = bean.getEndDateTime() == null ? null : bean.getEndDateTime().buildDateTime();
		this.specialty = bean.getSpecialty() == null ? null : ims.core.vo.lookups.Specialty.buildLookup(bean.getSpecialty());
		this.codingstatus = bean.getCodingStatus() == null ? null : ims.core.vo.lookups.CodingStatus.buildLookup(bean.getCodingStatus());
		this.histologystatus = bean.getHistologyStatus() == null ? null : ims.clinical.vo.lookups.HistologyStatus.buildLookup(bean.getHistologyStatus());
		this.codingcomment = ims.clinical.vo.CodingCommentVoCollection.buildFromBeanCollection(bean.getCodingComment());
		this.service = bean.getService() == null ? null : bean.getService().buildVo(map);
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.ConsultantStayForPatientCodingListVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.ConsultantStayForPatientCodingListVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.ConsultantStayForPatientCodingListVoBean();
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
		if(fieldName.equals("CONSULTANT"))
			return getConsultant();
		if(fieldName.equals("TRANSFERDATETIME"))
			return getTransferDateTime();
		if(fieldName.equals("ENDDATETIME"))
			return getEndDateTime();
		if(fieldName.equals("SPECIALTY"))
			return getSpecialty();
		if(fieldName.equals("CODINGSTATUS"))
			return getCodingStatus();
		if(fieldName.equals("HISTOLOGYSTATUS"))
			return getHistologyStatus();
		if(fieldName.equals("CODINGCOMMENT"))
			return getCodingComment();
		if(fieldName.equals("SERVICE"))
			return getService();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getConsultantIsNotNull()
	{
		return this.consultant != null;
	}
	public ims.core.vo.MedicLiteVo getConsultant()
	{
		return this.consultant;
	}
	public void setConsultant(ims.core.vo.MedicLiteVo value)
	{
		this.isValidated = false;
		this.consultant = value;
	}
	public boolean getTransferDateTimeIsNotNull()
	{
		return this.transferdatetime != null;
	}
	public ims.framework.utils.DateTime getTransferDateTime()
	{
		return this.transferdatetime;
	}
	public void setTransferDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.transferdatetime = value;
	}
	public boolean getEndDateTimeIsNotNull()
	{
		return this.enddatetime != null;
	}
	public ims.framework.utils.DateTime getEndDateTime()
	{
		return this.enddatetime;
	}
	public void setEndDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.enddatetime = value;
	}
	public boolean getSpecialtyIsNotNull()
	{
		return this.specialty != null;
	}
	public ims.core.vo.lookups.Specialty getSpecialty()
	{
		return this.specialty;
	}
	public void setSpecialty(ims.core.vo.lookups.Specialty value)
	{
		this.isValidated = false;
		this.specialty = value;
	}
	public boolean getCodingStatusIsNotNull()
	{
		return this.codingstatus != null;
	}
	public ims.core.vo.lookups.CodingStatus getCodingStatus()
	{
		return this.codingstatus;
	}
	public void setCodingStatus(ims.core.vo.lookups.CodingStatus value)
	{
		this.isValidated = false;
		this.codingstatus = value;
	}
	public boolean getHistologyStatusIsNotNull()
	{
		return this.histologystatus != null;
	}
	public ims.clinical.vo.lookups.HistologyStatus getHistologyStatus()
	{
		return this.histologystatus;
	}
	public void setHistologyStatus(ims.clinical.vo.lookups.HistologyStatus value)
	{
		this.isValidated = false;
		this.histologystatus = value;
	}
	public boolean getCodingCommentIsNotNull()
	{
		return this.codingcomment != null;
	}
	public ims.clinical.vo.CodingCommentVoCollection getCodingComment()
	{
		return this.codingcomment;
	}
	public void setCodingComment(ims.clinical.vo.CodingCommentVoCollection value)
	{
		this.isValidated = false;
		this.codingcomment = value;
	}
	public boolean getServiceIsNotNull()
	{
		return this.service != null;
	}
	public ims.core.vo.ServiceLiteVo getService()
	{
		return this.service;
	}
	public void setService(ims.core.vo.ServiceLiteVo value)
	{
		this.isValidated = false;
		this.service = value;
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
		if(this.consultant != null)
		{
			if(!this.consultant.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.codingcomment != null)
		{
			if(!this.codingcomment.isValidated())
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
		if(this.consultant != null)
		{
			String[] listOfOtherErrors = this.consultant.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.codingcomment != null)
		{
			String[] listOfOtherErrors = this.codingcomment.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
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
	
		ConsultantStayForPatientCodingListVo clone = new ConsultantStayForPatientCodingListVo(this.id, this.version);
		
		if(this.consultant == null)
			clone.consultant = null;
		else
			clone.consultant = (ims.core.vo.MedicLiteVo)this.consultant.clone();
		if(this.transferdatetime == null)
			clone.transferdatetime = null;
		else
			clone.transferdatetime = (ims.framework.utils.DateTime)this.transferdatetime.clone();
		if(this.enddatetime == null)
			clone.enddatetime = null;
		else
			clone.enddatetime = (ims.framework.utils.DateTime)this.enddatetime.clone();
		if(this.specialty == null)
			clone.specialty = null;
		else
			clone.specialty = (ims.core.vo.lookups.Specialty)this.specialty.clone();
		if(this.codingstatus == null)
			clone.codingstatus = null;
		else
			clone.codingstatus = (ims.core.vo.lookups.CodingStatus)this.codingstatus.clone();
		if(this.histologystatus == null)
			clone.histologystatus = null;
		else
			clone.histologystatus = (ims.clinical.vo.lookups.HistologyStatus)this.histologystatus.clone();
		if(this.codingcomment == null)
			clone.codingcomment = null;
		else
			clone.codingcomment = (ims.clinical.vo.CodingCommentVoCollection)this.codingcomment.clone();
		if(this.service == null)
			clone.service = null;
		else
			clone.service = (ims.core.vo.ServiceLiteVo)this.service.clone();
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
		if (!(ConsultantStayForPatientCodingListVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A ConsultantStayForPatientCodingListVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		ConsultantStayForPatientCodingListVo compareObj = (ConsultantStayForPatientCodingListVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getTransferDateTime() == null && compareObj.getTransferDateTime() != null)
				return -1;
			if(this.getTransferDateTime() != null && compareObj.getTransferDateTime() == null)
				return 1;
			if(this.getTransferDateTime() != null && compareObj.getTransferDateTime() != null)
				retVal = this.getTransferDateTime().compareTo(compareObj.getTransferDateTime());
		}
		if (retVal == 0)
		{
			if(this.getEndDateTime() == null && compareObj.getEndDateTime() != null)
				return -1;
			if(this.getEndDateTime() != null && compareObj.getEndDateTime() == null)
				return 1;
			if(this.getEndDateTime() != null && compareObj.getEndDateTime() != null)
				retVal = this.getEndDateTime().compareTo(compareObj.getEndDateTime());
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
		if(this.consultant != null)
			count++;
		if(this.transferdatetime != null)
			count++;
		if(this.enddatetime != null)
			count++;
		if(this.specialty != null)
			count++;
		if(this.codingstatus != null)
			count++;
		if(this.histologystatus != null)
			count++;
		if(this.codingcomment != null)
			count++;
		if(this.service != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 8;
	}
	protected ims.core.vo.MedicLiteVo consultant;
	protected ims.framework.utils.DateTime transferdatetime;
	protected ims.framework.utils.DateTime enddatetime;
	protected ims.core.vo.lookups.Specialty specialty;
	protected ims.core.vo.lookups.CodingStatus codingstatus;
	protected ims.clinical.vo.lookups.HistologyStatus histologystatus;
	protected ims.clinical.vo.CodingCommentVoCollection codingcomment;
	protected ims.core.vo.ServiceLiteVo service;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
