package ims.framework.enumerations;

import java.io.Serializable;

public class AlertCategory implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final AlertCategory ONE = new AlertCategory(1);
    public static final AlertCategory TWO = new AlertCategory(2);
    public static final AlertCategory THREE = new AlertCategory(3);
    public static final AlertCategory FOUR = new AlertCategory(4);    
    public static final AlertCategory OTHER = new AlertCategory(5); 
    
    private AlertCategory(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "ONE";
    	if (this.id == 2)
    		return "TWO";
    	if (this.id == 3)
    		return "THREE";
    	if (this.id == 4)
    		return "FOUR";   
    	if (this.id == 5)
    		return "OTHER";
    	
    	return "OTHER";
    }

    private int id; 
}
