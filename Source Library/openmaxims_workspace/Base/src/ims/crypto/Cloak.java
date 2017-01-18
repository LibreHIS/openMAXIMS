package ims.crypto;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Cloak 
{
	private static Cipher cipher = null;
	static
	{
		try 
		{
			cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static byte[] decryptData(byte[] val, SecretKey key)
	{
		byte[] dat = new byte[val.length];
		try
		{
			cipher.init(Cipher.DECRYPT_MODE, key);
			dat = cipher.doFinal(val);
		}
		catch (Exception e) 
		{
			return val;
		}
		return dat;		
	}
	
	public static byte[] encryptData(byte[] val, SecretKey key)
	{
		byte[] dat = new byte[val.length];
		try
		{
			cipher.init(Cipher.ENCRYPT_MODE, key);
			dat = cipher.doFinal(val);
		}
		catch (Exception e) 
		{
			return val;
		}
		return dat;				
	}

	public static String decryptString(String val, SecretKey key)
	{
		byte[] dat = val.getBytes();
		dat = AscBin.asc2bin(dat);
		dat = decryptData(dat, key);
		dat = unzip(dat);
		return new String(dat);
	}
	
	public static String encryptString(String val, SecretKey key)
	{
		byte[] dat = val.getBytes();
		dat = zip(dat);				
		dat = encryptData(dat, key);
		dat = AscBin.bin2asc(dat);
		return new String(dat);
	}
	
	public static String zipString(String val)
	{
		byte[] dat = val.getBytes();
		dat = zip(dat);				
		dat = AscBin.bin2asc(dat);
		return new String(dat);		
	}

	public static String unzipString(String val)
	{
		byte[] dat = val.getBytes();
		dat = AscBin.asc2bin(dat);
		dat = unzip(dat);
		return new String(dat);		
	}

	public static byte[] unzip(byte[] dat) 
	{
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(dat.length);
	    Inflater inf = new Inflater();
	    inf.setInput(dat);

	    byte[] buf = new byte[1024];
	    while (!inf.finished()) 
	    {
            int count;
			try 
			{
				count = inf.inflate(buf);
			}
			catch (DataFormatException e) 
			{
				return dat;
			}
            bos.write(buf, 0, count);
	    }
	    return bos.toByteArray();
	}

	public static byte[] zip(byte[] dat) 
	{
	    Deflater compressor = new Deflater();
	    compressor.setLevel(Deflater.BEST_COMPRESSION);
	    
	    compressor.setInput(dat);
	    compressor.finish();
	    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(dat.length);
	    
	    byte[] buf = new byte[1024];
	    while (!compressor.finished()) 
	    {
	        int count = compressor.deflate(buf);
	        bos.write(buf, 0, count);
	    }
	    return bos.toByteArray();
	}

}
