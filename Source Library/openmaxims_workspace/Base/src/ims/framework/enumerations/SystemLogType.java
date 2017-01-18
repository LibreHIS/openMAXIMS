package ims.framework.enumerations;

import java.io.Serializable;

public class SystemLogType implements Serializable
{
	private static final long serialVersionUID = 1L;
	 
	private int id;
	private String text;
	
	private static SystemLogType UNKNOWN = new SystemLogType(10000, "Unknown");
	public static SystemLogType APPLICATION = new SystemLogType(10001, "Application");
	public static SystemLogType AUTHENTICATION = new SystemLogType(10002, "Authentication");
	public static SystemLogType REPORTS = new SystemLogType(10003, "Reports");
	public static SystemLogType KIOSK = new SystemLogType(10004, "Kiosk");		
	public static SystemLogType SCANNING = new SystemLogType(10005, "Scanning");
	public static SystemLogType FILE_UPLOADING = new SystemLogType(10006, "File Uploading");
	public static SystemLogType HL7 = new SystemLogType(10007, "HL7");
	public static SystemLogType RULES_ENGINE = new SystemLogType(10008, "Rules Engine");
	public static SystemLogType PATIENT_MERGE = new SystemLogType(10009, "Patient Merge");
	public static SystemLogType CDS = new SystemLogType(10010, "Clinical Data Set");
	public static SystemLogType QUARTZ_JOB = new SystemLogType(10011, "Scheduled Jobs Engine");
	public static SystemLogType WEB_SERVICE = new SystemLogType(10012, "Web Service");
	public static SystemLogType JSCN = new SystemLogType(10013, "Web Client");
	public static SystemLogType NOTIFICATIONS_ENGINE = new SystemLogType(10014, "Notification Engine");
	public static SystemLogType INTEGRATION_ENGINE = new SystemLogType(10015, "Integration Engine (Evolve, Oceano ED)");
	public static SystemLogType CONFIGURATION_FLAG = new SystemLogType(10016, "Configuration Flag");
	public static SystemLogType DEPLOY_REPORTS = new SystemLogType(10017, "Deploy Reports");
	public static SystemLogType DEMENTIA_PROCESSING = new SystemLogType(10018, "Dementia Processing"); // FWB-428
	public static SystemLogType PDS = new SystemLogType(10019, "Personal Demographics Service"); // FWUI-1840
	public static SystemLogType PostCodeLookup = new SystemLogType(10020, "Post Code Lookup"); // FWb-570
	public static SystemLogType SDS = new SystemLogType(10021, "SDS"); // FWb-611

	public static SystemLogType[] ALL = new SystemLogType[] {
		
		APPLICATION,
		AUTHENTICATION, 	
		REPORTS,
		KIOSK, 
		SCANNING,
		FILE_UPLOADING,
		HL7,
		RULES_ENGINE,
		PATIENT_MERGE,
		CDS,
		QUARTZ_JOB,
		WEB_SERVICE,
		JSCN,
		NOTIFICATIONS_ENGINE,
		INTEGRATION_ENGINE,
		CONFIGURATION_FLAG,
		DEPLOY_REPORTS,
		DEMENTIA_PROCESSING,
		PDS,
		PostCodeLookup,
		SDS
		}; 
	
	private SystemLogType(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	public int getId()
	{
		return id;
	}
	public String getText()
	{
		return text;
	}
	public String toString()
	{
		return text + " (ID: " + id + ")";
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof SystemLogType)
			return ((SystemLogType)obj).id == id;
		return false;
	}
	public static SystemLogType getInstance(int id)
	{
		for(int x = 0; x < ALL.length; x++)
		{
			if(ALL[x].id == id)
				return ALL[x];
		}
		
		return UNKNOWN;
	}
}
