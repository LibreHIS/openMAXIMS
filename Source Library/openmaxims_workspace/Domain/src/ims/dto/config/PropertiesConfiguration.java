/*
 * Created on 16-Feb-04
 * 
 */
package ims.dto.config;

import ims.configuration.ConfigFlag;
import ims.configuration.EnvironmentConfig;
import ims.crypto.EncryptionType;
import ims.domain.exceptions.DomainRuntimeException;
import ims.dto.ServerCompatibility;

/**
 * Implementation of ConnectionConfiguration that uses properties to get configuration. First looks for a properites file "dto.properties" on the classpath. If the properties file is not available, the system-variables may be used. If system-properties are not set then hard-coded default values are used.
 * 
 * @author gcoghlan
 */
public class PropertiesConfiguration implements ConnectionConfiguration
{
	private static final org.apache.log4j.Logger	LOG											= ims.utils.Logging.getLogger(PropertiesConfiguration.class);
	private static final String						SERVERCOMPATIBILITY_C						= "c";
	private static final String						SERVERCOMPATIBILITY_JAVA					= "java";
	/** "xmiserverch2" */
	public static final String						DEFAULT_SERVER_NAME							= "xmiserverch2";
	/** 7300 */
	public static final Integer						DEFAULT_SERVER_PORT							= new Integer(7300);
	/** "dto.properties" */
	public static final String						PROPERTIES_FILE								= "dto.properties";
	/** "ims.dto.ServerName" */
	public static final String						PROP_SERVER_NAME							= "ims.dto.ServerName";
	/** "ims.dto.ServerPort" */
	public static final String						PROP_SERVER_PORT							= "ims.dto.ServerPort";
	/** "ims.dto.ServerCompatibility" */
	public static final String						PROP_SERVER_COMPATIBILITY					= "ims.dto.ServerCompatibility";
	/** "ims.dto.username" */
	public static final String						PROP_USER_NAME								= "ims.dto.username";
	/** "ims.dto.password" */
	public static final String						PROP_PASSWORD								= "ims.dto.password";
	/** "ims.dto.password.encryption" */
	public static final String						PROP_PASSWORD_ENCRYPTION					= "ims.dto.password.encryption";
	/** "ims.dto.connection.pool" */
	public static final String						PROP_CONNECTION_POOL						= "ims.dto.connection.pool";
	/** "ims.dto.connection.pool.maxActive" */
	public static final String						PROP_CONNECTION_POOL_MAXACTIVE				= "ims.dto.connection.pool.maxActive";
	/** "ims.dto.connection.pool.maxIdle" */
	public static final String						PROP_CONNECTION_POOL_MAXIDLE				= "ims.dto.connection.pool.maxIdle";
	/** "ims.dto.connection.pool.whenExhaustedAction" */
	public static final String						PROP_CONNECTION_POOL_WHEN_EXHAUSTED_ACTION	= "ims.dto.connection.pool.whenExhaustedAction";
	/** "ims.dto.connection.pool.maxWait" */
	public static final String						PROP_CONNECTION_POOL_MAXWAIT				= "ims.dto.connection.pool.maxWait";
	/** "ims.dto.shareLogin" */
	public static final String						PROP_SHARE_LOGIN							= "ims.dto.shareLogin";

	/**
	 * Reads the properties file from the classpath if it's available.
	 * 
	 * @throws DomainRuntimeException
	 *             if the properties file exists but cannot be read.
	 */
	public PropertiesConfiguration()
	{
		// Instead of reading from the properties file
		// we are now going to get the values from Configuration Flags in
		// the framework
	}

	/**
	 * Uses the property "ims.dto.ServerName" to define the server name.
	 * 
	 * @see ims.dto.ConnectionConfiguration#getServerName()
	 */
	public String getServerName()
	{
		String serverName = getProperty(EnvironmentConfig.getDtoServerName(), DEFAULT_SERVER_NAME);
		if (LOG.isDebugEnabled())
			LOG.debug("serverName:" + serverName);
		return serverName;
	}

	/**
	 * Uses the property "ims.dto.ServerPort" to define the server port.
	 * 
	 * @see ims.dto.ConnectionConfiguration#getServerPort()
	 */
	public int getServerPort()
	{
		return EnvironmentConfig.getDtoServerPort();
	}

	public String getUsername()
	{
		String userName = getProperty(EnvironmentConfig.getDtoUserName(), "SU");
		if (LOG.isDebugEnabled())
			LOG.debug("userName:" + userName);
		return userName;
	}

	public String getPassword()
	{
		String password = getProperty(EnvironmentConfig.getDtoUserPassword(), "icws");
		if (LOG.isDebugEnabled())
			LOG.debug("password:" + password);
		return password;
	}

	/**
	 * Use the properties to get an EncryptionType
	 * 
	 * @return null if the encryption property is not set
	 */
	public EncryptionType getEncryptionType()
	{
		return EncryptionType.getOne(EnvironmentConfig.getDtoDtoPasswordEncryption());
	}

	public ServerCompatibility getServerCompatibility()
	{
		String value = getProperty(ConfigFlag.DTO.SERVER_COMPATIBILITY.getValue());
		if (null != value)
		{
			if (SERVERCOMPATIBILITY_JAVA.equalsIgnoreCase(value))
			{
				if (LOG.isDebugEnabled())
					LOG.debug("ServerCompatibility.JAVA");
				return ServerCompatibility.JAVA;
			}
			else if (SERVERCOMPATIBILITY_C.equalsIgnoreCase(value))
			{
				if (LOG.isDebugEnabled())
					LOG.debug("ServerCompatibility.C");
				return ServerCompatibility.C;
			}
			else
			{
				String[] expectedValues = {SERVERCOMPATIBILITY_JAVA, SERVERCOMPATIBILITY_C};
				String message = failureMessage("server-compatibility", value, expectedValues, PROP_SERVER_COMPATIBILITY);
				LOG.error(message);
				throw new DomainRuntimeException(message);
			}

		}
		if (LOG.isDebugEnabled())
			LOG.debug("ServerCompatibility - null");
		return null;
	}

	/**
	 * Tries to get the value for the property. First, check the loaded properties. If it is not set there, try the system-properties. Otherwise return the default value, this may be null.
	 * 
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	private String getProperty(String propertyValue, String defaultValue)
	{
		if (LOG.isDebugEnabled())
			LOG.debug("getProperty(property=" + propertyValue);
		if (null == propertyValue)
		{
			propertyValue = defaultValue;
			if (LOG.isDebugEnabled())
				LOG.debug("Using System Property value=" + propertyValue);
		}
		return (null != propertyValue) ? propertyValue.trim() : null;
	}

	/**
	 * Uses
	 * 
	 * @see getProperty(String, String) to get the property value.
	 * @param property
	 * @return the value or null if the property is not set
	 */
	private String getProperty(String propertyValue)
	{
		return getProperty(propertyValue, null);
	}

	private static String failureMessage(String configType, String value, String[] expectedValues, String property)
	{
		StringBuffer sb = new StringBuffer("Incorrect value for ");
		sb.append(configType).append(" configuration of connection.");
		sb.append(" Provided value:").append(value).append(", but expected values are:");
		for (int i = 0; i < expectedValues.length; i++)
		{
			sb.append(expectedValues[i]);
			if (i + 1 < expectedValues.length)
			{
				sb.append(",");
			}
			else
			{
				sb.append(".");
			}
		}
		sb.append(" Check the ").append(property).append(" property in your ").append(PROPERTIES_FILE);
		return sb.toString();
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#isConnectionPooling()
	 */
	public boolean isConnectionPooling()
	{
		return ConfigFlag.DTO.CONNECTION_POOL.getValue();
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#isSharedLogin()
	 */
	public boolean isSharedLogin()
	{
		return ConfigFlag.DTO.SHARE_LOGIN.getValue();
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#getMaxActive()
	 */
	public Integer getMaxActive()
	{
		return new Integer(ConfigFlag.DTO.CONNECTION_POOL_MAXACTIVE.getValue());
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#getMaxIdle()
	 */
	public Integer getMaxIdle()
	{
		return new Integer(ConfigFlag.DTO.CONNECTION_POOL_MAXIDLE.getValue());
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#getMaxWait()
	 */
	public Integer getMaxWait()
	{
		return new Integer(ConfigFlag.DTO.CONNECTION_POOL_MAXWAIT.getValue());
	}

	/**
	 * @see ims.dto.config.ConnectionConfiguration#getWhenExhaustedAction()
	 */
	public String getWhenExhaustedAction()
	{
		String value = getProperty(ConfigFlag.DTO.CONNECTION_POOL_WHEN_EXHAUSTED_ACTION.getValue());
		if (null != value)
		{
			return value.trim();
		}
		else
		{
			return null;
		}
	}

}
