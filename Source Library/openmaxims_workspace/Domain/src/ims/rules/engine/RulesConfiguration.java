package ims.rules.engine;

import ims.domain.RulesFactory;
import ims.framework.exceptions.CodingRuntimeException;
import ims.rules.engine.impl.DroolsRulesEngine;
import ims.rules.interfaces.IRule;
import ims.utils.Logging;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public final class RulesConfiguration
{
	private static final Logger	LOG	= Logging.getLogger(DroolsRulesEngine.class);
	private List<Reader> files = new ArrayList<Reader>();	
	private List<IRule> userRules = new ArrayList<IRule>();	
	private RulesFactory userRulesFactory;
		
	public RulesConfiguration(RulesFactory userRulesFactory)
	{	
		this.userRulesFactory = userRulesFactory;
		
		initialize();
	}

	private void initialize() 
	{
		InputStream init = Thread.currentThread().getContextClassLoader().getResourceAsStream("rules.xml");
		if(init == null)
		{
			LOG.warn("The internal rules were not loaded (The file rules.xml was not found in \\webapp\\web-inf\\classes).");
		}
		else
		{
			LOG.warn("Loading internal rules...");
			InputSource in = new InputSource(init);
			
			if (in != null)
			{
				org.dom4j.Document doc;
				try 
				{
					doc = new SAXReader().read(in);
					
					Element root = doc.getRootElement();
					int count = 0;
					int ignore = 0;
					for(Iterator i = root.elementIterator(); i.hasNext();)
					{
						Element element = (Element) i.next();
						String name = element.attributeValue("name");
						String path = element.attributeValue("path");
						Boolean enabled = Boolean.parseBoolean(element.attributeValue("enabled"));
						
						if(enabled)
						{
							count++;
							LOG.warn("Loading internal rule '" + name + "' from " + path);
							addRuleFromFile(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path)));
						}
						else
						{
							ignore++;
						}
					}
					
					LOG.warn(count + " internal rule(s) loaded, " + ignore + " internal rule(s) ignored.");
				}
				catch (DocumentException e) 
				{
					e.printStackTrace();
				}
			}			
		}
				
		if(userRulesFactory != null && userRulesFactory.hasRulesProvider())
		{
			LOG.warn("Loading user rules...");
			int count = 0;
			
			IRule[] userRules = userRulesFactory.getRulesProvider().getUserRules();
			if(userRules != null)
			{
				for(int x = 0; x < userRules.length; x++)
				{
					IRule userRule = userRules[x];
					if(userRule != null)
					{
						LOG.warn("Loading user rule '" + userRule.getName() + "'...");
						userRule.markAsUserRule();
						addUserRule(userRule);
						count++;
					}
				}
			}
			
			LOG.warn(count + " user rule(s) loaded.");
		}	
		else
		{
			LOG.warn("User defined rules not loaded (No user rules provider available).");			
		}
	}
	
	public void addRuleFromFile(Reader reader)
	{
		files.add(reader);
	}
	public void addUserRule(IRule rule)
	{
		if(rule == null)
			throw new CodingRuntimeException("Invalid user rule");
		
		userRules.add(rule);
	}
	public List<Reader> getInternalRules()
	{
		return files;
	}
	public List<IRule> getUserRules()
	{
		return userRules;
	}
	public void clearRules()
	{
		files.clear();
		userRules.clear();
	}
	public void reloadAll()
	{
		clearRules();
		initialize();
	}
}
