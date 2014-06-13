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

package ims.clinicaladmin.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import ims.framework.enumerations.SortOrder;

/**
 * Linked to clinical.configuration.VTMHotlistItem business object (ID: 1073100026).
 */
public class VTMHotlistItemVoCollection extends ims.vo.ValueObjectCollection implements ims.vo.ImsCloneable, Iterable<VTMHotlistItemVo>
{
	private static final long serialVersionUID = 1L;

	private ArrayList<VTMHotlistItemVo> col = new ArrayList<VTMHotlistItemVo>();
	public String getBoClassName()
	{
		return "ims.clinical.configuration.domain.objects.VTMHotlistItem";
	}
	public boolean add(VTMHotlistItemVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			return this.col.add(value);
		}
		return false;
	}
	public boolean add(int index, VTMHotlistItemVo value)
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
	public int indexOf(VTMHotlistItemVo instance)
	{
		return col.indexOf(instance);
	}
	public VTMHotlistItemVo get(int index)
	{
		return this.col.get(index);
	}
	public boolean set(int index, VTMHotlistItemVo value)
	{
		if(value == null)
			return false;
		this.col.set(index, value);
		return true;
	}
	public void remove(VTMHotlistItemVo instance)
	{
		if(instance != null)
		{
			int index = indexOf(instance);
			if(index >= 0)
				remove(index);
		}
	}
	public boolean contains(VTMHotlistItemVo instance)
	{
		return indexOf(instance) >= 0;
	}
	public Object clone()
	{
		VTMHotlistItemVoCollection clone = new VTMHotlistItemVoCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add((VTMHotlistItemVo)this.col.get(x).clone());
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
	public VTMHotlistItemVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public VTMHotlistItemVoCollection sort(boolean caseInsensitive)
	{
		return sort(SortOrder.ASCENDING, caseInsensitive);
	}
	public VTMHotlistItemVoCollection sort(SortOrder order)
	{
		return sort(new VTMHotlistItemVoComparator(order));
	}
	public VTMHotlistItemVoCollection sort(SortOrder order, boolean caseInsensitive)
	{
		return sort(new VTMHotlistItemVoComparator(order, caseInsensitive));
	}
	@SuppressWarnings("unchecked")
	public VTMHotlistItemVoCollection sort(Comparator comparator)
	{
		Collections.sort(col, comparator);
		return this;
	}
	public ims.clinical.configuration.vo.VTMHotlistItemRefVoCollection toRefVoCollection()
	{
		ims.clinical.configuration.vo.VTMHotlistItemRefVoCollection result = new ims.clinical.configuration.vo.VTMHotlistItemRefVoCollection();
		for(int x = 0; x < this.col.size(); x++)
		{
			result.add(this.col.get(x));
		}
		return result;
	}
	public VTMHotlistItemVo[] toArray()
	{
		VTMHotlistItemVo[] arr = new VTMHotlistItemVo[col.size()];
		col.toArray(arr);
		return arr;
	}
	public ims.vo.interfaces.IHotlistItem[] toIHotlistItemArray()
	{
		ims.vo.interfaces.IHotlistItem[] arr = new ims.vo.interfaces.IHotlistItem[col.size()];
		col.toArray(arr);
		return arr;
	}
	public Iterator<VTMHotlistItemVo> iterator()
	{
		return col.iterator();
	}
	@Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
	private class VTMHotlistItemVoComparator implements Comparator
	{
		private int direction = 1;
		private boolean caseInsensitive = true;
		public VTMHotlistItemVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public VTMHotlistItemVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public VTMHotlistItemVoComparator(SortOrder order, boolean caseInsensitive)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
			this.caseInsensitive = caseInsensitive;
		}
		public int compare(Object obj1, Object obj2)
		{
			VTMHotlistItemVo voObj1 = (VTMHotlistItemVo)obj1;
			VTMHotlistItemVo voObj2 = (VTMHotlistItemVo)obj2;
			return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
		}
		public boolean equals(Object obj)
		{
			return false;
		}
	}
	public ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean[] getBeanCollection()
	{
		return getBeanCollectionArray();
	}
	public ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean[] getBeanCollectionArray()
	{
		ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean[] result = new ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean[col.size()];
		for(int i = 0; i < col.size(); i++)
		{
			VTMHotlistItemVo vo = ((VTMHotlistItemVo)col.get(i));
			result[i] = (ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean)vo.getBean();
		}
		return result;
	}
	public static VTMHotlistItemVoCollection buildFromBeanCollection(java.util.Collection beans)
	{
		VTMHotlistItemVoCollection coll = new VTMHotlistItemVoCollection();
		if(beans == null)
			return coll;
		java.util.Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			coll.add(((ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean)iter.next()).buildVo());
		}
		return coll;
	}
	public static VTMHotlistItemVoCollection buildFromBeanCollection(ims.clinicaladmin.vo.beans.VTMHotlistItemVoBean[] beans)
	{
		VTMHotlistItemVoCollection coll = new VTMHotlistItemVoCollection();
		if(beans == null)
			return coll;
		for(int x = 0; x < beans.length; x++)
		{
			coll.add(beans[x].buildVo());
		}
		return coll;
	}
}