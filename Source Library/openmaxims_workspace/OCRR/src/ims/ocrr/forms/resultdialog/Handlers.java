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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.ocrr.forms.resultdialog;

import ims.framework.delegates.*;

abstract public class Handlers implements ims.framework.UILogic, IFormUILogicCode
{
	abstract protected void onFormOpen() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onFormDialogClosed(ims.framework.FormName formName, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnClericalTaskClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnCommentsClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onLnkViewPatientDetailsClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnViewPACSClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onChkMarkForReviewValueChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onChkMarkAsSeenValueChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onChkEnableExitValueChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onChkMarkAsCheckedValueChanged() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnPrintResultClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnPrintOrderClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnStatusHistoryClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnResultHistoryClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onBtnCumulResultClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onLnkViewOrderNotesClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onDyngrdResultsCellButtonClicked(ims.framework.controls.DynamicGridCell cell);
	abstract protected void onDyngrdResultsCellValueChanged(ims.framework.controls.DynamicGridCell cell);
	abstract protected void onImbCloseClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onImbNextSpecClick() throws ims.framework.exceptions.PresentationLogicException;
	abstract protected void onImbPrevSpecClick() throws ims.framework.exceptions.PresentationLogicException;

	public final void setContext(ims.framework.UIEngine engine, GenForm form)
	{
		this.engine = engine;
		this.form = form;

		this.form.setFormOpenEvent(new FormOpen()
		{
			private static final long serialVersionUID = 1L;
			public void handle(Object[] args) throws ims.framework.exceptions.PresentationLogicException
			{
				onFormOpen();
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
		this.form.btnClericalTask().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnClericalTaskClick();
			}
		});
		this.form.btnComments().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnCommentsClick();
			}
		});
		this.form.lnkViewPatientDetails().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onLnkViewPatientDetailsClick();
			}
		});
		this.form.btnViewPACS().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnViewPACSClick();
			}
		});
		this.form.chkMarkForReview().setValueChangedEvent(new ValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onChkMarkForReviewValueChanged();
			}
		});
		this.form.chkMarkAsSeen().setValueChangedEvent(new ValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onChkMarkAsSeenValueChanged();
			}
		});
		this.form.chkEnableExit().setValueChangedEvent(new ValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onChkEnableExitValueChanged();
			}
		});
		this.form.chkMarkAsChecked().setValueChangedEvent(new ValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onChkMarkAsCheckedValueChanged();
			}
		});
		this.form.btnPrintResult().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnPrintResultClick();
			}
		});
		this.form.btnPrintOrder().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnPrintOrderClick();
			}
		});
		this.form.btnStatusHistory().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnStatusHistoryClick();
			}
		});
		this.form.btnResultHistory().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnResultHistoryClick();
			}
		});
		this.form.btnCumulResult().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onBtnCumulResultClick();
			}
		});
		this.form.lnkViewOrderNotes().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onLnkViewOrderNotesClick();
			}
		});
		this.form.dyngrdResults().setDynamicGridCellButtonClickedEvent(new DynamicGridCellButtonClicked()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.controls.DynamicGridCell cell) throws ims.framework.exceptions.PresentationLogicException
			{
				onDyngrdResultsCellButtonClicked(cell);
			}
		});
		this.form.dyngrdResults().setDynamicGridCellValueChangedEvent(new DynamicGridCellValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle(ims.framework.controls.DynamicGridCell cell) throws ims.framework.exceptions.PresentationLogicException
			{
				onDyngrdResultsCellValueChanged(cell);
			}
		});
		this.form.imbClose().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onImbCloseClick();
			}
		});
		this.form.imbNextSpec().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onImbNextSpecClick();
			}
		});
		this.form.imbPrevSpec().setClickEvent(new Click()
		{
			private static final long serialVersionUID = 1L;
			public void handle() throws ims.framework.exceptions.PresentationLogicException
			{
				onImbPrevSpecClick();
			}
		});
	}

	public void free()
	{
		this.engine = null;
		this.form = null;
	}
	protected ims.framework.UIEngine engine;
	protected GenForm form;
}