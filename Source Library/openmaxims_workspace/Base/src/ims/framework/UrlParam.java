package ims.framework;

import ims.framework.exceptions.CodingRuntimeException;

public class UrlParam
{
	private String key;
	private String value;
	
	public UrlParam(String key, String value)
	{
		if(key == null)
			throw new CodingRuntimeException("Invalid key for the Url parameter");
		if(value == null)
			throw new CodingRuntimeException("Invalid value for the Url parameter");
		
		this.key = key;
		this.value = value;
	}
	
	public String getKey()
	{
		return key;
	}
	public String getValue()
	{
		return value;
	}
}
