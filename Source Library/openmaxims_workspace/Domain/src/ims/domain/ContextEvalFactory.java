package ims.domain;

import ims.framework.interfaces.IContextEvalProvider;

public abstract class ContextEvalFactory
{
	public abstract boolean hasContextEvalProvider();
	public abstract IContextEvalProvider getContextEvalProvider();	
}
