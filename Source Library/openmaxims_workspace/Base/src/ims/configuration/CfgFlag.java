package ims.configuration;

import ims.framework.FormNameImpl;
import ims.framework.utils.Color;
import ims.framework.utils.Date;
import ims.framework.utils.DateTime;
import ims.framework.utils.DateTimeFormat;
import ims.framework.utils.ImagePath;
import ims.framework.utils.Time;
import ims.vo.LookupInstVo;
import ims.vo.LookupTypeVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

public class CfgFlag
{
	private static HashMap<String, FlagCategory> cats = new HashMap<String, FlagCategory>();	
	private static HashMap<String, CfgFlag> flags = new HashMap<String, CfgFlag>();
	private static boolean projectFlagsLoaded = false;
	private static boolean siteFlagsLoaded = false;
	private static String flagFileName;	
	
	private String key;
	private String name;
	private FlagType type;
	private Object defaultVal;
	private Object value;
	private String comment;
	private boolean isSystem = false;
	private boolean isEnum = false;
	private HashMap options;
	
	private static void addFlag(CfgFlag flag)
	{
		flags.put(flag.getKey(), flag);
	}
	
	private static void addCat(FlagCategory cat)
	{
		cats.put(cat.getKey(), cat);
	}
	
	public static FlagCategory getCat(String key)
	{
		return cats.get(key);
	}

	public static FlagCategory[] listCats()
	{
		FlagCategory[] ret = new FlagCategory[cats.size()];
		int i = 0;
		for (Iterator iter = cats.values().iterator(); iter.hasNext();)
		{
			ret[i] = (FlagCategory)iter.next();	
			i++;
		}
		return ret;
	}

	public CfgFlag(String key, String name, FlagType type, Object defaultVal, String comment, boolean isSystem, boolean isEnum, HashMap opts)
	{
		this.key = key;
		this.name = name;
		this.type = type;
		this.defaultVal = defaultVal;
		this.comment = comment;
		this.isSystem = isSystem;
		this.isEnum = isEnum;
		this.options = opts;
		this.value = defaultVal;
	}
	
	public static CfgFlag getFlag(String key)
	{
		CfgFlag flag = flags.get(key);
		if (flag == null) 
			throw new RuntimeException("Flag with key = " + key + " does not exist!");
		return flag;
	}	
	
	@SuppressWarnings("unchecked")
	public static boolean loadProjectFlags() throws Exception
	{
		if (projectFlagsLoaded) return true;
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ImsProjectFlags.xml");
		if (stream == null)
		{
			//throw new Exception("ImsProjectFlags.xml not found. Application cannot start.");
			return false;
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(new InputSource(stream));

		List catLst = document.selectSingleNode("config").selectNodes("category");
		for (Iterator catIter = catLst.iterator(); catIter.hasNext();)
		{
			Element catEl = (Element)catIter.next();
			String catKey = catEl.attributeValue("key");
			String catName = catEl.attributeValue("name");
			FlagCategory cat = new FlagCategory(catKey, catName);
			addCat(cat);			
			
			List flagLst = catEl.selectNodes("flag");
			for (Iterator flagIter = flagLst.iterator(); flagIter.hasNext();)
			{
				Element flagEl = (Element)flagIter.next();
				String flagKey = flagEl.attributeValue("key");
				String flagName = flagEl.attributeValue("name");
				FlagType flagType = FlagType.getFlagType(flagEl.attributeValue("type"));				
				boolean flagSys = flagEl.attributeValue("sys", "false").equalsIgnoreCase("true");
				boolean flagEnum = flagEl.attributeValue("enum", "false").equalsIgnoreCase("true");
				String flagComment = flagEl.attributeValue("desc");
				Object defVal = getValue(flagType, flagEl.attributeValue("def"));
				
				HashMap opts = null;
				List optLst = flagEl.selectNodes("opt");
				if (optLst != null && optLst.size() > 0)
				{
					opts = new HashMap();
					for (Iterator optIter = optLst.iterator(); optIter.hasNext();)
					{
						Element optEl = (Element)optIter.next();
						opts.put(optEl.attributeValue("val"),getValue(flagType, optEl.attributeValue("val")));
					}				
				}
				CfgFlag flag = new CfgFlag(flagKey, flagName, flagType, defVal, flagComment, flagSys, flagEnum, opts);
				addFlag(flag);
				cat.addFlag(flag);
			}
		}	
		projectFlagsLoaded = true;
		return true;
	}
	
	private static void createNewFlagFile(String fileName) throws IOException
	{
		String xmlHead="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";

		if (InitConfig.getFlagFileLocation().equals(""))			
		{
			if ( System.getProperty("catalina.home") == null)
			{
				throw new FileNotFoundException("Configuration Flags File location not specified by either 'init_config.xml' or 'catalina.home'. Cannot continue;");				
			}
			fileName = System.getProperty("catalina.home") + "/common/classes/" + fileName;				
		}
		File newFile = new File(fileName);
		FileOutputStream fw = new FileOutputStream(newFile);
		fw.write(xmlHead.getBytes());
		fw.write("<ConfigFlags>\r\n".getBytes());
		fw.write("</ConfigFlags>".getBytes());
		fw.close();
		siteFlagsLoaded = true;
	}

	public static boolean loadSiteFlags(InputSource src) throws Exception
	{
		if (siteFlagsLoaded) return true;
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(src);

		List flagLst = document.selectSingleNode("ConfigFlags").selectNodes("flag");
		for (Iterator iter = flagLst.iterator(); iter.hasNext();)
		{
			Element defEl = (Element) iter.next();
			if (defEl.attributeCount() == 0)
				continue;
			CfgFlag sysFlag = CfgFlag.getFlag(defEl.attribute("key").getValue());
			String strVal = defEl.attribute("value").getValue();
			sysFlag.setValue(getValue(sysFlag.getType(), strVal));
		}	
		siteFlagsLoaded = true;		
		return false; //NOT already loaded
	}
	
	public static boolean loadSiteFlags(String filename) throws Exception
	{
		if (siteFlagsLoaded) return true;
		
		InitConfig.loadInitConfig(); //Will do nothing if it has already be called from elsewhere.
		boolean ret;

		if (!InitConfig.getFlagFileLocation().equals(""))
		{
			String path = InitConfig.getFlagFileLocation();
			if (!path.endsWith("/") && !path.endsWith("\\"))
			{
				path += "/";
			}
			String filePath = path + filename;
			File f = new File(filePath);
			if (!f.exists())
			{
				createNewFlagFile(filePath);
				ret = false;			
			}
			else
			{
				ret = loadSiteFlags(new InputSource(filePath));				
			}
			flagFileName = filePath;
		}
		else
		{
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);		
			if (stream != null)
			{				
				ret = loadSiteFlags(new InputSource(stream));
				flagFileName = filename;
			}
			else
			{
				createNewFlagFile(filename);
				flagFileName = filename;
				ret = false;						
			}			
		}
		return ret;
	}

	
	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public String getComment()
	{
		return comment;
	}

	public Object getDefaultVal()
	{
		return defaultVal;
	}

	public boolean isSystem()
	{
		return isSystem;
	}

	public boolean isEnum()
	{
		return isEnum;
	}

	public String getKey()
	{
		return key;
	}

	public String getName()
	{
		return name;
	}

	public Object[] getOptions()
	{
		if (options == null) return null;
		Object[] ret = new Object[options.size()];
		int i = 0;
		for (Iterator iter = options.values().iterator(); iter.hasNext(); i++)
		{
			ret[i] = iter.next();			
		}
		return ret;
	}

	public FlagType getType()
	{
		return type;
	}
	
	public static Object getValue(FlagType flagType, String strValue) 
	{
		if (strValue == null) return null;
		
		Object ret = null;
		
		if (flagType.equals(FlagType.STRING))
		{
			ret = strValue;
		}
		else if (flagType.equals(FlagType.INT))
		{
			ret = new Integer(strValue);
		}
		else if (flagType.equals(FlagType.DECIMAL))
		{
			ret = new Float(strValue);						
		}
		else if (flagType.equals(FlagType.BOOL))
		{
			ret = new Boolean(strValue);
		}
		else if (flagType.equals(FlagType.DATE))
		{
			try
			{
				ret = new Date(strValue);
			}
			catch (ParseException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if (flagType.equals(FlagType.TIME))
		{
			ret = new Time(strValue);
		}
		else if (flagType.equals(FlagType.DATETIME))
		{
			try
			{
				ret = new DateTime(strValue);
			}
			catch (ParseException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if (flagType.equals(FlagType.COLOUR))
		{
			ret = Color.getColor(strValue);
		}					
		else if (flagType.equals(FlagType.LKUP_INST))
		{
			ret = new LookupInstVo(Integer.parseInt(strValue));
		}					
		else if (flagType.equals(FlagType.LKUP_TYPE))
		{
			ret = new LookupTypeVo(Integer.parseInt(strValue));
		}					
		else if (flagType.equals(FlagType.FORM))
		{
			StringTokenizer st = new StringTokenizer(strValue, ",");
			if (st.countTokens() == 1)
			{
				ret = new FormNameImpl(Integer.parseInt(strValue));
			}
			else
			{
				ret = new FormNameImpl(Integer.parseInt(st.nextToken()), st.nextToken());
			}
		}					
		else if (flagType.equals(FlagType.IMAGE))
		{
			StringTokenizer st = new StringTokenizer(strValue, ",");
			if (st.countTokens() == 1)
			{
				ret = new ImagePath(Integer.parseInt(strValue), "");
			}
			else
			{
				ret = new ImagePath(Integer.parseInt(st.nextToken()), st.nextToken());
			}
		}					
		return ret;
	}
	
	public static void saveFlag(String flagKey, Object flagValue) throws Exception
	{
		if (!projectFlagsLoaded || !siteFlagsLoaded)
		{
			throw new Exception("Flags have not been loaded. Cannot save.");
		}
		CfgFlag flag = CfgFlag.getFlag(flagKey);
		String valStr = flagValue.toString();
		if (flagValue instanceof Color)
		{
			valStr = ((Color)flagValue).getName();
		}
		else if (flagValue instanceof DateTime)
		{
			valStr = ((DateTime)flagValue).toString(DateTimeFormat.ISO);
		}
		
		InputSource src;
		if (!InitConfig.getFlagFileLocation().equals(""))
		{
			src = new InputSource(flagFileName);
		}
		else
		{
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(flagFileName);		
			if (stream == null)
			{
				return;
			}
			src = new InputSource(stream);
		}
		
		Document document = new SAXReader().read(src);
		Element el = document.getRootElement();
		List flagLst = document.selectSingleNode("ConfigFlags").selectNodes("flag");
		int i=0;
		boolean flagFound=false;
		while (i < flagLst.size())
		{
			Element defEl = (Element) flagLst.get(i);
			Attribute at =  defEl.attribute("key");
			// Not a valid type, con
			if (at == null)
			{
				i++;
				continue;
			}
			if (flagKey.equals(at.getValue()))
			{
				if ((flagValue == null && flag.getDefaultVal() == null) || flagValue.equals(flag.getDefaultVal()))
				{
					defEl.remove(defEl.attribute("key"));
					defEl.remove(defEl.attribute("value"));
					el.remove(defEl);
				}
				else
				{
					defEl.addAttribute("value", valStr);
					i++;
				}
				flagFound=true;
			}
			else
				i++;
		}			
		// If the flag was not found, we want to add it to the file
		if (!flagFound)
		{
			Element newEl = DocumentFactory.getInstance().createElement("flag");
			newEl.addAttribute("key", flagKey).addAttribute("value", valStr);
			el.add(newEl);
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
		
		//The 'Live' flag value will now only get set after the new value has been successfully saved to file.
		flag.setValue(flagValue);		
	}	
}

