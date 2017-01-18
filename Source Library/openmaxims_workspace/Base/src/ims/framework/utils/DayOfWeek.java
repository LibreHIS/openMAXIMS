package ims.framework.utils;

public class DayOfWeek
{
    public static final DayOfWeek MONDAY = new DayOfWeek(1);
    public static final DayOfWeek TUESDAY = new DayOfWeek(2);
    public static final DayOfWeek WEDNESDAY = new DayOfWeek(3);
    public static final DayOfWeek THURSDAY = new DayOfWeek(4);
    public static final DayOfWeek FRIDAY = new DayOfWeek(5);
    public static final DayOfWeek SATURDAY = new DayOfWeek(6);
    public static final DayOfWeek SUNDAY = new DayOfWeek(7);
  
    public String toString()
	{
    	return String.valueOf(this.value);
    }
    
    private DayOfWeek(int value)
    {
        this.value = value;
    }
    private int value;
}
