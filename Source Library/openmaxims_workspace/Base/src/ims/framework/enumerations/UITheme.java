package ims.framework.enumerations;

import java.io.Serializable;

public final class UITheme implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private int id;
	private String name;
	
	public static UITheme Blue = new UITheme(1, "blue");
	public static UITheme NewBlue = new UITheme(2, "new-blue");
	
	private UITheme(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof UITheme)
			return ((UITheme)obj).id == id;
		return false;
	}
	public static UITheme parse(int id)
	{
		if(id == Blue.id)
			return Blue;		
		
		return NewBlue;		
	}
}
