package ims.dto;

/**
 * @author mmihalec
 */
public class ServerCompatibility 
{
	/*
	 * Compatible with C servers
	 */
	public static final ServerCompatibility C = new ServerCompatibility(0);
	/*
	 * Compatible with JAVA servers
	 */
	public static final ServerCompatibility JAVA = new ServerCompatibility(1);
	
	public ServerCompatibility(int id)
	{
		this.id = id;
	}
	
	protected int id;
}