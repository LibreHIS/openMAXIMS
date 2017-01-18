package ims.rules.types;

import java.util.ArrayList;
import java.util.List;

import ims.rules.exceptions.RulesEngineRuntimeException;
import ims.rules.interfaces.IRuleAction;

public class RuleActionEntityMethod implements IRuleAction
{
	private static final long serialVersionUID = 1L;
	
	private RulesEngineEntityMethod method = null;
	private List<String> argumentValues = new ArrayList<String>();
	
	public RuleActionEntityMethod(RulesEngineEntityMethod method, List<String> argumentValues)
	{		
		this.method = method;
		this.argumentValues = argumentValues;
		
		if(method.getArguments() != null && argumentValues != null && method.getArguments().size() != argumentValues.size())
			throw new RulesEngineRuntimeException("Entity method argument don't match");
	}
	
	public RulesEngineEntityMethod getMethod()
	{
		return method;
	}	
	public List<String> getArgumentValues()
	{
		return argumentValues;
	}
}

