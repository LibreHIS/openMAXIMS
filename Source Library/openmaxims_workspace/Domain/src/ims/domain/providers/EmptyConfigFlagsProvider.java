package ims.domain.providers;

import ims.configuration.ConfigFlag;
import ims.configuration.IFlag;
import ims.framework.interfaces.IConfigFlagsProvider;

public class EmptyConfigFlagsProvider implements IConfigFlagsProvider
{
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(EmptyConfigFlagsProvider.class);
	
	public String getActiveGroup()
	{
		return "Default";
	}
	public IFlag getConfigFlag(String group, String name)
	{
		return ConfigFlag.getConfigFlag(name);
	}
	public IFlag[] getConfigFlags(String group)
	{
		return new IFlag[0];		
	}
	public String[] getGroups()
	{
		return new String[] { "Default" };
	}
	public String getValue(String key)
	{
		IFlag flag = ConfigFlag.getConfigFlag(key);
		if(flag == null)
		{
			LocalLogger.warn("Unable to find the flag " + key);
			return "";
		}
		return ConfigFlag.getConfigFlag(key).getDefaultValAsString();
	}
	public void setValue(String key, String value, String defaultValue, boolean isSystem) throws Exception
	{
		ConfigFlag.getConfigFlag(key).setDefaultValue(value);		
	}
}
