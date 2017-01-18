package ims.framework;

public class MessageButtons
{
	private int id;
	private String code;
	
	public static MessageButtons OK = new MessageButtons(1, "OK");
	public static MessageButtons OKCANCEL = new MessageButtons(2, "OKCancel");
	public static MessageButtons YESNO = new MessageButtons(3, "YesNo");
	public static MessageButtons YESNOCANCEL = new MessageButtons(4, "YesNoCancel");
	public static MessageButtons CONFIRMCANCEL = new MessageButtons(5, "ConfirmCancel");
		
	private MessageButtons(int id, String code)
	{
		this.id = id;
		this.code = code;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof MessageButtons)
			return ((MessageButtons)obj).id == id;
		return false;
	}
	public String toString()
	{
		return code;
	}
}
