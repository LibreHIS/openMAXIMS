package ims.crypto;

/**
 * @author mmihalec
 */
public class Encryption 
{
	public static String encrypt(String textSource, EncryptionType encryptionType)
	{
		if(encryptionType.equals(EncryptionType.PRIME))
			return encryptTextNORMAL(textSource);
		if(encryptionType.equals(EncryptionType.UNIX))
			return encryptTextHAMMERSMITH(textSource);
		return "";
	}
	
	private static String encryptTextNORMAL(String textSource)
	{
		char[] textEncrypted = textSource.toCharArray();
		
		int[] f = new int[] {10499, 10501, 10513, 10529, 10531, 10559, 10567, 10589, 10597, 1};
		long pwtot = 0;
		long epw = 0;

		for(int i = 0; i < 10 && i < textSource.length(); i++)
			pwtot += textEncrypted[i] * f[i];

		epw = pwtot % 1000003;
		return String.valueOf(epw);
	}
	
	private static String encryptTextHAMMERSMITH(String textSource)
	{
		int maxLength = textSource.length();
		char[] text = textSource.toCharArray();
		byte[] source = new byte[maxLength];
		byte[] result = new byte[maxLength];
		String enctypted = "";

		for(int x = 0; x < maxLength; x++)
		{
			source[x] = (byte)text[x];
		}
		
		UnixEncryption enc = new UnixEncryption();
		enc.pc1Encrypt(result, source);

		byte[] finalResult = new byte[maxLength];
		for(int x = 0; x < maxLength; x++)
		{
			finalResult[x] = result[x];
		}
		
		finalResult = enc.bin2asc(finalResult);

		for(int x = 0; x < finalResult.length; x++)
		{
			enctypted += (char)finalResult[x];
		}

		return enctypted;
	}
}
