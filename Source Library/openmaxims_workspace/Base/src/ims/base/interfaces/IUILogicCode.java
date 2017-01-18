package ims.base.interfaces;

import ims.framework.exceptions.FormOpenException;
import ims.framework.exceptions.PresentationLogicException;

public interface IUILogicCode
{	
	void initialize() throws FormOpenException;
	void open() throws PresentationLogicException;
}
