// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.referralwizard;

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
		return "ReferralWizard";
	}
	public int getWidth()
	{
		return 848;
	}
	public int getHeight()
	{
		return 632;
	}
	public String[] getContextVariables()
	{
		return new String[] { "_cv_Core.GPDetails", "_cv_Core.PatientToBeDisplayed", "_cv_Admin.Practice", "_cv_RefMan.CatsReferralWizard", "_cv_RefMan.ReferralTransfer", "_cv_RefMan.PatientsGP" };
	}
	public String getLocalVariablesPrefix()
	{
		return "_lv_RefMan.ReferralWizard.__internal_x_context__" + String.valueOf(getFormId());
	}
	public ims.framework.FormInfo[] getComponentsFormInfo()
	{
		ims.framework.FormInfo[] componentsInfo = new ims.framework.FormInfo[4];
		componentsInfo[0] = new ims.core.forms.patientsearchcomponent.FormInfo(102276);
		componentsInfo[1] = new ims.core.forms.demographicscomponent.FormInfo(134124);
		componentsInfo[2] = new ims.RefMan.forms.referraldetailscomponent.FormInfo(134118);
		componentsInfo[3] = new ims.core.forms.uploaddocument.FormInfo(102264);
		return componentsInfo;
	}
	public String getImagePath()
	{
		return "Images/RefMan/referral_wizard_48.png";
	}
}