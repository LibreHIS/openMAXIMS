/*
 * Created on Mar 26, 2004
 *
 */
package ims.vo;

import java.io.Serializable;

import ims.framework.utils.DateTime;
import ims.framework.utils.beans.DateTimeBean;

/**
 * @author gcoghlan
 *
 */
public class SystemInformation implements ImsCloneable,Comparable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String creationUser;
	private DateTime creationDateTime;
	private String lastupdateUser;
	private DateTime lastupdateDateTime;
	
	public SystemInformation()
	{
	}
	
	public SystemInformation(String creationUser, DateTime creationDateTime, String lastupdateUser, DateTime lastupdateDateTime )
	{
		this.creationDateTime = creationDateTime;
		this.creationUser = creationUser;
		this.lastupdateDateTime = lastupdateDateTime;
		this.lastupdateUser = lastupdateUser;
	}
	 
	public void populate(@SuppressWarnings("unused") ims.vo.ValueObjectBeanMap map, SysInfoBean bean)
	{
		this.creationDateTime = bean.getCreationDateTime().buildDateTime();
		this.creationUser = bean.getCreationUser();
		this.lastupdateDateTime = bean.getLastupdateDateTime().buildDateTime();
		this.lastupdateUser = bean.getLastupdateUser();
	}

	/**
	 * @return
	 */
	public DateTime getCreationDateTime() 
	{
		return this.creationDateTime;
	}

	/**
	 * @return
	 */
	public String getCreationUser() 
	{
		return this.creationUser;
	}

	/**
	 * @return
	 */
	public DateTime getLastupdateDateTime() 
	{
		return this.lastupdateDateTime;
	}

	/**
	 * @return
	 */
	public String getLastupdateUser() 
	{
		return this.lastupdateUser;
	}

	/**
	 * @param time
	 */
	public void setCreationDateTime(DateTime time) 
	{
		this.creationDateTime = time;
	}

	/**
	 * @param string
	 */
	public void setCreationUser(String string) 
	{
		this.creationUser = string;
	}

	/**
	 * @param time
	 */
	public void setLastupdateDateTime(DateTime time) 
	{
		this.lastupdateDateTime = time;
	}

	/**
	 * @param string
	 */
	public void setLastupdateUser(String string) 
	{
		this.lastupdateUser = string;
	}
	
	public Object clone()
	{
		return new SystemInformation(this.creationUser, this.creationDateTime, this.lastupdateUser, this.lastupdateDateTime);
	}
	
	public SystemInformation copy()
	{
		return (SystemInformation)clone();
	}
	
	public SysInfoBean getBean()
	{
		// FWB-132 NPE
		DateTimeBean creationDateBean = null;
		if (creationDateTime != null)
			creationDateBean = creationDateTime.getBean();
		
		DateTimeBean lastUpdateBean = null;
		if (lastupdateDateTime != null)
			lastUpdateBean = lastupdateDateTime.getBean();
		
		return new SysInfoBean(creationUser, creationDateBean,lastupdateUser, lastUpdateBean);
	}
	public static SysInfoBean[] getBeanArray(SystemInformation[] val)
	{
		SysInfoBean[] beans = new SysInfoBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof SystemInformation))
		{
			return false;
		}
		SystemInformation ov = (SystemInformation)o;
		if (this.getCreationDateTime() != null)
		{
			if (!this.getCreationDateTime().equals(ov.getCreationDateTime()))
				return false;
		}
		if (this.getLastupdateDateTime() != null)
		{
			if (!this.getLastupdateDateTime().equals(ov.getLastupdateDateTime()))
				return false;
		}
		if (this.getCreationUser() != null)
		{
			if (!this.getCreationUser().equals(ov.getCreationUser()))
				return false;
		}
		if (this.getLastupdateUser() != null)
		{
			if (!this.getLastupdateUser().equals(ov.getLastupdateUser()))
				return false;
		}
		return true;
	}
	
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public int compareTo(Object o)
	{
		if (o == null)
			return -1;
		if (!(o instanceof SystemInformation))
			return -1;
		SystemInformation vo = (SystemInformation)o;

		if (this.getCreationDateTime() != null)
		{
			if (this.getCreationDateTime().equals(vo.getCreationDateTime()))
			{
				if (this.getCreationUser() != null)
				{
					return this.getCreationUser().compareTo(vo.getCreationUser());
				}
			}			
			return this.getCreationDateTime().compareTo(vo.getCreationDateTime());			
		}
		else if (this.getCreationUser() != null)
		{
			return this.getCreationUser().compareTo(vo.getCreationUser());
		}
		return 0;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		String comma = "";
		if (this.creationUser != null)
		{
			sb.append("Creating User : " + this.getCreationUser());
			comma = ", ";
		}
		if (this.creationDateTime != null)
		{
			sb.append(comma + "Creation Date : " + this.getCreationDateTime().toString());
			comma = ", ";
		}
		if (this.creationDateTime != null)
		{
			sb.append(comma + "Last Update User : " + this.getLastupdateUser().toString());
			comma = ", ";
		}
		if (this.getLastupdateDateTime() != null)
		{
			sb.append(comma + "Last Update Date : " + this.getLastupdateDateTime().toString());
		}
		return sb.toString();
	}
}