package ims.configuration;

import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


public class BuildInfo 
{
	private String appDescription;
	private String appVersion;
	private String appTimestamp;
	private String warVersion = "";
	private String warTimestamp = "";
	private String frameworkVersionInfo="";
	private String frameworkTimestamp="";
	private String frameworkFullVersionInfo="";
	private String maximsICABversionInfo="";
	private String maximsICABTimestampInfo="";
	
	// WDEV-21284 - PDS Version Info
	private String maximsPDSversionInfo="";
	private String maximsPDSTimestampInfo="";
	private String maximsPDSSchemaVersionInfo="";
	private String maximsPDSSchemaTimestampInfo="";

	public BuildInfo()
	{
		this.appDescription = "";
		this.appVersion = "";
		this.appTimestamp="";
		this.maximsICABversionInfo="";
		this.maximsICABTimestampInfo="";
		this.maximsPDSversionInfo="";
		this.maximsPDSTimestampInfo="";
		this.maximsPDSSchemaVersionInfo="";
		this.maximsPDSSchemaTimestampInfo="";
	}
	public BuildInfo(String name, String version, String date)
	{
		this.appDescription = name;
		this.appVersion = version;
		this.appTimestamp = date;		
	}

	public BuildInfo(String name, String version, String date, String warVer, String warTs)
	{
		this.appDescription = name;
		this.appVersion = version;
		this.appTimestamp = date;	
		this.warVersion = warVer;
		this.warTimestamp = warTs;
	}

	public String getMaximsICABversionInfo()
	{
		return this.maximsICABversionInfo;
	}

	public String getMaximsICABTimestampInfo()
	{
		return this.maximsICABTimestampInfo;
	}

	public String getName()
	{
		return this.appDescription;
	}
	
	public String getAppTimestamp()
	{
		return this.appTimestamp;
	}
	
	public String getAppVersion()
	{
		return this.appVersion;
	}
	
	public String toString()
	{
		return this.appDescription + "-" + this.appVersion;
	}

	public String getWarTimestamp() 
	{
		return warTimestamp;
	}
	public String getWarVersion() 
	{
		return warVersion;
	}

	public final String getFrameworkVersionInfo() 
	{
		return frameworkVersionInfo; 
	}
	public final String getFrameworkFullVersionInfo() 
	{
		return frameworkFullVersionInfo; 
	} 
	public final String getFrameworkTimestamp() 
	{
		return frameworkTimestamp; 
	} 

	public final void setMaximsICABVersionInfo() 
	{
		String version,timestamp;
		
		version = "";
		timestamp = "";
		
		try
		{
			String path = EnvironmentConfig.getBaseUri();

			Manifest mf = null;			
			JarFile jf = new JarFile(path + "/WEB-INF/lib/MaximsICAB.jar");
			if (jf != null)
				mf = jf.getManifest();
			else
				this.maximsICABversionInfo="";
			
			jf.close();

			//set attributes from mf
			if (mf != null)
			{
				Attributes atts = mf.getMainAttributes();
				if (atts != null)
				{
					version = atts.getValue("Version"); 
					timestamp = atts.getValue("Timestamp");
				}				
			}
			
			this.maximsICABversionInfo = version;
			this.maximsICABTimestampInfo = timestamp;
		}
		catch (Exception e)
		{
			this.maximsICABversionInfo="";
		}
		
	}
	
	/**
	 * WDEV-21284 Versioning required for PDS jar
	 */
	public final void setMaximsIPDSVersionInfo() 
	{
		String version,timestamp;
		
		version = "";
		timestamp = "";
		
		try
		{
			String path = EnvironmentConfig.getBaseUri();

			Manifest mf = null;			
			JarFile jf = new JarFile(path + "/WEB-INF/lib/MaximsIPDS.jar");
			if (jf != null)
				mf = jf.getManifest();
			else
				this.maximsPDSversionInfo="";
			
			jf.close();

			//set attributes from mf
			if (mf != null)
			{
				Attributes atts = mf.getMainAttributes();
				if (atts != null)
				{
					version = atts.getValue("Version"); 
					timestamp = atts.getValue("Timestamp");
				}				
			}
			
			this.maximsPDSversionInfo = version;
			this.maximsPDSTimestampInfo = timestamp;
		}
		catch (Exception e)
		{
			this.maximsPDSversionInfo="";
		}
	}

	/**
	 * WDEV-21284 Versioning required for PDS Schema jar
	 */

	public final void setMaximsPDSSchemaVersionInfo() 
	{
		String version,timestamp;
		
		version = "";
		timestamp = "";
		
		try
		{
			String path = EnvironmentConfig.getBaseUri();

			Manifest mf = null;			
			JarFile jf = new JarFile(path + "/WEB-INF/lib/PDSSchema.jar");
			if (jf != null)
				mf = jf.getManifest();
			else
				this.maximsPDSSchemaVersionInfo="";
			
			jf.close();

			//set attributes from mf
			if (mf != null)
			{
				Attributes atts = mf.getMainAttributes();
				if (atts != null)
				{
					version = atts.getValue("Version"); 
					timestamp = atts.getValue("Timestamp");
				}				
			}
			
			this.maximsPDSSchemaVersionInfo = version;
			this.maximsPDSSchemaTimestampInfo = timestamp;
		}
		catch (Exception e)
		{
			this.maximsPDSSchemaVersionInfo="";
		}
	}

	
	public final void setFrameworkVersionInfo(String version) 
	{
		frameworkVersionInfo=version; 
	}
	public final void setFrameworkFullVersionInfo(String version) 
	{
		frameworkFullVersionInfo=version; 
	} 
	public final void setFrameworkTimestamp(String timestamp) 
	{
		frameworkTimestamp=timestamp; 
	} 

	
	public final String getDomainVersionInfo() 
	{
		return ims.domain.Version.getVersionInfo(); 
	}
	public final String getDomainFullVersionInfo() 
	{
		return ims.domain.Version.getFullVersionInfo(); 
	} 
	public final String getDomainTimestamp() 
	{
		return ims.domain.Version.getTimestamp(); 
	} 

	public final String getBaseVersionInfo() 
	{
		return ims.base.Version.getVersionInfo(); 
	}
	public final String getBaseFullVersionInfo() 
	{
		return ims.base.Version.getFullVersionInfo(); 
	} 
	public final String getBaseTimestamp() 
	{
		return ims.base.Version.getTimestamp(); 
	}
	public String getMaximsPDSSchemaTimestampInfo()
	{
		return maximsPDSSchemaTimestampInfo;
	}
	public void setMaximsPDSSchemaTimestampInfo(String maximsPDSSchemaTimestampInfo)
	{
		this.maximsPDSSchemaTimestampInfo = maximsPDSSchemaTimestampInfo;
	}
	public String getMaximsPDSSchemaVersionInfo()
	{
		return maximsPDSSchemaVersionInfo;
	}
	public void setMaximsPDSSchemaVersionInfo(String maximsPDSSchemaVersionInfo)
	{
		this.maximsPDSSchemaVersionInfo = maximsPDSSchemaVersionInfo;
	}
	public String getMaximsPDSTimestampInfo()
	{
		return maximsPDSTimestampInfo;
	}
	public void setMaximsPDSTimestampInfo(String maximsPDSTimestampInfo)
	{
		this.maximsPDSTimestampInfo = maximsPDSTimestampInfo;
	}
	public String getMaximsPDSversionInfo()
	{
		return maximsPDSversionInfo;
	}
	public void setMaximsPDSversionInfo(String maximsPDSversionInfo)
	{
		this.maximsPDSversionInfo = maximsPDSversionInfo;
	} 

}
