package ims.rules.engine;

import org.apache.log4j.Logger;

import ims.configuration.InitConfig;
import ims.domain.DomainSession;
import ims.domain.SystemLog;
import ims.rules.engine.impl.DroolsRulesEngine;
import ims.rules.exceptions.RulesEngineCompilationException;

public class RulesEngineFactory
{
	private static final Logger			LOG		= Logger.getLogger(RulesEngineFactory.class);
	private static RulesEngine engine = RulesEngineFactory.createInstance();	
	
	public static RulesEngine getInstance()
	{
		return engine;
	}
	private static RulesEngine createInstance()
	{	
		if(engine != null)
			return engine;
		
		if(InitConfig.getRulesEngine() == ims.configuration.RulesEngine.DROOLS)
		{ 
			try 
			{				
				DomainSession session = DomainSession.getSession();
				engine = new DroolsRulesEngine(getDefaultConfiguration(session), new SystemLog(session));
				engine.initialize();
			} 
			catch (RulesEngineCompilationException e) 
			{
				engine = null;
				LOG.warn("RulesEngineCompilationException loading rules engine ", e);
			}
			catch (Exception e) 
			{
				engine = null;
				LOG.warn("Exception loading rules engine", e);
			}
		}
		
		return engine;
	}
	private static RulesConfiguration getDefaultConfiguration(DomainSession session) throws Exception
	{
		return new RulesConfiguration(new RulesProviderFactory(session));
	}
}
