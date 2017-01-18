package ims.framework.enumerations;

import java.io.Serializable;

/**
 * @author mmihalec
 */
public class SortMode implements Serializable
{
	private static final long serialVersionUID = 1L;
    public static final SortMode NONE = new SortMode(0);
    public static final SortMode AUTOMATIC = new SortMode(1);
    public static final SortMode MANUAL = new SortMode(2);
    
    private SortMode(int id)
    {
        this.id = id;
    }   
    public String toString()
    {
        if(this.id == 0)
            return "";
        if(this.id == 1)            
            return "auto";
        else if(this.id == 2)
            return "manual";        
        else return "";
    }
    public String render()
    {
        return toString();
    }
    public boolean equals(Object obj)
    {
    	if(obj != null  && obj instanceof SortMode)
    		return this.id == ((SortMode)obj).id;
    	return false;
    }
    public int hashCode()
    {
    	return this.id;
    }
    
    private int id;    
}
