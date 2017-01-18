package ims.framework.enumerations;

import java.io.Serializable;

public class MouseButton implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	
   	public static final MouseButton LEFT = new MouseButton(1);
    public static final MouseButton RIGHT = new MouseButton(2);
	public static final MouseButton MIDDLE = new MouseButton(4);
	    
    private MouseButton(int value)
    {
        this.id = value;
    }    
    
    public boolean equals(Object value)
    {
    	if(value instanceof MouseButton)
    		return ((MouseButton)value).id == id;
    	return false;
    }
    
    public static MouseButton parse(String value)
    {
    	int id = Integer.valueOf(value);
    	    	
    	if(id == LEFT.id)
    		return LEFT;
    	else if(id == RIGHT.id)
    		return RIGHT;
    	else if(id == MIDDLE.id)
    		return MIDDLE;
    	
    	return LEFT;
    }
}
