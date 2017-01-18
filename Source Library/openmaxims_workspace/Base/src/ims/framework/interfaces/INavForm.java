package ims.framework.interfaces;

import ims.framework.enumerations.FormAccess;

public interface INavForm
{
	public String getNodeText();
	public IAppForm getAppForm();
	public boolean isReadOnly();
	public FormAccess getFormAccessForRip();
	public FormAccess getFormAccessForEpisEnd();
	public int getPositionIndex();
	public IGenericIdentifier[] getIdentifiers();
}
