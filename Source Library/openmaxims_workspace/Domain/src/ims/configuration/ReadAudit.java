package ims.configuration;

import java.io.Serializable;
import java.util.HashMap;

public class ReadAudit extends ims.domain.DomainObject implements Serializable
	{
	    private static final long serialVersionUID = 1L;
		public static final int CLASSID = 1204120000;

		private java.util.Date auditDateTime;
		private String auditUser;
		private Integer auditUserLocation;
		private String auditAction;
		private String auditHost;
		private Integer patientId;
		
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
		
		public String getAuditHost()
		{
			return this.auditHost;
		}
		
		public String getAuditAction()
		{
			return this.auditAction;
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
