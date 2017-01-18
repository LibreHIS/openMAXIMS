package ims.rules.engine;


import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.interfaces.ISystemLog;
import ims.framework.interfaces.ISystemLogWriter;
import ims.rules.exceptions.RulesEngineCompilationException;
import ims.rules.exceptions.RulesEngineException;
import ims.rules.types.RuleCompilationError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public abstract class RulesEngine
{	
	protected RulesConfiguration configuration;
	private ISystemLogWriter systemLog;	
	
	public RulesEngine(RulesConfiguration configuration, ISystemLogWriter systemLog)
	{
		this.configuration = configuration;
		this.systemLog = systemLog;		
	}
	
	public abstract List<RuleCompilationError> getCompilationErrors();
	
	public abstract void initialize() throws RulesEngineException, RulesEngineCompilationException;
	public abstract void reloadAll() throws RulesEngineException, RulesEngineCompilationException;
	public abstract int fireAllRules(Object[] facts);
	public abstract int fireAllRules(HashMap<String, Serializable> global);
	public abstract int fireAllRules(HashMap<String, Serializable> global, Object[] facts);
	
	protected ISystemLog createSystemLogEntry(SystemLogLevel level, String message)
	{
		if(systemLog == null)
			return null;
		
		return systemLog.createSystemLogEntry(SystemLogType.RULES_ENGINE, level, message);
	}
}