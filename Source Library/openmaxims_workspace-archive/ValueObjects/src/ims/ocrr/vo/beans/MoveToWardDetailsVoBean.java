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

package ims.ocrr.vo.beans;

public class MoveToWardDetailsVoBean extends ims.vo.ValueObjectBean
{
	public MoveToWardDetailsVoBean()
	{
	}
	public MoveToWardDetailsVoBean(ims.ocrr.vo.MoveToWardDetailsVo vo)
	{
		this.specimenid = vo.getSpecimenId();
		this.isroundclosed = vo.getIsRoundClosed();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.ocrr.vo.MoveToWardDetailsVo vo)
	{
		this.specimenid = vo.getSpecimenId();
		this.isroundclosed = vo.getIsRoundClosed();
	}

	public ims.ocrr.vo.MoveToWardDetailsVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.ocrr.vo.MoveToWardDetailsVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.ocrr.vo.MoveToWardDetailsVo vo = null;
		if(map != null)
			vo = (ims.ocrr.vo.MoveToWardDetailsVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.ocrr.vo.MoveToWardDetailsVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public Integer getSpecimenId()
	{
		return this.specimenid;
	}
	public void setSpecimenId(Integer value)
	{
		this.specimenid = value;
	}
	public Boolean getIsRoundClosed()
	{
		return this.isroundclosed;
	}
	public void setIsRoundClosed(Boolean value)
	{
		this.isroundclosed = value;
	}

	private Integer specimenid;
	private Boolean isroundclosed;
}