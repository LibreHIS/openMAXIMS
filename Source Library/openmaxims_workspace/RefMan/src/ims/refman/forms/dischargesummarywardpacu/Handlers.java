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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.dischargesummarywardpacu;

import ims.framework.delegates.*;

abstract public class Handlers implements ims.framework.UILogic, IFormUILogicCode
{
	abstract protected void bindgrdAppointmentscolStatusLookup();
	abstract protected void bindcmbFollowUpTypeLookup();
	abstract protected void defaultcmbFollowUpTypeLookupValue();
	abstract protected void bindcmbDischargeTypeLookup();
	abstract protected void defaultcmbDischargeTypeLookupValue();
	abstract protected void bindgrdMedicationscolFrequencyLookup();
	abstract protected void bindgrdDressingAndAidscolDressingAidLookup();
	abstract protected void bindgrdDressingAndAidscolPeriodIntervalLookup();
	abstract protected void onFormModeChanged();
	abstract protected void onMessageBoxClosed(int messageBoxId, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onFormDialogClosed(ims.framework.FormName formName, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnCloseClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnEditClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onRecBrowserValueChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnTTOPrintClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnDischargeClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnBookAppointmentClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onGrdAppointmentsSelectionChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onGrdAppointmentsSelectionCleared() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void oncmbFollowUpTypeValueSet(Object value);
	abstract protected void oncmbDischargeTypeValueSet(Object value);
	abstract protected void onGrdMedicationsSelectionChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onGrdMedicationsSelectionCleared() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onGrdDressingAndAidsSelectionChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onGrdDressingAndAidsSelectionCleared() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnPrintDischargeReportClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnPrintDischargeSummaryClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException;

	public final void setContext(ims.framework.UIEngine engine, GenForm form)
	{
		this.engine = engine;
		this.form = form;

		this.form.setFormModeChangedEvent(new FormModeChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle()
			{
				onFormModeChanged();
			}
		});
		this.form.setMessageBoxClosedEvent(new MessageBoxClosed()
		{
			private static final long serialVersionUID = 1L;
			public void handle(int messageBoxId, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException
			{
				onMessageBoxClosed(messageBoxId, result);
			}
		});
		this.form.setFormOpenEvent(new FormOpen()
		{
			private static final long serialVersionUID = 1L;
			public void handle(Object[] args) throws ims.framework.exceptions.PresentationLogicException
			{
				bindLookups();
				onFormOpen(args);
			}
		});
		this.form.setFormDialogClosedEvent(new FormDialogClosed()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.FormName formName, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException
			{
				onFormDialogClosed(formName, result);
			}
		});
		this.form.btnSave().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnSaveClick();
			}
		});
		this.form.btnClose().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnCloseClick();
			}
		});
		this.form.btnEdit().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnEditClick();
			}
		});
		this.form.recBrowser().setValueChangedEvent(new ValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onRecBrowserValueChanged();
			}
		});
		this.form.btnTTOPrint().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnTTOPrintClick();
			}
		});
		this.form.btnDischarge().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnDischargeClick();
			}
		});
		this.form.btnCancel().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnCancelClick();
			}
		});
		this.form.btnNew().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnNewClick();
			}
		});
		this.form.lyrContent().tabDischargeDetails().setTabActivatedEvent(new ims.framework.delegates.TabActivated()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onlyrContenttabDischargeDetailsActivated();
			}
		});
		this.form.lyrContent().tabNursing().setTabActivatedEvent(new ims.framework.delegates.TabActivated()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onlyrContenttabNursingActivated();
			}
		});
		this.form.lyrContent().tabDischargeSummary().setTabActivatedEvent(new ims.framework.delegates.TabActivated()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onlyrContenttabDischargeSummaryActivated();
			}
		});
		this.form.lyrContent().tabDischargeDetails().btnBookAppointment().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnBookAppointmentClick();
			}
		});
		this.form.lyrContent().tabDischargeDetails().grdAppointments().setSelectionChangedEvent(new GridSelectionChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.enumerations.MouseButton mouseButton) throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdAppointmentsSelectionChanged();
			}
		});
		this.form.lyrContent().tabDischargeDetails().grdAppointments().setSelectionClearedEvent(new SelectionCleared()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdAppointmentsSelectionCleared();
			}
		});
		this.form.lyrContent().tabDischargeDetails().cmbFollowUpType().setValueSetEvent(new ComboBoxValueSet()
		{
			private static final long serialVersionUID = 1L;
			public void handle(Object value)
			{
				oncmbFollowUpTypeValueSet(value);
			}
		});
		this.form.lyrContent().tabDischargeDetails().cmbDischargeType().setValueSetEvent(new ComboBoxValueSet()
		{
			private static final long serialVersionUID = 1L;
			public void handle(Object value)
			{
				oncmbDischargeTypeValueSet(value);
			}
		});
		this.form.lyrContent().tabNursing().grdMedications().setSelectionChangedEvent(new GridSelectionChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.enumerations.MouseButton mouseButton) throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdMedicationsSelectionChanged();
			}
		});
		this.form.lyrContent().tabNursing().grdMedications().setSelectionClearedEvent(new SelectionCleared()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdMedicationsSelectionCleared();
			}
		});
		this.form.lyrContent().tabNursing().grdDressingAndAids().setSelectionChangedEvent(new GridSelectionChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.enumerations.MouseButton mouseButton) throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdDressingAndAidsSelectionChanged();
			}
		});
		this.form.lyrContent().tabNursing().grdDressingAndAids().setSelectionClearedEvent(new SelectionCleared()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onGrdDressingAndAidsSelectionCleared();
			}
		});
		this.form.lyrContent().tabDischargeSummary().btnPrintDischargeReport().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnPrintDischargeReportClick();
			}
		});
		this.form.lyrContent().tabDischargeSummary().btnPrintDischargeSummary().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnPrintDischargeSummaryClick();
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentBOOK_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.BOOK_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentBOOK_WARD_ATTENDANCEItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.BOOK_WARD_ATTENDANCE, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentCANCEL_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.CANCEL_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentREBOOK_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.REBOOK_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentAMEND_BOOKING_COMMENTSItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.AMEND_BOOKING_COMMENTS, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentORDER_INVESTIGATIONItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.ORDER_INVESTIGATION, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentBOOK_THEATRE_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.BOOK_THEATRE_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentCANCEL_THEATRE_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.CANCEL_THEATRE_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentREBOOK_THEATRE_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.REBOOK_THEATRE_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentVIEW_APPT_HISTORYItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.VIEW_APPT_HISTORY, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentPRINTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.PRINT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentCANCEL_CAB_APPTItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.CANCEL_CAB_APPT, sender);
			}
		});
		this.form.getContextMenus().RefMan.getReferralAppointmentDetailsComponentVIEW_APPOINTMENT_OUTCOMEItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.ReferralAppointmentDetailsComponent.VIEW_APPOINTMENT_OUTCOME, sender);
			}
		});
		this.form.getContextMenus().RefMan.getmedicationsMenuaddMedicationItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.medicationsMenu.addMedication, sender);
			}
		});
		this.form.getContextMenus().RefMan.getmedicationsMenuremoveMedicationItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.medicationsMenu.removeMedication, sender);
			}
		});
		this.form.getContextMenus().RefMan.getmedicationsMenueditMedicationItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.medicationsMenu.editMedication, sender);
			}
		});
		this.form.getContextMenus().RefMan.getDischargeSummaryWardPacuADDItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.DischargeSummaryWardPacu.ADD, sender);
			}
		});
		this.form.getContextMenus().RefMan.getDischargeSummaryWardPacuREMOVEItem().setClickEvent(new ims.framework.delegates.MenuItemClick()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
			{
				onContextMenuItemClick(GenForm.ContextMenus.RefManNamespace.DischargeSummaryWardPacu.REMOVE, sender);
			}
		});
	}
	protected void bindLookups()
	{
		bindgrdAppointmentscolStatusLookup();
		bindcmbFollowUpTypeLookup();
		bindcmbDischargeTypeLookup();
		bindgrdMedicationscolFrequencyLookup();
		bindgrdDressingAndAidscolDressingAidLookup();
		bindgrdDressingAndAidscolPeriodIntervalLookup();
	}
	protected void rebindAllLookups()
	{
		bindgrdAppointmentscolStatusLookup();
		bindcmbFollowUpTypeLookup();
		bindcmbDischargeTypeLookup();
		bindgrdMedicationscolFrequencyLookup();
		bindgrdDressingAndAidscolDressingAidLookup();
		bindgrdDressingAndAidscolPeriodIntervalLookup();
	}
	protected void defaultAllLookupValues()
	{
		defaultcmbFollowUpTypeLookupValue();
		defaultcmbDischargeTypeLookupValue();
	}
	private void onlyrContenttabDischargeDetailsActivated()
	{
		this.form.lyrContent().showtabDischargeDetails();
	}
	private void onlyrContenttabNursingActivated()
	{
		this.form.lyrContent().showtabNursing();
	}
	private void onlyrContenttabDischargeSummaryActivated()
	{
		this.form.lyrContent().showtabDischargeSummary();
	}

	public void free()
	{
		this.engine = null;
		this.form = null;
	}
	protected ims.framework.UIEngine engine;
	protected GenForm form;
}
