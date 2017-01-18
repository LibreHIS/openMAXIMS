/*
 * Created on May 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain.lookups;

import ims.vo.LookupInstanceCollection;
import ims.vo.LookupInstVo;
import ims.vo.LookupTypeVo;

/**
 * @author gcoghlan
 *
 */
public interface LookupService {
	
	/**
	 * Get the ValueObject LookupCollection initialized with the instances.
	 * Retrieves the ims.domain.lookups.Lookup object for the provide typeId.
	 * Then transforms this into instances of the Class instanceClass and inserts these
	 * instances into the collection of type collectionClass.
	 * This method uses a global cache to check if a given lookup type has already been
	 * transformed into a collection - if so then that previous collection is returned.
	 * @param typeId
	 * @param collectionClass
	 * @param instanceClass
	 */
	public LookupInstanceCollection getLookupCollection(int typeId, Class collectionClass, Class instanceClass);
	
	/**
	 * If the useCache parameter is true, then a global cache is tested to see if the Collection type
	 * requested has already been created.
	 * Otherwise, retrieves the ims.domain.lookups.Lookup object for the provide typeId.
	 * Then transforms this into instances of the Class instanceClass and inserts these
	 * instances into the collection of type collectionClass.
	 * The resulting collection object is inserted into the global cache before being returned.
	 * @param typeId
	 * @param collectionClass
	 * @param instanceClass
	 * @param useCache
	 */
	public LookupInstanceCollection getLookupCollection(int typeId, Class collectionClass, Class instanceClass, boolean useCache, boolean activeOnly);

	
	/**
	 *	For returning the collection of lookup instances for a user defined lookup type 
	 * @param typeId
	 * @return
	 */
	public LookupInstanceCollection getLookupCollection(LookupTypeVo type);
	public LookupInstanceCollection getLookupCollection(LookupTypeVo type, boolean useCache, boolean activeOnly);
	
	/**
	 * Return a count of instances for the given lookup type
	 * @param typeId
	 * @return Integer number of instances
	 */
	public int countLookupInstances(int type);
	public int countLookupInstances(int type, boolean activeOnly);
	public int countLookupInstances(Class collectionClass, Class instanceClass, int type, boolean activeOnly);

	/**
	 * Will check if the cache contains the lookup collection for the lookup type with id = typeId.
	 * @param typeId
	 * @return
	 */
	public void refreshCache(int typeId);
	

	/**
	 * Returns a local VO Lookup instance corresponding to 
	 * this external code for the supplied external System
	 * @param VoLookup Class
	 * @param typeId
	 * @param extSystem
	 * @param extCode
	 * @return
	 */
	public LookupInstVo getLocalLookup(Class lookupClass, int typeId, String extSystem, String extCode);

	/**
	 * Returns the remote Code for this local LookupInstance 
	 * @param instId
	 * @param extSystem
	 * @return
	 */
	public String getRemoteLookup(int id, String extSystem);
	
	public LookupInstVo getLookupInstance(Class lookupClass, int instId);
	public LookupInstVo getLookupInstance(Class lookupClass, int typeId, int instId);
	
	public LookupTypeVo getLookupType(int typeId);
	
	public LookupInstVo getDefaultInstance(int formId, LookupTypeVo type);
	
	public LookupInstVo getDefaultInstance(Class lookupClass, int formId, int typeId);

//MN	public void clearDefaultInstanceCache(int formId, int typeId);
	
	public void clearAllCaches();
	
	public boolean hasChildren(int typeId);
	public boolean hasChildren(int typeId, LookupInstVo instance);	
}
