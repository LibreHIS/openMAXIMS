//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
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
//#############################################################################
//#EOH
// This code was generated by Sean Nesbitt using IMS Development Environment (version 1.22 build 41210.1630)
// Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
//14/03/2005 - fixed a consistency issue when adding a problem from Context menu
package ims.spinalinjuries.forms.medinjurydetails;

import ims.spinalinjuries.vo.lookups.LookupHelper;
import ims.core.vo.ClinicalContactShortVo;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.Control;
import ims.framework.cn.data.TreeNode;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Date;
import ims.generalmedical.vo.AssociatedFactorVo;
import ims.generalmedical.vo.AssociatedFactorVoCollection;
import ims.generalmedical.vo.InjuryDetailsVoCollection;
import ims.generalmedical.vo.MedicalProbOnAdmisVo;
import ims.generalmedical.vo.MedicalProbOnAdmisVoCollection;
import ims.generalmedical.vo.InjuryDetailsVo;
import ims.spinalinjuries.forms.medinjurydetails.GenForm.grdProblemsRow;
import ims.spinalinjuries.vo.lookups.InjuryClassModeofInjury;
import ims.spinalinjuries.vo.lookups.InjuryClassModeofInjuryCollection;
import ims.core.vo.lookups.ContactType;

public class Logic extends BaseLogic
{
	protected void onFormOpen() throws ims.framework.exceptions.FormOpenException
	{
		initialize();
	
		open();
	}

	private void initialize()
	{
		form.getContextMenus().getMedInjuryDetailsTextBoxAddToProblemItem().setVisible(true);
		loadCombos();
	}

	private boolean isMedicalAdmission()
	{
		if(form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())
		{
			ClinicalContactShortVo voCurrentClinical = form.getGlobalContext().Core.getCurrentClinicalContact();
			if(voCurrentClinical.getContactType().equals(ContactType.SPINALMEDICALADMISSION))
				return true;
			else
				return false;
		}
		else
		{
			return false;					//ClinicalContact may be null, CareContext has been selected
		}
	}

	private void open() 
	{
		form.setMode(FormMode.VIEW);
		
		clearScreen();

		form.cmbCauseInjury().setEnabled(false);
		
		InjuryDetailsVoCollection voInjuryDetails = null;
		boolean medicalAdmissionSelected = isMedicalAdmission();
				
		if(medicalAdmissionSelected)
			voInjuryDetails = domain.getInjuryDetailsByMedicalAdmissionClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		else
			voInjuryDetails = domain.getInjuryDetailsByCareContext(form.getGlobalContext().Core.getCurrentCareContext());

		if(voInjuryDetails!=null && voInjuryDetails.size()>0)
			form.getLocalContext().setInjuryDetails(voInjuryDetails.get(0));
		
				// populate global context and screen if a record exists for this episode
		if (voInjuryDetails != null  && voInjuryDetails.size()>0)
		{
			form.btnNew().setEnabled(false);
			form.btnUpdate().setEnabled(true);
				
			populateForm(voInjuryDetails.get(0));
			setProblemsRemoveVisibility();			
		}
		else
		{
			form.btnNew().setEnabled(true);
			form.btnUpdate().setEnabled(false);
		}
		
		if(!medicalAdmissionSelected)
			disableButtons(medicalAdmissionSelected);
	}
	
	private void disableButtons(boolean visible)
	{
		form.btnNew().setVisible(visible);
		form.btnUpdate().setVisible(visible);
	}

	private void populateForm(InjuryDetailsVo voInjuryDetailsVo)
	{		
		// recording hcp
		if (voInjuryDetailsVo.getClinicalContactIsNotNull())
			form.txtAuthoringHcp().setValue(voInjuryDetailsVo.getClinicalContact().getSeenBy().toString());
		
		//recording date
		if(voInjuryDetailsVo.getClinicalContact().getStartDateTimeIsNotNull())
			form.dtimAuthoringDateTime().setValue(voInjuryDetailsVo.getClinicalContact().getStartDateTime());
		
		//Date/Time of Injury:
		form.dteInjury().setValue(voInjuryDetailsVo.getInjuryDate());
		form.tmeInjury().setValue(voInjuryDetailsVo.getInjuryTime());
	
		//Date of Referral:
		form.dteReferral().setValue(voInjuryDetailsVo.getReferralDate());
			
		//Date of Transfer:
		form.dteTransfer().setValue(voInjuryDetailsVo.getTransferDate());
			
		//Description of Injury:
		form.txtDescInjury().setValue(voInjuryDetailsVo.getDescriptionOfInjury());
			
		//Associated Factors:
		AssociatedFactorVoCollection tempColl = voInjuryDetailsVo.getAssociatedFactor().sort();
		if (tempColl != null)
		{
			form.grdAssocFactors().getRows().clear();
			GenForm.grdAssocFactorsRow tempRow;
			for (int i=0; i<tempColl.size(); i++)
			{
				AssociatedFactorVo voAf = tempColl.get(i);
				tempRow = form.grdAssocFactors().getRows().newRow();
				tempRow.setValue(voAf);
				tempRow.setcolFactor(voAf.getAssociatedFactor());
				tempRow.setcolDetails(voAf.getDescription());
				tempRow.setactive(voAf.getIsActive().booleanValue());
			}
		}
			
		//Mode of Injury - Inactive lookups
		if(voInjuryDetailsVo.getModeOfInjury() != null && 
		   isModeOfInjuryInactive(voInjuryDetailsVo.getModeOfInjury()))
		{
			form.cmbModeInjury().newRow(voInjuryDetailsVo.getModeOfInjury(), voInjuryDetailsVo.getModeOfInjury().getText());
		}
		//Mode of Injury:
		form.cmbModeInjury().setValue(voInjuryDetailsVo.getModeOfInjury());
			
		//Cause of Injury:
		form.cmbCauseInjury().setValue(voInjuryDetailsVo.getCauseOfInjury());
			
		populateChildLkup();
		//Mechanism of Injury - Inactive lookups
		if(voInjuryDetailsVo.getMechanismOfInjury() != null &&
		   isMechanismOfInjuryInactive(voInjuryDetailsVo.getMechanismOfInjury()))
		{
			form.cmbMechInjury().newRow(voInjuryDetailsVo.getMechanismOfInjury(), voInjuryDetailsVo.getMechanismOfInjury().getText());
		}
		//Mechanism of Injury:
		form.cmbMechInjury().setValue(voInjuryDetailsVo.getMechanismOfInjury());
			
		//Loss of Consciousness:
		form.chkLOC().setValue(voInjuryDetailsVo.getSufferedLOC().booleanValue());
			
		//Duration of LOC:
		form.intDurLOC().setValue(voInjuryDetailsVo.getDurLOC());
		form.cmbUnitDurLOC().setValue(voInjuryDetailsVo.getDurLOCUnit());
			
		//Requires Ventillation:
		form.cmbReqVent().setValue(voInjuryDetailsVo.getRequiresVentillation());
			
		//Presenting Complaints:
		form.txtPresComp().setValue(voInjuryDetailsVo.getPresentingComplaints());
		
		
		//Problems
		MedicalProbOnAdmisVoCollection tempProbColl = null;  //wdev-13529
		if(engine.isRIEMode())  //wdev-13529
			tempProbColl = domain.getRIEProblems(form.getGlobalContext().Core.getCurrentCareContext());  //wdev-13529
		else
			tempProbColl = domain.getProblems(form.getGlobalContext().Core.getCurrentCareContext());	
		
		if (tempProbColl != null)
		{
			form.grdProblems().getRows().clear();
			GenForm.grdProblemsRow tempRow;
			for (int i=0; i<tempProbColl.size(); i++)
			{
				MedicalProbOnAdmisVo voProb = tempProbColl.get(i);
				if(voProb.getPatientProblemIsNotNull()){
					tempRow = form.grdProblems().getRows().newRow();
					tempRow.setValue(voProb);
					tempRow.setcolProblem(voProb.getPatientProblem());
					tempRow.setcolProblemButton(voProb.getPatientProblem());
					tempRow.setcolActive(voProb.getIsActive().booleanValue());
				}
			}
		}
		
		
	}

	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		clearScreen();
		
		ClinicalContactShortVo voClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
		if(voClinicalContact != null)
		{
			form.txtAuthoringHcp().setValue(voClinicalContact.getSeenBy().toString());
			form.dtimAuthoringDateTime().setValue(voClinicalContact.getStartDateTime());
		}
		
		form.btnNew().setEnabled(false);
		form.btnUpdate().setEnabled(false);
		
		form.cmbCauseInjury().setValue(null);
		form.cmbCauseInjury().setEnabled(false);
		form.cmbMechInjury().setValue(null);
		form.getLocalContext().setInjuryDetails(null); //wdev-13295
		
		form.setMode(FormMode.EDIT);
	}

	private void clearScreen() 
	{
		form.dteInjury().setValue(null);
		form.dtimAuthoringDateTime().setValue(null);
		form.dteReferral().setValue(null);
		form.dteTransfer().setValue(null);
		form.chkLOC().setValue(false);
		form.cmbCauseInjury().setValue(null);
		form.cmbMechInjury().setValue(null);
		form.cmbModeInjury().setValue(null);
		form.cmbMechInjury().clear();
		form.cmbReqVent().setValue(null);
		form.cmbUnitDurLOC().setValue(null);
		form.intDurLOC().setValue(null);
		form.txtAuthoringHcp().setValue(null);
		form.tmeInjury().setValue(null);
		form.txtDescInjury().setValue(null);
		form.txtPresComp().setValue(null);
		form.grdAssocFactors().getRows().clear();
		form.grdProblems().getRows().clear();
	}

	protected void onCmbModeInjuryValueChanged() throws ims.framework.exceptions.PresentationLogicException
	{
		if(form.cmbModeInjury().getValue() == null)
		{
			form.cmbCauseInjury().setValue(null);
			form.cmbCauseInjury().setEnabled(false);
			form.cmbMechInjury().setValue(null);
			form.cmbMechInjury().clear();
			return;
		}

		if(form.cmbModeInjury().getValue() != null && form.cmbModeInjury().getValue().equals(InjuryClassModeofInjury.TRAUMA))
		{
			form.cmbCauseInjury().setEnabled(true);
		}
		else
		{
			form.cmbCauseInjury().setValue(null);
			form.cmbCauseInjury().setEnabled(false);
			form.cmbMechInjury().setValue(null);
		}
		
		populateChildLkup();
	}
	
	protected void onBtnUpdateClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		form.btnUpdate().setEnabled(false);
		onChkLOCValueChanged(); //wdev-13385
		if(form.cmbModeInjury().getValue() == null)
		{
			form.cmbCauseInjury().setEnabled(false);
			return;
		}

		if(form.cmbModeInjury().getValue().equals(InjuryClassModeofInjury.TRAUMA))
		{
			form.cmbCauseInjury().setEnabled(true);
		}
		else
		{
			form.cmbCauseInjury().setEnabled(false);
		}
		
	}
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if(form.dteInjury().getValue()!= null)
		{
			if(form.dteInjury().getValue().isGreaterThan(new Date()))
			{
				engine.showMessage("'Date of Injury' cannot be set in the future");
				return;
			}
		}
		InjuryDetailsVo voInjuryDetails = form.getLocalContext().getInjuryDetails();
		if (voInjuryDetails == null)
			voInjuryDetails = new InjuryDetailsVo();
		
				
		voInjuryDetails = populateDataFromScreen(voInjuryDetails);

		try
		{
			String[] messages = voInjuryDetails.validate();
			if (messages != null)
			{
				engine.showErrors("Validation Errors", messages);
				return;
			}
	
			//save and update local context
			form.getLocalContext().setInjuryDetails(domain.saveInjuryDetails(voInjuryDetails));
			
		}
		catch(StaleObjectException e) 
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return;				
		}
		
		MedicalProbOnAdmisVoCollection collMedicalProbOnAdmisVo = new MedicalProbOnAdmisVoCollection();
		collMedicalProbOnAdmisVo = populateProblemsFromScreen();
		
		try
		{
			String[] messages = collMedicalProbOnAdmisVo.validate();
			if (messages != null)
			{
				engine.showErrors("Validation Errors", messages);
				return;
			}
		
			domain.saveProblemsOnAdmis(collMedicalProbOnAdmisVo);			
			
		}
		catch(StaleObjectException e) 
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return;				
		}
		
		open();
	}

	/**
	 * @param collMedicalProbOnAdmisVo
	 * @return
	 */
	private MedicalProbOnAdmisVoCollection populateProblemsFromScreen() 
	{
		// Problems
		GenForm.grdProblemsRowCollection problemRows = form.grdProblems().getRows();
		GenForm.grdProblemsRow problemRow = null;
		MedicalProbOnAdmisVoCollection voProbColl= new MedicalProbOnAdmisVoCollection();		
		MedicalProbOnAdmisVo voProbOnAdmis = null;		
		for (int i = 0; i < problemRows.size(); i++)				
		{
			problemRow = form.grdProblems().getRows().get(i);
			voProbOnAdmis = problemRow.getValue();
			voProbOnAdmis.setIsActive(new Boolean(problemRow.getcolActive()));
			voProbOnAdmis.setPatientProblem(problemRow.getcolProblem());
			
			if(voProbOnAdmis.getPatientProblemIsNotNull()){
				voProbOnAdmis.setProblemContext("Event Injury Details");	
				voProbOnAdmis.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
				voProbOnAdmis.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
				voProbColl.add(voProbOnAdmis);
			}
		}	
		return voProbColl;
	}

	private InjuryDetailsVo populateDataFromScreen(InjuryDetailsVo voInjuryDetails)
	{
		//Date/Time of Injury:
		voInjuryDetails.setInjuryDate(form.dteInjury().getValue());
		voInjuryDetails.setInjuryTime(form.tmeInjury().getValue());
		
		//Date of Referral:
		voInjuryDetails.setReferralDate(form.dteReferral().getValue());
		
		//Date of Transfer:		
		voInjuryDetails.setTransferDate(form.dteTransfer().getValue());
		
		//Mode of Injury:
		voInjuryDetails.setModeOfInjury(form.cmbModeInjury().getValue());
		
		//Cause of Injury:
		voInjuryDetails.setCauseOfInjury(form.cmbCauseInjury().getValue());
		
		//Mechanism of Injury:
		voInjuryDetails.setMechanismOfInjury(form.cmbMechInjury().getValue());
		
		//Loss of Consciousness:
		voInjuryDetails.setSufferedLOC(new Boolean(form.chkLOC().getValue()));

		//Duration of LOC:
		voInjuryDetails.setDurLOC(form.intDurLOC().getValue());
		voInjuryDetails.setDurLOCUnit(form.cmbUnitDurLOC().getValue());
		
		//Requires Ventillation:
		voInjuryDetails.setRequiresVentillation(form.cmbReqVent().getValue());
		
		//Description of Injury:
		voInjuryDetails.setDescriptionOfInjury(form.txtDescInjury().getValue());
		
		//Presenting Complaints:
		voInjuryDetails.setPresentingComplaints(form.txtPresComp().getValue());
		
		//Associated Factors
		GenForm.grdAssocFactorsRowCollection associatedFactorsRows = form.grdAssocFactors().getRows();
		GenForm.grdAssocFactorsRow associatedFactorsRow = null;
		AssociatedFactorVoCollection voAssocFactorColl= new AssociatedFactorVoCollection();		
		AssociatedFactorVo voAssocFactor = new AssociatedFactorVo();
		
		for (int i = 0; i < associatedFactorsRows.size(); i++)				
		{						
			associatedFactorsRow = form.grdAssocFactors().getRows().get(i);
			voAssocFactor = associatedFactorsRow.getValue();
			voAssocFactor.setAssociatedFactor(associatedFactorsRow.getcolFactor());
			voAssocFactor.setDescription(associatedFactorsRow.getcolDetails());
			voAssocFactor.setIsActive(new Boolean(associatedFactorsRow.getactive()));
			if(voAssocFactor.getAssociatedFactorIsNotNull() || voAssocFactor.getDescriptionIsNotNull())
				voAssocFactorColl.add(voAssocFactor);
		}
		
		voInjuryDetails.setAssociatedFactor(voAssocFactorColl);	
		
		voInjuryDetails.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		
		
		return voInjuryDetails;
		
	}
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		clearScreen();
		open();
	}

	private void newAssociatedFactors() 
	{
		// add an intervention row
		GenForm.grdAssocFactorsRow row;
		row = form.grdAssocFactors().getRows().newRow();
		row.setValue(new AssociatedFactorVo());
		row.setactive(true);
	}
	private void removeAssociatedFactors()
	{
		if(form.grdAssocFactors().getSelectedRowIndex() != -1)
			form.grdAssocFactors().getRows().remove(form.grdAssocFactors().getSelectedRowIndex());
	}

	private void newProblem() 
	{
		GenForm.grdProblemsRow row = form.grdProblems().getRows().newRow();
		row.showcolProblemButtonOpened();
		row.setcolActive(true);
		row.setValue(new MedicalProbOnAdmisVo());
	}
	
	private void removeProblem() 
	{
		if(form.grdProblems().getSelectedRowIndex() != -1)
			form.grdProblems().getRows().remove(form.grdProblems().getSelectedRowIndex());
	}

	protected void onGrdProblemsGridCommentChanged(int column, grdProblemsRow row) throws PresentationLogicException 
	{
		row.setcolProblem(row.getcolProblemButton());		
	}

	/* (non-Javadoc)
	 * @see ims.spinalinjuries.forms.medinjurydetails.Handlers#onContextMenuItemClick(int, ims.framework.Control)
	 */
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException 
	{
		if (sender.equals(form.txtDescInjury()))
		{
			switch (menuItemID)
			{
				case GenForm.ContextMenus.MedInjuryDetailsTextBox.AddToProblem:
					newProblemFromDescInjury();
					break;				
				case GenForm.ContextMenus.MedInjuryDetailsTextBox.AddToAssociatedFactors:
					newAssociatedFactorFromDescInjury();
					break;
			}
						
		}		
		else if (sender.equals(form.txtPresComp()))
		{
			switch (menuItemID)
			{
				case GenForm.ContextMenus.MedInjuryDetailsTextBox.AddToProblem:
					newProblemFromPresComp();
					break;
				case GenForm.ContextMenus.MedInjuryDetailsTextBox.AddToAssociatedFactors:
					newAssociatedFactorFromPresComp();
					break;
			}					
		}
		else if (sender.equals(form.grdAssocFactors()))
		{
			switch (menuItemID)
			{
				case GenForm.ContextMenus.MedInjuryDetailsAssociatedFactors.ADD:
					newAssociatedFactors();
				break;
				case GenForm.ContextMenus.MedInjuryDetailsAssociatedFactors.REMOVE:	//wdev-12392
					removeAssociatedFactors();
					setAssociatedFactorRemoveVisibility();
				break;
			}				
		}
		else if (sender.equals(form.grdProblems()))
		{
			switch (menuItemID)
			{
				case GenForm.ContextMenus.GenericGrid.Add:
					newProblem();
					break;
				case GenForm.ContextMenus.GenericGrid.Remove:
					removeProblem();
					setProblemsRemoveVisibility();
					break;
			}
		}
	}
	

	private void newProblemFromPresComp() 
	{
		//add selected text to problem grid
		if (form.txtPresComp().getSelectedText() != "")
		{
			grdProblemsRow grdProbRow = form.grdProblems().getRows().newRow();
			grdProbRow.setValue(new MedicalProbOnAdmisVo());
			grdProbRow.setcolProblem(form.txtPresComp().getSelectedText());
			grdProbRow.setcolProblemButton(form.txtPresComp().getSelectedText());
			grdProbRow.setcolActive(true);
		}
		else
		{
			engine.showMessage("Please select text to be added.");
			return;
		}
	}

	private void newProblemFromDescInjury() 
	{
		//add selected text to problem grid
		if (form.txtDescInjury().getSelectedText() != "")
		{
			grdProblemsRow grdProbRow = form.grdProblems().getRows().newRow();
			grdProbRow.setValue(new MedicalProbOnAdmisVo());
			grdProbRow.setcolActive(true);
			grdProbRow.setcolProblem(form.txtDescInjury().getSelectedText());
			grdProbRow.setcolProblemButton(form.txtDescInjury().getSelectedText());
		}
		else
		{
			engine.showMessage("Please select text to be added.");
			return;
		}
	}


	private void newAssociatedFactorFromPresComp() 
	{
		//add selected text to associated factors grid
		GenForm.grdAssocFactorsRow row = form.grdAssocFactors().getRows().newRow();
		row.setValue(new AssociatedFactorVo());			
		row.setactive(true);
		row.setcolDetails(form.txtPresComp().getSelectedText());
	}

	private void newAssociatedFactorFromDescInjury() 
	{
		//add selected text to associated factors grid
		GenForm.grdAssocFactorsRow row = form.grdAssocFactors().getRows().newRow();
		row.setValue(new AssociatedFactorVo());			
		row.setactive(true);
		row.setcolDetails(form.txtDescInjury().getSelectedText());
	}
	
	/* (non-Javadoc)
	 * @see ims.spinalinjuries.forms.medinjurydetails.Handlers#onFormModeChanged()
	 */
	protected void onFormModeChanged() 
	{
		if (form.getMode().equals(FormMode.EDIT))
		{
			form.getContextMenus().getMedInjuryDetailsTextBoxAddToProblemItem().setVisible(true);
			form.getContextMenus().getMedInjuryDetailsTextBoxAddToAssociatedFactorsItem().setVisible(true);
			form.getContextMenus().getGenericGridAddItem().setVisible(true);
			form.getContextMenus().getMedInjuryDetailsAssociatedFactorsADDItem().setVisible(true);			
		}		
		else
		{
			form.getContextMenus().getMedInjuryDetailsTextBoxAddToProblemItem().setVisible(false);
			form.getContextMenus().getMedInjuryDetailsTextBoxAddToAssociatedFactorsItem().setVisible(false);
			form.getContextMenus().getGenericGridAddItem().setVisible(false);			
			form.getContextMenus().getMedInjuryDetailsAssociatedFactorsADDItem().setVisible(false);			
		}
	}
	
	//Loads the hierarchical combo
	private void loadCombos()
	{
		InjuryClassModeofInjuryCollection injuryColl = LookupHelper.getInjuryClassModeofInjury(domain.getLookupService());
		TreeNode[] rootNodes = injuryColl.getRootNodes();
		if(rootNodes != null)
		{
			for (int i = 0; i < rootNodes.length; i++) 
			{
				InjuryClassModeofInjury lkParent = (InjuryClassModeofInjury)rootNodes[i];
				if(lkParent.isActive())
				{
					form.cmbModeInjury().newRow(lkParent, lkParent.getText());
				}
			}
		}
	}
	private void populateChildLkup()
	{
		form.cmbMechInjury().clear();
		if(form.cmbModeInjury().getValue() != null)
		{
			TreeNode[] childNodes = form.cmbModeInjury().getValue().getChildren();
			if(childNodes != null)
			{
				for(int i=0; i<childNodes.length; i++)
				{
					InjuryClassModeofInjury child = (InjuryClassModeofInjury)childNodes[i];
					if(child.isActive())
					{
						form.cmbMechInjury().newRow(child, child.getText());
					}
				}
			}
		}
	}
	
	private boolean isModeOfInjuryInactive(InjuryClassModeofInjury modeOfInjuryVO)
	{
		boolean notFound = true;
		for(int i=0; modeOfInjuryVO != null && i < form.cmbModeInjury().getValues().size(); i++)
		{
			if(form.cmbModeInjury().getValues().get(i).equals(modeOfInjuryVO))
			{
				notFound = false;
				break;
			}
		}
		return notFound;
	}
	
	private boolean isMechanismOfInjuryInactive(InjuryClassModeofInjury mechanismOfInjuryVO)
	{
		boolean notFound = true;
		for(int i=0; mechanismOfInjuryVO != null && i < form.cmbMechInjury().getValues().size(); i++)
		{
			if(form.cmbMechInjury().getValues().get(i).equals(mechanismOfInjuryVO))
			{
				notFound = false;
				break;
			}
		}
		return notFound;
	}
	
	private void setProblemsRemoveVisibility()
	{
		form.getContextMenus().getGenericGridRemoveItem().setVisible(false);
		if(form.grdProblems().getSelectedRowIndex()<0)
			return;
		
		MedicalProbOnAdmisVo voMedicalProbOnAdmis = form.grdProblems().getRows().get(form.grdProblems().getSelectedRowIndex()).getValue();
		boolean bVisible = 	form.grdProblems().getSelectedRowIndex() != -1 && 
							form.getMode().equals(FormMode.EDIT);
				
		//WDEV-10158 only non saved problems can be removed; saved problems can be made inactive
		if(voMedicalProbOnAdmis.getID_PatientProblem()==null)
			form.getContextMenus().getGenericGridRemoveItem().setVisible(bVisible);
	}
	//-----wdev-12392
	private void setAssociatedFactorRemoveVisibility()
	{
		form.getContextMenus().getMedInjuryDetailsAssociatedFactorsREMOVEItem().setVisible(false);
		if(form.grdAssocFactors().getSelectedRowIndex()<0)
			return;
		
		AssociatedFactorVo voAssociatedFactor = form.grdAssocFactors().getRows().get(form.grdAssocFactors().getSelectedRowIndex()).getValue();
		boolean bVisible = 	form.grdAssocFactors().getSelectedRowIndex() != -1 && 
							form.getMode().equals(FormMode.EDIT);
				
		
		if(voAssociatedFactor.getID_InjuryAssocFactor() == null)
			form.getContextMenus().getMedInjuryDetailsAssociatedFactorsREMOVEItem().setVisible(bVisible);

	}
	//------------
	
	@Override
	protected void onGrdProblemsSelectionChanged() 
	{
		setProblemsRemoveVisibility();		
	}

	@Override
	protected void onGrdProblemsGridCheckBoxClicked(int column, grdProblemsRow row, boolean isChecked) throws PresentationLogicException {
		setProblemsRemoveVisibility();			
	}

	//wdev-12392
	protected void onGrdAssocFactorsSelectionChanged() throws PresentationLogicException 
	{
		setAssociatedFactorRemoveVisibility();
		
	}

	//wdev-13385
	protected void onChkLOCValueChanged() throws PresentationLogicException 
	{
		if(form.chkLOC().getValue()  == true )
		{
			if(form.getMode().equals(FormMode.EDIT))
			{
				form.intDurLOC().setEnabled(true);
				form.cmbUnitDurLOC().setEnabled(true);
			}
		}
		else
		{
			if(form.getMode().equals(FormMode.EDIT))
			{
				form.intDurLOC().setEnabled(false);
				form.cmbUnitDurLOC().setEnabled(false);
			}
		}
		
	}

	//wdev-13529
	protected void onRIEDialogClosed(DialogResult result) throws PresentationLogicException 
	{
		if(result.equals(DialogResult.OK))
		{
			MedicalProbOnAdmisVoCollection tempProbColl = new MedicalProbOnAdmisVoCollection();
			for(int i = 0;i<form.grdProblems().getRows().size();i++)
			{
				tempProbColl.add(form.grdProblems().getRows().get(i).getValue());//domain.getProblems(form.getGlobalContext().Core.getCurrentCareContext());
			}
			if(tempProbColl.size() < 1)
				return;
			
			try 
			{
				domain.saveProblemOnAdmisAsRie(tempProbColl);
			} 
			catch (StaleObjectException e) 
			{
				engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			}
		}
			
		
	}
	
}