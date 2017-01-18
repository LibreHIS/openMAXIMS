/*
 * Created on 19-Feb-04
 *
 */
package ims.domain;

/**
 * @author gcoghlan
 *
 */
public class SystemInformation implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
	private java.util.Date creationDateTime;
	private String creationUser;
	private java.util.Date lastUpdateDateTime;
	private String lastUpdateUser;
	
	public java.util.Date getCreationDateTime()
	{
		return this.creationDateTime;
	}

	public String getCreationUser()
	{
		return this.creationUser;
	}

	public java.util.Date getLastUpdateDateTime()
	{
		return this.lastUpdateDateTime;
	}

	public String getLastUpdateUser()
	{
		return this.lastUpdateUser;
	}

	/**
	 * @param date
	 */
	public void setCreationDateTime(java.util.Date date)
	{
		this.creationDateTime = date;
	}

	/**
	 * @param user
	 */
	public void setCreationUser(String user)
	{
		this.creationUser = user;
	}

	/**
	 * @param date
	 */
	public void setLastUpdateDateTime(java.util.Date date)
	{
		this.lastUpdateDateTime = date;
	}

	/**
	 * @param user
	 */
	public void setLastUpdateUser(String user)
	{
		this.lastUpdateUser = user;
	}
	
	public String toString()
	{
		String result="";
		if (this.creationDateTime != null)
		{
			result = "Created:" + this.creationDateTime + " By " + this.creationUser;
		}
		if (this.lastUpdateDateTime != null)
		{
			result = result + " (Last Updated " + this.lastUpdateDateTime + " By " + this.lastUpdateUser + ")";
		}
		return result;
			
	}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof SystemInformation))
			return false;
		
		SystemInformation compObj = (SystemInformation) o;
		if ((compObj.getCreationDateTime() == null && this.getCreationDateTime() != null) ||
			(compObj.getCreationDateTime() != null && this.getCreationDateTime() == null) ||
			(compObj.getCreationDateTime() != null && this.getCreationDateTime() != null && !compObj.getCreationDateTime().equals(this.getCreationDateTime())))
			return false;
		
		if ((compObj.getLastUpdateDateTime() == null && this.getLastUpdateDateTime() != null) ||
			(compObj.getLastUpdateDateTime() != null && this.getLastUpdateDateTime() == null) ||
			(compObj.getLastUpdateDateTime() != null && this.getLastUpdateDateTime() != null && !compObj.getLastUpdateDateTime().equals(this.getLastUpdateDateTime())))
				return false;

		if ((compObj.getCreationUser() == null && this.getCreationUser() != null) ||
			(compObj.getCreationUser() != null && this.getCreationUser() == null) ||
			(compObj.getCreationUser() != null && this.getCreationUser() != null && !compObj.getCreationUser().equals(this.getCreationUser())))
				return false;
		
		if ((compObj.getLastUpdateUser() == null && this.getLastUpdateUser() != null) ||
			(compObj.getLastUpdateUser() != null && this.getLastUpdateUser() == null) ||
			(compObj.getLastUpdateUser() != null && this.getLastUpdateUser() != null && !compObj.getLastUpdateUser().equals(this.getLastUpdateUser())))
				return false;

		return true;
	}
}
