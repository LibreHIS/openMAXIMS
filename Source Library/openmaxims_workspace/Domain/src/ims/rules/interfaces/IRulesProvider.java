package ims.rules.interfaces;

import java.io.Serializable;

public interface IRulesProvider extends Serializable
{	
	IRule[] getUserRules();
}
