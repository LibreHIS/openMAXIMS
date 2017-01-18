package ims.framework.enumerations;

import java.io.Serializable;

public final class NavigationStyle implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private int id;	
	
	public static NavigationStyle Text = new NavigationStyle(1);
	public static NavigationStyle Image = new NavigationStyle(2);	
	
	private NavigationStyle(int id)
	{
		this.id = id;		
	}
		
	public boolean equals(Object obj)
	{
		if(obj instanceof NavigationStyle)
			return ((NavigationStyle)obj).id == id;
		return false;
	}
	public static NavigationStyle parse(int id)
	{
		if(id == Text.id)
			return Text;
		else if(id == Image.id)
			return Image;		
		
		throw new RuntimeException("Unable to find NavigationStyle matching id " + id);
	}
}
