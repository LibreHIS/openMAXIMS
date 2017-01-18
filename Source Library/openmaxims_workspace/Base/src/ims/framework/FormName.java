package ims.framework;

import ims.framework.utils.beans.FormNameBean;
import ims.vo.ImsCloneable;
import java.io.Serializable;

public abstract class FormName implements Serializable, ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;	
	
   	protected FormName()
    {
   		this.id = -1;
    }
	
   	protected FormName(int id)
    {
        this.id = id;
    }
   	protected FormName(int id, String name)
    {
        this(id);
        this.name = name;
    }
   	
	public int hashCode()
	{
		return this.id;
	}
    
    public boolean equals(Object object)
    {
        if (object != null && object instanceof FormName)
        {
          FormName x = (FormName)object;
          return x.id == this.id;
        }
        return false;
    }

    public int getID()
    {
        return this.id;
    }
    
    public String getName()
    {
    	return name;
    }
    	
	public Object clone()
	{
		//it's immutable anyway. Just return itself.
		return this;
	}

	public int compareTo(Object arg0) 
	{
		if (!(arg0 instanceof FormName)) return 1;
		FormName val = (FormName)arg0;
		
		if (this.getID() < val.getID()) return -1;
		else if (this.getID() > val.getID()) return 1;
		else return 0;
	} 	
	
	public FormNameBean getBean()
	{
		return new FormNameBean(this);
	}
	
	public String toString()
	{
		if (this.name == null)
		{
			return (""+id);			
		}
		return id + "," + name ;
	}
    private int id;
    private String name;
}
