package ims.configuration;

/**
 * ConfigurationItems class to be used in conjunction with the ConfigProperty
 * object in DevEnv.  New status methods required for each use.
 * @author bworwood
 *
 */
public class ConfigItems
{
		// Provider Systems
		public static final String SendADT = "SendADTBeforeORM";
		public static final String Hl7Facility = "Hl7Facility";
		public static final String ProcessingID = "ProcessingID";
		public static final String EnableDemographicMatching = "EnableDemographicMatching";
		public static final String EnablePatientRegistration = "EnablePatientRegistration";
		public static final String SendOCSStatusMessages="SendOCSStatusMessages";
		public static final String SendA10 = "SendA10Messages";
		public static final String SendOMG = "SendClinicalOrderMessages";
		public static final String GenericClinicCode = "GenericClinicCode";
		public static final String UseGpAsOrderingProvider="UseGpAsOrderingProvider";
//		public static final String SendDeomgraphicFeed="SendDeomgraphicFeed";
		public static final String SendA40onMerge="SendA40onMerge";
		public static final String SendQAsAsOBXs="SendQAsAsOBXinOrders";
		public static final String SupportSendNumber="SupportSendNumber";
		public static final String useSiteAndSource="useSiteAndSource";
/*		public static final String SendDocumentManagementMessages="SendDocumentManagementMessages";*/
		public static final String RebookApptWithCancelandFullXO="RebookApptWithCancelandFullXO";
		public static final String XOasNWforApptRebookNonDiagnostic="XOasNWforApptRebookNonDiagnostic";
		public static final String AutoCheckGPResults="AutoCheckGPResults";
		public static final String NHSNumberasSecondaryIdentifier="NHSNumberasSecondaryIdentifier";
		public static final String NTERemoveDuplicateQAs="NTERemoveDuplicateQAs";
		public static final String UseLineBreakBetweenQAs="UseLineBreakBetweenQAs";
		public static final String ClinicalImagingSeparateNTEs="ClinicalImagingSeparateNTEs";
		public static final String SecondaryID="SecondaryID";
		public static final String forwardHL7DemographicUpdates="forwardHL7DemographicUpdates";
		public static final String PreAdmitADTOnFirstAppointment="PreAdmitADTOnFirstAppointment";
		public static final String IncludeCCGCodeinPD1="IncludeCCGCodeinPD1";
		public static final String RenderTSAsISO_SECS="RenderTSAsISO_SECS";
		public static final String IncludeBayInPatientLocaton="IncludeBayInPatientLocaton";
		public static final String TimeOfBirthInPID="TimeOfBirthInPID";
		public static final String TimeOfDeathInPID="TimeOfDeathInPID";
		public static final String PracticeNameInPD1="PracticeNameInPD1";
	
		private String propertyName;
		private String propertyValue;
		
		private static final String[] ProviderSystemsConfig
		= {SendADT, Hl7Facility, ProcessingID, EnableDemographicMatching, EnablePatientRegistration, SendOCSStatusMessages, SendA10, SendOMG, GenericClinicCode,UseGpAsOrderingProvider,SendA40onMerge,SendQAsAsOBXs,SupportSendNumber,useSiteAndSource,/*SendDocumentManagementMessages,*/RebookApptWithCancelandFullXO,XOasNWforApptRebookNonDiagnostic,AutoCheckGPResults,NHSNumberasSecondaryIdentifier,NTERemoveDuplicateQAs,UseLineBreakBetweenQAs,ClinicalImagingSeparateNTEs,SecondaryID,forwardHL7DemographicUpdates,PreAdmitADTOnFirstAppointment,IncludeCCGCodeinPD1,RenderTSAsISO_SECS,IncludeBayInPatientLocaton,TimeOfBirthInPID,TimeOfDeathInPID,PracticeNameInPD1};

		public static String[] getProviderSystemsConfigItems()
		{
			return ProviderSystemsConfig;
		}

		public ConfigItems (String propertyName, String propertyValue)
		{
			this.propertyName = propertyName;
			this.propertyValue = propertyValue;
		}
		
		public String getPropertyValue()
		{
			return propertyValue;
		}
		
		public String getPropertyName()
		{
			return propertyName;
		}
}


