package ims.framework;

public interface ConfigFlag
{
	Object get(String key);
	void put(String key, Object value);	
}
