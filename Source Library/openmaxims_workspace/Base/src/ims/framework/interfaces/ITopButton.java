package ims.framework.interfaces;

import ims.framework.FormName;

public interface ITopButton
{
	int	getITopButtonID();
	String getITopButtonText();
	FormName getITopButtonForm();
	String getITopButtonUrl();	
	boolean getITopButtonIsAlwaysEnabled();
	boolean getITopButtonPatientMustBeSelected();
	boolean getITopButtonEnabled();
	void setITopButtonEnabled(boolean value);
	boolean getITopButtonContextDependent();
	void setITopButtonContextDependent(boolean value);
	boolean getITopButtonDisplayMaximiseButton();
	boolean getITopButtonDisplayCloseButton();	
}
