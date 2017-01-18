package ims.framework.enumerations;

import java.io.Serializable;

public class FontStyle implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final FontStyle NORMAL = new FontStyle(1);
    public static final FontStyle ITALIC = new FontStyle(2);
    public static final FontStyle OBLIQUE = new FontStyle(3);
    
    private FontStyle(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "normal";
    	if (this.id == 2)
    		return "italic";
    	if (this.id == 3)
    		return "oblique";    	
    	
    	return "normal";
    }

    private int id; 
}
