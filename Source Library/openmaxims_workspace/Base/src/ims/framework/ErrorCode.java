package ims.framework;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ErrorCode
{
	public static final ErrorCode ACTIVE_TEMPLATE_USING_INV  = new ErrorCode("ACTIVE_TEMPLATE_USING_INV", "The following template(s) are using this Investigation, please remove the item from each template before making the Investigation inactive");
	public static final ErrorCode ACTIVE_PROFILE_USING_INV  = new ErrorCode("ACTIVE_PROFILE_USING_INV", "The following profile(s) are using this Investigation, please remove the item from each profile before making the Investigation inactive");

	private String errorName;
	private String errorMessage;
	
	public ErrorCode (String name, String message)
	{
		this.errorName = name;		
		this.errorMessage = message;
	}

	public String getMessage()
	{
		return errorMessage;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof ErrorCode))
			return false;
		
		ErrorCode err = (ErrorCode)o;
		
		return err.errorName.equals(this.errorName);
	}
	
	public int hashCode()
	{
		return this.errorName.hashCode();
	}
	
	@SuppressWarnings("unchecked")
	public static ErrorCode[] getAllErrors()
	{
		ArrayList errs = new ArrayList();
		Field[] flds = ErrorCode.class.getFields();
		for (int i = 0; i < flds.length; i++)
		{
			Class c = flds[i].getType();
			if (ErrorCode.class.isAssignableFrom(c))
			{
				try 
				{
					errs.add(flds[i].get(null));
				}
				catch (IllegalArgumentException e) 
				{
					return null;
				}
				catch (IllegalAccessException e) 
				{
					return null;
				}
			}
	 	}
		ErrorCode[] errArray = new ErrorCode[errs.size()];
		errs.toArray(errArray);
		return errArray;		
	}
	
	public static ErrorCode getErrorCode(String name)
	{
		ErrorCode[] errs = getAllErrors();
		String upName = name.toUpperCase();
		for (int i = 0; i < errs.length; i++)
		{
			if (errs[i].errorName.equals(upName))
				return errs[i];
		}
		return null;
	}
}
