//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
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
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.emergency.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import ims.framework.enumerations.SortOrder;

/**
 * Linked to emergency.HistoryOfPatientMeds business object (ID: 1086100027).
 */
public class HistoryOfPatientMedsVoCollection extends ims.vo.ValueObjectCollection implements ims.vo.ImsCloneable, Iterable<HistoryOfPatientMedsVo>
{
	private static final long serialVersionUID = 1L;

	private ArrayList<HistoryOfPatientMedsVo> col = new ArrayList<HistoryOfPatientMedsVo>();
	public String getBoClassName()
	{
		return "ims.emergency.domain.objects.HistoryOfPatientMeds";
	}
	public boolean add(HistoryOfPatientMedsVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			return this.col.add(value);
		}
		return false;
	}
	public boolean add(int index, HistoryOfPatientMedsVo value)
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
	public int indexOf(HistoryOfPatientMedsVo instance)
	{
		return col.indexOf(instance);
	}
	public HistoryOfPatientMedsVo get(int index)
	{
		return this.col.get(index);
	}
	public boolean set(int index, HistoryOfPatientMedsVo value)
	{
		if(value == null)
			return false;
		this.col.set(index, value);
		return true;
	}
	public void remove(HistoryOfPatientMedsVo instance)
	{
		if(instance != null)
		{
			int index = indexOf(instance);
			if(index >= 0)
				remove(index);
		}
	}
	public boolean contains(HistoryOfPatientMedsVo instance)
	{
		return indexOf(instance) >= 0;
	}
	public Object clone()
	{
		HistoryOfPatientMedsVoCollection clone = new HistoryOfPatientMedsVoCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add((HistoryOfPatientMedsVo)this.col.get(x).clone());
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
	public HistoryOfPatientMedsVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public HistoryOfPatientMedsVoCollection sort(boolean caseInsensitive)
	{
		return sort(SortOrder.ASCENDING, caseInsensitive);
	}
	public HistoryOfPatientMedsVoCollection sort(SortOrder order)
	{
		return sort(new HistoryOfPatientMedsVoComparator(order));
	}
	public HistoryOfPatientMedsVoCollection sort(SortOrder order, boolean caseInsensitive)
	{
		return sort(new HistoryOfPatientMedsVoComparator(order, caseInsensitive));
	}
	@SuppressWarnings("unchecked")
	public HistoryOfPatientMedsVoCollection sort(Comparator comparator)
	{
		Collections.sort(col, comparator);
		return this;
	}
	public ims.emergency.vo.HistoryOfPatientMedsRefVoCollection toRefVoCollection()
	{
		ims.emergency.vo.HistoryOfPatientMedsRefVoCollection result = new ims.emergency.vo.HistoryOfPatientMedsRefVoCollection();
		for(int x = 0; x < this.col.size(); x++)
		{
			result.add(this.col.get(x));
		}
		return result;
	}
	public HistoryOfPatientMedsVo[] toArray()
	{
		HistoryOfPatientMedsVo[] arr = new HistoryOfPatientMedsVo[col.size()];
		col.toArray(arr);
		return arr;
	}
	public Iterator<HistoryOfPatientMedsVo> iterator()
	{
		return col.iterator();
	}
	@Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
	private class HistoryOfPatientMedsVoComparator implements Comparator
	{
		private int direction = 1;
		private boolean caseInsensitive = true;
		public HistoryOfPatientMedsVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public HistoryOfPatientMedsVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public HistoryOfPatientMedsVoComparator(SortOrder order, boolean caseInsensitive)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
			this.caseInsensitive = caseInsensitive;
		}
		public int compare(Object obj1, Object obj2)
		{
			HistoryOfPatientMedsVo voObj1 = (HistoryOfPatientMedsVo)obj1;
			HistoryOfPatientMedsVo voObj2 = (HistoryOfPatientMedsVo)obj2;
			return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
		}
		public boolean equals(Object obj)
		{
			return false;
		}
	}
	public ims.emergency.vo.beans.HistoryOfPatientMedsVoBean[] getBeanCollection()
	{
		return getBeanCollectionArray();
	}
	public ims.emergency.vo.beans.HistoryOfPatientMedsVoBean[] getBeanCollectionArray()
	{
		ims.emergency.vo.beans.HistoryOfPatientMedsVoBean[] result = new ims.emergency.vo.beans.HistoryOfPatientMedsVoBean[col.size()];
		for(int i = 0; i < col.size(); i++)
		{
			HistoryOfPatientMedsVo vo = ((HistoryOfPatientMedsVo)col.get(i));
			result[i] = (ims.emergency.vo.beans.HistoryOfPatientMedsVoBean)vo.getBean();
		}
		return result;
	}
	public static HistoryOfPatientMedsVoCollection buildFromBeanCollection(java.util.Collection beans)
	{
		HistoryOfPatientMedsVoCollection coll = new HistoryOfPatientMedsVoCollection();
		if(beans == null)
			return coll;
		java.util.Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			coll.add(((ims.emergency.vo.beans.HistoryOfPatientMedsVoBean)iter.next()).buildVo());
		}
		return coll;
	}
	public static HistoryOfPatientMedsVoCollection buildFromBeanCollection(ims.emergency.vo.beans.HistoryOfPatientMedsVoBean[] beans)
	{
		HistoryOfPatientMedsVoCollection coll = new HistoryOfPatientMedsVoCollection();
		if(beans == null)
			return coll;
		for(int x = 0; x < beans.length; x++)
		{
			coll.add(beans[x].buildVo());
		}
		return coll;
	}
}