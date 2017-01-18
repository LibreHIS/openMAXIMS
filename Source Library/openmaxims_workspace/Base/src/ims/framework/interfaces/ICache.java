package ims.framework.interfaces;

public interface ICache
{
	public Object put(String key, Object value);
	public Object get(String key);
	public Object remove(String key);
}
