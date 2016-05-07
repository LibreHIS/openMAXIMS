// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo.beans;

public class TrackingStatusConfigVoBean extends ims.vo.ValueObjectBean
{
	public TrackingStatusConfigVoBean()
	{
	}
	public TrackingStatusConfigVoBean(ims.RefMan.vo.TrackingStatusConfigVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.timeintopacu = vo.getTimeintoPACU() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeintoPACU().getBean();
		this.timestartanaesthetist = vo.getTimeStartAnaesthetist() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeStartAnaesthetist().getBean();
		this.theatrein = vo.getTheatreIn() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTheatreIn().getBean();
		this.surgerystart = vo.getSurgeryStart() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getSurgeryStart().getBean();
		this.surgeryfinish = vo.getSurgeryFinish() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getSurgeryFinish().getBean();
		this.timeintorecovery = vo.getTimeIntoRecovery() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeIntoRecovery().getBean();
		this.timeoutofrecovery = vo.getTimeOutOfRecovery() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeOutOfRecovery().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.TrackingStatusConfigVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.timeintopacu = vo.getTimeintoPACU() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeintoPACU().getBean();
		this.timestartanaesthetist = vo.getTimeStartAnaesthetist() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeStartAnaesthetist().getBean();
		this.theatrein = vo.getTheatreIn() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTheatreIn().getBean();
		this.surgerystart = vo.getSurgeryStart() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getSurgeryStart().getBean();
		this.surgeryfinish = vo.getSurgeryFinish() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getSurgeryFinish().getBean();
		this.timeintorecovery = vo.getTimeIntoRecovery() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeIntoRecovery().getBean();
		this.timeoutofrecovery = vo.getTimeOutOfRecovery() == null ? null : (ims.framework.utils.beans.ColorBean)vo.getTimeOutOfRecovery().getBean();
	}

	public ims.RefMan.vo.TrackingStatusConfigVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.TrackingStatusConfigVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.TrackingStatusConfigVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.TrackingStatusConfigVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.TrackingStatusConfigVo();
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
	public ims.framework.utils.beans.ColorBean getTimeintoPACU()
	{
		return this.timeintopacu;
	}
	public void setTimeintoPACU(ims.framework.utils.beans.ColorBean value)
	{
		this.timeintopacu = value;
	}
	public ims.framework.utils.beans.ColorBean getTimeStartAnaesthetist()
	{
		return this.timestartanaesthetist;
	}
	public void setTimeStartAnaesthetist(ims.framework.utils.beans.ColorBean value)
	{
		this.timestartanaesthetist = value;
	}
	public ims.framework.utils.beans.ColorBean getTheatreIn()
	{
		return this.theatrein;
	}
	public void setTheatreIn(ims.framework.utils.beans.ColorBean value)
	{
		this.theatrein = value;
	}
	public ims.framework.utils.beans.ColorBean getSurgeryStart()
	{
		return this.surgerystart;
	}
	public void setSurgeryStart(ims.framework.utils.beans.ColorBean value)
	{
		this.surgerystart = value;
	}
	public ims.framework.utils.beans.ColorBean getSurgeryFinish()
	{
		return this.surgeryfinish;
	}
	public void setSurgeryFinish(ims.framework.utils.beans.ColorBean value)
	{
		this.surgeryfinish = value;
	}
	public ims.framework.utils.beans.ColorBean getTimeIntoRecovery()
	{
		return this.timeintorecovery;
	}
	public void setTimeIntoRecovery(ims.framework.utils.beans.ColorBean value)
	{
		this.timeintorecovery = value;
	}
	public ims.framework.utils.beans.ColorBean getTimeOutOfRecovery()
	{
		return this.timeoutofrecovery;
	}
	public void setTimeOutOfRecovery(ims.framework.utils.beans.ColorBean value)
	{
		this.timeoutofrecovery = value;
	}

	private Integer id;
	private int version;
	private ims.framework.utils.beans.ColorBean timeintopacu;
	private ims.framework.utils.beans.ColorBean timestartanaesthetist;
	private ims.framework.utils.beans.ColorBean theatrein;
	private ims.framework.utils.beans.ColorBean surgerystart;
	private ims.framework.utils.beans.ColorBean surgeryfinish;
	private ims.framework.utils.beans.ColorBean timeintorecovery;
	private ims.framework.utils.beans.ColorBean timeoutofrecovery;
}