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
// This code was generated by Gabriel Maxim using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2013 IMS MAXIMS. All rights reserved.

package ims.pathways.forms.rttstatuspointsconfig;

import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.framework.Control;
import ims.framework.FormName;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.pathways.forms.rttstatuspointsconfig.GenForm.grdChildRTTsRow;
import ims.pathways.forms.rttstatuspointsconfig.GenForm.grdRTTStatusPointsRow;
import ims.pathways.vo.RTTStatusPointVo;
import ims.pathways.vo.RTTStatusPointVoCollection;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormModeChanged()
	{
		updateControlsState();
	}
	private void updateControlsState()
	{
		if (form.getMode().equals(FormMode.VIEW))
		{		
			form.btnEdit().setVisible(form.grdRTTStatusPoints().getValue() != null);
			form.getContextMenus().Pathways.getRTTSatusPointsConfigEDITItem().setVisible(form.grdRTTStatusPoints().getValue() != null);
		}
		form.getContextMenus().Pathways.getRTTStatusPointConfigChildSelectNEWItem().setVisible(FormMode.EDIT.equals(form.getMode()));
		form.getContextMenus().Pathways.getRTTStatusPointConfigChildSelectREMOVEItem().setVisible(FormMode.EDIT.equals(form.getMode()) && form.grdChildRTTs().getSelectedRow() != null);
	}
	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialize();
		open();
	}
	private void initialize()
	{
		form.getLocalContext().setselectedRecord(null);
		form.getGlobalContext().Pathways.setFollowingRTTStatusPoints(null);
		form.grdRTTStatusPoints().getRows().clear();
		clearInstanceControls();
	}
	private void open()
	{
		populateRTTStatusPointsGrid();

		if (form.getLocalContext().getselectedRecordIsNotNull())
		{	
			form.getLocalContext().setselectedRecord(domain.getRTTStatusPoint(form.getLocalContext().getselectedRecord()));
			form.grdRTTStatusPoints().setValue(form.getLocalContext().getselectedRecord());
		}	

		if (form.grdRTTStatusPoints().getValue() !=null)
			populateInstanceControlsFromData(form.getLocalContext().getselectedRecord());

		form.setMode(FormMode.VIEW);

	}
	private void populateRTTStatusPointsGrid()
	{
		form.grdRTTStatusPoints().getRows().clear();
		RTTStatusPointVoCollection coll = domain.listRTTStatusPoints();

		if (coll == null || coll.size() == 0)
			return;

		for (int i=0; i<coll.size(); i++)
		{
			RTTStatusPointVo statPointVo = coll.get(i);
			if (statPointVo == null)
				continue;
			grdRTTStatusPointsRow row = form.grdRTTStatusPoints().getRows().newRow();
			row.setcolNatCode(statPointVo.getNationalCode().intValue());
			row.setcolDescription(statPointVo.getDescription().toString());
			row.setcolLocalCode(statPointVo.getLocalCodeIsNotNull() ? statPointVo.getLocalCode().toString() : "");
			row.setValue(statPointVo);
		}


	}
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		open();
	}
	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (save())
			open();
	}
	private boolean save()
	{
		RTTStatusPointVo statusPointVo = populateDataFromScreen(form.getLocalContext().getselectedRecord());

		String[] errors = statusPointVo.validate();

		if ((errors != null) && (errors.length>0))
		{
			engine.showErrors(errors);
			return false;
		}

		try
		{
			form.getLocalContext().setselectedRecord((domain.saveRTTStatusPoint(statusPointVo)));
		}
		catch (StaleObjectException e)
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return false;
		} 
		catch (UniqueKeyViolationException ex)
		{
			engine.showMessage(ex.getMessage());
			form.txtLocalCode().setFocus();
			return false;

		}
		form.getGlobalContext().Pathways.setFollowingRTTStatusPoints(null);
		return true;
	}
	private RTTStatusPointVo populateDataFromScreen(RTTStatusPointVo selectedRecord)
	{
		selectedRecord = form.getLocalContext().getselectedRecord();
		selectedRecord.setLocalCode(form.txtLocalCode().getValue());

		RTTStatusPointVoCollection childRTTcoll = populateChildRTTs();
		selectedRecord.setChildren(childRTTcoll);

		return selectedRecord;
	}

	private RTTStatusPointVoCollection populateChildRTTs()
	{
		RTTStatusPointVoCollection childRTTStatusPoints = new RTTStatusPointVoCollection();

		for (int i=0;i<form.grdChildRTTs().getRows().size(); i++)
		{
			RTTStatusPointVo childVo = form.grdChildRTTs().getRows().get(i).getValue();
			if (childVo == null)
			{
				childVo = new RTTStatusPointVo();
			}	
			childVo.setNationalCode(form.grdChildRTTs().getRows().get(i).getValue().getNationalCode());
			childVo.setDescription(form.grdChildRTTs().getRows().get(i).getValue().getDescription());
			childVo.setLocalCode(form.grdChildRTTs().getRows().get(i).getValue().getLocalCode());
			childRTTStatusPoints.add(childVo);
		}
		return childRTTStatusPoints;
	}

	@Override
	protected void onBtnEditClick() throws ims.framework.exceptions.PresentationLogicException
	{
		editInstance();
	}

	private void editInstance()
	{
		form.setMode(FormMode.EDIT);
		
	}
	@Override
	protected void onGrdRTTStatusPointsSelectionChanged() throws PresentationLogicException
	{
		selectionChanged();
		updateControlsState();

	}
	private void selectionChanged()
	{

		if (form.grdRTTStatusPoints().getValue() instanceof RTTStatusPointVo)
		{
			RTTStatusPointVo selectedRecord = domain.getRTTStatusPoint(form.grdRTTStatusPoints().getValue());

			form.getLocalContext().setselectedRecord(selectedRecord);
			populateInstanceControlsFromData(selectedRecord);
		}

	}

	private void populateInstanceControlsFromData(RTTStatusPointVo selectedRecord)
	{
		clearInstanceControls();

		if (selectedRecord == null)
			return;
		form.intNatCode().setValue(selectedRecord.getNationalCode());
		form.txtDescription().setValue(selectedRecord.getDescription());
		form.txtLocalCode().setValue(selectedRecord.getLocalCodeIsNotNull() ? selectedRecord.getLocalCode() : "");
		if (selectedRecord.getChildrenIsNotNull() && selectedRecord.getChildren().size() > 0)
		{
			populateChildRTTsControls(selectedRecord.getChildren());
		}
	}	


	private void clearInstanceControls()
	{
		form.intNatCode().setValue(null);
		form.txtDescription().setValue(null);
		form.txtLocalCode().setValue(null);
		form.grdChildRTTs().getRows().clear();

	}
	@Override
	protected void onContextMenuItemClick(int menuItemID, Control sender)throws PresentationLogicException
	{
		switch(menuItemID)
		{
		case GenForm.ContextMenus.PathwaysNamespace.RTTSatusPointsConfig.EDIT:
			editInstance();
			break;
		case GenForm.ContextMenus.PathwaysNamespace.RTTStatusPointConfigChildSelect.NEW:
			addOrRemoveChildRTTS();
			break;
		case GenForm.ContextMenus.PathwaysNamespace.RTTStatusPointConfigChildSelect.REMOVE:
			form.grdChildRTTs().getValues().remove(form.grdChildRTTs().getSelectedRow().getValue());
			form.grdChildRTTs().removeSelectedRow();
			break;
		}
		
		updateControlsState();
	}
	private void addOrRemoveChildRTTS()
	{
		engine.open(form.getForms().Pathways.RTTStatusPointSelectDialog, new Object[]{form.getLocalContext().getselectedRecord(),form.grdChildRTTs().getValues()});
	}
	
	@Override
	protected void onFormDialogClosed(FormName formName, DialogResult result) throws PresentationLogicException
	{
		if (formName.equals(form.getForms().Pathways.RTTStatusPointSelectDialog) && DialogResult.OK.equals(result))
		{			
			populateChildRTTsControls(form.getGlobalContext().Pathways.getFollowingRTTStatusPoints());
			updateControlsState();
		}

	}
	private void populateChildRTTsControls(RTTStatusPointVoCollection followingRTTStatusPoints)
	{
		form.grdChildRTTs().getRows().clear();

		if (followingRTTStatusPoints == null || followingRTTStatusPoints.size() == 0)
			return;

		for (int i=0; i<followingRTTStatusPoints.size(); i++)
		{	
			if 	(followingRTTStatusPoints.get(i) == null)
				continue;
			grdChildRTTsRow row = form.grdChildRTTs().getRows().newRow();
			row.setcolNatCode(followingRTTStatusPoints.get(i).getNationalCode());
			row.setcolDesc(followingRTTStatusPoints.get(i).getDescriptionIsNotNull() ? followingRTTStatusPoints.get(i).getDescription() : "");
			row.setcolLocCode(followingRTTStatusPoints.get(i).getLocalCodeIsNotNull() ? followingRTTStatusPoints.get(i).getLocalCode() : " ");

			row.setValue(followingRTTStatusPoints.get(i));
		}		

	}
	@Override
	protected void onGrdChildRTTsSelectionChanged()	throws PresentationLogicException
	{
		updateControlsState();
	}
}