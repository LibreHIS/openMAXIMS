// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.booktheatreslotdetaildialog;

public final class FormInfo extends ims.framework.FormInfo
{
	private static final long serialVersionUID = 1L;
	public FormInfo(Integer formId)
	{
		super(formId);
	}
	public String getNamespaceName()
	{
		return "RefMan";
	}
	public String getFormName()
	{
		return "BookTheatreSlotDetailDialog";
	}
	public int getWidth()
	{
		return 544;
	}
	public int getHeight()
	{
		return 640;
	}
	public String[] getContextVariables()
	{
		return new String[] { "_cv_Scheduling.Sch_BookingTheatre", "_cv_Scheduling.BookTheatreSlotDialogData", "_cv_RefMan.SelectedPatientElectiveList", "_cv_Scheduling.AppointmentIds", "_cv_RefMan.SelectedWaitingListConfig", "_cv_RefMan.ReferralEROD" };
	}
	public String getLocalVariablesPrefix()
	{
		return "_lv_RefMan.BookTheatreSlotDetailDialog.__internal_x_context__" + String.valueOf(getFormId());
	}
	public ims.framework.FormInfo[] getComponentsFormInfo()
	{
		ims.framework.FormInfo[] componentsInfo = new ims.framework.FormInfo[0];
		return componentsInfo;
	}
	public String getImagePath()
	{
		return "";
	}
}