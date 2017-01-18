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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import ims.framework.enumerations.SortOrder;

/**
 * Linked to RefMan.PatientElectiveList business object (ID: 1014100020).
 */
public class PatientElectiveListBedInfoVoCollection extends ims.vo.ValueObjectCollection implements ims.vo.ImsCloneable, Iterable<PatientElectiveListBedInfoVo>, ims.vo.interfaces.IPendingAdmissionCollection
{
	private static final long serialVersionUID = 1L;

	private ArrayList<PatientElectiveListBedInfoVo> col = new ArrayList<PatientElectiveListBedInfoVo>();
	public String getBoClassName()
	{
		return "ims.RefMan.domain.objects.PatientElectiveList";
	}
	public boolean add(PatientElectiveListBedInfoVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			return this.col.add(value);
		}
		return false;
	}
	public boolean add(int index, PatientElectiveListBedInfoVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			this.col.add(index, value);
			return true;
		}
		return false;
	}
	public void clear()
	{
		this.col.clear();
	}
	public void remove(int index)
	{
		this.col.remove(index);
	}
	public int size()
	{
		return this.col.size();
	}
	public int indexOf(PatientElectiveListBedInfoVo instance)
	{
		return col.indexOf(instance);
	}
	public PatientElectiveListBedInfoVo get(int index)
	{
		return this.col.get(index);
	}
	public boolean set(int index, PatientElectiveListBedInfoVo value)
	{
		if(value == null)
			return false;
		this.col.set(index, value);
		return true;
	}
	public void remove(PatientElectiveListBedInfoVo instance)
	{
		if(instance != null)
		{
			int index = indexOf(instance);
			if(index >= 0)
				remove(index);
		}
	}
	public boolean contains(PatientElectiveListBedInfoVo instance)
	{
		return indexOf(instance) >= 0;
	}
	public Object clone()
	{
		PatientElectiveListBedInfoVoCollection clone = new PatientElectiveListBedInfoVoCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add((PatientElectiveListBedInfoVo)this.col.get(x).clone());
			else
				clone.col.add(null);
		}
		
		return clone;
	}
	public boolean isValidated()
	{
		for(int x = 0; x < col.size(); x++)
			if(!this.col.get(x).isValidated())
				return false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(col.size() == 0)
			return null;
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		for(int x = 0; x < col.size(); x++)
		{
			String[] listOfOtherErrors = this.col.get(x).validate();
			if(listOfOtherErrors != null)
			{
				for(int y = 0; y < listOfOtherErrors.length; y++)
				{
					listOfErrors.add(listOfOtherErrors[y]);
				}
			}
		}
		
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
			return null;
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		return result;
	}
	public PatientElectiveListBedInfoVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public PatientElectiveListBedInfoVoCollection sort(boolean caseInsensitive)
	{
		return sort(SortOrder.ASCENDING, caseInsensitive);
	}
	public PatientElectiveListBedInfoVoCollection sort(SortOrder order)
	{
		return sort(new PatientElectiveListBedInfoVoComparator(order));
	}
	public PatientElectiveListBedInfoVoCollection sort(SortOrder order, boolean caseInsensitive)
	{
		return sort(new PatientElectiveListBedInfoVoComparator(order, caseInsensitive));
	}
	@SuppressWarnings("unchecked")
	public PatientElectiveListBedInfoVoCollection sort(Comparator comparator)
	{
		Collections.sort(col, comparator);
		return this;
	}
	public ims.RefMan.vo.PatientElectiveListRefVoCollection toRefVoCollection()
	{
		ims.RefMan.vo.PatientElectiveListRefVoCollection result = new ims.RefMan.vo.PatientElectiveListRefVoCollection();
		for(int x = 0; x < this.col.size(); x++)
		{
			result.add(this.col.get(x));
		}
		return result;
	}
	public PatientElectiveListBedInfoVo[] toArray()
	{
		PatientElectiveListBedInfoVo[] arr = new PatientElectiveListBedInfoVo[col.size()];
		col.toArray(arr);
		return arr;
	}
	public ims.vo.interfaces.IPendingAdmission[] toIPendingAdmissionArray()
	{
		ims.vo.interfaces.IPendingAdmission[] arr = new ims.vo.interfaces.IPendingAdmission[col.size()];
		col.toArray(arr);
		return arr;
	}
	public Iterator<PatientElectiveListBedInfoVo> iterator()
	{
		return col.iterator();
	}
	@Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
	private class PatientElectiveListBedInfoVoComparator implements Comparator
	{
		private int direction = 1;
		private boolean caseInsensitive = true;
		public PatientElectiveListBedInfoVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public PatientElectiveListBedInfoVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public PatientElectiveListBedInfoVoComparator(SortOrder order, boolean caseInsensitive)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
			this.caseInsensitive = caseInsensitive;
		}
		public int compare(Object obj1, Object obj2)
		{
			PatientElectiveListBedInfoVo voObj1 = (PatientElectiveListBedInfoVo)obj1;
			PatientElectiveListBedInfoVo voObj2 = (PatientElectiveListBedInfoVo)obj2;
			return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
		}
		public boolean equals(Object obj)
		{
			return false;
		}
	}
	public ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean[] getBeanCollection()
	{
		return getBeanCollectionArray();
	}
	public ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean[] getBeanCollectionArray()
	{
		ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean[] result = new ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean[col.size()];
		for(int i = 0; i < col.size(); i++)
		{
			PatientElectiveListBedInfoVo vo = ((PatientElectiveListBedInfoVo)col.get(i));
			result[i] = (ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean)vo.getBean();
		}
		return result;
	}
	public static PatientElectiveListBedInfoVoCollection buildFromBeanCollection(java.util.Collection beans)
	{
		PatientElectiveListBedInfoVoCollection coll = new PatientElectiveListBedInfoVoCollection();
		if(beans == null)
			return coll;
		java.util.Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			coll.add(((ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean)iter.next()).buildVo());
		}
		return coll;
	}
	public static PatientElectiveListBedInfoVoCollection buildFromBeanCollection(ims.RefMan.vo.beans.PatientElectiveListBedInfoVoBean[] beans)
	{
		PatientElectiveListBedInfoVoCollection coll = new PatientElectiveListBedInfoVoCollection();
		if(beans == null)
			return coll;
		for(int x = 0; x < beans.length; x++)
		{
			coll.add(beans[x].buildVo());
		}
		return coll;
	}
	/**
	* IPendingAdmissionCollection function
	*/
	public Boolean add(ims.vo.interfaces.IPendingAdmission pendingAdmission)
	{
		if (pendingAdmission instanceof PatientElectiveListBedInfoVo)
			return add((PatientElectiveListBedInfoVo) pendingAdmission);
		
		return false;
	}
		
	public ims.vo.interfaces.IPendingAdmission getIPendingAdmission(Integer i)
	{
		return get(i);
	}
}
