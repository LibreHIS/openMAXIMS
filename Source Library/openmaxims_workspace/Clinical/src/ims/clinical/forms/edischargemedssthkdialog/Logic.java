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
// This code was generated by Catalin Tomozei using IMS Development Environment (version 1.71 build 3946.24554)
// Copyright (C) 1995-2010 IMS MAXIMS. All rights reserved.

package ims.clinical.forms.edischargemedssthkdialog;

import ims.clinical.forms.clinicalcoding.Logic.IClinicalCodingCustomHotlistProvider;
import ims.clinical.forms.edischargemedssthkdialog.GenForm.GroupSelectionEnumeration;
import ims.clinical.vo.TTAMedicationDetailVo;
import ims.clinical.vo.enums.EDischargeMedsContextMenu;
import ims.clinical.vo.lookups.CodingItemType;
import ims.clinical.vo.lookups.DoseFormIndicator;
import ims.clinical.vo.lookups.DoseFormIndicatorCollection;
import ims.clinical.vo.lookups.MedicationDuration;
import ims.configuration.gen.ConfigFlag;
import ims.core.clinical.vo.MedicationRefVo;
import ims.core.resource.people.vo.HcpRefVo;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.MedicationLiteVo;
import ims.core.vo.MedicationLiteVoCollection;
import ims.core.vo.MemberOfStaffShortVo;
import ims.core.vo.lookups.MedicationDoseUnit;
import ims.core.vo.lookups.MedicationDoseUnitCollection;
import ims.core.vo.lookups.MedicationFrequency;
import ims.core.vo.lookups.MedicationRoute;
import ims.core.vo.lookups.MedicationRouteCollection;
import ims.core.vo.lookups.Specialty;
import ims.domain.exceptions.DomainInterfaceException;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.DateTime;
import ims.vo.ValueObject;
import ims.vo.ValueObjectCollection;
import ims.vo.interfaces.IClinicalCodingValue;

import java.util.ArrayList;

public class Logic extends BaseLogic
{	
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		if (args != null && args.length > 0)
		{
			if (args.length >= 3 && args[2] instanceof FormMode)
			{
				form.setMode((FormMode) args[2]);
				if (((FormMode) args[2]).equals(FormMode.VIEW) && args.length >= 2 && args[1] instanceof Boolean && ((Boolean)args[1]).equals(Boolean.TRUE))
				{
					form.chkPrescriberContacted().setEnabled(false);
				}
			}
			if (args.length >= 1 && args[0] instanceof EDischargeMedsContextMenu)
			{
				form.getLocalContext().setFormMode((EDischargeMedsContextMenu) args[0]);
				
				initialize();
				//clear();
				open();	
				form.ccMedication().setFocus();
			}
			if (args.length >= 2 && args[1] instanceof Boolean)
			{
				form.lblPrescriberContacted().setVisible(((Boolean) args[1]).booleanValue());
				form.chkPrescriberContacted().setVisible(((Boolean) args[1]).booleanValue());				
			}
			
		}
	}	
	
	private void open() throws PresentationLogicException 
	{			
		
		populateScreenFromData(form.getLocalContext().getEditedRecord());
		updateControlState();
	}
	private void populateFavMeds(boolean showOpened)
	{
		if (!(domain.getHcpLiteUser() instanceof HcpRefVo))
			return;
		
		if (form.getLocalContext().getEditedRecord() == null) //WDEV-15028
		{
			form.ccMedication().clear();
		}
		
		form.ccMedication().setCustomHotlist(new IClinicalCodingCustomHotlistProvider()
		{
			
			public ValueObjectCollection listCodingItems(String value) throws DomainInterfaceException
			{
				int value2 = ConfigFlag.GEN.HCP_FAVOURITE_MEDICATIONS_LIST_SIZE.getValue();
				if (value2 <1)
					return null;
				return domain.listFrequentMedications(value2, (HcpRefVo) domain.getHcpLiteUser());
			}
		});
		
		if (form.getLocalContext().getEditedRecord() == null) //WDEV-15028
		{
			form.ccMedication().search(showOpened);
		}
		else if (form.getLocalContext().getEditedRecordIsNotNull() && !showOpened)
		{
			form.ccMedication().search(false);
		}
		
		setMedicationHotlist();
	}
	private Specialty getSpecialty()
	{
		if ((form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull()) && (form.getGlobalContext().Core.getCurrentClinicalContact().getSpecialtyIsNotNull()))
		{
			return form.getGlobalContext().Core.getCurrentClinicalContact().getSpecialty();
		}
		else if ((form.getGlobalContext().Core.getEpisodeofCareShortIsNotNull()) && (form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialtyIsNotNull()))
		{
			return form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialty();
		}
		return null;
	}
	
	private void updateControlState()
	{
		boolean isEdit = form.getMode().equals(FormMode.EDIT);
		if (isEdit)
		{
			form.cmbForm().setEnabled(form.ccMedication().getValue()!=null && form.cmbForm().getValues().size()>0);
			form.cmbRoute().setEnabled(form.cmbForm().getValue()!=null);
			form.decDose().setEnabled(form.cmbRoute().getValue()!=null);
			
			
			
		}
		form.cmbDoseUnit().setVisible((form.cmbRoute().getValue()!=null&&form.cmbDoseUnit().getValues().size() > 0)||form.cmbRoute().getValue()==null);
		form.txtDoseUnit().setVisible(!form.cmbDoseUnit().getVisible());
		
		form.cmbDoseUnit().setEnabled(isEdit && form.cmbRoute().getValue()!=null);
		form.txtDoseUnit().setEnabled(isEdit && form.cmbRoute().getValue()!=null);
		
		
		//WDEV-14407
		if (form.getLocalContext().getEditedRecord() != null)
		{
			if (form.getLocalContext().getEditedRecord().getMedication() == null)
			{
				if (form.getMode().equals(FormMode.EDIT))
				{
					form.ccMedication().setParentEditing(true);
				}
				else
				{
					form.ccMedication().setParentEditing(false); //WDEV-15023
				}
			}
			else if (form.ccMedication().isNoSuitableTermFound() != null && ! form.ccMedication().isNoSuitableTermFound().booleanValue())
			{
				form.ccMedication().setTaxonomySearch(false);
			}
			
			form.ccMedication().hideTaxonomySearch();
		}
		
	}
	
	private void populateScreenFromData(TTAMedicationDetailVo record) throws PresentationLogicException
	{
		clear();
		
		if (record == null)
			return;		
						
		form.ccMedication().setValue(record);
		ccMedicationValueChanged();//WDEV-11894
		if (record.getFormIsNotNull() && !form.cmbForm().getValues().contains(record.getForm()))//WDEV-11894
		{
			form.cmbForm().newRow(record.getForm(), record.getForm().getIItemText());//WDEV-11894
		}
		form.cmbForm().setValue(record.getFormIsNotNull() ? record.getForm() : null);//WDEV-11894
		
		cmbFormValueChanged();//WDEV-11894
		if (record.getRouteIsNotNull() && !form.cmbRoute().getValues().contains(record.getRoute()))//WDEV-11894
		{
			form.cmbRoute().newRow(record.getRoute(), record.getRoute().getIItemText());
		}
		form.cmbRoute().setValue(record.getRouteIsNotNull() ? record.getRoute() : null);//WDEV-11894
		cmbRouteValueChanged();//WDEV-11894
		if (record.getDoseUnitIsNotNull() && !form.cmbDoseUnit().getValues().contains(record.getDoseUnit()))//WDEV-11894
		{
			form.cmbDoseUnit().newRow(record.getDoseUnit(), record.getDoseUnit().getIItemText());//WDEV-11894
		}
		form.decDose().setValue(record.getDoseValueIsNotNull() ? record.getDoseValue() : null);
		form.cmbDoseUnit().setValue(record.getDoseUnitIsNotNull() ? record.getDoseUnit() : null);
		form.txtDoseUnit().setValue(record.getUnitText());
		if (record.getPrescriberContactedIsNotNull() && record.getPrescriberContacted())
			form.chkPrescriberContacted().setValue(record.getPrescriberContacted());
		//form.txtDoseUnit().setValue(record.getdose ? record.getFrequencyValue() : null);
		form.cmbFrequency().setValue(record.getFrequencyUnitIsNotNull() ? record.getFrequencyUnit() : null);
		form.intDuration().setValue(record.getDurationValueIsNotNull() ? record.getDurationValue() : null);
		form.cmbDuration().setValue(record.getDurationUnitIsNotNull() ? record.getDurationUnit() : null);
		form.dteCommencement().setValue(record.getCommencedDateIsNotNull() ? record.getCommencedDate() : null);
		form.txtCommentsForPharmacy().setValue(record.getClinicalCommentsForPharmacyIsNotNull() ? record.getClinicalCommentsForPharmacy() : null);
		form.txtInstructionsForPatient().setValue(record.getMedicationInstructionsForPatientIsNotNull() ? record.getMedicationInstructionsForPatient() : null);
	}
	
	private TTAMedicationDetailVo populateDataFromScreen(TTAMedicationDetailVo record)
	{	
		if (record == null)
			record = new TTAMedicationDetailVo();
		
		//Medication
		IClinicalCodingValue result = form.ccMedication().getValue();
		if (result != null)
		{
			ValueObject medication = result.getIClinicalCodingValue();			
			if (medication instanceof MedicationLiteVo)
			{				
				if (((MedicationLiteVo) medication).getID_MedicationIsNotNull() && ((MedicationLiteVo) medication).getID_Medication() < 0)
				{
					MedicationLiteVo med = (MedicationLiteVo) medication;
					med.setMedicationName(result.getIClinicalCodingValueDescription());
					med.setIsActive(true);
					
					record.setMedication(med);
				}
				else
				{
					record.setMedication(domain.getMedicationLiteVo((MedicationLiteVo) medication));
				}
				
				record.setOtherMedicationText(result.getIClinicalCodingValueDescription());
			}
			else
			{
				throw new CodingRuntimeException("Component should return MedicationLiteVo");
			}					
		}
		
		//AuthoringInfo
		AuthoringInformationVo authoringInfoVo = new AuthoringInformationVo();		
		MemberOfStaffShortVo user = (MemberOfStaffShortVo) domain.getMosUser();
		if (user != null && user.getHcp() != null)
		{
			HcpLiteVo hcpUser = new HcpLiteVo();
			hcpUser.setMos(user);
			hcpUser.setID_Hcp(user.getHcp().getID_Hcp());
			
			authoringInfoVo.setAuthoringDateTime(new DateTime());
			authoringInfoVo.setAuthoringHcp(hcpUser);
		}										
		record.setAuthoringInformation(authoringInfoVo);
		
		if (form.chkPrescriberContacted().isVisible())
			record.setPrescriberContacted(form.chkPrescriberContacted().getValue());
		
		record.setClinicalCommentsForPharmacy(form.txtCommentsForPharmacy().getValue());		
		record.setCommencedDate(form.dteCommencement().getValue());
		record.setDoseValue(form.decDose().getValue());
		record.setDoseUnit(form.cmbDoseUnit().getValue());
		record.setUnitText(form.txtDoseUnit().getValue());//WDEV-11894
		record.setDurationValue(form.intDuration().getValue());
		record.setDurationUnit(form.cmbDuration().getValue());
		record.setForm(form.cmbForm().getValue());
		record.setFrequencyUnit(form.cmbFrequency().getValue());
		//record.setFrequencyValue(form.intFrequency().getValue());
		record.setMedicationInstructionsForPatient(form.txtInstructionsForPatient().getValue());
		record.setRoute(form.cmbRoute().getValue());
		
		return record;
	}
	
	private void initialize() throws PresentationLogicException 
	{
		initializeMedicationControl(true);
		
		boolean iMMedic = domain.getHcpLiteUser() instanceof HcpRefVo;//WDEV-11888	
		form.GroupSelection().setEnabled(GroupSelectionEnumeration.rdoMyHotlist, iMMedic);//WDEV-11888
		form.GroupSelection().setValue(iMMedic?GroupSelectionEnumeration.rdoMyHotlist:GroupSelectionEnumeration.rdoHotList);//WDEV-11888
		if (form.getGlobalContext().Clinical.getEDischargeMedsIsNotNull())
			form.getLocalContext().setEditedRecord((TTAMedicationDetailVo) form.getGlobalContext().Clinical.getEDischargeMeds());//.clone());
		radioGroupValueChanged(!form.getLocalContext().getEditedRecordIsNotNull());
		
		//populateFavMeds();
	}

	private void initializeMedicationControl(boolean hotlist)
	{
		form.ccMedication().setCodingItemType(CodingItemType.MEDICATION);			
		form.ccMedication().setHotlist(hotlist);				
		form.ccMedication().setSpecialty(getSpecialty());
		form.ccMedication().setParentEditing(true);
		form.ccMedication().hideTaxonomySearch(); //WDEV-14407 
		//form.ccMedication().setTaxonomySearch(true); WDEV-14407 
		form.ccMedication().setIsRequired(true);
	}
	
	private void clear()
	{
		//form.ccMedication().clear();
		form.decDose().setValue(null);
		form.cmbDoseUnit().setValue(null);
		form.cmbForm().setValue(null);
		form.cmbRoute().setValue(null);
		form.txtDoseUnit().setValue(null);//WDEV-11894
		form.cmbFrequency().setValue(null);
		form.intDuration().setValue(null);
		form.cmbDuration().setValue(null);;
		form.dteCommencement().setValue(null);
		form.txtCommentsForPharmacy().setValue(null);
		form.txtInstructionsForPatient().setValue(null);
	}
	
	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		//if (form.getLocalContext().getEditedRecord() == null)   //wdev-11855
		//{
			if (form.ccMedication().getValue() == null)
			{
				engine.showMessage("Medication is mandatory!");
				return;
			}
			else//WDEV-12033
			{
				if (form.ccMedication().getValue().getIClinicalCodingValue().getBoId()<0 && form.ccMedication().getValue().getIClinicalCodingValueDescription().trim().length() <3)//WDEV-12033
				{
					engine.showMessage("Medication name must be 3 characters or more");
					return;
				}
			}
		//}
			String[] errors = valitateUI();
			if(errors != null && errors.length > 0)
			{
				engine.showErrors(errors);
				return ;
			}
			
		TTAMedicationDetailVo record = populateDataFromScreen(form.getLocalContext().getEditedRecord());
		
		//WDEV-11888-Start
		if (record!=null && record.getMedicationIsNotNull() && record.getMedication().getID_MedicationIsNotNull() && record.getMedication().getID_Medication().intValue()>=0 && domain.getHcpLiteUser() instanceof HcpRefVo)
		{
			HcpRefVo medic = (HcpRefVo)domain.getHcpLiteUser();
			domain.addMedicationToHotlist(record.getMedication(), medic);//WDEV-11888
		}
		//WDEV-11888-End
		form.getGlobalContext().Clinical.setEDischargeMeds(record);
		engine.close(DialogResult.OK);
	}
	
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}

	private String[] valitateUI()
	{
		ArrayList<String> errors = new ArrayList<String>();
		
		Float 				dose 		  = form.decDose().getValue();
		MedicationDoseUnit 	doseUnit 	  = form.cmbDoseUnit().getValue();
		String 				doseText      = form.txtDoseUnit().getValue();//WDEV-11894
		DoseFormIndicator 	formm 		  = form.cmbForm().getValue();
		MedicationRoute 	route		  = form.cmbRoute().getValue();
		MedicationFrequency frequencyUnit = form.cmbFrequency().getValue();
		Integer 			duration 	  = form.intDuration().getValue();
		 MedicationDuration 	durationUnit  = form.cmbDuration().getValue();
		
		String clinicianComments = form.txtCommentsForPharmacy().getValue();
		
		if ((dose == null || (doseUnit == null && doseText==null) || formm == null || route == null || frequencyUnit == null ) && clinicianComments == null) //WDEV-11894
		{
			errors.add("If Dose, Form, Route or Frequency are empty, Additional Prescribing Instructions field become mandatory");			
			
			String[] searchErrors = new String[errors.size()];
			errors.toArray(searchErrors);
			engine.showErrors("Invalid TTA Medication Record", searchErrors);
			return searchErrors;
		}
		
		else if (dose != null && doseUnit == null && doseText==null)//WDEV-11894
		{
			errors.add("If a numeric value is specified in the Dose field then the Dose Unit field become mandatory");
		}
		else if (dose != null && (doseUnit != null || doseText!=null))//WDEV-11894
		{
			if (formm == null || route == null || frequencyUnit==null)//WDEV-11894
			{
				errors.add("If a numeric value is specified in the Dose field then the Form, Route, Frequency fields become mandatory");//WDEV-11894				
			}
			
			else
			{			/*WDEV-11894
				if (frequency != null && frequencyUnit == null)
				{
					errors.add("If a numeric value is specified in the Frequency field then the Frequency Unit field become mandatory");
				}*/
				if (duration != null && durationUnit == null)
				{
					errors.add("If a numeric value is specified in the Duration field then the Duration Unit field become mandatory");
				}
				if (duration == null && durationUnit !=null)
				{
					errors.add("If a value is selected in the Duration Unit field then the Duration field become mandatory");
				}
			}
		}
		else if (dose == null && doseUnit != null)
		{
			errors.add("If a value is selected in the unit field then Dose field become mandatory");
		}
		
		if (errors.size() > 0) 
		{
			String[] searchErrors = new String[errors.size()];
			errors.toArray(searchErrors);
			engine.showErrors("Invalid TTA Medication Record", searchErrors);
			return searchErrors;
		}
		
		return null;
	}

	@Override
	protected void onCcMedicationValueChanged() throws PresentationLogicException 
	{	
		ccMedicationValueChanged();
		openCmbForm();
		updateControlState();
		
	}

	private void ccMedicationValueChanged()
	{
		if (form.ccMedication().isAllSelected() != null	&& form.ccMedication().isAllSelected().booleanValue())
		{
			form.GroupSelection().setValue(GroupSelectionEnumeration.rdoAll);
			form.ccMedication().setHotlist(new Boolean(false));
			form.ccMedication().search();
		}
		form.cmbForm().clear();
		form.cmbRoute().clear();
		form.decDose().setValue(null);
		form.cmbDoseUnit().clear();
		form.txtDoseUnit().setValue(null);
		if (form.ccMedication().getValue() != null && form.ccMedication().getValue().getIClinicalCodingValue() instanceof MedicationRefVo)
		{
			MedicationRefVo medication = (MedicationRefVo) form.ccMedication().getValue().getIClinicalCodingValue();
			if (medication.getID_MedicationIsNotNull())
				populateCmbForm(domain.getForms(medication));
		}
	}

	@Override
	protected void onBtnCloseClick() throws PresentationLogicException 
	{	
		engine.close(DialogResult.OK);
	}

	@Override
	protected void onRadioButtonGroupSelectionValueChanged() throws PresentationLogicException 
	{	
		radioGroupValueChanged(true);
		updateControlState();
	}

	private void radioGroupValueChanged(boolean showOpened)
	{
		//WDEV-11888 - Start
		if (form.GroupSelection().getValue().equals(GroupSelectionEnumeration.rdoMyHotlist))
		{	
			if (form.getMode().equals(FormMode.EDIT))
			{
				//WDEV-15028
				if (form.getLocalContext().getEditedRecord() == null || form.getLocalContext().getEditedRecordIsNotNull() && form.getLocalContext().getEditedRecord().getMedicationIsNotNull() && form.getLocalContext().getEditedRecord().getMedication().getID_Medication().intValue() < 0 )
					populateFavMeds(showOpened);//WDEV - 11979
				else setMedicationHotlist();
			}
		}//WDEV-11888 - End
		else if(form.GroupSelection().getValue().equals(GroupSelectionEnumeration.rdoHotList))
		{
			form.ccMedication().setHotlist(true);
		}
		else if(form.GroupSelection().getValue().equals(GroupSelectionEnumeration.rdoAll))
		{
			form.ccMedication().setHotlist(false);
		}
	}

	private void setMedicationHotlist()//WDEV - 11979
	{
		form.ccMedication().setCustomHotlist(new IClinicalCodingCustomHotlistProvider()
		{
			
			public ValueObjectCollection listCodingItems(String value) throws DomainInterfaceException
			{
					HcpRefVo hcp = (HcpRefVo) domain.getHcpUser();
					MedicationLiteVoCollection listMedicationHotlist = domain.listMedicationHotlist(value, hcp);
					if (listMedicationHotlist != null)
						listMedicationHotlist.sort(false);
					return listMedicationHotlist;
			}
		});
	}

	@Override
	protected void onCmbFormValueChanged() throws PresentationLogicException
	{
		cmbFormValueChanged();
		openCmbRoute();
		updateControlState();
		
	}

	private void cmbFormValueChanged()
	{
		
		form.cmbRoute().clear();
		form.decDose().setValue(null);
		form.cmbDoseUnit().clear();
		form.txtDoseUnit().setValue(null);
		if (form.cmbForm().getValue() != null && form.ccMedication().getValue() != null && form.ccMedication().getValue().getIClinicalCodingValue() instanceof MedicationRefVo)
		{
			MedicationRefVo medication = (MedicationRefVo) form.ccMedication().getValue().getIClinicalCodingValue();
			if (medication.getID_MedicationIsNotNull())
				populateCmbRoute(domain.getRoutes(medication, form.cmbForm().getValue()));
		}
	}

	private void populateCmbRoute(MedicationRouteCollection routes)
	{
		form.cmbRoute().clear();
		if (routes == null)
			return;
		for (int i = 0 ; i < routes.size() ; i++)
		{
			MedicationRoute route = routes.get(i);
			if (route != null)
			{
				form.cmbRoute().newRow(route, route.getText());
			}
		}
		
	}

	private void openCmbRoute()
	{
		if (form.cmbRoute().getValues().size() == 0)
			return;
		if (form.cmbRoute().getValues().size()==1)
		{
			form.cmbRoute().setValue((MedicationRoute) form.cmbRoute().getValues().get(0));
			cmbRouteValueChanged();
			openCmbUnits();
			
		}
		else
			form.cmbRoute().showOpened();
	}

	@Override
	protected void onCmbRouteValueChanged() throws PresentationLogicException
	{
		cmbRouteValueChanged();
		openCmbUnits();
		updateControlState();
		
	}

	private void cmbRouteValueChanged()
	{
		form.decDose().setValue(null);
		form.cmbDoseUnit().clear();
		form.txtDoseUnit().setValue(null);
		if (form.cmbRoute().getValue() != null && form.cmbForm().getValue() != null && form.ccMedication().getValue() != null && form.ccMedication().getValue().getIClinicalCodingValue() instanceof MedicationRefVo)
		{
			MedicationRefVo medication = (MedicationRefVo) form.ccMedication().getValue().getIClinicalCodingValue();
			if (medication.getID_MedicationIsNotNull())
				populateCmbUnits(domain.getUnits(medication, form.cmbForm().getValue(),form.cmbRoute().getValue()));
		}
	}
	private void populateCmbUnits(MedicationDoseUnitCollection units)
	{
		form.cmbDoseUnit().clear();
		if (units == null)
			return;
		for (int i = 0 ; i < units.size() ; i++)
		{
			if (units.get(i)!=null)
			{
				form.cmbDoseUnit().newRow(units.get(i), units.get(i).getIItemText());
			}
		}
		
	}

	private void openCmbUnits()
	{
		if (form.cmbDoseUnit().getValues().size() == 0)
			return;
		if (form.cmbDoseUnit().getValues().size()==1)
			form.cmbDoseUnit().setValue((MedicationDoseUnit) form.cmbDoseUnit().getValues().get(0));
		else
			form.cmbDoseUnit().showOpened();
	}

	private void populateCmbForm(DoseFormIndicatorCollection forms)
	{
		form.cmbForm().clear();
		if (forms==null)
			return;
		for (int i = 0 ; i < forms.size() ; i++)
		{
			DoseFormIndicator tForm = forms.get(i);
			if (tForm == null)
				continue;
			form.cmbForm().newRow(tForm, tForm.getIItemText());
		}
		
	}

	private void openCmbForm()
	{
		if (form.cmbForm().getValues().size() == 0)
			return;
		if (form.cmbForm().getValues().size()==1)
		{
			form.cmbForm().setValue((DoseFormIndicator) form.cmbForm().getValues().get(0));
			cmbFormValueChanged();
			openCmbRoute();
		}	
		else
			form.cmbForm().showOpened();
	}
}