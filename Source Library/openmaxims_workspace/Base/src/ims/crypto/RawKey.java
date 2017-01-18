package ims.crypto;

import javax.crypto.SecretKey;

public class RawKey implements SecretKey
{
	private static final long serialVersionUID = 1L;
	private byte[] raw;
	
	public RawKey(byte[] val)
	{
		this.raw = val;
	}
	public String getAlgorithm() 
	{
		return "DESede";
	}
	public String getFormat() 
	{
		return "RAW";
	}
	public byte[] getEncoded() 
	{
		return this.raw;
	}	
}