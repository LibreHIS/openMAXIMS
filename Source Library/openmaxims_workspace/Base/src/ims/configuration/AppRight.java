package ims.configuration;

import ims.framework.interfaces.IAppRight;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AppRight implements Serializable, Comparable, IAppRight
{
	private static final long serialVersionUID = 1L;
	private static final Map<String, AppRight> RIGHTS = new HashMap<String, AppRight>();
		
	public static final AppRight OCS_ALLOW_INV_SEARCH = new AppRight("OCS_ALLOW_INV_SEARCH", "Allows a user to search for arbitrary investigations on the Order Entry screen, bypassing Order sets and Templates.");
	public static final AppRight ALLOW_ORDERINVESTIGATIONS_CAREUK  = new AppRight("ALLOW_ORDERINVESTIGATIONS_CAREUK","Used to control enabling/disabling of the orderinvestigations buttons on 'ReferralAppointmentDetailsComponent' and on 'AtConsultation'.");  // FWB-157
	public static final AppRight LOOKUP_TEXT_MODIFY = new AppRight("LOOKUP_TEXT_MODIFY", "Allows a user to edit the text description of a lookup instance.");
	public static final AppRight REMOVE_LOCATION_SERVICE_ACTFUNC = new AppRight("REMOVE_LOCATION_SERVICE_ACTFUNC", "Used to allow functionalilty to remove Services/Activities/Functions and LocationService/LocationServiceActivity/LocationServiceFunc.");
	public static final AppRight MARK_RESULT_AS_CHECKED = new AppRight("MARK_RESULT_AS_CHECKED", "Used to allow authorised user to mark a Path/Rad result as checked.");
	public static final AppRight OCS_CHANGE_INVESTIGATION = new AppRight("OCS_CHANGE_INVESTIGATION", "Used to control whether a user can modify the name of a configured investigation or profile");
//	public static final AppRight CAN_AUTHORIZE = new AppRight("CAN_AUTHORIZE", "Used to control whether a user can authorize Orders");//FWB-335
	public static final AppRight CAN_AUTHORIZE_PROCEDURE = new AppRight("CAN_AUTHORIZE_PROCEDURE", "On discharge summary allows a user record a procedure outside of the configured list for treating hospital"); //WDEV-14113
	public static final AppRight CAN_AUTHORIZE_PATHOLOGY_ORDERS = new AppRight("CAN_AUTHORIZE_PATHOLOGY_ORDERS", "Used to control whether a user can authorize pathology Orders");//FWB-335 
	public static final AppRight CAN_AUTHORIZE_CLINICAL_IMAGING_ORDERS = new AppRight("CAN_AUTHORIZE_CLINICAL_IMAGING_ORDERS", "Used to control whether a user can authorize clinical imaging Orders");//FWB-335 
	public static final AppRight CAN_CONFIRM_NURSING_DOCUMENTATION = new AppRight("CAN_CONFIRM_NURSING_DOCUMENTATION", "Used to control whether a user can confirm Nursing Documentation");
	public static final AppRight CAN_UNAPPROVE_NTPF_INVOICE = new AppRight("CAN_UNAPPROVE_NTPF_INVOICE", "Used to control whether a user can unapprove a previously approved Invoice");	
	public static final AppRight CAN_EDIT_PROCEDURE_NAME = new AppRight("CAN_EDIT_PROCEDURE_NAME", "Used to control whether a user can edit the text of a procedure name.");
	public static final AppRight CAN_REMOVE_PTR_WAITING_LIST_ENTRY = new AppRight("CAN_REMOVE_PTR_WAITING_LIST_ENTRY", "Used to control whether a user can remove a Waiting List entry.");
	public static final AppRight CAN_UPDATE_RESULT_STATUS = new AppRight("CAN_UPDATE_RESULT_STATUS", "Only specific roles should be allowed to mark a result as Seen and/or Reviewed.");
	public static final AppRight CAN_ADD_CONTACT_AFTER_ENDDATE_SUPPLIED = new AppRight("CAN_ADD_CONTACT_AFTER_ENDDATE_SUPPLIED","Allows the user create new Care Contexts and Clinical Contacts after Episode of Care has been ended.");
//FWB-956 	public static final AppRight CAN_EDIT_REFERRING_CONSULTANT = new AppRight("CAN_EDIT_REFERRING_CONSULTANT","Allows the user Edit the Referring Consulant on Case Referral screen.");
	public static final AppRight CAN_VIEW_CONFIDENTIAL_INVESTIGATIONS_ORDERED= new AppRight("CAN_VIEW_CONFIDENTIAL_INVESTIGATIONS_ORDERED","Allows the user view confidential Investigations Ordered.");
	public static final AppRight CAN_VIEW_CONFIDENTIAL_INVESTIGATION_RESULTS= new AppRight("CAN_VIEW_CONFIDENTIAL_INVESTIGATION_RESULTS","Allows the user view confidential Investigation Results."); 
	public static final AppRight CAN_APPROVE_INVOICE_WITHOUT_TC_ACCREDITATION = new AppRight("CAN_APPROVE_INVOICE_WITHOUT_TC_ACCREDITATION","Allows the user approve an invoice without treating consultant accreditation.");
	public static final AppRight CAN_SUBSCRIBE_TO_SHARED_DICTIONARIES  = new AppRight("CAN_SUBSCRIBE_TO_SHARED_DICTIONARIES","Allows the user to subscribe to Shared Dictionaries.");
	public static final AppRight CAN_ADD_WORDS = new AppRight("CAN_ADD_WORDS","Allows the user add words to their Dictionary.");
	public static final AppRight CAN_UNDISCHARGE_EPISODE = new AppRight("CAN_UNDISCHARGE_EPISODE","Allows the user undischarge an episode.");
	public static final AppRight CAN_ACCESS_PRICE_ADJUSTMENT = new AppRight("CAN_ACCESS_PRICE_ADJUSTMENT","Allows the user access to the price adjustment functions.");
	public static final AppRight CAN_REGISTER_CHILD_FROM_CLIENT_RECORD = new AppRight("CAN_REGISTER_CHILD_FROM_CLIENT_RECORD","Allows the user register child clients for a client.");
	public static final AppRight CAN_RECORD_NCP_COMMENT = new AppRight("CAN_RECORD_NCP_COMMENT","Allows the user record a NCP comment.");
	public static final AppRight CAN_ACCEPT_CATS_REFERRAL = new AppRight("CAN_ACCEPT_CATS_REFERRAL","Allows the user accept a CATS referral.");
	public static final AppRight CAN_REJECT_CATS_REFERRAL = new AppRight("CAN_REJECT_CATS_REFERRAL","Allows the user reject a CATS referral.");
	public static final AppRight CAN_REQUEST_SECOND_OPINION_FOR_CATS_REFERRAL  = new AppRight("CAN_REQUEST_SECOND_OPINION_FOR_CATS_REFERRAL","Allows the user request a second opinion for a CATS referral.");
	public static final AppRight ALLOW_BEDMAINTENANCE_ADTWARDVIEW = new AppRight("ALLOW_BEDMAINTENANCE_ADTWARDVIEW","On ADT Ward View, show the Bed Maintenance Tab");
	public static final AppRight ALLOW_EDIT_CONSULTATION_DIAGNOSIS = new AppRight("ALLOW_EDIT_CONSULTATION_DIAGNOSIS","Allows Discharge CO-ordinators edit Diagnosis info on At Consultation screen.");
	public static final AppRight ALLOW_MARK_PATIENT_AS_DECEASED  = new AppRight("ALLOW_MARK_PATIENT_AS_DECEASED","Allows Patients to be marked as deceased");	
	public static final AppRight CAN_OVERBOOK_THEATRE_APPTS = new AppRight("CAN_OVERBOOK_THEATRE_APPTS","Allows overbooking of theatre appointments");
	public static final AppRight ALLOWED_TO_CLEAR_VACCINE_DETAILS = new AppRight("ALLOWED_TO_CLEAR_VACCINE_DETAILS","Allows vaccine details to be cleared");
	public static final AppRight CAN_ALLOCATE_WORKLIST_TASKS = new AppRight("CAN_ALLOCATE_WORKLIST_TASKS","Allows user allocate work list tasks");
	public static final AppRight CAN_UNDO_DISCHARGE = new AppRight("CAN_UNDO_DISCHARGE","Allows user to UNDO discharge");
	public static final AppRight CAN_DISCHARGE_INPATIENT_IN_RCHT = new AppRight("CAN_DISCHARGE_INPATIENT_IN_RCHT","Allows user discharge inpatient in RCHT");
	public static final AppRight CAN_OVERBOOK_FLEXIBLE_APPT = new AppRight("CAN_OVERBOOK_FLEXIBLE_APPT","Allows booking an appointment into a flexible slot while ignoring the time validation");
	public static final AppRight ALLOW_CANCEL_ORDERINVESTIGATION = new AppRight("ALLOW_CANCEL_ORDERINVESTIGATION","Allows cancellation of orders.");
	public static final AppRight ALLOW_ADT_PENDINGEMERGENCY_REMOVAL = new AppRight("ALLOW_ADT_PENDINGEMERGENCY_REMOVAL","Allows Pending Emergency ADT event to be removed.");
	public static final AppRight CAN_EDIT_INITIALLY_SEEN_BY = new AppRight("CAN_EDIT_INITIALLY_SEEN_BY","Allows Initially Seen By be edited");
	public static final AppRight CAN_REVIEW_GP_DISCHARGE_OUTCOME = new AppRight("CAN_REVIEW_GP_DISCHARGE_OUTCOME","Allows review of GP Discharge outcome");
	public static final AppRight CAN_REVIEW_AND_REMOVE_DNA_APPTS = new AppRight("CAN_REVIEW_AND_REMOVE_DNA_APPTS","Allows review and removal of DNA'ed appointments from worklists");
	public static final AppRight CAN_REVIEW_AND_REMOVE_CANCELLED_APPTS = new AppRight("CAN_REVIEW_AND_REMOVE_CANCELLED_APPTS","Allows review and removal of cancelled appointments from worklists");
	public static final AppRight CAN_RESET_PROV_CANC_AND_REF_REJECT = new AppRight("CAN_RESET_PROV_CANC_AND_REF_REJECT","Allows resetting of Provisionally Cancelled and Rejected referrals");
	public static final AppRight CAN_VIEW_CLINICS_ACROSS_ALL_CONTRACTS = new AppRight("CAN_VIEW_CLINICS_ACROSS_ALL_CONTRACTS","Allow the user view clinics across all contracts.");
	public static final AppRight CAN_UNDO_ASSESSMENT_COMPLETION = new AppRight("CAN_UNDO_ASSESSMENT_COMPLETION","Allow the user undo a completed assessment.");
	public static final AppRight NURSING_ADMIN_ROLE = new AppRight("NURSING_ADMIN_ROLE","Allow the user access to nursing administration functions.");
	public static final AppRight CAN_COUNTERSIGN = new AppRight("CAN_COUNTERSIGN","Allow the user counter sign ICP actions.");
	public static final AppRight AUTHORISED_DATA_CORRECTOR = new AppRight("AUTHORISED_DATA_CORRECTOR","Allows the user make certain data corrections.");
	public static final AppRight CAN_UNLINK_REFERRAL_FROM_EPISODE = new AppRight("CAN_UNLINK_REFERRAL_FROM_EPISODE","Allows the user unlink referral from the episode.");
	public static final AppRight CAN_EDIT_CATS_REPORT_DOCUMENT_SENT_DATE = new AppRight("CAN_EDIT_CATS_REPORT_DOCUMENT_SENT_DATE","Allows the user edit CATS document sent date");	
	public static final AppRight PHARMACY_CAN_APPROVE = new AppRight("PHARMACY_CAN_APPROVE","Allows the user Approve on eDischarge Pharmacy Approval");	
	public static final AppRight PHARMACY_CAN_REJECT = new AppRight("PHARMACY_CAN_REJECT","Allows the user Reject on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_SUSPENDED = new AppRight("PHARMACY_CAN_SUSPENDED","Allows the user Suspend on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_PRINT_REQUEST = new AppRight("PHARMACY_CAN_PRINT_REQUEST","Allows the user Print Request on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_ENTER_PHARMACY_SYSTEM_DETAILS = new AppRight("PHARMACY_CAN_ENTER_PHARMACY_SYSTEM_DETAILS","Allows the user enter Pharmacy System on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_DISPENSED_TTA = new AppRight("PHARMACY_CAN_DISPENSED_TTA","Allows the user DispensedTTA on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_DO_FINAL_CHECKS = new AppRight("PHARMACY_CAN_DO_FINAL_CHECKS","Allows the user FinalCheck on eDischarge Pharmacy Approval");
	public static final AppRight PHARMACY_CAN_ACCESS_PHARMACY_TAB = new AppRight("PHARMACY_CAN_ACCESS_PHARMACY_TAB","Allows the user access eDischarge Pharmacy tab");
	public static final AppRight ALLOW_LINK_DISCHARGE_TO_INVOICE = new AppRight("ALLOW_LINK_DISCHARGE_TO_INVOICE","User is allowed link a Discharge Summary to an Invoice");
	public static final AppRight CAN_PRINT_DISCHARGE_FROM_WARD_PACU_SCREEN = new AppRight("CAN_PRINT_DISCHARGE_FROM_WARD_PACU_SCREEN","User is allowed print print discharge summary from Ward-Pacu Form");
	public static final AppRight ALLOW_EDIT_IMAGES = new AppRight("ALLOW_EDIT_IMAGES","User is allowed edit images");
	public static final AppRight ALLOW_ANAESTHETIC_TYPE_CHANGE  = new AppRight("ALLOW_ANAESTHETIC_TYPE_CHANGE","When Booking a Theatre Appointment allow the users to override the defaulted Anaesthetic Type from the Fit For Surgery record");
	public static final AppRight CAN_UNDO_RACPC_CLINICAL_DETAILS_COMPLETE = new AppRight("CAN_UNDO_RACPC_CLINICAL_DETAILS_COMPLETE","Allows the role undo completion of RACPC Clinical Details");
	public static final AppRight CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE = new AppRight("CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE","Allows the role undo completion of surgical notes");
	public static final AppRight ALLOW_DELETION_OF_APPOINTMENT_CLINICAL_NOTES = new AppRight("ALLOW_DELETION_OF_APPOINTMENT_CLINICAL_NOTES","Allows the role delete appointment clinical notes");//FWB-351
	public static final AppRight CAN_ADD_RESULT_ANNOTATIONS = new AppRight("CAN_ADD_RESULT_ANNOTATIONS","Allows the role add result annotations");//FWB-359
	public static final AppRight CAN_CORRECT_RESULT_ANNOTATIONS = new AppRight("CAN_CORRECT_RESULT_ANNOTATIONS","Allows the role correct result annotations");//FWB-364
	public static final AppRight CAN_ALLOCATE_PATIENT_CLERICAL_TASKS = new AppRight("CAN_ALLOCATE_PATIENT_CLERICAL_TASKS","Allows the role add patient clerical tasks");//FWB-360
	public static final AppRight CAN_REMOVE_DRUG_FROM_PRESCRIPTION = new AppRight("CAN_REMOVE_DRUG_FROM_PRESCRIPTION","Allows the role remove a drug from a perscription");//FWB-365
	public static final AppRight ED_ALLOW_CORRECTION_OF_ANY_TRIAGE_NOTE = new AppRight("ED_ALLOW_CORRECTION_OF_ANY_TRIAGE_NOTE","Allows the role correct any selected triage note.");//FWB-385
	public static final AppRight ED_ALLOW_CORRECTION_OF_ANY_MEDIC_NOTE = new AppRight("ED_ALLOW_CORRECTION_OF_ANY_MEDIC_NOTE","Allows the role correct any selected medic note.");//FWB-386
	public static final AppRight ED_CAN_VIEW_TRACKING_ACROSS_ALL_ED_SITES = new AppRight("ED_CAN_VIEW_TRACKING_ACROSS_ALL_ED_SITES","Allows the role view tracking across all sites.");//FWB-388
	public static final AppRight DISCHARGE_ACCESS_TO_VTE_ASSESSMENT = new AppRight("DISCHARGE_ACCESS_TO_VTE_ASSESSMENT","Allows the role access the VTE assessment while discharging.");//FWB-390
	public static final AppRight CAN_EDIT_ALL_CLINICAL_NOTES = new AppRight("CAN_EDIT_ALL_CLINICAL_NOTES","Allows the role edit all clinical notes."); //http://jira/browse/FWB-399
	public static final AppRight CC_ACCESS_ALL_REFERRALS = new AppRight("CC_ACCESS_ALL_REFERRALS","Allows the role access all CC referrals."); //http://jira/browse/FWB-402
	public static final AppRight CC_ACCESS_CYPS_REFERRALS = new AppRight("CC_ACCESS_CYPS_REFERRALS","Allows the role access CYPS referrals."); //http://jira/browse/FWB-402
	public static final AppRight ALLOW_USER_PASSWORD_UPDATE_ONLY = new AppRight("ALLOW_USER_PASSWORD_UPDATE_ONLY","Users with this role can only update the password fields on the user managment screen"); //http://jira/browse/FWB-414
	public static final AppRight RESET_RESULT_STATUS_TO_NEW = new AppRight("RESET_RESULT_STATUS_TO_NEW","Users with this role can set a result status to new");//http://jira/browse/FWB-416
	public static final AppRight CAN_ATTACH_APPOINTMENT_TO_ICP = new AppRight("CAN_ATTACH_APPOINTMENT_TO_ICP","Users with this role can attach an appointment to an ICP");//http://jira/browse/FWB-424
	public static final AppRight CAN_EDIT_AND_RIE_DEMENTIA = new AppRight("CAN_EDIT_AND_RIE_DEMENTIA","Users with this role can edit and RIE FIND and AMTS Dementia related data");//http://jira/browse/FWB-430
	public static final AppRight MAXIMS_ADT_CAN_DISCHARGE = new AppRight("MAXIMS_ADT_CAN_DISCHARGE","Users with this right can discharge patient from standalone Maxims ADT");//http://jira/browse/FWB-444
	public static final AppRight ED_CAN_PERFORM_ADMISSION = new AppRight("ED_CAN_PERFORM_ADMISSION","Users with this right can admit patients from ED");//http://jira/browse/FWB-450
	public static final AppRight ED_CAN_PERFORM_TRANSFER = new AppRight("ED_CAN_PERFORM_TRANSFER","Users with this right can transfer patients from ED");//http://jira/browse/FWB-450
	public static final AppRight CAN_AUTHORISE_EAS = new AppRight("CAN_AUTHORISE_EAS","HCP users with this right can authorise EASs");//http://jira/browse/FWB-453
	public static final AppRight ED_CAN_UNDO_ED_DISCHARGE = new AppRight("ED_CAN_UNDO_ED_DISCHARGE","Users with this right can undo an ED discharge");//http://jira/browse/FWD-229
	public static final AppRight ED_ATTENDENCE_NOTES_CAN_RIE_DIAGNOSIS = new AppRight("ED_ATTENDENCE_NOTES_CAN_RIE_DIAGNOSIS","Users with this right can RIE diagnoses from the attendence notes form.");//http://jira/browse/FWB-456
	public static final AppRight CANNOT_EDIT_ALLERGIES_COMPONENT = new AppRight("CANNOT_EDIT_ALLERGIES_COMPONENT","Users with this right cannot edit allergies on the allergies component");//http://jira/browse/FWB-465
	public static final AppRight CANNOT_EDIT_ALERTS_COMPONENT = new AppRight("CANNOT_EDIT_ALERTS_COMPONENT","Users with this right cannot edit alerts on the alerts component");//http://jira/browse/FWB-465
	public static final AppRight CAN_REFRESH_CONTRACT_CODING = new AppRight("CAN_REFRESH_CONTRACT_CODING","Users with this right can refresh contract coding");//http://jira/browse/FWB-471
	public static final AppRight CAN_VIEW_ALL_LISTOWNERS = new AppRight("CAN_VIEW_ALL_LISTOWNERS","Users with this right can view all profiles in the Clinic List");//http://jira/browse/FWB-474
	public static final AppRight CAN_DIRECTLY_REJECT_REFERRAL = new AppRight("CAN_DIRECTLY_REJECT_REFERRAL","Users with this right can view reject a referral without going to Second Opinion");//http://jira/browse/WDEV-17896 
	public static final AppRight HAN_CAN_ALLOCATE_TASKS = new AppRight("HAN_CAN_ALLOCATE_TASKS","Users with this right can allocate Hospital At Night tasks");//http://jira/browse/FWB-479
	public static final AppRight ALLOW_DELETION_OF_ELECTIVE_LIST_NOTES = new AppRight("ALLOW_DELETION_OF_ELECTIVE_LIST_NOTES","Users with this right can delete elective list notes");//http://jira/browse/FWB-485
	public static final AppRight ALLOW_NEW_NAES_ACTION_PLAN = new AppRight("ALLOW_NEW_ACTION_PLAN","Users with this right can create new NAES Action Plans");//http://jira/browse/FWB-503
	public static final AppRight ED_CAN_PRESCRIBE = new AppRight("ED_CAN_PRESCRIBE","Users with this right will be able to prescribe or edit prescription details");//http://jira/browse/FWB-504
	public static final AppRight VIEW_OTHER_CODED_RECORDS = new AppRight("VIEW_OTHER_CODED_RECORDS","Users with this right will be able to view other coded records");//http://jira/browse/FWB-540
	public static final AppRight ALLOW_UNDO_REFERRAL_CLOCKIMPACT = new AppRight("ALLOW_UNDO_REFERRAL_CLOCKIMPACT","Users with this right have the ability to de-select the Activity Subject to RTT Clock checkbox");//http://jira/browse/FWB-541
	public static final AppRight CAN_UNDO_APPOINTMENT_OUTCOME = new AppRight("CAN_UNDO_APPOINTMENT_OUTCOME","Users with this right have the ability to undo an appointment outcome");//http://jira/browse/FWB-545
	public static final AppRight CAN_RECORD_OVERALL_PATHWAY_BREACH_REASON = new AppRight("CAN_RECORD_OVERALL_PATHWAY_BREACH_REASON","Users with this right have the ability to record an overall pathway breach reason");//http://jira/browse/FWB-546
	public static final AppRight PATHWAY_ALLOW_CORRECTION_OF_ANY_BREACH_REASON = new AppRight("PATHWAY_ALLOW_CORRECTION_OF_ANY_BREACH_REASON","Users with this right have the ability to correct pathway breach reasons.");//http://jira/browse/FWB-547
	public static final AppRight BED_MANAGEMENT_CAN_CLOSE_WARD_BAYS = new AppRight("BED_MANAGEMENT_CAN_CLOSE_WARD_BAYS","Users with this right can close ward bays.");//http://jira/browse/FWB-555
	public static final AppRight BED_MANAGEMENT_CAN_BLOCK_WARD_BAYS = new AppRight("BED_MANAGEMENT_CAN_BLOCK_WARD_BAYS","Users with this right can block ward bays.");//http://jira/browse/FWB-555
	public static final AppRight BED_MANAGEMENT_CAN_REOPEN_WARD_BAYS_OOH = new AppRight("BED_MANAGEMENT_CAN_REOPEN_WARD_BAYS_OOH","Users with this right can reopen ward bays OOH");//http://jira/browse/FWB-555
	public static final AppRight PDS_SYNCHRONISE_INTERACTIVE = new AppRight("PDS_SYNCHRONISE_INTERACTIVE","Users with this right Synchronise with PDS interactively.");//http://jira/browse/FWB-566
	public static final AppRight PDS_ACCESS_SENSITIVE_RECORDS = new AppRight("PDS_ACCESS_SENSITIVE_RECORDS","Users with this right sensitive PDS records.");//http://jira/browse/FWB-566
	public static final AppRight PDS_RECORD_TEMP_SENSITIVE_ADDRESS = new AppRight("PDS_RECORD_TEMP_SENSITIVE_ADDRESS","Users with this right can record a temporary sensitive address");//http://jira/browse/FWB-566
	public static final AppRight CAN_UPDATE_REFERRAL_URGENCY = new AppRight("CAN_UPDATE_REFERRAL_URGENCY","Users with this right can the urgency of a referral");//http://jira/browse/FWB-568
	public static final AppRight REFERRALS_CAN_PERFORM_DOWNGRADE = new AppRight("REFERRALS_CAN_PERFORM_DOWNGRADE","Users with this right can perform a referral downgrade");//http://jira/browse/FWB-569
	public static final AppRight REFERRALS_CAN_PERFORM_CONSULTANT_UPGRADE = new AppRight("REFERRALS_CAN_PERFORM_CONSULTANT_UPGRADE","Users with this right can perform a consultant upgrade on a referral");//http://jira/browse/FWB-569
	public static final AppRight PDS_ACCESS_UNDECEASE = new AppRight("PDS_ACCESS_UNDECEASE","Users with this right can undecease a patient on PDS");//http://jira/browse/FWB-571
	public static final AppRight CAN_RECORD_REFERRAL_ADMIN_EVENT = new AppRight("CAN_RECORD_REFERRAL_ADMIN_EVENT","Users with this right have access to Record Referral Admin Event");//http://jira/browse/FWB-572
	public static final AppRight PENDING_EMERGENCY_CAN_RECORD_REQUEST_FOR_REPATRIATION = new AppRight("PENDING_EMERGENCY_CAN_RECORD_REQUEST_FOR_REPATRIATION","Users with this right can record a request for repatriation");//http://jira/browse/FWB-573
	public static final AppRight PENDING_EMERGENCY_CAN_RECORD_CLINICAL_NEED = new AppRight("PENDING_EMERGENCY_CAN_RECORD_CLINICAL_NEED","Users with this right can record clinical need for a pending emergency admission");//http://jira/browse/FWB-574
	public static final AppRight CAN_INACTIVATE_PATIENT = new AppRight("CAN_INACTIVATE_PATIENT","Users with this right can inactivate a patient record");//http://jira/browse/WDEV-20689
	public static final AppRight PDS_ACCESS_RECORD_ON_DISSENT = new AppRight("PDS_ACCESS_RECORD_ON_DISSENT","Users with this right can see patient demographic records with the consent status of Express Dissent.");//http://jira/browse/FWB-577
	public static final AppRight CAN_CANCEL_FROM_ADMISSION_DETAILS = new AppRight("CAN_CANCEL_FROM_ADMISSION_DETAILS","Users with this right can cancel the current admission from the bed info dialog");//http://jira/browse/FWB-591
	public static final AppRight CAN_EDIT_ADMITTING_DETAILS = new AppRight("CAN_EDIT_ADMITTING_DETAILS","Users with this right can edit the admitting details from the bed info dialog.");//http://jira/browse/WDEV-22326
	public static final AppRight CAN_VIEW_STAYS_FROM_ADMISSION_DETAILS = new AppRight("CAN_VIEW_STAYS_FROM_ADMISSION_DETAILS","Users with this right can use the stays dialog from the bed info dialog.");//http://jira/browse/WDEV-22325
	public static final AppRight CAN_CANCEL_ANY_CASENOTE_REQUEST = new AppRight("CAN_CANCEL_ANY_CASENOTE_REQUEST","Users with this right can cancel any case note request.");//http://jira/browse/FWB-597
	public static final AppRight CAN_RIE_PATIENT_DOCUMENTS = new AppRight("CAN_RIE_PATIENT_DOCUMENTS","Users with this right can RIE patient documents.");//http://jira/browse/FWB-595
	public static final AppRight ED_AUTO_SWITCH_TO_ATTENDANCE_DETAILS = new AppRight("ED_AUTO_SWITCH_TO_ATTENDANCE_DETAILS","Automatically switches to the Attendance Details form when saving demographics.");//http://jira/browse/FWB-602
	public static final AppRight CAN_EDIT_REFERRAL_DATE = new AppRight("CAN_EDIT_REFERRAL_DATE", "Users with this right can edit the referral date on the Referral Details form.");  // FWB-605
	public static final AppRight REFERRAL_TRIAGE_ACTION_CAN_ACCEPT = new AppRight("REFERRAL_TRIAGE_ACTION_CAN_ACCEPT", "Users with this right can Accept on the Referral Triage form.");  // FWB-608
	public static final AppRight REFERRAL_TRIAGE_ACTION_CAN_REJECT = new AppRight("REFERRAL_TRIAGE_ACTION_CAN_REJECT", "Users with this right can Reject on the Referral Triage form.");  // FWB-608
	public static final AppRight REFERRAL_TRIAGE_ACTION_CAN_REDIRECT = new AppRight("REFERRAL_TRIAGE_ACTION_CAN_REDIRECT", "Users with this right can Redirect on the Referral Triage form.");  // FWB-608
	
	static
	{
		RIGHTS.put(REFERRAL_TRIAGE_ACTION_CAN_ACCEPT.getName(), REFERRAL_TRIAGE_ACTION_CAN_ACCEPT);//FWB-608
		RIGHTS.put(REFERRAL_TRIAGE_ACTION_CAN_REJECT.getName(), REFERRAL_TRIAGE_ACTION_CAN_REJECT);//FWB-608
		RIGHTS.put(REFERRAL_TRIAGE_ACTION_CAN_REDIRECT.getName(), REFERRAL_TRIAGE_ACTION_CAN_REDIRECT);//FWB-608
		RIGHTS.put(ED_AUTO_SWITCH_TO_ATTENDANCE_DETAILS.getName(), ED_AUTO_SWITCH_TO_ATTENDANCE_DETAILS);//FWB-602
		RIGHTS.put(CAN_RIE_PATIENT_DOCUMENTS.getName(), CAN_RIE_PATIENT_DOCUMENTS);//FWB-595
		RIGHTS.put(CAN_CANCEL_ANY_CASENOTE_REQUEST.getName(), CAN_CANCEL_ANY_CASENOTE_REQUEST);//FWB-597
		RIGHTS.put(CAN_CANCEL_FROM_ADMISSION_DETAILS.getName(), CAN_CANCEL_FROM_ADMISSION_DETAILS);//FWB-591
		RIGHTS.put(CAN_EDIT_ADMITTING_DETAILS.getName(), CAN_EDIT_ADMITTING_DETAILS);//WDEV-22326
		RIGHTS.put(CAN_VIEW_STAYS_FROM_ADMISSION_DETAILS.getName(), CAN_VIEW_STAYS_FROM_ADMISSION_DETAILS);//WDEV-22325
		RIGHTS.put(PDS_ACCESS_RECORD_ON_DISSENT.getName(), PDS_ACCESS_RECORD_ON_DISSENT);//FWB-577
		RIGHTS.put(CAN_INACTIVATE_PATIENT.getName(), CAN_INACTIVATE_PATIENT);//WDEV-20689
		RIGHTS.put(CAN_REFRESH_CONTRACT_CODING.getName(), CAN_REFRESH_CONTRACT_CODING);//FWB-471
		RIGHTS.put(OCS_ALLOW_INV_SEARCH.getName(), OCS_ALLOW_INV_SEARCH);
		RIGHTS.put(ALLOW_ORDERINVESTIGATIONS_CAREUK.getName(), ALLOW_ORDERINVESTIGATIONS_CAREUK);  // FWB-157
		RIGHTS.put(LOOKUP_TEXT_MODIFY.getName(), LOOKUP_TEXT_MODIFY);
		RIGHTS.put(REMOVE_LOCATION_SERVICE_ACTFUNC.getName(), REMOVE_LOCATION_SERVICE_ACTFUNC);
		RIGHTS.put(MARK_RESULT_AS_CHECKED.getName(), MARK_RESULT_AS_CHECKED);
		RIGHTS.put(OCS_CHANGE_INVESTIGATION.getName(), OCS_CHANGE_INVESTIGATION);
	//	RIGHTS.put(CAN_AUTHORIZE.getName(), CAN_AUTHORIZE); //FWB-335
		RIGHTS.put(CAN_AUTHORIZE_PATHOLOGY_ORDERS.getName(), CAN_AUTHORIZE_PATHOLOGY_ORDERS); //FWB-335 
		RIGHTS.put(CAN_AUTHORIZE_CLINICAL_IMAGING_ORDERS.getName(), CAN_AUTHORIZE_CLINICAL_IMAGING_ORDERS);//FWB-335 
		RIGHTS.put(CAN_AUTHORIZE_PROCEDURE.getName(), CAN_AUTHORIZE_PROCEDURE); //WDEV-14113 
		RIGHTS.put(CAN_CONFIRM_NURSING_DOCUMENTATION.getName(), CAN_CONFIRM_NURSING_DOCUMENTATION);
		RIGHTS.put(CAN_UNAPPROVE_NTPF_INVOICE.getName(), CAN_UNAPPROVE_NTPF_INVOICE);
		RIGHTS.put(CAN_EDIT_PROCEDURE_NAME.getName(), CAN_EDIT_PROCEDURE_NAME);
		RIGHTS.put(CAN_REMOVE_PTR_WAITING_LIST_ENTRY.getName(), CAN_REMOVE_PTR_WAITING_LIST_ENTRY);
		RIGHTS.put(CAN_UPDATE_RESULT_STATUS.getName(), CAN_UPDATE_RESULT_STATUS);
		RIGHTS.put(CAN_ADD_CONTACT_AFTER_ENDDATE_SUPPLIED.getName(), CAN_ADD_CONTACT_AFTER_ENDDATE_SUPPLIED);
//FWB-956		RIGHTS.put(CAN_EDIT_REFERRING_CONSULTANT.getName(), CAN_EDIT_REFERRING_CONSULTANT);
		RIGHTS.put(CAN_VIEW_CONFIDENTIAL_INVESTIGATIONS_ORDERED.getName(),CAN_VIEW_CONFIDENTIAL_INVESTIGATIONS_ORDERED);
		RIGHTS.put(CAN_VIEW_CONFIDENTIAL_INVESTIGATION_RESULTS.getName(), CAN_VIEW_CONFIDENTIAL_INVESTIGATION_RESULTS);
		RIGHTS.put(CAN_APPROVE_INVOICE_WITHOUT_TC_ACCREDITATION.getName(),CAN_APPROVE_INVOICE_WITHOUT_TC_ACCREDITATION);
		RIGHTS.put(CAN_SUBSCRIBE_TO_SHARED_DICTIONARIES.getName(),CAN_SUBSCRIBE_TO_SHARED_DICTIONARIES);
		RIGHTS.put(CAN_ADD_WORDS.getName(),CAN_ADD_WORDS);
		RIGHTS.put(CAN_UNDISCHARGE_EPISODE.getName(),CAN_UNDISCHARGE_EPISODE);
		RIGHTS.put(CAN_ACCESS_PRICE_ADJUSTMENT.getName(),CAN_ACCESS_PRICE_ADJUSTMENT);
		RIGHTS.put(CAN_REGISTER_CHILD_FROM_CLIENT_RECORD.getName(),CAN_REGISTER_CHILD_FROM_CLIENT_RECORD);
		RIGHTS.put(CAN_RECORD_NCP_COMMENT.getName(),CAN_RECORD_NCP_COMMENT);
		RIGHTS.put(CAN_ACCEPT_CATS_REFERRAL.getName(), CAN_ACCEPT_CATS_REFERRAL);
		RIGHTS.put(CAN_REJECT_CATS_REFERRAL.getName(), CAN_REJECT_CATS_REFERRAL);
		RIGHTS.put(CAN_REQUEST_SECOND_OPINION_FOR_CATS_REFERRAL.getName(), CAN_REQUEST_SECOND_OPINION_FOR_CATS_REFERRAL);
		RIGHTS.put(ALLOW_BEDMAINTENANCE_ADTWARDVIEW.getName(),ALLOW_BEDMAINTENANCE_ADTWARDVIEW);
		RIGHTS.put(ALLOW_EDIT_CONSULTATION_DIAGNOSIS.getName(), ALLOW_EDIT_CONSULTATION_DIAGNOSIS);
		RIGHTS.put(ALLOW_MARK_PATIENT_AS_DECEASED.getName(), ALLOW_MARK_PATIENT_AS_DECEASED);
		RIGHTS.put(CAN_OVERBOOK_THEATRE_APPTS.getName(), CAN_OVERBOOK_THEATRE_APPTS);
		RIGHTS.put(ALLOWED_TO_CLEAR_VACCINE_DETAILS.getName(), ALLOWED_TO_CLEAR_VACCINE_DETAILS);
		RIGHTS.put(CAN_ALLOCATE_WORKLIST_TASKS.getName(), CAN_ALLOCATE_WORKLIST_TASKS);
		RIGHTS.put(CAN_UNDO_DISCHARGE.getName(), CAN_UNDO_DISCHARGE);
		RIGHTS.put(CAN_DISCHARGE_INPATIENT_IN_RCHT.getName(), CAN_DISCHARGE_INPATIENT_IN_RCHT);
		RIGHTS.put(CAN_OVERBOOK_FLEXIBLE_APPT.getName(), CAN_OVERBOOK_FLEXIBLE_APPT);
		RIGHTS.put(ALLOW_CANCEL_ORDERINVESTIGATION.getName(), ALLOW_CANCEL_ORDERINVESTIGATION);
		RIGHTS.put(ALLOW_ADT_PENDINGEMERGENCY_REMOVAL.getName(), ALLOW_ADT_PENDINGEMERGENCY_REMOVAL);
		RIGHTS.put(CAN_EDIT_INITIALLY_SEEN_BY.getName(), CAN_EDIT_INITIALLY_SEEN_BY);
		RIGHTS.put(CAN_REVIEW_GP_DISCHARGE_OUTCOME.getName(), CAN_REVIEW_GP_DISCHARGE_OUTCOME);
		RIGHTS.put(CAN_REVIEW_AND_REMOVE_DNA_APPTS.getName(), CAN_REVIEW_AND_REMOVE_DNA_APPTS);
		RIGHTS.put(CAN_REVIEW_AND_REMOVE_CANCELLED_APPTS.getName(), CAN_REVIEW_AND_REMOVE_CANCELLED_APPTS);
		RIGHTS.put(CAN_RESET_PROV_CANC_AND_REF_REJECT.getName(), CAN_RESET_PROV_CANC_AND_REF_REJECT);
		RIGHTS.put(CAN_VIEW_CLINICS_ACROSS_ALL_CONTRACTS.getName(),CAN_VIEW_CLINICS_ACROSS_ALL_CONTRACTS);
		RIGHTS.put(CAN_UNDO_ASSESSMENT_COMPLETION.getName(),CAN_UNDO_ASSESSMENT_COMPLETION);
		RIGHTS.put(NURSING_ADMIN_ROLE.getName(),NURSING_ADMIN_ROLE);
		RIGHTS.put(CAN_COUNTERSIGN.getName(),CAN_COUNTERSIGN);
		RIGHTS.put(AUTHORISED_DATA_CORRECTOR.getName(),AUTHORISED_DATA_CORRECTOR);
		RIGHTS.put(CAN_UNLINK_REFERRAL_FROM_EPISODE.getName(),CAN_UNLINK_REFERRAL_FROM_EPISODE);
		RIGHTS.put(CAN_EDIT_CATS_REPORT_DOCUMENT_SENT_DATE.getName(),CAN_EDIT_CATS_REPORT_DOCUMENT_SENT_DATE);
		RIGHTS.put(PHARMACY_CAN_APPROVE.getName(), PHARMACY_CAN_APPROVE);		
		RIGHTS.put(PHARMACY_CAN_REJECT.getName(), PHARMACY_CAN_REJECT);
		RIGHTS.put(PHARMACY_CAN_SUSPENDED.getName(), PHARMACY_CAN_SUSPENDED);
		RIGHTS.put(PHARMACY_CAN_PRINT_REQUEST.getName(), PHARMACY_CAN_PRINT_REQUEST);
		RIGHTS.put(PHARMACY_CAN_ENTER_PHARMACY_SYSTEM_DETAILS.getName(), PHARMACY_CAN_ENTER_PHARMACY_SYSTEM_DETAILS);
		RIGHTS.put(PHARMACY_CAN_DISPENSED_TTA.getName(), PHARMACY_CAN_DISPENSED_TTA);
		RIGHTS.put(PHARMACY_CAN_DO_FINAL_CHECKS.getName(), PHARMACY_CAN_DO_FINAL_CHECKS);
		RIGHTS.put(PHARMACY_CAN_ACCESS_PHARMACY_TAB.getName(), PHARMACY_CAN_ACCESS_PHARMACY_TAB);
		RIGHTS.put(ALLOW_LINK_DISCHARGE_TO_INVOICE.getName(), ALLOW_LINK_DISCHARGE_TO_INVOICE);
		RIGHTS.put(CAN_PRINT_DISCHARGE_FROM_WARD_PACU_SCREEN.getName(),CAN_PRINT_DISCHARGE_FROM_WARD_PACU_SCREEN);
		RIGHTS.put(ALLOW_EDIT_IMAGES.getName(), ALLOW_EDIT_IMAGES);
		RIGHTS.put(ALLOW_ANAESTHETIC_TYPE_CHANGE.getName(),ALLOW_ANAESTHETIC_TYPE_CHANGE);
		RIGHTS.put(CAN_UNDO_RACPC_CLINICAL_DETAILS_COMPLETE.getName(),CAN_UNDO_RACPC_CLINICAL_DETAILS_COMPLETE);
		RIGHTS.put(CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE.getName(),CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE);
		RIGHTS.put(ALLOW_DELETION_OF_APPOINTMENT_CLINICAL_NOTES.getName(),ALLOW_DELETION_OF_APPOINTMENT_CLINICAL_NOTES); //FWB-351
		RIGHTS.put(CAN_ADD_RESULT_ANNOTATIONS.getName(),CAN_ADD_RESULT_ANNOTATIONS); //FWB-359
		RIGHTS.put(CAN_CORRECT_RESULT_ANNOTATIONS.getName(),CAN_CORRECT_RESULT_ANNOTATIONS); //FWB-364
		RIGHTS.put(CAN_ALLOCATE_PATIENT_CLERICAL_TASKS.getName(),CAN_ALLOCATE_PATIENT_CLERICAL_TASKS); //FWB-360
		RIGHTS.put(CAN_REMOVE_DRUG_FROM_PRESCRIPTION.getName(),CAN_REMOVE_DRUG_FROM_PRESCRIPTION); //FWB-365
		RIGHTS.put(ED_ALLOW_CORRECTION_OF_ANY_TRIAGE_NOTE.getName(),ED_ALLOW_CORRECTION_OF_ANY_TRIAGE_NOTE); //FWB-385
		RIGHTS.put(ED_ALLOW_CORRECTION_OF_ANY_MEDIC_NOTE.getName(),ED_ALLOW_CORRECTION_OF_ANY_MEDIC_NOTE); //FWB-386
		RIGHTS.put(ED_CAN_VIEW_TRACKING_ACROSS_ALL_ED_SITES.getName(),ED_CAN_VIEW_TRACKING_ACROSS_ALL_ED_SITES); //FWB-388
		RIGHTS.put(DISCHARGE_ACCESS_TO_VTE_ASSESSMENT.getName(),DISCHARGE_ACCESS_TO_VTE_ASSESSMENT); //FWB-390
		RIGHTS.put(CAN_EDIT_ALL_CLINICAL_NOTES.getName(),CAN_EDIT_ALL_CLINICAL_NOTES); //FWB-399
		RIGHTS.put(CC_ACCESS_ALL_REFERRALS.getName(),CC_ACCESS_ALL_REFERRALS); //FWB-402
		RIGHTS.put(CC_ACCESS_CYPS_REFERRALS.getName(),CC_ACCESS_CYPS_REFERRALS); //FWB-402
		RIGHTS.put(ALLOW_USER_PASSWORD_UPDATE_ONLY.getName(),ALLOW_USER_PASSWORD_UPDATE_ONLY); //http://jira/browse/FWB-414
		RIGHTS.put(RESET_RESULT_STATUS_TO_NEW.getName(),RESET_RESULT_STATUS_TO_NEW); //http://jira/browse/FWB-416
		RIGHTS.put(CAN_ATTACH_APPOINTMENT_TO_ICP.getName(),CAN_ATTACH_APPOINTMENT_TO_ICP); //http://jira/browse/FWB-424
		RIGHTS.put(CAN_EDIT_AND_RIE_DEMENTIA.getName(),CAN_EDIT_AND_RIE_DEMENTIA); //http://jira/browse/FWB-430
		RIGHTS.put(MAXIMS_ADT_CAN_DISCHARGE.getName(),MAXIMS_ADT_CAN_DISCHARGE); //http://jira/browse/FWB-444
		RIGHTS.put(ED_CAN_PERFORM_ADMISSION.getName(),ED_CAN_PERFORM_ADMISSION); //http://jira/browse/FWB-450
		RIGHTS.put(ED_CAN_PERFORM_TRANSFER.getName(),ED_CAN_PERFORM_TRANSFER); //http://jira/browse/FWB-450
		RIGHTS.put(CAN_AUTHORISE_EAS.getName(),CAN_AUTHORISE_EAS); //http://jira/browse/FWB-453
		RIGHTS.put(ED_CAN_UNDO_ED_DISCHARGE.getName(),ED_CAN_UNDO_ED_DISCHARGE); //http://jira/browse/FWD-229
		RIGHTS.put(ED_ATTENDENCE_NOTES_CAN_RIE_DIAGNOSIS.getName(),ED_ATTENDENCE_NOTES_CAN_RIE_DIAGNOSIS); //http://jira/browse/FWB-456
		RIGHTS.put(CANNOT_EDIT_ALLERGIES_COMPONENT.getName(),CANNOT_EDIT_ALLERGIES_COMPONENT); //http://jira/browse/FWB-465
		RIGHTS.put(CANNOT_EDIT_ALERTS_COMPONENT.getName(),CANNOT_EDIT_ALERTS_COMPONENT); //http://jira/browse/FWB-465
		RIGHTS.put(CAN_VIEW_ALL_LISTOWNERS.getName(),CAN_VIEW_ALL_LISTOWNERS); //http://jira/browse/FWB-474
		RIGHTS.put(CAN_DIRECTLY_REJECT_REFERRAL.getName(),CAN_DIRECTLY_REJECT_REFERRAL); //http://jira/browse/WDEV-17896 
		RIGHTS.put(HAN_CAN_ALLOCATE_TASKS.getName(),HAN_CAN_ALLOCATE_TASKS); //http://jira/browse/FWB-479
		RIGHTS.put(ALLOW_DELETION_OF_ELECTIVE_LIST_NOTES.getName(),ALLOW_DELETION_OF_ELECTIVE_LIST_NOTES); //http://jira/browse/FWB-485
		RIGHTS.put(ALLOW_NEW_NAES_ACTION_PLAN.getName(),ALLOW_NEW_NAES_ACTION_PLAN); //http://jira/browse/FWB-503
		RIGHTS.put(ED_CAN_PRESCRIBE.getName(),ED_CAN_PRESCRIBE); //http://jira/browse/FWB-504
		RIGHTS.put(VIEW_OTHER_CODED_RECORDS.getName(),VIEW_OTHER_CODED_RECORDS); //http://jira/browse/FWB-540
		RIGHTS.put(ALLOW_UNDO_REFERRAL_CLOCKIMPACT.getName(),ALLOW_UNDO_REFERRAL_CLOCKIMPACT); //http://jira/browse/FWB-541
		RIGHTS.put(CAN_UNDO_APPOINTMENT_OUTCOME.getName(),CAN_UNDO_APPOINTMENT_OUTCOME ); //http://jira/browse/FWB-545
		RIGHTS.put(CAN_RECORD_OVERALL_PATHWAY_BREACH_REASON.getName(),CAN_RECORD_OVERALL_PATHWAY_BREACH_REASON ); //http://jira/browse/FWB-546
		RIGHTS.put(PATHWAY_ALLOW_CORRECTION_OF_ANY_BREACH_REASON.getName(),PATHWAY_ALLOW_CORRECTION_OF_ANY_BREACH_REASON ); //http://jira/browse/FWB-547
		RIGHTS.put(BED_MANAGEMENT_CAN_CLOSE_WARD_BAYS.getName(),BED_MANAGEMENT_CAN_CLOSE_WARD_BAYS ); //http://jira/browse/FWB-555
		RIGHTS.put(BED_MANAGEMENT_CAN_BLOCK_WARD_BAYS.getName(),BED_MANAGEMENT_CAN_BLOCK_WARD_BAYS ); //http://jira/browse/FWB-555
		RIGHTS.put(BED_MANAGEMENT_CAN_REOPEN_WARD_BAYS_OOH.getName(),BED_MANAGEMENT_CAN_REOPEN_WARD_BAYS_OOH ); //http://jira/browse/FWB-555
		RIGHTS.put(PDS_SYNCHRONISE_INTERACTIVE.getName(),PDS_SYNCHRONISE_INTERACTIVE ); //http://jira/browse/FWB-566
		RIGHTS.put(PDS_ACCESS_SENSITIVE_RECORDS.getName(),PDS_ACCESS_SENSITIVE_RECORDS ); //http://jira/browse/FWB-566
		RIGHTS.put(PDS_RECORD_TEMP_SENSITIVE_ADDRESS.getName(),PDS_RECORD_TEMP_SENSITIVE_ADDRESS ); //http://jira/browse/FWB-566
		RIGHTS.put(CAN_UPDATE_REFERRAL_URGENCY.getName(),CAN_UPDATE_REFERRAL_URGENCY ); //http://jira/browse/FWB-568
		RIGHTS.put(REFERRALS_CAN_PERFORM_DOWNGRADE.getName(),REFERRALS_CAN_PERFORM_DOWNGRADE ); //http://jira/browse/FWB-569
		RIGHTS.put(REFERRALS_CAN_PERFORM_CONSULTANT_UPGRADE.getName(),REFERRALS_CAN_PERFORM_CONSULTANT_UPGRADE ); //http://jira/browse/FWB-569
		RIGHTS.put(PDS_ACCESS_UNDECEASE.getName(),PDS_ACCESS_UNDECEASE ); //http://jira/browse/FWB-571
		RIGHTS.put(CAN_RECORD_REFERRAL_ADMIN_EVENT.getName(),CAN_RECORD_REFERRAL_ADMIN_EVENT); //http://jira/browse/FWB-572
		RIGHTS.put(PENDING_EMERGENCY_CAN_RECORD_REQUEST_FOR_REPATRIATION.getName(),PENDING_EMERGENCY_CAN_RECORD_REQUEST_FOR_REPATRIATION); //http://jira/browse/FWB-573
		RIGHTS.put(PENDING_EMERGENCY_CAN_RECORD_CLINICAL_NEED.getName(),PENDING_EMERGENCY_CAN_RECORD_CLINICAL_NEED); //http://jira/browse/FWB-574
		RIGHTS.put(CAN_EDIT_REFERRAL_DATE.getName(), CAN_EDIT_REFERRAL_DATE); // FWB-605
	}
	
	private String name;
	private String comment;
	
	public AppRight()
	{
	}
	public AppRight(String rightName, String rightComment)	
	{
		this.name = rightName;
		this.comment = rightComment;
	}
	
	public String toString()
	{
		return this.getName();
	}
	public String getName()
	{
		return name;
	}
	public String getComment()
	{
		return comment;
	}
	
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof AppRight))
			return false;
		AppRight comp = (AppRight)o;
		return comp.getName().equals(this.getName());
	}
	
	public int hashCode()
	{
		return this.getName().hashCode();
	}
	public int compareTo(Object o)
	{
		if (o == null || !(o instanceof AppRight))
			return -1;
		AppRight comp = (AppRight)o;
		return comp.getName().compareTo(this.getName());
	}
	
	public static AppRight[] getAllRights()
	{
		AppRight[] ret = new AppRight[RIGHTS.size()];
		Iterator iter = RIGHTS.values().iterator();
		int i = 0;
		while (iter.hasNext())
		{
			AppRight right = (AppRight)iter.next();
			ret[i] = right;
			i++;
		}
		return ret;
	}
	
	public static AppRight getRight(String name)
	{
		return RIGHTS.get(name);
	}
}
