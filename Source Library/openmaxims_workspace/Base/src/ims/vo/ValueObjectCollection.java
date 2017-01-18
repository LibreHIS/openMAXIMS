/*
 * Created on 08-Sep-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo;

import java.io.Serializable;
import java.util.ArrayList;

import ims.framework.IItem;
import ims.framework.IItemCollection;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ValueObjectCollection implements ImsCloneable, IItemCollection, Serializable
{
	private static final long serialVersionUID = 1L;
	
    public abstract Object clone();
	public abstract boolean isValidated();
	public abstract String[] validate();
	public abstract int size();
	public abstract String getBoClassName();
	
	private int rieCount;
	private int activeCount;
	public int getActiveCount()
	{
		return activeCount;
	}
	public void setActiveCount(int activeCount)
	{
		this.activeCount = activeCount;
	}
	public int getRieCount()
	{
		return rieCount;
	}
	public void setRieCount(int rieCount)
	{
		this.rieCount = rieCount;
	}    
    public IItem[] getItems()
    {
        IItem[] items = new IItem[getTypedCollection().size()];
         for (int i = 0; i < getTypedCollection().size(); i++)
        {
            items[i] = (IItem)getTypedCollection().get(i);
        }
        return items;
    }    
    public int compareTo(Object obj)
    {
    	if(obj != null && ValueObjectCollection.class.isAssignableFrom(obj.getClass()))
    	{
    		if(this.equals(obj))
        		return 0;
    		
    		return this.size() > ((ValueObjectCollection)obj).size() ? 1 : -1;
    	}
    	
    	return -1;
    }
    @SuppressWarnings("unchecked")
	public ValueObjectMixedCollection getValueObjectMixedCollection()
    {
    	return new ValueObjectMixedCollection(getTypedCollection());
    }
    protected abstract ArrayList getTypedCollection();
       
}
