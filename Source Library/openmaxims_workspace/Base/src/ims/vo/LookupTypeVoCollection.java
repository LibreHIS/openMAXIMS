package ims.vo;

import ims.framework.enumerations.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LookupTypeVoCollection implements ims.vo.ImsCloneable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<LookupTypeVo> col = new ArrayList<LookupTypeVo>();
	public void add(LookupTypeVo value)
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
	public LookupTypeVo get(int index)
	{
		return col.get(index);
	}
	
	public Object clone()
	{
		LookupTypeVoCollection newColl = new LookupTypeVoCollection();
		for (int i = 0; i < this.size(); i++)
		{
			newColl.add((LookupTypeVo)this.get(i).clone());
		}
		return newColl;
	}
	
	public Collection getBeanCollection()
	{
		java.util.List<LookupTypeBean> l = new java.util.ArrayList<LookupTypeBean>();
		for (int i = 0; i < this.col.size(); i++)
		{
			LookupTypeVo type = this.col.get(i);
			l.add(type.getBean());
		}
		return java.util.Collections.unmodifiableCollection(l);
	}
	public LookupTypeVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public LookupTypeVoCollection sort(SortOrder order)
	{
		return sort(new LookupTypeVoComparator(order));
	}
	@SuppressWarnings("unchecked")
	public LookupTypeVoCollection sort(Comparator comparator)
	{
		Collections.sort(this.col, comparator);
		return this;
	}
	private class LookupTypeVoComparator implements Comparator
	{
		private int direction = 1;
		public LookupTypeVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public LookupTypeVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				this.direction = -1;
			}
		}
		public int compare(Object obj1, Object obj2)
		{
			LookupTypeVo voObj1 = (LookupTypeVo)obj1;
			LookupTypeVo voObj2 = (LookupTypeVo)obj2;
			return direction*(voObj1.compareTo(voObj2));
		}
	}
	
	
}
