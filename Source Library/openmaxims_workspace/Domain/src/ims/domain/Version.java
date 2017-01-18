
package ims.domain;
			
public class Version 
{
	public static final int Major = 1;
	public static final int Minor = 1498;
	public static final String Timestamp = "20150512 16:30:29";
			
	public static final String getVersionInfo() 
	{
		return String.valueOf(Major) + "." + String.valueOf(Minor); 
	}
	public static final String getFullVersionInfo() 
	{
		return String.valueOf(Major) + "." + String.valueOf(Minor) + "." + Timestamp; 
	} 
	public static final String getTimestamp() 
	{
		return Timestamp; 
	} 
}			
		