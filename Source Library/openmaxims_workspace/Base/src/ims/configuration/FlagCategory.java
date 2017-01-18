package ims.configuration;

import java.util.HashMap;
import java.util.Iterator;

public class FlagCategory
{
	private String key;
	private String name;
	private HashMap<String, CfgFlag> flags = new HashMap<String, CfgFlag>();

	protected FlagCategory(String key, String name)
	{
		this.key = key;
		this.name = name;
	}
	
	protected void addFlag(CfgFlag flag)
	{
		this.flags.put(flag.getKey(), flag);
	}
	
	public CfgFlag[] listFlags()
	{
		CfgFlag[] ret = new CfgFlag[flags.size()];
		int i = 0;
		for (Iterator<CfgFlag> iter = flags.values().iterator(); iter.hasNext();)
		{
			ret[i] = iter.next();
			i++;
		}
		return ret;
	}

	public String getKey()
	{
		return key;
	}

	public String getName()
	{
		return name;
	}
}
