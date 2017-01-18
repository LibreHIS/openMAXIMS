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


public class InpatientClinicalCodingWorklistSearchCriteriaVo extends ims.vo.ValueObject implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public InpatientClinicalCodingWorklistSearchCriteriaVo()
	{
	}
	public InpatientClinicalCodingWorklistSearchCriteriaVo(ims.RefMan.vo.beans.InpatientClinicalCodingWorklistSearchCriteriaVoBean bean)
	{
		this.specialty = bean.getSpecialty() == null ? null : ims.core.vo.lookups.Specialty.buildLookup(bean.getSpecialty());
		this.dischargewards = ims.core.vo.LocationLiteVoCollection.buildFromBeanCollection(bean.getDischargeWards());
		this.datefrom = bean.getDateFrom() == null ? null : bean.getDateFrom().buildDate();
		this.dateto = bean.getDateTo() == null ? null : bean.getDateTo().buildDate();
		// Interface field type not supported.
		this.codingstatuses = ims.vo.LookupInstanceBean.buildLookupInstanceVoCollection(bean.getCodingStatuses());
		this.comments = ims.vo.LookupInstanceBean.buildLookupInstanceVoCollection(bean.getComments());
		this.awaitinghistology = bean.getAwaitingHistology();
		this.hospital = bean.getHospital() == null ? null : bean.getHospital().buildVo();
		this.casenotelocation = ims.core.vo.LocationLiteVoCollection.buildFromBeanCollection(bean.getCaseNoteLocation());
		this.mrnnumbers = bean.getMRNNumbers();
		this.lastcomment = bean.getLastComment();
		this.allcomments = bean.getAllComments();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.InpatientClinicalCodingWorklistSearchCriteriaVoBean bean)
	{
		this.specialty = bean.getSpecialty() == null ? null : ims.core.vo.lookups.Specialty.buildLookup(bean.getSpecialty());
		this.dischargewards = ims.core.vo.LocationLiteVoCollection.buildFromBeanCollection(bean.getDischargeWards());
		this.datefrom = bean.getDateFrom() == null ? null : bean.getDateFrom().buildDate();
		this.dateto = bean.getDateTo() == null ? null : bean.getDateTo().buildDate();
		// Interface field type not supported.
		this.codingstatuses = ims.vo.LookupInstanceBean.buildLookupInstanceVoCollection(bean.getCodingStatuses());
		this.comments = ims.vo.LookupInstanceBean.buildLookupInstanceVoCollection(bean.getComments());
		this.awaitinghistology = bean.getAwaitingHistology();
		this.hospital = bean.getHospital() == null ? null : bean.getHospital().buildVo(map);
		this.casenotelocation = ims.core.vo.LocationLiteVoCollection.buildFromBeanCollection(bean.getCaseNoteLocation());
		this.mrnnumbers = bean.getMRNNumbers();
		this.lastcomment = bean.getLastComment();
		this.allcomments = bean.getAllComments();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.InpatientClinicalCodingWorklistSearchCriteriaVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.InpatientClinicalCodingWorklistSearchCriteriaVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.InpatientClinicalCodingWorklistSearchCriteriaVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
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
	public boolean getDischargeWardsIsNotNull()
	{
		return this.dischargewards != null;
	}
	public ims.core.vo.LocationLiteVoCollection getDischargeWards()
	{
		return this.dischargewards;
	}
	public void setDischargeWards(ims.core.vo.LocationLiteVoCollection value)
	{
		this.isValidated = false;
		this.dischargewards = value;
	}
	public boolean getDateFromIsNotNull()
	{
		return this.datefrom != null;
	}
	public ims.framework.utils.Date getDateFrom()
	{
		return this.datefrom;
	}
	public void setDateFrom(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.datefrom = value;
	}
	public boolean getDateToIsNotNull()
	{
		return this.dateto != null;
	}
	public ims.framework.utils.Date getDateTo()
	{
		return this.dateto;
	}
	public void setDateTo(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.dateto = value;
	}
	public boolean getUserIsNotNull()
	{
		return this.user != null;
	}
	public ims.vo.interfaces.IMos getUser()
	{
		return this.user;
	}
	public void setUser(ims.vo.interfaces.IMos value)
	{
		this.isValidated = false;
		this.user = value;
	}
	public boolean getCodingStatusesIsNotNull()
	{
		return this.codingstatuses != null;
	}
	public ims.vo.LookupInstanceCollection getCodingStatuses()
	{
		return this.codingstatuses;
	}
	public void setCodingStatuses(ims.vo.LookupInstanceCollection value)
	{
		this.isValidated = false;
		this.codingstatuses = value;
	}
	public boolean getCommentsIsNotNull()
	{
		return this.comments != null;
	}
	public ims.vo.LookupInstanceCollection getComments()
	{
		return this.comments;
	}
	public void setComments(ims.vo.LookupInstanceCollection value)
	{
		this.isValidated = false;
		this.comments = value;
	}
	public boolean getAwaitingHistologyIsNotNull()
	{
		return this.awaitinghistology != null;
	}
	public Boolean getAwaitingHistology()
	{
		return this.awaitinghistology;
	}
	public void setAwaitingHistology(Boolean value)
	{
		this.isValidated = false;
		this.awaitinghistology = value;
	}
	public boolean getHospitalIsNotNull()
	{
		return this.hospital != null;
	}
	public ims.core.vo.LocationLiteVo getHospital()
	{
		return this.hospital;
	}
	public void setHospital(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.hospital = value;
	}
	public boolean getCaseNoteLocationIsNotNull()
	{
		return this.casenotelocation != null;
	}
	public ims.core.vo.LocationLiteVoCollection getCaseNoteLocation()
	{
		return this.casenotelocation;
	}
	public void setCaseNoteLocation(ims.core.vo.LocationLiteVoCollection value)
	{
		this.isValidated = false;
		this.casenotelocation = value;
	}
	public boolean getMRNNumbersIsNotNull()
	{
		return this.mrnnumbers != null;
	}
	public String getMRNNumbers()
	{
		return this.mrnnumbers;
	}
	public static int getMRNNumbersMaxLength()
	{
		return 255;
	}
	public void setMRNNumbers(String value)
	{
		this.isValidated = false;
		this.mrnnumbers = value;
	}
	public boolean getLastCommentIsNotNull()
	{
		return this.lastcomment != null;
	}
	public Boolean getLastComment()
	{
		return this.lastcomment;
	}
	public void setLastComment(Boolean value)
	{
		this.isValidated = false;
		this.lastcomment = value;
	}
	public boolean getAllCommentsIsNotNull()
	{
		return this.allcomments != null;
	}
	public Boolean getAllComments()
	{
		return this.allcomments;
	}
	public void setAllComments(Boolean value)
	{
		this.isValidated = false;
		this.allcomments = value;
	}
	public final String getIItemText()
	{
		return toString();
	}
	public final Integer getBoId() 
	{
		return null;
	}
	public final String getBoClassName()
	{
		return null;
	}
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(!(obj instanceof InpatientClinicalCodingWorklistSearchCriteriaVo))
			return false;
		InpatientClinicalCodingWorklistSearchCriteriaVo compareObj = (InpatientClinicalCodingWorklistSearchCriteriaVo)obj;
		if(this.getSpecialty() == null && compareObj.getSpecialty() != null)
			return false;
		if(this.getSpecialty() != null && compareObj.getSpecialty() == null)
			return false;
		if(this.getSpecialty() != null && compareObj.getSpecialty() != null)
			if(!this.getSpecialty().equals(compareObj.getSpecialty()))
				return false;
		if(this.getDateFrom() == null && compareObj.getDateFrom() != null)
			return false;
		if(this.getDateFrom() != null && compareObj.getDateFrom() == null)
			return false;
		if(this.getDateFrom() != null && compareObj.getDateFrom() != null)
			if(!this.getDateFrom().equals(compareObj.getDateFrom()))
				return false;
		if(this.getDateTo() == null && compareObj.getDateTo() != null)
			return false;
		if(this.getDateTo() != null && compareObj.getDateTo() == null)
			return false;
		if(this.getDateTo() != null && compareObj.getDateTo() != null)
			if(!this.getDateTo().equals(compareObj.getDateTo()))
				return false;
		if(this.getDischargeWards() == null && compareObj.getDischargeWards() != null)
			return false;
		if(this.getDischargeWards() != null && compareObj.getDischargeWards() == null)
			return false;
		if(this.getDischargeWards() != null && compareObj.getDischargeWards() != null)
			if(!this.getDischargeWards().equals(compareObj.getDischargeWards()))
				return false;
		if(this.getUser() == null && compareObj.getUser() != null)
			return false;
		if(this.getUser() != null && compareObj.getUser() == null)
			return false;
		if(this.getUser() != null && compareObj.getUser() != null)
			if(!this.getUser().equals(compareObj.getUser()))
				return false;
		if(this.getAwaitingHistology() == null && compareObj.getAwaitingHistology() != null)
			return false;
		if(this.getAwaitingHistology() != null && compareObj.getAwaitingHistology() == null)
			return false;
		if(this.getAwaitingHistology() != null && compareObj.getAwaitingHistology() != null)
			if(!this.getAwaitingHistology().equals(compareObj.getAwaitingHistology()))
				return false;
		if(this.getHospital() == null && compareObj.getHospital() != null)
			return false;
		if(this.getHospital() != null && compareObj.getHospital() == null)
			return false;
		if(this.getHospital() != null && compareObj.getHospital() != null)
			if(!this.getHospital().equals(compareObj.getHospital()))
				return false;
		if(this.getCaseNoteLocation() == null && compareObj.getCaseNoteLocation() != null)
			return false;
		if(this.getCaseNoteLocation() != null && compareObj.getCaseNoteLocation() == null)
			return false;
		if(this.getCaseNoteLocation() != null && compareObj.getCaseNoteLocation() != null)
			return this.getCaseNoteLocation().equals(compareObj.getCaseNoteLocation());
		return super.equals(obj);
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
		if(this.hospital != null)
		{
			if(!this.hospital.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.casenotelocation != null)
		{
			if(!this.casenotelocation.isValidated())
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
		if(this.hospital != null)
		{
			String[] listOfOtherErrors = this.hospital.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.casenotelocation != null)
		{
			String[] listOfOtherErrors = this.casenotelocation.validate();
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
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		InpatientClinicalCodingWorklistSearchCriteriaVo clone = new InpatientClinicalCodingWorklistSearchCriteriaVo();
		
		if(this.specialty == null)
			clone.specialty = null;
		else
			clone.specialty = (ims.core.vo.lookups.Specialty)this.specialty.clone();
		if(this.dischargewards == null)
			clone.dischargewards = null;
		else
			clone.dischargewards = (ims.core.vo.LocationLiteVoCollection)this.dischargewards.clone();
		if(this.datefrom == null)
			clone.datefrom = null;
		else
			clone.datefrom = (ims.framework.utils.Date)this.datefrom.clone();
		if(this.dateto == null)
			clone.dateto = null;
		else
			clone.dateto = (ims.framework.utils.Date)this.dateto.clone();
		clone.user = this.user;
		clone.codingstatuses = this.codingstatuses;
		clone.comments = this.comments;
		clone.awaitinghistology = this.awaitinghistology;
		if(this.hospital == null)
			clone.hospital = null;
		else
			clone.hospital = (ims.core.vo.LocationLiteVo)this.hospital.clone();
		if(this.casenotelocation == null)
			clone.casenotelocation = null;
		else
			clone.casenotelocation = (ims.core.vo.LocationLiteVoCollection)this.casenotelocation.clone();
		clone.mrnnumbers = this.mrnnumbers;
		clone.lastcomment = this.lastcomment;
		clone.allcomments = this.allcomments;
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
		if (!(InpatientClinicalCodingWorklistSearchCriteriaVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A InpatientClinicalCodingWorklistSearchCriteriaVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		InpatientClinicalCodingWorklistSearchCriteriaVo compareObj = (InpatientClinicalCodingWorklistSearchCriteriaVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getSpecialty() == null && compareObj.getSpecialty() != null)
				return -1;
			if(this.getSpecialty() != null && compareObj.getSpecialty() == null)
				return 1;
			if(this.getSpecialty() != null && compareObj.getSpecialty() != null)
				retVal = this.getSpecialty().compareTo(compareObj.getSpecialty());
		}
		if (retVal == 0)
		{
			if(this.getDateFrom() == null && compareObj.getDateFrom() != null)
				return -1;
			if(this.getDateFrom() != null && compareObj.getDateFrom() == null)
				return 1;
			if(this.getDateFrom() != null && compareObj.getDateFrom() != null)
				retVal = this.getDateFrom().compareTo(compareObj.getDateFrom());
		}
		if (retVal == 0)
		{
			if(this.getDateTo() == null && compareObj.getDateTo() != null)
				return -1;
			if(this.getDateTo() != null && compareObj.getDateTo() == null)
				return 1;
			if(this.getDateTo() != null && compareObj.getDateTo() != null)
				retVal = this.getDateTo().compareTo(compareObj.getDateTo());
		}
		if (retVal == 0)
		{
			if(this.getDischargeWards() == null && compareObj.getDischargeWards() != null)
				return -1;
			if(this.getDischargeWards() != null && compareObj.getDischargeWards() == null)
				return 1;
			if(this.getDischargeWards() != null && compareObj.getDischargeWards() != null)
				retVal = this.getDischargeWards().compareTo(compareObj.getDischargeWards());
		}
		if (retVal == 0)
		{
			if(this.getUser() == null && compareObj.getUser() != null)
				return -1;
			if(this.getUser() != null && compareObj.getUser() == null)
				return 1;
			if(this.getUser() != null && compareObj.getUser() != null)
				retVal = this.getUser().compareTo(compareObj.getUser());
		}
		if (retVal == 0)
		{
			if(this.getAwaitingHistology() == null && compareObj.getAwaitingHistology() != null)
				return -1;
			if(this.getAwaitingHistology() != null && compareObj.getAwaitingHistology() == null)
				return 1;
			if(this.getAwaitingHistology() != null && compareObj.getAwaitingHistology() != null)
				retVal = this.getAwaitingHistology().compareTo(compareObj.getAwaitingHistology());
		}
		if (retVal == 0)
		{
			if(this.getHospital() == null && compareObj.getHospital() != null)
				return -1;
			if(this.getHospital() != null && compareObj.getHospital() == null)
				return 1;
			if(this.getHospital() != null && compareObj.getHospital() != null)
				retVal = this.getHospital().compareTo(compareObj.getHospital());
		}
		if (retVal == 0)
		{
			if(this.getCaseNoteLocation() == null && compareObj.getCaseNoteLocation() != null)
				return -1;
			if(this.getCaseNoteLocation() != null && compareObj.getCaseNoteLocation() == null)
				return 1;
			if(this.getCaseNoteLocation() != null && compareObj.getCaseNoteLocation() != null)
				retVal = this.getCaseNoteLocation().compareTo(compareObj.getCaseNoteLocation());
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
		if(this.specialty != null)
			count++;
		if(this.dischargewards != null)
			count++;
		if(this.datefrom != null)
			count++;
		if(this.dateto != null)
			count++;
		if(this.user != null)
			count++;
		if(this.codingstatuses != null)
			count++;
		if(this.comments != null)
			count++;
		if(this.awaitinghistology != null)
			count++;
		if(this.hospital != null)
			count++;
		if(this.casenotelocation != null)
			count++;
		if(this.mrnnumbers != null)
			count++;
		if(this.lastcomment != null)
			count++;
		if(this.allcomments != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 13;
	}
	protected ims.core.vo.lookups.Specialty specialty;
	protected ims.core.vo.LocationLiteVoCollection dischargewards;
	protected ims.framework.utils.Date datefrom;
	protected ims.framework.utils.Date dateto;
	protected ims.vo.interfaces.IMos user;
	protected ims.vo.LookupInstanceCollection codingstatuses;
	protected ims.vo.LookupInstanceCollection comments;
	protected Boolean awaitinghistology;
	protected ims.core.vo.LocationLiteVo hospital;
	protected ims.core.vo.LocationLiteVoCollection casenotelocation;
	protected String mrnnumbers;
	protected Boolean lastcomment;
	protected Boolean allcomments;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
