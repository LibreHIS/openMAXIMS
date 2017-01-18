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
// This code was generated by Ander Telleria using IMS Development Environment (version 1.66 build 3233.21563)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.forms.interpreterdetails;


import ims.RefMan.forms.interpreterdetails.GenForm.grdInterpreterDetailsRow;
import ims.RefMan.vo.InterpreterDetailsVo;
import ims.RefMan.vo.InterpreterDetailsVoCollection;
import ims.configuration.gen.ConfigFlag;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;

import java.util.ArrayList;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.VIEW);
		updateControlStatus();
		updateContextMenu();
		//Setting the language of the Interpreters into the Control
		form.txtLanguage().setValue(form.getGlobalContext().RefMan.getLanguage());
		//Filling the interpreters grid
		InterpreterDetailsVoCollection interpreters = domain.getInterpreters(form.getGlobalContext().RefMan.getCatsReferral());
		form.getLocalContext().setinterpretersList(interpreters);
		if (interpreters!=null)
			populateGrid(interpreters);
	}
	
	private void populateGrid(InterpreterDetailsVoCollection interpreters)
	{
		form.grdInterpreterDetails().getRows().clear();
		for (InterpreterDetailsVo item : interpreters)
		{
			grdInterpreterDetailsRow row = form.grdInterpreterDetails().getRows().newRow();
			row.setValue(item);
			if (item.getBookingRefNoIsNotNull())
				row.setcolBookingRefNumber(item.getBookingRefNo());
			if (item.getBookingDateIsNotNull())
				row.setcolDate(item.getBookingDate().toString());
			if (item.getBookingUserIsNotNull())
				row.setcolRecordingUser(item.getBookingUser().toString());
		}
	}


	@Override
	protected void onGrdInterpreterDetailsSelectionChanged() throws ims.framework.exceptions.PresentationLogicException
	{
		
		if (form.grdInterpreterDetails().getSelectedRow()!=null)
		{
			updateControlStatus();
			updateContextMenu();
			grdInterpreterDetailsRow row = form.grdInterpreterDetails().getSelectedRow();
			form.getLocalContext().setInterpreter(row.getValue());
			form.txtBRN().setValue(row.getValue().getBookingRefNo());
			form.dteBooking().setValue(row.getValue().getBookingDate());
			form.ccBU().setValue(row.getValue().getBookingUser());
		}
	}
	@Override
	protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
	{
		switch (menuItemID)
		{
			case ims.RefMan.forms.interpreterdetails.GenForm.ContextMenus.RefManNamespace.InterpreterMenu.New :
				newRecord();
				break;
				
			case ims.RefMan.forms.interpreterdetails.GenForm.ContextMenus.RefManNamespace.InterpreterMenu.Edit :
				editRecord();
				break;
	}
}


	private void clearInterpreterPanel()
	{
		form.txtBRN().setValue(null);
		form.dteBooking().setValue(null);
		form.ccBU().setValue(null);
	
	}


	@Override
	protected void onBtnCancelClick() throws PresentationLogicException
	{
		clearInterpreterPanel();
		form.setMode(FormMode.VIEW);
		populateGrid(form.getLocalContext().getinterpretersList());
		updateContextMenu();
	}
	


	@Override
	protected void onBtnEditClick() throws PresentationLogicException
	{
		editRecord();
	}

	private void editRecord() 
	{
		form.setMode(FormMode.EDIT);
		updateContextMenu();
		updateControlStatus();
	}

	@Override
	protected void onBtnNewClick() throws PresentationLogicException
	{
		newRecord();
	}

	private void newRecord() 
	{
		clearInterpreterPanel();
		form.setMode(FormMode.EDIT);
		updateContextMenu();
		updateControlStatus();
		form.getLocalContext().setInterpreter(null);
		form.grdInterpreterDetails().setValue(null);
	}

	@Override
	protected void onBtnSaveClick() throws PresentationLogicException
	{
		ArrayList<String> errors=validate();
		if (errors.size()>0)
		{
			engine.showErrors(errors.toArray(new String[0]));
			return;
		}
		InterpreterDetailsVo interpreterVo=null;
		if (form.getLocalContext().getInterpreterIsNotNull())
			interpreterVo=form.getLocalContext().getInterpreter();
		else
			interpreterVo=new InterpreterDetailsVo();
		if (form.dteBooking()!=null)
			interpreterVo.setBookingDate(form.dteBooking().getValue());
		if (form.txtBRN()!=null)
			interpreterVo.setBookingRefNo(form.txtBRN().getValue());
		if (form.ccBU()!=null)
		{
			interpreterVo.setBookingUser(form.ccBU().getValue());
		}
		interpreterVo.setCatsReferral(form.getGlobalContext().RefMan.getCatsReferral());
		InterpreterDetailsVoCollection interpreters = form.getLocalContext().getinterpretersList();
		if (interpreterVo.getID_InterpreterDetails()!=null)
		{
			for (int i=0;i<interpreters.size();i++)
			{
				InterpreterDetailsVo interpreter = interpreters.get(i);
				if (interpreter.getID_InterpreterDetails()==interpreterVo.getID_InterpreterDetails())
				{
					interpreters.set(i, interpreter);
				}
			}
		}
		else
			interpreters.add(interpreterVo);
		
		form.getLocalContext().setinterpretersList(interpreters);
		
		try
		{
			form.getLocalContext().setinterpretersList(domain.saveInterpreters(interpreters));
		}
		catch (StaleObjectException e)
		{
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			form.getLocalContext().setinterpretersList(domain.getInterpreters(form.getGlobalContext().RefMan.getCatsReferral()));
			
		}
		populateGrid(form.getLocalContext().getinterpretersList());
		clearInterpreterPanel();
		form.setMode(FormMode.VIEW);
		updateContextMenu();
	}
	
	private void updateContextMenu()
	{
		if (form.getMode().equals(FormMode.VIEW))
		{
		form.getContextMenus().RefMan.getInterpreterMenuNewItem().setVisible(true);
		form.getContextMenus().RefMan.getInterpreterMenuNewItem().setEnabled(true);
		form.getContextMenus().RefMan.getInterpreterMenuEditItem().setEnabled(false);
			if (form.grdInterpreterDetails().getSelectedRow()!=null)
			{
				form.getContextMenus().RefMan.getInterpreterMenuEditItem().setVisible(true);
				form.getContextMenus().RefMan.getInterpreterMenuEditItem().setEnabled(true);
			}
			else
			{
				form.getContextMenus().RefMan.getInterpreterMenuEditItem().setVisible(false);
				form.getContextMenus().RefMan.getInterpreterMenuEditItem().setEnabled(false);
			}
				
		}
		if (form.getMode().equals(FormMode.EDIT))
		{
			form.getContextMenus().RefMan.hideAllInterpreterMenuMenuItems();
		}
	}
	
	private void updateControlStatus()
	{
		if (form.getMode().equals(FormMode.VIEW))
			if (form.grdInterpreterDetails().getSelectedRow()!=null)
			{
				form.ccBU().isRequired(true);
				form.btnEdit().setVisible(true);
				form.btnEdit().setEnabled(true);
			}
			else
			{
				form.btnEdit().setVisible(false);
				form.btnEdit().setEnabled(false);
			}
		if (form.getMode().equals(FormMode.EDIT))
			form.ccBU().isRequired(true);
	}
	
	private ArrayList<String> validate()
	{
		ArrayList<String> errors=new ArrayList<String>();
		if (form.dteBooking().getValue()==null)
			errors.add("Booking Date is Mandatory");
		if (form.ccBU().getValue()==null)
			errors.add("Booking User is Mandatory");
		if (form.txtBRN().getValue()==null)
			errors.add("Booking Reference Number is Mandatory");
		return errors;
	}

	@Override
	protected void onBtnCloseClick() throws PresentationLogicException 
	{
		engine.close(DialogResult.OK);		
	}
}
