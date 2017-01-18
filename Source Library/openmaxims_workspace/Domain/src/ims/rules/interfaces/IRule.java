package ims.rules.interfaces;

import ims.framework.utils.Date;
import ims.rules.types.RulesEngineEntity;

import java.io.Serializable;
import java.util.List;

public interface IRule extends Serializable
{	
	String getName();
	String getDescription();
	int getPriority();
	Date getValidFrom();
	Date getValidTo();
	RulesEngineEntity getRootEntity();
	List<IRuleCondition> getConditions();
	List<IRuleAction> getActions();
	String[] validate();
	List<RulesEngineEntity> getAllEntities();	
	boolean isUserRule();
	void markAsUserRule();
	void setPriority(int priority);
}
