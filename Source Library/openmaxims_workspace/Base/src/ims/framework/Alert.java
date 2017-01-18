package ims.framework;

import java.io.Serializable;

import ims.framework.enumerations.AlertCategory;
import ims.framework.utils.Image;

abstract public class Alert implements Serializable
{
	private static final long serialVersionUID = 1L;	
	private static Integer statID = new Integer(0);
	
	public Alert(Image icon, Object data, String tooltip, AlertCategory category, boolean allowMultiple)
	{
		synchronized(Alert.statID)
		{
			this.id = Alert.statID.intValue();
			Alert.statID = new Integer(this.id + 1);
		}
		this.icon = icon;
		this.data = data;
		this.tooltip = tooltip;
		this.category = category;
		this.allowMultiple = allowMultiple;
	}
	
	public Alert(Image icon, Object data, String tooltip, AlertCategory category)
	{
		synchronized(Alert.statID)
		{
			this.id = Alert.statID.intValue();
			Alert.statID = new Integer(this.id + 1);
		}
		this.icon = icon;
		this.data = data;
		this.tooltip = tooltip;
		this.category = category;
	}
	
	public Alert(Image icon, Object data, String tooltip)
	{
		synchronized(Alert.statID)
		{
			this.id = Alert.statID.intValue();
			Alert.statID = new Integer(this.id + 1);
		}
		this.icon = icon;
		this.data = data;
		this.tooltip = tooltip;
	}
	public int getID()
	{
		return this.id;
	}
	public Image getIcon()
	{
		return this.icon;
	}
	public String getTooltip()
	{
		return this.tooltip;
	}
	public AlertCategory getCategory()
	{
		return this.category;
	}
	public abstract int getIndex();
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Alert)			
			return this.id == ((Alert)obj).id;
		return false;
	}
	public int hashCode()
	{
		return this.id;
	}
	public boolean allowMultiple()
	{
		return allowMultiple;
	}
	public boolean enabledInEditMode()
	{
		return false;
	}
	
	protected int id;
	protected Object data;
	protected Image icon;
	protected String tooltip;
	protected AlertCategory category;
	protected boolean allowMultiple = false;
}
