package ims.framework;

public class VersionInfo
{
	private int major;
	private int minor;
	
	public int getMajor()
	{
		return major;
	}
	public int getMinor()
	{
		return minor;
	}
	
	public VersionInfo(int major, int minor)
	{
		this.major = major;
		this.minor = minor;
	}
	public VersionInfo(String version)
	{
		try
		{
			int index = version.indexOf(".");
			if(index >= 0)
			{
				major = new Integer(version.substring(0, index)).intValue();
				minor = new Integer(version.substring(index + 1)).intValue();
			}
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Invalid version: " + version);
		}
	}
	public int compareTo(VersionInfo version)
	{
		if(version.major > major)
			return -1;
		else if(version.major < major)
			return 1;
		else
		{
			if(version.minor > minor)
				return -1;
			else if(version.minor < minor)
				return 1;
		}
		
		return 0;		
	}
	public String toString()
	{
		return String.valueOf(major) + "." + String.valueOf(minor);
	}
}
