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

package ims.therapies.vo.beans;

public class FunctionalTransfersVoBean extends ims.vo.ValueObjectBean
{
	public FunctionalTransfersVoBean()
	{
	}
	public FunctionalTransfersVoBean(ims.therapies.vo.FunctionalTransfersVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.clinicalcontact = vo.getClinicalContact() == null ? null : new ims.vo.RefVoBean(vo.getClinicalContact().getBoId(), vo.getClinicalContact().getBoVersion());
		this.authoringcp = vo.getAuthoringCP() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getAuthoringCP().getBean();
		this.authoringdatetime = vo.getAuthoringDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getAuthoringDateTime().getBean();
		this.movements = vo.getMovements() == null ? null : vo.getMovements().getBeanCollection();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.therapies.vo.FunctionalTransfersVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.clinicalcontact = vo.getClinicalContact() == null ? null : new ims.vo.RefVoBean(vo.getClinicalContact().getBoId(), vo.getClinicalContact().getBoVersion());
		this.authoringcp = vo.getAuthoringCP() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getAuthoringCP().getBean(map);
		this.authoringdatetime = vo.getAuthoringDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getAuthoringDateTime().getBean();
		this.movements = vo.getMovements() == null ? null : vo.getMovements().getBeanCollection();
	}

	public ims.therapies.vo.FunctionalTransfersVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.therapies.vo.FunctionalTransfersVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.therapies.vo.FunctionalTransfersVo vo = null;
		if(map != null)
			vo = (ims.therapies.vo.FunctionalTransfersVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.therapies.vo.FunctionalTransfersVo();
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
	public ims.vo.RefVoBean getClinicalContact()
	{
		return this.clinicalcontact;
	}
	public void setClinicalContact(ims.vo.RefVoBean value)
	{
		this.clinicalcontact = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean getAuthoringCP()
	{
		return this.authoringcp;
	}
	public void setAuthoringCP(ims.core.vo.beans.HcpLiteVoBean value)
	{
		this.authoringcp = value;
	}
	public ims.framework.utils.beans.DateTimeBean getAuthoringDateTime()
	{
		return this.authoringdatetime;
	}
	public void setAuthoringDateTime(ims.framework.utils.beans.DateTimeBean value)
	{
		this.authoringdatetime = value;
	}
	public ims.therapies.vo.beans.FunctionalTransfersTechniqueVoBean[] getMovements()
	{
		return this.movements;
	}
	public void setMovements(ims.therapies.vo.beans.FunctionalTransfersTechniqueVoBean[] value)
	{
		this.movements = value;
	}

	private Integer id;
	private int version;
	private ims.vo.RefVoBean clinicalcontact;
	private ims.core.vo.beans.HcpLiteVoBean authoringcp;
	private ims.framework.utils.beans.DateTimeBean authoringdatetime;
	private ims.therapies.vo.beans.FunctionalTransfersTechniqueVoBean[] movements;
}