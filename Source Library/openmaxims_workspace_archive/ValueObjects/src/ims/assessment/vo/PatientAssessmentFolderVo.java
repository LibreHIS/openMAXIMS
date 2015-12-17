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

package ims.assessment.vo;

/**
 * Linked to Assessment.Configuration.PatientAssessmentFolder business object (ID: 1083100006).
 */
public class PatientAssessmentFolderVo extends ims.assessment.configuration.vo.PatientAssessmentFolderRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientAssessmentFolderVo()
	{
	}
	public PatientAssessmentFolderVo(Integer id, int version)
	{
		super(id, version);
	}
	public PatientAssessmentFolderVo(ims.assessment.vo.beans.PatientAssessmentFolderVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.foldername = bean.getFolderName();
		this.patientassessments = ims.assessment.vo.UserAssessmentShortVoCollection.buildFromBeanCollection(bean.getPatientAssessments());
		this.graphicassessments = ims.assessment.vo.GraphicAssessmentShortVoCollection.buildFromBeanCollection(bean.getGraphicAssessments());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.assessment.vo.beans.PatientAssessmentFolderVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.foldername = bean.getFolderName();
		this.patientassessments = ims.assessment.vo.UserAssessmentShortVoCollection.buildFromBeanCollection(bean.getPatientAssessments());
		this.graphicassessments = ims.assessment.vo.GraphicAssessmentShortVoCollection.buildFromBeanCollection(bean.getGraphicAssessments());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.assessment.vo.beans.PatientAssessmentFolderVoBean bean = null;
		if(map != null)
			bean = (ims.assessment.vo.beans.PatientAssessmentFolderVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.assessment.vo.beans.PatientAssessmentFolderVoBean();
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
		if(fieldName.equals("FOLDERNAME"))
			return getFolderName();
		if(fieldName.equals("PATIENTASSESSMENTS"))
			return getPatientAssessments();
		if(fieldName.equals("GRAPHICASSESSMENTS"))
			return getGraphicAssessments();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getFolderNameIsNotNull()
	{
		return this.foldername != null;
	}
	public String getFolderName()
	{
		return this.foldername;
	}
	public static int getFolderNameMaxLength()
	{
		return 255;
	}
	public void setFolderName(String value)
	{
		this.isValidated = false;
		this.foldername = value;
	}
	public boolean getPatientAssessmentsIsNotNull()
	{
		return this.patientassessments != null;
	}
	public ims.assessment.vo.UserAssessmentShortVoCollection getPatientAssessments()
	{
		return this.patientassessments;
	}
	public void setPatientAssessments(ims.assessment.vo.UserAssessmentShortVoCollection value)
	{
		this.isValidated = false;
		this.patientassessments = value;
	}
	public boolean getGraphicAssessmentsIsNotNull()
	{
		return this.graphicassessments != null;
	}
	public ims.assessment.vo.GraphicAssessmentShortVoCollection getGraphicAssessments()
	{
		return this.graphicassessments;
	}
	public void setGraphicAssessments(ims.assessment.vo.GraphicAssessmentShortVoCollection value)
	{
		this.isValidated = false;
		this.graphicassessments = value;
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
		if(this.patientassessments != null)
		{
			if(!this.patientassessments.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.graphicassessments != null)
		{
			if(!this.graphicassessments.isValidated())
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
		if(this.foldername != null)
			if(this.foldername.length() > 255)
				listOfErrors.add("The length of the field [foldername] in the value object [ims.assessment.vo.PatientAssessmentFolderVo] is too big. It should be less or equal to 255");
		if(this.patientassessments != null)
		{
			String[] listOfOtherErrors = this.patientassessments.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.graphicassessments != null)
		{
			String[] listOfOtherErrors = this.graphicassessments.validate();
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
	
		PatientAssessmentFolderVo clone = new PatientAssessmentFolderVo(this.id, this.version);
		
		clone.foldername = this.foldername;
		if(this.patientassessments == null)
			clone.patientassessments = null;
		else
			clone.patientassessments = (ims.assessment.vo.UserAssessmentShortVoCollection)this.patientassessments.clone();
		if(this.graphicassessments == null)
			clone.graphicassessments = null;
		else
			clone.graphicassessments = (ims.assessment.vo.GraphicAssessmentShortVoCollection)this.graphicassessments.clone();
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
		if (!(PatientAssessmentFolderVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientAssessmentFolderVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((PatientAssessmentFolderVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((PatientAssessmentFolderVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.foldername != null)
			count++;
		if(this.patientassessments != null)
			count++;
		if(this.graphicassessments != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 3;
	}
	protected String foldername;
	protected ims.assessment.vo.UserAssessmentShortVoCollection patientassessments;
	protected ims.assessment.vo.GraphicAssessmentShortVoCollection graphicassessments;
	private boolean isValidated = false;
	private boolean isBusy = false;
}