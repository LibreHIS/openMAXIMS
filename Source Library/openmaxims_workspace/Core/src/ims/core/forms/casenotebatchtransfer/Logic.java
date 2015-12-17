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
// This code was generated by Bogdan Tofei using IMS Development Environment (version 1.80 build 5332.26009)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.

package ims.core.forms.casenotebatchtransfer;

import ims.configuration.gen.ConfigFlag;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.MemberOfStaffLiteVo;
import ims.core.vo.PatientCaseNoteBatchTransferVo;
import ims.core.vo.PatientCaseNoteBatchTransferVoCollection;
import ims.core.vo.PatientCaseNoteCommentSaveVo;
import ims.core.vo.PatientCaseNoteTransferVo;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.DialogResult;
import ims.framework.utils.Date;
import ims.framework.utils.DateTime;

import java.util.ArrayList;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialise();
	}

	private void initialise()
	{
		Object mosUser = domain.getMosUser();
		
		if(mosUser instanceof MemberOfStaffLiteVo)
		{
			form.getLocalContext().setCurrentMOS((MemberOfStaffLiteVo) mosUser);
		}
		
		form.dtimTransfer().setValue(new DateTime());
		form.txtTransferredBy().setValue(form.getLocalContext().getCurrentMOS() != null ? form.getLocalContext().getCurrentMOS().getIMosName() : "");
		
		form.btnTransfer().setImage(form.getImages().Core.transfer_up_down22x22);
		form.btnCancel().setImage(form.getImages().Emergency.CORECTNOTE16);
		
	}

	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}

	@Override
	protected void onBtnTransferClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (validateUIRules())
		{
			saveTransfers();
			engine.close(DialogResult.OK);
		}
	}

	private boolean saveTransfers()
	{

		if (form.getGlobalContext().Core.getPatientCaseNotesForBatchTransfer() == null || form.getGlobalContext().Core.getPatientCaseNotesForBatchTransfer().size() == 0)
			return false;

		PatientCaseNoteBatchTransferVoCollection patientCaseNotes = form.getGlobalContext().Core.getPatientCaseNotesForBatchTransfer();

		for (int i = 0; i < patientCaseNotes.size(); i++)
		{

			PatientCaseNoteBatchTransferVo caseNote = patientCaseNotes.get(i);

			if (caseNote == null)
				continue;

			// create the transfer
			PatientCaseNoteTransferVo record = populateTransferData(caseNote);

			String[] errors = record.validate();

			if (errors != null && errors.length > 0)
			{
				engine.showErrors("Transfer Validation Errors", errors);
				return false;
			}

			try
			{
				domain.saveTransfer(record, false);
			}
			catch (StaleObjectException e)
			{
				engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
				return false;
			}
		}

		return true;
	}

	
	private boolean validateUIRules() 
	{
		ArrayList<String> uiErrors = new ArrayList<String>();
		
		if(form.getLocalContext().getCurrentMOS() == null)
		{
			uiErrors.add("Logged in user must be a MOS.");
		}
		
		if(form.dtimTransfer().getValue() == null )
		{
			uiErrors.add("Transfer Date is mandatory.");
		}
		
		if(form.dtimTransfer().getValue() != null &&  form.dtimTransfer().getValue().getDate().isLessThan(new Date()))
		{
			uiErrors.add("Transfer Date cannot be set in the past.");
		}
		
		if (form.qmbLocation().getValue() == null )
		{
			uiErrors.add("Transfer to Location is mandatory.");
		}
				
		String[] uiResults = new String[uiErrors.size()];
		uiErrors.toArray(uiResults);
		
		if (uiResults != null && uiResults.length > 0)
		{
			engine.showErrors(uiResults);
			return false;
		}
		
		return true;
	}
	
	private PatientCaseNoteTransferVo populateTransferData(PatientCaseNoteBatchTransferVo caseNote)
	{
		PatientCaseNoteTransferVo transfer =  new PatientCaseNoteTransferVo();
		
		transfer.setPatient(caseNote.getPatient());
		transfer.setCaseNote(caseNote);
		transfer.setTransferredFromLocation(caseNote.getCurrentLocation());
		transfer.setTransferredToLocation(form.qmbLocation().getValue());
		transfer.setTransferredBy(form.getLocalContext().getCurrentMOS());
		transfer.setTransferDate(form.dtimTransfer().getValue());
		transfer.setReasonForTransfer(form.cmbReason().getValue());
		
		if(form.txtComments().getValue() != null)
		{
			
			PatientCaseNoteCommentSaveVo comment = new PatientCaseNoteCommentSaveVo();
			
			comment.setAuthoredBy(form.getLocalContext().getCurrentMOS());
			comment.setAuthoredDate(new DateTime());
			comment.setCaseNote(caseNote);
			
			comment.setComment("Transfer Comment: " + form.txtComments().getValue());
			comment.setPatient(caseNote.getPatient());
			comment.setCaseNoteFolderLocation(form.qmbLocation().getValue());
			
			transfer.setTansferComment(comment);
		}
		
		return transfer;
	}

	@Override
	protected void onQmbLocationTextSubmited(String value) throws ims.framework.exceptions.PresentationLogicException
	{
		form.qmbLocation().clear();
		
		LocationLiteVoCollection locs = domain.listCaseNoteLocations(value);
		
		if(locs == null || locs.size() == 0)
			return;
		
		for(LocationLiteVo loc : locs)
		{
			if(loc == null)
				continue;
			
			form.qmbLocation().newRow(loc, loc.getName());
		}
		
		if(locs.size() == 1)
		{
			form.qmbLocation().setValue(locs.get(0));
		}
		else
			form.qmbLocation().showOpened();
	}
}