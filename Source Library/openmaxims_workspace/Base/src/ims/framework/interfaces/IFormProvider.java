package ims.framework.interfaces;

import ims.vo.LookupInstVo;

public interface IFormProvider
{
	public IAppForm[] getAllForms();
	public IAppForm getForm(int formId);
	public LookupInstVo getDefaultLookupInstance(int formId, int lookupTypeId, Class lookupClass);
}
