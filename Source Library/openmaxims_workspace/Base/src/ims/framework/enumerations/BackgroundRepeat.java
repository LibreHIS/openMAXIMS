package ims.framework.enumerations;

import java.io.Serializable;

public class BackgroundRepeat implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final BackgroundRepeat REPEAT = new BackgroundRepeat(1);
    public static final BackgroundRepeat REPEAT_X = new BackgroundRepeat(2);
    public static final BackgroundRepeat REPEAT_Y = new BackgroundRepeat(3);
    public static final BackgroundRepeat NO_REPEAT = new BackgroundRepeat(4);
    
    private BackgroundRepeat(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "repeat";
    	if (this.id == 2)
    		return "repeat-x";
    	if (this.id == 3)
    		return "repeat-y";
    	if (this.id == 4)
    		return "no-repeat";    	
    	
    	return "no-repeat";
    }

    private int id; 
}
