package ims.domain.servicemanager;


import ims.domain.impl.DomainImpl;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.*;
import java.io.Serializable;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;



class MonProxy implements ProxyInvocationHandler, Serializable {

	private static final long serialVersionUID = 1L;
	private Object monitoredObj;// underlying object
    private String className;// class name of monitored object
    private final DomainImpl domainImpl;
    Params params;// parameters associated with monitoring
       
      MonProxy(Object monitoredObj, Params params,DomainImpl domainImpl)
      {
          this.monitoredObj = monitoredObj;
          this.params=params;
          this.domainImpl = domainImpl;
          this.className="(class=" + monitoredObj.getClass().getName() + ")";       
      }
 
    /** Return the underlying object being Monitored */
    public Object getMonitoredObject()
    {
    	 return monitoredObj;
    }
     
	public DomainImpl getDomainImpl()
	{
		return this.domainImpl;
	}



     /** Method that monitors method invocations of the proxied interface.  This method is not explicitly called.  
      *  The MonProxy class automatically calls it. 
      *  
      *  
      */
      public Object invoke(Object proxy, Method method,  Object[] args) throws Throwable
      {

    	Monitor mon=null;
        boolean isExceptionSummaryEnabled=(params.isExceptionSummaryEnabled && params.isEnabled);// track jamon stats for Exceptions?
        boolean isExceptionDetailEnabled=(params.isExceptionDetailEnabled && params.isEnabled);// save detailed stack trace in the exception buffer?
        if (params.isInterfaceEnabled && params.isEnabled)
        {
        	mon=MonitorFactory.start(new StringBuffer().append(method.getDeclaringClass().getName()).append(".").append(method.getName()).toString());
        }
        
        try
        {
           return method.invoke(monitoredObj, args);// executes underlying interfaces method;
        }
        catch (InvocationTargetException e)
        {
          if (isExceptionSummaryEnabled || isExceptionDetailEnabled)
          {
            String sqlMessage="";
            Throwable rootCause=e.getCause();
            
            // Add special info if it is a SQLException
            if (rootCause instanceof SQLException  && isExceptionSummaryEnabled) 
            {
              SQLException sqlException=(SQLException) rootCause;
              sqlMessage=",ErrorCode="+sqlException.getErrorCode()+",SQLState="+sqlException.getSQLState();
            }
               
            if (isExceptionSummaryEnabled)
            {
              // Add jamon entries for Exceptions
              MonitorFactory.add("Exception: InvocationTargetException","Exception",1); //counts total exceptions
              MonitorFactory.add("Exception: Root cause exception=" + rootCause.getClass().getName() + sqlMessage,"Exception",1); // Message for the exception
              MonitorFactory.add("Exception: " + className + " Exception: " + method.toString(),"Exception",1); // Exception and method that threw it.
            }
            
            
            // Add stack trace to buffer if it is enabled.
            if (isExceptionDetailEnabled)
            {
               params.exceptionBuffer.addRow(new Object[] {new Long(++params.exceptionID), new Date(), getExceptionTrace(rootCause), method.toString(), });
            }
          } // end if (enabled)           
          
          throw e.getCause();

        } finally
        {
          if (mon!=null)
          {
           mon.stop();
          }
        }
      }

 	 // Return Exception information as a row (1 dim array)
 	 String getExceptionTrace(Throwable exception) 
 	 {
 		 
 		 // each line of the stack trace will be returned in the array.
 		 StackTraceElement elements[] = exception.getStackTrace();
 		 StringBuffer trace=new StringBuffer().append(exception).append("\n");
 		 
 		 for (int i=0; i<elements.length; i++)
 		 {
 			 trace.append(elements[i]).append("\n");
 		 }

          return trace.toString(); 
 	 }      

}
