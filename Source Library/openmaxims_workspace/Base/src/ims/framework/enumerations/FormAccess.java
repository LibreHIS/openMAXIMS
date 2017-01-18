package ims.framework.enumerations;

public class FormAccess
{
	private static final long serialVersionUID = 1L;
   	public static final FormAccess NO_ACCESS = new FormAccess(1);
    public static final FormAccess READ_WRITE = new FormAccess(2);
	public static final FormAccess READ_ONLY = new FormAccess(3);

	protected int id; 
    
    private FormAccess(int value)
    {
        this.id = value;
    }
    
    public String toString()
    {
    	if (id == 1)
    		return "--";
    	else if (id == 2)
    		return "RW";
    	else if (id == 3)
    		return "RO";
    	else 
    		return "";
    }
}
