package ims.domain.servicemanager;

import ims.domain.impl.DomainImpl;

import java.lang.reflect.Proxy;

import java.util.Collection;
import java.util.Set;
import java.util.List;

import java.util.HashSet;
import java.util.ArrayList;

public class ProxyFactory
{
    private static final Class[] CLASS_ARRAY=new Class[0];
    private static String[] exceptionHeader={"ID", "StartTime", "ExceptionStackTrace", "MethodName",  };
    private static BufferList exceptionBuffer=new BufferList(exceptionHeader);
    private static boolean isLoggingEnabled = true;
    private static boolean isMonEnabled = true;
    private static Params monParams=new Params();
    
	private static boolean isLoggingEnabled()
	{
		return isLoggingEnabled;
	}
	
	private static boolean isMonEnabled()
	{
		return isMonEnabled;
	}
	
    public static Object monitor(Object object,DomainImpl domainImpl) {
        if (!isMonEnabled() || object==null) // if not enabled return the original object unchanged, not the proxy
        {
          return object;
        }
        else
        {
          return monitorNoCheck (object, getInterfaces(object.getClass()),domainImpl);// proxy will implement ALL interfaces of this class
        }
    }

    private static Object monitorNoCheck(Object object, Class[] interfaces,DomainImpl domainImpl) 
    {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                interfaces,// proxy will implement ALL interfaces in array
                new MonProxy(object, monParams, domainImpl)
         );
        
    }    

	
	
    /** By passing any interface to the log method, all public method calls and exceptions
     *  will be logged. It will be a runtime error if the Object passed does not implement an interface. Note that
     *  only interfaces implemented directly by the passed in Object are logged.  Should you want to cast to an interface
     *  implemented by a base class to the passed in Object you should call one of the more explicit monitor(..) methods
     *  
     *    Sample call:
     *     MyInterface myProxyObject=(MyInterface) LoggingProxyFactory.log(myObject);
     */

   public static Object log(Object object,Class clazz,DomainImpl domainImpl) 
   {
       if (!isLoggingEnabled() || object==null) // if not enabled return the original object unchanged, not the proxy
       {
         return object;
       }
       else
       {
         return logNoCheck (object, getInterfaces(object.getClass()),clazz,domainImpl);// proxy will implement ALL interfaces of this class
       }
   }
   
   private static Object logNoCheck(Object object, Class[] interfaces,Class clazz,DomainImpl domainImpl) 
   {
       return Proxy.newProxyInstance(
               object.getClass().getClassLoader(),
               interfaces,// proxy will implement ALL interfaces in array
               new LoggingProxy(object,clazz,domainImpl)
        );
       
   }    

   
   
   /** For every class in the Object/Interface heirarchy find its implemented interfaces.  All interfaces this class 
    * implements are returned.  Either the Class of an Object or interfaces Class may be passed to 
    *  this method.  The difference between this method and the method 'Class.getInterfaces()' is 
    *  that this one returns ALL implemented interfaces whereas that one only returns interfaces that 
    *  are directly implemented by the Class.  
    */
   public static Class[] getInterfaces(Class cls) 
   {
       if (cls==null)
       {
           return null;
       }
       
       Set interfaceHeirarchy=new HashSet();
       // Get class heirarchy and loop through it and its interfaces adding each interface to the passed
       // in Set.
       Class[] objTree=getClassHeirarchy(cls);
       for (int i=0;i<objTree.length;i++)
       {
          getInterfaces(objTree[i], interfaceHeirarchy);
       }
           
       return toClassArray(interfaceHeirarchy);
       
   }
   
   /** Convert a Collection to a Class[] array */
   @SuppressWarnings("unchecked")
private static Class[] toClassArray(Collection coll) 
   {
       if (coll==null || coll.size()==0)
       {
          return null;
       }
       else
       { 
         return (Class[]) coll.toArray(CLASS_ARRAY);// convert the Set to Class[]
       }
   }

   /*
    *  Returns the inheritance heirarchy of the specified Class that was passed in.  For example if there
    *  are three levels such as Base1, Base2, Base3 then it would return an array of these 3 Class elements.
    */

   @SuppressWarnings("unchecked")
private static Class[] getClassHeirarchy(Class cls) 
   {
       if (cls==null) 
       {
         return null;
       }
       
       // classes will contain the inheritance chain of Objects.
       List classes=new ArrayList();
       // Loop through super classes until null is found which indicates there are no more Objects
       // in the inheritance chain.
       while (cls!=null)
       {
         classes.add(cls);
         cls = cls.getSuperclass();
       } 
           
       return toClassArray(classes);
   }

   /** A recursive method called for each Object or interface in the heirarchy.  All interfaces are added into the 
    * passed in Set.  When either a Class is null, or it implements no other interfaces the recursive method bubbles
    * up the chain.
    * 
    */
   @SuppressWarnings("unchecked")
private static void getInterfaces(Class cls, Set heirarchy) 
   {
       if (cls != null) 
       {
         Class[] heir = cls.getInterfaces();// gets immediate implemented interfaces of passed in Class or interface
         int len=(heir==null) ? 0 : heir.length;
         for (int i = 0; i < len; i++) 
         {
           heirarchy.add(heir[i]);
           getInterfaces(heir[i],heirarchy);// recursive
         }
        }
        
    }         
 
 // Standard and Exception monitoring methods

/** 
  * Get the number of Exceptions that can be stored in the buffer before the oldest entries must
  * be removed.
  * 
  */
 public static int getExceptionBufferSize() 
 {
   return exceptionBuffer.getBufferSize();
 }
 
 /** 
  * Set the number of Exceptions that can be stored in the buffer before the oldest entries must
  * be removed.  A value of 0 will disable collecting of Exceptions in the buffer. 
  * 
  */
 
 public static void setExceptionBufferSize(int exceptionBufferSize) 
 {
	  exceptionBuffer.setBufferSize(exceptionBufferSize);
 }
 
 /** 
  * Remove all Exceptions from the buffer.
  *
  */
 public static void resetExceptionDetail() 
 {
	  exceptionBuffer.reset();  
 }
 

}
