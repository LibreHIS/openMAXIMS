/*
 * Created on 11-Nov-2004
 *
 */
package ims.configuration;

/**
 * @author jmacenri
 *
 */
public class SiteCode 
{
	public static final String IMS = "IMS";
	public static final String MWHB = "MWHB";
	public static final String CCO = "CCO";	
	public static final String RCHT = "RCHT";
	public static final String STEES = "STEES";
	public static final String NTPF = "NTPF";
	public static final String BHRT = "BHRT";
	public static final String SVUH = "SVUH";
	public static final String BLK = "BLK";
	public static final String HMR = "HMR";
	public static final String PON = "PON";
	public static final String WST = "WST";
	
	public static String[] getSiteCodes()
	{
		return new String[]{IMS, MWHB, CCO, RCHT, STEES, NTPF, BHRT, SVUH, BLK, HMR, PON, WST };
	}
	
	public static boolean isValidSiteCode(String code)
	{
		if (code == null) return false;
		
		String[] codes = getSiteCodes();
		for (int i = 0; i < codes.length; i++)
		{
			if (codes[i].equals(code)) return true;
		}
		return false;
	}
}
