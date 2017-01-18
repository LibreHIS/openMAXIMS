package ims.vo;

import java.io.Serializable;

public class LookupInstRefVo implements ImsCloneable, Serializable, Comparable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String text;
	private boolean active;

	public LookupInstRefVo() 
	{
		this.id = 0;
	}

	public LookupInstRefVo(int id) 
	{
		this.id = id;
	}
	
	public LookupInstRefVo(int id, String text, boolean active) 
	{
		this.id = id;
		this.text = text;
		this.active = active;
	}

	public final int getId()
	{
		return this.id;
	}
	public final void setId(int id)
	{
		this.id = id;
	}
	
	public final String getText()
	{
		return this.text;
	}
	
	public final void setText(String value)
	{
		this.text = value;
	}
	
	public final boolean isActive()
	{
		return this.active;
	}
	
	public final void setActive(boolean value)
	{
		this.active = value;
	}

	
	public String toString() 
	{
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("{id=").append(this.id).append(", ");
		sb.append("text=").append(this.text).append(", ");
		sb.append("active=").append(this.active).append("}");
		return sb.toString();
	}
	
	public boolean equals(Object obj) {
		if (null == obj)
		{
			return false;
		}
		if ( !(obj instanceof LookupInstRefVo) )
		{
			return false;
		}
		LookupInstRefVo lookupInst = (LookupInstRefVo) obj;
		if (lookupInst.getId() != getId())
		{
			return false;
		}
		return true;
	}
	
	public int hashCode()
	{
		return this.getId();
	}

	public Object clone() 
	{
		LookupInstRefVo clone = null;
		try {
			Class[] params = { Integer.TYPE };
			java.lang.reflect.Constructor ctor = this.getClass().getConstructor(params);			
			Object[] args = {new Integer(this.id)};			
			clone = (LookupInstRefVo)ctor.newInstance(args);
		}
		catch(Throwable e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
		clone.active = this.active;
		clone.text = this.text;
		return clone;
	}
	
	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(obj instanceof LookupInstRefVo))
		{
			throw new ClassCastException("A LookupInstRefVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		LookupInstRefVo compareObj = (LookupInstRefVo)obj;
		return this.text.compareTo(compareObj.text);		
	}
}
