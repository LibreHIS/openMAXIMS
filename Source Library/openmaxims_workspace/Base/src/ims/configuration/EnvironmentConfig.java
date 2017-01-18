package ims.configuration;

import ims.configuration.delegates.EnvironmentConfigHibernateUseSQLCommentsValueChanged;
import ims.configuration.delegates.EnvironmentConfigShowSQLValueChanged;
import ims.configuration.delegates.EnvironmentConfigPatientMergeValueChanged;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

// Server.xml example:
// <Environment name="mainDS" type="java.lang.String" value="dev"/>


public class EnvironmentConfig 
{
	private static boolean initialized = false;
	private static Context initialContext = null;
	private static Context environmentContext = null;	
	
	private static List<EnvironmentConfigShowSQLValueChanged> showSQLValueChanged = new ArrayList<EnvironmentConfigShowSQLValueChanged>();
	private static List<EnvironmentConfigHibernateUseSQLCommentsValueChanged> hibernateUseSQLCommentsValueChanged = new ArrayList<EnvironmentConfigHibernateUseSQLCommentsValueChanged>();
	private static List<EnvironmentConfigPatientMergeValueChanged> patientMergeValueChanged = new ArrayList<EnvironmentConfigPatientMergeValueChanged>();
	
	// Default values when the parameter is not found in the configuration file
	// ------------
	private static final String defaultFlagsGroupName = "Default";
	private static final String defaultApplicationServerTypeValue = "TOMCAT";
	private static final String defaultCacheProvider = "EHCACHE"; // Accepted values: "EHCACHE", "TREECACHE", "NONE"
	private static final String defaultLoggingLevel = "WARN"; // Accepted values:  "FATAL", "ERROR", "WARN", "INFO", "DEBUG"
	private static final int defaultResultSetDefaultMax = 200;
	private static final int defaultResultSetTopMax = 1000;
	private static final String defaultMainPasswordEncryption = "HASH"; // Accepted values: "HASH", "PRIME"
	private static final String defaultDtoServerName = "localhost";
	private static final int defaultDtoServerPort = 2100;
	private static final String defaultDtoUserName = "SU";
	private static final String defaultDtoPassword = "icws";
	private static final String defaultDtoPasswordEncryption = "PRIME";
	private static final String defaultImportExportSourceName = "IMS_DEV";
	private static final String defaultCapscanServerName = "";
	private static final String defaultCapscanPool = "PAF";
	private static final String defaultCapscanDistPool = "IMSMAXIMS";
	private static final String defaultEpresServerName = "";
	private static final String defaultEpresProgId = "";
	private static final String defaultEpresDomain = "";
	private static final String defaultEpresDomainUser = "";
	private static final String defaultEpresDomainPassword = "";
	private static final String defaultEpresLoggingLevel = "WARNING";
	// ------------
	
	private static String flagsGroupName = null;
	private static String applicationServerTypeValue = null;
	private static String extractedMainDataSourceName = null;
	private static String extractedSecondDataSourceName = null;
	private static String mainDataSourceName = null;
	private static String secondDataSourceName = null;
	private static String cacheProvider = null; // Accepted values: "EHCACHE", "TREECACHE", "NONE"
	private static String loggingLevel = null; // Accepted values:  "FATAL", "ERROR", "WARN", "INFO", "DEBUG"
	private static String clusterNodes = null;
	private static Integer resultSetDefaultMax = null;
	private static Integer resultSetTopMax = null;
	private static Boolean useSecureAuthentication = null;
	private static Boolean showSQL = null;
	private static Boolean formatSQL = null;
	private static Boolean hibernateEnableStatistics = null;
	private static Boolean hibernateUseSQLComments = null;	
	private static Boolean useServiceManagerMonitoring = null;
	private static Boolean useServiceManagerLogging = null;
	private static Boolean auditEnabled = null;
	private static String mainPasswordEncryption = null; // Accepted values: "HASH", "PRIME"
	private static String importExportSourceName = null;
	private static String fileUploadMountpoint = null;
	private static String baseUri = null;
	private static Boolean caseSensitiveDatabase = null;
	private static String openOfficeHomePath = null;
	private static String userImagesStorePath = null;
	private static String imageServerURL = null;
	private static String applicationURL = null;
	private static String LDAPServer = null;
	private static String LDAPBase = null;
	private static String LDAPDomain = null;
	private static Integer LDAPPort = null;
	private static String LDAPGroup = null;
	private static String LDAP_SECURITY_PROTOCOL = null;
	private static Boolean patientMerge=null; 
	private static Boolean scheduledJobs =null;
	private static String userProviderFlag = null;

	//DTO
	private static String dtoServerName = null;
	private static Integer dtoServerPort = null;
	private static String dtoUsername = null;
	private static String dtoPassword = null;
	private static String dtoPasswordEncryption = null;
	
	//CAPSCAN
	private static String capscanServerName = null;
	private static String capscanPool = null;
	private static String capscanDistPool = null;
	
	//Eprescribing
	private static String epresServerName = null;
	private static String epresProgId = null;
	private static String epresDomain = null;
	private static String epresDomainUser = null;
	private static String epresDomainPassword = null;
	private static String epresLoggingLevel = null;
	
	//QUARTZ
	private static String quartzDbDriver = null;
	private static String quartzDbUrl = null;
	private static String quartzDbUsername = null;
	private static String quartzDbPassword = null;	
		
	private synchronized static void initialize()
	{
		if(initialized == true)
			return;
		
		try
		{
			initialContext = new InitialContext();
			environmentContext = (Context)initialContext.lookup("java:comp/env");
		}
		catch(NamingException e)
		{
			throw new RuntimeException(e);
		}
		
		initialized = true;
	}
	
	public static String getFlagsGroupName() 
	{
		if(flagsGroupName != null)
			return flagsGroupName;
		
		initialize();		
		
		try 
		{
			flagsGroupName = (String)environmentContext.lookup("FlagsGroupName");
		} 
		catch (NamingException e) 
		{
			flagsGroupName = defaultFlagsGroupName;
		}
		
		return flagsGroupName;
	}
	
	public static String getMainDataSourceName() 
	{
		if(mainDataSourceName != null)
			return mainDataSourceName;
		
		initialize();			
		extractDataSources();
		
		try 
		{
			mainDataSourceName = (String)environmentContext.lookup("MainDataSourceName");
		} 
		catch (NamingException e) 
		{
			if(extractedMainDataSourceName != null)
			{
				mainDataSourceName = extractedMainDataSourceName;
			}
			else
			{
				throw new RuntimeException("Unable to get the main datasource name", e);
			}
		}
		
		return mainDataSourceName;
	}	
	public static String getSecondDataSourceName() 
	{
		if(secondDataSourceName != null)
			return secondDataSourceName;
		
		initialize();			
		extractDataSources();
		
		try 
		{
			secondDataSourceName = (String)environmentContext.lookup("SecondDataSourceName");
		} 
		catch (NamingException e) 
		{
			if(extractedSecondDataSourceName != null)
			{
				secondDataSourceName = extractedSecondDataSourceName;
			}
			else
			{
				throw new RuntimeException("Unable to get the second datasource name", e);
			}
		}
		
		return secondDataSourceName;
	}	
	public static String getApplicationServerType() 
	{
		if(applicationServerTypeValue != null)
			return applicationServerTypeValue;
		
		initialize();		
		
		try 
		{
			applicationServerTypeValue = (String)environmentContext.lookup("ApplicationServerType");
		} 
		catch (NamingException e) 
		{
			applicationServerTypeValue = defaultApplicationServerTypeValue;
		}
		
		return applicationServerTypeValue;
	}
	public static void setApplicationServerType(String value)
	{
		if(value == null)
			value = defaultApplicationServerTypeValue;
		applicationServerTypeValue = value;
	}
	public static String GetLoggingLevel() 
	{
		if(loggingLevel != null)
			return loggingLevel;
		
		initialize();		
		
		try 
		{
			loggingLevel = (String)environmentContext.lookup("LoggingLevel");
		} 
		catch (NamingException e) 
		{
			loggingLevel = defaultLoggingLevel;
		}
		
		return loggingLevel;
	}
	public static void SetLoggingLevel(String value)
	{
		loggingLevel = value;
	}
	
	public static Boolean getPatientMerge()
	{
		if(patientMerge!=null)
		{
			return patientMerge;
		}
		initialize();
		try 
		{
			patientMerge=(Boolean)environmentContext.lookup("PatientMerge");
		}
		catch (NamingException e)
		{
			patientMerge=false;
		}
		return patientMerge;
	}
	
	public static void SetPatientMerge(Boolean value)
	{
		patientMerge=value;
		FirePatientMergeValueChanged(value);
	}
	
	public static Boolean getScheduledJobs()
	{
		if(scheduledJobs!=null)
		{
			return scheduledJobs;
		}
		initialize();
		try 
		{
			scheduledJobs=(Boolean)environmentContext.lookup("ScheduledJobs");
		}
		catch (NamingException e)
		{
			scheduledJobs=true;
		}
		return scheduledJobs;
	}
	
	public static String getCacheProvider() 
	{
		if(cacheProvider != null)
			return cacheProvider;
		
		initialize();		
		
		try 
		{
			cacheProvider = (String)environmentContext.lookup("CacheProvider");
		} 
		catch (NamingException e) 
		{
			cacheProvider = defaultCacheProvider;
		}
		
		return cacheProvider;
	}
	public static String getClusterNodes() 
	{
		if(clusterNodes != null)
			return clusterNodes;
		
		initialize();		
		
		try 
		{
			clusterNodes = (String)environmentContext.lookup("ClusterNodes");
		} 
		catch (NamingException e) 
		{
			clusterNodes = "";
		}
		
		return clusterNodes;
	}
	public static int getResultSetDefaultMax() 
	{
		if(resultSetDefaultMax != null)
			return resultSetDefaultMax;
		
		initialize();		
		
		try 
		{
			resultSetDefaultMax = (Integer)environmentContext.lookup("ResultSetDefaultMax");
		} 
		catch (NamingException e) 
		{
			resultSetDefaultMax = defaultResultSetDefaultMax;
		}
		
		return resultSetDefaultMax;
	}
	public static void setResultSetDefaultMax(int value)
	{
		resultSetDefaultMax = value;
	}
	public static int getResultSetTopMax() 
	{
		if(resultSetTopMax != null)
			return resultSetTopMax;
		
		initialize();		
		
		try 
		{
			resultSetTopMax = (Integer)environmentContext.lookup("ResultSetTopMax");
		} 
		catch (NamingException e) 
		{
			resultSetTopMax = defaultResultSetTopMax;
		}
		
		return resultSetTopMax;
	}
	public static void setResultSetTopMax(int value)
	{
		resultSetTopMax = value;
	}
	public static boolean getUseSecureAuthentication() 
	{
		if(useSecureAuthentication != null)
			return useSecureAuthentication;
		
		initialize();		
		
		try 
		{
			useSecureAuthentication = (Boolean)environmentContext.lookup("UseSecureAuthentication");
		} 
		catch (NamingException e) 
		{
			useSecureAuthentication = false;
		}
		
		return useSecureAuthentication;
	}
	public static boolean getShowSQL() 
	{
		if(showSQL != null)
			return showSQL;
		
		initialize();		
		
		try 
		{
			showSQL = (Boolean)environmentContext.lookup("ShowSQL");
		} 
		catch (NamingException e) 
		{
			showSQL = false;
		}
		
		return showSQL;
	}
	public static void setShowSQL(boolean value)
	{
		showSQL = value;
		FireShowSQLValueChanged(value);
	}
	public static boolean getFormatSQL() 
	{
		if(formatSQL != null)
			return formatSQL;
		
		initialize();		
		
		try 
		{
			formatSQL = (Boolean)environmentContext.lookup("FormatSQL");
		} 
		catch (NamingException e) 
		{
			formatSQL = true;
		}
		
		return formatSQL;
	}
	public static boolean getHibernateEnableStatistics() 
	{
		if(hibernateEnableStatistics != null)
			return hibernateEnableStatistics;
		
		initialize();		
		
		try 
		{
			hibernateEnableStatistics = (Boolean)environmentContext.lookup("HibernateEnableStatistics");
		} 
		catch (NamingException e) 
		{
			hibernateEnableStatistics = true;
		}
		
		return hibernateEnableStatistics;
	}
	public static boolean getHibernateUseSQLComments() 
	{
		if(hibernateUseSQLComments != null)
			return hibernateUseSQLComments;
		
		initialize();		
		
		try 
		{
			hibernateUseSQLComments = (Boolean)environmentContext.lookup("HibernateUseSQLComments");
		} 
		catch (NamingException e) 
		{
			hibernateUseSQLComments = false;
		}
		
		return hibernateUseSQLComments;
	}
	public static void setHibernateUseSQLComments(boolean value)
	{
		hibernateUseSQLComments = value;
		FireHibernateUseSQLCommentsValueChanged(value);
	}
	public static boolean getUseServiceManagerMonitoring() 
	{
		if(useServiceManagerMonitoring != null)
			return useServiceManagerMonitoring;
		
		initialize();		
		
		try 
		{
			useServiceManagerMonitoring = (Boolean)environmentContext.lookup("UseServiceManagerMonitoring");
		} 
		catch (NamingException e) 
		{
			useServiceManagerMonitoring = true;
		}
		
		return useServiceManagerMonitoring;
	}
	public static boolean getUseServiceManagerLogging() 
	{
		if(useServiceManagerLogging != null)
			return useServiceManagerLogging;
		
		initialize();		
		
		try 
		{
			useServiceManagerLogging = (Boolean)environmentContext.lookup("UseServiceManagerLogging");
		} 
		catch (NamingException e) 
		{
			useServiceManagerLogging = true;
		}
		
		return useServiceManagerLogging;
	}
	public static boolean getAuditEnabled() 
	{
		if(auditEnabled != null)
			return auditEnabled;
		
		initialize();		
		
		try 
		{
			auditEnabled = (Boolean)environmentContext.lookup("AuditEnabled");
		} 
		catch (NamingException e) 
		{
			auditEnabled = true;
		}
		
		return auditEnabled;
	}	
	public static String getMainPasswordEncryption()
	{
		if(mainPasswordEncryption != null)
			return mainPasswordEncryption;
		
		initialize();
		
		try 
		{
			mainPasswordEncryption = (String)environmentContext.lookup("MainPasswordEncryption");
		} 
		catch (NamingException e) 
		{
			mainPasswordEncryption = defaultMainPasswordEncryption;
		}
		
		return mainPasswordEncryption;		
	}
	public static String getDtoServerName()
	{
		if(dtoServerName != null)
			return dtoServerName;
		
		initialize();			
		
		try 
		{
			dtoServerName = (String)environmentContext.lookup("DtoServerName");
		} 
		catch (NamingException e) 
		{
			dtoServerName = defaultDtoServerName;
		}
		
		return dtoServerName;		
	}	
	public static int getDtoServerPort()
	{
		if(dtoServerPort != null)
			return dtoServerPort;
		
		initialize();			
		
		try 
		{
			dtoServerPort = (Integer)environmentContext.lookup("DtoServerPort");
		} 
		catch (NamingException e) 
		{
			dtoServerPort = defaultDtoServerPort;
		}
		
		return dtoServerPort;		
	}	
	public static String getDtoUserName()
	{
		if(dtoUsername != null)
			return dtoUsername;
		
		initialize();			
		
		try 
		{
			dtoUsername = (String)environmentContext.lookup("DtoUserName");
		} 
		catch (NamingException e) 
		{
			dtoUsername = defaultDtoUserName;
		}
		
		return dtoUsername;		
	}	
	public static String getDtoUserPassword()
	{
		if(dtoPassword != null)
			return dtoPassword;
		
		initialize();			
		
		try 
		{
			dtoPassword = (String)environmentContext.lookup("DtoUserPassword");
		} 
		catch (NamingException e) 
		{
			dtoPassword = defaultDtoPassword;
		}
		
		return dtoPassword;	
	}

	public static String getDtoDtoPasswordEncryption()
	{
		if(dtoPasswordEncryption != null)
			return dtoPasswordEncryption;
		
		initialize();			
		
		try 
		{
			dtoPasswordEncryption = (String)environmentContext.lookup("DtoPasswordEncryption");
		} 
		catch (NamingException e) 
		{
			dtoPasswordEncryption = defaultDtoPasswordEncryption;
		}
		
		return dtoPasswordEncryption;
	}
	public static String getImportExportSourceName()
	{
		if(importExportSourceName != null)
			return importExportSourceName;
		
		initialize();			
		
		try 
		{
			importExportSourceName = (String)environmentContext.lookup("ImportExportSourceName");
		} 
		catch (NamingException e) 
		{
			importExportSourceName = defaultImportExportSourceName;
		}
		
		return importExportSourceName;		
	}
	public static String getCapscanServerName()
	{
		if(capscanServerName != null)
			return capscanServerName;
		
		initialize();			
		
		try 
		{
			capscanServerName = (String)environmentContext.lookup("CapscanServerName");
		} 
		catch (NamingException e) 
		{
			capscanServerName = defaultCapscanServerName;
		}
		
		return capscanServerName;
	}
	public static String getCapscanPool()
	{
		if(capscanPool != null)
			return capscanPool;
		
		initialize();			
		
		try 
		{
			capscanPool = (String)environmentContext.lookup("CapscanPool");
		} 
		catch (NamingException e) 
		{
			capscanPool = defaultCapscanPool;
		}
		
		return capscanPool;
	}
	public static String getCapscanDistPool()
	{
		if(capscanDistPool != null)
			return capscanDistPool;
		
		initialize();			
		
		try 
		{
			capscanDistPool = (String)environmentContext.lookup("CapscanDistPool");
		} 
		catch (NamingException e) 
		{
			capscanDistPool = defaultCapscanDistPool;
		}
		
		return capscanDistPool;
	}
	public static String getEpresServerName()
	{
		if(epresServerName != null)
			return epresServerName;
		
		initialize();			
		
		try 
		{
			epresServerName = (String)environmentContext.lookup("EpresServerName");
		} 
		catch (NamingException e) 
		{
			epresServerName = defaultEpresServerName;
		}
		
		return epresServerName;
	}
	public static String getEpresProgId()
	{
		if(epresProgId != null)
			return epresProgId;
		
		initialize();			
		
		try 
		{
			epresProgId = (String)environmentContext.lookup("EpresProgId");
		} 
		catch (NamingException e) 
		{
			epresProgId = defaultEpresProgId;
		}
		
		return epresProgId;
	}
	public static String getEpresDomain()
	{
		if(epresDomain != null)
			return epresDomain;
		
		initialize();			
		
		try 
		{
			epresDomain = (String)environmentContext.lookup("EpresDomain");
		} 
		catch (NamingException e) 
		{
			epresDomain = defaultEpresDomain;
		}
		
		return epresDomain;
	}
	public static String getEpresDomainUser()
	{
		if(epresDomainUser != null)
			return epresDomainUser;
		
		initialize();			
		
		try 
		{
			epresDomainUser = (String)environmentContext.lookup("EpresDomainUser");
		} 
		catch (NamingException e) 
		{
			epresDomainUser = defaultEpresDomainUser;
		}
		
		return epresDomainUser;
	}
	public static String getEpresDomainPassword()
	{
		if(epresDomainPassword != null)
			return epresDomainPassword;
		
		initialize();			
		
		try 
		{
			epresDomainPassword = (String)environmentContext.lookup("EpresDomainPassword");
		} 
		catch (NamingException e) 
		{
			epresDomainPassword = defaultEpresDomainPassword;
		}
		
		return epresDomainPassword;
	}
	
	public static String getEpresLoggingLevel()
	{
		if(epresLoggingLevel != null)
			return epresLoggingLevel;
		
		initialize();			
		
		try 
		{
			epresLoggingLevel = (String)environmentContext.lookup("EpresDomainLoggingLevel");
		} 
		catch (NamingException e) 
		{
			epresLoggingLevel = defaultEpresLoggingLevel;
		}
		
		return epresLoggingLevel;
	}
	
	public static String getFileUploadMountpoint()
	{
		if(fileUploadMountpoint != null)
			return fileUploadMountpoint;
		
		initialize();			
		
		try 
		{
			fileUploadMountpoint = (String)environmentContext.lookup("FileUploadMountpoint");
		} 
		catch (NamingException e) 
		{
			fileUploadMountpoint = "";
		}
		
		return fileUploadMountpoint;		
	}	
	public static String getBaseUri()
	{
		if(baseUri != null)
			return baseUri;
		
		initialize();			
		
		try 
		{
			baseUri = (String)environmentContext.lookup("BaseUri");
		} 
		catch (NamingException e) 
		{
			baseUri = "";
		}
		
		return baseUri;		
	}
	public static void setBaseUri(String realPath)
	{
		baseUri = realPath;		
	}
	public static String getAplicationURL()
	{
		if(applicationURL != null)
			return applicationURL;
		
		initialize();			
		
		try 
		{
			applicationURL = (String)environmentContext.lookup("AplicationURL");
		} 
		catch (NamingException e) 
		{
			applicationURL = "";
		}
		
		return applicationURL;		
	}
	public static void setAplicationURL(String url)
	{
		applicationURL = url;				
	}	
	public static boolean getCaseSensitiveDatabase()
	{
		if(caseSensitiveDatabase != null)
			return caseSensitiveDatabase;
		
		initialize();			
		
		try 
		{
			caseSensitiveDatabase = (Boolean)environmentContext.lookup("CaseSensitiveDatabase");
		} 
		catch (NamingException e) 
		{
			caseSensitiveDatabase = true;
		}
		
		return caseSensitiveDatabase;		
	}
	private static void extractDataSources() 
	{
		try
		{
			Context initCtx = new InitialContext();
			
			if (getApplicationServerType() .equals(AppServer.WEBSPHERE) || getApplicationServerType() .equals(AppServer.TOMCAT))
			{				
				initCtx = (Context)initCtx.lookup("java:comp/env");
			}
			
			NamingEnumeration<NameClassPair> names = initCtx.list("jdbc");
			List<String> nameOpts = new ArrayList<String>();
			int i = 1;
			while (names.hasMore())
			{
				NameClassPair pair = names.next();				
				i++;
				nameOpts.add(pair.getName());
			}
			
			if(nameOpts.size() > 1)
			{
				extractedSecondDataSourceName = nameOpts.get(1);				
			}
			if(nameOpts.size() > 0)
			{
				extractedMainDataSourceName = nameOpts.get(0);				
			}			
		}
		catch (NamingException e) 
		{
			throw new RuntimeException(e);
		}
	}	
	public static String getOpenOfficeHomePath()
	{
		if(openOfficeHomePath != null)
			return openOfficeHomePath;
		
		initialize();			
		
		try 
		{
			openOfficeHomePath = (String)environmentContext.lookup("OpenOfficeHomePath");
		} 
		catch (NamingException e) 
		{
			openOfficeHomePath = "";
		}
		
		return openOfficeHomePath;		
	}
	public static String getLDAPServer()
	{
		if(LDAPServer != null)
			return LDAPServer;
		
		initialize();			
		
		try 
		{
			LDAPServer = (String)environmentContext.lookup("LDAPServer");
		} 
		catch (NamingException e) 
		{
			LDAPServer = "";
		}
		
		return LDAPServer;		
	}
	public static String getUserImagesStorePath()
	{
		if(userImagesStorePath != null)
			return userImagesStorePath;
		
		initialize();			
		
		try 
		{
			userImagesStorePath = (String)environmentContext.lookup("UserImagesStorePath");
		} 
		catch (NamingException e) 
		{
			String baseUri =  getBaseUri();
			if (baseUri != null && !baseUri.equals(""))
			{
				if (baseUri.endsWith("/"))
				{
					userImagesStorePath = getBaseUri() + "uploads" +  "/";					
				}
				else if (baseUri.endsWith("\\"))
				{
					userImagesStorePath = getBaseUri() + "uploads" +  "\\";
				}
				
				return userImagesStorePath;
			}
			
			userImagesStorePath = "" ;
		}
		
		return userImagesStorePath;		
	}
	public static String getImageServerURL()
	{
		if(imageServerURL != null)
			return imageServerURL;
		
		initialize();			
		
		try 
		{
			imageServerURL = (String)environmentContext.lookup("ImageServerURL");
		} 
		catch (NamingException e) 
		{						
			imageServerURL = "";
		}
		
		return imageServerURL;		
	}
	
	public static String getUserProviderFlag()
	{
		if(userProviderFlag != null)
			return userProviderFlag;
		
		initialize();			
		
		try 
		{
			userProviderFlag = (String)environmentContext.lookup("UserProviderFlag");
		} 
		catch (NamingException e) 
		{
			userProviderFlag = "";
		}
		
		return userProviderFlag;		
	}
	
	public static String getLDAPBase()
	{
		if(LDAPBase != null)
			return LDAPBase;
		
		initialize();			
		
		try 
		{
			LDAPBase = (String)environmentContext.lookup("LDAPBase");
		} 
		catch (NamingException e) 
		{
			LDAPBase = "";
		}
		
		return LDAPBase;		
	}
	public static String getLDAPDomain()
	{
		if(LDAPDomain != null)
			return LDAPDomain;
		
		initialize();			
		
		try 
		{
			LDAPDomain = (String)environmentContext.lookup("LDAPDomain");
		} 
		catch (NamingException e) 
		{
			LDAPDomain = "";
		}
		
		return LDAPDomain;		
	}
	public static int getLDAPPort()
	{
		if(LDAPPort != null)
			return LDAPPort;
		
		initialize();			
		
		try 
		{
			LDAPPort = (Integer)environmentContext.lookup("LDAPPort");
		} 
		catch (NamingException e) 
		{
			LDAPPort = 389;
		}
		
		return LDAPPort;		
	}	
	public static String getLDAPGroup()
	{
		if(LDAPGroup != null)
			return LDAPGroup;
		
		initialize();			
		
		try 
		{
			LDAPGroup = (String)environmentContext.lookup("LDAPGroup");
		} 
		catch (NamingException e) 
		{
			LDAPGroup = "";
		}
		
		return LDAPGroup;		
	}

	public static String getLDAPSecurityProtocol()
	{
		if(LDAP_SECURITY_PROTOCOL != null)
			return LDAP_SECURITY_PROTOCOL;
		initialize();			
		try 
		{
			LDAP_SECURITY_PROTOCOL = (String)environmentContext.lookup("LDAP_SECURITY_PROTOCOL");
		} 
		catch (NamingException e) 
		{
			LDAP_SECURITY_PROTOCOL = "";
		}
		return LDAP_SECURITY_PROTOCOL;		
	}

	public static String getQuartzDbDriver()
	{
		if(quartzDbDriver != null)
			return quartzDbDriver;
		
		initialize();			
		
		try 
		{
			quartzDbDriver = (String)environmentContext.lookup("QuartzDbDriver");
			/* quartzDbDriver = ((BasicDataSource) environmentContext.lookup("jdbc/" + EnvironmentConfig.getMainDataSourceName())).getDriverClassName(); */
		} 
		catch (NamingException e) 
		{
			quartzDbDriver = "";
		}
		
		return quartzDbDriver;		
	}
	public static String getQuartzDbUrl()
	{
		if(quartzDbUrl != null)
			return quartzDbUrl;
		
		initialize();			
		
		try 
		{
			quartzDbUrl = (String)environmentContext.lookup("QuartzDbUrl");
		} 
		catch (NamingException e) 
		{
			quartzDbUrl = "";
		}
		
		return quartzDbUrl;		
	}
	public static String getQuartzDbUsername()
	{
		if(quartzDbUsername != null)
			return quartzDbUsername;
		
		initialize();			
		
		try 
		{
			quartzDbUsername = (String)environmentContext.lookup("QuartzDbUsername");
		} 
		catch (NamingException e) 
		{
			quartzDbUsername = "";
		}
		
		return quartzDbUsername;		
	}
	public static String getQuartzDbPassword()
	{
		if(quartzDbPassword != null)
			return quartzDbPassword;
		
		initialize();			
		
		try 
		{
			quartzDbPassword = (String)environmentContext.lookup("QuartzDbPassword");
		} 
		catch (NamingException e) 
		{
			quartzDbPassword = "";
		}
		
		return quartzDbPassword;		
	}

	public static void AddStartPatientMergeServletDelegate(EnvironmentConfigPatientMergeValueChanged delegate)
	{
		if(patientMergeValueChanged.contains(delegate))
			return;		
		patientMergeValueChanged.add(delegate);
	}
	public static void RemovePatientMergeServletDelegate(EnvironmentConfigPatientMergeValueChanged delegate)
	{
		patientMergeValueChanged.remove(delegate);		
	}
	private static void FirePatientMergeValueChanged(boolean value)
	{
		for(int x = 0; x < patientMergeValueChanged.size(); x++)
		{
			patientMergeValueChanged.get(x).handle(value);
		}
	}

	
	public static void AddShowSQLDelegate(EnvironmentConfigShowSQLValueChanged delegate)
	{
		if(showSQLValueChanged.contains(delegate))
			return;		
		showSQLValueChanged.add(delegate);
	}
	public static void RemoveShowSQLDelegate(EnvironmentConfigShowSQLValueChanged delegate)
	{
		showSQLValueChanged.remove(delegate);		
	}
	private static void FireShowSQLValueChanged(boolean value)
	{
		for(int x = 0; x < showSQLValueChanged.size(); x++)
		{
			showSQLValueChanged.get(x).handle(value);
		}
	}
	
	
	public static void AddHibernateUseSQLCommentsDelegate(EnvironmentConfigHibernateUseSQLCommentsValueChanged delegate)
	{
		if(hibernateUseSQLCommentsValueChanged.contains(delegate))
			return;		
		hibernateUseSQLCommentsValueChanged.add(delegate);
	}
	public static void RemoveHibernateUseSQLCommentsDelegate(EnvironmentConfigHibernateUseSQLCommentsValueChanged delegate)
	{
		hibernateUseSQLCommentsValueChanged.remove(delegate);		
	}
	private static void FireHibernateUseSQLCommentsValueChanged(boolean value)
	{
		for(int x = 0; x < hibernateUseSQLCommentsValueChanged.size(); x++)
		{
			hibernateUseSQLCommentsValueChanged.get(x).handle(value);
		}
	}
	
	
}
