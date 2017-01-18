package ims.framework;

public class MessageIcon
{
	private int id;
	private String imagePath;
	
	public static MessageIcon ERROR = new MessageIcon(1, "g/error-1.gif");
	public static MessageIcon WARNING = new MessageIcon(2, "g/exl-4.gif");
	public static MessageIcon INFORMATION = new MessageIcon(3, "g/info-1.gif");
	public static MessageIcon QUESTION = new MessageIcon(4, "g/yesno-question-1.gif");	
	
	private MessageIcon(int id, String imagePath)
	{
		this.id = id;
		this.imagePath = imagePath;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof MessageIcon)
			return ((MessageIcon)obj).id == id;
		return false;
	}
	public String toString()
	{
		return imagePath;
	}
}
