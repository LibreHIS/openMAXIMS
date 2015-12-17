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

package ims.hl7.vo.beans;

public class ifMOSMessageQueueVoBean extends ims.vo.ValueObjectBean
{
	public ifMOSMessageQueueVoBean()
	{
	}
	public ifMOSMessageQueueVoBean(ims.hl7.vo.ifMOSMessageQueueVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.name = vo.getName() == null ? null : (ims.core.vo.beans.PersonNameBean)vo.getName().getBean();
		this.stafftype = vo.getStaffType() == null ? null : (ims.vo.LookupInstanceBean)vo.getStaffType().getBean();
		this.isactive = vo.getIsActive();
		this.commchannels = vo.getCommChannels() == null ? null : vo.getCommChannels().getBeanCollection();
		this.codemappings = vo.getCodeMappings() == null ? null : vo.getCodeMappings().getBeanCollection();
		this.systeminformation = vo.getSystemInformation() == null ? null : (ims.vo.SysInfoBean)vo.getSystemInformation().getBean();
		this.hcptype = vo.getHcpType() == null ? null : (ims.vo.LookupInstanceBean)vo.getHcpType().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.hl7.vo.ifMOSMessageQueueVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.name = vo.getName() == null ? null : (ims.core.vo.beans.PersonNameBean)vo.getName().getBean(map);
		this.stafftype = vo.getStaffType() == null ? null : (ims.vo.LookupInstanceBean)vo.getStaffType().getBean();
		this.isactive = vo.getIsActive();
		this.commchannels = vo.getCommChannels() == null ? null : vo.getCommChannels().getBeanCollection();
		this.codemappings = vo.getCodeMappings() == null ? null : vo.getCodeMappings().getBeanCollection();
		this.systeminformation = vo.getSystemInformation() == null ? null : (ims.vo.SysInfoBean)vo.getSystemInformation().getBean();
		this.hcptype = vo.getHcpType() == null ? null : (ims.vo.LookupInstanceBean)vo.getHcpType().getBean();
	}

	public ims.hl7.vo.ifMOSMessageQueueVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.hl7.vo.ifMOSMessageQueueVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.hl7.vo.ifMOSMessageQueueVo vo = null;
		if(map != null)
			vo = (ims.hl7.vo.ifMOSMessageQueueVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.hl7.vo.ifMOSMessageQueueVo();
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
	public ims.core.vo.beans.PersonNameBean getName()
	{
		return this.name;
	}
	public void setName(ims.core.vo.beans.PersonNameBean value)
	{
		this.name = value;
	}
	public ims.vo.LookupInstanceBean getStaffType()
	{
		return this.stafftype;
	}
	public void setStaffType(ims.vo.LookupInstanceBean value)
	{
		this.stafftype = value;
	}
	public Boolean getIsActive()
	{
		return this.isactive;
	}
	public void setIsActive(Boolean value)
	{
		this.isactive = value;
	}
	public ims.core.vo.beans.CommChannelVoBean[] getCommChannels()
	{
		return this.commchannels;
	}
	public void setCommChannels(ims.core.vo.beans.CommChannelVoBean[] value)
	{
		this.commchannels = value;
	}
	public ims.core.vo.beans.TaxonomyMapBean[] getCodeMappings()
	{
		return this.codemappings;
	}
	public void setCodeMappings(ims.core.vo.beans.TaxonomyMapBean[] value)
	{
		this.codemappings = value;
	}
	public ims.vo.SysInfoBean getSystemInformation()
	{
		return this.systeminformation;
	}
	public void setSystemInformation(ims.vo.SysInfoBean value)
	{
		this.systeminformation = value;
	}
	public ims.vo.LookupInstanceBean getHcpType()
	{
		return this.hcptype;
	}
	public void setHcpType(ims.vo.LookupInstanceBean value)
	{
		this.hcptype = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.PersonNameBean name;
	private ims.vo.LookupInstanceBean stafftype;
	private Boolean isactive;
	private ims.core.vo.beans.CommChannelVoBean[] commchannels;
	private ims.core.vo.beans.TaxonomyMapBean[] codemappings;
	private ims.vo.SysInfoBean systeminformation;
	private ims.vo.LookupInstanceBean hcptype;
}