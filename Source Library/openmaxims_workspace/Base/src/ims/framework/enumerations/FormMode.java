package ims.framework.enumerations;

import java.io.Serializable;

public class FormMode implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final FormMode VIEW = new FormMode(1);
    public static final FormMode EDIT = new FormMode(2);
    
    private FormMode(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
    	if (this.id == 1)
    		return "View";
    	return "Edit";
    }

    private int id; 
}
