package ims.framework;

import ims.framework.utils.beans.FormNameBean;
import ims.vo.ImsCloneable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mmihalec
 *
 */
public class FormNameCollection implements ImsCloneable, Serializable
{
	private static final long serialVersionUID = 1L;


	public void add(FormName value)
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
	public int indexOf(FormName instance)
	{
		return this.col.indexOf(instance);
	}
	public FormName get(int index)
	{
		return this.col.get(index);
	}
	public void set(int index, FormName value)
	{
		this.col.set(index, value);
	}
	public void remove(FormName instance)
	{
		remove(indexOf(instance));
	}
	public Object clone()
	{
	    FormNameCollection clone = new FormNameCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add((FormName)this.col.get(x).clone());
			else
				clone.col.add(null);
		}
		
		return clone;
	}
	public java.util.Collection getBeanCollection()
	{
		List<FormNameBean> l = new ArrayList<FormNameBean>();
		for (int i = 0; i < this.size(); i++)
		{
			l.add(this.get(i).getBean());
		}
		return l;
	}


	private ArrayList<FormName> col = new ArrayList<FormName>();
}
