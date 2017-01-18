/*
 * Created on 24-Aug-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.utils;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DateTimeFormat 
{
	public static final DateTimeFormat ISO = new DateTimeFormat("yyyyMMddHHmm");
	public static final DateTimeFormat STANDARD = new DateTimeFormat("dd/MM/yyyy HH:mm");
	public static final DateTimeFormat STANDARD_SECS = new DateTimeFormat("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormat AMERICAN = new DateTimeFormat("MM/dd/yyyy HH:mm");
	public static final DateTimeFormat RUSSIAN = new DateTimeFormat("dd.MM.yyyy HH:mm"); // :)
	public static final DateTimeFormat MEDIUM = new DateTimeFormat("dd MMM yyyy HH:mm");
	public static final DateTimeFormat LONG = new DateTimeFormat("dd MMMM yyyy HH:mm");
	public static final DateTimeFormat MILLI = new DateTimeFormat("yyyyMMddHHmmssSSS");
	public static final DateTimeFormat ISO_SECS = new DateTimeFormat("yyyyMMddHHmmss");
    
	private DateTimeFormat(String value)
	{
		this.id = value;
	}

	public String toString()
	{
		return this.id;
	}
	private String id;

}
