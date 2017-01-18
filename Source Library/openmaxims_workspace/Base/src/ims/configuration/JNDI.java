package ims.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ims.framework.interfaces.ISchedulerServlet;
import ims.framework.interfaces.IUploadServlet;

public class JNDI
{
	public static ISchedulerServlet getTaskSchedulerServlet() throws NamingException
	{
		return (ISchedulerServlet)getInstance("QuartzServlet");
	}
	public static void setTaskSchedulerServlet(ISchedulerServlet instance) throws NamingException
	{
		setInstance("QuartzServlet", instance);
	}
	
	public static IUploadServlet getUploadedServlet() throws NamingException
	{
		return (IUploadServlet)getInstance("Upload");
	}
	public static void setUploadServlet(IUploadServlet instance) throws NamingException
	{
		setInstance("Upload", instance);
	}
	
	private static Object getInstance(String name) throws NamingException
	{
		return getContext().getEnvironment().get(name);
	}
	private static void setInstance(String name, Object instance) throws NamingException
	{
		getContext().addToEnvironment(name, instance);
	}
	private static Context getContext() throws NamingException
	{
		return new InitialContext();
	}
}
