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
 * Linked to RefMan.TCIForPatientElectiveList business object (ID: 1096100064).
 */
public class TCIForPatientElectivetListWLVo extends ims.RefMan.vo.TCIForPatientElectiveListRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public TCIForPatientElectivetListWLVo()
	{
	}
	public TCIForPatientElectivetListWLVo(Integer id, int version)
	{
		super(id, version);
	}
	public TCIForPatientElectivetListWLVo(ims.RefMan.vo.beans.TCIForPatientElectivetListWLVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.tcidate = bean.getTCIDate() == null ? null : bean.getTCIDate().buildDate();
		this.tcitime = bean.getTCITime() == null ? null : bean.getTCITime().buildTime();
		this.tcioffermethod = bean.getTCIOfferMethod() == null ? null : ims.core.vo.lookups.TCIOfferMethod.buildLookup(bean.getTCIOfferMethod());
		this.datetcioffered = bean.getDateTCIOffered() == null ? null : bean.getDateTCIOffered().buildDate();
		this.datetciaccepted = bean.getDateTCIAccepted() == null ? null : bean.getDateTCIAccepted().buildDate();
		this.comments = bean.getComments();
		this.rttbreachreason = bean.getRTTBreachReason() == null ? null : ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason.buildLookup(bean.getRTTBreachReason());
		this.planningelective = bean.getPlanningElective() == null ? null : ims.RefMan.vo.lookups.PlanningElective.buildLookup(bean.getPlanningElective());
		this.tcihospital = bean.getTCIHospital() == null ? null : bean.getTCIHospital().buildVo();
		this.tciward = bean.getTCIWard() == null ? null : bean.getTCIWard().buildVo();
		this.tcibed = bean.getTCIBed();
		this.tciconsultant = bean.getTCIConsultant() == null ? null : new ims.core.resource.people.vo.HcpRefVo(new Integer(bean.getTCIConsultant().getId()), bean.getTCIConsultant().getVersion());
		this.isactive = bean.getIsActive();
		this.bedmanagercomment = bean.getBedManagerComment();
		this.day28breachreason = bean.getDay28BreachReason() == null ? null : ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason.buildLookup(bean.getDay28BreachReason());
		this.day28breachcomment = bean.getDay28BreachComment();
		this.rttbreachcomment = bean.getRTTBreachComment();
		this.plannedtcidate = bean.getPlannedTCIDate() == null ? null : bean.getPlannedTCIDate().buildDate();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.TCIForPatientElectivetListWLVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.tcidate = bean.getTCIDate() == null ? null : bean.getTCIDate().buildDate();
		this.tcitime = bean.getTCITime() == null ? null : bean.getTCITime().buildTime();
		this.tcioffermethod = bean.getTCIOfferMethod() == null ? null : ims.core.vo.lookups.TCIOfferMethod.buildLookup(bean.getTCIOfferMethod());
		this.datetcioffered = bean.getDateTCIOffered() == null ? null : bean.getDateTCIOffered().buildDate();
		this.datetciaccepted = bean.getDateTCIAccepted() == null ? null : bean.getDateTCIAccepted().buildDate();
		this.comments = bean.getComments();
		this.rttbreachreason = bean.getRTTBreachReason() == null ? null : ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason.buildLookup(bean.getRTTBreachReason());
		this.planningelective = bean.getPlanningElective() == null ? null : ims.RefMan.vo.lookups.PlanningElective.buildLookup(bean.getPlanningElective());
		this.tcihospital = bean.getTCIHospital() == null ? null : bean.getTCIHospital().buildVo(map);
		this.tciward = bean.getTCIWard() == null ? null : bean.getTCIWard().buildVo(map);
		this.tcibed = bean.getTCIBed();
		this.tciconsultant = bean.getTCIConsultant() == null ? null : new ims.core.resource.people.vo.HcpRefVo(new Integer(bean.getTCIConsultant().getId()), bean.getTCIConsultant().getVersion());
		this.isactive = bean.getIsActive();
		this.bedmanagercomment = bean.getBedManagerComment();
		this.day28breachreason = bean.getDay28BreachReason() == null ? null : ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason.buildLookup(bean.getDay28BreachReason());
		this.day28breachcomment = bean.getDay28BreachComment();
		this.rttbreachcomment = bean.getRTTBreachComment();
		this.plannedtcidate = bean.getPlannedTCIDate() == null ? null : bean.getPlannedTCIDate().buildDate();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.TCIForPatientElectivetListWLVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.TCIForPatientElectivetListWLVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.TCIForPatientElectivetListWLVoBean();
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
		if(fieldName.equals("TCIDATE"))
			return getTCIDate();
		if(fieldName.equals("TCITIME"))
			return getTCITime();
		if(fieldName.equals("TCIOFFERMETHOD"))
			return getTCIOfferMethod();
		if(fieldName.equals("DATETCIOFFERED"))
			return getDateTCIOffered();
		if(fieldName.equals("DATETCIACCEPTED"))
			return getDateTCIAccepted();
		if(fieldName.equals("COMMENTS"))
			return getComments();
		if(fieldName.equals("RTTBREACHREASON"))
			return getRTTBreachReason();
		if(fieldName.equals("PLANNINGELECTIVE"))
			return getPlanningElective();
		if(fieldName.equals("TCIHOSPITAL"))
			return getTCIHospital();
		if(fieldName.equals("TCIWARD"))
			return getTCIWard();
		if(fieldName.equals("TCIBED"))
			return getTCIBed();
		if(fieldName.equals("TCICONSULTANT"))
			return getTCIConsultant();
		if(fieldName.equals("ISACTIVE"))
			return getIsActive();
		if(fieldName.equals("BEDMANAGERCOMMENT"))
			return getBedManagerComment();
		if(fieldName.equals("DAY28BREACHREASON"))
			return getDay28BreachReason();
		if(fieldName.equals("DAY28BREACHCOMMENT"))
			return getDay28BreachComment();
		if(fieldName.equals("RTTBREACHCOMMENT"))
			return getRTTBreachComment();
		if(fieldName.equals("PLANNEDTCIDATE"))
			return getPlannedTCIDate();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getTCIDateIsNotNull()
	{
		return this.tcidate != null;
	}
	public ims.framework.utils.Date getTCIDate()
	{
		return this.tcidate;
	}
	public void setTCIDate(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.tcidate = value;
	}
	public boolean getTCITimeIsNotNull()
	{
		return this.tcitime != null;
	}
	public ims.framework.utils.Time getTCITime()
	{
		return this.tcitime;
	}
	public void setTCITime(ims.framework.utils.Time value)
	{
		this.isValidated = false;
		this.tcitime = value;
	}
	public boolean getTCIOfferMethodIsNotNull()
	{
		return this.tcioffermethod != null;
	}
	public ims.core.vo.lookups.TCIOfferMethod getTCIOfferMethod()
	{
		return this.tcioffermethod;
	}
	public void setTCIOfferMethod(ims.core.vo.lookups.TCIOfferMethod value)
	{
		this.isValidated = false;
		this.tcioffermethod = value;
	}
	public boolean getDateTCIOfferedIsNotNull()
	{
		return this.datetcioffered != null;
	}
	public ims.framework.utils.Date getDateTCIOffered()
	{
		return this.datetcioffered;
	}
	public void setDateTCIOffered(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.datetcioffered = value;
	}
	public boolean getDateTCIAcceptedIsNotNull()
	{
		return this.datetciaccepted != null;
	}
	public ims.framework.utils.Date getDateTCIAccepted()
	{
		return this.datetciaccepted;
	}
	public void setDateTCIAccepted(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.datetciaccepted = value;
	}
	public boolean getCommentsIsNotNull()
	{
		return this.comments != null;
	}
	public String getComments()
	{
		return this.comments;
	}
	public static int getCommentsMaxLength()
	{
		return 1000;
	}
	public void setComments(String value)
	{
		this.isValidated = false;
		this.comments = value;
	}
	public boolean getRTTBreachReasonIsNotNull()
	{
		return this.rttbreachreason != null;
	}
	public ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason getRTTBreachReason()
	{
		return this.rttbreachreason;
	}
	public void setRTTBreachReason(ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason value)
	{
		this.isValidated = false;
		this.rttbreachreason = value;
	}
	public boolean getPlanningElectiveIsNotNull()
	{
		return this.planningelective != null;
	}
	public ims.RefMan.vo.lookups.PlanningElective getPlanningElective()
	{
		return this.planningelective;
	}
	public void setPlanningElective(ims.RefMan.vo.lookups.PlanningElective value)
	{
		this.isValidated = false;
		this.planningelective = value;
	}
	public boolean getTCIHospitalIsNotNull()
	{
		return this.tcihospital != null;
	}
	public ims.core.vo.LocationLiteVo getTCIHospital()
	{
		return this.tcihospital;
	}
	public void setTCIHospital(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.tcihospital = value;
	}
	public boolean getTCIWardIsNotNull()
	{
		return this.tciward != null;
	}
	public ims.core.vo.LocationLiteVo getTCIWard()
	{
		return this.tciward;
	}
	public void setTCIWard(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.tciward = value;
	}
	public boolean getTCIBedIsNotNull()
	{
		return this.tcibed != null;
	}
	public String getTCIBed()
	{
		return this.tcibed;
	}
	public static int getTCIBedMaxLength()
	{
		return 255;
	}
	public void setTCIBed(String value)
	{
		this.isValidated = false;
		this.tcibed = value;
	}
	public boolean getTCIConsultantIsNotNull()
	{
		return this.tciconsultant != null;
	}
	public ims.core.resource.people.vo.HcpRefVo getTCIConsultant()
	{
		return this.tciconsultant;
	}
	public void setTCIConsultant(ims.core.resource.people.vo.HcpRefVo value)
	{
		this.isValidated = false;
		this.tciconsultant = value;
	}
	public boolean getIsActiveIsNotNull()
	{
		return this.isactive != null;
	}
	public Boolean getIsActive()
	{
		return this.isactive;
	}
	public void setIsActive(Boolean value)
	{
		this.isValidated = false;
		this.isactive = value;
	}
	public boolean getBedManagerCommentIsNotNull()
	{
		return this.bedmanagercomment != null;
	}
	public String getBedManagerComment()
	{
		return this.bedmanagercomment;
	}
	public static int getBedManagerCommentMaxLength()
	{
		return 1000;
	}
	public void setBedManagerComment(String value)
	{
		this.isValidated = false;
		this.bedmanagercomment = value;
	}
	public boolean getDay28BreachReasonIsNotNull()
	{
		return this.day28breachreason != null;
	}
	public ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason getDay28BreachReason()
	{
		return this.day28breachreason;
	}
	public void setDay28BreachReason(ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason value)
	{
		this.isValidated = false;
		this.day28breachreason = value;
	}
	public boolean getDay28BreachCommentIsNotNull()
	{
		return this.day28breachcomment != null;
	}
	public String getDay28BreachComment()
	{
		return this.day28breachcomment;
	}
	public static int getDay28BreachCommentMaxLength()
	{
		return 255;
	}
	public void setDay28BreachComment(String value)
	{
		this.isValidated = false;
		this.day28breachcomment = value;
	}
	public boolean getRTTBreachCommentIsNotNull()
	{
		return this.rttbreachcomment != null;
	}
	public String getRTTBreachComment()
	{
		return this.rttbreachcomment;
	}
	public static int getRTTBreachCommentMaxLength()
	{
		return 255;
	}
	public void setRTTBreachComment(String value)
	{
		this.isValidated = false;
		this.rttbreachcomment = value;
	}
	public boolean getPlannedTCIDateIsNotNull()
	{
		return this.plannedtcidate != null;
	}
	public ims.framework.utils.Date getPlannedTCIDate()
	{
		return this.plannedtcidate;
	}
	public void setPlannedTCIDate(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.plannedtcidate = value;
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
		if(this.tcihospital != null)
		{
			if(!this.tcihospital.isValidated())
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
		if(this.comments != null)
			if(this.comments.length() > 1000)
				listOfErrors.add("The length of the field [comments] in the value object [ims.RefMan.vo.TCIForPatientElectivetListWLVo] is too big. It should be less or equal to 1000");
		if(this.tcihospital != null)
		{
			String[] listOfOtherErrors = this.tcihospital.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.tcibed != null)
			if(this.tcibed.length() > 255)
				listOfErrors.add("The length of the field [tcibed] in the value object [ims.RefMan.vo.TCIForPatientElectivetListWLVo] is too big. It should be less or equal to 255");
		if(this.isactive == null)
			listOfErrors.add("IsActive is mandatory");
		if(this.bedmanagercomment != null)
			if(this.bedmanagercomment.length() > 1000)
				listOfErrors.add("The length of the field [bedmanagercomment] in the value object [ims.RefMan.vo.TCIForPatientElectivetListWLVo] is too big. It should be less or equal to 1000");
		if(this.day28breachcomment != null)
			if(this.day28breachcomment.length() > 255)
				listOfErrors.add("The length of the field [day28breachcomment] in the value object [ims.RefMan.vo.TCIForPatientElectivetListWLVo] is too big. It should be less or equal to 255");
		if(this.rttbreachcomment != null)
			if(this.rttbreachcomment.length() > 255)
				listOfErrors.add("The length of the field [rttbreachcomment] in the value object [ims.RefMan.vo.TCIForPatientElectivetListWLVo] is too big. It should be less or equal to 255");
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
	
		TCIForPatientElectivetListWLVo clone = new TCIForPatientElectivetListWLVo(this.id, this.version);
		
		if(this.tcidate == null)
			clone.tcidate = null;
		else
			clone.tcidate = (ims.framework.utils.Date)this.tcidate.clone();
		if(this.tcitime == null)
			clone.tcitime = null;
		else
			clone.tcitime = (ims.framework.utils.Time)this.tcitime.clone();
		if(this.tcioffermethod == null)
			clone.tcioffermethod = null;
		else
			clone.tcioffermethod = (ims.core.vo.lookups.TCIOfferMethod)this.tcioffermethod.clone();
		if(this.datetcioffered == null)
			clone.datetcioffered = null;
		else
			clone.datetcioffered = (ims.framework.utils.Date)this.datetcioffered.clone();
		if(this.datetciaccepted == null)
			clone.datetciaccepted = null;
		else
			clone.datetciaccepted = (ims.framework.utils.Date)this.datetciaccepted.clone();
		clone.comments = this.comments;
		if(this.rttbreachreason == null)
			clone.rttbreachreason = null;
		else
			clone.rttbreachreason = (ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason)this.rttbreachreason.clone();
		if(this.planningelective == null)
			clone.planningelective = null;
		else
			clone.planningelective = (ims.RefMan.vo.lookups.PlanningElective)this.planningelective.clone();
		if(this.tcihospital == null)
			clone.tcihospital = null;
		else
			clone.tcihospital = (ims.core.vo.LocationLiteVo)this.tcihospital.clone();
		if(this.tciward == null)
			clone.tciward = null;
		else
			clone.tciward = (ims.core.vo.LocationLiteVo)this.tciward.clone();
		clone.tcibed = this.tcibed;
		clone.tciconsultant = this.tciconsultant;
		clone.isactive = this.isactive;
		clone.bedmanagercomment = this.bedmanagercomment;
		if(this.day28breachreason == null)
			clone.day28breachreason = null;
		else
			clone.day28breachreason = (ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason)this.day28breachreason.clone();
		clone.day28breachcomment = this.day28breachcomment;
		clone.rttbreachcomment = this.rttbreachcomment;
		if(this.plannedtcidate == null)
			clone.plannedtcidate = null;
		else
			clone.plannedtcidate = (ims.framework.utils.Date)this.plannedtcidate.clone();
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
		if (!(TCIForPatientElectivetListWLVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A TCIForPatientElectivetListWLVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((TCIForPatientElectivetListWLVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((TCIForPatientElectivetListWLVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.tcidate != null)
			count++;
		if(this.tcitime != null)
			count++;
		if(this.tcioffermethod != null)
			count++;
		if(this.datetcioffered != null)
			count++;
		if(this.datetciaccepted != null)
			count++;
		if(this.comments != null)
			count++;
		if(this.rttbreachreason != null)
			count++;
		if(this.planningelective != null)
			count++;
		if(this.tcihospital != null)
			count++;
		if(this.tciward != null)
			count++;
		if(this.tcibed != null)
			count++;
		if(this.tciconsultant != null)
			count++;
		if(this.isactive != null)
			count++;
		if(this.bedmanagercomment != null)
			count++;
		if(this.day28breachreason != null)
			count++;
		if(this.day28breachcomment != null)
			count++;
		if(this.rttbreachcomment != null)
			count++;
		if(this.plannedtcidate != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 18;
	}
	protected ims.framework.utils.Date tcidate;
	protected ims.framework.utils.Time tcitime;
	protected ims.core.vo.lookups.TCIOfferMethod tcioffermethod;
	protected ims.framework.utils.Date datetcioffered;
	protected ims.framework.utils.Date datetciaccepted;
	protected String comments;
	protected ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason rttbreachreason;
	protected ims.RefMan.vo.lookups.PlanningElective planningelective;
	protected ims.core.vo.LocationLiteVo tcihospital;
	protected ims.core.vo.LocationLiteVo tciward;
	protected String tcibed;
	protected ims.core.resource.people.vo.HcpRefVo tciconsultant;
	protected Boolean isactive;
	protected String bedmanagercomment;
	protected ims.scheduling.vo.lookups.RTTWeekWaitOr28DayRuleBreachReason day28breachreason;
	protected String day28breachcomment;
	protected String rttbreachcomment;
	protected ims.framework.utils.Date plannedtcidate;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
