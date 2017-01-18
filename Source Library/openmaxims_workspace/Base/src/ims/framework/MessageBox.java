package ims.framework;

public abstract class MessageBox
{
	protected int id; 
	protected String text;
	protected String title;
	protected MessageButtons buttons; 
	protected MessageIcon icon;
	protected MessageDefaultButton defaultButton;
	protected boolean hasId;
	
	public MessageBox(String text)
	{
		this(null, text, "Application", MessageButtons.OK, MessageIcon.INFORMATION, MessageDefaultButton.BUTTON1);
	}
	public MessageBox(String text, String title)
	{
		this(null, text, title, MessageButtons.OK, MessageIcon.INFORMATION, MessageDefaultButton.BUTTON1);
	}
	public MessageBox(int id, String text, String title, MessageButtons buttons)
	{
		this(id, text, title, buttons, MessageIcon.INFORMATION, MessageDefaultButton.BUTTON1);
	}
	public MessageBox(int id, String text, String title, MessageButtons buttons, MessageIcon icon)
	{
		this(id, text, title, buttons, icon, MessageDefaultButton.BUTTON1);
	}
	public MessageBox(Integer id, String text, String title, MessageButtons buttons, MessageIcon icon, MessageDefaultButton defaultButton)
	{
		this.id = id == null ? 0 : id;
		this.hasId = id != null;// && !buttons.equals(MessageButtons.OK); - OK should still return event
		
		this.text = text.replace('\"', '\'');
		this.title = title;
		this.buttons = buttons;
		this.icon = icon;
		this.defaultButton = defaultButton;		
	}
	
	public abstract void render(StringBuffer sb);	
}
