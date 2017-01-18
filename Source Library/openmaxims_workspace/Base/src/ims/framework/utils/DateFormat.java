package ims.framework.utils;

public class DateFormat
{
    public static final DateFormat ISO = new DateFormat("yyyyMMdd");
    public static final DateFormat STANDARD = new DateFormat("dd/MM/yyyy");
    public static final DateFormat STANDARD_NONCENTURY = new DateFormat("dd/MM/yy");
    public static final DateFormat AMERICAN = new DateFormat("MM/dd/yyyy");
    public static final DateFormat RUSSIAN = new DateFormat("dd.MM.yyyy"); // :)
    public static final DateFormat MEDIUM = new DateFormat("dd MMM yyyy");
    public static final DateFormat LONG = new DateFormat("dd MMMM yyyy");
    public static final DateFormat RULES = new DateFormat("dd-MMM-yyyy");
    
    private DateFormat(String value)
    {
    	this.id = value;
    }

    public String toString()
    {
        return this.id;
    }
    private String id;
}
