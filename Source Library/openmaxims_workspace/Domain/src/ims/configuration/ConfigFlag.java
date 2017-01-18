/*
 * Created on 18-Aug-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.configuration;


import ims.domain.factory.ConfigFlagsFactory;
import ims.framework.FormName;
import ims.framework.enumerations.SortOrder;
import ims.framework.interfaces.IConfigFlagsProvider;
import ims.framework.utils.Color;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.xml.sax.InputSource;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ConfigFlag
{
	
	private static boolean flagsLoaded = false;
	private static String flagFileName;	
	
	private static IConfigFlagsProvider getConfigFlagsProvider()
	{
		try
		{			
			return new ConfigFlagsFactory().getProvider();
		}
		catch(Exception ex)
		{
			throw new RuntimeException("Unable to initialize the config flags provider.", ex);
		}
	}
	
	public final static String SystemPassword = "imsmaximsplc!";
	//************************
	// The Public flag classes
	//************************
	public static abstract class PublicFlag	
	{		
		private boolean visible = true;
		
		public String getName()
		{
			return getInternalFlag().getName();
		}
		public final void setVisible(boolean value)
		{
			visible = value;
		}
		public final boolean isVisible()
		{
			return visible;
		}
		protected abstract AFlag getInternalFlag();
		
		// FWB-86
		public String getDefaultValue()
		{
			return getInternalFlag().getDefaultValAsString();
		}
	}
	
	public static class StringFlag extends PublicFlag
	{
		protected StringInternalFlag internalFlag;
		
		public StringFlag(StringInternalFlag internalFlag)
		{
			this.internalFlag = internalFlag;
		}
		
		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}
		
		public String getValue()
		{
			return internalFlag.getValue();
		}
		
		public String getValue(boolean pluralVersion) 
		{
			return internalFlag.getValue(pluralVersion);
		}
	}

	public static class DynamicEnumFlag extends StringFlag
	{
		public DynamicEnumFlag(StringInternalFlag internal)
		{
			super(internal);
		}

		public void setOptions(String[] opts)
		{
			this.internalFlag.options = opts;
		}
		
		@SuppressWarnings("unchecked")
		public void setOptions(List opts)
		{
			this.internalFlag.options = (String[])opts.toArray(new String[opts.size()]);
		}	
		
		public String[] getOptions()
		{
			return this.internalFlag.getOptions();
		}
		
		
	}
		
	public static class IntFlag extends PublicFlag
	{
		private IntInternalFlag internalFlag;
		
		public IntFlag(IntInternalFlag internal)
		{
			this.internalFlag = internal;
		}
		
		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}
		
		public int getValue()
		{
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return Integer.parseInt(this.getDefaultValue());
			return Integer.parseInt(value);	
		}
	}

	public static class DecimalFlag extends PublicFlag
	{
		private DecimalInternalFlag internalFlag;
		
		public DecimalFlag(DecimalInternalFlag internal)
		{
			this.internalFlag = internal;
		}

		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}
		
		public float getValue()
		{
			return internalFlag.getValue();
		}
	}

	public static class BooleanFlag extends PublicFlag
	{
		private BooleanInternalFlag internalFlag;
		
		public BooleanFlag(BooleanInternalFlag internal)
		{
			this.internalFlag = internal;
		}
		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}		
		public boolean getValue()
		{
			return internalFlag.getValue();
		}
	}

	public static class ColourFlag extends PublicFlag
	{
		private ColourInternalFlag internalFlag;
		
		public ColourFlag(ColourInternalFlag internal)
		{
			this.internalFlag = internal;
		}

		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}
		public Color getValue()
		{
			return internalFlag.getValue();
		}
	}

	public static class FormFlag extends PublicFlag
	{
		private FormInternalFlag internalFlag;
		
		public FormFlag(FormInternalFlag internal)
		{
			this.internalFlag = internal;
		}

		protected AFlag getInternalFlag()
		{
			return internalFlag;
		}
		public FormName getValue()
		{
			return internalFlag.getValue();
		}	
	}

	public static class StringInternalLocalFlag extends StringInternalFlag
	{
		private String flagValue;
		protected StringInternalLocalFlag(String name, String defaultVal, String comment) 
		{
			super( name,  defaultVal,  comment);
		}

		protected StringInternalLocalFlag(String name, String defaultVal, String comment, boolean isSystem) 
		{
			super( name,  defaultVal,  comment,isSystem);
		}

		protected StringInternalLocalFlag(String name, String defaultVal, String comment, String[] options) 
		{
			super( name,  defaultVal,  comment,options);
		}
		
		protected StringInternalLocalFlag(String name, String defaultVal, String comment, String[] options, boolean isSystem) 
		{
			super( name,  defaultVal,  comment,options,isSystem);
		}
		
		protected  void setValue(String newVal)
		{
			flagValue = newVal;
		}
		
		public String getValue()
		{
			if(null==flagValue)
				return super.getDefaultVal();
			return flagValue;
		}


	}
	//************************
	// The Internal flag classes
	//************************
	public static class StringInternalFlag extends AFlag
	{
		protected String defaultVal;

		public StringInternalFlag(String name, String defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;					
		}

		public StringInternalFlag(String name, String defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}

		public StringInternalFlag(String name, String defaultVal, String comment, String[] options) 
		{
			this(name, defaultVal, comment);
			this.options = options;
		}
		
		public StringInternalFlag(String name, String defaultVal, String comment, String[] options, boolean isSystem) 
		{
			this(name, defaultVal, comment, options);
			this.isSystem = isSystem;		
		}

		protected void setValue(String newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(this.getName(), newVal, this.getDefaultVal(), this.isSystem());
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);
			}			
		}		
		public String getValue()
		{	
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return this.getDefaultVal();
			
			return value;			
		}
		public String getDefaultVal()
		{
			return defaultVal;
		}
				
		//This is an slightly odd convenience method used for flags
		//that return a configurable label. At times the code will want the plural version of the label value
		public String getValue(boolean pluralVersion) 
		{
			if (pluralVersion)
			{
				if (this.name.equals("DISPLAY_NAME_LOCATION") && this.getValue().equals("Location")) return "Locations"; 
				if (this.name.equals("DISPLAY_NAME_LOCATION") && this.getValue().equals("Hospital")) return "Hospitals"; 
				if (this.name.equals("DISPLAY_NAME_SERVICE") && this.getValue().equals("Service")) return "Services"; 
				if (this.name.equals("DISPLAY_NAME_SERVICE") && this.getValue().equals("Specialty")) return "Specialties"; 
				if (this.name.equals("DISPLAY_NAME_SERVICE") && this.getValue().equals("Discipline/Department")) return "Disciplines/Departments"; 
				if (this.name.equals("DISPLAY_NAME_ACTIVITY") && this.getValue().equals("Activity")) return "Activities"; 
				if (this.name.equals("DISPLAY_NAME_ACTIVITY") && this.getValue().equals("Procedure")) return "Procedures";
				if (this.name.equals("DISPLAY_NAME_FUNCTION") && this.getValue().equals("Function")) return "Functions"; 
				if (this.name.equals("DISPLAY_NAME_FUNCTION") && this.getValue().equals("Clinic Type")) return "Clinic Types"; 
				
			}
			return this.getValue();
		}	
		
		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();	
			else if (newVal instanceof String)
				setValue((String) newVal);
		}

		public String getValAsString()
		{
			return getValue();
		}

		public String getDefaultValAsString()
		{
			return this.defaultVal;
		}
		
		public FlagType getType()
		{
			return FlagType.STRING;
		}
		public Object getObjectVal()
		{
			return this.getValue();
		}

		public void setToDefault()
		{
			setValue(defaultVal);		
		}
		public void setDefaultValue(String value)
		{
			defaultVal = value;
		}
	}
		
	public static class IntInternalFlag extends AFlag
	{
		private int defaultVal;

		public IntInternalFlag(String name, int defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;					
		}

		public IntInternalFlag(String name, int defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}

		private void setValue(String newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(this.getName(), newVal, String.valueOf(this.getDefaultVal()), this.isSystem());				
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);				
			}	
		}

		private void setValue(Integer newVal)
		{
			setValue(String.valueOf(newVal));	
		}

		public int getValue()
		{
			return Integer.parseInt(getValAsString());
		}

		public int getDefaultVal()
		{
			return defaultVal;
		}
		
		public String getValAsString()
		{
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return getDefaultValAsString();
			return value;
		}
				
		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();		
			else if (newVal instanceof Integer)
				setValue((Integer) newVal);
			else if (newVal instanceof String)
				setValue((String)newVal);
		}

		public String getDefaultValAsString()
		{
			return "" + defaultVal;
		}
	
		public FlagType getType()
		{
			return FlagType.INT;
		}
		public Object getObjectVal()
		{
			return getValue();
		}
		public void setToDefault()
		{
			setValue(defaultVal);
		}	
		public void setDefaultValue(String value)
		{
			defaultVal = Integer.parseInt(value);
		}
	}
		
	public static class DecimalInternalFlag extends AFlag
	{
		private float defaultVal;

		protected DecimalInternalFlag(String name, float defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;				
		}

		protected DecimalInternalFlag(String name, float defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}
	
		public float getDefaultVal()
		{
			return defaultVal;
		}

		public float getValue()
		{
			return Float.parseFloat(getValAsString());
		}
		
		public String getValAsString()
		{
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return getDefaultValAsString();
			return value;
		}
		
		private void setValue(String newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(this.getName(), newVal, String.valueOf(this.getDefaultVal()), this.isSystem());				
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);				
			}
		}
 		private void setValue(Float newVal)
		{
 			setValue(String.valueOf(newVal));
		}
		
		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();			
			else if (newVal instanceof Float)
				setValue((Float)newVal);
			else if (newVal instanceof String)
				setValue((String)newVal);
		}
		public String getDefaultValAsString()
		{
			return "" + defaultVal;
		}
		public FlagType getType()
		{
			return FlagType.DECIMAL;
		}
		public Object getObjectVal()
		{
			return getValue();
		}
		public void setToDefault()
		{
			setValue(getDefaultValAsString());			
		}		
		public void setDefaultValue(String value)
		{
			defaultVal = Float.parseFloat(value);
		}
	}
		
	public static class BooleanInternalFlag extends AFlag
	{
		private boolean defaultVal;

		public BooleanInternalFlag(String name, boolean defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;					
		}

		public BooleanInternalFlag(String name, boolean defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}

		public boolean getDefaultVal()
		{
			return defaultVal;
		}
		public boolean getValue()
		{
			return Boolean.parseBoolean(getValAsString());
		}
		
		public String getValAsString()
		{
			String value = getConfigFlagsProvider().getValue(name);
			if(value == null)
				return Boolean.toString(defaultVal);
			
			return value;
		}

		private void setValue(Boolean newVal)
		{
			setValue(String.valueOf(newVal));
		}
		
		private void setValue(String newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(name, newVal, String.valueOf(defaultVal), isSystem);
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);
			} 
		}

		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();				
			else if (newVal instanceof Boolean)
				setValue((Boolean) newVal);
			else if (newVal instanceof String)
				setValue((String)newVal);
		}
		
		public String getDefaultValAsString()
		{
			return "" + defaultVal;
		}
		public FlagType getType()
		{
			return FlagType.BOOL;
		}
		public Object getObjectVal()
		{
			return getValue();
		}
		public void setToDefault()
		{
			setValue(defaultVal);			
		}		
		public void setDefaultValue(String value)
		{
			defaultVal = Boolean.parseBoolean(value);
		}
	}
		
	public static class ColourInternalFlag extends AFlag
	{
		private Color defaultVal;

		public ColourInternalFlag(String name, Color defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;				
		}

		protected ColourInternalFlag(String name, Color defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}

		public Color getDefaultVal()
		{
			return defaultVal;
		}

		public Color getValue()
		{
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return getDefaultVal();
			return Color.getColor(value);
		}
		
		public String getValAsString()
		{
			return getValue().getName();
		}

		public boolean hasOptions()
		{
			return false;
		}
		
		private void setValue(Color newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(this.getName(), newVal.getName(), this.getDefaultVal().getName(), this.isSystem());
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);
			}
		}
		
		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();				
			else if (newVal instanceof Color)
				setValue((Color) newVal);
			else if (newVal instanceof String)
				setValue(Color.getColor((String)newVal));
		}
		
		public String getDefaultValAsString()
		{
			return this.defaultVal.getName();
		}
		public FlagType getType()
		{
			return FlagType.COLOUR;
		}
		public Object getObjectVal()
		{
			return this.getValue();
		}
		public void setToDefault()
		{
			setValue(getDefaultVal());		
		}		
		public void setDefaultValue(String value)
		{
			defaultVal = Color.getColor(value);
		}
	}
	
	public static class FormInternalFlag extends AFlag
	{
		private FormName defaultVal;

		public FormInternalFlag(String name, FormName defaultVal, String comment) 
		{
			this.name = name;
			this.defaultVal = defaultVal;
			this.comment = comment;
			this.isSystem = false;					
		}

		protected FormInternalFlag(String name, FormName defaultVal, String comment, boolean isSystem) 
		{
			this(name, defaultVal, comment);
			this.isSystem = isSystem;		
		}

		public FormName getDefaultVal()
		{
			return defaultVal;
		}

		public FormName getValue()
		{
			String value = getConfigFlagsProvider().getValue(this.getName());
			if(value == null)
				return getDefaultVal();
			return (FormName)CfgFlag.getValue(FlagType.FORM, value);
		}
		
		public String getValAsString()
		{
			return this.getValue().toString();
		}

		private void setValue(FormName newVal)
		{
			try 
			{
				getConfigFlagsProvider().setValue(this.getName(), String.valueOf(newVal.getID()), String.valueOf(this.getDefaultVal().getID()), this.isSystem());
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("Error setting '" + newVal + "' for the flag " + this.getName(), e);
			}
		}
		
		public void setValue(Object newVal)
		{
			if (newVal == null)
				setToDefault();				
			else if (newVal instanceof FormName)
				setValue((FormName) newVal);
			else if (newVal instanceof String)
				setValue((FormName)CfgFlag.getValue(FlagType.FORM, (String)newVal));
		}
		public String getDefaultValAsString()
		{
			return this.defaultVal.toString();
		}
		public FlagType getType()
		{
			return FlagType.FORM;
		}
		public Object getObjectVal()
		{
			return this.getValue();
		}
		public void setToDefault()
		{
			setValue(getDefaultVal());			
		}		
		public void setDefaultValue(String value)
		{
			defaultVal = (FormName)CfgFlag.getValue(FlagType.FORM, value);
		}
	}

	public static final StringFlag DISPLAY_CHOICE = new StringFlag(new StringInternalLocalFlag("DISPLAY_CHOICE", "ALL", "Which Config Flag groups to show."));
	//public static final StringFlag APPSERVER_TYPE = new StringFlag(new StringInternalLocalFlag("APPSERVER_TYPE", AppServer.TOMCAT, "The type of J2EE AppServer in use.", AppServer.getAllAppServerNames(), true));
	public static final StringFlag APP_CONTEXT_NAME = new StringFlag(new StringInternalLocalFlag("APP_CONTEXT_NAME", "", "Required in order to construct correct URL to web services deployed at this instance.", true));
	//public static final StringFlag SOURCE_INSTANCE = new StringFlag(new StringInternalLocalFlag("SOURCE_INSTANCE", "IMS_DEV", "Used for date export/import to identify the source of XML objects", true));
	//public static final StringFlag BASE_URI = new StringFlag(new StringInternalLocalFlag("BASE_URI", "", "The base URI for the running app for use within Image to load Image Info.", true));
	public static final StringFlag HOST_NAME = new StringFlag(new StringInternalLocalFlag("HOST_NAME", "", "Hostname of this server. Set at startup by CNHost", true));
	public static final StringFlag START_TIME = new StringFlag(new StringInternalLocalFlag("START_TIME", "", "Application startup time. Set at startup by CNHost", true));
	public static final StringFlag DBTYPE = new StringFlag(new StringInternalLocalFlag("DBTYPE", "", "Database type of main datasource. Set at startup by Registry", true));
	public static final StringFlag DBNAME = new StringFlag(new StringInternalLocalFlag("DBNAME", "", "Database name of main datasource. Set at startup by Registry", true));
	public static final BooleanFlag ENABLE_DB_CONNECTION_TRACE = new BooleanFlag(new BooleanInternalFlag("ENABLE_DB_CONNECTION_TRACE", false, "Switch on/off database connection tracing. Oracle support only for now."));

	
	
	@SuppressWarnings("unchecked")
	public static AFlag[] getFlags(Class clazz) 
	{
		ArrayList flags = new ArrayList();
		Field[] flds = clazz.getFields();
		for (int i = 0; i < flds.length; i++)
		{
			Class c = flds[i].getType();
			if (PublicFlag.class.isAssignableFrom(c))
			{
				try 
				{
					PublicFlag pf = (PublicFlag)flds[i].get(null);
					if(pf.isVisible())
						flags.add(pf.getInternalFlag());
				}
				catch (IllegalArgumentException e) 
				{
					return null;
				}
				catch (IllegalAccessException e) 
				{
					return null;
				}
			}
		}
		Collections.sort(flags, new IFlagComparator(SortOrder.DESCENDING, true));
		AFlag[] cfgs = new AFlag[flags.size()];
		flags.toArray(cfgs);
		return cfgs;		
	}		

	
	public static String[] getLookupInstanceNames(String className)
	{
		try
		{
			Class c = Class.forName(className);
			Method m = c.getMethod("getNegativeInstanceNames", (Class[])null);
			String[] ret = (String[])m.invoke((Object)null, (Object[])null);
			return ret;						
		}
		catch(ClassNotFoundException ex)
		{			
			return new String[0];
		}	
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new String[0];
		}	
	}

	private static void setAllToDefault()
	{
		setToDefault(FW.class);
		setToDefault(DOM.class);
		setToDefault(GEN.class);
		setToDefault(DTO.class);
		setToDefault(HL7.class);
		setToDefault(UI.class);
		setToDefault(RCHTIM.class);
		DISPLAY_CHOICE.getInternalFlag().setToDefault();
	}
	protected static void setToDefault(Class clazz)
	{
		AFlag[] flags = getFlags(clazz);
		for (int i = 0; i < flags.length; i++)
		{
			flags[i].setToDefault();
		}
	}
	public static void reloadFlags()
	{
		setAllToDefault();
	}

	public static AFlag getConfigFlag(String name)
	{
		if (name == null) return null;
		if (name.equals("DISPLAY_CHOICE")) return DISPLAY_CHOICE.getInternalFlag();			
		
		AFlag[] flags = getFlags(UI.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}
		
		flags = getFlags(FW.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}
		
		flags = getFlags(DOM.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}
		
		flags = getFlags(GEN.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}
		
		flags = getFlags(DTO.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}

		flags = getFlags(HL7.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}

		flags = getFlags(RCHTIM.class);
		for (int i = 0; i < flags.length; i++)
		{
			AFlag flag = flags[i];
			if (flag.getName().equals(name)) return flag;
		}
		return null;
	}

	public static IFlag saveFlag(IFlag flag, String flagName, Object flagValue) throws Exception
	{
		//AFlag flag = ConfigFlag.getConfigFlag(flagName);
		
		//if (!flagsLoaded) 
		//	return flag;
		if (flag == null) 
			return flag;
		
		flag.setValue(flagValue);
		
		
		String valStr = flag.getValAsString();
		if (flag.getName().equals(ConfigFlag.ENABLE_DB_CONNECTION_TRACE.getName()))
		{
			//Don't want this flag to be persisted. Want it always to revert to false on restart.
			return flag;
		}
				
		InputSource src=null;
		if (!InitConfig.getFlagFileLocation().equals(""))
		{
			src = new InputSource(flagFileName);
		}
		else if (flagFileName != null)
		{
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(flagFileName);		
			if (stream == null)
			{
				return flag;
			}
			src = new InputSource(stream);
		}
		
		if (src != null)
		{
		Document document = new SAXReader().read(src);
		Element el = document.getRootElement();
		List flagLst = document.selectSingleNode("ConfigFlags").selectNodes("flag");
		int i=0;
		boolean flagFound=false;
		while (i < flagLst.size() && !flagFound)
		{
			DefaultElement defEl = (DefaultElement) flagLst.get(i);
			Attribute at =  defEl.attribute("name");
			// Not a valid type, con
			if (at != null && flagName.equals(at.getValue()))
			{
				// If new value is the same as default, remove from config..				
				if (valStr.equals(flag.getDefaultValAsString()))
				{
					defEl.remove(defEl.attribute("name"));
					defEl.remove(defEl.attribute("value"));
					el.remove(defEl);
				}
				else
				{
					if(defEl.attribute("value") != null)
						defEl.attribute("value").setValue(valStr);
					else
					{
						defEl.add(new DefaultAttribute("value", valStr));
					}
				}
				flagFound=true;
			}
			i++;
		}			
		// If the flag was not found, we want to add it to the file
		if (!flagFound)
		{
			DefaultElement newEl = new DefaultElement("flag");
			newEl.add(new DefaultAttribute("name", flagName));
			newEl.add(new DefaultAttribute("value", valStr));
			el.add(newEl);
		}
		//FWB-108 Sort configflags before saving them
		XPath xpath = DocumentHelper.createXPath("//flag");
		XPath sortXPath = DocumentHelper.createXPath("@name");
		flagLst = xpath.selectNodes(document, sortXPath);
		  
		 el.clearContent();
		 for (int j=0;j<flagLst.size();j++)
		  {
			 el.add((Element)flagLst.get(j));
		  }

		OutputFormat format = OutputFormat.createPrettyPrint();
		OutputStream out = null;
		if (!InitConfig.getFlagFileLocation().equals(""))
		{
			out = new FileOutputStream(flagFileName);
		}
		else
		{
			URL url = Thread.currentThread().getContextClassLoader().getResource(flagFileName);
			out = new FileOutputStream(url.getFile());
		}
		
		XMLWriter  writer = new XMLWriter( out, format );
		writer.write( document );
		writer.close();
		}
		return flag;
		
	}	
	
	public static void setValue(PublicFlag flag, Object value)
	{
		flag.getInternalFlag().setValue(value);		
	}
		
	/*
	 * returns the full path and name of the config flags file.
	 * if this returns just the file name it indicates that the config flags are 
	 * loaded from the classpath.
	 */
	public static String getConfigFlagFullFilename()
	{
		return flagFileName;
	}
	
	public static AFlag[] getAllFlags()
	{
		ArrayList<AFlag> allFlags = new ArrayList<AFlag>();
		
		addToList(allFlags, getFlags(FW.class));
		addToList(allFlags, getFlags(DOM.class));
		addToList(allFlags, getFlags(UI.class));
		addToList(allFlags, getFlags(HL7.class));
		addToList(allFlags, getFlags(GEN.class));
		addToList(allFlags, getFlags(DTO.class));

		AFlag[] ret = new AFlag[allFlags.size()];
		allFlags.toArray(ret);
		return ret;
	}

	private static void addToList(ArrayList<AFlag> allFlags, AFlag[] flags)
	{
		for (int i = 0; i < flags.length; i++)
		{
			allFlags.add(flags[i]);
		}
	}
	
	public static boolean flagsAreLoaded()
	{
		return flagsLoaded;	
	}
	
	public static class UI
	{
		public static final IntFlag MAX_NO_SELECTED_LOCATIONS_STORED = new IntFlag(new IntInternalFlag("MAX_NO_SELECTED_LOCATIONS_STORED", 10, "The maximum number of location selected/stored in history ", true));
		public static final IntFlag HEART_BEAT_TIMER = new IntFlag(new IntInternalFlag("HEART_BEAT_TIMER", 0, "Specifies the interval in minutes after which the application will send empty request"));
		public static final FormFlag DEMOGRAPHICS_FORM = new FormFlag(new FormInternalFlag("DEMOGRAPHICS_FORM", (FormName)CfgFlag.getValue(FlagType.FORM,"102101"), "Demographics form to load from Patient Search", true));
		public static final IntFlag AUTO_LOCK_TIMER = new IntFlag(new IntInternalFlag("AUTO_LOCK_TIMER", 0, "Specifies the interval in minutes after which the application will auto lock"));
		public static final StringFlag DEMOGRAPHICS_TYPE = new StringFlag(new StringInternalFlag("DEMOGRAPHICS_TYPE", "IRISH", "Which type of demographics form should be displayed", new String[]{"IRISH", "UK"}, true));
		public static final StringFlag DISPLAY_PATID_TYPE = new StringFlag(new StringInternalFlag("DISPLAY_PATID_TYPE", "HOSPNUM", "Which Patient Identifier Type is selected by default on Patient Search Form, and which is displayed on Patient Info bar", getLookupInstanceNames("ims.core.vo.lookups.PatIdType")));
		public static final StringFlag RIP_INFO_PREFIX = new StringFlag(new StringInternalFlag("RIP_INFO_PREFIX", "", "Patient Info prefix characters to indicate RIP"));
		public static final BooleanFlag DISPLAY_PATIENT_NAME_IN_PROPER_CASE =  new BooleanFlag(new BooleanInternalFlag("DISPLAY_PATIENT_NAME_IN_PROPER_CASE",false,"PID Bar - Display the name of the patient in proper case."));//FWB-297
		public static final BooleanFlag HAS_PAEDIATRIC_PATIENTS  = new BooleanFlag(new BooleanInternalFlag("HAS_PAEDIATRIC_PATIENTS",false,"If true, the format of the displayed age on PID bar will change to reflect Paediatric ages."));//FWB-269
		public static final StringFlag RICHTEXT_CONTROL_DEFAULT_FONT_NAME = new StringFlag(new StringInternalFlag("RICHTEXT_CONTROL_DEFAULT_FONT_NAME","Arial","Richtext control default font name",new String[]{"Tahoma","Arial", "Courier New", "Times New Roman", "Verdana", "Book Antiqua", "Garamond", "Book Antiqua", "Lucida Sans Unicode", "Lucida Grande", "Comic Sans MS"}));
		public static final StringFlag RICHTEXT_CONTROL_DEFAULT_FONT_SIZE = new StringFlag(new StringInternalFlag("RICHTEXT_CONTROL_DEFAULT_FONT_SIZE","12px","Richtext control default font size",new String[]{"12px","14px", "16px", "20px", "24px", "32px", "42px"}));
	}
	public static class FW
	{
		public static final BooleanFlag ENABLE_CCOW = new BooleanFlag(new BooleanInternalFlag("ENABLE_CCOW", false, "Enable/Disable CCOW patient context sharing with other applications on the same desktop"));
		public static final StringFlag SMTP_SERVER = new StringFlag(new StringInternalFlag("SMTP_SERVER", "smtp.ie.imsgrp.net", "Specify the email SMTP server"));
		public static final StringFlag SMTP_SENDER = new StringFlag(new StringInternalFlag("SMTP_SENDER", "", "Specify the email sender"));
		public static final StringFlag SMTP_AUTH = new StringFlag(new StringInternalFlag("SMTP_AUTH", "", "If valued, SMTP authentication will be applied. Value is in the format username:password"));
		public static final IntFlag SMTP_PORT  = new IntFlag(new IntInternalFlag("SMTP_PORT", 2525, "Specify the email SMTP server port"));		
		public static final BooleanFlag REQUEST_LOGGING = new BooleanFlag(new BooleanInternalFlag("REQUEST_LOGGING", false, "Turns on/off logging of incoming http request messages.", true));
		public static final BooleanFlag MAIL_FATAL = new BooleanFlag(new BooleanInternalFlag("MAIL_FATAL", false, "Turns on/off automatic mailing of fatal errors to IMS Support.", true));
		public static final StringFlag DOMAIN = new StringFlag(new StringInternalFlag("DOMAIN", "imsmaxims.com", "Domain name of the site."));
		public static final StringFlag FATAL_TO = new StringFlag(new StringInternalFlag("FATAL_TO",  "coedev@imsmaxims.com", "Addresses to send Fatal Error mail to. Comma seperate if multiple."));
		public static final IntFlag PASSWD_MIN_LEN = new IntFlag(new IntInternalFlag("PASSWD_MIN_LEN", 0, "Sets the minimum length user passwords must be. 0 indicates no minimum."));
		public static final IntFlag PASSWD_MAX_LEN = new IntFlag(new IntInternalFlag("PASSWD_MAX_LEN", 0, "Sets the maximum length user passwords must be. 0 indicates no maximum."));  // FWB-433
		public static final IntFlag PASSWD_REUSE = new IntFlag(new IntInternalFlag("PASSWD_REUSE", 0, "Sets the number of previous passwords that cannot be reuse. 0 indicates no check."));
		public static final StringFlag DEBUG_HTTP_REQUEST_AND_RESPONSE  = new StringFlag(new StringInternalFlag("DEBUG_HTTP_REQUEST_AND_RESPONSE","NONE","Debug HTTPServlet request and response", getLookupInstanceNames("ims.admin.vo.lookups.HTTPServletDebug")));
		public static final IntFlag NUMBER_OF_DAYS_BEFORE_PASSWORD_EXPIRATION  = new IntFlag(new IntInternalFlag("NUMBER_OF_DAYS_BEFORE_PASSWORD_EXPIRATION", 14, "Sets the number of days before user passwords expire."));
		public static final IntFlag NUMBER_OF_DAYS_AFTER_PASSWORD_EXPIRED  = new IntFlag(new IntInternalFlag("NUMBER_OF_DAYS_AFTER_PASSWORD_EXPIRED", 14, "Sets the number of days after user passwords expire."));
		public static final StringFlag PASSWORD_EXPIRED_MESSAGE  = new StringFlag(new StringInternalFlag("PASSWORD_EXPIRED_MESSAGE","Your password has expired, please contact the administrator to reset your password!","Password expired message"));		 
	}
	public static class DOM
	{
		public static final BooleanFlag READ_AUDIT_ENABLED = new BooleanFlag(new BooleanInternalFlag("READ_AUDIT_ENABLED", false, "Turns on/off Read Audit Functionality"));
		public static final StringFlag INTERFACE_PATID_TYPE = new StringFlag(new StringInternalFlag("INTERFACE_PATID_TYPE", "HOSPNUM", "Which Patient ID type is used by Default on Interface calls.", getLookupInstanceNames("ims.core.vo.lookups.PatIdType")));
		public static final IntFlag ASSESSMENT_COMPONENT_NO = new IntFlag(new IntInternalFlag("ASSESSMENT_COMPONENT_NO", 16, "Specifies the number of assessment components for the application", true));		
		public static final BooleanFlag AUDIT_PATIENT_FROM_OBJECT = new BooleanFlag(new BooleanInternalFlag("AUDIT_PATIENT_FROM_OBJECT", false, "If TRUE, will check the object being audited for patient reference if the global context is null."));  // WDEV-15061
		public static final BooleanFlag PATHWAY_ENTITY_EVENT_FUNCTIONALITY = new BooleanFlag(new BooleanInternalFlag("PATHWAY_ENTITY_EVENT_FUNCTIONALITY",false,"The Interceptor will check to see if an object being saved is a candidate of PathwayEntityEvent and create an event (which may be rule based)", true)); // FWB-473
		public static final BooleanFlag ENTITY_TARGETMETHOD_FUNCTIONALITY =new BooleanFlag(new BooleanInternalFlag("ENTITY_TARGETMETHOD_FUNCTIONALITY",false,"The Interceptor will check to see if an object being saved is a candidate of TargetMethod invocation", true)); // FWB-240
		public static final StringFlag USE_PDS = new StringFlag(new StringInternalFlag("USE_PDS", "None", "Controls how the PDS service is used.", new String[]{"None","Primary"},true));  //http://jira/browse/FWB-593		
	}
	public static class GEN
	{
		public static final StringFlag SESSION_LOG_LOCATION = new StringFlag(new StringInternalFlag("SESSION_LOG_LOCATION",  "", "Specifies location to place individual session log files."));
		public static final StringFlag QUERY_SERVER_URL = new StringFlag(new StringInternalFlag("QUERY_SERVER_URL", "http://localhost:8080/ImsQueryServer", "URL of IMS Query Server"));
		public static final StringFlag REPORT_SERVER_URL = new StringFlag(new StringInternalFlag("REPORT_SERVER_URL", "http://localhost/Scripts/ImsReportServerCgi.exe/", "URL of IMS Report Server"));
		public static final BooleanFlag RELEASE_MODE = new BooleanFlag(new BooleanInternalFlag("RELEASE_MODE", true, "Specfies whether the application is to operate in production or development mode. Affects exception handling to prevent application stopping on error.", true));
		public static final StringFlag LOG_FILE = new StringFlag(new StringInternalFlag("LOG_FILE","stdout" , "Log file for application logging. Default location is TOMCAT_HOME/logs."));
		public static final StringFlag LOGGING_LEVEL = new StringFlag(new StringInternalFlag("LOGGING_LEVEL", "WARN", "Selects the Logging Level that will be used throughout the Application.", new String[] {"FATAL", "ERROR", "WARN", "INFO", "DEBUG"} ));
		public static final StringFlag PDF_UPLOAD_URL = new StringFlag(new StringInternalFlag("PDF_UPLOAD_URL", "", "URL for upload pdf servlet"));		
		public static final StringFlag IMAGES_UPLOAD_URL = new StringFlag(new StringInternalFlag("IMAGES_UPLOAD_URL", "", "URL for upload images servlet"));
		public static final StringFlag UPLOAD_URL = new StringFlag(new StringInternalFlag("UPLOAD_URL", "", "URL for upload servlet"));
		public static final BooleanFlag ICAB_ENABLED = new BooleanFlag(new BooleanInternalFlag("ICAB_ENABLED", false, "Is the Choose and Book Interface Enabled", true));
		public static final IntFlag SESSION_TIMEOUT = new IntFlag(new IntInternalFlag("SESSION_TIMEOUT", 30, "Application session idle timeout, in minutes"));
		public static final BooleanFlag ALWAYS_PROMPT_FOR_SYSTEM_PASSWORD = new BooleanFlag(new BooleanInternalFlag("ALWAYS_PROMPT_FOR_SYSTEM_PASSWORD", false, "Determines if user will be asked for password every time they enter a system form, or just on the first time.", true));
		public static final StringFlag FILE_UPLOAD_DIR = new StringFlag(new StringInternalFlag("FILE_UPLOAD_DIR", "uploads", "Specifies location to place uploaded files."));
		public static final StringFlag PDF_STORE_PATH = new StringFlag(new StringInternalFlag("PDF_STORE_PATH", "D:/files/", "Path to location where pdf will be stored"));		
		public static final StringFlag ARCHIVE_STORE_PATH = new StringFlag(new StringInternalFlag("ARCHIVE_STORE_PATH", "D:/archive/", "Path to location where archived files will be stored"));
		public static final StringFlag LOGIN_MESSAGE = new StringFlag(new StringInternalFlag("LOGIN_MESSAGE", "", "Message to be displayed at the login screen like 'Computer Misuse Act 1990 - Unauthorised access to this system is an offence'"));
		public static final StringFlag PATIENT_DASHBOARD_URL = new StringFlag(new StringInternalFlag("PATIENT_DASHBOARD_URL", "", "URL for Patient Dashboard"));
		public static final IntFlag LOGIN_ATTEMPTS = new IntFlag(new IntInternalFlag("LOGIN_ATTEMPTS", 0, "Number of times in succession a user can make an incorrect login before their account is locked"));
		public static final StringFlag LOGIN_ATTEMPTS_MESSAGE = new StringFlag(new StringInternalFlag("LOGIN_ATTEMPTS_MESSAGE", "Account locked: Too many failed login attempts. Please contact your administrator.", "Message to display after 3 (default LOGIN_ATTEMPTS config flag value) number of unsuccessful login attempts."));
		public static final StringFlag PDS_ROLE_ASSERTION_SERVLET_URL  = new StringFlag(new StringInternalFlag("PDS_ROLE_ASSERTION_SERVLET_URL", "", "PDS Functionality: RoleAssertion servlet URL used for SAML xml retrieval"));
	}
	public static class DTO
	{
		public static final StringFlag CONNECTION_POOL_TYPE = new StringFlag(new StringInternalFlag("CONNECTION_POOL_TYPE", "APACHE", "Type of Connection pool implementation to use SYNCHLIST/APACHE", new String[] {"SYNCHLIST", "APACHE"}, true));
		public static final StringFlag DTO_CONNECTION_TYPE = new StringFlag(new StringInternalFlag("DTO_CONNECTION_TYPE", "MAXIMS", "Type of dto connection either Maxims or Hearts", new String[] {"MAXIMS", "HEARTS"}, true));
		public static final StringFlag SERVER_COMPATIBILITY = new StringFlag(new StringInternalFlag("SERVER_COMPATIBILITY", "java", "DTO Server Compatibility - c/java", new String[] {"java", "c"}, true));
		public static final BooleanFlag CONNECTION_POOL = new BooleanFlag(new BooleanInternalFlag("CONNECTION_POOL", true, "Use Connection pool", true));
		public static final BooleanFlag SHARE_LOGIN = new BooleanFlag(new BooleanInternalFlag("SHARE_LOGIN", true, "Share Login Details", true));
		public static final IntFlag CONNECTION_POOL_MAXACTIVE = new IntFlag(new IntInternalFlag("CONNECTION_POOL_MAXACTIVE", 8, "Maximum number of pooled connections", true));
		public static final IntFlag CONNECTION_POOL_MAXIDLE = new IntFlag(new IntInternalFlag("CONNECTION_POOL_MAXIDLE", 8, "Maximum number of idle pooled connections", true));
		public static final StringFlag CONNECTION_POOL_WHEN_EXHAUSTED_ACTION = new StringFlag(new StringInternalFlag("CONNECTION_POOL_WHEN_EXHAUSTED_ACTION", "grow", "Action to take when exhausted pool encountered - block/grow/fail", new String[] {"block", "grow", "fail"}, true));
		public static final IntFlag CONNECTION_POOL_MAXWAIT = new IntFlag(new IntInternalFlag("CONNECTION_POOL_MAXWAIT", -11, "Maximum wait for connection - only used with block exhausted action", true));		

	}
	public static class HL7
	{
	}
	public static class RCHTIM
	{
	}
}


