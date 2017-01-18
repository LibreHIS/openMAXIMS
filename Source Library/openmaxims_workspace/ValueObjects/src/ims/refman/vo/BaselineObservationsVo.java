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
 * Linked to RefMan.NursingNotes business object (ID: 1096100002).
 */
public class BaselineObservationsVo extends ims.RefMan.vo.NursingNotesRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public BaselineObservationsVo()
	{
	}
	public BaselineObservationsVo(Integer id, int version)
	{
		super(id, version);
	}
	public BaselineObservationsVo(ims.RefMan.vo.beans.BaselineObservationsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.catsreferral = bean.getCatsReferral() == null ? null : new ims.RefMan.vo.CatsReferralRefVo(new Integer(bean.getCatsReferral().getId()), bean.getCatsReferral().getVersion());
		this.authoringuser = bean.getAuthoringUser() == null ? null : bean.getAuthoringUser().buildVo();
		this.authoringdatetime = bean.getAuthoringDateTime() == null ? null : bean.getAuthoringDateTime().buildDateTime();
		this.vitalsigns = bean.getVitalSigns() == null ? null : bean.getVitalSigns().buildVo();
		this.smokingstatus = bean.getSmokingStatus() == null ? null : ims.core.vo.lookups.SmokingStatus.buildLookup(bean.getSmokingStatus());
		this.observationnotes = bean.getObservationNotes();
		this.specialneeds = ims.clinical.vo.PatientSpecialNeedVoCollection.buildFromBeanCollection(bean.getSpecialNeeds());
		this.allergies = ims.core.vo.PatientAllergyCollection.buildFromBeanCollection(bean.getAllergies());
		this.advicegiven = bean.getAdviceGiven();
		this.unitsofalcoholconsumedperweek = bean.getUnitsOfAlcoholConsumedPerWeek();
		this.referredtosomkingcessation = bean.getReferredToSomkingCessation();
		if(bean.getComorbidities() != null)
		{
			this.comorbidities = new ims.core.clinical.vo.PatientDiagnosisRefVoCollection();
			for(int comorbidities_i = 0; comorbidities_i < bean.getComorbidities().length; comorbidities_i++)
			{
				this.comorbidities.add(new ims.core.clinical.vo.PatientDiagnosisRefVo(new Integer(bean.getComorbidities()[comorbidities_i].getId()), bean.getComorbidities()[comorbidities_i].getVersion()));
			}
		}
		this.mrsaresult = bean.getMRSAResult() == null ? null : ims.nursing.vo.lookups.MRSAResult.buildLookup(bean.getMRSAResult());
		if(bean.getCurrentMedications() != null)
		{
			this.currentmedications = new ims.core.clinical.vo.PatientMedicationRefVoCollection();
			for(int currentmedications_i = 0; currentmedications_i < bean.getCurrentMedications().length; currentmedications_i++)
			{
				this.currentmedications.add(new ims.core.clinical.vo.PatientMedicationRefVo(new Integer(bean.getCurrentMedications()[currentmedications_i].getId()), bean.getCurrentMedications()[currentmedications_i].getVersion()));
			}
		}
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.BaselineObservationsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.catsreferral = bean.getCatsReferral() == null ? null : new ims.RefMan.vo.CatsReferralRefVo(new Integer(bean.getCatsReferral().getId()), bean.getCatsReferral().getVersion());
		this.authoringuser = bean.getAuthoringUser() == null ? null : bean.getAuthoringUser().buildVo(map);
		this.authoringdatetime = bean.getAuthoringDateTime() == null ? null : bean.getAuthoringDateTime().buildDateTime();
		this.vitalsigns = bean.getVitalSigns() == null ? null : bean.getVitalSigns().buildVo(map);
		this.smokingstatus = bean.getSmokingStatus() == null ? null : ims.core.vo.lookups.SmokingStatus.buildLookup(bean.getSmokingStatus());
		this.observationnotes = bean.getObservationNotes();
		this.specialneeds = ims.clinical.vo.PatientSpecialNeedVoCollection.buildFromBeanCollection(bean.getSpecialNeeds());
		this.allergies = ims.core.vo.PatientAllergyCollection.buildFromBeanCollection(bean.getAllergies());
		this.advicegiven = bean.getAdviceGiven();
		this.unitsofalcoholconsumedperweek = bean.getUnitsOfAlcoholConsumedPerWeek();
		this.referredtosomkingcessation = bean.getReferredToSomkingCessation();
		if(bean.getComorbidities() != null)
		{
			this.comorbidities = new ims.core.clinical.vo.PatientDiagnosisRefVoCollection();
			for(int comorbidities_i = 0; comorbidities_i < bean.getComorbidities().length; comorbidities_i++)
			{
				this.comorbidities.add(new ims.core.clinical.vo.PatientDiagnosisRefVo(new Integer(bean.getComorbidities()[comorbidities_i].getId()), bean.getComorbidities()[comorbidities_i].getVersion()));
			}
		}
		this.mrsaresult = bean.getMRSAResult() == null ? null : ims.nursing.vo.lookups.MRSAResult.buildLookup(bean.getMRSAResult());
		if(bean.getCurrentMedications() != null)
		{
			this.currentmedications = new ims.core.clinical.vo.PatientMedicationRefVoCollection();
			for(int currentmedications_i = 0; currentmedications_i < bean.getCurrentMedications().length; currentmedications_i++)
			{
				this.currentmedications.add(new ims.core.clinical.vo.PatientMedicationRefVo(new Integer(bean.getCurrentMedications()[currentmedications_i].getId()), bean.getCurrentMedications()[currentmedications_i].getVersion()));
			}
		}
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.BaselineObservationsVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.BaselineObservationsVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.BaselineObservationsVoBean();
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
		if(fieldName.equals("CATSREFERRAL"))
			return getCatsReferral();
		if(fieldName.equals("AUTHORINGUSER"))
			return getAuthoringUser();
		if(fieldName.equals("AUTHORINGDATETIME"))
			return getAuthoringDateTime();
		if(fieldName.equals("VITALSIGNS"))
			return getVitalSigns();
		if(fieldName.equals("SMOKINGSTATUS"))
			return getSmokingStatus();
		if(fieldName.equals("OBSERVATIONNOTES"))
			return getObservationNotes();
		if(fieldName.equals("SPECIALNEEDS"))
			return getSpecialNeeds();
		if(fieldName.equals("ALLERGIES"))
			return getAllergies();
		if(fieldName.equals("ADVICEGIVEN"))
			return getAdviceGiven();
		if(fieldName.equals("UNITSOFALCOHOLCONSUMEDPERWEEK"))
			return getUnitsOfAlcoholConsumedPerWeek();
		if(fieldName.equals("REFERREDTOSOMKINGCESSATION"))
			return getReferredToSomkingCessation();
		if(fieldName.equals("COMORBIDITIES"))
			return getComorbidities();
		if(fieldName.equals("MRSARESULT"))
			return getMRSAResult();
		if(fieldName.equals("CURRENTMEDICATIONS"))
			return getCurrentMedications();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getCatsReferralIsNotNull()
	{
		return this.catsreferral != null;
	}
	public ims.RefMan.vo.CatsReferralRefVo getCatsReferral()
	{
		return this.catsreferral;
	}
	public void setCatsReferral(ims.RefMan.vo.CatsReferralRefVo value)
	{
		this.isValidated = false;
		this.catsreferral = value;
	}
	public boolean getAuthoringUserIsNotNull()
	{
		return this.authoringuser != null;
	}
	public ims.core.vo.MemberOfStaffShortVo getAuthoringUser()
	{
		return this.authoringuser;
	}
	public void setAuthoringUser(ims.core.vo.MemberOfStaffShortVo value)
	{
		this.isValidated = false;
		this.authoringuser = value;
	}
	public boolean getAuthoringDateTimeIsNotNull()
	{
		return this.authoringdatetime != null;
	}
	public ims.framework.utils.DateTime getAuthoringDateTime()
	{
		return this.authoringdatetime;
	}
	public void setAuthoringDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.authoringdatetime = value;
	}
	public boolean getVitalSignsIsNotNull()
	{
		return this.vitalsigns != null;
	}
	public ims.core.vo.VitalSignsVo getVitalSigns()
	{
		return this.vitalsigns;
	}
	public void setVitalSigns(ims.core.vo.VitalSignsVo value)
	{
		this.isValidated = false;
		this.vitalsigns = value;
	}
	public boolean getSmokingStatusIsNotNull()
	{
		return this.smokingstatus != null;
	}
	public ims.core.vo.lookups.SmokingStatus getSmokingStatus()
	{
		return this.smokingstatus;
	}
	public void setSmokingStatus(ims.core.vo.lookups.SmokingStatus value)
	{
		this.isValidated = false;
		this.smokingstatus = value;
	}
	public boolean getObservationNotesIsNotNull()
	{
		return this.observationnotes != null;
	}
	public String getObservationNotes()
	{
		return this.observationnotes;
	}
	public static int getObservationNotesMaxLength()
	{
		return 1000;
	}
	public void setObservationNotes(String value)
	{
		this.isValidated = false;
		this.observationnotes = value;
	}
	public boolean getSpecialNeedsIsNotNull()
	{
		return this.specialneeds != null;
	}
	public ims.clinical.vo.PatientSpecialNeedVoCollection getSpecialNeeds()
	{
		return this.specialneeds;
	}
	public void setSpecialNeeds(ims.clinical.vo.PatientSpecialNeedVoCollection value)
	{
		this.isValidated = false;
		this.specialneeds = value;
	}
	public boolean getAllergiesIsNotNull()
	{
		return this.allergies != null;
	}
	public ims.core.vo.PatientAllergyCollection getAllergies()
	{
		return this.allergies;
	}
	public void setAllergies(ims.core.vo.PatientAllergyCollection value)
	{
		this.isValidated = false;
		this.allergies = value;
	}
	public boolean getAdviceGivenIsNotNull()
	{
		return this.advicegiven != null;
	}
	public Boolean getAdviceGiven()
	{
		return this.advicegiven;
	}
	public void setAdviceGiven(Boolean value)
	{
		this.isValidated = false;
		this.advicegiven = value;
	}
	public boolean getUnitsOfAlcoholConsumedPerWeekIsNotNull()
	{
		return this.unitsofalcoholconsumedperweek != null;
	}
	public Float getUnitsOfAlcoholConsumedPerWeek()
	{
		return this.unitsofalcoholconsumedperweek;
	}
	public void setUnitsOfAlcoholConsumedPerWeek(Float value)
	{
		this.isValidated = false;
		this.unitsofalcoholconsumedperweek = value;
	}
	public boolean getReferredToSomkingCessationIsNotNull()
	{
		return this.referredtosomkingcessation != null;
	}
	public Boolean getReferredToSomkingCessation()
	{
		return this.referredtosomkingcessation;
	}
	public void setReferredToSomkingCessation(Boolean value)
	{
		this.isValidated = false;
		this.referredtosomkingcessation = value;
	}
	public boolean getComorbiditiesIsNotNull()
	{
		return this.comorbidities != null;
	}
	public ims.core.clinical.vo.PatientDiagnosisRefVoCollection getComorbidities()
	{
		return this.comorbidities;
	}
	public void setComorbidities(ims.core.clinical.vo.PatientDiagnosisRefVoCollection value)
	{
		this.isValidated = false;
		this.comorbidities = value;
	}
	public boolean getMRSAResultIsNotNull()
	{
		return this.mrsaresult != null;
	}
	public ims.nursing.vo.lookups.MRSAResult getMRSAResult()
	{
		return this.mrsaresult;
	}
	public void setMRSAResult(ims.nursing.vo.lookups.MRSAResult value)
	{
		this.isValidated = false;
		this.mrsaresult = value;
	}
	public boolean getCurrentMedicationsIsNotNull()
	{
		return this.currentmedications != null;
	}
	public ims.core.clinical.vo.PatientMedicationRefVoCollection getCurrentMedications()
	{
		return this.currentmedications;
	}
	public void setCurrentMedications(ims.core.clinical.vo.PatientMedicationRefVoCollection value)
	{
		this.isValidated = false;
		this.currentmedications = value;
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
		if(this.vitalsigns != null)
		{
			if(!this.vitalsigns.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.specialneeds != null)
		{
			if(!this.specialneeds.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.allergies != null)
		{
			if(!this.allergies.isValidated())
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
		if(this.catsreferral == null)
			listOfErrors.add("CatsReferral is mandatory");
		if(this.authoringuser == null)
			listOfErrors.add("AuthoringUser is mandatory");
		if(this.authoringdatetime == null)
			listOfErrors.add("AuthoringDateTime is mandatory");
		if(this.vitalsigns != null)
		{
			String[] listOfOtherErrors = this.vitalsigns.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.observationnotes != null)
			if(this.observationnotes.length() > 1000)
				listOfErrors.add("The length of the field [observationnotes] in the value object [ims.RefMan.vo.BaselineObservationsVo] is too big. It should be less or equal to 1000");
		if(this.specialneeds != null)
		{
			String[] listOfOtherErrors = this.specialneeds.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.allergies != null)
		{
			String[] listOfOtherErrors = this.allergies.validate();
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
	
		BaselineObservationsVo clone = new BaselineObservationsVo(this.id, this.version);
		
		clone.catsreferral = this.catsreferral;
		if(this.authoringuser == null)
			clone.authoringuser = null;
		else
			clone.authoringuser = (ims.core.vo.MemberOfStaffShortVo)this.authoringuser.clone();
		if(this.authoringdatetime == null)
			clone.authoringdatetime = null;
		else
			clone.authoringdatetime = (ims.framework.utils.DateTime)this.authoringdatetime.clone();
		if(this.vitalsigns == null)
			clone.vitalsigns = null;
		else
			clone.vitalsigns = (ims.core.vo.VitalSignsVo)this.vitalsigns.clone();
		if(this.smokingstatus == null)
			clone.smokingstatus = null;
		else
			clone.smokingstatus = (ims.core.vo.lookups.SmokingStatus)this.smokingstatus.clone();
		clone.observationnotes = this.observationnotes;
		if(this.specialneeds == null)
			clone.specialneeds = null;
		else
			clone.specialneeds = (ims.clinical.vo.PatientSpecialNeedVoCollection)this.specialneeds.clone();
		if(this.allergies == null)
			clone.allergies = null;
		else
			clone.allergies = (ims.core.vo.PatientAllergyCollection)this.allergies.clone();
		clone.advicegiven = this.advicegiven;
		clone.unitsofalcoholconsumedperweek = this.unitsofalcoholconsumedperweek;
		clone.referredtosomkingcessation = this.referredtosomkingcessation;
		clone.comorbidities = this.comorbidities;
		if(this.mrsaresult == null)
			clone.mrsaresult = null;
		else
			clone.mrsaresult = (ims.nursing.vo.lookups.MRSAResult)this.mrsaresult.clone();
		clone.currentmedications = this.currentmedications;
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
		if (!(BaselineObservationsVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A BaselineObservationsVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((BaselineObservationsVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((BaselineObservationsVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.catsreferral != null)
			count++;
		if(this.authoringuser != null)
			count++;
		if(this.authoringdatetime != null)
			count++;
		if(this.vitalsigns != null)
			count++;
		if(this.smokingstatus != null)
			count++;
		if(this.observationnotes != null)
			count++;
		if(this.specialneeds != null)
			count++;
		if(this.allergies != null)
			count++;
		if(this.advicegiven != null)
			count++;
		if(this.unitsofalcoholconsumedperweek != null)
			count++;
		if(this.referredtosomkingcessation != null)
			count++;
		if(this.comorbidities != null)
			count++;
		if(this.mrsaresult != null)
			count++;
		if(this.currentmedications != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 14;
	}
	protected ims.RefMan.vo.CatsReferralRefVo catsreferral;
	protected ims.core.vo.MemberOfStaffShortVo authoringuser;
	protected ims.framework.utils.DateTime authoringdatetime;
	protected ims.core.vo.VitalSignsVo vitalsigns;
	protected ims.core.vo.lookups.SmokingStatus smokingstatus;
	protected String observationnotes;
	protected ims.clinical.vo.PatientSpecialNeedVoCollection specialneeds;
	protected ims.core.vo.PatientAllergyCollection allergies;
	protected Boolean advicegiven;
	protected Float unitsofalcoholconsumedperweek;
	protected Boolean referredtosomkingcessation;
	protected ims.core.clinical.vo.PatientDiagnosisRefVoCollection comorbidities;
	protected ims.nursing.vo.lookups.MRSAResult mrsaresult;
	protected ims.core.clinical.vo.PatientMedicationRefVoCollection currentmedications;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
