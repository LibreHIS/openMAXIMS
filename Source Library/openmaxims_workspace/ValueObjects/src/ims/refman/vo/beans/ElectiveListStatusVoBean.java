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

package ims.RefMan.vo.beans;

public class ElectiveListStatusVoBean extends ims.vo.ValueObjectBean
{
	public ElectiveListStatusVoBean()
	{
	}
	public ElectiveListStatusVoBean(ims.RefMan.vo.ElectiveListStatusVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.authoringuser = vo.getAuthoringUser() == null ? null : (ims.core.vo.beans.MemberOfStaffLiteVoBean)vo.getAuthoringUser().getBean();
		this.statusdatetime = vo.getStatusDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getStatusDateTime().getBean();
		this.statuscomment = vo.getStatusComment();
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListStatus().getBean();
		this.removalreason = vo.getRemovalReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getRemovalReason().getBean();
		this.removalotherreason = vo.getRemovalOtherReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getRemovalOtherReason().getBean();
		this.undoremovalreason = vo.getUndoRemovalReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getUndoRemovalReason().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.ElectiveListStatusVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.authoringuser = vo.getAuthoringUser() == null ? null : (ims.core.vo.beans.MemberOfStaffLiteVoBean)vo.getAuthoringUser().getBean(map);
		this.statusdatetime = vo.getStatusDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getStatusDateTime().getBean();
		this.statuscomment = vo.getStatusComment();
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListStatus().getBean();
		this.removalreason = vo.getRemovalReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getRemovalReason().getBean();
		this.removalotherreason = vo.getRemovalOtherReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getRemovalOtherReason().getBean();
		this.undoremovalreason = vo.getUndoRemovalReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getUndoRemovalReason().getBean();
	}

	public ims.RefMan.vo.ElectiveListStatusVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.ElectiveListStatusVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.ElectiveListStatusVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.ElectiveListStatusVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.ElectiveListStatusVo();
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
	public ims.core.vo.beans.MemberOfStaffLiteVoBean getAuthoringUser()
	{
		return this.authoringuser;
	}
	public void setAuthoringUser(ims.core.vo.beans.MemberOfStaffLiteVoBean value)
	{
		this.authoringuser = value;
	}
	public ims.framework.utils.beans.DateTimeBean getStatusDateTime()
	{
		return this.statusdatetime;
	}
	public void setStatusDateTime(ims.framework.utils.beans.DateTimeBean value)
	{
		this.statusdatetime = value;
	}
	public String getStatusComment()
	{
		return this.statuscomment;
	}
	public void setStatusComment(String value)
	{
		this.statuscomment = value;
	}
	public ims.vo.LookupInstanceBean getElectiveListStatus()
	{
		return this.electiveliststatus;
	}
	public void setElectiveListStatus(ims.vo.LookupInstanceBean value)
	{
		this.electiveliststatus = value;
	}
	public ims.vo.LookupInstanceBean getRemovalReason()
	{
		return this.removalreason;
	}
	public void setRemovalReason(ims.vo.LookupInstanceBean value)
	{
		this.removalreason = value;
	}
	public ims.vo.LookupInstanceBean getRemovalOtherReason()
	{
		return this.removalotherreason;
	}
	public void setRemovalOtherReason(ims.vo.LookupInstanceBean value)
	{
		this.removalotherreason = value;
	}
	public ims.vo.LookupInstanceBean getUndoRemovalReason()
	{
		return this.undoremovalreason;
	}
	public void setUndoRemovalReason(ims.vo.LookupInstanceBean value)
	{
		this.undoremovalreason = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.MemberOfStaffLiteVoBean authoringuser;
	private ims.framework.utils.beans.DateTimeBean statusdatetime;
	private String statuscomment;
	private ims.vo.LookupInstanceBean electiveliststatus;
	private ims.vo.LookupInstanceBean removalreason;
	private ims.vo.LookupInstanceBean removalotherreason;
	private ims.vo.LookupInstanceBean undoremovalreason;
}
