/*
 * Created on 04-May-2005
 *
 */
package ims.vo;

import java.util.Iterator;

/**
 * @author jmacenri
 *
 */
public class LookupInstanceBean 
{
	private int id;
	private String text;
	private boolean active;
	
	public LookupInstanceBean()
	{
		
	}
	public LookupInstanceBean(LookupInstVo inst)
	{
		this.id = inst.getId();
		this.text = inst.getText();
		this.active = inst.isActive();		
	}
	public boolean isActive() 
	{
		return this.active;
	}
	public void setActive(boolean active) 
	{
		this.active = active;
	}
	public int getId() 
	{
		return this.id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getText() 
	{
		return this.text;
	}
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public LookupInstVo buildLookupInstVo()
	{
		return new LookupInstVo(this.id, this.text, this.active);
	}
	public static LookupInstVo[] buildLookupInstVoArray(LookupInstanceBean[] beans)
	{
		LookupInstVo[] ret = new LookupInstVo[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildLookupInstVo();			
		}
		return ret;		
	}
	
	public static LookupInstanceCollection buildLookupInstanceVoCollection(java.util.Collection beans)
	{
		LookupInstanceCollection ret = new LookupInstanceCollection();
		Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			ret.add(((LookupInstanceBean)iter.next()).buildLookupInstVo());			
		}
		return ret;		
	}

}
