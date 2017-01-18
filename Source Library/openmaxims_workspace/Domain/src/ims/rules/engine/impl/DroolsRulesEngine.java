package ims.rules.engine.impl;

import ims.framework.enumerations.SystemLogLevel;
import ims.framework.interfaces.ISystemLogWriter;
import ims.rules.engine.RulesConfiguration;
import ims.rules.engine.RulesEngine;
import ims.rules.exceptions.RulesEngineCompilationException;
import ims.rules.exceptions.RulesEngineException;
import ims.rules.exceptions.RulesEngineRuntimeException;
import ims.rules.interfaces.IRule;
import ims.rules.types.RuleCompilationError;
import ims.utils.Logging;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;
import org.drools.runtime.rule.ConsequenceException;

public class DroolsRulesEngine extends RulesEngine
{	
	private static final Logger	LOG	= Logging.getLogger(DroolsRulesEngine.class);
	private PackageBuilder builder;
	private DroolsRuleGenerator rulesGenerator = new DroolsRuleGenerator();
	private List<RuleCompilationError> compilationErrors = new ArrayList<RuleCompilationError>();
		
	public DroolsRulesEngine(RulesConfiguration configuration, ISystemLogWriter systemLog) 
	{
		super(configuration, systemLog);				
	}

	@Override
	public synchronized void initialize() throws RulesEngineException, RulesEngineCompilationException 
	{
		builder = new PackageBuilder(); // packageConfiguration);
		
		if(configuration.getInternalRules().size() > 0 || configuration.getUserRules().size() > 0)
		{		
			LOG.warn("Starting Drools rules engine...");

			/* TODO */
			LOG.warn("Drools rules engine is starting");
			// createSystemLogEntry(SystemLogLevel.INFORMATION, "Drools rules engine is starting");
			
			List<Reader> internalRulesList = configuration.getInternalRules();
			int internalRulesCount = internalRulesList.size();			
			if(internalRulesCount > 0)
			{
				LOG.warn("Adding " + internalRulesCount + " internal rules...");
				
				for(int x = 0; x < internalRulesCount; x++)
				{
					try
					{
						builder.addPackageFromDrl(internalRulesList.get(x));					
					}
					catch (DroolsParserException e)
					{
						throw new RulesEngineException(e.getCause());
					}
					catch (IOException e)
					{
						throw new RulesEngineException(e.getCause());
					}
					catch (Exception e)
					{
						throw new RulesEngineException(e);
					}
				}
			}
						
			List<IRule> userRulesList = configuration.getUserRules();
			int userRulesCount = userRulesList.size();
			if(userRulesCount > 0)
			{
				LOG.warn("Adding " + userRulesCount + " user rules...");
				
				for(int x = 0; x < userRulesCount; x++)
				{
					try
					{
						LOG.warn("Adding '" + userRulesList.get(x).getName() + "' user rule (" + (x + 1) + " of " + userRulesCount + ")...");						
						builder.addPackageFromDrl(new StringReader(rulesGenerator.generate(userRulesList.get(x))));						
					}
					catch (DroolsParserException e)
					{
						throw new RulesEngineException(e.getCause());
					}
					catch (IOException e)
					{
						throw new RulesEngineException(e.getCause());
					}
					catch (Exception e)
					{
						throw new RulesEngineException(e);
					}
				}							
			}			
			
			LOG.warn("Compiling all rules...");

			/* TODO  */
			/* builder.compileAll();

			PackageBuilderErrors errors = builder.getErrors();
			if(errors.size() > 0)
			{
				for(int x = 0; x < errors.size(); x++)
				{
					compilationErrors.add(new RuleCompilationError(errors.get(x).getMessage()));
					LOG.error(errors.get(x).getMessage());
				}
				
				String errorMessage = "Drools rules engine is NOT running, " + errors.size() + " compilation error(s) found while loading " + internalRulesCount + " internal rule(s) and " + userRulesCount + " user rule(s)";
				LOG.error(errorMessage);
				createSystemLogEntry(SystemLogLevel.FATALERROR, errorMessage);
				throw new RulesEngineCompilationException(errors.size() + " error(s) found.");
			}
			else
			{	
				String message = "Drools rules engine was started with " + internalRulesCount + " internal rule(s) and " + userRulesCount + " user rule(s)";
				createSystemLogEntry(SystemLogLevel.INFORMATION, message);
				LOG.warn(message);
			}*/
		}
	}
	
	@Override
	public void reloadAll() throws RulesEngineException, RulesEngineCompilationException
	{		
		LOG.warn("Reloading Drools rules...");
		super.configuration.reloadAll();
		initialize();
	}
	
	@Override	
	public List<RuleCompilationError> getCompilationErrors()
	{
		return compilationErrors;
	}
	
	@Override
	public int fireAllRules(Object[] facts)
	{
		return fireAllRules(null, facts);
	}
	@Override
	public int fireAllRules(HashMap<String, Serializable> global)
	{
		return fireAllRules(global, null);
	}
	@Override
	public int fireAllRules(HashMap<String, Serializable> global, Object[] facts)
	{	
		if(builder == null || builder.getPackage() == null)
			return -1;
		
		int globalAndFactsCount = 0;
		
		if(global != null)
		{
			globalAndFactsCount += global.keySet().size();
		}
		if(facts != null)
		{
			globalAndFactsCount += facts.length;
		}
		
		if(globalAndFactsCount == 0)
			return -1;
		
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(builder.getPackage());
		WorkingMemory workingMemory = ruleBase.newStatefulSession();		
		
		if(global != null)
		{
			Iterator<String> i = global.keySet().iterator();
			while(i.hasNext())
			{
				String key = i.next();			
				workingMemory.setGlobal(key, global.get(key));
			}
		}
		
		if(facts != null)
		{
			for(int x = 0; x < facts.length; x++)
			{
				workingMemory.insert(facts[x]);
			}
		}
		try {
			return workingMemory.fireAllRules();
		} catch (ConsequenceException ce)
		{
			throw new RulesEngineRuntimeException(ce);
		}
	}		
}
