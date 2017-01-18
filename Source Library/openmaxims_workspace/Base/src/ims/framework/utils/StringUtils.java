package ims.framework.utils;

import java.security.SecureRandom;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class StringUtils
{
	public static String cleanHtml(String source)
	{
		if(source == null || source.trim().length() == 0)
			return source;
		if(!Jsoup.isValid(source, Whitelist.basic()))
			return Jsoup.clean(source, Whitelist.basic());
		return source;
	}
	public static String encodeXML(String source)
	{
		if (source == null) return "";
		
		char[] ar = source.toCharArray();
		StringBuffer sb = new StringBuffer(source.length() * 2);
		for (int i = 0; i < ar.length; ++i)
		{
			switch (ar[i])
			{
				case '©':
					sb.append("&#169;");
					break;
				case '®':
					sb.append("&#174;");
					break;	
				case '£':
					sb.append("&#163;");
					break;			
				case '€':
					sb.append("&#8364;");
					break;					
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '\'':
					sb.append("&apos;");
					break;
				case '\"':
					sb.append("&quot;");
					break;
				case '\t':
					sb.append("&#x9;");
					break;
				case '\r':
					sb.append("&#xD;");
					break;
				case '\n':
					sb.append("&#xA;");
					break;
				case '°':
					sb.append("&#xb0;");
					break;
				default:
					int b = ar[i];
					if (b > 31 && b < 220) // Ignore symbols, that won't be displayed anyway
						sb.append(ar[i]);
			}
		}
		return sb.toString();
	}
	
	public static String encodeHtml(String source)
	{
		if (source == null) return "";
		
		char[] ar = source.toCharArray();
		StringBuffer sb = new StringBuffer(source.length() * 2);
		for (int i = 0; i < ar.length; ++i)
		{
			switch (ar[i])
			{
				case '©':
					sb.append("&#169;");
					break;
				case '®':
					sb.append("&#174;");
					break;	
				case '£':
					sb.append("&#163;");
					break;			
				case '€':
					sb.append("&#8364;");
					break;					
				case '&':
					sb.append("&amp;");
					break;
				case '\'':
					sb.append("&apos;");
					break;
				case '\"':
					sb.append("&quot;");
					break;
				case '\t':
					sb.append("&#x9;");
					break;
				case '\r':
					sb.append("&#xD;");
					break;
				case '°':
					sb.append("&#xb0;");
					break;
				default:
					int b = ar[i];
					if (b > 31 && b < 220) // Ignore symbols, that won't be displayed anyway
						sb.append(ar[i]);
			}
		}
		return sb.toString();
	}
	
	public static String[] splitString(String source, String token)
	{
		StringTokenizer st = new  StringTokenizer(source, token);
		
		int nCount = st.countTokens();
		
		if(nCount == 0)
			return null;
		
		String[] result = new String[nCount];
		
		for(int i = 0; i < nCount; i++)
			result[i] = st.nextToken();
		
		return result;
	}
	
	public static String generateUniqueString()
    {    
        String str = "";
        
        try
        {
            //Get Random Segment
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
            str += Integer.toHexString(prng.nextInt());
            while (str.length () < 8)
            {
                str = '0' + str;
            }
            
            //Get CurrentTimeMillis() segment
            str += Long.toHexString(System.currentTimeMillis());
            while (str.length () < 12)
            {
                str = '0' + str;
            }

            //Get Random Segment
            SecureRandom secondPrng = SecureRandom.getInstance("SHA1PRNG");
            str += Integer.toHexString(secondPrng.nextInt());
            while (str.length () < 8)
            {
                str = '0' + str;
            }
             
            //Get Third Random Segment
            byte bytes[] = new byte[16];
            SecureRandom thirdPrng = SecureRandom.getInstance("SHA1PRNG");
            thirdPrng.nextBytes(bytes);
            str += Integer.toHexString(thirdPrng.nextInt());
            while (str.length () < 8)
            {
                str = '0' + str;
            }
        }
        catch(java.security.NoSuchAlgorithmException ex)
        {
            ex.getMessage();
        }
        
        return str;
    }

	public static String encodeBase64(String text)
	{		
		if(text == null)
			return null;
		return Base64.encodeBytes(text.getBytes());
	}
	public static String decodeBase64(String text)
	{		
		if(text == null)
			return null;
		
		return new String(Base64.decode(text));
	}	
}
