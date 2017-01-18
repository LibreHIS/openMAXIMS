package ims.framework.utils;

public class TimeFormat
{
	public static final TimeFormat DEFAULT = new TimeFormat(1);
	public static final TimeFormat FLAT4 = new TimeFormat(2);
	public static final TimeFormat FLAT6 = new TimeFormat(3);
	public static final TimeFormat FSEC = new TimeFormat(4);
	
	private TimeFormat(int id)
	{
		this.id = id;
	}
	protected int id;
}