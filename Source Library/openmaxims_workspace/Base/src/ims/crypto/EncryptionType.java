package ims.crypto;

public class EncryptionType
{
	public static final EncryptionType HASH = new EncryptionType("HASH");
	public static final EncryptionType PRIME = new EncryptionType("PRIME");
	public static final EncryptionType UNIX = new EncryptionType("UNIX");
	public static final EncryptionType CLOAK = new EncryptionType("CLOAK");
	public static final EncryptionType NONE = new EncryptionType("NONE");

	private String encName;
	
	private EncryptionType(String name)
	{
		encName = name;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof EncryptionType))
			return false;
		EncryptionType enct = (EncryptionType)o;
		return enct.encName.equals(this.encName);
	}

	public int hashCode()
	{
		return this.encName.hashCode();
	}
	
	public static String[] getAllNames()
	{
		return new String[]{HASH.encName, PRIME.encName, UNIX.encName, CLOAK.encName, NONE.encName};
	}
	
	public static EncryptionType getOne(String name)
	{
		if (name.equals(HASH.encName))
			return HASH;
		else if (name.equals(PRIME.encName))
			return PRIME;
		else if (name.equals(UNIX.encName))
			return UNIX;
		else if (name.equals(CLOAK.encName))
			return CLOAK;
		else if (name.equals(NONE.encName))
			return NONE;
		else 
			return null;
	}
}
