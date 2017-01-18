package ims.configuration;

import java.io.FileNotFoundException;
import java.io.InputStream;

import ims.framework.VersionInfo;
import ims.framework.enumerations.UITheme;
import ims.framework.enumerations.application.AssessmentRecordingLevel;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class InitConfig 
{
	private static ConfigType configType = ConfigType.HIB;
	private static String hibernateVersion = "2";
	private static String flagFileLocation = "";
	private static Integer printFormId;
	private static Integer recordedInErrorFormId;
	private static Integer auditViewFormId;	
	private static Integer recordingOnBehalfOfFormId;
	private static Integer locationsManagerFormId;
	private static Integer locationsSelectionFormId;
	private static Integer pasContactsFormId;
	private static Integer orderEntryFormId;
	private static Integer patientSummaryFormId;
	private static Integer assessmentContainerFormId;
	private static Integer reportViewerFormId;
	private static Integer passwordPromptDialogId;
	private static Integer contextViewerDialogId;
	private static Integer userPasswordChangeDialogId;
	private static boolean initCfgLoaded = false;
	private static boolean tabLayout = false;
	private static String locationProviderClassName;
	private static String smsSenderProviderClassName;
	private static String pathwayEntityProviderClassName = "ims.pathways.helper.PathwayEntityProvider";
	private static String userProviderClassName = "ims.admin.helper.UserProvider";
	private static String roleProviderClassName = "ims.admin.helper.RoleProvider";
	private static String formProviderClassName = "ims.admin.helper.FormProvider";
	private static String imageProviderClassName = "ims.admin.helper.ImageProvider";
	private static String rulesProviderClassName = "";
	private static String configFlagsProviderClassName = "";
	private static String contextEvalProviderClassName = "ims.core.helper.ContextEvalProvider";
	private static String contextSetterClassName = "ims.admin.helper.ContextSetter";
	private static String projectName = "";		
	private static String uploadDownloadUrlProviderClassName = "";
	private static String securityTokenProviderClassName = "";
	private static String externalEncodingProviderClassName = "";
	private static String scheduledJobsProviderClassName = "";
	private static String securityTokenHandlerProviderClassName = "";
	private static String dictionaryProviderClassName = "";
	private static String localSettingsProviderClassName = "";
	private static String printersProviderClassName = "";
	private static String screenHintProviderClassName = "";
	private static String addressProviderClassName = "";
	private static String appParamHandlerProviderClassName = "";
	private static String systemLogProviderClassName = "";
	private static String notificationsProviderClassName = "";
	private static boolean search = false;
	private static Integer spellCheckerId;
	private static String iCABConfigurationFile = "";
	private static boolean collapsibleNavigation = false;
	private static Integer rulesEngine;	 
	private static Integer assessmentRecordingLevel;
	private static UITheme theme = UITheme.NewBlue;
	
	private static VersionInfo frameworkVersion;	
	private static VersionInfo domainVersion;
	private static VersionInfo baseVersion;
	
	public static void loadInitConfig()
	{
		if (initCfgLoaded) return;

		InputStream init = Thread.currentThread().getContextClassLoader().getResourceAsStream("init_config.xml");
		try
		{
		if(init==null)
		{
				throw new FileNotFoundException("The file init_config.xml was not found. It should be found in \\webapp\\web-inf\\classes");
		}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		InputSource in = new InputSource(init);
		
		
			
		if (in != null)
		{
			org.dom4j.Document doc;
			try 
			{
				doc = new SAXReader().read(in);
				
				Element el = (Element) doc.selectSingleNode( "/init_config/flags_file_location" );
				if (el != null) 
					flagFileLocation = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/config_type" );
				if (el != null)
				{
					if(el.getText().toUpperCase().equals("XML"))
						configType = ConfigType.XML;
					if(el.getText().toUpperCase().equals("DTO"))
						configType = ConfigType.DTO;
				}
				
				el = (Element) doc.selectSingleNode( "/init_config/hibernate_version" );
				if (el != null) 
					hibernateVersion = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/print_form_id" );
				if (el != null) 
					printFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/rie_form_id" );
				if (el != null) 
					recordedInErrorFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/audit_form_id" );
				if (el != null) 
					auditViewFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/recording_on_behalf_of_form_id" );
				if (el != null) 
					recordingOnBehalfOfFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/location_manager_form_id" );
				if (el != null) 
					locationsManagerFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/ICABConfigurationFile" );
				if (el != null) 
					iCABConfigurationFile = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/location_selection_form_id" );
				if (el != null) 
					locationsSelectionFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/pas_contacts_form_id" );
				if (el != null) 
					pasContactsFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/order_entry_form_id" );
				if (el != null) 
					orderEntryFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/patient_summary_form_id" );
				if (el != null) 
					patientSummaryFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/assessmentContainerFormId" );
				if (el != null) 
					assessmentContainerFormId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/reportViewerFormId" );
				if (el != null) 
					reportViewerFormId = new Integer(el.getText());

				el = (Element) doc.selectSingleNode( "/init_config/passwordPromptDialogId" );
				if (el != null) 
					passwordPromptDialogId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/userPasswordChangeDialogId" );
				if (el != null) 
					userPasswordChangeDialogId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/context_viewer_dialog_id" );
				if (el != null) 
					contextViewerDialogId = new Integer(el.getText());

				el = (Element) doc.selectSingleNode( "/init_config/tab_layout" );
				if (el != null) 
					tabLayout = el.getText().equals("1"); 
				
				el = (Element) doc.selectSingleNode( "/init_config/locationProviderClassName" );
				if (el != null) 
					locationProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/SMSSenderProviderClassName" );
				if (el != null) 
					smsSenderProviderClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/userProviderClassName" );
				if (el != null) 
					userProviderClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/roleProviderClassName" );
				if (el != null) 
					roleProviderClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/formProviderClassName" );
				if (el != null) 
					formProviderClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/imageProviderClassName" );
				if (el != null) 
					imageProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/rulesProviderClassName" );
				if (el != null) 
					rulesProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/configFlagsProviderClassName" );
				if (el != null) 
					configFlagsProviderClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/contextEvalProviderClassName" );
				if (el != null) 
					contextEvalProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/addressProviderClassName" );
				if (el != null) 
					addressProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/appParamHandlerProviderClassName" );
				if (el != null) 
					appParamHandlerProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/systemLogProviderClassName" );
				if (el != null) 
					systemLogProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/notificationsProviderClassName" );
				if (el != null) 
					notificationsProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/contextSetterClassName" );
				if (el != null) 
					contextSetterClassName = el.getText();

				el = (Element) doc.selectSingleNode( "/init_config/projectName" );
				if (el != null) 
					projectName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/frameworkVersion" );
				if (el != null) 
					frameworkVersion = new ims.framework.VersionInfo(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/domainVersion" );
				if (el != null) 
					domainVersion = new ims.framework.VersionInfo(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/baseVersion" );
				if (el != null) 
					baseVersion = new ims.framework.VersionInfo(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/securityTokenProviderClassName" );
				if (el != null) 
					securityTokenProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/uploadDownloadUrlProviderClassName" );
				if (el != null) 
					uploadDownloadUrlProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/externalEncodingProviderClassName" );
				if (el != null) 
					externalEncodingProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/scheduledJobsProviderClassName" );
				if (el != null) 
					scheduledJobsProviderClassName = el.getText();						

				el = (Element) doc.selectSingleNode( "/init_config/securityTokenHandlerProviderClassName" );
				if (el != null) 
					securityTokenHandlerProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/dictionaryProviderClassName" );
				if (el != null) 
					dictionaryProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/pathwayEntityProviderClassName" );
				if (el != null) 
					pathwayEntityProviderClassName = el.getText();
				
				
				el = (Element) doc.selectSingleNode( "/init_config/localSettingsProviderClassName" );
				if (el != null) 
					localSettingsProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/printersProviderClassName" );
				if (el != null) 
					printersProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/screenHintProviderClassName" );
				if (el != null) 
					screenHintProviderClassName = el.getText();
				
				el = (Element) doc.selectSingleNode( "/init_config/spellChecker" );
				if (el != null) 
					spellCheckerId = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/search" );
				if (el != null) 
					search = el.getText().equals("1");
				
				el = (Element) doc.selectSingleNode( "/init_config/collapsibleNavigation" );
				if (el != null) 
					collapsibleNavigation = el.getText().equals("1");
				
				el = (Element) doc.selectSingleNode( "/init_config/rulesEngine" );
				if (el != null) 
					rulesEngine = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/assessmentRecordingLevel" );
				if (el != null) 
					assessmentRecordingLevel = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/assessmentRecordingLevel" );
				if (el != null) 
					assessmentRecordingLevel = new Integer(el.getText());
				
				el = (Element) doc.selectSingleNode( "/init_config/defaultTheme" );
				if (el != null) 
					theme = UITheme.parse(new Integer(el.getText()));
				
				initCfgLoaded = true;
			}
			
			catch (DocumentException e) 
			{
				e.printStackTrace();
				
			}
		}

	}

	public static VersionInfo getFrameworkVersion() 
	{
		return frameworkVersion;
	}
	public static VersionInfo getDomainVersion() 
	{
		return domainVersion;
	}
	public static VersionInfo getBaseVersion() 
	{
		return baseVersion;
	}
	public static ConfigType getConfigType() 
	{
		return configType;
	}
	public static String getFlagFileLocation() 
	{
		//JME: 20061211: A way of forcing the override of the value in init_config.xml.
		//This enables a war file to be used elsewhere without having to modify any expanded contents
		if (System.getProperty("ims.flags.file.location") != null)
			return System.getProperty("ims.flags.file.location");
		
		return flagFileLocation;
	}
	public static String getHibernateVersion() 
	{
		return hibernateVersion;
	}
	public static Integer getPrintFormId() 
	{
		return printFormId;
	}
	public static Integer getRecordedInErrorFormId() 
	{
		return recordedInErrorFormId;
	}
	public static Integer getAuditViewFormId() 
	{
		return auditViewFormId;
	}
	public static Integer getRecordingOnBehalfOfFormId() 
	{
		return recordingOnBehalfOfFormId;
	}
	public static Integer getLocationsManagerFormId() 
	{
		return locationsManagerFormId;
	}
	public static Integer getLocationsSelectionFormId() 
	{
		return locationsSelectionFormId;
	}
	public static Integer getPASContactsFormId() 
	{
		return pasContactsFormId;
	}
	public static Integer getOrderEntryFormId() 
	{
		return orderEntryFormId;
	}
	public static Integer getPatientSummaryFormId() 
	{
		return patientSummaryFormId;
	}
	public static boolean getIsTabLayout() 
	{
		return tabLayout;
	}
	public static String getLocationProviderClassName() 
	{
		return locationProviderClassName;
	}
	public static String getSMSSenderProviderClassName() 
	{
		return smsSenderProviderClassName;
	}
	public static String getProjectName()
	{
		return projectName;
	}
	public static String getScheduledJobsProviderClassName()
	{
		return scheduledJobsProviderClassName;
	}
	public static String getRoleProviderClassName()
	{
		return roleProviderClassName;
	}

	public static String getUserProviderClassName()
	{
		return userProviderClassName;
	}

	public static String getContextEvalProviderClassName()
	{
		return contextEvalProviderClassName;
	}

	public static String getContextSetterClassName()
	{
		return contextSetterClassName;
	}
	
	public static String getFormProviderClassName()
	{
		return formProviderClassName;
	}

	public static String getImageProviderClassName()
	{
		return imageProviderClassName;
	}
	
	public static String getRulesProviderClassName()
	{
		return rulesProviderClassName;
	}

	public static String getConfigFlagsProviderClassName()
	{
		return configFlagsProviderClassName;
	}

	public static Integer getAssessmentContainerFormId()
	{
		return assessmentContainerFormId;
	}
	
	public static Integer getReportViewerFormId()
	{
		return reportViewerFormId;
	}

	public static Integer getPasswordPromptDialogId()
	{
		return passwordPromptDialogId;
	}
	
	public static Integer getUserPasswordChangeDialogId()
	{
		return userPasswordChangeDialogId;
	}	
	
	public static Integer getContextViewerDialogId()
	{
		return contextViewerDialogId;
	}
	public static String getSecurityTokenProviderClassName()
	{
		return securityTokenProviderClassName;
	}
	public static String getExternalEncodingProviderClassName()
	{
		return externalEncodingProviderClassName;
	}
	public static String getUploadDownloadUrlProviderClassName()
	{
		return uploadDownloadUrlProviderClassName;
	}
	public static String getSecurityTokenHandlerProviderClassName()
	{
		return securityTokenHandlerProviderClassName;
	}
	public static String getDictionaryProviderClassName()
	{
		return dictionaryProviderClassName;
	}
	public static String getLocalSettingsProviderClassName()
	{
		return localSettingsProviderClassName;
	}
	public static String getPrintersProviderClassName()
	{
		return printersProviderClassName;
	}
	public static String getScreenHintProviderClassName()
	{
		return screenHintProviderClassName;
	}
	public static String getAddressProviderClassName()
	{
		return addressProviderClassName;
	}	
	public static String getAppParamHandlerProviderClassName()
	{
		return appParamHandlerProviderClassName;
	}
	public static String getSystemLogProviderClassName()
	{
		return systemLogProviderClassName;
	}
	public static String getNotificationsProviderClassName()
	{
		return notificationsProviderClassName;
	}	
	public static boolean isSearchEnabled()
	{
		return search;
	}
	public static boolean isNavigationCollapsible()
	{
		return collapsibleNavigation;
	}
	public static Integer getSpellCheckerId()
	{
		return spellCheckerId;
	}
	public static String getICABConfigurationFile()
	{
		return iCABConfigurationFile;
	}
	public static RulesEngine getRulesEngine()
	{
		return RulesEngine.parse(rulesEngine);
	}
	public static AssessmentRecordingLevel getAssessmentRecordingLevel()
	{
		if(assessmentRecordingLevel == null)
			return null;
		
		return AssessmentRecordingLevel.parse(assessmentRecordingLevel);		
	}
	public static UITheme getTheme()
	{
		return theme;
	}

	public static String getPathwayEntityProviderClassName() 
	{
		return pathwayEntityProviderClassName;
	}
}
