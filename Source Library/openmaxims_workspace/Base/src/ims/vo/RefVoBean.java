package ims.vo;

public class RefVoBean
{
	private int id;
	private int version;
	
	public RefVoBean(int refId, int refVersion)
	{
		this.id = refId;
		this.version = refVersion;
	}

	public RefVoBean(Integer refId, int refVersion)
	{
		if (refId != null)
			this.id = refId.intValue();
		this.version = refVersion;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}
}
