/*
 * Created on 10-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.lookups.LookupService;
import ims.dto.client.Lkup;
import ims.dto.client.Lkup.LkupRecord;
import ims.framework.FormName;
import ims.vo.LookupInstanceCollection;
import ims.vo.LookupInstVo;
import ims.vo.LookupTypeVo;

/**
 * @author jmacenri
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DTOLookupServiceImpl extends DTODomainImplementation implements LookupService
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see ims.domain.lookups.LookupService#getLookupCollection(int, java.lang.Class, java.lang.Class)
	 */
	public LookupInstanceCollection getLookupCollection(int typeId, Class collectionClass, Class instanceClass)
	{
		return getLookupCollection(typeId, collectionClass, instanceClass, true, true);
	}

	/**
	 * @see ims.domain.lookups.LookupService#getLookupCollection(int, java.lang.Class, java.lang.Class, boolean)
	 */
	public LookupInstanceCollection getLookupCollection(int typeId, Class collectionClass, Class instanceClass, boolean useCache, boolean activeOnly)
	{
		Result result = null;

		Lkup lkup = (Lkup) getDTOInstance(Lkup.class);
		lkup.Filter.clear();
		lkup.Filter.Lkup_typ = "" + typeId;
		lkup.Connection.setListSize(20);
		result = lkup.list(1000);
		if (result != null)
		{
			throw new DomainRuntimeException(result.getMessage());

		}
		LookupInstanceCollection collection = createCollection(collectionClass);
		LkupRecord rec;
		for (int i = 0; i < lkup.DataCollection.count(); i++)
		{
			rec = lkup.DataCollection.get(i);
			LookupInstVo voInstance = createLookupInstance(rec, instanceClass);
			if (voInstance != null && voInstance.isActive())
				insertInstance(collection, voInstance);
		}
		return collection;
	}

	private LookupInstanceCollection createCollection(Class collectionClass)
	{
		try
		{
			LookupInstanceCollection collection = (LookupInstanceCollection)collectionClass.newInstance();
			return collection;
		}
		catch (Throwable e)
		{
			throw new DomainRuntimeException(e);
		}
	}

	private LookupInstVo createLookupInstance(LkupRecord instance, Class instanceClass)
	{
		
		if (instance.Lkup_id == null || instance.Lkup_id.equals(""))
			return null;
		
		Object voInstance = null;
		Class[] params = {Integer.TYPE, String.class, Boolean.TYPE};
		try
		{
			java.lang.reflect.Constructor ctor = instanceClass.getConstructor(params);
			boolean active = true;
			if (instance.Lkup_stat!= null && (instance.Lkup_stat.equals("N") || instance.Lkup_stat.equals("I")|| instance.Lkup_stat.equals("0")))
			{
				active = false;
			}
			Object[] args = {new Integer(instance.Lkup_id), instance.Lkup_nm, new Boolean(active)};
			voInstance = ctor.newInstance(args);
		}
		catch (Throwable e)
		{
			throw new DomainRuntimeException(e);
		}
		return (LookupInstVo) voInstance;
	}

	private void insertInstance(Object collection, Object instance)
	{
		Class[] params = {instance.getClass()};
		try
		{
			java.lang.reflect.Method addMethod = collection.getClass().getMethod("add", params);
			addMethod.invoke(collection, new Object[]{instance});
		}
		catch (Throwable e)
		{
			throw new DomainRuntimeException(e);
		}
	}

	/**
	 * Returns false: this implementation does not use the LookupService - it <em>is</em> the implementation of the lookup service.
	 * 
	 * @see ims.domain.impl.DomainImpl#usesLookupService()
	 */
	protected final boolean usesLookupService()
	{
		return false;
	}

	public void refreshCache(int typeId)
	{
		return;
	}

	public LookupInstVo getLocalLookup(Class lookupClass, int typeId, String extSystem, String extCode)
	{
		Result result = null;

		Lkup lkup = (Lkup) getDTOInstance(Lkup.class);
		lkup.Filter.clear();
		lkup.Filter.Lkup_typ = "" + typeId;
		lkup.Filter.Lkup_srcc = extCode;
		result = lkup.get();
		if (result != null)
		{
			if (result.getId() == -2)
				return null;
			throw new DomainRuntimeException(result.getMessage());
		}
		if (lkup.DataCollection.count() == 0)
			return null;
		LkupRecord rec = lkup.DataCollection.get(0);

		return this.createLookupInstance(rec, lookupClass);
	}

	public String getRemoteLookup(int typeId, int instId, String extSystem)
	{
		if (extSystem != null)
		{
			//Simply here to avoid warnings
		}
			
		Result result = null;

		Lkup lkup = (Lkup) getDTOInstance(Lkup.class);
		lkup.Filter.clear();
		lkup.Filter.Lkup_typ = "" + typeId;
		lkup.Filter.Lkup_id = "" + instId;
		result = lkup.get();
		if (result != null)
		{
			if (result.getId() == -2)
				return null;
			throw new DomainRuntimeException(result.getMessage());
		}
		if (lkup.DataCollection.count() == 0)
			return null;
		LkupRecord rec = lkup.DataCollection.get(0);
		return rec.Lkup_srcc;
	}

	public LookupInstVo getLookupInstance(Class lookupClass, int typeId, int instId, String text)
	{
		if (typeId > 0)
		{
			//Simply here to avoid warnings
		}
		
		Object voInstance = null;
		Class[] params = {Integer.TYPE, String.class, Boolean.TYPE};
		try
		{
			java.lang.reflect.Constructor ctor = lookupClass.getConstructor(params);
			boolean active = true;
			Object[] args = {new Integer(instId), text, new Boolean(active)};
			voInstance = ctor.newInstance(args);
		}
		catch (Throwable e)
		{
			throw new DomainRuntimeException(e);
		}
		return (LookupInstVo) voInstance;
	}

	public LookupInstVo getLookupInstance(Class lookupClass, int typeId, int instId)
	{
		Result result = null;

		Lkup lkup = (Lkup) getDTOInstance(Lkup.class);
		lkup.Filter.clear();
		lkup.Filter.Lkup_typ = "" + typeId;
		lkup.Filter.Lkup_id = "" + instId;
		result = lkup.get();
		if (result != null)
		{
			if (result.getId() == -2)
				return null;
			throw new DomainRuntimeException(result.getMessage());
		}
		if (lkup.DataCollection.count() == 0)
			return null;
		LkupRecord rec = lkup.DataCollection.get(0);
		return this.createLookupInstance(rec, lookupClass);
	}

	public String getRemoteLookup(int id, String extSystem)
	{
		return null;
	}

	public LookupInstanceCollection getLookupCollection(LookupTypeVo type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public LookupInstanceCollection getLookupCollection(LookupTypeVo type, boolean useCache, boolean activeOnly)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public LookupTypeVo getLookupType(int typeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public LookupInstVo getDefaultInstance(FormName form, LookupTypeVo type)
	{
		// TODO Auto-generated method stub
		if (form != null && type != null) {}
		
		return null;
	}

	public LookupInstVo getDefaultInstance(Class lookupClass, FormName form, int typeId)
	{
		// TODO Auto-generated method stub
		if (lookupClass != null && form != null && typeId > 0) {}
		return null;
	}

	public void clearDefaultInstanceCache(FormName form, int typeId)
	{
		// TODO Auto-generated method stub
		if (form != null && typeId > 0) {}
	}

	public LookupInstVo getLookupInstance(Class lookupClass, int instId)
	{
		// TODO Auto-generated method stub
		if (lookupClass != null && instId > 0) {}
		return null;
	}

	public boolean hasChildren(int typeId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasChildren(int typeId, LookupInstVo instance)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public int countLookupInstances(int type)
	{
		return this.countLookupInstances(type, false);
	}

	public int countLookupInstances(int type, boolean activeOnly)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void clearAllCaches()
	{
	}

	public LookupInstVo getDefaultInstance(int formId, LookupTypeVo type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public LookupInstVo getDefaultInstance(Class lookupClass, int formId, int typeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void clearDefaultInstanceCache(@SuppressWarnings("unused") int formId, @SuppressWarnings("unused") int typeId)
	{
		// TODO Auto-generated method stub
		
	}

	public int countLookupInstances(Class collectionClass, Class instanceClass, int type, boolean activeOnly)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
