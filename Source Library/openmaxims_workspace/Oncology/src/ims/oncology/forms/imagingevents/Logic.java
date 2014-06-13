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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.53 build 2643.26724)
// Copyright (C) 1995-2007 IMS MAXIMS plc. All rights reserved.

package ims.oncology.forms.imagingevents;

import ims.clinical.vo.lookups.CodingItemType;
import ims.core.vo.CancerImagingEventLiteVo;
import ims.core.vo.LocSiteLiteVoCollection;
import ims.core.vo.lookups.Specialty;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.Control;
import ims.framework.MessageButtons;
import ims.framework.MessageIcon;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.oncology.forms.imagingevents.GenForm.grdDetailsRow;
import ims.oncology.forms.imagingevents.GenForm.ctn1Container.GroupSelectionEnumeration;
import ims.oncology.vo.ImagingEventsVo;
import ims.oncology.vo.ImagingEventsVoCollection;
import ims.vo.interfaces.IClinicalCodingValue;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		loadLocSite();
		initializeCustomControl();
		open();
	}
	
	private void initializeCustomControl() 
	{
		form.ctn1().customControlCodingItem().setCodingItemType(CodingItemType.IMAGING_EVENT);
//		form.ctn1().customControlCodingItem().setHotlist(new Boolean(false));

		form.ctn1().customControlCodingItem().setSearchAllSelected(Boolean.FALSE);

	
		form.ctn1().customControlCodingItem().setHotlist(new Boolean(true));
		form.ctn1().customControlCodingItem().setSpecialty(getSpecialty());
	}

	private Specialty getSpecialty() 
	{
		if ( (form.getGlobalContext().Core.getEpisodeofCareShortIsNotNull() )
			&& (form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialtyIsNotNull()) )
		{
			return form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialty();
		}
		return null;
	}

	private void loadLocSite() 
	{
		LocSiteLiteVoCollection voColl = domain.listLocSite("%%");
		for (int i = 0; voColl != null && i < voColl.size(); i++)
			form.ctn1().cmbHospitalPerformed().newRow(voColl.get(i), voColl.get(i).getName());
	}

	protected void onGrdDetailsSelectionChanged() throws ims.framework.exceptions.PresentationLogicException
	{
		populateScreenFromData(form.grdDetails().getSelectedRow().getValue()); 
		if (form.getMode().equals(FormMode.VIEW))
		{
			//form.btnUpdate().setVisible(true);
			//form.btnUpdate().setEnabled(true);
			
			updateContextMenu();
		}
		updateControlsState();
	}
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		newInstance();
	}

	protected void onBtnUpdateClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
	}
	
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (save())
			open();
	}
	
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		open();
	}
	
	public void open() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.VIEW);
		clearInstanceControls();
		fillGrid();
		updateContextMenu();
		updateControlsState();
	}
	
	private void fillGrid() 
	{
		form.grdDetails().getRows().clear();
		
		
		//wdev-11556
		ImagingEventsVoCollection voImagingEventsColl = domain.listImagingEventsVo(form.getGlobalContext().Core.getEpisodeofCareShort());
		//-----
		if (voImagingEventsColl != null && voImagingEventsColl.size() > 0)
		{			
			for (int i = 0 ; i < voImagingEventsColl.size() ; i++)
				addGridRow(voImagingEventsColl.get(i));
			
			if (form.getLocalContext().getCurrentRecordIsNotNull())
			{
				form.grdDetails().setValue(form.getLocalContext().getCurrentRecord());
				form.getLocalContext().setCurrentRecord(form.grdDetails().getValue());
				populateScreenFromData(form.getLocalContext().getCurrentRecord());
				
			}
		
		}
	}

	private void populateScreenFromData(ImagingEventsVo voImagingEvent) 
	{
		clearInstanceControls();
		if (voImagingEvent == null)
			return;

		form.ctn1().cmbHospitalPerformed().setValue(voImagingEvent.getHospitalSiteOfImage());
		form.ctn1().dteImaging().setValue(voImagingEvent.getImageDate());
		if (voImagingEvent.getImagingEventIsNotNull())
			form.ctn1().customControlCodingItem().setValue(voImagingEvent.getImagingEvent());

		if (voImagingEvent.getImagingEventDescriptionIsNotNull())
		{
			form.ctn1().customControlCodingItem().setSelectedItem(voImagingEvent.getImagingEvent());
			form.ctn1().customControlCodingItem().setDescription(voImagingEvent.getImagingEventDescription());
		}

		form.ctn1().cmbAnatomicalSite().setValue(voImagingEvent.getAnatomicalSite());
		form.ctn1().dteReport().setValue(voImagingEvent.getReportDate());
		form.ctn1().intLesionSize().setValue(voImagingEvent.getLesionSize());
		form.ctn1().customControlAuthoringInfo().setValue(voImagingEvent.getAuthoringInformation());
		form.ctn1().dteRequest().setValue(voImagingEvent.getRequestedDate());
		form.ctn1().cmbResult().setValue(voImagingEvent.getInvestigationResult());
		form.getLocalContext().setCurrentRecord(voImagingEvent);
	}

	private void addGridRow(ImagingEventsVo voImagingEvent) 
	{
		grdDetailsRow row = form.grdDetails().getRows().newRow();
		row.setValue(voImagingEvent);
		row.setBackColor((form.grdDetails().getRows().size() % 2) == 0 ? Color.Beige : Color.Default);

		row.setColHospital(voImagingEvent.getHospitalSiteOfImageIsNotNull() ? voImagingEvent.getHospitalSiteOfImage().getName() : "");
		row.setColImagingDate(voImagingEvent.getImageDateIsNotNull() ? voImagingEvent.getImageDate().toString() : "");
//		row.setColModality(voImagingEvent.getImagingEventIsNotNull() ? voImagingEvent.getImagingEvent().getImagingEventName() : voImagingEvent.getImagingEventDescriptionIsNotNull() ? voImagingEvent.getImagingEventDescription() : "");
		row.setColModality(voImagingEvent.getImagingEventDescriptionIsNotNull() ? voImagingEvent.getImagingEventDescription() : "");
		row.setColSite(voImagingEvent.getAnatomicalSiteIsNotNull() ? voImagingEvent.getAnatomicalSite().getText() : "");
		row.setColReportDate(voImagingEvent.getReportDateIsNotNull() ? voImagingEvent.getReportDate().toString() : "");
		row.setColSize(voImagingEvent.getLesionSizeIsNotNull() ? voImagingEvent.getLesionSize().toString() : "");
		row.setColReportResult(voImagingEvent.getInvestigationResultIsNotNull() ? voImagingEvent.getInvestigationResult().toString() : "");
	}

	public void clearInstanceControls()
	{
		form.ctn1().customControlCodingItem().clear();
		form.ctn1().cmbHospitalPerformed().setValue(null);
		form.ctn1().dteImaging().setValue(null);
		form.ctn1().cmbAnatomicalSite().setValue(null);
		form.ctn1().dteReport().setValue(null);
		form.ctn1().intLesionSize().setValue(null);
		form.ctn1().customControlAuthoringInfo().setValue(null);
		form.ctn1().dteRequest().setValue(null);
		form.ctn1().cmbResult().setValue(null);

	}

	public void newInstance() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		clearInstanceControls();
		form.getLocalContext().setCurrentRecord(new ImagingEventsVo());
		form.ctn1().customControlAuthoringInfo().initializeComponent();
		
		form.ctn1().GroupSelection().setEnabled(true);
		form.ctn1().GroupSelection().setValue(GroupSelectionEnumeration.rdoSpecialty);
		
		form.ctn1().customControlCodingItem().clear();
		form.ctn1().customControlCodingItem().setSearchAllSelected(Boolean.FALSE);
		form.ctn1().customControlCodingItem().setHotlist(Boolean.TRUE);
		// WDEV-3126 correcting the tab order. 
		form.ctn1().customControlCodingItem().setFocus();
		
		form.ctn1().setcustomControlCodingItemEnabled(true);
		form.ctn1().customControlCodingItem().setParentEditing(new Boolean(form.getMode().equals(FormMode.EDIT)));

	}
	
	public boolean save() throws ims.framework.exceptions.PresentationLogicException
	{
		ImagingEventsVo voImagingEvent = (ImagingEventsVo) form.getLocalContext().getCurrentRecord();
		voImagingEvent = populateDataFromScreen(voImagingEvent);
		if (voImagingEvent == null)
			return false;
		
		//wdev-11556
		if( form.getGlobalContext().Core.getCurrentCareContext() != null)
			if (! voImagingEvent.getCareContextIsNotNull())
				voImagingEvent.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		if (! voImagingEvent.getEpisodeOfCareIsNotNull())
			voImagingEvent.setEpisodeOfCare(form.getGlobalContext().Core.getEpisodeofCareShort());
		//-------------------
		
		String[] arrErrors =  voImagingEvent.validate();	
		if(arrErrors != null)
		{
			engine.showErrors(arrErrors);
			return false;
		}
		
		try
		{			
			form.getLocalContext().setCurrentRecord(domain.saveImagingEventVo(voImagingEvent));
		}
		catch(StaleObjectException e)
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return false;
		} 
		
		form.setMode(FormMode.VIEW);
		
		return true;
	}
	
	private ImagingEventsVo populateDataFromScreen(ImagingEventsVo voImagingEvent) 
	{
		voImagingEvent.setHospitalSiteOfImage(form.ctn1().cmbHospitalPerformed().getValue());
		voImagingEvent.setImageDate(form.ctn1().dteImaging().getValue());
		IClinicalCodingValue voResult = form.ctn1().customControlCodingItem().getValue();

		if (voResult != null)
		{

			if (voResult.getIClinicalCodingValue() instanceof CancerImagingEventLiteVo)
			{
				voImagingEvent.setImagingEvent((CancerImagingEventLiteVo) voResult.getIClinicalCodingValue());
				voImagingEvent.setImagingEventDescription(voResult.getIClinicalCodingValueDescription());						
			}
			else
			{
				throw new CodingRuntimeException("CancerImagingEventLiteVo expected from component");
			}
		}
		else if (voImagingEvent.getID_ImagingeventsIsNotNull() 
				&& voImagingEvent.getImagingEventDescriptionIsNotNull()) 
		{
			engine.showMessage("A coding Value and Description are mandatory. Please enter one.", "Missing Coding Data", MessageButtons.OK,MessageIcon.WARNING);
			return null;
		}
		voImagingEvent.setAnatomicalSite(form.ctn1().cmbAnatomicalSite().getValue());
		voImagingEvent.setReportDate(form.ctn1().dteReport().getValue());
		voImagingEvent.setLesionSize(form.ctn1().intLesionSize().getValue());
		voImagingEvent.setAuthoringInformation(form.ctn1().customControlAuthoringInfo().getValue());
		voImagingEvent.setRequestedDate(form.ctn1().dteRequest().getValue());
		if (form.ctn1().cmbResult()!=null)
			voImagingEvent.setInvestigationResult(form.ctn1().cmbResult().getValue());
		return voImagingEvent;
	}
	
	public void updateControlsState()
	{
		if(form.getMode().equals(FormMode.VIEW))
		{
			form.btnUpdate().setVisible(form.grdDetails().getValue() instanceof ImagingEventsVo);
			form.btnUpdate().setEnabled(form.grdDetails().getValue() instanceof ImagingEventsVo);
		}
		//WDEV-10755 expand on edit
		else
			form.ctn1().setCollapsed(false);
	}
	
	public void updateInstance()
	{
		// TODO: Add you code here.
	}

	protected void onCustomControlCodingItemValueChanged() throws PresentationLogicException 
	{
		if (form.ctn1().customControlCodingItem().isAllSelected() != null && form.ctn1().customControlCodingItem().isAllSelected().booleanValue())
		{
			form.ctn1().GroupSelection().setValue(GroupSelectionEnumeration.rdoAll);
			form.ctn1().customControlCodingItem().setHotlist(new Boolean(false));
			form.ctn1().customControlCodingItem().search();						
		}		
	}
	
	@Override
	protected void onFormModeChanged() 
	{
		boolean bAddnew = form.getMode().equals(FormMode.EDIT) && 
		  (form.getLocalContext().getCurrentRecord() == null ||  
		  form.getLocalContext().getCurrentRecord().getID_Imagingevents() == null);

		if (form.getMode().equals(FormMode.VIEW))
		{
			//form.btnUpdate().setEnabled(false);//form.grdDetails().getSelectedRow() != null);
			form.ctn1().setcustomControlCodingItemEnabled(true);
		}
		else
		{
			form.ctn1().customControlAuthoringInfo().setIsRequiredPropertyToControls(true);
			form.ctn1().setcustomControlCodingItemEnabled(true);
			form.ctn1().GroupSelection().setEnabled(bAddnew);
		}
		form.ctn1().setcustomControlCodingItemEnabled(true);
		
		
		form.ctn1().customControlCodingItem().setParentEditing(new Boolean(form.getMode().equals(FormMode.EDIT)));

		updateControlsState();
		updateContextMenu();
	}

	@Override
	protected void onDteReportValueChanged() throws PresentationLogicException 
	{
		// TODO Auto-generated method stub
		
	}

	private void updateContextMenu()
	{
		if(form.getMode() == FormMode.VIEW)
		{
			form.getContextMenus().getLIPNewItem().setVisible(true);
			form.getContextMenus().getLIPNewItem().setEnabled(true);
			form.getContextMenus().getLIPUpdateItem().setVisible(true);
			form.getContextMenus().getLIPUpdateItem().setEnabled(form.grdDetails().getValue() instanceof ImagingEventsVo ? true : false);
		}
		else if(form.getMode() == FormMode.EDIT)
			form.getContextMenus().hideAllLIPMenuItems();
	}

	@Override
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException 
	{
		switch(menuItemID)
		{
			case GenForm.ContextMenus.LIP.New:
				newInstance();
				break;
			case GenForm.ContextMenus.LIP.Update:
				updateRecord();
				break;
			default:
				break;
		}
	}

	private void updateRecord()
	{
		form.setMode(FormMode.EDIT);
	}

	@Override
	protected void onRadioButtonGroupSelectionValueChanged() throws PresentationLogicException 
	{
		form.ctn1().customControlCodingItem().setHotlist(form.ctn1().GroupSelection().getValue().equals(GroupSelectionEnumeration.rdoAll) ? new Boolean(false) : new Boolean(true));		
	}

}