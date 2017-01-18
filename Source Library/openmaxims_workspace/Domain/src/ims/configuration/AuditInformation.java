/*
 * Created on 01-Oct-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.configuration;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author bworwood
 *
 * AuditInformation class used by DomainObjects to help record
 * database changes.
 **/

public class AuditInformation extends ims.domain.DomainObject implements Serializable
{
    private static final long serialVersionUID = 1L;
	public static final int CLASSID = 1204100000;

	private java.util.Date auditDateTime;
	private String auditUser;
	private Integer auditRoleId;
	private Integer auditUserLocation;
	private String auditAction;
	private String auditHost;
	private Integer classIdentifier;
	private String className;
	private String diffString; 
	private Integer patientId;
	private Integer contactId;
	private Integer careContextId;
	
	public static boolean isConfigurationObject()
	{
		return false;
	}
	
	public java.util.Date getAuditDateTime()
	{
		return this.auditDateTime;
	}

	public Integer getAuditUserLocation()
	{
		return auditUserLocation;
	}

	public void setAuditUserLocation(Integer auditUserLocation)
	{
		this.auditUserLocation = auditUserLocation;
	}

	public String getAuditUser()
	{
		return this.auditUser;
	}
	
	public Integer getAuditRoleId()
	{
		return auditRoleId;
	}

	public void setAuditRoleId(Integer auditRoleId)
	{
		this.auditRoleId = auditRoleId;
	}

	public String getAuditHost()
	{
		return this.auditHost;
	}
	
	public String getAuditAction()
	{
		return this.auditAction;
	}
	

	public Integer getCareContextId()
	{
		return careContextId;
	}

	public void setCareContextId(Integer careContextId)
	{
		this.careContextId = careContextId;
	}

	public Integer getClassIdentifier()
	{
		return this.classIdentifier;
	}
	
	public String getClassName()
	{
		return this.className;
	}
	
	public String getDiffString()
	{
		return this.diffString;
	}
	
	public void setDiffString(String diffString)
	{
		this.diffString = diffString;
	}

	/**
	 * @param date
	 */
	public void setAuditDateTime(java.util.Date date)
	{
		this.auditDateTime = date;
	}

	/**
	 * @param user
	 */
	public void setAuditUser(String user)
	{
		this.auditUser = user;
	}
	
	public void setAuditHost(String host)
	{
		this.auditHost = host;
	}

	public void setAuditAction(String action)
	{
		this.auditAction = action;
	}
	
	
	public void setClassIdentifier(Integer id)
	{
		this.classIdentifier = id;
	}
	
	public void setClassName(String name)
	{
		this.className = name;
	}


	public int getClassId() {
		return CLASSID;
	}

	public Integer getPatientId()
	{
		return this.patientId;
	}
	
	public void setPatientId(Integer id)
	{
		this.patientId = id;
	}
	
	public Integer getContactId()
	{
		return this.contactId;
	}
	
	public void setContactId(Integer id)
	{
		this.contactId = id;
	}

	public String toDiffString()
	{
		return null;
		
	}

	public String toAuditString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getClassVersion()
	{
		return "" + CLASSID;
	}

	public String toXMLString()
	{
		return "";
	}

	public String toXMLString(HashMap domMap)
	{
		return "";
	}

	public Class getRealDomainClass()
	{
		return AuditInformation.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}	
	
}
