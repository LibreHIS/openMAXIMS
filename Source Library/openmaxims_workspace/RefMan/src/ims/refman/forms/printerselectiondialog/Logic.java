//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Catalin Tomozei using IMS Development Environment (version 1.66 build 3233.21563)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.forms.printerselectiondialog;

import ims.RefMan.helper.CatsReferralReportBuilder;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.CatsReferralReportsVo;
import ims.RefMan.vo.ReferralOutcomeVo;
import ims.RefMan.vo.enums.ReferralOutcomeReportType;
import ims.RefMan.vo.lookups.ReportStatus;
import ims.configuration.gen.ConfigFlag;
import ims.core.resource.people.vo.MemberOfStaffRefVo;
import ims.core.vo.PatientDocumentVo;
import ims.core.vo.ServerDocumentVo;
import ims.core.vo.lookups.DocumentCategory;
import ims.core.vo.lookups.DocumentCreationType;
import ims.core.vo.lookups.FileType;
import ims.core.vo.lookups.PreActiveActiveInactiveStatus;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.PrinterScope;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.interfaces.ILocation;
import ims.framework.utils.Date;
import ims.framework.utils.DateTime;
import ims.ocrr.orderingresults.vo.OrderInvestigationRefVoCollection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;

import com.ims.query.builder.client.QueryBuilderClient;
import com.ims.query.builder.client.SeedValue;
import com.ims.query.builder.client.exceptions.QueryBuilderClientException;
import com.ims.query.server.ResultCollection;
import com.ims.query.server.ResultHolder;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(Logic.class);

	private static final Integer CATS_FINAL_REPORT_ID = 130;
	private static final Integer CATS_INTERIM_REPORT_ID = 131;
	private static final Integer RESULT_INVESTIGATION_REPORT = 309;


	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Form logic
	// --------------------------------------------------------------------------------------------------------------------------------

	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		ILocation locs = domain.getCurrentLocation();
		form.lblLoc().setValue(locs != null ? locs.getName() : "No location selected");

		// WDEV-12536 - Initialise printer custom control
		form.ccPrinter().initialize(locs, PrinterScope.DEFAULT);
	}

	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}

	@Override
	protected void onBtnPrintClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (form.ccPrinter().getSelectedPrinter() == null)
		{
			catchErrors("No printer selected", SystemLogLevel.ERROR);
			return;
		}

		if (ConfigFlag.GEN.PDF_UPLOAD_URL.getValue() == "")
		{
			catchErrors("PDF_UPLOAD_URL flag need to be set", SystemLogLevel.ERROR);
			return;
		}

		String fileName = generateName() + ".pdf";
		String patientDocumentName = null;
		DocumentCategory patientDocumentCategory = null;


		Object[] coverReport = null;
		QueryBuilderClient client = new QueryBuilderClient(ConfigFlag.GEN.QUERY_SERVER_URL.getValue(), engine.getSessionId());

		ReferralOutcomeReportType reportType = form.getGlobalContext().RefMan.getReferralOutcomeReportType();
		form.getLocalContext().setReferralOutcomeReportType(reportType);
		Integer reportID = 0;

		if (reportType.equals(ReferralOutcomeReportType.INTERIM))
		{
			patientDocumentName = "Cats Interim Discharge Report";
			patientDocumentCategory = DocumentCategory.CATS_INTERIM_REPORT;

			printCatsReportAndInvestigations(client, CATS_INTERIM_REPORT_ID, form.getGlobalContext().RefMan.getCatsReferral(), fileName, patientDocumentName, patientDocumentCategory, form.ccPrinter().getSelectedPrinter().getIPrinterName());
			return;
		}
		if (reportType.equals(ReferralOutcomeReportType.FINAL))
		{
			patientDocumentName = "CATS Report";
			patientDocumentCategory = DocumentCategory.CATS_FINAL_REPORT;

			printCatsReportAndInvestigations(client, CATS_FINAL_REPORT_ID, form.getGlobalContext().RefMan.getCatsReferral(), fileName, patientDocumentName, patientDocumentCategory, form.ccPrinter().getSelectedPrinter().getIPrinterName());
			return;
		}
		if (reportType.equals(ReferralOutcomeReportType.PRESCRIPTIONS))
		{
			patientDocumentName = "FP10 - Prescriptions";
			patientDocumentCategory = DocumentCategory.PRESCRIPTIONS;
			reportID = 132;
			coverReport = domain.getSystemReportAndTemplate(new Integer(132));
			client.addSeed(new SeedValue("CatsReferral_id", form.getGlobalContext().RefMan.getCatsReferral().getID_CatsReferral(), Integer.class));
		}

		if (reportType.equals(ReferralOutcomeReportType.REJECTIONS))
		{
			patientDocumentName = "Rejection Notification Letter";
			patientDocumentCategory = DocumentCategory.REJECTIONS;
			reportID = 157;
			coverReport = domain.getSystemReportAndTemplate(new Integer(157));
			client.addSeed(new SeedValue("CatsReferral_id", form.getGlobalContext().RefMan.getCatsReferral().getID_CatsReferral(), Integer.class));
		}

		if (coverReport == null || coverReport.length < 2)
		{
			engine.showMessage("I could not get the report and template !");
			return;
		}

		if (coverReport[0] == null || coverReport[1] == null)
		{
			engine.showMessage("The report has not been deployed !");
			return;
		}

		String[] catsDischargeReport = domain.getSystemReportAndTemplate(reportID);

		if (coverReport != null && ((String) coverReport[2]).length() > 0)
			patientDocumentName = (String) coverReport[2];

		if (catsDischargeReport == null)
		{
			engine.showMessage("No template was assigned to this dialog !");
			return;
		}

		try
		{
			String url = client.buildReportAsUrl((String) coverReport[0], (String) coverReport[1], ConfigFlag.GEN.REPORT_SERVER_URL.getValue(), "PDF", form.ccPrinter().getSelectedPrinter().getIPrinterName(), 1);
			if (url != null)
			{
				engine.openUrl(url);
			}

			byte[] buffer = client.buildReport((String) coverReport[0], (String) coverReport[1], ConfigFlag.GEN.REPORT_SERVER_URL.getValue(), "PDF", "", 1);

			if (buffer == null || (buffer != null && buffer.length == 0))
			{
				catchErrors("PDF size is zero", SystemLogLevel.ERROR);
				return;
			}

			try
			{
				engine.uploadFile(ConfigFlag.GEN.PDF_UPLOAD_URL.getValue(), buffer, fileName, ConfigFlag.GEN.FILE_UPLOAD_DIR.getValue());
			}
			catch (Exception e)
			{
				catchErrors(e.toString(), SystemLogLevel.ERROR);
				return;
			}

			save(fileName, patientDocumentName, patientDocumentCategory);
		}
		catch (QueryBuilderClientException e1)
		{
			engine.showMessage("Error creating report: " + e1.getMessage());
			return;
		}
	}

	@Override
	protected void onBtnPreviewClick() throws PresentationLogicException
	{
		Object[] coverReport = null;
		QueryBuilderClient client = new QueryBuilderClient(ConfigFlag.GEN.QUERY_SERVER_URL.getValue(), engine.getSessionId());

		ReferralOutcomeReportType reportType = form.getGlobalContext().RefMan.getReferralOutcomeReportType();
		form.getLocalContext().setReferralOutcomeReportType(reportType);
		Integer reportID = 0;

		if (reportType.equals(ReferralOutcomeReportType.INTERIM))
		{
			previewCatsReprotAndIvestigations(client, CATS_INTERIM_REPORT_ID, form.getGlobalContext().RefMan.getCatsReferral(), null, null, null, null);
			return;
		}
		if (reportType.equals(ReferralOutcomeReportType.FINAL))
		{
			previewCatsReprotAndIvestigations(client, CATS_FINAL_REPORT_ID, form.getGlobalContext().RefMan.getCatsReferral(), null, null, null, null);
			return;
		}
		if (reportType.equals(ReferralOutcomeReportType.PRESCRIPTIONS))
		{
			reportID = 132;
			coverReport = domain.getSystemReportAndTemplate(new Integer(132));
			client.addSeed(new SeedValue("CatsReferral_id", form.getGlobalContext().RefMan.getCatsReferral().getID_CatsReferral(), Integer.class));
		}

		if (reportType.equals(ReferralOutcomeReportType.REJECTIONS))
		{
			reportID = 157;
			coverReport = domain.getSystemReportAndTemplate(new Integer(157));
			client.addSeed(new SeedValue("CatsReferral_id", form.getGlobalContext().RefMan.getCatsReferral().getID_CatsReferral(), Integer.class));
		}

		if (coverReport == null || coverReport.length < 2)
		{
			engine.showMessage("I could not get the report and template !");
			return;
		}

		if (coverReport[0] == null || coverReport[1] == null)
		{
			engine.showMessage("The report has not been deployed !");
			return;
		}

		String[] catsDischargeReport = domain.getSystemReportAndTemplate(reportID);

		if (catsDischargeReport == null)
		{
			engine.showMessage("No template was assigned to this dialog !");
			return;
		}

		try
		{
			String url = client.buildReportAsUrl((String) coverReport[0], (String) coverReport[1], ConfigFlag.GEN.REPORT_SERVER_URL.getValue(), "PDF", "", 1);
			if (url != null)
			{
				engine.openUrl(url);
			}
		}
		catch (QueryBuilderClientException e1)
		{
			engine.showMessage("Error creating report: " + e1.getMessage());
			return;
		}
	}

	private void previewCatsReprotAndIvestigations(QueryBuilderClient queryClient, Integer reportID, CatsReferralRefVo voCatsReferral, String fileName, String patientDocumentName, DocumentCategory patientDocumentCategory, String printer)
	{
		try
		{
			byte[] buffer = buildMergedReport(queryClient, reportID, voCatsReferral, printer);

//			// Get reports
//			String[] coverReport = domain.getSystemReportAndTemplate(reportID);
//			String[] investigationReport = domain.getSystemReportAndTemplate(RESULT_INVESTIGATION_REPORT);
//			
//			// Get order investigation
//			OrderInvestigationRefVoCollection investigationList = domain.listInvestigations(voCatsReferral);
//			
//			// Build CATS Referral Discharge Report (Final or Interim)
//			CatsReferralReportBuilder reportBuilder = new CatsReferralReportBuilder();
//			byte[] buffer = reportBuilder.getCatsReferralFinalDischargeReport(voCatsReferral, investigationList, coverReport, investigationReport, engine.getSessionId()); 

			if (buffer == null)
			{
				catchErrors("PDF size is zero", SystemLogLevel.ERROR);
				return;
			}

			try
			{
				// Build checksum
				CRC32 crc = new CRC32();
				crc.reset();
				crc.update(buffer);

				// Build unique key
				String key = String.valueOf(System.currentTimeMillis());
				key += engine.getSessionId() != null ? engine.getSessionId() : "";
				key += String.valueOf(crc.getValue());

				// Add PDF byte array
				ResultCollection.putResult(key, new ResultHolder(buffer, "application/pdf"));

				// Code to preview a PDF byte array
				String urlToOpen = getQueryServerRoot() + "/ReturnAsUrlServlet?action=getResult&id=" + key + "&appservername=" + InetAddress.getLocalHost().getHostName();

				engine.openUrl(urlToOpen);
			}
			catch (UnknownHostException e)
			{
				engine.showMessage("Error creating report: " + e.getMessage());
				e.printStackTrace();
				return;
			}
		}
		catch (DomainInterfaceException e1)
		{
			engine.showMessage(e1.getMessage());
			return;
		}
		catch (QueryBuilderClientException e1)
		{
			engine.showMessage("Error creating report: " + e1.getMessage());
			return;
		}
	}

	/**
	 * WDEV-12345 Function used to build the CATS Report (merged with Resulted Investigations)
	 * <br> If a printer name is passed then the report will also be printed to that printer
	 */
	private byte[] buildMergedReport(QueryBuilderClient queryClient, Integer reportID, CatsReferralRefVo voCatsReferral, String printer) throws DomainInterfaceException, QueryBuilderClientException
	{
		// Get CATS Referral Discharge Report
		String[] coverReport = domain.getSystemReportAndTemplate(reportID);
		
		// Check for report and template
		if (coverReport == null || coverReport.length < 2)
		{
			engine.showMessage("I could not get the report and template !");
			return null;
		}

		if (coverReport[0] == null || coverReport[1] == null)
		{
			engine.showMessage("The report has not been deployed !");
			return null;
		}

		// Get investigation report
		String[] investigationReport = domain.getSystemReportAndTemplate(RESULT_INVESTIGATION_REPORT);
		
		// Check for report and template
		if (coverReport == null || coverReport.length < 2)
		{
			engine.showMessage("I could not get the report and template !");
			return null;
		}

		if (coverReport[0] == null || coverReport[1] == null)
		{
			engine.showMessage("The report has not been deployed !");
			return null;
		}

		// Get order investigation
		OrderInvestigationRefVoCollection investigationList = domain.listInvestigations(voCatsReferral);
		
		// Build CATS Referral Discharge Report (Final or Interim)
		CatsReferralReportBuilder reportBuilder = new CatsReferralReportBuilder();

		// Return content of the CATS Referral Discharge Report
		return reportBuilder.getCatsReferralFinalDischargeReport(voCatsReferral, investigationList, coverReport, investigationReport, engine.getSessionId(), printer, 1);
	}

	private String getQueryServerRoot()
	{
		String root = ConfigFlag.GEN.QUERY_SERVER_URL.getValue().trim();

		if (root.endsWith("/"))
			root = root.substring(0, root.length() - 1);

		if (root.endsWith("/ReportBuilder"))
		{
			int index = root.lastIndexOf("/");

			if (index > -1)
			{
				return root.substring(0, index);
			}
		}

		return root;
	}

	private void printCatsReportAndInvestigations(QueryBuilderClient queryClient, Integer reportID, CatsReferralRefVo voCatsReferral, String fileName, String patientDocumentName, DocumentCategory patientDocumentCategory, String printer)
	{
		try
		{
			byte[] buffer = buildMergedReport(queryClient, reportID, voCatsReferral, printer);
			
//    		// Get reports
//    		String[] coverReport = domain.getSystemReportAndTemplate(reportID);
//    		String[] investigationReport = domain.getSystemReportAndTemplate(RESULT_INVESTIGATION_REPORT);
//    		
//    		// Get order investigation
//    		OrderInvestigationRefVoCollection investigationList = domain.listInvestigations(voCatsReferral);
//    		
//    		// Build CATS Referral Discharge Report (Final or Interim)
//    		CatsReferralReportBuilder reportBuilder = new CatsReferralReportBuilder();
//    		byte[] buffer = reportBuilder.getCatsReferralFinalDischargeReport(voCatsReferral, investigationList, coverReport, investigationReport, engine.getSessionId(), printer, 1);

			// Upload PDF byte array
			engine.uploadFile(ConfigFlag.GEN.PDF_UPLOAD_URL.getValue(), buffer, fileName, ConfigFlag.GEN.FILE_UPLOAD_DIR.getValue());

			previewPDFDoc(fileName);//	WDEV-15210
		}
		catch (Exception e)
		{
			catchErrors(e.toString(), SystemLogLevel.ERROR);
			return;
		}

		save(fileName, patientDocumentName, patientDocumentCategory);
	}

	private void previewPDFDoc(String fileName)//	WDEV-15210
	{
		StringBuilder filePath = new StringBuilder();

		filePath.append(ConfigFlag.GEN.FILE_SERVER_URL.getValue());

		if (!(filePath.toString().endsWith("/") || filePath.toString().endsWith("\\")))
			filePath.append("/");
		
		Date date = new Date();
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		
		filePath.append(year + "\\" + month + "\\" + day + "\\");

		filePath.append(fileName);

		engine.openUrl(filePath.toString());
	}

	private boolean save(String fileName, String patientDocumentName, DocumentCategory patientDocumentCategory)
	{
		PatientDocumentVo vo = populatePatientDocumentVo(populateServetDocumentVo(fileName), patientDocumentName, patientDocumentCategory);

		String[] str = vo.validate();
		if (str != null && str.length > 0)
		{
			engine.showErrors(str);
			return false;
		}

		CatsReferralReportsVo catReferral = domain.getCatsReferral(form.getGlobalContext().RefMan.getCatsReferral());
		// WDEV-11282
		catReferral.setCATSReportSentDate(new DateTime());

		str = catReferral.validate();
		if (str != null && str.length > 0)
		{
			engine.showErrors(str);
			return false;
		}

		ReferralOutcomeVo voOutcome = form.getGlobalContext().RefMan.getSelectedReferralOutcomeVo();
		ReferralOutcomeReportType reportType = form.getGlobalContext().RefMan.getReferralOutcomeReportType();

		if (reportType != null)
		{
			if (reportType.equals(ReferralOutcomeReportType.FINAL))
				catReferral.setIsFinalReportRequired(ReportStatus.GENERATED);

			if (voOutcome != null && reportType.equals(ReferralOutcomeReportType.FINAL))
				voOutcome.setCatsFinalReportStatus(ReportStatus.GENERATED);
		}

		try
		{
			domain.savePatientDocument(vo, catReferral, voOutcome);
		}
		catch (StaleObjectException e)
		{
			catchErrors(e.toString(), SystemLogLevel.ERROR);
			return false;
		}

		CatsReferralReportsVo catReferralTemp = domain.getCatsReferral(form.getGlobalContext().RefMan.getCatsReferral()); // wdev-13804
		form.getGlobalContext().RefMan.setCatsReferral(catReferralTemp); // wdev-13804
		engine.close(DialogResult.OK);
		return true;
	}

	private ServerDocumentVo populateServetDocumentVo(String fileName)
	{
		DateTime date = new DateTime();
		int year = date.getDate().getYear();
		int month = date.getDate().getMonth();
		int day = date.getDate().getDay();

		ServerDocumentVo vo = new ServerDocumentVo();
		String filePath = year + "/" + month + "/" + day + "/" + fileName;
		vo.setFileName(filePath);
		vo.setFileType(FileType.PDF);
		return vo;
	}

	private PatientDocumentVo populatePatientDocumentVo(ServerDocumentVo serverDocumentVo, String patientDocumentName, DocumentCategory patientDocumentCategory)
	{
		PatientDocumentVo vo = new PatientDocumentVo();
		vo.setPatient(form.getGlobalContext().Core.getPatientShort());
		vo.setEpisodeofCare(form.getGlobalContext().Core.getEpisodeofCareShort());
		vo.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		vo.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		vo.setReferral(null);

		vo.setName(patientDocumentName);
		vo.setServerDocument(serverDocumentVo);
		vo.setCreationType(DocumentCreationType.GENERATED);
		vo.setCategory(patientDocumentCategory);

		vo.setRecordingUser((MemberOfStaffRefVo) domain.getMosUser(engine.getLoggedInUser().getUsername()));
		vo.setRecordingDateTime(new DateTime());
		vo.setStatus(PreActiveActiveInactiveStatus.ACTIVE);
		return vo;
	}

	private void catchErrors(String error, SystemLogLevel level)
	{
		if (level.equals(SystemLogLevel.ERROR))
		{
			engine.showMessage(error);
		}

		LOG.error(error);
		engine.createSystemLogEntry(SystemLogType.FILE_UPLOADING, level, error);
	}

	private String generateName()
	{
		String str = "";

		try
		{
			// Get Random Segment
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			str += Integer.toHexString(prng.nextInt());
			while (str.length() < 8)
			{
				str = '0' + str;
			}

			// Get CurrentTimeMillis() segment
			str += Long.toHexString(System.currentTimeMillis());
			while (str.length() < 12)
			{
				str = '0' + str;
			}

			// Get Random Segment
			SecureRandom secondPrng = SecureRandom.getInstance("SHA1PRNG");
			str += Integer.toHexString(secondPrng.nextInt());
			while (str.length() < 8)
			{
				str = '0' + str;
			}

			// Get IdentityHash() segment
			str += Long.toHexString(System.identityHashCode((Object) this));
			while (str.length() < 8)
			{
				str = '0' + str;
			}
			// Get Third Random Segment
			byte bytes[] = new byte[16];
			SecureRandom thirdPrng = SecureRandom.getInstance("SHA1PRNG");
			thirdPrng.nextBytes(bytes);
			str += Integer.toHexString(thirdPrng.nextInt());
			while (str.length() < 8)
			{
				str = '0' + str;
			}
		}
		catch (java.security.NoSuchAlgorithmException ex)
		{
			ex.getMessage();
		}

		return str;
	}
}
