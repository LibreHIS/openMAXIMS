package ims.domain;

import ims.rules.interfaces.IRulesProvider;

public abstract class RulesFactory
{
	public abstract boolean hasRulesProvider();
	public abstract IRulesProvider getRulesProvider();	
	
}
