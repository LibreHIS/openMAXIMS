package ims.framework.enumerations;

import java.io.Serializable;

public class FontWeight implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final FontWeight NORMAL = new FontWeight(1);
    public static final FontWeight BOLD = new FontWeight(2);
    public static final FontWeight BOLDER = new FontWeight(3);
    public static final FontWeight LIGHTER = new FontWeight(4);
    
    private FontWeight(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "normal";
    	if (this.id == 2)
    		return "bold";
    	if (this.id == 3)
    		return "bolder";
    	if (this.id == 4)
    		return "lighter";    	
    	
    	return "normal";
    }

    private int id; 
}
