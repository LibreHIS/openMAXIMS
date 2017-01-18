/*
 * Created on 29-Sep-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.configuration;

import java.util.HashMap;

import ims.domain.SystemInformation;

/**
 * @author bworwood
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class RecordedInError extends ims.domain.DomainObject implements java.io.Serializable, ims.domain.SystemInformationRetainer 
{
    private static final long serialVersionUID = 1L;
	public static final int CLASSID = 1204100000;
	
	
	private Integer classIdentifier;
	private int boClassId;  // Business Object Class Id
	private Integer clinContactId;
	private Integer careContextId;
	private Integer patientId;
	private String className;

	private String comment;
	private int formId;  // Form from which this recorded in error record was instantiated
	/** SystemInformation */
	private ims.domain.SystemInformation systemInformation = new ims.domain.SystemInformation();
	
	public RecordedInError (Integer id, int ver)
	{
		super(id, ver);
	}
	public RecordedInError ()
	{
	  	super();
	}
	
	public static boolean isConfigurationObject()
	{
		return false;
	}
	
	public Integer getClassIdentifier()
	{
		return this.classIdentifier;
	}
	
	public void setClassIdentifier(Integer id)
	{
		this.classIdentifier = id;
	}
	
	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public int getFormId()
	{
		return formId;
	}

	public void setFormId(int formId)
	{
		this.formId = formId;
	}

	public String getClassName()
	{
		return this.className;
	}
	
	public void setClassName(String name)
	{
		this.className = name;
	}
	
	public int getBoClassId()
	{
		return boClassId;
	}

	public void setBoClassId(int boClassId)
	{
		this.boClassId = boClassId;
	}

	public Integer getClinContactId()
	{
		return clinContactId;
	}

	public void setClinContactId(Integer clinContactId)
	{
		this.clinContactId = clinContactId;
	}

	public Integer getPatientId()
	{
		return patientId;
	}

	public void setPatientId(Integer patientId)
	{
		this.patientId = patientId;
	}


	public int getClassId() {
		return CLASSID;
	}

	public SystemInformation getSystemInformation() 
	{
		if (systemInformation == null) systemInformation = new ims.domain.SystemInformation();
		return systemInformation;
	}
	public Integer getCareContextId()
	{
		return careContextId;
	}
	public void setCareContextId(Integer careContextId)
	{
		this.careContextId = careContextId;
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
		return RecordedInError.class;
	}
	public static String[] getCollectionFields()
	{
		return null;
	}

}
