package ims.framework.utils;

import ims.framework.IEnhancedItem;
/**
 * @author mmihalec
 *
 * This class represents the base of all specific enum classes within the system.
 * By design, the concrete enum classes will not have access to any fields or methods (except constructor)
 * as they will only define an enum type.
 */
public abstract class Enum implements IEnhancedItem
{
	protected Enum(int id)
	{
		this(id, null, null, null);
	}
	protected Enum(int id, String text)
	{
		this(id, text, null, null);
	}
	protected Enum(int id, String text, Image image, Color textColor)
	{
		this.id = id;
		this.text = text;
		this.image = image;
		this.textColor = textColor;
	}		
	public final String getText()
	{
		return this.text;		
	}
	public final Image getImage()
	{
		return this.image;
	}
	public final Color getTextColor()
	{
		return this.textColor;
	}
	public final Image getIItemImage()
	{
		return getImage();
	}
    public final Color getIItemTextColor()
    {
    	return getTextColor();
    }
    public final String getIItemText()
    {
    	return this.getText();
    }    
    public final String toString()
    {
    	return this.text;
    }
    
	protected int id;
	private String text;
	private Image image;
	private Color textColor;
}
