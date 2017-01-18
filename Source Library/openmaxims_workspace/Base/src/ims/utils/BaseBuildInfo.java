package ims.utils;

public class BaseBuildInfo
{
	public BaseBuildInfo()
	{
		this.appDescription = "";
		this.appVersion = "";
		this.appTimestamp="";
	}
	public BaseBuildInfo(String name, String version, String date)
	{
		this.appDescription = name;
		this.appVersion = version;
		this.appTimestamp = date;		
	}

	public BaseBuildInfo(String name, String version, String date, String warVer, String warTs)
	{
		this.appDescription = name;
		this.appVersion = version;
		this.appTimestamp = date;	
		this.warVersion = warVer;
		this.warTimestamp = warTs;
	}

	public String getName()
	{
		return this.appDescription;
	}
	
	public String getAppTimestamp()
	{
		return this.appTimestamp;
	}
	
	public String getAppVersion()
	{
		return this.appVersion;
	}
	
	public String toString()
	{
		return this.appDescription + "-" + this.appVersion;
	}

	public String getWarTimestamp() 
	{
		return warTimestamp;
	}
	public String getWarVersion() 
	{
		return warVersion;
	}
	
	protected String appDescription;
	protected String appVersion;
	protected String appTimestamp;
	protected String warVersion = "";
	protected String warTimestamp = "";
}
