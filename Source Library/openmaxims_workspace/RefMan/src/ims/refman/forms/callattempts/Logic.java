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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.65 build 3182.26548)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.forms.callattempts;

import ims.RefMan.forms.callattempts.GenForm.grdCallsRow;
import ims.RefMan.vo.AppointmentCallAttemptsVo;
import ims.RefMan.vo.AppointmentCallAttemptsVoCollection;
import ims.RefMan.vo.CatsReferralCallAttemptsVo;
import ims.RefMan.vo.ReferralServiceFullVo;
import ims.core.vo.MemberOfStaffLiteVo;
import ims.core.vo.ServiceShortVo;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.framework.utils.DateTimeFormat;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialise();
		
		open();
	}		
	
	private void initialise() 
	{
		ReferralServiceFullVo services = domain.getReferralServices();
		if (services != null && services.getReferralServicesIsNotNull())
		{
			for (ServiceShortVo service : services.getReferralServices())
			{
				form.cmbService().newRow(service,service.getServiceName());
			}
		}
	}
	
	@Override
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		newInstance();
	}
	@Override
	protected void onBtnEditClick() throws ims.framework.exceptions.PresentationLogicException
	{
		updateRecord();
	}
	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (save())
			open();
	}
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		open();
	}
	
	public void open() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.VIEW);
		updateContextMenu();
		clearInstanceControls();
		
		fillGrid();		
	}

	@Override
	protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
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

	private void fillGrid() 
	{
		form.grdCalls().getRows().clear();
		
		CatsReferralCallAttemptsVo voCATS = domain.getCatsReferral(form.getGlobalContext().RefMan.getCatsReferral());
		
		if (voCATS != null)
		{			
			form.getLocalContext().setCatsReferral(voCATS);
			
			voCATS.getCallAttempts().sort();
			
			if (!engine.isDialog()) // WDEV-15485
			{
				AppointmentCallAttemptsVoCollection callAttempts = domain.getCallAttempts(form.getGlobalContext().RefMan.getCatsReferral());

				if (callAttempts == null)
					return;

				for (int i = 0; i < callAttempts.size(); i++)
					addGridRow(callAttempts.get(i));
			}
			else
			{
				for (int i = 0; voCATS.getCallAttemptsIsNotNull() && i < voCATS.getCallAttempts().size(); i++)
					addGridRow(voCATS.getCallAttempts().get(i));
			}
			
			if (form.getLocalContext().getSelectedRecordIsNotNull())
			{
				for(int i = 0 ; i < form.grdCalls().getRows().size() ; i++)
				{
					if (form.grdCalls().getRows().get(i).getValue().getID_AppointmentCallAttemptsIsNotNull()
						&& form.getLocalContext().getSelectedRecord().getID_AppointmentCallAttemptsIsNotNull()
						&& form.grdCalls().getRows().get(i).getValue().getID_AppointmentCallAttempts().equals(form.getLocalContext().getSelectedRecord().getID_AppointmentCallAttempts()) )
					{
						form.getLocalContext().setSelectedRecord(form.grdCalls().getRows().get(i).getValue());
						populateScreenFromData(form.getLocalContext().getSelectedRecord());
						form.grdCalls().setValue(form.getLocalContext().getSelectedRecord());
						form.btnEdit().setVisible(true);
						form.btnEdit().setEnabled(true);
						updateContextMenu();
					}
				}
			}
		}
	}

	private void populateScreenFromData(AppointmentCallAttemptsVo voCallAttempt) 
	{
		clearInstanceControls();

		form.dtimDateTime().setValue(voCallAttempt.getCallDateTime());
		form.cmbService().setValue(voCallAttempt.getAppointmentService());
		MemberOfStaffLiteVo voMos = (MemberOfStaffLiteVo) voCallAttempt.getCaller();
		if (voMos != null)
		{
			form.cmbCaller().newRow(voMos, voMos.getName().toString());
			form.cmbCaller().setValue(voMos);
		}

		form.cmbReason().setValue(voCallAttempt.getReason());
		form.txtComments().setValue(voCallAttempt.getCallerComments());

		form.getLocalContext().setSelectedRecord(voCallAttempt);
	}

	private void addGridRow(AppointmentCallAttemptsVo voCall) 
	{
		grdCallsRow row = form.grdCalls().getRows().newRow();
		row.setValue(voCall);
		row.setBackColor((form.grdCalls().getRows().size() % 2) == 0 ? Color.Beige : Color.Default);

		row.setColDateTime(voCall.getCallDateTimeIsNotNull() ? voCall.getCallDateTime().toString(DateTimeFormat.STANDARD) : "");
		row.setColService(voCall.getAppointmentServiceIsNotNull() ? voCall.getAppointmentService().getServiceName().toString() : "");
		row.setColReason(voCall.getReasonIsNotNull() ? voCall.getReason().toString() : "");
	}

	public void clearInstanceControls()
	{
		form.dtimDateTime().setValue(null);
		form.cmbService().setValue(null);
		form.cmbCaller().setValue(null);
		form.cmbReason().setValue(null);
		form.txtComments().setValue(null);
	}

	public void newInstance() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		clearInstanceControls();
		form.getLocalContext().setSelectedRecord(null); //WDEV-15485
		form.grdCalls().setValue(null);
		
		form.cmbService().setValue((form.getLocalContext().getCatsReferral() != null && form.getLocalContext().getCatsReferral().getReferralDetails() != null ) ? form.getLocalContext().getCatsReferral().getReferralDetails().getService() : null);//WDEV-18013
		MemberOfStaffLiteVo voMos = (MemberOfStaffLiteVo) domain.getMosUser();
		if (voMos != null)
		{
			form.cmbCaller().newRow(voMos, voMos.getName().toString());
			form.cmbCaller().setValue(voMos);
		}
		form.dtimDateTime().setValue(new DateTime());
	}
	
	public boolean save() throws ims.framework.exceptions.PresentationLogicException
	{
		AppointmentCallAttemptsVoCollection voCallColl = populateDataFromScreen();

		if (voCallColl == null)
			return false;
		
		CatsReferralCallAttemptsVo voCATS = form.getLocalContext().getCatsReferral();
		voCATS.setCallAttempts(voCallColl);
		
		String[] arrErrors =  voCATS.validate();	
		if(arrErrors != null)
		{
			engine.showErrors(arrErrors);
			return false;
		}
		
		try
		{			
			form.getLocalContext().setCatsReferral(domain.saveCATSReferral(voCATS));
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
	
	private AppointmentCallAttemptsVoCollection populateDataFromScreen() 
	{
		AppointmentCallAttemptsVo voCall = form.getLocalContext().getSelectedRecord();
		if (voCall == null)
			voCall = new AppointmentCallAttemptsVo();
		
		voCall.setAppointmentService(form.cmbService().getValue());
		voCall.setCallDateTime(form.dtimDateTime().getValue());
		voCall.setCaller((MemberOfStaffLiteVo) domain.getMosUser());
		voCall.setCallerComments(form.txtComments().getValue());
		voCall.setReason(form.cmbReason().getValue());

		String[] arrErrors =  voCall.validate();	
		if(arrErrors != null)
		{
			engine.showErrors(arrErrors);
			return null;
		}

		if (form.grdCalls().getValue() != null)
			form.grdCalls().getSelectedRow().setValue(voCall);
		else
			addGridRow(voCall);
		
		AppointmentCallAttemptsVoCollection voColl = new AppointmentCallAttemptsVoCollection();
		
		for ( int  i = 0 ; i < form.grdCalls().getRows().size() ; i ++)
			voColl.add(form.grdCalls().getRows().get(i).getValue());

		return voColl;
	}
	
	protected void onFormModeChanged() 
	{
		if (form.getMode().equals(FormMode.VIEW))
		{
			if (form.grdCalls().getSelectedRow() == null) //WDEV-15485
			{
				form.btnEdit().setVisible(false);
				form.btnEdit().setEnabled(false);
			}
				
			//--------------------
			if(engine.isDialog())
			{
				form.btnClose().setVisible(true);
				form.btnClose().setEnabled(true);
			}
			else
				form.btnClose().setVisible(false);
			//-------------------
		}

		updateContextMenu();
	}

	private void updateContextMenu()
	{
		if(form.getMode() == FormMode.VIEW)
		{
			form.getContextMenus().getLIPNewItem().setVisible(true);
			form.getContextMenus().getLIPNewItem().setEnabled(true);
			form.getContextMenus().getLIPUpdateItem().setVisible(true);
			form.getContextMenus().getLIPUpdateItem().setEnabled(form.grdCalls().getSelectedRowIndex() >= 0 ? true : false);
		}
		else if(form.getMode() == FormMode.EDIT)
			form.getContextMenus().hideAllLIPMenuItems();
	}

	private void updateRecord()
	{
		form.setMode(FormMode.EDIT);
	}

	@Override
	protected void onGrdCallsSelectionChanged() throws PresentationLogicException
	{
		populateScreenFromData(form.grdCalls().getSelectedRow().getValue()); 
		if (form.getMode().equals(FormMode.VIEW))
		{
			form.btnEdit().setVisible(true);
			form.btnEdit().setEnabled(true);
			
			updateContextMenu();
		}
	}

	@Override
	protected void onBtnCloseClick() throws PresentationLogicException
	{
		engine.close(DialogResult.OK);
	}

	@Override //WDEV-15485
	protected void onRIEDialogClosed(DialogResult result) throws PresentationLogicException
	{
		if (result.equals(DialogResult.OK))
		{
			form.getLocalContext().setSelectedRecord(null);
		}
		
	}

}
