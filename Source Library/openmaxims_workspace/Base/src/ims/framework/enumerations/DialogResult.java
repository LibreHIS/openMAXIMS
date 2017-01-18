package ims.framework.enumerations;

import java.io.Serializable;

public class DialogResult implements Serializable
{
	private static final long serialVersionUID = 1L;
   	public static final DialogResult OK = new DialogResult(1);
    public static final DialogResult CANCEL = new DialogResult(2);
	public static final DialogResult YES = new DialogResult(3);
	public static final DialogResult NO = new DialogResult(4);
	public static final DialogResult RETRY = new DialogResult(5);
	public static final DialogResult ABORT = new DialogResult(6);
	public static final DialogResult CONFIRM = new DialogResult(7);
    
    private DialogResult(int value)
    {
        this.id = value;
    }
    protected int id; 
}
