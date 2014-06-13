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

package ims.core.vo;

/**
 * Linked to core.clinical.PatientProcedure business object (ID: 1003100017).
 */
public class PatientProcedureForSummaryOverviewVo extends ims.core.clinical.vo.PatientProcedureRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientProcedureForSummaryOverviewVo()
	{
	}
	public PatientProcedureForSummaryOverviewVo(Integer id, int version)
	{
		super(id, version);
	}
	public PatientProcedureForSummaryOverviewVo(ims.core.vo.beans.PatientProcedureForSummaryOverviewVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.procedure = bean.getProcedure() == null ? null : bean.getProcedure().buildVo();
		this.proceduredescription = bean.getProcedureDescription();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.PatientProcedureForSummaryOverviewVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.procedure = bean.getProcedure() == null ? null : bean.getProcedure().buildVo(map);
		this.proceduredescription = bean.getProcedureDescription();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.PatientProcedureForSummaryOverviewVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.PatientProcedureForSummaryOverviewVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.PatientProcedureForSummaryOverviewVoBean();
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
		if(fieldName.equals("PROCEDURE"))
			return getProcedure();
		if(fieldName.equals("PROCEDUREDESCRIPTION"))
			return getProcedureDescription();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getProcedureIsNotNull()
	{
		return this.procedure != null;
	}
	public ims.core.vo.ProcedureForSummaryOverviewVo getProcedure()
	{
		return this.procedure;
	}
	public void setProcedure(ims.core.vo.ProcedureForSummaryOverviewVo value)
	{
		this.isValidated = false;
		this.procedure = value;
	}
	public boolean getProcedureDescriptionIsNotNull()
	{
		return this.proceduredescription != null;
	}
	public String getProcedureDescription()
	{
		return this.proceduredescription;
	}
	public static int getProcedureDescriptionMaxLength()
	{
		return 255;
	}
	public void setProcedureDescription(String value)
	{
		this.isValidated = false;
		this.proceduredescription = value;
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
		if(this.proceduredescription == null || this.proceduredescription.length() == 0)
			listOfErrors.add("ProcedureDescription is mandatory");
		else if(this.proceduredescription.length() > 255)
			listOfErrors.add("The length of the field [proceduredescription] in the value object [ims.core.vo.PatientProcedureForSummaryOverviewVo] is too big. It should be less or equal to 255");
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
	
		PatientProcedureForSummaryOverviewVo clone = new PatientProcedureForSummaryOverviewVo(this.id, this.version);
		
		if(this.procedure == null)
			clone.procedure = null;
		else
			clone.procedure = (ims.core.vo.ProcedureForSummaryOverviewVo)this.procedure.clone();
		clone.proceduredescription = this.proceduredescription;
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
		if (!(PatientProcedureForSummaryOverviewVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientProcedureForSummaryOverviewVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((PatientProcedureForSummaryOverviewVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((PatientProcedureForSummaryOverviewVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.procedure != null)
			count++;
		if(this.proceduredescription != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 2;
	}
	protected ims.core.vo.ProcedureForSummaryOverviewVo procedure;
	protected String proceduredescription;
	private boolean isValidated = false;
	private boolean isBusy = false;
}