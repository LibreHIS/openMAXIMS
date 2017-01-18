package ims.framework.enumerations;

import java.io.Serializable;

public class FontFamily implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final FontFamily ANTIQUA = new FontFamily(1);
    public static final FontFamily ARIAL = new FontFamily(2);
    public static final FontFamily CALIBRI = new FontFamily(3);
    public static final FontFamily COMIC_SANS = new FontFamily(4);
    public static final FontFamily COURIER = new FontFamily(5);
    public static final FontFamily GEORGIA = new FontFamily(6);
    public static final FontFamily HELVETICA = new FontFamily(7);
    public static final FontFamily IMPACT = new FontFamily(8);
    public static final FontFamily TIMES_NEW_ROMAN = new FontFamily(9);
    public static final FontFamily VERANDA = new FontFamily(10);
    public static final FontFamily BRUSH_SCRIPT_MT = new FontFamily(11);
    public static final FontFamily CURSIVE = new FontFamily(12);
    public static final FontFamily FANTASY = new FontFamily(13);
    public static final FontFamily GARAMOND = new FontFamily(14);
    public static final FontFamily TAHOMA = new FontFamily(15);
        
    private FontFamily(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "Antiqua";
    	if (this.id == 2)
    		return "Arial";
    	if (this.id == 3)
    		return "Calibri";
    	if (this.id == 4)
    		return "Comic Sans";
    	if (this.id == 5)
    		return "Courier";
    	if (this.id == 6)
    		return "Georgia";
    	if (this.id == 7)
    		return "Helvetica";
    	if (this.id == 8)
    		return "Impact";
    	if (this.id == 9)
    		return "Times New Roman";
    	if (this.id == 10)
    		return "Verdana";
    	if (this.id == 11)
    		return "Brush Script Mt";
    	if (this.id == 12)
    		return "Cursive";
    	if (this.id == 13)
    		return "Fanstasy";
    	if (this.id == 14)
    		return "Garamond";
    	if (this.id == 15)
    		return "Tahoma";
    	
    	return "Tahoma";
    }

    private int id; 
}
