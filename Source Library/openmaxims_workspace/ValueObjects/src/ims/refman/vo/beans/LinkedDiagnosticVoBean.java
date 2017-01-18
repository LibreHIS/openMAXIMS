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

public class LinkedDiagnosticVoBean extends ims.vo.ValueObjectBean
{
	public LinkedDiagnosticVoBean()
	{
	}
	public LinkedDiagnosticVoBean(ims.RefMan.vo.LinkedDiagnosticVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.diagnostics = vo.getDiagnostics() == null ? null : (ims.vo.LookupInstanceBean)vo.getDiagnostics().getBean();
		this.periodvalue = vo.getPeriodValue();
		this.periodtype = vo.getPeriodType() == null ? null : (ims.vo.LookupInstanceBean)vo.getPeriodType().getBean();
		this.sameday = vo.getSameDay() == null ? null : (ims.vo.LookupInstanceBean)vo.getSameDay().getBean();
		this.requesteddate = vo.getRequestedDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getRequestedDate().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.LinkedDiagnosticVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.diagnostics = vo.getDiagnostics() == null ? null : (ims.vo.LookupInstanceBean)vo.getDiagnostics().getBean();
		this.periodvalue = vo.getPeriodValue();
		this.periodtype = vo.getPeriodType() == null ? null : (ims.vo.LookupInstanceBean)vo.getPeriodType().getBean();
		this.sameday = vo.getSameDay() == null ? null : (ims.vo.LookupInstanceBean)vo.getSameDay().getBean();
		this.requesteddate = vo.getRequestedDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getRequestedDate().getBean();
	}

	public ims.RefMan.vo.LinkedDiagnosticVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.LinkedDiagnosticVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.LinkedDiagnosticVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.LinkedDiagnosticVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.LinkedDiagnosticVo();
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
	public ims.vo.LookupInstanceBean getDiagnostics()
	{
		return this.diagnostics;
	}
	public void setDiagnostics(ims.vo.LookupInstanceBean value)
	{
		this.diagnostics = value;
	}
	public Integer getPeriodValue()
	{
		return this.periodvalue;
	}
	public void setPeriodValue(Integer value)
	{
		this.periodvalue = value;
	}
	public ims.vo.LookupInstanceBean getPeriodType()
	{
		return this.periodtype;
	}
	public void setPeriodType(ims.vo.LookupInstanceBean value)
	{
		this.periodtype = value;
	}
	public ims.vo.LookupInstanceBean getSameDay()
	{
		return this.sameday;
	}
	public void setSameDay(ims.vo.LookupInstanceBean value)
	{
		this.sameday = value;
	}
	public ims.framework.utils.beans.DateBean getRequestedDate()
	{
		return this.requesteddate;
	}
	public void setRequestedDate(ims.framework.utils.beans.DateBean value)
	{
		this.requesteddate = value;
	}

	private Integer id;
	private int version;
	private ims.vo.LookupInstanceBean diagnostics;
	private Integer periodvalue;
	private ims.vo.LookupInstanceBean periodtype;
	private ims.vo.LookupInstanceBean sameday;
	private ims.framework.utils.beans.DateBean requesteddate;
}
