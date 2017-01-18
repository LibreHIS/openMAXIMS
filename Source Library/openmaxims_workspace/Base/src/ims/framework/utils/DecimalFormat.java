package ims.framework.utils;

public class DecimalFormat 
{
	public static String format(Object value, int precision, int scale)
	{
		return format(value, precision, scale, '.');
	}
	public static String format(Object value, int precision, int scale, char decimalSeparator)
	{
		if(value == null)
			return "";
		
		String precisionStr = "";
		String scaleStr = "";
		for(int x = 0; x < precision-scale-1; x++)
			precisionStr += "#";
		precisionStr += "0";
		for(int x = 0; x < scale; x++)
			scaleStr += "0";
		
		return format(value, precisionStr + decimalSeparator + scaleStr);		
	}
	private static String format(Object value, String formatString)
	{
		if(value == null || formatString == null)
			return "";
		
		return new java.text.DecimalFormat(formatString).format(value);
	}
}
