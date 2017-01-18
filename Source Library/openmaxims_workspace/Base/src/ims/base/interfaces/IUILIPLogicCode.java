package ims.base.interfaces;

import ims.framework.exceptions.PresentationLogicException;

public interface IUILIPLogicCode extends IUILogicCode
{	
	boolean allowNew();
	boolean allowUpdate();
	void clear();
	void clearInstanceControls();
	void newInstance() throws PresentationLogicException;		
	boolean save() throws PresentationLogicException;
	void search();
	void updateControlsState();
	void updateInstance();		
	String[] validateUIRules();
}
