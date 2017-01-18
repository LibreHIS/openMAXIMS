package ims.rules.types;

import java.io.Serializable;

public class RuleCompilationError implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public RuleCompilationError(String message)
	{
		this.message = message;
	}
	
	public String toString()
	{
		return message;
	}
}
