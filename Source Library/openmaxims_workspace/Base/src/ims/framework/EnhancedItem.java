package ims.framework;

import java.io.Serializable;

import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.utils.Color;
import ims.framework.utils.Image;
import ims.framework.utils.ImagePath;

/**
 * IEnhancedItem implementation
 * @author mmihalec
 */
public class EnhancedItem implements IEnhancedItem, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final EnhancedItem YES = new EnhancedItem(-100, "Yes", new ImagePath(1, "g/Yes.gif", new Integer(10), new Integer(10)));
	public static final EnhancedItem NO = new EnhancedItem(-101, "No", new ImagePath(0, "g/No.gif", new Integer(10), new Integer(10)));
	
	public EnhancedItem(int id, String text)
	{
		this(id, text, null, null);
	}
	public EnhancedItem(int id, String text, Color textColor)
	{
		this(id, text, null, textColor);
	}	
	public EnhancedItem(int id, String text, Image image)
	{
		this(id, text, image, null);
	}
	public EnhancedItem(int id, String text, Image image, Color textColor)
	{
		if(text == null)
			throw new CodingRuntimeException("Invalid item text");
		
		this.id = id;
		this.text = text;
		this.image = image;
		this.textColor = textColor;
	}
	public final int getIItemID()
	{
		return this.id;
	}
	public final String getIItemText() 
	{
		return this.text;
	}
	public Image getIItemImage() 
	{
		return this.image;
	}
	public Color getIItemTextColor() 
	{
		return this.textColor;
	}
	public String toString()
	{
		return this.getIItemText();
	}
	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof EnhancedItem)
			return this.id == ((EnhancedItem)obj).id;
		return false;
	}
	public int hashCode()
	{
		return this.id;
	}

	private int id;	
	private String text;	
	protected Color textColor;
	protected Image image;	
}
