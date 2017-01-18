package ims.framework.interfaces;

public interface ITopButtonConfig
{
	ITopButton[] getITopButtonConfigButtons();
	ITopButtonSection[] getITopButtonConfigSections();
	int getITopButtonConfigNoColumns();
	boolean getITopButtonConfigIncludePatientSelectionHistory();
}
