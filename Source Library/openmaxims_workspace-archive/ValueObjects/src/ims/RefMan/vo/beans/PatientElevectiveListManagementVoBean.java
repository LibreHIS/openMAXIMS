// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo.beans;

public class PatientElevectiveListManagementVoBean extends ims.vo.ValueObjectBean
{
	public PatientElevectiveListManagementVoBean()
	{
	}
	public PatientElevectiveListManagementVoBean(ims.RefMan.vo.PatientElevectiveListManagementVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientLite_IdentifiersVoBean)vo.getPatient().getBean();
		this.referral = vo.getReferral() == null ? null : new ims.vo.RefVoBean(vo.getReferral().getBoId(), vo.getReferral().getBoVersion());
		this.consultant = vo.getConsultant() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConsultant().getBean();
		this.dateonlist = vo.getDateOnList() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOnList().getBean();
		this.electiveadmissiontype = vo.getElectiveAdmissionType() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveAdmissionType().getBean();
		this.intendedmanagement = vo.getIntendedManagement() == null ? null : (ims.vo.LookupInstanceBean)vo.getIntendedManagement().getBean();
		this.anticipatedstay = vo.getAnticipatedStay();
		if(vo.getSuspensions() != null)
		{
			this.suspensions = new ims.vo.RefVoBean[vo.getSuspensions().size()];
			for(int suspensions_i = 0; suspensions_i < vo.getSuspensions().size(); suspensions_i++)
			{
				this.suspensions[suspensions_i] = new ims.vo.RefVoBean(vo.getSuspensions().get(suspensions_i).getBoId(),vo.getSuspensions().get(suspensions_i).getBoVersion());
			}
		}
		this.operativeprocedurestatus = vo.getOperativeProcedureStatus();
		this.primaryprocedure = vo.getPrimaryProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getPrimaryProcedure().getBean();
		if(vo.getOtherProcedures() != null)
		{
			this.otherprocedures = new ims.vo.RefVoBean[vo.getOtherProcedures().size()];
			for(int otherprocedures_i = 0; otherprocedures_i < vo.getOtherProcedures().size(); otherprocedures_i++)
			{
				this.otherprocedures[otherprocedures_i] = new ims.vo.RefVoBean(vo.getOtherProcedures().get(otherprocedures_i).getBoId(),vo.getOtherProcedures().get(otherprocedures_i).getBoVersion());
			}
		}
		if(vo.getTCIHistory() != null)
		{
			this.tcihistory = new ims.vo.RefVoBean[vo.getTCIHistory().size()];
			for(int tcihistory_i = 0; tcihistory_i < vo.getTCIHistory().size(); tcihistory_i++)
			{
				this.tcihistory[tcihistory_i] = new ims.vo.RefVoBean(vo.getTCIHistory().get(tcihistory_i).getBoId(),vo.getTCIHistory().get(tcihistory_i).getBoVersion());
			}
		}
		this.interpretatorrequired = vo.getInterpretatorRequired();
		if(vo.getNotes() != null)
		{
			this.notes = new ims.vo.RefVoBean[vo.getNotes().size()];
			for(int notes_i = 0; notes_i < vo.getNotes().size(); notes_i++)
			{
				this.notes[notes_i] = new ims.vo.RefVoBean(vo.getNotes().get(notes_i).getBoId(),vo.getNotes().get(notes_i).getBoVersion());
			}
		}
		this.erod = vo.getEROD() == null ? null : new ims.vo.RefVoBean(vo.getEROD().getBoId(), vo.getEROD().getBoVersion());
		if(vo.getERODHistory() != null)
		{
			this.erodhistory = new ims.vo.RefVoBean[vo.getERODHistory().size()];
			for(int erodhistory_i = 0; erodhistory_i < vo.getERODHistory().size(); erodhistory_i++)
			{
				this.erodhistory[erodhistory_i] = new ims.vo.RefVoBean(vo.getERODHistory().get(erodhistory_i).getBoId(),vo.getERODHistory().get(erodhistory_i).getBoVersion());
			}
		}
		this.tcidetails = vo.getTCIDetails() == null ? null : (ims.RefMan.vo.beans.PatientElectiveListTCIVoBean)vo.getTCIDetails().getBean();
		this.electivelist = vo.getElectiveList() == null ? null : (ims.admin.vo.beans.ElectiveListConfigurationVoBean)vo.getElectiveList().getBean();
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.RefMan.vo.beans.ElectiveListStatusVoBean)vo.getElectiveListStatus().getBean();
		this.patientstatus = vo.getPatientStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getPatientStatus().getBean();
		this.requirestciby = vo.getRequiresTCIBy() == null ? null : (ims.framework.utils.beans.DateBean)vo.getRequiresTCIBy().getBean();
		this.electivelistreason = vo.getElectiveListReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListReason().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.PatientElevectiveListManagementVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientLite_IdentifiersVoBean)vo.getPatient().getBean(map);
		this.referral = vo.getReferral() == null ? null : new ims.vo.RefVoBean(vo.getReferral().getBoId(), vo.getReferral().getBoVersion());
		this.consultant = vo.getConsultant() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConsultant().getBean(map);
		this.dateonlist = vo.getDateOnList() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOnList().getBean();
		this.electiveadmissiontype = vo.getElectiveAdmissionType() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveAdmissionType().getBean();
		this.intendedmanagement = vo.getIntendedManagement() == null ? null : (ims.vo.LookupInstanceBean)vo.getIntendedManagement().getBean();
		this.anticipatedstay = vo.getAnticipatedStay();
		if(vo.getSuspensions() != null)
		{
			this.suspensions = new ims.vo.RefVoBean[vo.getSuspensions().size()];
			for(int suspensions_i = 0; suspensions_i < vo.getSuspensions().size(); suspensions_i++)
			{
				this.suspensions[suspensions_i] = new ims.vo.RefVoBean(vo.getSuspensions().get(suspensions_i).getBoId(),vo.getSuspensions().get(suspensions_i).getBoVersion());
			}
		}
		this.operativeprocedurestatus = vo.getOperativeProcedureStatus();
		this.primaryprocedure = vo.getPrimaryProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getPrimaryProcedure().getBean(map);
		if(vo.getOtherProcedures() != null)
		{
			this.otherprocedures = new ims.vo.RefVoBean[vo.getOtherProcedures().size()];
			for(int otherprocedures_i = 0; otherprocedures_i < vo.getOtherProcedures().size(); otherprocedures_i++)
			{
				this.otherprocedures[otherprocedures_i] = new ims.vo.RefVoBean(vo.getOtherProcedures().get(otherprocedures_i).getBoId(),vo.getOtherProcedures().get(otherprocedures_i).getBoVersion());
			}
		}
		if(vo.getTCIHistory() != null)
		{
			this.tcihistory = new ims.vo.RefVoBean[vo.getTCIHistory().size()];
			for(int tcihistory_i = 0; tcihistory_i < vo.getTCIHistory().size(); tcihistory_i++)
			{
				this.tcihistory[tcihistory_i] = new ims.vo.RefVoBean(vo.getTCIHistory().get(tcihistory_i).getBoId(),vo.getTCIHistory().get(tcihistory_i).getBoVersion());
			}
		}
		this.interpretatorrequired = vo.getInterpretatorRequired();
		if(vo.getNotes() != null)
		{
			this.notes = new ims.vo.RefVoBean[vo.getNotes().size()];
			for(int notes_i = 0; notes_i < vo.getNotes().size(); notes_i++)
			{
				this.notes[notes_i] = new ims.vo.RefVoBean(vo.getNotes().get(notes_i).getBoId(),vo.getNotes().get(notes_i).getBoVersion());
			}
		}
		this.erod = vo.getEROD() == null ? null : new ims.vo.RefVoBean(vo.getEROD().getBoId(), vo.getEROD().getBoVersion());
		if(vo.getERODHistory() != null)
		{
			this.erodhistory = new ims.vo.RefVoBean[vo.getERODHistory().size()];
			for(int erodhistory_i = 0; erodhistory_i < vo.getERODHistory().size(); erodhistory_i++)
			{
				this.erodhistory[erodhistory_i] = new ims.vo.RefVoBean(vo.getERODHistory().get(erodhistory_i).getBoId(),vo.getERODHistory().get(erodhistory_i).getBoVersion());
			}
		}
		this.tcidetails = vo.getTCIDetails() == null ? null : (ims.RefMan.vo.beans.PatientElectiveListTCIVoBean)vo.getTCIDetails().getBean(map);
		this.electivelist = vo.getElectiveList() == null ? null : (ims.admin.vo.beans.ElectiveListConfigurationVoBean)vo.getElectiveList().getBean(map);
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.RefMan.vo.beans.ElectiveListStatusVoBean)vo.getElectiveListStatus().getBean(map);
		this.patientstatus = vo.getPatientStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getPatientStatus().getBean();
		this.requirestciby = vo.getRequiresTCIBy() == null ? null : (ims.framework.utils.beans.DateBean)vo.getRequiresTCIBy().getBean();
		this.electivelistreason = vo.getElectiveListReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListReason().getBean();
	}

	public ims.RefMan.vo.PatientElevectiveListManagementVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.PatientElevectiveListManagementVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.PatientElevectiveListManagementVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.PatientElevectiveListManagementVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.PatientElevectiveListManagementVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public Integer getId()
	{
		return this.id;
	}
	public void setId(Integer value)
	{
		this.id = value;
	}
	public int getVersion()
	{
		return this.version;
	}
	public void setVersion(int value)
	{
		this.version = value;
	}
	public ims.core.vo.beans.PatientLite_IdentifiersVoBean getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.vo.beans.PatientLite_IdentifiersVoBean value)
	{
		this.patient = value;
	}
	public ims.vo.RefVoBean getReferral()
	{
		return this.referral;
	}
	public void setReferral(ims.vo.RefVoBean value)
	{
		this.referral = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean getConsultant()
	{
		return this.consultant;
	}
	public void setConsultant(ims.core.vo.beans.HcpLiteVoBean value)
	{
		this.consultant = value;
	}
	public ims.framework.utils.beans.DateBean getDateOnList()
	{
		return this.dateonlist;
	}
	public void setDateOnList(ims.framework.utils.beans.DateBean value)
	{
		this.dateonlist = value;
	}
	public ims.vo.LookupInstanceBean getElectiveAdmissionType()
	{
		return this.electiveadmissiontype;
	}
	public void setElectiveAdmissionType(ims.vo.LookupInstanceBean value)
	{
		this.electiveadmissiontype = value;
	}
	public ims.vo.LookupInstanceBean getIntendedManagement()
	{
		return this.intendedmanagement;
	}
	public void setIntendedManagement(ims.vo.LookupInstanceBean value)
	{
		this.intendedmanagement = value;
	}
	public Integer getAnticipatedStay()
	{
		return this.anticipatedstay;
	}
	public void setAnticipatedStay(Integer value)
	{
		this.anticipatedstay = value;
	}
	public ims.vo.RefVoBean[] getSuspensions()
	{
		return this.suspensions;
	}
	public void setSuspensions(ims.vo.RefVoBean[] value)
	{
		this.suspensions = value;
	}
	public Boolean getOperativeProcedureStatus()
	{
		return this.operativeprocedurestatus;
	}
	public void setOperativeProcedureStatus(Boolean value)
	{
		this.operativeprocedurestatus = value;
	}
	public ims.core.vo.beans.ProcedureLiteVoBean getPrimaryProcedure()
	{
		return this.primaryprocedure;
	}
	public void setPrimaryProcedure(ims.core.vo.beans.ProcedureLiteVoBean value)
	{
		this.primaryprocedure = value;
	}
	public ims.vo.RefVoBean[] getOtherProcedures()
	{
		return this.otherprocedures;
	}
	public void setOtherProcedures(ims.vo.RefVoBean[] value)
	{
		this.otherprocedures = value;
	}
	public ims.vo.RefVoBean[] getTCIHistory()
	{
		return this.tcihistory;
	}
	public void setTCIHistory(ims.vo.RefVoBean[] value)
	{
		this.tcihistory = value;
	}
	public Boolean getInterpretatorRequired()
	{
		return this.interpretatorrequired;
	}
	public void setInterpretatorRequired(Boolean value)
	{
		this.interpretatorrequired = value;
	}
	public ims.vo.RefVoBean[] getNotes()
	{
		return this.notes;
	}
	public void setNotes(ims.vo.RefVoBean[] value)
	{
		this.notes = value;
	}
	public ims.vo.RefVoBean getEROD()
	{
		return this.erod;
	}
	public void setEROD(ims.vo.RefVoBean value)
	{
		this.erod = value;
	}
	public ims.vo.RefVoBean[] getERODHistory()
	{
		return this.erodhistory;
	}
	public void setERODHistory(ims.vo.RefVoBean[] value)
	{
		this.erodhistory = value;
	}
	public ims.RefMan.vo.beans.PatientElectiveListTCIVoBean getTCIDetails()
	{
		return this.tcidetails;
	}
	public void setTCIDetails(ims.RefMan.vo.beans.PatientElectiveListTCIVoBean value)
	{
		this.tcidetails = value;
	}
	public ims.admin.vo.beans.ElectiveListConfigurationVoBean getElectiveList()
	{
		return this.electivelist;
	}
	public void setElectiveList(ims.admin.vo.beans.ElectiveListConfigurationVoBean value)
	{
		this.electivelist = value;
	}
	public ims.RefMan.vo.beans.ElectiveListStatusVoBean getElectiveListStatus()
	{
		return this.electiveliststatus;
	}
	public void setElectiveListStatus(ims.RefMan.vo.beans.ElectiveListStatusVoBean value)
	{
		this.electiveliststatus = value;
	}
	public ims.vo.LookupInstanceBean getPatientStatus()
	{
		return this.patientstatus;
	}
	public void setPatientStatus(ims.vo.LookupInstanceBean value)
	{
		this.patientstatus = value;
	}
	public ims.framework.utils.beans.DateBean getRequiresTCIBy()
	{
		return this.requirestciby;
	}
	public void setRequiresTCIBy(ims.framework.utils.beans.DateBean value)
	{
		this.requirestciby = value;
	}
	public ims.vo.LookupInstanceBean getElectiveListReason()
	{
		return this.electivelistreason;
	}
	public void setElectiveListReason(ims.vo.LookupInstanceBean value)
	{
		this.electivelistreason = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.PatientLite_IdentifiersVoBean patient;
	private ims.vo.RefVoBean referral;
	private ims.core.vo.beans.HcpLiteVoBean consultant;
	private ims.framework.utils.beans.DateBean dateonlist;
	private ims.vo.LookupInstanceBean electiveadmissiontype;
	private ims.vo.LookupInstanceBean intendedmanagement;
	private Integer anticipatedstay;
	private ims.vo.RefVoBean[] suspensions;
	private Boolean operativeprocedurestatus;
	private ims.core.vo.beans.ProcedureLiteVoBean primaryprocedure;
	private ims.vo.RefVoBean[] otherprocedures;
	private ims.vo.RefVoBean[] tcihistory;
	private Boolean interpretatorrequired;
	private ims.vo.RefVoBean[] notes;
	private ims.vo.RefVoBean erod;
	private ims.vo.RefVoBean[] erodhistory;
	private ims.RefMan.vo.beans.PatientElectiveListTCIVoBean tcidetails;
	private ims.admin.vo.beans.ElectiveListConfigurationVoBean electivelist;
	private ims.RefMan.vo.beans.ElectiveListStatusVoBean electiveliststatus;
	private ims.vo.LookupInstanceBean patientstatus;
	private ims.framework.utils.beans.DateBean requirestciby;
	private ims.vo.LookupInstanceBean electivelistreason;
}