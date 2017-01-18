/*
 * Created on 05-Nov-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo;

import ims.framework.IEnhancedItem;
import ims.framework.IItem;
import ims.framework.IItemCollection;
import ims.framework.enumerations.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LookupInstanceCollection implements IItemCollection, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<LookupInstVo> col = new ArrayList<LookupInstVo>();
	private LookupInstVo[] rootNodes;
	private boolean sorted = false;

	public void add(LookupInstVo value)
	{
		if(value != null)
		{
			if(indexOf(value) < 0)
			{
				this.col.add(value);
			}
		}
	}
	public int size()
	{
		return this.col.size();
	}
	public int indexOf(LookupInstVo instance)
	{
		return this.col.indexOf(instance);
	}
	public LookupInstVo getIndex(int index)
	{
		return this.col.get(index);
	}
	public void remove(int index)
	{
		this.col.remove(index);
	}	
	public LookupInstVo getInstanceById(int instanceId)
	{
		LookupInstVo item;
		for (int i = 0; i < this.size(); i++)
		{
			item = this.getIndex(i);
			if (item.getID() == instanceId)
				return item;
		}
		return null;
	}

	private void sortHierarchy()
	{
		if (this.sorted) return;
		ArrayList<LookupInstVo> rootItems = new ArrayList<LookupInstVo>();
		LookupInstVo item;
		for (int i = 0; i < this.col.size(); i++)
		{
			item = this.col.get(i);
			if (item.getParentInstance() == null)
			{
				rootItems.add(item);
			}
			else
			{
				LookupInstVo realParent = getRealParent(item.getParentInstance());
				if (realParent != null)
					realParent.addChild(item);
			}
		}
		this.rootNodes = new LookupInstVo[rootItems.size()];
		rootItems.toArray(this.rootNodes);
		this.sorted = true;
	}
	
	private LookupInstVo getRealParent(LookupInstVo parentInstance)
	{
		if (col.indexOf(parentInstance) == -1)
			return null;
		
		return col.get(col.indexOf(parentInstance));
	}
	public LookupInstanceCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	@SuppressWarnings("unchecked")
	public LookupInstanceCollection sort(SortOrder order)
	{
		Collections.sort(this.col, new LookupTypeComparator(order));
		return this;
	}

	private class LookupTypeComparator implements Comparator
	{
		private int direction = 1;
		public LookupTypeComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public LookupTypeComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				this.direction = -1;
			}
		}
		public int compare(Object obj1, Object obj2)
		{
			int ret = 0;
			LookupInstVo voObj1 = (LookupInstVo)obj1;
			LookupInstVo voObj2 = (LookupInstVo)obj2;
			if (voObj1.getOrder() < voObj2.getOrder()) ret = -1;
			if (voObj1.getOrder() > voObj2.getOrder()) ret = 1;
			return this.direction*(ret);
		}
	}
	
	public LookupInstVo[] getRoots()
	{
		if (!this.sorted)
		{
			sortHierarchy();
		}
		return this.rootNodes;		
	}
	
	protected Object[] toArray(Object[] arr)
	{
		return this.col.toArray(arr);
	}
	
	public java.util.Collection getBeanCollection()
	{		
		java.util.List<LookupInstanceBean> l = new java.util.ArrayList<LookupInstanceBean>();
		for (int i = 0; i < this.col.size(); i++)
		{
			LookupInstVo inst = this.col.get(i);
			l.add(inst.getBean());
		}
		return java.util.Collections.unmodifiableCollection(l);
	}
    public IItem[] getItems() 
    {
        IEnhancedItem[] items = new IEnhancedItem[this.col.size()];    
        for (int i = 0; i < this.col.size(); i++)
        {
            items[i] = this.col.get(i);
        }        
        return items;
    }

	

}
