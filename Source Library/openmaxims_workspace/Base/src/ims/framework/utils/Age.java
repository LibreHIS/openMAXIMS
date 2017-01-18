package ims.framework.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Age implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int years;
	private int months;
	private int days;
		
	public Age(int years, int months, int days)
	{
		this.years = years;
		this.months = months;
		this.days = days;
	}
	public Age(Date dateOfBirth)
	{
		parse(dateOfBirth, null);
	}
	public Age(PartialDate dateOfBirth)
	{
		if(dateOfBirth == null)
			throw new RuntimeException("Invalid birth date");
		
		parse(new Date(dateOfBirth.getYear(), dateOfBirth.getMonth() == null ? 1 : dateOfBirth.getMonth(), dateOfBirth.getDay() == null ? 1 : dateOfBirth.getDay()), null);
	}
	public Age(PartialDate dateOfBirth, Date dateOfDeath)
	{
		if(dateOfBirth == null)
			throw new RuntimeException("Invalid birth date");
		
		parse(new Date(dateOfBirth.getYear(), dateOfBirth.getMonth() == null ? 1 : dateOfBirth.getMonth(), dateOfBirth.getDay() == null ? 1 : dateOfBirth.getDay()), dateOfDeath);
	}
	public Age(PartialDate dateOfBirth, PartialDate dateOfDeath)
	{
		if(dateOfBirth == null)
			throw new RuntimeException("Invalid birth date");
		
		Date dateOfDeathDate = null;
		if(dateOfDeath != null)
			dateOfDeathDate = new Date(dateOfDeath.getYear(), dateOfDeath.getMonth() == null ? 1 : dateOfDeath.getMonth(), dateOfDeath.getDay() == null ? 1 : dateOfDeath.getDay());
		
		parse(new Date(dateOfBirth.getYear(), dateOfBirth.getMonth() == null ? 1 : dateOfBirth.getMonth(), dateOfBirth.getDay() == null ? 1 : dateOfBirth.getDay()), dateOfDeathDate);
	}
	public Age(Date dateOfBirth, Date dateofDeath)
	{
		parse(dateOfBirth, dateofDeath);
	}
	private void parse(Date dateOfBirth, Date dateofDeath)
	{
		if(dateOfBirth == null)
			throw new RuntimeException("Invalid birth date");
		
		Date endDate = dateofDeath;
		if(endDate == null)
			endDate = new Date();
		
		java.util.Date pFrom = dateOfBirth.getDate();
		java.util.Date pTo = endDate.getDate();
			
		if ( pTo.after( pFrom ) )
		{
	        Calendar lFrom = new GregorianCalendar();
	        lFrom.setTime( pFrom );
	        Calendar lTo = new GregorianCalendar();
	        lTo.setTime( pTo );
	 
	        int lFromYear = lFrom.get( Calendar.YEAR );
	        int lFromMonth = lFrom.get( Calendar.MONTH );
	        int lFromDay = lFrom.get( Calendar.DAY_OF_MONTH );
	 
	        int lToYear = lTo.get( Calendar.YEAR );
	        int lToMonth = lTo.get( Calendar.MONTH );
	        int lToDay = lTo.get( Calendar.DAY_OF_MONTH );
	 
	        int lYearDiff = lToYear - lFromYear;
	        int lMonthDiff = lToMonth - lFromMonth;
	        int lDayDiff = lToDay - lFromDay;
	 
	        if ( lDayDiff < 0 )
	        {
	            lMonthDiff--;
	            Calendar lTemp = new GregorianCalendar();
	            lTemp.setTime( pTo );
	            lTemp.add( Calendar.MONTH, -1 );
	            lDayDiff = lTemp.getActualMaximum( Calendar.DAY_OF_MONTH ) + lDayDiff;
	        }
	 
	        if ( lMonthDiff < 0 )
	        {
	            lYearDiff--;
	            lMonthDiff = 12 + lMonthDiff;
	        }
	        
	        years = lYearDiff;
	        months = lMonthDiff;
	        days = lDayDiff;	        
		}
	}
	
	public int getYears()
	{
		return years;
	}
	public int getMonths()
	{
		return months;
	}
	public int getDays()
	{
		return days;
	}
	
	public String toString()
	{
		if(years > 0)
		{
			return String.valueOf(years);			
		}
		
		if(months > 0)
		{
			return String.valueOf(months) + " month" + (months == 1 ? "" : "s");
		}
		
		if(days >= 0)
		{
			return String.valueOf(days) + " day" + (days == 1 ? "" : "s");
		}
		
		return "-";
	}
	
    public String toPaediatricString()
    { 
          if(years > 0) 
          { 
                if(years < 5 && months > 0) 
                      return String.valueOf(years) + " year" + (years == 1 ? " " : "s ") + String.valueOf(months) + " month" + (months == 1 ? "" : "s"); 

                if(years < 5 && months == 0) 
                      return String.valueOf(years) + " year" + (years == 1 ? " " : "s "); 

                return String.valueOf(years); 
          } 

          if(months > 0) 
          { 
                return String.valueOf(months) + " month" + (months == 1 ? "" : "s"); 
          } 

          if(days >= 0) 
          { 
                if (days == 0) 
                      return String.valueOf("Under 1 Day"); 

                return String.valueOf(days) + " day" + (days == 1 ? "" : "s"); 
          } 

          return "-"; 
    } 
}
