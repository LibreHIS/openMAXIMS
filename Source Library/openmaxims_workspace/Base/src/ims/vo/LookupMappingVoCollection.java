package ims.vo;

import ims.framework.enumerations.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LookupMappingVoCollection implements ims.vo.ImsCloneable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<LookupMappingVo> col = new ArrayList<LookupMappingVo>();
	
	public void add(LookupMappingVo value)
	{
		this.col.add(value);
	}
	public void remove(int index)
	{
		this.col.remove(index);
	}
	public void clear()
	{
	    this.col.clear();
	}
	public int size()
	{
		return this.col.size();
	}
	public LookupMappingVo get(int index)
	{
		return col.get(index);
	}
	
	public Object clone()
	{
		LookupMappingVoCollection newColl = new LookupMappingVoCollection();
		for (int i = 0; i < this.size(); i++)
		{
			newColl.add((LookupMappingVo)this.get(i).clone());
		}
		return newColl;
	}

	
	public LookupMappingVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public LookupMappingVoCollection sort(boolean caseInsensitive)
	{
		return sort(SortOrder.ASCENDING, caseInsensitive);
	}
	public LookupMappingVoCollection sort(SortOrder order)
	{
		return sort(new LookupMappingVoComparator(order));
	}
	public LookupMappingVoCollection sort(SortOrder order, boolean caseInsensitive)
	{
		return sort(new LookupMappingVoComparator(order, caseInsensitive));
	}
	@SuppressWarnings("unchecked")
	public LookupMappingVoCollection sort(Comparator comparator)
	{
		Collections.sort(col, comparator);
		return this;
	}
	public LookupMappingVo[] toArray()
	{
		LookupMappingVo[] arr = new LookupMappingVo[col.size()];
		col.toArray(arr);
		return arr;
	}
	public boolean contains(LookupMappingVo mapping)
	{
		return col.contains(mapping);
	}

	private class LookupMappingVoComparator implements Comparator
	{
		private int direction = 1;
		private boolean caseInsensitive = true;
		public LookupMappingVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public LookupMappingVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public LookupMappingVoComparator(SortOrder order, boolean caseInsensitive)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
			this.caseInsensitive = caseInsensitive;
		}
		public int compare(Object obj1, Object obj2)
		{
			LookupMappingVo voObj1 = (LookupMappingVo)obj1;
			LookupMappingVo voObj2 = (LookupMappingVo)obj2;
			return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
		}
	}
	
}
