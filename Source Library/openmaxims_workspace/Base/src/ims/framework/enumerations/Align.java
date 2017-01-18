package ims.framework.enumerations;

import java.io.Serializable;

public class Align implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final Align LEFT = new Align(1);
    public static final Align RIGHT = new Align(2);
    public static final Align CENTER = new Align(3);
    public static final Align JUSTIFY = new Align(4);    
    
    private Align(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "left";
    	if (this.id == 2)
    		return "right";
    	if (this.id == 3)
    		return "center";
    	if (this.id == 4)
    		return "justify";   
    	
    	return "center";
    }

    private int id; 
}
