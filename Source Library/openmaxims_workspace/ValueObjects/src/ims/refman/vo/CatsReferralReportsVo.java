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
 * Linked to RefMan.CatsReferral business object (ID: 1004100035).
 */
public class CatsReferralReportsVo extends ims.RefMan.vo.CatsReferralRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public CatsReferralReportsVo()
	{
	}
	public CatsReferralReportsVo(Integer id, int version)
	{
		super(id, version);
	}
	public CatsReferralReportsVo(ims.RefMan.vo.beans.CatsReferralReportsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.isfinalreportrequired = bean.getIsFinalReportRequired() == null ? null : ims.RefMan.vo.lookups.ReportStatus.buildLookup(bean.getIsFinalReportRequired());
		this.catsreportsentdate = bean.getCATSReportSentDate() == null ? null : bean.getCATSReportSentDate().buildDateTime();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.CatsReferralReportsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.isfinalreportrequired = bean.getIsFinalReportRequired() == null ? null : ims.RefMan.vo.lookups.ReportStatus.buildLookup(bean.getIsFinalReportRequired());
		this.catsreportsentdate = bean.getCATSReportSentDate() == null ? null : bean.getCATSReportSentDate().buildDateTime();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.CatsReferralReportsVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.CatsReferralReportsVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.CatsReferralReportsVoBean();
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
		if(fieldName.equals("ISFINALREPORTREQUIRED"))
			return getIsFinalReportRequired();
		if(fieldName.equals("CATSREPORTSENTDATE"))
			return getCATSReportSentDate();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getIsFinalReportRequiredIsNotNull()
	{
		return this.isfinalreportrequired != null;
	}
	public ims.RefMan.vo.lookups.ReportStatus getIsFinalReportRequired()
	{
		return this.isfinalreportrequired;
	}
	public void setIsFinalReportRequired(ims.RefMan.vo.lookups.ReportStatus value)
	{
		this.isValidated = false;
		this.isfinalreportrequired = value;
	}
	public boolean getCATSReportSentDateIsNotNull()
	{
		return this.catsreportsentdate != null;
	}
	public ims.framework.utils.DateTime getCATSReportSentDate()
	{
		return this.catsreportsentdate;
	}
	public void setCATSReportSentDate(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.catsreportsentdate = value;
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
	
		CatsReferralReportsVo clone = new CatsReferralReportsVo(this.id, this.version);
		
		if(this.isfinalreportrequired == null)
			clone.isfinalreportrequired = null;
		else
			clone.isfinalreportrequired = (ims.RefMan.vo.lookups.ReportStatus)this.isfinalreportrequired.clone();
		if(this.catsreportsentdate == null)
			clone.catsreportsentdate = null;
		else
			clone.catsreportsentdate = (ims.framework.utils.DateTime)this.catsreportsentdate.clone();
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
		if (!(CatsReferralReportsVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A CatsReferralReportsVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((CatsReferralReportsVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((CatsReferralReportsVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.isfinalreportrequired != null)
			count++;
		if(this.catsreportsentdate != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 2;
	}
	protected ims.RefMan.vo.lookups.ReportStatus isfinalreportrequired;
	protected ims.framework.utils.DateTime catsreportsentdate;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
