package ims.framework;

import java.io.Serializable;

import ims.framework.interfaces.IMenuAction;

public final class MenuAction implements IMenuAction, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String description;
	
	public MenuAction(int id)
	{
		this(id, "<Description not available>");	
	}
	public MenuAction(int id, String description)
	{
		this.id = id;
		this.description = description;
	}
	
	public int getMenuActionID()
	{
		return id;
	}
	public String getMenuActionDescription()
	{
		return description;
	}	
	public String toString()
	{
		return description;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof IMenuAction)
			return ((IMenuAction)obj).getMenuActionID() == id;
		return false;
	}
	public int getHashCode()
	{
		return id;
	}
}
