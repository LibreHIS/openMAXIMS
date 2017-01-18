package ims.vo;

import ims.framework.utils.DateTime;
import ims.framework.utils.beans.DateTimeBean;

public class SysInfoBean
{
	private String creationUser;
	private DateTimeBean creationDateTime;
	private String lastupdateUser;
	private DateTimeBean lastupdateDateTime;

	public SysInfoBean()
	{
		super();
	}

	public SysInfoBean(String creationUser, DateTimeBean creationDateTime, String lastupdateUser, DateTimeBean lastupdateDateTime )
	{
		this.creationDateTime = creationDateTime;
		this.creationUser = creationUser;
		this.lastupdateDateTime = lastupdateDateTime;
		this.lastupdateUser = lastupdateUser;
	}
		 
	public void populate(@SuppressWarnings("unused") ims.vo.ValueObjectBeanMap map, SystemInformation vo)
	{
		this.creationDateTime = vo.getCreationDateTime().getBean();
		this.creationUser = vo.getCreationUser();
		this.lastupdateDateTime = vo.getLastupdateDateTime().getBean();
		this.lastupdateUser = vo.getLastupdateUser();
	}

	public SystemInformation buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public SystemInformation buildVo(ims.vo.ValueObjectBeanMap map)
	{
		SystemInformation vo = null;
		if(vo == null)
		{
			vo = new SystemInformation();
			vo.populate(map, this);
		}
		return vo;
	}
	
	public SystemInformation buildSystemInformation()
	{
		//FWB-132 NPE
		DateTime creationDateTimeVo=null;
		if (creationDateTime != null)
			creationDateTimeVo=creationDateTime.buildDateTime();
		
		DateTime lastUpdateDateTimeVo=null;
		if (lastupdateDateTime != null)
			lastUpdateDateTimeVo=lastupdateDateTime.buildDateTime();
		return new SystemInformation(creationUser,creationDateTimeVo , lastupdateUser,lastUpdateDateTimeVo );
	}

	public SystemInformation[] buildSystemInformationArray(SysInfoBean[] beans)
	{
		SystemInformation[] ret = new SystemInformation[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildSystemInformation();			
		}
		return ret;		
	}

	public DateTimeBean getCreationDateTime()
	{
		return creationDateTime;
	}

	public String getCreationUser()
	{
		return creationUser;
	}

	public DateTimeBean getLastupdateDateTime()
	{
		return lastupdateDateTime;
	}

	public String getLastupdateUser()
	{
		return lastupdateUser;
	}

}
