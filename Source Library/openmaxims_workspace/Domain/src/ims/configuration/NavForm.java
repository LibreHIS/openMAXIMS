package ims.configuration;

import java.io.Serializable;
import java.util.ArrayList;

import ims.framework.enumerations.FormAccess;
import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.IGenericIdentifier;
import ims.framework.interfaces.INavForm;

public class NavForm implements INavForm, Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private String nodeText;
	private IAppForm appForm;
	private boolean readOnly;
	private int positionIndex;
	private ArrayList<IGenericIdentifier> linkedClasses = new ArrayList<IGenericIdentifier>();
	
	public String getNodeText()
	{
		return nodeText;
	}
	public IAppForm getAppForm()
	{
		return appForm;
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	public FormAccess getFormAccessForRip()
	{
		return FormAccess.READ_WRITE;
	}
	
	public FormAccess getFormAccessForEpisEnd()
	{
		return FormAccess.READ_WRITE;
	}
	
	public int getPositionIndex()
	{
		return positionIndex;
	}
	
	public void setAppForm(IAppForm appForm)
	{
		this.appForm = appForm;
	}
	
	public void setNodeText(String nodeText)
	{
		this.nodeText = nodeText;
	}
	
	public void setPositionIndex(int positionIndex)
	{
		this.positionIndex = positionIndex;
	}
	
	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
	}
	public IGenericIdentifier[] getIdentifiers()
	{
		IGenericIdentifier[] ret = new IGenericIdentifier[linkedClasses.size()];
		linkedClasses.toArray(ret);
		return ret;
	}

}
