package ims.framework;

public class MessageDefaultButton
{
	private int id;
	
	public static MessageDefaultButton BUTTON1 = new MessageDefaultButton(1);
	public static MessageDefaultButton BUTTON2 = new MessageDefaultButton(2);
	public static MessageDefaultButton BUTTON3 = new MessageDefaultButton(3);	
	
	private MessageDefaultButton(int id)
	{
		this.id = id;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof MessageDefaultButton)
			return ((MessageDefaultButton)obj).id == id;
		return false;
	}
}
