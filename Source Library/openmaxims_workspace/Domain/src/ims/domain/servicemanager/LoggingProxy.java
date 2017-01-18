/**
 * Logging proxy
 * Logs all method entries and exits into the Logger classname
 *  If the log level is set to info records entry into the method
 *  if log level is debug records entry and parameter values exit and exit via exception
 */
package ims.domain.servicemanager;

import ims.domain.impl.DomainImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.Serializable;

import org.apache.log4j.Level;



class LoggingProxy implements ProxyInvocationHandler ,Serializable
{
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 256;
    private Object monitoredObj;// underlying object
    private Class clazz;
    private final DomainImpl domainImpl;
    private Level logLevel;
    
       
      LoggingProxy(Object monitoredObj,Class clazz,DomainImpl domainImpl)
      {
          this.monitoredObj = monitoredObj;
          this.clazz=clazz;
          this.domainImpl = domainImpl;
          logLevel = ims.utils.Logging.getLogger(clazz).getLevel();
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



      public Object invoke(Object proxy, Method method,  Object[] args) throws Throwable 
      {
    	  final String methodName = method.getName();
    	  if (logLevel==Level.INFO)
    	  {
    		  entryinfo( methodName );
    	  }
    	  if (logLevel==Level.DEBUG)
    	  {
    		  entrydebug( methodName, args );
    	  }
          try 
          {
        	  Object result =  method.invoke(monitoredObj, args);// executes underlying interfaces method;
        	  if (logLevel==Level.DEBUG)
        	  {
	        	if( Void.TYPE.equals( method.getReturnType() ) )
	        	  {
	        		  voidExit( methodName );
	        	  }
	        	  else
	        	  {
	        		  exit( methodName, result );
	        	  }
        	  }
	          return result;
          }
        catch (InvocationTargetException e)
        {
        	exception(methodName,e);
        	throw e.getTargetException();
        }
        catch( Throwable t )
        {
            exception( methodName, t );
            throw t;
        } 
     }

 	 // Return Exception information as a row (1 dim array)
 	 String getExceptionTrace(Throwable exception) {
 		 
 		 // each line of the stack trace will be returned in the array.
 		 StackTraceElement elements[] = exception.getStackTrace();
 		 StringBuffer trace=new StringBuffer().append(exception).append("\n");
 		 
 		 for (int i=0; i<elements.length; i++) 
 		 {
 			 trace.append(elements[i]).append("\n");
 		 }

          return trace.toString(); 
 	 } 
 	 
 	   private void entrydebug( String methodName, Object[] args )
 	   {
 		   StringBuffer buffer = new StringBuffer( BUFFER_SIZE );
 	       buffer.append( "BEGIN " );
 	       buffer.append( methodName );
 	       buffer.append( "(" );
 	       if(null != args)
 	       {
	 	       int count = args.length;
	 	       for( int i = 0; i < count; i++ )
	 	       {
	 	           Object arg = args[i];
	 	           if( i > 0 )
	 	           {
	 	               buffer.append( ", " );
	 	           }
	 	           convert( buffer, arg );
	 	       }
 	       }
 	       buffer.append( ")" );
 	      ims.utils.Logging.getLogger(clazz).debug( buffer.toString() );
 	   }
 	   private void entryinfo( String methodName)
 	   {
 		   StringBuffer buffer = new StringBuffer( BUFFER_SIZE );
 	       buffer.append( "BEGIN " );
 	       buffer.append( methodName );
 	      ims.utils.Logging.getLogger(clazz).info( buffer.toString() );
 	   }

 	   private void convert( StringBuffer buffer, Object input )
 	   {
 	       if( input == null )
 	       {
 	           buffer.append( "<null>" );
 	           return;
 	       }

 	       if( !( input instanceof Object[] ) )
 	       {
 	           buffer.append( input.toString() );
 	           return;
 	       }
 	       buffer.append( "(" );
 	       buffer.append( getJavaClassName( input.getClass() ) );
 	       buffer.append( "){" );
 	       Object[] array = ( Object[] ) input;
 	       int count = array.length;
 	       for( int i = 0; i < count; i++ )
 	       {
 	           if( i > 0 )
 	           {
 	               buffer.append( ", " );
 	           }

 	           convert( buffer, array[i] );
 	       }
 	       buffer.append( "}" );
 	   }

 	   public static String getJavaClassName( Class clazz )
 	   {
 	       if( clazz.isArray() )
 	       {
 	           return getJavaClassName( clazz.getComponentType() ) + "[]";
 	       }
 	       return clazz.getName();
 	   }
 	  private void exit( String methodName, Object result )
 	    {
 	        StringBuffer buffer = new StringBuffer( BUFFER_SIZE );
 	        buffer.append( "END " );
 	        buffer.append( methodName );
 	        buffer.append( "() [" );
 	        convert( buffer, result );
 	        buffer.append( "]" );
 	       ims.utils.Logging.getLogger(clazz).debug( buffer.toString() );
 	    }

 	    private void voidExit( String methodName )
 	    {
 	        StringBuffer buffer = new StringBuffer( BUFFER_SIZE );
 	        buffer.append( "END " );
 	        buffer.append( methodName );
 	        buffer.append( "()" );
 	       ims.utils.Logging.getLogger(clazz).debug( buffer.toString() );
 	    }
 	    
 	   private void exception( String methodName, Throwable t )
 	    {
 	        StringBuffer buffer = new StringBuffer( BUFFER_SIZE );
 	        buffer.append( "EXCEPTION " );
 	        buffer.append( methodName );
 	        buffer.append( "() -- " );
 	        buffer.append( t.getClass().getName() );
 	       ims.utils.Logging.getLogger(clazz).debug( buffer.toString(), t );
 	    }

}

