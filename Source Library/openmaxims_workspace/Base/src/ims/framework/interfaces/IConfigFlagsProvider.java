package ims.framework.interfaces;

import ims.configuration.IFlag;

public interface IConfigFlagsProvider 
{
	String[] getGroups();
	String getActiveGroup();
	
	IFlag getConfigFlag(String group, String name);
	IFlag[] getConfigFlags(String group);
	
	String getValue(String key);
	void setValue(String key, String value, String defaultValue, boolean isSystem) throws Exception;		
}
