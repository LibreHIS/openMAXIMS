//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
/*
 * This code was generated
 * Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
 * IMS Development Environment (version 1.80 build 5589.25814)
 * WARNING: DO NOT MODIFY the content of this file
 * Generated on 12/10/2015, 13:24
 *
 */
package ims.RefMan.vo.domain;

import ims.vo.domain.DomainObjectMap;
import java.util.HashMap;

import org.hibernate.proxy.HibernateProxy;

/**
 * @author Florin Blindu
 */
public class SuspensionDetailsForPatientElectiveListVoAssembler
{
  	/**
	 * Copy one ValueObject to another
	 * @param valueObjectDest to be updated
	 * @param valueObjectSrc to copy values from
	 */
	 public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo copy(ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObjectDest, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObjectSrc) 
	 {
	 	if (null == valueObjectSrc) 
		{
			return valueObjectSrc;
		}
		valueObjectDest.setID_SuspensionDetailsForPatientElectiveList(valueObjectSrc.getID_SuspensionDetailsForPatientElectiveList());
	    valueObjectDest.setIsRIE(valueObjectSrc.getIsRIE());
		// StartDate
		valueObjectDest.setStartDate(valueObjectSrc.getStartDate());
		// EndDate
		valueObjectDest.setEndDate(valueObjectSrc.getEndDate());
		// Initator
		valueObjectDest.setInitator(valueObjectSrc.getInitator());
		// Comments
		valueObjectDest.setComments(valueObjectSrc.getComments());
		// SuspensionReason
		valueObjectDest.setSuspensionReason(valueObjectSrc.getSuspensionReason());
	 	return valueObjectDest;
	 }

 
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * This is a convenience method only.
	 * It is intended to be used when one called to an Assembler is made.
 	 * If more than one call to an Assembler is made then #createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(DomainObjectMap, Set) should be used.
	 * @param domainObjectSet - Set of ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList objects.
	 */
	public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(java.util.Set domainObjectSet)	
	{
		return createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(new DomainObjectMap(), domainObjectSet);
	}
	
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectSet - Set of ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList objects.
	 */
	public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(DomainObjectMap map, java.util.Set domainObjectSet)	
	{
		ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voList = new ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection();
		if ( null == domainObjectSet ) 
		{
			return voList;
		}
		int rieCount=0;
		int activeCount=0;
		java.util.Iterator iterator = domainObjectSet.iterator();
		while( iterator.hasNext() ) 
		{
			ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject = (ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList) iterator.next();
			ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo vo = create(map, domainObject);
			
			if (vo != null)
				voList.add(vo);
				
			if (domainObject != null)
			{				
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param domainObjectList - List of ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList objects.
	 */
	public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(java.util.List domainObjectList) 
	{
		return createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(new DomainObjectMap(), domainObjectList);
	}
	
	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectList - List of ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList objects.
	 */
	public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection createSuspensionDetailsForPatientElectiveListVoCollectionFromSuspensionDetailsForPatientElectiveList(DomainObjectMap map, java.util.List domainObjectList) 
	{
		ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voList = new ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection();
		if ( null == domainObjectList ) 
		{
			return voList;
		}		
		int rieCount=0;
		int activeCount=0;
		for (int i = 0; i < domainObjectList.size(); i++)
		{
			ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject = (ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList) domainObjectList.get(i);
			ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo vo = create(map, domainObject);

			if (vo != null)
				voList.add(vo);
			
			if (domainObject != null)
			{
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList set from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.Set extractSuspensionDetailsForPatientElectiveListSet(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voCollection) 
	 {
	 	return extractSuspensionDetailsForPatientElectiveListSet(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.Set extractSuspensionDetailsForPatientElectiveListSet(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voCollection, java.util.Set domainObjectSet, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectSet == null)
		{
			domainObjectSet = new java.util.HashSet();			
		}
		java.util.Set newSet = new java.util.HashSet();
		for(int i=0; i<size; i++) 
		{
			ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo vo = voCollection.get(i);
			ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject = SuspensionDetailsForPatientElectiveListVoAssembler.extractSuspensionDetailsForPatientElectiveList(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}
			
			//Trying to avoid the hibernate collection being marked as dirty via its public interface methods. (like add)
			if (!domainObjectSet.contains(domainObject)) domainObjectSet.add(domainObject);
			newSet.add(domainObject);			
		}
		java.util.Set removedSet = new java.util.HashSet();
		java.util.Iterator iter = domainObjectSet.iterator();
		//Find out which objects need to be removed
		while (iter.hasNext())
		{
			ims.domain.DomainObject o = (ims.domain.DomainObject)iter.next();			
			if ((o == null || o.getIsRIE() == null || !o.getIsRIE().booleanValue()) && !newSet.contains(o))
			{
				removedSet.add(o);
			}
		}
		iter = removedSet.iterator();
		//Remove the unwanted objects
		while (iter.hasNext())
		{
			domainObjectSet.remove(iter.next());
		}
		return domainObjectSet;	 
	 }


	/**
	 * Create the ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList list from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.List extractSuspensionDetailsForPatientElectiveListList(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voCollection) 
	 {
	 	return extractSuspensionDetailsForPatientElectiveListList(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.List extractSuspensionDetailsForPatientElectiveListList(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection voCollection, java.util.List domainObjectList, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectList == null)
		{
			domainObjectList = new java.util.ArrayList();			
		}
		for(int i=0; i<size; i++) 
		{
			ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo vo = voCollection.get(i);
			ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject = SuspensionDetailsForPatientElectiveListVoAssembler.extractSuspensionDetailsForPatientElectiveList(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}

			int domIdx = domainObjectList.indexOf(domainObject);
			if (domIdx == -1)
			{
				domainObjectList.add(i, domainObject);
			}
			else if (i != domIdx && i < domainObjectList.size())
			{
				Object tmp = domainObjectList.get(i);
				domainObjectList.set(i, domainObjectList.get(domIdx));
				domainObjectList.set(domIdx, tmp);
			}
		}
		
		//Remove all ones in domList where index > voCollection.size() as these should
		//now represent the ones removed from the VO collection. No longer referenced.
		int i1=domainObjectList.size();
		while (i1 > size)
		{
			domainObjectList.remove(i1-1);
			i1=domainObjectList.size();
		}
		return domainObjectList;	 
	 }

 

	/**
	 * Create the ValueObject from the ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList object.
	 * @param domainObject ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList
	 */
	 public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo create(ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return null;
		}
		DomainObjectMap map = new DomainObjectMap();
		return create(map, domainObject);
	 }
	 
	 /**
	  * Create the ValueObject from the ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList object.
	  * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	  * @param domainObject
	  */
	  public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo create(DomainObjectMap map, ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject) 
	  {
	  		if (null == domainObject) 
	  		{
				return null;
			}
			// check if the domainObject already has a valueObject created for it
			ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObject = (ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo) map.getValueObject(domainObject, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo.class);
			if ( null == valueObject ) 
			{
				valueObject = new ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo(domainObject.getId(), domainObject.getVersion());
				map.addValueObject(domainObject, valueObject);

				valueObject = insert(map, valueObject, domainObject);
				
			}
	 		return valueObject;
	  }

	/**
	 * Update the ValueObject with the Domain Object.
	 * @param valueObject to be updated
	 * @param domainObject ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList
	 */
	 public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo insert(ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObject, ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
		DomainObjectMap map = new DomainObjectMap();
		return insert(map, valueObject, domainObject);
	 }
	 
	/**
	 * Update the ValueObject with the Domain Object.
	 * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	 * @param valueObject to be updated
	 * @param domainObject ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList
	 */
	 public static ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo insert(DomainObjectMap map, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObject, ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
	 	if (null == map) 
	 	{
			map = new DomainObjectMap();
		}

		valueObject.setID_SuspensionDetailsForPatientElectiveList(domainObject.getId());
		valueObject.setIsRIE(domainObject.getIsRIE());
		
		// If this is a recordedInError record, and the domainObject
		// value isIncludeRecord has not been set, then we return null and
		// not the value object
		if (valueObject.getIsRIE() != null && valueObject.getIsRIE().booleanValue() == true && !domainObject.isIncludeRecord())
			return null;
			
		// If this is not a recordedInError record, and the domainObject
		// value isIncludeRecord has been set, then we return null and
		// not the value object
		if ((valueObject.getIsRIE() == null || valueObject.getIsRIE().booleanValue() == false) && domainObject.isIncludeRecord())
			return null;
			
		// StartDate
		java.util.Date StartDate = domainObject.getStartDate();
		if ( null != StartDate ) 
		{
			valueObject.setStartDate(new ims.framework.utils.Date(StartDate) );
		}
		// EndDate
		java.util.Date EndDate = domainObject.getEndDate();
		if ( null != EndDate ) 
		{
			valueObject.setEndDate(new ims.framework.utils.Date(EndDate) );
		}
		// Initator
		ims.domain.lookups.LookupInstance instance3 = domainObject.getInitator();
		if ( null != instance3 ) {
			ims.framework.utils.ImagePath img = null;
			ims.framework.utils.Color color = null;		
			img = null;
			if (instance3.getImage() != null) 
			{
				img = new ims.framework.utils.ImagePath(instance3.getImage().getImageId(), instance3.getImage().getImagePath());
			}
			color = instance3.getColor();
			if (color != null) 
				color.getValue();

			ims.core.vo.lookups.Initator voLookup3 = new ims.core.vo.lookups.Initator(instance3.getId(),instance3.getText(), instance3.isActive(), null, img, color);
			ims.core.vo.lookups.Initator parentVoLookup3 = voLookup3;
			ims.domain.lookups.LookupInstance parent3 = instance3.getParent();
			while (parent3 != null)
			{
				if (parent3.getImage() != null) 
				{
					img = new ims.framework.utils.ImagePath(parent3.getImage().getImageId(), parent3.getImage().getImagePath() );
				}
				else 
				{
					img = null;
				}
				color = parent3.getColor();
    			if (color != null) 
    				color.getValue();
								parentVoLookup3.setParent(new ims.core.vo.lookups.Initator(parent3.getId(),parent3.getText(), parent3.isActive(), null, img, color));
				parentVoLookup3 = parentVoLookup3.getParent();
								parent3 = parent3.getParent();
			}			
			valueObject.setInitator(voLookup3);
		}
				// Comments
		valueObject.setComments(domainObject.getComments());
		// SuspensionReason
		ims.domain.lookups.LookupInstance instance5 = domainObject.getSuspensionReason();
		if ( null != instance5 ) {
			ims.framework.utils.ImagePath img = null;
			ims.framework.utils.Color color = null;		
			img = null;
			if (instance5.getImage() != null) 
			{
				img = new ims.framework.utils.ImagePath(instance5.getImage().getImageId(), instance5.getImage().getImagePath());
			}
			color = instance5.getColor();
			if (color != null) 
				color.getValue();

			ims.RefMan.vo.lookups.SuspensionReason voLookup5 = new ims.RefMan.vo.lookups.SuspensionReason(instance5.getId(),instance5.getText(), instance5.isActive(), null, img, color);
			ims.RefMan.vo.lookups.SuspensionReason parentVoLookup5 = voLookup5;
			ims.domain.lookups.LookupInstance parent5 = instance5.getParent();
			while (parent5 != null)
			{
				if (parent5.getImage() != null) 
				{
					img = new ims.framework.utils.ImagePath(parent5.getImage().getImageId(), parent5.getImage().getImagePath() );
				}
				else 
				{
					img = null;
				}
				color = parent5.getColor();
    			if (color != null) 
    				color.getValue();
								parentVoLookup5.setParent(new ims.RefMan.vo.lookups.SuspensionReason(parent5.getId(),parent5.getText(), parent5.isActive(), null, img, color));
				parentVoLookup5 = parentVoLookup5.getParent();
								parent5 = parent5.getParent();
			}			
			valueObject.setSuspensionReason(voLookup5);
		}
		 		return valueObject;
	 }


	/**
	 * Create the domain object from the value object.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param valueObject - extract the domain object fields from this.
	 */
	public static ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList extractSuspensionDetailsForPatientElectiveList(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObject) 
	{
		return 	extractSuspensionDetailsForPatientElectiveList(domainFactory, valueObject, new HashMap());
	}

	public static ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList extractSuspensionDetailsForPatientElectiveList(ims.domain.ILightweightDomainFactory domainFactory, ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo valueObject, HashMap domMap) 
	{
		if (null == valueObject) 
		{
			return null;
		}
		Integer id = valueObject.getID_SuspensionDetailsForPatientElectiveList();
		ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList domainObject = null;
		if ( null == id) 
		{
			if (domMap.get(valueObject) != null)
			{
				return (ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList)domMap.get(valueObject);
			}
			// ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVo ID_SuspensionDetailsForPatientElectiveList field is unknown
			domainObject = new ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList();
			domMap.put(valueObject, domainObject);
		}
		else 
		{
			String key = (valueObject.getClass().getName() + "__" + valueObject.getID_SuspensionDetailsForPatientElectiveList());
			if (domMap.get(key) != null)
			{
				return (ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList)domMap.get(key);
			}
			domainObject = (ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList) domainFactory.getDomainObject(ims.RefMan.domain.objects.SuspensionDetailsForPatientElectiveList.class, id );
			
			//TODO: Not sure how this should be handled. Effectively it must be a staleobject exception, but maybe should be handled as that further up.
			if (domainObject == null) 
				return null;

			domMap.put(key, domainObject);
		}
		domainObject.setVersion(valueObject.getVersion_SuspensionDetailsForPatientElectiveList());

		java.util.Date value1 = null;
		ims.framework.utils.Date date1 = valueObject.getStartDate();		
		if ( date1 != null ) 
		{
			value1 = date1.getDate();
		}
		domainObject.setStartDate(value1);
		java.util.Date value2 = null;
		ims.framework.utils.Date date2 = valueObject.getEndDate();		
		if ( date2 != null ) 
		{
			value2 = date2.getDate();
		}
		domainObject.setEndDate(value2);
		// create LookupInstance from vo LookupType
		ims.domain.lookups.LookupInstance value3 = null;
		if ( null != valueObject.getInitator() ) 
		{
			value3 =
				domainFactory.getLookupInstance(valueObject.getInitator().getID());
		}
		domainObject.setInitator(value3);
		//This is to overcome a bug in both Sybase and Oracle which prevents them from storing an empty string correctly
		//Sybase stores it as a single space, Oracle stores it as NULL. This fix will make them consistent at least.
		if (valueObject.getComments() != null && valueObject.getComments().equals(""))
		{
			valueObject.setComments(null);
		}
		domainObject.setComments(valueObject.getComments());
		// create LookupInstance from vo LookupType
		ims.domain.lookups.LookupInstance value5 = null;
		if ( null != valueObject.getSuspensionReason() ) 
		{
			value5 =
				domainFactory.getLookupInstance(valueObject.getSuspensionReason().getID());
		}
		domainObject.setSuspensionReason(value5);

		return domainObject;
	}

}
