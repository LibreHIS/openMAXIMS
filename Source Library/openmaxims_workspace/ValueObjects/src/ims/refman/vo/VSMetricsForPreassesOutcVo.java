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
 * Linked to core.vitals.Metrics business object (ID: 1022100011).
 */
public class VSMetricsForPreassesOutcVo extends ims.core.vitals.vo.MetricsRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public VSMetricsForPreassesOutcVo()
	{
	}
	public VSMetricsForPreassesOutcVo(Integer id, int version)
	{
		super(id, version);
	}
	public VSMetricsForPreassesOutcVo(ims.RefMan.vo.beans.VSMetricsForPreassesOutcVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.heightvalue = bean.getHeightValue();
		this.heightestimatedmeasured = bean.getHeightEstimatedMeasured();
		this.weightvalue = bean.getWeightValue();
		this.weightestimatedmeasured = bean.getWeightEstimatedMeasured();
		this.bmi = bean.getBMI();
		this.surfacearea = bean.getSurfaceArea();
		this.necksize = bean.getNeckSize();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.VSMetricsForPreassesOutcVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.heightvalue = bean.getHeightValue();
		this.heightestimatedmeasured = bean.getHeightEstimatedMeasured();
		this.weightvalue = bean.getWeightValue();
		this.weightestimatedmeasured = bean.getWeightEstimatedMeasured();
		this.bmi = bean.getBMI();
		this.surfacearea = bean.getSurfaceArea();
		this.necksize = bean.getNeckSize();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo(map);
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.VSMetricsForPreassesOutcVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.VSMetricsForPreassesOutcVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.VSMetricsForPreassesOutcVoBean();
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
		if(fieldName.equals("HEIGHTVALUE"))
			return getHeightValue();
		if(fieldName.equals("HEIGHTESTIMATEDMEASURED"))
			return getHeightEstimatedMeasured();
		if(fieldName.equals("WEIGHTVALUE"))
			return getWeightValue();
		if(fieldName.equals("WEIGHTESTIMATEDMEASURED"))
			return getWeightEstimatedMeasured();
		if(fieldName.equals("BMI"))
			return getBMI();
		if(fieldName.equals("SURFACEAREA"))
			return getSurfaceArea();
		if(fieldName.equals("NECKSIZE"))
			return getNeckSize();
		if(fieldName.equals("PATIENT"))
			return getPatient();
		if(fieldName.equals("AUTHORINGINFORMATION"))
			return getAuthoringInformation();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getHeightValueIsNotNull()
	{
		return this.heightvalue != null;
	}
	public Float getHeightValue()
	{
		return this.heightvalue;
	}
	public void setHeightValue(Float value)
	{
		this.isValidated = false;
		this.heightvalue = value;
	}
	public boolean getHeightEstimatedMeasuredIsNotNull()
	{
		return this.heightestimatedmeasured != null;
	}
	public Boolean getHeightEstimatedMeasured()
	{
		return this.heightestimatedmeasured;
	}
	public void setHeightEstimatedMeasured(Boolean value)
	{
		this.isValidated = false;
		this.heightestimatedmeasured = value;
	}
	public boolean getWeightValueIsNotNull()
	{
		return this.weightvalue != null;
	}
	public Float getWeightValue()
	{
		return this.weightvalue;
	}
	public void setWeightValue(Float value)
	{
		this.isValidated = false;
		this.weightvalue = value;
	}
	public boolean getWeightEstimatedMeasuredIsNotNull()
	{
		return this.weightestimatedmeasured != null;
	}
	public Boolean getWeightEstimatedMeasured()
	{
		return this.weightestimatedmeasured;
	}
	public void setWeightEstimatedMeasured(Boolean value)
	{
		this.isValidated = false;
		this.weightestimatedmeasured = value;
	}
	public boolean getBMIIsNotNull()
	{
		return this.bmi != null;
	}
	public Float getBMI()
	{
		return this.bmi;
	}
	public void setBMI(Float value)
	{
		this.isValidated = false;
		this.bmi = value;
	}
	public boolean getSurfaceAreaIsNotNull()
	{
		return this.surfacearea != null;
	}
	public Float getSurfaceArea()
	{
		return this.surfacearea;
	}
	public void setSurfaceArea(Float value)
	{
		this.isValidated = false;
		this.surfacearea = value;
	}
	public boolean getNeckSizeIsNotNull()
	{
		return this.necksize != null;
	}
	public Float getNeckSize()
	{
		return this.necksize;
	}
	public void setNeckSize(Float value)
	{
		this.isValidated = false;
		this.necksize = value;
	}
	public boolean getPatientIsNotNull()
	{
		return this.patient != null;
	}
	public ims.core.patient.vo.PatientRefVo getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.patient.vo.PatientRefVo value)
	{
		this.isValidated = false;
		this.patient = value;
	}
	public boolean getAuthoringInformationIsNotNull()
	{
		return this.authoringinformation != null;
	}
	public ims.core.vo.AuthoringInformationVo getAuthoringInformation()
	{
		return this.authoringinformation;
	}
	public void setAuthoringInformation(ims.core.vo.AuthoringInformationVo value)
	{
		this.isValidated = false;
		this.authoringinformation = value;
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
		if(this.authoringinformation != null)
		{
			if(!this.authoringinformation.isValidated())
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
		if(this.patient == null)
			listOfErrors.add("Patient is mandatory");
		if(this.authoringinformation != null)
		{
			String[] listOfOtherErrors = this.authoringinformation.validate();
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
	
		VSMetricsForPreassesOutcVo clone = new VSMetricsForPreassesOutcVo(this.id, this.version);
		
		clone.heightvalue = this.heightvalue;
		clone.heightestimatedmeasured = this.heightestimatedmeasured;
		clone.weightvalue = this.weightvalue;
		clone.weightestimatedmeasured = this.weightestimatedmeasured;
		clone.bmi = this.bmi;
		clone.surfacearea = this.surfacearea;
		clone.necksize = this.necksize;
		clone.patient = this.patient;
		if(this.authoringinformation == null)
			clone.authoringinformation = null;
		else
			clone.authoringinformation = (ims.core.vo.AuthoringInformationVo)this.authoringinformation.clone();
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
		if (!(VSMetricsForPreassesOutcVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A VSMetricsForPreassesOutcVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((VSMetricsForPreassesOutcVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((VSMetricsForPreassesOutcVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.heightvalue != null)
			count++;
		if(this.heightestimatedmeasured != null)
			count++;
		if(this.weightvalue != null)
			count++;
		if(this.weightestimatedmeasured != null)
			count++;
		if(this.bmi != null)
			count++;
		if(this.surfacearea != null)
			count++;
		if(this.necksize != null)
			count++;
		if(this.patient != null)
			count++;
		if(this.authoringinformation != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 9;
	}
	protected Float heightvalue;
	protected Boolean heightestimatedmeasured;
	protected Float weightvalue;
	protected Boolean weightestimatedmeasured;
	protected Float bmi;
	protected Float surfacearea;
	protected Float necksize;
	protected ims.core.patient.vo.PatientRefVo patient;
	protected ims.core.vo.AuthoringInformationVo authoringinformation;
	private boolean isValidated = false;
	private boolean isBusy = false;
}