// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.secondopiniondialog;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.RefMan.domain.SecondOpinionDialog.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.RefMan.domain.SecondOpinionDialog domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	protected final void oncmbReasonValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.cmbReason().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.RefMan.vo.lookups.SecondOpinionCategory existingInstance = (ims.RefMan.vo.lookups.SecondOpinionCategory)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbReasonLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.RefMan.vo.lookups.SecondOpinionCategory)
		{
			ims.RefMan.vo.lookups.SecondOpinionCategory instance = (ims.RefMan.vo.lookups.SecondOpinionCategory)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbReasonLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.RefMan.vo.lookups.SecondOpinionCategory existingInstance = (ims.RefMan.vo.lookups.SecondOpinionCategory)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.cmbReason().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbReasonLookup()
	{
		this.form.cmbReason().clear();
		ims.RefMan.vo.lookups.SecondOpinionCategoryCollection lookupCollection = ims.RefMan.vo.lookups.LookupHelper.getSecondOpinionCategory(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.cmbReason().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbReasonLookupValue(int id)
	{
		ims.RefMan.vo.lookups.SecondOpinionCategory instance = ims.RefMan.vo.lookups.LookupHelper.getSecondOpinionCategoryInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.cmbReason().setValue(instance);
	}
	protected final void defaultcmbReasonLookupValue()
	{
		this.form.cmbReason().setValue((ims.RefMan.vo.lookups.SecondOpinionCategory)domain.getLookupService().getDefaultInstance(ims.RefMan.vo.lookups.SecondOpinionCategory.class, engine.getFormName().getID(), ims.RefMan.vo.lookups.SecondOpinionCategory.TYPE_ID));
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.RefMan.domain.SecondOpinionDialog domain;
}