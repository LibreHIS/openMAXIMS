package ims.framework.enumerations;

import java.io.Serializable;

public class BorderStyle implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final BorderStyle NONE = new BorderStyle(1);
    public static final BorderStyle HIDDEN = new BorderStyle(2);
    public static final BorderStyle DOTTEED = new BorderStyle(3);
    public static final BorderStyle SOLID = new BorderStyle(4);
    public static final BorderStyle DASHED = new BorderStyle(5);
    public static final BorderStyle DOUBLE = new BorderStyle(6);
    public static final BorderStyle GROOVE = new BorderStyle(7);
    public static final BorderStyle RIDGE = new BorderStyle(8);
    public static final BorderStyle INSERT = new BorderStyle(9);
    public static final BorderStyle OUTSET = new BorderStyle(10);        
    
    private BorderStyle(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "none";
    	if (this.id == 2)
    		return "hidden";
    	if (this.id == 3)
    		return "dotted";
    	if (this.id == 4)
    		return "solid";
    	if (this.id == 5)
    		return "dashed";
    	if (this.id == 6)
    		return "double";
    	if (this.id == 7)
    		return "groove";
    	if (this.id == 8)
    		return "ridge";
    	if (this.id == 9)
    		return "insert";
    	if (this.id == 10)
    		return "outset";
    	
    	return "none";
    }

    private int id; 
}
