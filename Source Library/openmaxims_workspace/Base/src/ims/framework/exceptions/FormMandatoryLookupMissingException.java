package ims.framework.exceptions;

/**
 * @author mmihalec
 */
public class FormMandatoryLookupMissingException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    public FormMandatoryLookupMissingException() 
	{
	}
	public FormMandatoryLookupMissingException(String s) 
	{
		super(s);
	}	
	public FormMandatoryLookupMissingException(Throwable cause) 
	{
		super(cause);
	}
	public FormMandatoryLookupMissingException(String s, Throwable cause) 
	{
		super(s, cause);	
	}	
	public static String getError(String[] errorList) 
	{
		String error = "";
		for(int x = 0; x < errorList.length; x++)
		{
			if(error.length() > 0)
				error += "\r\n";
			error += errorList[x];
		}
		
		error += "\r\n\r\nPlease configure all lookup types before using this page.";
		
		return error;
	}

}
