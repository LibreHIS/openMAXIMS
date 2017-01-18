package ims.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AppServer
{
	public static final String TOMCAT = "TOMCAT";
	public static final String WEBLOGIC = "WEBLOGIC";
	public static final String WEBSPHERE = "WEBSPHERE";
	public static final String SJSAS = "SJSAS";
	
	private static final AppServer TOMCAT_APP = new AppServer(TOMCAT);
	private static final AppServer WEBLOGIC_APP= new AppServer(WEBLOGIC);
	private static final AppServer WEBSPHERE_APP = new AppServer(WEBSPHERE);
	private static final AppServer SJSAS_APP = new AppServer(SJSAS);
	
	private static HashMap<String, AppServer> appservers = new HashMap<String, AppServer>();
	
	static
	{
		appservers.put(TOMCAT, TOMCAT_APP);
		appservers.put(WEBLOGIC, WEBLOGIC_APP);
		appservers.put(WEBSPHERE, WEBSPHERE_APP);
		appservers.put(SJSAS, SJSAS_APP);		
	}
		
	private String appserverName;
	private String jndiClassName = "";
	private String jndiUrl = "";
	private String transFactoryClass = "";
	private String transMgrLookupClass = "";
	private String jndiPrefix;
	
	public static String[] getAllAppServerNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		Iterator<String> iter = appservers.keySet().iterator();
		while (iter.hasNext())
		{
			names.add(iter.next());			
		}
		String[] ret = new String[names.size()];
		names.toArray(ret);
		return ret;
	}
	
	public static AppServer getAppServer(String name)
	{
		return appservers.get(name.toUpperCase());
	}
	
	public AppServer(String name)
	{
		this.appserverName = name;
		if (this.appserverName.equals(WEBLOGIC))
		{
			jndiClassName = "weblogic.jndi.WLInitialContextFactory";
			jndiUrl = "t3://localhost:7001/";
			transFactoryClass = "org.hibernate.transaction.JTATransactionFactory";
			transMgrLookupClass = "org.hibernate.transaction.WeblogicTransactionManagerLookup";
			jndiPrefix = "jdbc/";
		}
		else if (this.appserverName.equals(WEBSPHERE))
		{
			jndiClassName = "com.ibm.websphere.naming.WsnInitialContextFactory";
			jndiUrl = "iiop://localhost:900/";
			transFactoryClass = "org.hibernate.transaction.JTATransactionFactory";
			transMgrLookupClass = "org.hibernate.transaction.WeblogicTransactionManagerLookup";			
			jndiPrefix = "java:comp/env/jdbc/";
		}
		else if (this.appserverName.equals(TOMCAT))
		{
			jndiPrefix = "java:comp/env/jdbc/";			
		}
		else if (this.appserverName.equals(SJSAS))
		{
			jndiPrefix = "jdbc/";
			//transMgrLookupClass = "org.hibernate.transaction.SunONETransactionManagerLookup";
			//transFactoryClass = "org.hibernate.transaction.JTATransactionFactory";
		}
	}
	
	public String getName()
	{
		return appserverName;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof AppServer))
			return false;
		AppServer app = (AppServer)o;
		return app.getName().equals(this.getName());
	}
	
	public int hashCode()
	{
		return this.getName().hashCode();
	}

	public String getJndiClassName()
	{
		return jndiClassName;
	}

	public String getJndiUrl()
	{
		return jndiUrl;
	}

	public String getTransFactoryClass()
	{
		return transFactoryClass;
	}

	public String getTransMgrLookupClass()
	{
		return transMgrLookupClass;
	}

	public String getJndiPrefix()
	{
		return jndiPrefix;
	}

}
