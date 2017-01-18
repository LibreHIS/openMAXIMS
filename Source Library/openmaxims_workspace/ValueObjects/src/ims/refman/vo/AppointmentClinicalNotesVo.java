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
 * Linked to RefMan.AppointmentClinicalNotes business object (ID: 1096100059).
 */
public class AppointmentClinicalNotesVo extends ims.RefMan.vo.AppointmentClinicalNotesRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public AppointmentClinicalNotesVo()
	{
	}
	public AppointmentClinicalNotesVo(Integer id, int version)
	{
		super(id, version);
	}
	public AppointmentClinicalNotesVo(ims.RefMan.vo.beans.AppointmentClinicalNotesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.catsreferral = bean.getCatsReferral() == null ? null : new ims.RefMan.vo.CatsReferralRefVo(new Integer(bean.getCatsReferral().getId()), bean.getCatsReferral().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo();
		this.clinicalnote = bean.getClinicalNote();
		this.clinicalnotetype = bean.getClinicalNoteType() == null ? null : ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment.buildLookup(bean.getClinicalNoteType());
		this.appointment = bean.getAppointment() == null ? null : new ims.scheduling.vo.Booking_AppointmentRefVo(new Integer(bean.getAppointment().getId()), bean.getAppointment().getVersion());
		this.authoringuser = bean.getAuthoringUser() == null ? null : new ims.core.resource.people.vo.MemberOfStaffRefVo(new Integer(bean.getAuthoringUser().getId()), bean.getAuthoringUser().getVersion());
		this.sysinfo = bean.getSysInfo() == null ? null : bean.getSysInfo().buildSystemInformation();
		this.notetype = bean.getNoteType() == null ? null : ims.RefMan.vo.lookups.ReportNoteType.buildLookup(bean.getNoteType());
		this.notestatus = bean.getNoteStatus() == null ? null : ims.RefMan.vo.lookups.ReportNoteStatus.buildLookup(bean.getNoteStatus());
		this.copytogp = bean.getCopyToGP();
		this.copytopatient = bean.getCopyToPatient();
		this.copytosecondarycare = bean.getCopyToSecondaryCare();
		this.correspondencemadeactivedatetime = bean.getCorrespondenceMadeActiveDateTime() == null ? null : bean.getCorrespondenceMadeActiveDateTime().buildDateTime();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.AppointmentClinicalNotesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.catsreferral = bean.getCatsReferral() == null ? null : new ims.RefMan.vo.CatsReferralRefVo(new Integer(bean.getCatsReferral().getId()), bean.getCatsReferral().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo(map);
		this.clinicalnote = bean.getClinicalNote();
		this.clinicalnotetype = bean.getClinicalNoteType() == null ? null : ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment.buildLookup(bean.getClinicalNoteType());
		this.appointment = bean.getAppointment() == null ? null : new ims.scheduling.vo.Booking_AppointmentRefVo(new Integer(bean.getAppointment().getId()), bean.getAppointment().getVersion());
		this.authoringuser = bean.getAuthoringUser() == null ? null : new ims.core.resource.people.vo.MemberOfStaffRefVo(new Integer(bean.getAuthoringUser().getId()), bean.getAuthoringUser().getVersion());
		this.sysinfo = bean.getSysInfo() == null ? null : bean.getSysInfo().buildSystemInformation();
		this.notetype = bean.getNoteType() == null ? null : ims.RefMan.vo.lookups.ReportNoteType.buildLookup(bean.getNoteType());
		this.notestatus = bean.getNoteStatus() == null ? null : ims.RefMan.vo.lookups.ReportNoteStatus.buildLookup(bean.getNoteStatus());
		this.copytogp = bean.getCopyToGP();
		this.copytopatient = bean.getCopyToPatient();
		this.copytosecondarycare = bean.getCopyToSecondaryCare();
		this.correspondencemadeactivedatetime = bean.getCorrespondenceMadeActiveDateTime() == null ? null : bean.getCorrespondenceMadeActiveDateTime().buildDateTime();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.AppointmentClinicalNotesVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.AppointmentClinicalNotesVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.AppointmentClinicalNotesVoBean();
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
		if(fieldName.equals("AUTHORINGINFORMATION"))
			return getAuthoringInformation();
		if(fieldName.equals("CLINICALNOTE"))
			return getClinicalNote();
		if(fieldName.equals("CLINICALNOTETYPE"))
			return getClinicalNoteType();
		if(fieldName.equals("APPOINTMENT"))
			return getAppointment();
		if(fieldName.equals("AUTHORINGUSER"))
			return getAuthoringUser();
		if(fieldName.equals("SYSINFO"))
			return getSysInfo();
		if(fieldName.equals("NOTETYPE"))
			return getNoteType();
		if(fieldName.equals("NOTESTATUS"))
			return getNoteStatus();
		if(fieldName.equals("COPYTOGP"))
			return getCopyToGP();
		if(fieldName.equals("COPYTOPATIENT"))
			return getCopyToPatient();
		if(fieldName.equals("COPYTOSECONDARYCARE"))
			return getCopyToSecondaryCare();
		if(fieldName.equals("CORRESPONDENCEMADEACTIVEDATETIME"))
			return getCorrespondenceMadeActiveDateTime();
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
	public boolean getClinicalNoteIsNotNull()
	{
		return this.clinicalnote != null;
	}
	public String getClinicalNote()
	{
		return this.clinicalnote;
	}
	public static int getClinicalNoteMaxLength()
	{
		return 5000;
	}
	public void setClinicalNote(String value)
	{
		this.isValidated = false;
		this.clinicalnote = value;
	}
	public boolean getClinicalNoteTypeIsNotNull()
	{
		return this.clinicalnotetype != null;
	}
	public ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment getClinicalNoteType()
	{
		return this.clinicalnotetype;
	}
	public void setClinicalNoteType(ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment value)
	{
		this.isValidated = false;
		this.clinicalnotetype = value;
	}
	public boolean getAppointmentIsNotNull()
	{
		return this.appointment != null;
	}
	public ims.scheduling.vo.Booking_AppointmentRefVo getAppointment()
	{
		return this.appointment;
	}
	public void setAppointment(ims.scheduling.vo.Booking_AppointmentRefVo value)
	{
		this.isValidated = false;
		this.appointment = value;
	}
	public boolean getAuthoringUserIsNotNull()
	{
		return this.authoringuser != null;
	}
	public ims.core.resource.people.vo.MemberOfStaffRefVo getAuthoringUser()
	{
		return this.authoringuser;
	}
	public void setAuthoringUser(ims.core.resource.people.vo.MemberOfStaffRefVo value)
	{
		this.isValidated = false;
		this.authoringuser = value;
	}
	public boolean getSysInfoIsNotNull()
	{
		return this.sysinfo != null;
	}
	public ims.vo.SystemInformation getSysInfo()
	{
		return this.sysinfo;
	}
	public void setSysInfo(ims.vo.SystemInformation value)
	{
		this.isValidated = false;
		this.sysinfo = value;
	}
	public boolean getNoteTypeIsNotNull()
	{
		return this.notetype != null;
	}
	public ims.RefMan.vo.lookups.ReportNoteType getNoteType()
	{
		return this.notetype;
	}
	public void setNoteType(ims.RefMan.vo.lookups.ReportNoteType value)
	{
		this.isValidated = false;
		this.notetype = value;
	}
	public boolean getNoteStatusIsNotNull()
	{
		return this.notestatus != null;
	}
	public ims.RefMan.vo.lookups.ReportNoteStatus getNoteStatus()
	{
		return this.notestatus;
	}
	public void setNoteStatus(ims.RefMan.vo.lookups.ReportNoteStatus value)
	{
		this.isValidated = false;
		this.notestatus = value;
	}
	public boolean getCopyToGPIsNotNull()
	{
		return this.copytogp != null;
	}
	public Boolean getCopyToGP()
	{
		return this.copytogp;
	}
	public void setCopyToGP(Boolean value)
	{
		this.isValidated = false;
		this.copytogp = value;
	}
	public boolean getCopyToPatientIsNotNull()
	{
		return this.copytopatient != null;
	}
	public Boolean getCopyToPatient()
	{
		return this.copytopatient;
	}
	public void setCopyToPatient(Boolean value)
	{
		this.isValidated = false;
		this.copytopatient = value;
	}
	public boolean getCopyToSecondaryCareIsNotNull()
	{
		return this.copytosecondarycare != null;
	}
	public Boolean getCopyToSecondaryCare()
	{
		return this.copytosecondarycare;
	}
	public void setCopyToSecondaryCare(Boolean value)
	{
		this.isValidated = false;
		this.copytosecondarycare = value;
	}
	public boolean getCorrespondenceMadeActiveDateTimeIsNotNull()
	{
		return this.correspondencemadeactivedatetime != null;
	}
	public ims.framework.utils.DateTime getCorrespondenceMadeActiveDateTime()
	{
		return this.correspondencemadeactivedatetime;
	}
	public void setCorrespondenceMadeActiveDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.correspondencemadeactivedatetime = value;
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
		if(this.catsreferral == null)
			listOfErrors.add("CatsReferral is mandatory");
		if(this.authoringinformation == null)
			listOfErrors.add("Authoring Information is mandatory");
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
		if(this.clinicalnote == null || this.clinicalnote.length() == 0)
			listOfErrors.add("ClinicalNote is mandatory");
		if(this.clinicalnotetype == null)
			listOfErrors.add("ClinicalNoteType is mandatory");
		if(this.appointment == null)
			listOfErrors.add("Appointment is mandatory");
		if(this.authoringuser == null)
			listOfErrors.add("AuthoringUser is mandatory");
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
	
		AppointmentClinicalNotesVo clone = new AppointmentClinicalNotesVo(this.id, this.version);
		
		clone.catsreferral = this.catsreferral;
		if(this.authoringinformation == null)
			clone.authoringinformation = null;
		else
			clone.authoringinformation = (ims.core.vo.AuthoringInformationVo)this.authoringinformation.clone();
		clone.clinicalnote = this.clinicalnote;
		if(this.clinicalnotetype == null)
			clone.clinicalnotetype = null;
		else
			clone.clinicalnotetype = (ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment)this.clinicalnotetype.clone();
		clone.appointment = this.appointment;
		clone.authoringuser = this.authoringuser;
		if(this.sysinfo == null)
			clone.sysinfo = null;
		else
			clone.sysinfo = (ims.vo.SystemInformation)this.sysinfo.clone();
		if(this.notetype == null)
			clone.notetype = null;
		else
			clone.notetype = (ims.RefMan.vo.lookups.ReportNoteType)this.notetype.clone();
		if(this.notestatus == null)
			clone.notestatus = null;
		else
			clone.notestatus = (ims.RefMan.vo.lookups.ReportNoteStatus)this.notestatus.clone();
		clone.copytogp = this.copytogp;
		clone.copytopatient = this.copytopatient;
		clone.copytosecondarycare = this.copytosecondarycare;
		if(this.correspondencemadeactivedatetime == null)
			clone.correspondencemadeactivedatetime = null;
		else
			clone.correspondencemadeactivedatetime = (ims.framework.utils.DateTime)this.correspondencemadeactivedatetime.clone();
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
		if (!(AppointmentClinicalNotesVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A AppointmentClinicalNotesVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((AppointmentClinicalNotesVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((AppointmentClinicalNotesVo)obj).getBoId());
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
		if(this.authoringinformation != null)
			count++;
		if(this.clinicalnote != null)
			count++;
		if(this.clinicalnotetype != null)
			count++;
		if(this.appointment != null)
			count++;
		if(this.authoringuser != null)
			count++;
		if(this.sysinfo != null)
			count++;
		if(this.notetype != null)
			count++;
		if(this.notestatus != null)
			count++;
		if(this.copytogp != null)
			count++;
		if(this.copytopatient != null)
			count++;
		if(this.copytosecondarycare != null)
			count++;
		if(this.correspondencemadeactivedatetime != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 13;
	}
	protected ims.RefMan.vo.CatsReferralRefVo catsreferral;
	protected ims.core.vo.AuthoringInformationVo authoringinformation;
	protected String clinicalnote;
	protected ims.RefMan.vo.lookups.ClinicalNoteTypeForAnAppointment clinicalnotetype;
	protected ims.scheduling.vo.Booking_AppointmentRefVo appointment;
	protected ims.core.resource.people.vo.MemberOfStaffRefVo authoringuser;
	protected ims.vo.SystemInformation sysinfo;
	protected ims.RefMan.vo.lookups.ReportNoteType notetype;
	protected ims.RefMan.vo.lookups.ReportNoteStatus notestatus;
	protected Boolean copytogp;
	protected Boolean copytopatient;
	protected Boolean copytosecondarycare;
	protected ims.framework.utils.DateTime correspondencemadeactivedatetime;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
