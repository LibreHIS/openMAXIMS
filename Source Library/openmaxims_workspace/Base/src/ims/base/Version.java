
package ims.base;
			
public class Version 
{
	public static final int Major = 1;
	public static final int Minor = 1622;
	public static final String Timestamp = "20150528 11:05:18";
			
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
		