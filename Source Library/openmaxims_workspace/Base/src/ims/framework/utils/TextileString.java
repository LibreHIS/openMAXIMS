package ims.framework.utils;

import ims.utils.textile.Textile;

public final class TextileString
{
	public TextileString(String value)
	{
		this.value = value;
	}
	public String toString()
	{
		if(this.value == null)
			return "";
		
		return new Textile().process(this.value);
	}	
	private String value;
}
