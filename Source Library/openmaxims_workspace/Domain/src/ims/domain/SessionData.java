package ims.domain;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.configuration.EnvironmentConfig;
import ims.configuration.InitConfig;
import ims.framework.Alert;
import ims.framework.Context;
import ims.framework.ExternalApplication;
import ims.framework.InternalAlert;
import ims.framework.MessageBox;
import ims.framework.SessionConstants;
import ims.framework.Url;
import ims.framework.UserAgent;
import ims.framework.enumerations.FormMode;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.UILayoutState;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.interfaces.IAppParam;
import ims.framework.interfaces.IAppRole;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.IDynamicNavigation;
import ims.framework.interfaces.ILocation;
import ims.framework.interfaces.ISearchResult;
import ims.framework.interfaces.ISelectedPatient;
import ims.framework.utils.SizeInfo;

import org.apache.log4j.Logger;

/**
 * @author mmihalec
 */
public final class SessionData implements Serializable
{	
	private static final long serialVersionUID = 1L;

	public SessionData()
	{	
		HashMap<String, Object> session = new HashMap<String, Object>();
		
		this.previouslySelectedPatients = new GenericDataTypeCollection<ArrayList<ISelectedPatient>>(session, "Previously Selected Patients");
		this.sessionId = new GenericDataType<String>(session, "Session Id");
		this.alerts = new GenericDataTypeCollection<ArrayList<Alert>>(session, "Alerts");	    
		this.dialogOpened = new GenericDataType<Boolean>(session, "Dialog Opened");
		this.uploadDebug = new GenericDataType<Boolean>(session, "Upload Debug");
		this.uploadUrl = new GenericDataType<String>(session, "Upload Url");
		this.emailAddressToOpen = new GenericDataType<String>(session, "Email address to open");
		this.urlToOpen = new GenericDataTypeCollection<ArrayList<Url>>(session, "Url to open");
		this.urlToClose = new GenericDataTypeCollection<ArrayList<Integer>>(session, "Url id to close");
		this.urlToCloseOnContextChanged = new GenericDataTypeCollection<ArrayList<Integer>>(session, "Url id to close on context changed");
		this.customUrlToOpen = new GenericDataTypeCollection<ArrayList<Url>>(session, "Custom URL to open using ActiveX");
		this.currentCustomUrlToOpenID = new GenericDataType<Integer>(session, "Current custom Url To Open ID");
		this.filesToDelete = new GenericDataTypeCollection<ArrayList<String>>(session, "Files to Delete");
		this.externalApplicationToRun = new GenericDataTypeCollection<ArrayList<ExternalApplication>>(session, "External Applications To Run");
		this.showCloseButtonForDialog = new GenericDataType<Boolean>(session, "Show Close Button for Dialog");
		this.showResizableDialog = new GenericDataType<Boolean>(session, "Show Resizable Dialog");
		this.formParams = new GenericDataType<Object[]>(session, "Form parameters");
		this.captionOverride = new GenericDataType<String>(session, "Caption override");
		this.changeTheme = new GenericDataType<String>(session, "Change theme");		
		this.closeForm = new GenericDataType<Boolean>(session, "Close form");
		this.context = new GenericDataType<Context>(session, "Context");
		this.currentFormID = new GenericDataType<Integer>(session, "Current Form ID");
		this.currentFormMode = new GenericDataType<FormMode>(session, "Current Form Mode");		
		this.currentDynamicNavigation = new GenericDataType<IDynamicNavigation>(session, "Current Dynamic Navigation");	
		this.dialogID = new GenericDataType<Integer>(session, "Dialog ID");
		this.dialogResult = new GenericDataType<DialogResult>(session, "Dialog Result");		
		this.dialogResults = new GenericDataTypeCollection<ArrayList<DialogResult>>(session, "Dialog results");		
		this.domainSession = new GenericDataType<DomainSession>(session, SessionConstants.DOMAIN_SESSION);
		this.errors = new GenericDataTypeCollection<ArrayList>(session, "Errors");
		this.errorsTitle = new GenericDataType<String>(session, "ErrorsTitle");	    
		this.fireOpenEvent = new GenericDataType<Boolean>(session, "Fire Open Event");
		this.formsData = new GenericDataType<HashMap>(session, "Forms data");
		this.nextMessageBoxIdentifier = new GenericDataType<Integer>(session, "Next Message Box Identifier");
		this.messageBox = new GenericDataTypeCollection<ArrayList<String>>(session, "MessageBox");
		this.messageBoxes = new GenericDataTypeCollection<ArrayList<MessageBox>>(session, "Message Boxes");
		this.domainMessageBox = new GenericDataTypeCollection<ArrayList<String>>(session, "DomainMessageBox");
		this.newAlerts = new GenericDataTypeCollection<ArrayList<Alert>>(session, "NewAlerts");
		this.openForm = new GenericDataType<Integer>(session, "Open Form");
		this.openDialogCallerIdentifiers = new GenericDataTypeCollection<ArrayList<String>>(session, "Open Form Caller Identifiers");
		this.patientInfo = new GenericDataType<String>(session, "Patient Info");
		this.patientId = new GenericDataType<Integer>(session, "Patient ID");
		this.patientInfoTextColor = new GenericDataType<String>(session, "Patient Info Text Color");
		this.previousForm = new GenericDataType<Integer>(session, "Previous Form");
		this.previousFormWasClosed = new GenericDataType<Object>(session, "Previous Form Was Closed");
		this.previousNonDialogFormID = new GenericDataType<Integer>(session, "Previous Non Dialog Form ID");
		this.currentNonDialogFormID = new GenericDataType<Integer>(session, "Current Non Dialog Form ID");
		this.previousForms = new GenericDataTypeCollection<ArrayList<Integer>>(session, "Previous forms");
		this.previousModes = new GenericDataTypeCollection<ArrayList<FormMode>>(session, "Previous modes");
		this.removedAlerts = new GenericDataTypeCollection<ArrayList<Alert>>(session, "RemovedAlerts");
		this.role = new GenericDataType<IAppRole>(session, "Role");		
		this.user = new GenericDataType<IAppUser>(session, "User");
		this.xmlLogger = new GenericDataType<Logger>(session, "XMLLogger");
		this.listRIERecordsOnly = new GenericDataType<Boolean>(session, "IncludeRIE");
		this.listRIERecordsOnly.set(Boolean.FALSE);
		this.rieBoClassName = new GenericDataType<String>(session, "RIEBoClassName");
		this.listRecordInfo = new GenericDataType<HashMap>(session, "ListRecordInfo");
		this.listRecordInfo.set(new HashMap());
		this.configurationModule = new GenericDataType<Configuration>(session, "ConfigurationModule");		
		this.selectedLocation = new GenericDataType<ILocation>(session, "Selected Location");
		this.storedLocations = new LocationArrayType(session, "Stored Locations");
		this.sendStoredLocations = new GenericDataType<Boolean>(session, "Send Stored Locations");
		this.runtimeClientFormArea = new GenericDataType<SizeInfo>(session, "Runtime Client Form Area");
		this.currentUILayoutState = new GenericDataType<UILayoutState>(session, "Current UI Layout State");
		this.navigationCollapsed = new GenericDataType<Boolean>(session, "Navigation Collapsed");
		this.uiLayoutChanged = new GenericDataType<Boolean>(session, "UI Layout Changed");
		this.startupMode = new GenericDataType<Boolean>(session, "Startup Mode");
		this.progressBarText = new GenericDataType<String>(session, "Progress Bar Text");
		this.formDarkHeaderHeight = new GenericDataType<Integer>(session, "Form Dark Header Height");
		this.systemPasswordEntered = new GenericDataType<Boolean>(session, "System Password Entered");
		this.systemFormToOpen = new GenericDataType<Integer>(session, "System Form To Open");
		this.topButtons = new GenericDataType<Object>(session, "Top buttons");
		this.userAgent = new GenericDataType<UserAgent>(session, "User Agent");
		//this.domain = new GenericDataType<Object>(session, "Domain");
		this.autoLockSent = new GenericDataType<Boolean>(session, "Auto Lock Sent");
		this.autoLockInterval = new GenericDataType<Integer>(session, "Auto Lock Interval");		
		this.heartBeatInterval = new GenericDataType<Integer>(session, "Heart Beat Sent");
		this.heartBeatCounter = new GenericDataType<Integer>(session, "Heart Beat Counter");
		this.externalNotifications = new GenericDataTypeCollection<ArrayList<String>>(session, "External Notifications");
		this.passwordSalt = new GenericDataType<String>(session, "Password Salt");
		this.lastSearchResults = new GenericDataType<ISearchResult[]>(session, "Last Search Results");
		this.lastSearchText = new GenericDataType<String>(session, "Last Search Text");
		this.appParams = new GenericDataType<IAppParam[]>(session, "Application Parameters");
		this.uniqueClientId = new GenericDataType<String>(session, "Unique Client Id");
		this.clientSesionTimeout = new GenericDataType<HashMap>(session, "Application Roles Timeouts");
		this.clientAutolockTimer = new GenericDataType<HashMap>(session, "Application Roles Autolock timer");
		
		//Web Services	
		this.securityTokenParameters = new GenericDataType<HashMap>(session, "Security Token Parameters");
		this.restrictPatientAccess = new GenericDataType<String>(session, "Restrict Patient Access");
		this.restrictPatientId = new GenericDataType<Integer>(session, "Restrict Access To Patient Id");			
		this.securityTokenPatientIdentifierType = new GenericDataType<String>(session, "Patient Identifier Type");
		this.securityTokenPatientIdentifierValue = new GenericDataType<String>(session, "Patient Identifier Value");
		this.securityTokenLaunchUsername = new GenericDataType<String>(session, "Security Token Launch Username");
		this.securityTokenLaunchPassword = new GenericDataType<String>(session, "Security Token Launch Password");
		this.defaultStartupForm = new GenericDataType<Integer>(session, "Default Startup Form");
		
		//WebService Data
		this.appointmentStartDateTime = new GenericDataType<String>(session, "Appointment Start Date Time");
		this.appointmentEndDateTime = new GenericDataType<String>(session, "Appointment End Date Time");
		this.appointmentLocationCode = new GenericDataType<String>(session, "Appointment Location Code");
		this.appointmentConsultantCode = new GenericDataType<String>(session, "Appointment Consultant Code");
				
		this.copyToClipboard = new GenericDataType<StringBuilder>(session, "Text to be copied to user clipboard");
		this.clearClipboard = new GenericDataType<Boolean>(session, "Clear user clipboard");
		
		// FWUI-1770 Patient RIP / Episode Ended
		this.isPatientRIP = new GenericDataType<Boolean>(session, "Patient RIP");
		this.isEpisodeEnded = new GenericDataType<Boolean>(session, "Episode Ended");
		
		this.loginAttempts = new GenericDataType<Integer>(session, "Number of times in succession a user can make an incorrect login before their account is locked");
		
		this.medicodeInputFile = new GenericDataType<String>(session, "Medicode input file");
		
		//PDS stuff
		this.smartcardTicketId = new GenericDataType<String>(session, "Smartcard ticket ID");
		this.samlXml = new GenericDataType<String>(session, "SAML Xml");
		
		initializeWithDefaultValues();		
	}
	
	private void initializeWithDefaultValues()
	{
		initializePasswordSalt();
		//FWUI-1805
		uploadUrl.set("Upload");
		currentUILayoutState.set(UILayoutState.DEFAULT);
		uiLayoutChanged.set(true);
		navigationCollapsed.set(false);		
		currentCustomUrlToOpenID.set(Integer.valueOf(0));
		clientSesionTimeout.set(new HashMap());
		clientAutolockTimer.set(new HashMap());
		this.loginAttempts.set(Integer.valueOf(0));
	}
	public void initializePasswordSalt()
	{
		// when in release mode, we enable password encryption
		if(InitConfig.getConfigType() == ims.configuration.ConfigType.XML || EnvironmentConfig.getUseSecureAuthentication())
		{
			this.passwordSalt.set(generatePasswordSalt());			
		}
	}
	private String generatePasswordSalt()
	{
		String str = "";
	
		try
		{
			str += Long.toHexString(System.currentTimeMillis());
			while (str.length () < 12)
			{
				str = '0' + str;
			}
	
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			str += Integer.toHexString(prng.nextInt());
			while (str.length () < 8)
			{
				str = '0' + str;
			}
		
			str += Long.toHexString(System.identityHashCode(this));
			while (str.length() < 8)
			{
				str = '0' + str;
			}
			
			prng = SecureRandom.getInstance("SHA1PRNG");
			str += Integer.toHexString(prng.nextInt());
			while (str.length () < 8)
			{
				str = '0' + str;
			}
		}
		catch(java.security.NoSuchAlgorithmException ex)
		{
			throw new RuntimeException("Unable to generate a password salt for secure authentication", ex);
		}
	
		return str;
	}
	
	/**
	 * freeSessionResources
	 * <p> Method to Free Session Resources on load of another form </p>
	 *
	 */
	public void freeSessionResources()
	{
		this.context.remove();		
	}
	
	/**
	 * handleDialogClosed
	 * <p> Method called from CNHost that will reset all session
	 * attribute values when a dialog closed event is received </p>
	 *
	 */
	public void handleDialogClosed()
	{
	    Integer currentFormIDTmp = this.currentFormID.get();
	    this.previousForm.set(currentFormIDTmp);
	    this.previousFormWasClosed.set(new Integer(1));
		    
		ArrayList previousForms = this.previousForms.get();
		ArrayList previousModes = this.previousModes.get();
		ArrayList dialogResults = this.dialogResults.get();
		
		int key = dialogResults.size();
		// removing dialog data by level key
		this.formsData.get().remove(new Integer(key));
		
		int last = previousForms.size() - 1;
		this.dialogID.set(currentFormIDTmp);
		this.currentFormID.set((Integer) previousForms.get(last));
		this.currentFormMode.set((FormMode) previousModes.get(last));
		this.dialogResult.set((DialogResult) dialogResults.get(last));
		if (last > 0)
		{
			previousForms.remove(last);
			previousModes.remove(last);
			dialogResults.remove(last);
		}
		else
		{
			this.previousForms.remove();
			this.previousModes.remove();
			this.dialogResults.remove();
		}
	}
	
	/**
	 * handleOpenDialog
	 * <p> Method used by UIEngine open method and
	 * CNHost TopButtonClick to set values required
	 * for opening a dialog
	 * @param value - form identifier </p>
	 */
	public void handleOpen(Integer value)
	{		
		this.openForm.set(value);
	}
	
	public class GenericDataType<Type> implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		protected GenericDataType(HashMap<String, Object> session, String name)
		{
			this.session = session;
			this.name = name;
		}
		
		@SuppressWarnings("unchecked")
		public Type get()
		{
			return (Type)this.session.get(this.name);
		}
		public void set(Type value)
		{
			this.session.put(this.name, value);
		}
		public void remove()
		{
			this.session.remove(this.name);
		}
		
		private HashMap<String, Object> session;
		private String name;
	}
	public class GenericDataTypeCollection<Type extends List> implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		protected GenericDataTypeCollection(HashMap<String, Object> session, String name)
		{
			this.session = session;
			this.name = name;
		}
		
		@SuppressWarnings("unchecked")
		public Type get()
		{
			return (Type)this.session.get(this.name);
		}
		public void set(Type value)
		{
			this.session.put(this.name, value);
		}
		public void remove()
		{
			this.session.remove(this.name);
		}
		public void clear()
		{
			List a = get();
			if (a != null) 
				a.clear();
		}
		
		private HashMap<String, Object> session;
		private String name;
	}			
	public class LocationArrayType implements Serializable
	{
		private static final long serialVersionUID = 1L;
		protected LocationArrayType(HashMap<String, Object> session, String name)
		{
			this.session = session;
			this.name = name;
		}
		public ILocation find(int id)
		{
			ILocation[] locations = (ILocation[])this.session.get(this.name);
			if(locations == null)
				return null;
			
			for(int x = 0; x < locations.length; x++)
			{
				if(locations[x].getID() == id)
					return locations[x];
			}
			return null;
		}
		public ILocation[] get()
		{
			return (ILocation[])this.session.get(this.name);
		}
		public void set(ILocation[] value)
		{
			set(value, false);
		}
		public void set(ILocation[] value, boolean noLimit)
		{
			if(noLimit)
				this.session.put(this.name, value);
			else
			{
				ILocation[] valueToSet = value;
				
				if(value != null && value.length > 0)
				{
					int size = value.length;
					int maxSize = ConfigFlag.UI.MAX_NO_SELECTED_LOCATIONS_STORED.getValue();				
					if(size > maxSize)
					{
						valueToSet = new ILocation[maxSize];
						for(int x = 0; x < maxSize; x++)
						{
							valueToSet[x] = value[x];
						}
					}				
				}
				
				this.session.put(this.name, valueToSet);	
			}
		}
		public void remove()
		{
			this.session.remove(this.name);
		}
		
		private HashMap<String, Object> session;
		private String name;
	}
	
	/**
	 * ChangeTheme
	 * Set by handleLogin and selectRole and used by
	 * the render method for the form to determine whether
	 * jscn is to be informed of a theme change.
	 */	
	public GenericDataType<String> changeTheme;
	/**
	 * PatientInfo
	 * Holds onto the Patient Information that is generally displayed at the top of the screen
	 */
		
	public GenericDataType<String> patientInfo;
	/**
	 * PatientId
	 * Holds onto the Patient Id
	 */
	public GenericDataType<Integer> patientId;
	
	/**
	 * PatientInfoTextColor
	 * The Color that the Patient Information is to be dispayed in is held here.
	 */
	public GenericDataType<String> patientInfoTextColor;
    
	/**
     * StringType
     * The eMail address to be passed to the email client at the next postback
     */
    public GenericDataType<String> emailAddressToOpen;
	
	/**
     * UrlToOpen
     * The Url to be opened at the next postback
     */
    public GenericDataTypeCollection<ArrayList<Url>> customUrlToOpen;
    
    /**
     * UrlToOpen
     * The Url to be opened at the next postback
     */
    public GenericDataTypeCollection<ArrayList<Integer>> urlToClose;
    
    /**
     * UrlToOpen
     * The Url to be closed at on context change
     */
    public GenericDataTypeCollection<ArrayList<Integer>> urlToCloseOnContextChanged;
    
    
    /**
     * UrlToOpen
     * The current id of Url to be opened at the next postback
     */
    public GenericDataType<Integer> currentCustomUrlToOpenID;
        
    /**
     * customUlToOpen
     * The Url to be opened at the next postback using InternetExplorer ActiveX
     */
    public GenericDataTypeCollection<ArrayList<Url>> urlToOpen;
    
    /**
     * FilesToDelete
     * The files to be deleted at the next postback
     */
    public GenericDataTypeCollection<ArrayList<String>> filesToDelete;
    
    /**
     * ExternalApplicationToRun
     * The files to be executed at the next postback
     */
    public GenericDataTypeCollection<ArrayList<ExternalApplication>> externalApplicationToRun;
	
	/**
	 * CaptionOverride
	 * If a caption for a form is to be modified, it's value should be set here.
	 */
	public GenericDataType<String> captionOverride;
    
	/**
     * ShowCloseButtonForDialog
     * If false, the X button on the top right of a dialog will be hidden
     */
    public GenericDataType<Boolean> showCloseButtonForDialog;
    
    /**
     * ShowResizableDialog
     * If true, the dialog can be resized / maximized
     */
    public GenericDataType<Boolean> showResizableDialog;
    
    /**
     * Form Parameters to be passed on onFormOpen Event
     */
    public GenericDataType<Object[]> formParams;
	
	/**
	 * User
	 * Holds details of the current logged in user
	 */
	public GenericDataType<IAppUser> user;
	
	/**
	 * ConfigurationModule
	 * Holds onto configuration details for the current logged in user
	 */
	public GenericDataType<Configuration> configurationModule;
	
	/**
	 * Specifies if a dialog was opened
	 */
	public GenericDataType<Boolean> dialogOpened;
	
	/**
	 * CurrentFormID
	 * Holds onto the ID of the currently loaded form
	 */
	public GenericDataType<Integer> currentFormID;
	
	/**
	 * PreviousForm
	 */
	public GenericDataType<Integer> previousForm;
	
	/**
	 * PreviousFormWasClosed
	 * Used by the createForm method to determine
	 * whether the previous form was closed or not.
	 * If it was closed, it will clear it's context etc..
	 */
	public GenericDataType<Object> previousFormWasClosed;
	/**
	 * DialogID
	 * Used by DialogClosing event
	 */
	public GenericDataType<Integer> dialogID;
	/**
	 * The list of previous forms loaded
	 */
	public GenericDataTypeCollection<ArrayList<Integer>> previousForms;
	/**
	 * The list of previous form modes
	 */
	public GenericDataTypeCollection<ArrayList<FormMode>> previousModes;
	/**
	 * The list of dialog results
	 */
	public GenericDataTypeCollection<ArrayList<DialogResult>> dialogResults;
	/**
	 * CurrentFormMode
	 * Stores the mode is to be opened in.
	 */
	public GenericDataType<FormMode> currentFormMode;
	/**
	 * Indicates if the form open event should be fired
	 */
	public GenericDataType<Boolean> fireOpenEvent;
	/**
	 * List of forms
	 */
	//public HashMapType forms;
	/**
	 * List of images
	 */
	//public HashMapType images;
	/**
	 * List of alerts
	 */
	public GenericDataTypeCollection<ArrayList<Alert>> alerts;
	/**
	 * List of previously selected patients
	 */
	public GenericDataTypeCollection<ArrayList<ISelectedPatient>> previouslySelectedPatients;
	/**
	 * Forms Data
	 */
	public GenericDataType<HashMap> formsData;
	/**
	 * Role
	 * Holds onto the current selected role
	 */
	public GenericDataType<IAppRole> role;
	
	/**
	 * Dialog Result
	 */
	public GenericDataType<DialogResult> dialogResult;
	
	/**
	 * Close form
	 */
	public GenericDataType<Boolean> closeForm;
	
	/**
	 * 	Context
	 */
	public GenericDataType<Context> context;
	
	/**
	 * DomainSession
	 * Stores the DomainSession associated with
	 * the current HashMap
	 */
	public GenericDataType<DomainSession> domainSession;
	
	/**
	 * Domain
	 * Stores the domain required for the corresponding
	 * logic on formload
	 */
	//public GenericDataType<Object> domain;
	
	/**
	 * Errors
	 * Stores the list of errors to be displayed
	 */
	public GenericDataTypeCollection<ArrayList> errors;
	
	/**
	 * LastSearchResults
	 * Stores the list of search results
	 */
	public GenericDataType<ISearchResult[]> lastSearchResults;
	
	/**
	 * LastSearch
	 * Stores the last search text
	 */
	public GenericDataType<String> lastSearchText;
	
	/**
	 * AppParams
	 * Stores the last search text
	 */
	public GenericDataType<IAppParam[]> appParams;
	
	/**
	 * ErrorsTitle
	 * Holds onto the title to be displayed in the error message box.
	 */
	public GenericDataType<String> errorsTitle;
	
	/**
	 * UniqueClientId
	 * Holds onto the unique ID of a client
	 */
	public GenericDataType<String> uniqueClientId;
	
	/**
	 * Form
	 */
	//public ObjectType form;
	
	/**
	 * SessionId
	 * Gets or sets the session id.
	 */
	public GenericDataType<String> sessionId;
	/**
	 * UploadUrl
	 * Gets or sets the upload Url used by the file uploader controls.
	 */
	public GenericDataType<String> uploadUrl;
	
	/**
	 * UploadDebug
	 */
	public GenericDataType<Boolean> uploadDebug;	
	
	/**
	 * Previous Non-Dialog Form ID
	 * Holds the ID of the previous form that is not a dialog
	 */
	public GenericDataType<Integer> previousNonDialogFormID;
	
	/**
	 * Current Non-Dialog Form ID
	 * Holds the ID of the current form that is not a dialog
	 */
	public GenericDataType<Integer> currentNonDialogFormID;
	
	/**
	 * External Notifications
	 */
	public GenericDataTypeCollection<ArrayList<String>> externalNotifications;
	/**
	 * Next Message Box Identifier
	 */
	public GenericDataType<Integer> nextMessageBoxIdentifier;
	
	/**
	 * Message Boxes
	 */
	public GenericDataTypeCollection<ArrayList<MessageBox>> messageBoxes;
	/**
	 * MessageBox
	 */
	public GenericDataTypeCollection<ArrayList<String>> messageBox;
	
	/**
	 * DomainMessageBox
	 */
	public GenericDataTypeCollection<ArrayList<String>> domainMessageBox;

	/**
	 * NewAlerts
	 * List of Alerts to be added to view
	 */
	public GenericDataTypeCollection<ArrayList<Alert>> newAlerts;
	
	/**
	 * Open Form
	 * Holds onto the reference to the form that is to be opened.
	 */
	public GenericDataType<Integer> openForm;
	
	/**
	 * Open Form Caller Identifiers
	 * Holds onto the reference to the form or component identifier who created the request to open a dialog
	 */
	public GenericDataTypeCollection<ArrayList<String>> openDialogCallerIdentifiers;
	
	/**
	 * RemovedAlerts
	 * List of Alerts to be removed from view
	 */
	public GenericDataTypeCollection<ArrayList<Alert>> removedAlerts;
	
	/**
	 * UIEngine
	 * Used by formLoader and UIEngine to set engine
	 * required for form loading.
	 */
	//public ObjectType uiEngine;
	
	/**
	 * UILogic
	 * Used by formLoader to set the logic required for the form.
	 */
	//public ObjectType uiLogic;

	/**
	 * XMLLogger
	 * If the Session Log Configuration Flag has been set to true
	 * it is possible to log xml activity for individual sessions.
	 * This is the logger for the current session.
	 */
	public GenericDataType<Logger> xmlLogger;
	
	/**
	 * IncludeRIE
	 * If this value is set, RecordedInError items will be included
	 * from the domain back to UI
	 */
	public GenericDataType<Boolean> listRIERecordsOnly;
	
	/**
	 * RIEBoClassName
	 * When IncludeRIE is set, the rule of returning RIE records will only
	 * apply for objects of this type
	 */
	public GenericDataType<String> rieBoClassName;
	 
	/**
	 * ListRecordInfo
	 * HashMap of information based on VoCollections.  Holds onto the
	 * rieCount, activeCount and the boClassname
	 */
	public GenericDataType<HashMap> listRecordInfo;
	
	public GenericDataType<HashMap> clientSesionTimeout;
	
	public GenericDataType<HashMap> clientAutolockTimer;
	
	/**
	 * SelectedLocation
	 * Holds onto to the currently selected location
	 */
	public GenericDataType<ILocation> selectedLocation;
	
	/**
	 * Stored Locations
	 * Holds the locations stored for the computer
	 */
	public LocationArrayType storedLocations;
	
	/**
	 * Send stored locations
	 * Specifies whether to send the stored locations as part of the render
	 */
	public GenericDataType<Boolean> sendStoredLocations;
	
	/**
	 * Runtime Client Form Area
	 * Specifies the size of the form at runtime
	 */
	public GenericDataType<SizeInfo> runtimeClientFormArea;
	
	/**
	 * Stores the dynamic navigation class being used by the logged in user for chosen Role
	 */
	public GenericDataType<IDynamicNavigation> currentDynamicNavigation;

	/**
	 * Defines the state of the current UI layout 
	 */
	public GenericDataType<UILayoutState> currentUILayoutState;
	/**
	 * Navigation collapsed
	 */
	public GenericDataType<Boolean> navigationCollapsed;
	/**
	 * Defines the state of the last used UI layout 
	 */
	public GenericDataType<Boolean> uiLayoutChanged;
	
	/**
	 * Specifies if the standard application layout has been rendered.
	 * When false it means that the first form (startup form) hasn't been opened yet 
	 */
	public GenericDataType<Boolean> startupMode;
	
	/**
	 * Specifies the text that will appear on the progress bar
	 * When null no progress bar will be displayed 
	 */
	public GenericDataType<String> progressBarText;
	
	/**
	 * Specifies the last form dark header height  
	 */
	public GenericDataType<Integer> formDarkHeaderHeight;	
	
	/**
	 * Specifies if the system password had been entered
	 */
	public GenericDataType<Boolean> systemPasswordEntered;
	
	/**
	 * Specifies the system form to be opened after the user authenticates successfully
	 */
	public GenericDataType<Integer> systemFormToOpen;
	
	/**
	 * Specifies the top button config instance
	 */
	public GenericDataType<Object> topButtons;
	
	/**
	 * Specifies the session user agent
	 */
	public GenericDataType<UserAgent> userAgent;
	
	public GenericDataType<Boolean> autoLockSent;
	
	public GenericDataType<Integer> autoLockInterval;
	
	public GenericDataType<Integer> heartBeatInterval;
	
	public GenericDataType<Integer> heartBeatCounter;
	
	public GenericDataType<String> passwordSalt;
	
	public GenericDataType<HashMap> securityTokenParameters;
	
	public GenericDataType<String> restrictPatientAccess;
	
	public GenericDataType<String> securityTokenPatientIdentifierType;
	
	public GenericDataType<String> securityTokenPatientIdentifierValue;
	
	public GenericDataType<String> securityTokenLaunchUsername;
	
	public GenericDataType<String> securityTokenLaunchPassword;
	
	public GenericDataType<Integer> restrictPatientId;
	
	public GenericDataType<Integer> defaultStartupForm;	
	
	public GenericDataType<String> appointmentStartDateTime;
	
	public GenericDataType<String> appointmentEndDateTime;
	
	public GenericDataType<String> appointmentLocationCode;
	
	public GenericDataType<String> appointmentConsultantCode;
	
	// FWUI-1770
	public GenericDataType<Boolean> isPatientRIP;
	public GenericDataType<Boolean> isEpisodeEnded;

	public GenericDataType<StringBuilder> copyToClipboard;	
	public GenericDataType<Boolean> clearClipboard;	
	
	public GenericDataType<Integer> loginAttempts;
	
	public GenericDataType<String> medicodeInputFile;
	
	//PDS Stuff
	public GenericDataType<String> smartcardTicketId;	
	public GenericDataType<String> samlXml;	
	
	public void addAlert(Alert alert)
	{
    	if(alert == null)
    		throw new CodingRuntimeException("Unable to add a null alert");    	

    	// we don't allow any alerts to be added if we are in RIE mode 
    	// and alert is not an internal alert type
    	if(this.listRIERecordsOnly.get().booleanValue() && !(alert instanceof InternalAlert))
    		return;
    	
    	if(!alert.allowMultiple())
    		removeAlertsByType(alert.getClass());
    	
    	this.newAlerts.get().add(alert);    	
    	this.alerts.get().add(alert);    	
	}
	
	public void removeAlertsByType(Class typeOfAlert)
	{
	   	clearAlertsByType(typeOfAlert);
	}
	
	public void clearAlertsByType(Class typeOfAlert)
	{
    	if(typeOfAlert == null)
    		throw new CodingRuntimeException("Unable to remove a null alert type");
    	    	
		// we don't allow any alerts to be removed if we are in RIE mode
    	// and alert type is not an internal alert type
    	if(this.listRIERecordsOnly.get().booleanValue() && !InternalAlert.class.isAssignableFrom(typeOfAlert))
    		return;
    	
    	ArrayList existingAlerts = this.alerts.get();
    	for(int x = existingAlerts.size() - 1; x >= 0; x--)
    	{
    		if(existingAlerts.get(x) != null && existingAlerts.get(x) instanceof Alert)
    		{
	    		Alert alert = (Alert)existingAlerts.get(x);
	    		if(alert.getClass().equals(typeOfAlert))
	    		{   	    				    		
	    			this.removedAlerts.get().add(alert);
	    			this.alerts.get().remove(alert);
	    		}
    		}
    	}    	
    	
    	// clear all just added alerts of that type
    	ArrayList newAlerts = this.newAlerts.get();
    	for(int x = newAlerts.size() - 1; x >= 0; x--)
    	{
    		//if(existingAlerts.get(x) != null && !this.sessData.newAlerts.get().contains(existingAlerts.get(x)) && existingAlerts.get(x) instanceof Alert)
    		if(newAlerts.get(x) != null && newAlerts.get(x) instanceof Alert)
    		{
	    		Alert alert = (Alert)newAlerts.get(x);
	    		if(alert.getClass().equals(typeOfAlert))
	    		{    				    	
	    			this.newAlerts.get().remove(alert);	    				    			
	    		}
    		}
    	}  	
	}
	public void clearAlerts()
	{
		// we don't allow any alerts to be removed if we are in RIE mode 
    	if(this.listRIERecordsOnly.get().booleanValue())
    		return;
    	
    	ArrayList<Alert> existingAlerts = this.alerts.get();    	
    	for (int x = existingAlerts.size() - 1; x >=0 ; x--)
    	{
    		if(existingAlerts.get(x) instanceof InternalAlert)
    			continue;    
    		
    		this.removedAlerts.get().add(existingAlerts.get(x));
    		this.alerts.get().remove(existingAlerts.get(x));
    	}   
    	
    	// clear all just added alerts
    	if(this.newAlerts.get() != null)
    		this.newAlerts.get().clear();		
	}
	
    public void removeAlert(Alert alert)
	{
    	if(alert == null)
    		throw new CodingRuntimeException("Unable to remove a null alert");
    	
    	// we don't allow any alerts to be removed if we are in RIE mode 
    	if(this.listRIERecordsOnly.get().booleanValue())
    		return;
    	
    	if(this.alerts.get().contains(alert))
    	{
    		this.removedAlerts.get().add(alert);   	    		
    		this.alerts.get().remove(alert);
    	}
    }

 
}
