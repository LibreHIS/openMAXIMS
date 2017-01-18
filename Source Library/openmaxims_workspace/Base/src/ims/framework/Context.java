package ims.framework;

public interface Context
{
	public Object get(String key);
	public void put(String key, Object value);
	public void remove(String key);
	public String[] getAllLocalVariablesNames();
	public String[] getLocalVariablesNames(String prefix);
	public String[] getGlobalVariablesNames();
	public String[] getPersistentGlobalVariablesNames();
	public boolean isValidContextType(Class clazz);
}
