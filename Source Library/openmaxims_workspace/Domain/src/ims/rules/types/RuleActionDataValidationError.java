package ims.rules.types;

import ims.rules.interfaces.IRuleAction;

public class RuleActionDataValidationError implements IRuleAction
{
	private static final long serialVersionUID = 1L;	
	
	private String message;

	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		if(message == null)
			message = "";
		this.message = message;
	}
	
	public RuleActionDataValidationError()
	{
		this("");
	}
	public RuleActionDataValidationError(String message)
	{
		this.message = message;
	}	
}

