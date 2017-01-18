package ims.domain;

import ims.framework.interfaces.IFormProvider;

public abstract class FormFactory
{
	public abstract boolean hasFormProvider();
	public abstract IFormProvider getFormProvider();	

}
