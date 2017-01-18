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
 * Linked to Scheduling.PendingEmergencyTheatre business object (ID: 1090100024).
 */
public class PendingEmergencyTheatreForBookVo extends ims.scheduling.vo.PendingEmergencyTheatreRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PendingEmergencyTheatreForBookVo()
	{
	}
	public PendingEmergencyTheatreForBookVo(Integer id, int version)
	{
		super(id, version);
	}
	public PendingEmergencyTheatreForBookVo(ims.RefMan.vo.beans.PendingEmergencyTheatreForBookVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.primaryprocedure = bean.getPrimaryProcedure() == null ? null : bean.getPrimaryProcedure().buildVo();
		this.proclaterality = bean.getProcLaterality() == null ? null : ims.core.vo.lookups.LateralityLRB.buildLookup(bean.getProcLaterality());
		this.secondaryprocedure = bean.getSecondaryProcedure() == null ? null : bean.getSecondaryProcedure().buildVo();
		this.secondaryproclaterality = bean.getSecondaryProcLaterality() == null ? null : ims.core.vo.lookups.LateralityLRB.buildLookup(bean.getSecondaryProcLaterality());
		this.surgerytimemins = bean.getSurgeryTimeMins();
		this.theatretype = bean.getTheatreType() == null ? null : ims.scheduling.vo.lookups.TheatreType.buildLookup(bean.getTheatreType());
		this.expectedhospital = bean.getExpectedHospital() == null ? null : bean.getExpectedHospital().buildVo();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.PendingEmergencyTheatreForBookVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.primaryprocedure = bean.getPrimaryProcedure() == null ? null : bean.getPrimaryProcedure().buildVo(map);
		this.proclaterality = bean.getProcLaterality() == null ? null : ims.core.vo.lookups.LateralityLRB.buildLookup(bean.getProcLaterality());
		this.secondaryprocedure = bean.getSecondaryProcedure() == null ? null : bean.getSecondaryProcedure().buildVo(map);
		this.secondaryproclaterality = bean.getSecondaryProcLaterality() == null ? null : ims.core.vo.lookups.LateralityLRB.buildLookup(bean.getSecondaryProcLaterality());
		this.surgerytimemins = bean.getSurgeryTimeMins();
		this.theatretype = bean.getTheatreType() == null ? null : ims.scheduling.vo.lookups.TheatreType.buildLookup(bean.getTheatreType());
		this.expectedhospital = bean.getExpectedHospital() == null ? null : bean.getExpectedHospital().buildVo(map);
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.PendingEmergencyTheatreForBookVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.PendingEmergencyTheatreForBookVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.PendingEmergencyTheatreForBookVoBean();
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
		if(fieldName.equals("PRIMARYPROCEDURE"))
			return getPrimaryProcedure();
		if(fieldName.equals("PROCLATERALITY"))
			return getProcLaterality();
		if(fieldName.equals("SECONDARYPROCEDURE"))
			return getSecondaryProcedure();
		if(fieldName.equals("SECONDARYPROCLATERALITY"))
			return getSecondaryProcLaterality();
		if(fieldName.equals("SURGERYTIMEMINS"))
			return getSurgeryTimeMins();
		if(fieldName.equals("THEATRETYPE"))
			return getTheatreType();
		if(fieldName.equals("EXPECTEDHOSPITAL"))
			return getExpectedHospital();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getPrimaryProcedureIsNotNull()
	{
		return this.primaryprocedure != null;
	}
	public ims.core.vo.ProcedureLiteVo getPrimaryProcedure()
	{
		return this.primaryprocedure;
	}
	public void setPrimaryProcedure(ims.core.vo.ProcedureLiteVo value)
	{
		this.isValidated = false;
		this.primaryprocedure = value;
	}
	public boolean getProcLateralityIsNotNull()
	{
		return this.proclaterality != null;
	}
	public ims.core.vo.lookups.LateralityLRB getProcLaterality()
	{
		return this.proclaterality;
	}
	public void setProcLaterality(ims.core.vo.lookups.LateralityLRB value)
	{
		this.isValidated = false;
		this.proclaterality = value;
	}
	public boolean getSecondaryProcedureIsNotNull()
	{
		return this.secondaryprocedure != null;
	}
	public ims.core.vo.ProcedureLiteVo getSecondaryProcedure()
	{
		return this.secondaryprocedure;
	}
	public void setSecondaryProcedure(ims.core.vo.ProcedureLiteVo value)
	{
		this.isValidated = false;
		this.secondaryprocedure = value;
	}
	public boolean getSecondaryProcLateralityIsNotNull()
	{
		return this.secondaryproclaterality != null;
	}
	public ims.core.vo.lookups.LateralityLRB getSecondaryProcLaterality()
	{
		return this.secondaryproclaterality;
	}
	public void setSecondaryProcLaterality(ims.core.vo.lookups.LateralityLRB value)
	{
		this.isValidated = false;
		this.secondaryproclaterality = value;
	}
	public boolean getSurgeryTimeMinsIsNotNull()
	{
		return this.surgerytimemins != null;
	}
	public Integer getSurgeryTimeMins()
	{
		return this.surgerytimemins;
	}
	public void setSurgeryTimeMins(Integer value)
	{
		this.isValidated = false;
		this.surgerytimemins = value;
	}
	public boolean getTheatreTypeIsNotNull()
	{
		return this.theatretype != null;
	}
	public ims.scheduling.vo.lookups.TheatreType getTheatreType()
	{
		return this.theatretype;
	}
	public void setTheatreType(ims.scheduling.vo.lookups.TheatreType value)
	{
		this.isValidated = false;
		this.theatretype = value;
	}
	public boolean getExpectedHospitalIsNotNull()
	{
		return this.expectedhospital != null;
	}
	public ims.core.vo.LocationLiteVo getExpectedHospital()
	{
		return this.expectedhospital;
	}
	public void setExpectedHospital(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.expectedhospital = value;
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
		if(this.primaryprocedure == null)
			listOfErrors.add("PrimaryProcedure is mandatory");
		if(this.surgerytimemins == null)
			listOfErrors.add("SurgeryTimeMins is mandatory");
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
	
		PendingEmergencyTheatreForBookVo clone = new PendingEmergencyTheatreForBookVo(this.id, this.version);
		
		if(this.primaryprocedure == null)
			clone.primaryprocedure = null;
		else
			clone.primaryprocedure = (ims.core.vo.ProcedureLiteVo)this.primaryprocedure.clone();
		if(this.proclaterality == null)
			clone.proclaterality = null;
		else
			clone.proclaterality = (ims.core.vo.lookups.LateralityLRB)this.proclaterality.clone();
		if(this.secondaryprocedure == null)
			clone.secondaryprocedure = null;
		else
			clone.secondaryprocedure = (ims.core.vo.ProcedureLiteVo)this.secondaryprocedure.clone();
		if(this.secondaryproclaterality == null)
			clone.secondaryproclaterality = null;
		else
			clone.secondaryproclaterality = (ims.core.vo.lookups.LateralityLRB)this.secondaryproclaterality.clone();
		clone.surgerytimemins = this.surgerytimemins;
		if(this.theatretype == null)
			clone.theatretype = null;
		else
			clone.theatretype = (ims.scheduling.vo.lookups.TheatreType)this.theatretype.clone();
		if(this.expectedhospital == null)
			clone.expectedhospital = null;
		else
			clone.expectedhospital = (ims.core.vo.LocationLiteVo)this.expectedhospital.clone();
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
		if (!(PendingEmergencyTheatreForBookVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PendingEmergencyTheatreForBookVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((PendingEmergencyTheatreForBookVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((PendingEmergencyTheatreForBookVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.primaryprocedure != null)
			count++;
		if(this.proclaterality != null)
			count++;
		if(this.secondaryprocedure != null)
			count++;
		if(this.secondaryproclaterality != null)
			count++;
		if(this.surgerytimemins != null)
			count++;
		if(this.theatretype != null)
			count++;
		if(this.expectedhospital != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 7;
	}
	protected ims.core.vo.ProcedureLiteVo primaryprocedure;
	protected ims.core.vo.lookups.LateralityLRB proclaterality;
	protected ims.core.vo.ProcedureLiteVo secondaryprocedure;
	protected ims.core.vo.lookups.LateralityLRB secondaryproclaterality;
	protected Integer surgerytimemins;
	protected ims.scheduling.vo.lookups.TheatreType theatretype;
	protected ims.core.vo.LocationLiteVo expectedhospital;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
