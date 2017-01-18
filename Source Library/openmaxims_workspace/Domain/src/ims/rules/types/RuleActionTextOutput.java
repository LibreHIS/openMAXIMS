package ims.rules.types;

import ims.rules.interfaces.IRuleAction;

public class RuleActionTextOutput implements IRuleAction
{
	private static final long serialVersionUID = 1L;	
	
	private String text;

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		if(text == null)
			text = "";
		this.text = text;
	}
	
	public RuleActionTextOutput()
	{
		this("");
	}
	public RuleActionTextOutput(String text)
	{
		this.text = text;
	}	
}

