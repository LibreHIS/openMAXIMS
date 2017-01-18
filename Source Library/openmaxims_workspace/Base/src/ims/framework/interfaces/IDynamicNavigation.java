package ims.framework.interfaces;

import ims.base.interfaces.IModifiable;
import ims.framework.enumerations.FormAccess;

public interface IDynamicNavigation extends IModifiable
{
	boolean shouldRenderForm();	
	void renderForm(StringBuffer sb) throws Exception;
	void preRenderData() throws Exception;
	void renderData(StringBuffer sb) throws Exception;
	INavForm getForm(int id);
	public Integer getUniqueNavigationId(int formId);
	INavigation getNavigation();
	FormAccess getFormAccess(INavForm navForm) throws Exception;
}
