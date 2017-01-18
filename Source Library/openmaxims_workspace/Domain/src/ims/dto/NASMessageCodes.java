package ims.dto;

/**
 * @author mmihalec
 */
public class NASMessageCodes 
{
	public static final int	SOCK_PING						= 99000;
	public static final int	SOCK_CONNECT					= 99001;
	public static final int	SOCK_DISCONNECT 				= 99002;
	public static final int	SOCK_MESSAGE					= 99003;
	public static final int	LEN_CHARS						= 9;		

	public static final String SEPARATOR       				= String.valueOf((char)0x1e);	
	public static final String TERMINATOR       			= String.valueOf((char)31);
	public static final String PAIRSEPARATOR       			= String.valueOf((char)28);
	public static final String ATTRIBUTEVALUESEPARATOR     	= String.valueOf((char)29);
}
