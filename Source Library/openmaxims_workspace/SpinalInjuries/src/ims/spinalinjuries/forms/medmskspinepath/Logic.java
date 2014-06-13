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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.22 build 41222.900)
// Copyright (C) 1995-2005 IMS MAXIMS plc. All rights reserved.
// 23/03/2005 - AU - fixed HCP to be defaulted from MedicalAdmissionContact not from domain

package ims.spinalinjuries.forms.medmskspinepath;

import java.util.ArrayList;

import ims.configuration.gen.ConfigFlag;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.CareContextLiteVo;
import ims.core.vo.CareContextShortVo;
import ims.core.vo.CareContextVo;
import ims.core.vo.ClinicalContactShortVo;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.HcpLiteVoCollection;
import ims.core.vo.VertebrallevelVo;
import ims.core.vo.VertebrallevelVoCollection;
import ims.core.vo.lookups.ContactType;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.cn.data.TreeNode;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.generalmedical.vo.MSKSpinePathologyFindingShortVoCollection;
import ims.generalmedical.vo.MSKSpinePathologyFindingVo;
import ims.generalmedical.vo.MSKSpinePathologyFindingVoCollection;
import ims.spinalinjuries.vo.lookups.InjuryClassModeofInjury;
import ims.spinalinjuries.vo.lookups.InjuryClassModeofInjuryCollection;
import ims.spinalinjuries.vo.lookups.LookupHelper;

public class Logic extends BaseLogic
{
	protected void onFormOpen() throws ims.framework.exceptions.FormOpenException
	{		
		initialize();
		open();
			
	}
	
	private void initialize()
	{
		loadCombos();
		
		ClinicalContactShortVo currentClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
		
		Boolean bMedicalAdmissionContactSelected = new Boolean(currentClinicalContact!=null && currentClinicalContact.getContactType().equals(ContactType.SPINALMEDICALADMISSION));
		Boolean bMedicalInpatientForm = new Boolean(engine.getFormName().equals(form.getForms().SpinalInjuries.MedMskSpinePathInpatient));
		
		form.getLocalContext().setIsMedicalInpatientForm(bMedicalInpatientForm);
		form.getLocalContext().setIsMedicalAdmissionSelected(bMedicalAdmissionContactSelected);
		displayRecordBrowser(bMedicalInpatientForm.booleanValue());
		form.chkProblem().setVisible(!form.getLocalContext().getIsMedicalInpatientForm().booleanValue());
		
		if(writeConditionsApply(bMedicalInpatientForm,	bMedicalAdmissionContactSelected)){
			form.getLocalContext().setWriteConditionsApply(Boolean.TRUE);
			activateButtons(true);
		}
		else{
			form.getLocalContext().setWriteConditionsApply(Boolean.FALSE);
			activateButtons(false);
		}
		
		form.chkActiveOnly().setValue(true);
		
		form.ccAuthoring().setIsRequiredPropertyToControls(true); //WDEV-15172
	}
	/**
	 * inpatient form writable when Spinal Medical Admission contact NOT selected
	 * admission form writable when Spinal Medical Admission contact IS selected
	 * @param  bMedicalInpatientForm, currentClinicalContact
	 * @return boolean
	 */		
	private boolean writeConditionsApply(Boolean bMedicalInpatientForm, Boolean bMedicalAdmissionContactSelected) {
	
		if(bMedicalInpatientForm.booleanValue()){
			
			if(bMedicalAdmissionContactSelected.booleanValue())
				return false;
			else
				return true;
		}
		else{
			if(bMedicalAdmissionContactSelected.booleanValue())
				return true;
			else
				return false;
		}
	}
	private void displayRecordBrowser(boolean visible)
	{
		form.pnlPathology().setVisible(!visible);
		form.lblBrowser().setVisible(visible);
		form.recbrPathology().setVisible(visible);
	}

	private void loadCombos()
	{
		// Level of Injury
		VertebrallevelVoCollection voCollVertebral = domain.listVertebralLevel(Boolean.TRUE);
		VertebrallevelVo voVertebralItem = null;

		for (int i = 0; i < voCollVertebral.size(); i++)
		{
			voVertebralItem = voCollVertebral.get(i);
			form.cmbLevelofInjury().newRow(voVertebralItem, voVertebralItem.getName());
		}

		// Mode of Injury
		InjuryClassModeofInjuryCollection injuryColl = LookupHelper.getInjuryClassModeofInjury(domain.getLookupService());
		TreeNode[] rootNodes = injuryColl.getRootNodes();
		if (rootNodes != null)
		{
			for (int i = 0; i < rootNodes.length; i++)
			{
				InjuryClassModeofInjury lkParent = (InjuryClassModeofInjury) rootNodes[i];
				if (lkParent.isActive())
					form.cmbModeofInjury().newRow(lkParent, lkParent.getText());
			}
		}
	}

	private void open()
	{
		clearForm(true);
		form.getLocalContext().setfinding(null);
		form.setMode(FormMode.VIEW);
			
		if(engine.getFormName().equals(form.getForms().SpinalInjuries.MedMskSpinePathInpatient))
		{
			fillRecordBrowser();
		}
		else
		{
			// See if an examination already exists for the Clinical Contact
			ClinicalContactShortVo voClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
			
			if(form.getLocalContext().getIsMedicalAdmissionSelected().booleanValue())
			{
				if (form.chkActiveOnly().getValue())
					form.getLocalContext().setSpinePathologyFindingColl(domain.getMskSpinePath(voClinicalContact, Boolean.TRUE)); //active only records
				else
					form.getLocalContext().setSpinePathologyFindingColl(domain.getMskSpinePath(voClinicalContact, null)); 
			}
			else
			{
				form.getLocalContext().setSpinePathologyFindingColl(domain.getSpinalMedicalAdmissionPathologyByCareContext(form.getGlobalContext().Core.getCurrentCareContext()));
			}
		}
		
		populateScreenFromData();
		
	}

	private void addSelectedClinicalContactToBrowser()
	{
		//If a ClinicalContact has been selected but does not have any Spine Pathology records yet
		//then add it to the Record Browser
		ClinicalContactShortVo voSelectedClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
		if(voSelectedClinicalContact != null)
		{
			form.recbrPathology().newRow(voSelectedClinicalContact, "Clinical Contact: " + voSelectedClinicalContact.getStartDateTime()+ " - "+voSelectedClinicalContact.getSeenBy(), Color.Green);
			form.getLocalContext().setUpdatedInstance(voSelectedClinicalContact);
		}
	}

	private void activateButtons(boolean visible)
	{
		form.btnNew().setVisible(visible);
		form.btnNew().setEnabled(visible);
		form.btnUpdate().setVisible(visible && form.getLocalContext().getfindingIsNotNull());
		form.btnUpdate().setEnabled(visible && form.getLocalContext().getfindingIsNotNull());
	}

	private void fillRecordBrowser()
	{
		boolean foundClinicalContact = false;
		form.recbrPathology().clear();
		
		CareContextShortVo voCareContext = form.getGlobalContext().Core.getCurrentCareContext();
		
		if(voCareContext != null)
		{
			MSKSpinePathologyFindingShortVoCollection voSpinePathologyFindColl = domain.listByCareContext(voCareContext);
			
			if(form.getGlobalContext().Core.getCurrentClinicalContact() == null)
			{
				form.recbrPathology().newRow(voCareContext, "Care Context:    " + voCareContext.getStartDateTime() + " - " + voCareContext.getEpisodeOfCare().getResponsibleHCP(), Color.Green);
				form.recbrPathology().setValue(voCareContext);
			}
			else
				form.recbrPathology().newRow(voCareContext, "Care Context:    " + voCareContext.getStartDateTime() + " - " + voCareContext.getEpisodeOfCare().getResponsibleHCP());
			
			if(voSpinePathologyFindColl != null)
			{
				for(int x = 0; x < voSpinePathologyFindColl.size(); x++)
				{
					if(voSpinePathologyFindColl.get(x) != null)
					{
						if(voSpinePathologyFindColl.get(x).getClinicalContact() != null)
						{
							ClinicalContactShortVo voClinicalContact = voSpinePathologyFindColl.get(x).getClinicalContact();
							foundClinicalContact = checkIfSelectedClinicalContact(voClinicalContact);
							if(!foundClinicalContact)
								form.recbrPathology().newRow(voClinicalContact, "Clinical Contact: " + voClinicalContact.getStartDateTime() + " - " + voClinicalContact.getSeenBy());	
						}
					}
				}
			}
			
			if(!foundClinicalContact)
				addSelectedClinicalContactToBrowser();
			
			setRecordBrowser();
			browseRecord();
		}
	}

	private boolean checkIfSelectedClinicalContact(ClinicalContactShortVo voClinicalContact)
	{
		//check is it the selected ClinicalContact
		boolean clinicalContact = false;
		ClinicalContactShortVo voSelectedClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
		if(voSelectedClinicalContact != null)
		{
			if(voClinicalContact.getID_ClinicalContact().equals(voSelectedClinicalContact.getID_ClinicalContact()))
			{
				clinicalContact = true;
				form.recbrPathology().newRow(voClinicalContact, "Clinical Contact: " + voClinicalContact.getStartDateTime() + " - " + voClinicalContact.getSeenBy(), Color.Green);
				form.getLocalContext().setUpdatedInstance(voClinicalContact);
			}
		}
		return clinicalContact;
	}

	private boolean checkIfExistPrimary()
	{
		ims.core.vo.PatientShort voSelectedPatient = form.getGlobalContext().Core.getPatientShort();
	
		if(voSelectedPatient != null)
		{
			Integer prim = domain.countForPrimaryPathology(voSelectedPatient);
			
			if (prim != null)
			{
				if (prim.intValue() > 0)
					return true;
			}
		}
		return false;
	}
	
	private void setRecordBrowser()
	{
		// after an update need to set the record browser to display the updated record
		if(form.getLocalContext().getUpdatedInstanceIsNotNull())
		{
			if(form.getLocalContext().getUpdatedInstance() instanceof CareContextShortVo)
			{
				CareContextShortVo voCareContext = (CareContextShortVo) form.getLocalContext().getUpdatedInstance();
				form.recbrPathology().setValue(voCareContext);
			}
			else
			{
				ClinicalContactShortVo voClinicalContact = (ClinicalContactShortVo) form.getLocalContext().getUpdatedInstance();
				form.recbrPathology().setValue(voClinicalContact);
			}
		}
	}

	private void populateScreenFromData()
	{
		MSKSpinePathologyFindingVoCollection voSpinePathologyColl = form.getLocalContext().getSpinePathologyFindingColl();
		
		if(form.getLocalContext().getWriteConditionsApply().booleanValue()){
			if(voSpinePathologyColl == null )
			{
				form.btnNew().setVisible(true);
				form.btnNew().setEnabled(true);
				form.btnUpdate().setVisible(false);
			}
			else
			{
				form.btnNew().setVisible(true);
				form.btnNew().setEnabled(true);
				form.btnUpdate().setVisible(true);
			}
		}
		else
			activateButtons(false);
		
		if(voSpinePathologyColl == null )
			return;
		
		if(form.getLocalContext().getWriteConditionsApply().booleanValue()){
			
			ClinicalContactShortVo voClinicalContactShort = form.getGlobalContext().Core.getCurrentClinicalContact();
			if (voClinicalContactShort != null && voClinicalContactShort.getStartDateTimeIsNotNull())
			{
				form.ccAuthoring().initializeComponent(false); //WDEV-15172
			}
		}
	
		MSKSpinePathologyFindingVoCollection findings = voSpinePathologyColl;
		form.grdIndex().getRows().clear();
		GenForm.grdIndexRow row;
		int primaryRow = 0;
		if(findings != null && findings.size()>0)
		{
			for (int i = 0; i < findings.size(); i++)
			{
				MSKSpinePathologyFindingVo vo = findings.get(i);
				row = form.grdIndex().getRows().newRow();
				row.setValue(vo);
				if (vo.getModeOfInjuryIsNotNull())
					row.setcolModeofInjury(vo.getModeOfInjury().getText());
				if (vo.getPathSiteIsNotNull())
					row.setcolLevelofInjury(vo.getPathSite().getName());
				if (vo.getTypeOfInjuryIsNotNull())
					row.setcolTypeofInjury(vo.getTypeOfInjury().getText());
				if (vo.getMechanismOfInjuryIsNotNull())
					row.setcolMechanismofInjury(vo.getMechanismOfInjury().getText());
				if (vo.getIsPrimaryPathology().booleanValue() == true)
				{
					row.setcolisPrimary(form.getImages().Core.AnswerBox_Yes);
					primaryRow = i;
				}
				if (vo.getIsActive().equals(Boolean.FALSE))
					row.setBackColor(Color.LightYellow);
				
				form.grdIndex().setValue(vo);
				
			}

			if (primaryRow != 0)
			{
				// Move the primary finding to the top of the grid
				form.grdIndex().swap(primaryRow, 0);
				form.grdIndex().setValue(findings.get(primaryRow));
			}
			
			if(form.getLocalContext().getfindingIsNotNull())
				form.grdIndex().setValue(form.getLocalContext().getfinding());
	
			if (findings.size() > 0)
			{
				getSelectedInstance();
			}
		}
	}
	
	private void populateContactInfo(MSKSpinePathologyFindingVo voSpinePathologyFinding) 
	{ 
		if(voSpinePathologyFinding != null)
		{
			//WDEV-15172
			AuthoringInformationVo authoringInfo = new AuthoringInformationVo(); 
			authoringInfo.setAuthoringHcp(voSpinePathologyFinding.getAuthoringHCP());
			authoringInfo.setAuthoringDateTime(voSpinePathologyFinding.getAuthoringDateTime());
			form.ccAuthoring().setValue(authoringInfo);
		}
	}

	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if(allowNew())
		{
			clearForm(false);
			setButtonsEditMode();
			form.chkActive().setValue(true);
			form.getLocalContext().setfinding(null);
			setUpdatedHeader();
			
			MSKSpinePathologyFindingVo voMSKSpinePathologyFinding = new MSKSpinePathologyFindingVo();
			if(engine.getFormName().equals(form.getForms().SpinalInjuries.MedMskSpinePathInpatient))
			{
				if(form.recbrPathology().getValue() != null && form.recbrPathology().getValue() instanceof ClinicalContactShortVo)
					voMSKSpinePathologyFinding.setClinicalContact((ClinicalContactShortVo) form.recbrPathology().getValue());
				else
					voMSKSpinePathologyFinding.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
			}
			else
				voMSKSpinePathologyFinding.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
				
			
			if(form.getGlobalContext().Core.getCurrentCareContextIsNotNull())
				voMSKSpinePathologyFinding.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
			
			form.getLocalContext().setfinding(voMSKSpinePathologyFinding);
			
			if(engine.getFormName().equals(form.getForms().SpinalInjuries.MedMskSpinePathInpatient))
			{		
				form.ccAuthoring().setValue(null);
				form.ccAuthoring().initializeComponent(true); //WDEV-15172
			}
			if(engine.getFormName().equals(form.getForms().SpinalInjuries.MedMskSpinePath))
			{
				if(form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())
				{
					ClinicalContactShortVo voClinicalContactShort = form.getGlobalContext().Core.getCurrentClinicalContact();
					
					if(voClinicalContactShort != null)
					{
						form.ccAuthoring().initializeComponent(false); //WDEV-15172
					}
				}
			}
		}
	}

	private boolean allowNew()
	{
		if(form.cmbModeofInjury().getValues().size() == 0)
		{
			engine.showMessage("The InjuryClassModeOfInjury lookup must first be populated before this form can be used. ");
			return false;
		}		
				
		if(form.cmbLevelofInjury().getValues().size() == 0)
		{
			engine.showMessage("The Vertebral Levels must be defined for 'Level of Injury' before this form can be used. ");
			return false;			
		}
		
		return true;
	}

	protected void onBtnUpdateClick() throws ims.framework.exceptions.PresentationLogicException
	{
		setUpdatedHeader();
		
		setButtonsEditMode();
	}

	private void setUpdatedHeader()
	{
		if(form.recbrPathology().getValue() != null)
		{
			if ((form.recbrPathology().getValue() instanceof CareContextVo))
			{
				CareContextVo voCareContext = (CareContextVo) form.recbrPathology().getValue();
				form.getLocalContext().setUpdatedInstance(voCareContext);
			}
			else if (form.recbrPathology().getValue() instanceof CareContextShortVo) //WDEV-15081
			{
				CareContextShortVo voCareContextShort = (CareContextShortVo) form.recbrPathology().getValue();
				form.getLocalContext().setUpdatedInstance(voCareContextShort);
			}
			else
			{
				ClinicalContactShortVo voClinicalContact  = (ClinicalContactShortVo) form.recbrPathology().getValue();
				form.getLocalContext().setUpdatedInstance(voClinicalContact);
			}
		}
	}
	
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		
		MSKSpinePathologyFindingVo voPathFinding = form.getLocalContext().getfinding();
		
		if (voPathFinding == null)
			voPathFinding = new MSKSpinePathologyFindingVo();
		
		//WDEV-15172
		if (form.ccAuthoring().getValue() != null)
		{
			voPathFinding.setAuthoringDateTime(form.ccAuthoring().getValue().getAuthoringDateTime());  
			voPathFinding.setAuthoringHCP(form.ccAuthoring().getValue().getAuthoringHcp());
		}
		
		voPathFinding.setModeOfInjury(form.cmbModeofInjury().getValue());
		voPathFinding.setPathSite(form.cmbLevelofInjury().getValue());
		voPathFinding.setIsPrimaryPathology(new Boolean(form.chkPrimPathology().getValue()));
		voPathFinding.setIsProblem(new Boolean(form.chkProblem().getValue()));
		voPathFinding.setTypeOfInjury(form.cmbTypeOfInjury().getValue());
		voPathFinding.setMechanismOfInjury(form.cmbMechanismofInjury().getValue());
		voPathFinding.setIsActive(new Boolean(form.chkActive().getValue()));

		if ( ! validateUiErrors(voPathFinding)) //WDEV-15172
			 return;
		
		String[] errors = voPathFinding.validate();
		if (errors != null)
		{
			engine.showErrors(errors);
			return;
		}

		try
		{
			form.getLocalContext().setfinding(domain.saveMskSpinePath(voPathFinding, form.getGlobalContext().Core.getCurrentCareContext()));
		}
		catch (StaleObjectException e)
		{
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return;
		}

		open();
	}

	private boolean validateUiErrors(MSKSpinePathologyFindingVo voPathFinding)
	{
		ArrayList<String> listOfErrors = new ArrayList<String>();
		
		if(form.ccAuthoring().getValue() == null)
		{
			listOfErrors.add(form.ccAuthoring().getErrors());
		}
		
		MSKSpinePathologyFindingVo tempVo = domain.getMskSpinePathForPrimaryPathology(form.getGlobalContext().Core.getPatientShort());	//wdev-11015
		
		if (voPathFinding != null && !voPathFinding.getID_MskSpinePathIsNotNull() && form.chkPrimPathology().getValue() == true && tempVo != null)  //wdev-11015
		{
			listOfErrors.add("A primary record already exists");	
		}
		else //wdev-11015
		{
			
			if(tempVo != null && voPathFinding != null && voPathFinding.getID_MskSpinePathIsNotNull() && form.chkPrimPathology().getValue() == true && !voPathFinding.getID_MskSpinePath().equals(tempVo.getID_MskSpinePath())) //wdev-11015)
				listOfErrors.add("A primary record already exists");
		}
		if (form.cmbModeofInjury().getValue() == null)
		{
			listOfErrors.add("Please select a 'Mode of Injury'");
			
		}
		else
		{
			if (InjuryClassModeofInjury.TRAUMA.equals(form.cmbModeofInjury().getValue()) && form.cmbTypeOfInjury().getValue() == null)
			{
				listOfErrors.add("Type Of Injury is mandatory");
			}
		}
		if (form.cmbLevelofInjury().getValue() == null)
		{
			listOfErrors.add("Please select a 'Level of Injury'");
			
		}

		int errorCount = listOfErrors.size();
		String[] result = new String[errorCount];

		for (int x = 0; x < errorCount; x++)
			result[x] = (String) listOfErrors.get(x);

		if (result != null && result.length > 0)
		{
			engine.showErrors(result);
			return false;
		}

		return true;
	}

	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.getLocalContext().setUpdatedInstance(null);
		open();
	}

	protected void onCmbModeofInjuryValueChanged() throws PresentationLogicException
	{
		setTraumaCombos();
		populateMechanismOfInjury();
	}

	private void setTraumaCombos()
	{
		if (form.cmbModeofInjury().getValue() != null)
		{
			// If the value is trauma, then cause and mechanism become active
			if (form.cmbModeofInjury().getValue().equals(InjuryClassModeofInjury.TRAUMA))
			{
				form.cmbTypeOfInjury().setEnabled(form.getMode().equals(FormMode.EDIT));
			}
			else
			{
				form.cmbTypeOfInjury().setEnabled(false);
				form.cmbTypeOfInjury().setValue(null);
				form.cmbMechanismofInjury().setValue(null);
			}
		}
		else
		{
			form.cmbTypeOfInjury().setEnabled(false);
			form.cmbTypeOfInjury().setValue(null);
		}
	}

	private void setButtonsEditMode()
	{
		form.setMode(FormMode.EDIT);
		form.btnNew().setVisible(false);
		form.btnUpdate().setVisible(false);
		form.cmbTypeOfInjury().setEnabled(false);

		if (form.cmbModeofInjury().getValue() != null)
		{
			if (form.cmbModeofInjury().getValue().equals(InjuryClassModeofInjury.TRAUMA))
				form.cmbTypeOfInjury().setEnabled(form.getMode().equals(FormMode.EDIT));
			else
				form.cmbTypeOfInjury().setEnabled(false);
		}
	}

	private void clearForm(boolean includeGrid)
	{
		if (includeGrid)
			form.grdIndex().getRows().clear();
		form.chkPrimPathology().setValue(false);
		form.chkProblem().setValue(false);
		form.cmbTypeOfInjury().setValue(null);
		form.cmbLevelofInjury().setValue(null);
		form.cmbMechanismofInjury().setValue(null);
		form.cmbModeofInjury().setValue(null);
		form.cmbMechanismofInjury().clear();
		form.chkActive().setValue(false);
		form.grdIndex().setValue(null);
	}

	protected void onGrdIndexSelectionChanged() throws PresentationLogicException
	{
		getSelectedInstance();
	}

	private void getSelectedInstance()
	{
		MSKSpinePathologyFindingVo vo = form.grdIndex().getValue();
		form.getLocalContext().setfinding(vo);

		populateContactInfo(vo);
		form.cmbTypeOfInjury().setValue(vo.getTypeOfInjury());
		form.cmbLevelofInjury().setValue(vo.getPathSite());
		form.chkPrimPathology().setValue(vo.getIsPrimaryPathology().booleanValue());
		form.chkProblem().setValue(vo.getIsProblem().booleanValue());
		form.chkActive().setValue(vo.getIsActive().booleanValue());

		if (vo.getModeOfInjury() != null && isModeOfInjuryInactive(vo.getModeOfInjury()))
		{
			form.cmbModeofInjury().newRow(vo.getModeOfInjury(), vo.getModeOfInjury().getText());
		}
		form.cmbModeofInjury().setValue(vo.getModeOfInjury());

		setTraumaCombos();
		populateMechanismOfInjury();
		if (vo.getMechanismOfInjury() != null && isMechanismOfInjuryActive(vo.getMechanismOfInjury()))
		{
			form.cmbMechanismofInjury().newRow(vo.getMechanismOfInjury(), vo.getMechanismOfInjury().getText());
		}
		form.cmbMechanismofInjury().setValue(vo.getMechanismOfInjury());
		//wdev-13578
		if(form.getLocalContext().getWriteConditionsApply().booleanValue())
		{
			form.btnUpdate().setEnabled(form.getLocalContext().getfindingIsNotNull());
		}
		//---------------
	}

	private void populateMechanismOfInjury()
	{
		form.cmbMechanismofInjury().clear();
		if (form.cmbModeofInjury().getValue() != null)
		{
			TreeNode[] childNodes = form.cmbModeofInjury().getValue().getChildren();
			if (childNodes != null)
			{
				for (int i = 0; i < childNodes.length; i++)
				{
					InjuryClassModeofInjury child = (InjuryClassModeofInjury) childNodes[i];
					if (child.isActive())
					{
						form.cmbMechanismofInjury().newRow(child, child.getText());
					}
				}
			}
		}
	}

	private boolean isModeOfInjuryInactive(InjuryClassModeofInjury modeOfInjuryVO)
	{
		boolean notFound = true;
		for (int i = 0; modeOfInjuryVO != null && i < form.cmbModeofInjury().getValues().size(); i++)
		{
			if (form.cmbModeofInjury().getValues().get(i).equals(modeOfInjuryVO))
			{
				notFound = false;
				break;
			}
		}
		return notFound;
	}

	private boolean isMechanismOfInjuryActive(InjuryClassModeofInjury mechanismOfInjuryVO)
	{
		boolean notFound = true;
		for (int i = 0; mechanismOfInjuryVO != null && i < form.cmbMechanismofInjury().getValues().size(); i++)
		{
			if (form.cmbMechanismofInjury().getValues().get(i).equals(mechanismOfInjuryVO))
			{
				notFound = false;
				break;
			}
		}
		return notFound;
	}

	protected void onChkActiveOnlyValueChanged() throws PresentationLogicException
	{
		clearForm(true);
		open();
	}

	protected void onFormModeChanged()
	{
		prepareRecordBrowser();
		
		checkAuthoringControls();
		
		if(form.getMode().equals(FormMode.VIEW))
		{
			form.cmbTypeOfInjury().setEnabled(false);
			form.chkProblem().setEnabled(false);
		}
		else
		{
			form.chkProblem().setEnabled(true);
		}
	}

	private void checkAuthoringControls()
	{
		
		//WDEV-15172
		form.ccAuthoring().setEnabledAuthoringHCP(form.getMode().equals(FormMode.EDIT) && form.getLocalContext().getIsMedicalInpatientForm());
		form.ccAuthoring().setEnabledDateTime(form.getMode().equals(FormMode.EDIT) && form.getLocalContext().getIsMedicalInpatientForm());
	}

	private void prepareRecordBrowser()
	{
		if(form.getMode().equals(FormMode.VIEW)&& form.recbrPathology().getVisible())
			form.recbrPathology().setEnabled(true);
		else
			form.recbrPathology().setEnabled(false);
	}

	protected void onRecbrPathologyValueChanged() throws PresentationLogicException
	{
		browseRecord();
	}

	private void browseRecord()
	{
		clearForm(true);
		
		form.btnUpdate().setVisible(false);
		form.ccAuthoring().setValue(null); //WDEV-15172
		
		updateBrowseInformation();
		
		if(form.recbrPathology().getValue() != null)
		{			
			if(form.recbrPathology().getValue() instanceof ClinicalContactShortVo)
			{
				if(form.chkActiveOnly().getValue())
					form.getLocalContext().setSpinePathologyFindingColl(domain.getMskSpinePath((ClinicalContactShortVo) form.recbrPathology().getValue(), Boolean.TRUE));
				else
					form.getLocalContext().setSpinePathologyFindingColl(domain.getMskSpinePath((ClinicalContactShortVo) form.recbrPathology().getValue(), null));
			}
			else
			{
				if(form.chkActiveOnly().getValue())
					form.getLocalContext().setSpinePathologyFindingColl(domain.getByCareContext((CareContextLiteVo) form.recbrPathology().getValue(), Boolean.TRUE));
				else
					form.getLocalContext().setSpinePathologyFindingColl(domain.getByCareContext((CareContextLiteVo) form.recbrPathology().getValue(), null));
			}
			populateScreenFromData();
		}
		checkRecordIsEditable();
	}

	private void checkRecordIsEditable()
	{
		if(form.recbrPathology().getValue() != null)
		{
			if(form.recbrPathology().getValue() instanceof CareContextShortVo)
			{
				CareContextShortVo voCurrentCareContext = form.getGlobalContext().Core.getCurrentCareContext();
				CareContextShortVo voSelectedCareContext = (CareContextShortVo) form.recbrPathology().getValue();
				if(voSelectedCareContext.getID_CareContext().equals(voCurrentCareContext.getID_CareContext()) && form.getGlobalContext().Core.getCurrentClinicalContact() == null)
					activateButtons(true);
				else
					activateButtons(false);
				
			}
			else
			{
				ClinicalContactShortVo voCurrentClinicalContact = form.getGlobalContext().Core.getCurrentClinicalContact();
				ClinicalContactShortVo voSelectedClinicalContact = (ClinicalContactShortVo) form.recbrPathology().getValue();
				if(voCurrentClinicalContact != null)
				{
					if(voSelectedClinicalContact.getID_ClinicalContact().equals(voCurrentClinicalContact.getID_ClinicalContact()))
						activateButtons(true);
					else
						activateButtons(false);
				}
				else
					activateButtons(false);
			}
		}
		isRecordOfTypeMedicalAdmission();
	}
	
	private void isRecordOfTypeMedicalAdmission()
	{
		ClinicalContactShortVo voCurrentClinical = form.getGlobalContext().Core.getCurrentClinicalContact();
		if(voCurrentClinical != null)
		{
			if(voCurrentClinical.getContactType().equals(ContactType.SPINALMEDICALADMISSION))
				activateButtons(false);
		}
	}

	private void updateBrowseInformation()
	{
		form.lblBrowser().setValue(form.recbrPathology().getRecordState("Assessment", "Assessments"));
	}
}