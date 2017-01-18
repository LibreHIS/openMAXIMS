/*
 * Created on May 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain.lookups.impl;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.log4j.Logger;

import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.factory.FormFactory;
import ims.domain.lookups.LookupInstance;
import ims.domain.lookups.LookupMapping;
import ims.domain.lookups.LookupService;
import ims.framework.interfaces.IFormProvider;
import ims.framework.utils.Image;
import ims.framework.utils.Color;
import ims.framework.utils.ImagePath;
import ims.utils.Logging;
import ims.vo.LookupInstanceCollection;
import ims.vo.LookupInstVo;
import ims.vo.LookupTypeVo;

/**
 * Implementation of LookupService.
 * 
 * @author gcoghlan
 */
public class LookupServiceImpl extends ims.domain.impl.DomainImpl implements LookupService
{
	private static final long serialVersionUID = 1L;
	static final Logger	LOG	= Logging.getLogger(LookupServiceImpl.class);

	/**
	 * Returns false: this implementation does not use the LookupService - it <em>is</em> the implementation of the lookup service.
	 * 
	 * @see ims.domain.impl.DomainImpl#usesLookupService()
	 */
	protected final boolean usesLookupService()
	{
		return false;
	}

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

		if (collectionClass == null)
			collectionClass = LookupInstanceCollection.class;
		
		if (instanceClass == null) 
			instanceClass = LookupInstVo.class;

		DomainFactory factory = getDomainFactory();
		ims.domain.lookups.Lookup domainLookup = factory.getLookup(typeId);
		if (null == domainLookup)
		{
			throw new DomainRuntimeException("No lookup for id:" + typeId);
		}
		
		LookupInstanceCollection coll = createCollection(collectionClass);
		for (Iterator iter = domainLookup.getInstances().iterator(); iter.hasNext();)
		{
			ims.domain.lookups.LookupInstance element = (ims.domain.lookups.LookupInstance) iter.next();
			if (element.isActive() || !activeOnly)
			{
				LookupInstVo voInstance = createLookupInstance(element, instanceClass);
				insertInstance(coll, voInstance);
			}
		}
		/* Add the new lookup collection to the cache
		 * Sort it, and arrange hierarchy so doesn't need to be done at any later stage.
		 */		
		coll.sort();
		coll.getRoots();

		return coll;
	}


	public void refreshCache(int typeId)
	{
	}

	public LookupInstVo getLocalLookup(Class lookupClass, int typeId, String extSystem, String extCode)
	{
		if (extCode == null || extCode.equals(""))
			return null;

		DomainFactory factory = getDomainFactory();
		LookupInstance domInst = factory.getLookupInstance(typeId, extSystem, extCode);
		if (domInst == null)
		{
			// This will save us constantly re-querying for a reverse lookup that doesn't exist.
//			map.put(mapKey, "UNMAPPED");
			return null;
		}
		
		LookupInstVo voInstance = this.createLookupInstance(domInst, lookupClass);
		return voInstance;		
	}

	public String getRemoteLookup(int id, String extSystem)
	{
		DomainFactory factory = getDomainFactory();
		LookupInstance inst = factory.getLookupInstance(id);
		LookupMapping mapping = inst.getMapping(extSystem);
		if (mapping != null)
		{
			return mapping.getExtCode();
		}
		else
		{
			return null;
		}
	}

	public LookupInstVo getLookupInstance(Class lookupClass, int instId)
	{
		DomainFactory factory = getDomainFactory();
		LookupInstance inst = factory.getLookupInstance(instId);
		return createLookupInstance(inst, lookupClass);
	}

	public LookupInstVo getLookupInstance(Class lookupClass, int typeId, int instId)
	{
		return getLookupInstance(lookupClass, instId);
	}
	
	public LookupInstanceCollection getLookupCollection(LookupTypeVo type)
	{
		return getLookupCollection(type, true, true);
	}

	public LookupInstanceCollection getLookupCollection(LookupTypeVo type, boolean useCache, boolean activeOnly)
	{
		return getLookupCollection(type.getId(), null, null, useCache, activeOnly);
	}

	public LookupTypeVo getLookupType(int typeId)
	{
		DomainFactory factory = getDomainFactory();
		ims.domain.lookups.Lookup domainLookup = factory.getLookup(typeId);
		return domainLookup.extractLookupTypeVo();
	}

	public LookupInstVo getDefaultInstance(int formId, LookupTypeVo type)
	{
		return filterDefaultLookupInstance(getDefaultDomainInstance(formId, type.getId(), null));		
	}
	public LookupInstVo getDefaultInstance(Class lookupClass, int formId, int typeId)
	{
		return filterDefaultLookupInstance(getDefaultDomainInstance(formId, typeId, lookupClass));
	}	

	public boolean hasChildren(int typeId, LookupInstVo instance)
	{
		
		LookupInstanceCollection coll = null;
		
		coll = getLookupCollection(typeId, getLookupCollectionClass(typeId), getLookupClass(typeId), true, true);
		LookupInstVo[] roots = coll.getRoots();
		for (int i = 0; i < roots.length; i++)
		{
			if (instance != null && instance.equals(roots[i]))
			{
				return roots[i].getChildInstances().size() > 0;
			}
			else if (instance == null)
			{
				if (roots[i].getChildInstances().size() > 0)
					return true;				
			}
		}
		return false;
	}
	
	public boolean hasChildren(int typeId)
	{
		return hasChildren(typeId, null);
	}

	private LookupInstanceCollection createCollection(Class collectionClass)
	{
		try
		{
			LookupInstanceCollection collection = (LookupInstanceCollection)collectionClass.newInstance();
			return collection;
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}
	}

	private LookupInstVo createLookupInstance(LookupInstance instance, Class instanceClass)
	{
		if (instanceClass == null) instanceClass = LookupInstVo.class;
		
		Object voInstance = null;
		ims.domain.lookups.LookupInstance doParent = instance.getParent();
		Object voParent = null;
		if (doParent != null)
		{
			voParent = createLookupInstance(doParent, instanceClass);
		}
		Class[] params = {Integer.TYPE, String.class, Boolean.TYPE, instanceClass, Image.class, Color.class, Integer.TYPE};
		try
		{
			java.lang.reflect.Constructor ctor = instanceClass.getConstructor(params);

			Image img = null;
			if (instance.getImage() != null)
			{
				img = new ImagePath(instance.getImage().getImageId(), instance.getImage().getImagePath());
			}
			Color color = instance.getColor();
			if (color != null)
			{
				color.getValue();				
			}
			Object[] args = {Integer.valueOf(instance.getId()), instance.getText(), Boolean.valueOf(instance.isActive()), voParent, img, color, Integer.valueOf(instance.getOrder())};
			voInstance = ctor.newInstance(args);
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}
		return (LookupInstVo) voInstance;
	}

	private void insertInstance(LookupInstanceCollection collection, LookupInstVo instance)
	{
		Class[] params = {instance.getClass()};
		try
		{
			java.lang.reflect.Method addMethod = collection.getClass().getMethod("add", params);
			addMethod.invoke(collection, new Object[]{instance});
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}
	}

	private LookupInstVo getDefaultDomainInstance(int formId, int typeId, Class lookupClass)
	{
		FormFactory formFactory = new FormFactory(this.getDomainFactory().getDomainSession());
		if (!formFactory.hasFormProvider())
			throw new DomainRuntimeException("No Form Provider available. Unable to get default instance for Form.");
		
		IFormProvider provider = formFactory.getFormProvider();
		return provider.getDefaultLookupInstance(formId, typeId, lookupClass);
	}

	private Class getLookupClass(int typeId)
	{
		try
		{
			Class c = Class.forName("ims.vo.lookups.ClassHelper");
			Method m = c.getMethod("getLookupClass", new Class[] {Integer.TYPE});
			return (Class)m.invoke(null, new Object[] {Integer.valueOf(typeId)});
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}
	}
	private Class getLookupCollectionClass(int typeId)
	{
		try
		{
			Class c = Class.forName("ims.vo.lookups.ClassHelper");
			Method m = c.getMethod("getLookupCollectionClass", new Class[] {Integer.TYPE});
			return (Class)m.invoke(null, new Object[] {Integer.valueOf(typeId)});
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}
	}
	

	public int countLookupInstances(Class collectionClass, Class lookupClass, int type, boolean activeOnly)
	{
		LookupInstanceCollection cachedCollection = getLookupCollection(type, collectionClass, lookupClass, true, activeOnly);
		if (cachedCollection == null)
			return 0;
		return cachedCollection.size();
	}
	
	public int countLookupInstances(int type, boolean activeOnly)
	{
		return countLookupInstances(LookupInstanceCollection.class, LookupInstVo.class, type, activeOnly);	
	}
	public int countLookupInstances(int type)
	{
		return countLookupInstances(LookupInstanceCollection.class, LookupInstVo.class, type, false);
	}

	public synchronized void clearAllCaches()
	{
	}
	
	/*
	 * This method filters all inactive instances 
	 * WDEV-3700
	 */
	private LookupInstVo filterDefaultLookupInstance(LookupInstVo instance)
	{		
		if(instance == null || !instance.isActive())
			return null;
		return instance;
	}
}
