package ims.framework.enumerations;

import java.io.Serializable;

public class UILayoutState implements Serializable
{
	private static final long serialVersionUID = 1L;
   	public static final UILayoutState DEFAULT = new UILayoutState(1);
    public static final UILayoutState FULLSCREEN = new UILayoutState(2);    
	
    private UILayoutState(int value)
    {
        this.id = value;
    }
  
    protected int id; 
}
