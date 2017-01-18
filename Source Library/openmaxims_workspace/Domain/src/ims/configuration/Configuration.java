package ims.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpSession;

import ims.crypto.Cloak;
import ims.crypto.Encryption;
import ims.crypto.EncryptionType;
import ims.crypto.RawKey;
import ims.domain.DomainSession;
import ims.domain.exceptions.DomainRuntimeException;
import ims.framework.interfaces.IAppUser;
import ims.utils.Base64Coder;

abstract public class Configuration implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static SecretKey key = new RawKey(new byte[] {-62, -110, 88, -14, 112, -122, 127, -65, 49, -15, -5, -14, -14, 19, 35, 37, 42, 38, -9, -68, -23, 107, 4, 61});
	private static Configuration globalConfig = null;
	private static BuildInfo buildInfo=null;
	private static final org.apache.log4j.Logger	LocalLogger				= ims.utils.Logging.getLogger(Configuration.class);

	
	abstract public IAppUser login(String name, String password, DomainSession session) throws Exception;
	abstract public IAppUser login(String st, DomainSession session) throws Exception;
	abstract public IAppUser secureLogin(String name, String password, String passwordSalt, DomainSession session) throws Exception;
	abstract public IAppUser getAppUser(String username, DomainSession session) throws Exception; 

	abstract public Map getRegisteredForms();
	abstract public Map getRegisteredImages();
	abstract public java.util.List getPathwayEntities();
	
	abstract public boolean isTabLayout();
	abstract public void newPassword(IAppUser user, String value, DomainSession session);
	
	abstract public void lockAccount(IAppUser user, boolean value, DomainSession session);
	
	abstract public String getDbuse();
	abstract public void reload() throws Exception;
	
	abstract public ClassConfig getClassConfig();	
					
	public static String encryptPasswordWithCloak(String password)
	{
		return Cloak.encryptString(password, key);
	}
	public static String decryptPasswordWithCloak(String password)
	{
		return Cloak.decryptString(password, key);
	}
	public static String getSHA1Hash(String password, String salt) throws Exception
	{
		return getSHA1Hash(password, salt, true);
	}
	public static String getSHA1Hash(String password, String salt, boolean hex) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		String text = password + salt;
		md.update((text).getBytes("UTF-8"));		
		byte[] raw = md.digest();
				
		if(hex)
		{
			StringBuffer hexString = new StringBuffer();
			for (int i=0; i < raw.length; i++) 
			{
			  String t = Integer.toHexString(0xFF & raw[i]);
			  hexString.append((t.length() == 1 ? "0" : "") + t);		  
			}
			return hexString.toString();
		}
		
		return new String(Base64Coder.encode(raw));
	}	
	public static String getHash(String password) throws Exception
	{
		//FWD-169
		if (EncryptionType.getOne(EnvironmentConfig.getMainPasswordEncryption()).equals(EncryptionType.HASH))
		{
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes("UTF-8"));
			byte[] raw = md.digest();
			return new String(Base64Coder.encode(raw));
		}
		else if (EncryptionType.getOne(EnvironmentConfig.getMainPasswordEncryption()).equals(EncryptionType.PRIME))
		{
			return Encryption.encrypt(password, EncryptionType.PRIME);
		}
		else if (EncryptionType.getOne(EnvironmentConfig.getMainPasswordEncryption()).equals(EncryptionType.UNIX))
		{
			return Encryption.encrypt(password, EncryptionType.UNIX);
		}
		else if (EncryptionType.getOne(EnvironmentConfig.getMainPasswordEncryption()).equals(EncryptionType.CLOAK))
		{
			return Cloak.encryptString(password, key);
		}
		else if (EncryptionType.getOne(EnvironmentConfig.getMainPasswordEncryption()).equals(EncryptionType.NONE))
		{
			return password;
		}
		else
			throw new DomainRuntimeException("No Password encryption algorithm has been configured");
		
	}
	
	public void logout()
	{
		// Does nothing, DTOconfig will override this method
	}
	
	public void setHttpSession(javax.servlet.http.HttpSession session)
	{
		if (session == null) return;
		// Does nothing, DTOconfig will override this method		
	}
	public static Configuration loadConfiguration() throws Exception
	{
		return loadConfiguration(null);
	}
	
	public static BuildInfo getBuildInfo()
	{
		return buildInfo;
	}
	
	public static Configuration loadConfiguration(HttpSession session) throws Exception
	{
		if (globalConfig != null)
			return globalConfig;
		
		InitConfig.loadInitConfig(); //Will do nothing if it has already be called from elsewhere.
		
		ConfigType configType = InitConfig.getConfigType();
		if (configType.equals(ConfigType.HIB)) 
		{
			Class c = Class.forName("ims.domain.hibernate3.HibernateConfiguration");
			globalConfig = (Configuration)c.newInstance();
		}
		else if (configType.equals(ConfigType.XML)) 
		{
			Class[] parameterTypes = new Class[1];
			Object[] parameterValues = new Object[1];

			parameterTypes[0] = String.class;
			parameterValues[0] = "configuration.xml";

			Class dtoClass = Class.forName("ims.configuration.XMLConfiguration");
			Constructor cons = dtoClass.getConstructor(parameterTypes);
			globalConfig = (Configuration) cons.newInstance(parameterValues);
		}
		else if (configType.equals(ConfigType.DTO)) 
		{
			Class[] parameterTypes = new Class[2];
			Object[] parameterValues = new Object[2];

			String url = "configuration.xml";
			parameterTypes[0] = String.class;
			parameterTypes[1] = HttpSession.class;

			parameterValues[0] = url;
			parameterValues[1] = session;

			Class dtoClass = Class.forName("ims.dto.DTOConfiguration");
			Constructor dtoConstructor = dtoClass.getConstructor(parameterTypes);
			globalConfig = (Configuration) dtoConstructor.newInstance(parameterValues);
		}
		return globalConfig;
	}

	
	/**
	 * Method to set the local appVersion and appTimestamp and appDescription variables from the values in classes folder file - ImsAppBuild.properties Sample contents: ims.build.item=Choose & Book Project ims.build.timestamp=20050718152000 ims.build.version=ICAB_0001
	 */
	public static BuildInfo readAppBuildDetails(Class cls)
	{
		if (buildInfo != null)
		{
			// Just in case it's called twice by mistake.
			return buildInfo;
		}
		
		InputStream stream = null;
		String appDescription = "UNKNOWN";
		String appVersion = "UNKNOWN";
		String appTimestamp = "UNKNOWN";
		String warTimestamp = "UNKNOWN";
		String warVersion = "UNKNOWN";
		try
		{
			stream = cls.getClassLoader().getResourceAsStream("ImsAppBuild.properties");
			if (stream != null)
			{
				Properties prop = new Properties();
				prop.load(stream);

				String val = prop.getProperty("ims.build.item");
				appDescription = val == null ? "Unspecified" : val;

				val = prop.getProperty("ims.build.timestamp");
				appTimestamp = val == null ? "Unspecified" : val;

				val = prop.getProperty("ims.build.version");
				appVersion = val == null ? "Unspecified" : val;

				val = prop.getProperty("ims.warfile.version");
				warVersion = val == null ? "Unspecified" : val;

				val = prop.getProperty("ims.warfile.timestamp");
				warTimestamp = val == null ? "Unspecified" : val;
			}
			else
			{
				LocalLogger.error("BuildInformation File not found - ImsAppBuild.properties");
			}
		}
		catch (IOException e)
		{
			LocalLogger.error("IOException occurred reading Application Build Information - " + e.getMessage(), e);
		}
		finally
		{
			if (stream != null)
			{
				try
				{
					stream.close();
				}
				catch (IOException e1)
				{
				}
			}
		}
		buildInfo = new BuildInfo(appDescription, appVersion, appTimestamp, warVersion, warTimestamp);
		return buildInfo;
	}

	
	public static void main(String[] args)
	{
		try
		{
			Integer a = new Integer(3);
			Integer b = new Integer(3);
			System.out.println("hash of a is " + a.hashCode());
			System.out.println("hash of b is " + b.hashCode());
			System.out.println("equality is " + a.equals(b) );
			//System.out.println(Configuration.getHash("icws"));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
