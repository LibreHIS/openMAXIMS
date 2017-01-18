package ims.rules.engine.impl;

import ims.configuration.ConfigFlag;
import ims.framework.utils.DateFormat;
import ims.framework.utils.StringUtils;
import ims.rules.interfaces.IRule;
import ims.rules.interfaces.IRuleAction;
import ims.rules.interfaces.IRuleCondition;
import ims.rules.types.RuleActionDataValidationError;
import ims.rules.types.RuleActionEntityMethod;
import ims.rules.types.RuleActionNotificationElement;
import ims.rules.types.RuleActionNotificationElementType;
import ims.rules.types.RuleActionTextOutput;
import ims.rules.types.RuleActionUserNotification;
import ims.rules.types.RuleLogicalCondition;
import ims.rules.types.RuleValueComparator;
import ims.rules.types.RuleValueCondition;
import ims.rules.types.RuleValueConditionEntry;
import ims.rules.types.RulesEngineEntity;
import ims.rules.types.RulesEngineEntityMethodArgument;
import ims.rules.types.RulesEngineFieldType;
import ims.rules.types.RulesEngineMethodArgumentType;
import ims.rules.types.RuleLogicalCondition.RuleLogicalConditionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DroolsRuleGenerator 
{
	private StringBuilder checkForNullMethods = null;
	private StringBuilder containsMethods = null;
	private HashMap<String, String> checkForNullMethodNames = null; 
	private List<String> generatedMethodNames;
	private List<IRuleCondition> skippedRules;
	
	private IRule rule = null;
	
	public String generate(IRule rule)
	{
		this.rule = rule;
		
		StringBuilder sb = new StringBuilder();
		StringBuilder header = new StringBuilder();
		checkForNullMethods = new StringBuilder();
		containsMethods = new StringBuilder();
		checkForNullMethodNames = new HashMap<String, String>();
		generatedMethodNames = new ArrayList<String>();
		skippedRules = new ArrayList<IRuleCondition>();
		
		String ruleId = StringUtils.generateUniqueString();
		
		header.append("package ims.rules.userrules;");
		header.append("\r\n");		
		renderImports(header, rule);			
		header.append("\r\n");
		renderGlobals(header);
		header.append("\r\n");
		
		renderConditionsMethod(sb, rule, ruleId);
		
		sb.append("\r\n\r\n");
		sb.append("rule \"" + rule.getName() + "\"");
		sb.append("\r\n");
		sb.append("no-loop true");
		sb.append("\r\n");
		sb.append("salience " + rule.getPriority());
		
		if(rule.getValidFrom() != null)
		{
			sb.append("\r\n");
			sb.append("date-effective \"" + rule.getValidFrom().toString(DateFormat.RULES) + "\"");
		}
		if(rule.getValidTo() != null)
		{
			sb.append("\r\n");
			sb.append("date-expires \"" + rule.getValidTo().toString(DateFormat.RULES) + "\"");
		}
		
		sb.append("\r\n");		
		sb.append("when");		
		renderEntitiesDeclaration(sb, rule);
		sb.append("\r\n\teval(isConditionMeet" + ruleId + "(engine, entity));");
		sb.append("\r\n");
		sb.append("then");		
		if(!ConfigFlag.GEN.RELEASE_MODE.getValue())
		{
			renderTextOutputAction(sb, new RuleActionTextOutput("Rule '" + rule.getName() + "' was fired."));
		}
		renderCreateSystemLogAction(sb, rule);
		renderActions(sb, rule.getActions(), rule);		
		sb.append("\r\n");
		sb.append("end");
		
		header.append(checkForNullMethods);
		header.append(containsMethods);		
		header.append(sb);
		return header.toString();
	}

	private void renderImports(StringBuilder sb, IRule rule)
	{
		renderImports(sb, rule, true);
	}
	private void renderImports(StringBuilder sb, IRule rule, boolean onlyRootEntity) 
	{
		List<RulesEngineEntity> entities = null;
		
		if(onlyRootEntity)
		{
			entities = new ArrayList<RulesEngineEntity>();
			entities.add(rule.getRootEntity());
		}
		else
		{
			entities = rule.getAllEntities();
		}
		
		for(int x = 0; x < entities.size(); x++)
		{
			RulesEngineEntity entity = entities.get(x);
			sb.append("\r\nimport ");
			sb.append(entity.getBusinessObjectName());
			sb.append(";");
		}		
		
		sb.append("\r\nimport ims.rules.engine.RulesRuntimeEngine;");
		sb.append("\r\nimport ims.domain.DomainFactory;");
	}
	private void renderGlobals(StringBuilder sb) 
	{
		sb.append("\r\nglobal RulesRuntimeEngine engine;");
		sb.append("\r\nglobal DomainFactory factory;");
	}
	private void renderEntitiesDeclaration(StringBuilder sb, IRule rule) 
	{
		sb.append("\r\n\tentity : " + rule.getRootEntity().getBusinessObjectName() + "();");		
	}
	
	private void renderConditionsMethod(StringBuilder sb, IRule rule, String ruleId) 
	{
		sb.append("\r\nfunction boolean isConditionMeet" + ruleId + "(RulesRuntimeEngine engine, " + rule.getRootEntity().getBusinessObjectName() + " entity)");
		sb.append("\r\n{");
		sb.append("\r\n\tif");
		for(int x = 0; x < rule.getConditions().size(); x++)
		{
			renderCondition(sb, rule.getConditions().get(x), rule.getConditions(), false);			
		}
		sb.append("");
		sb.append("\r\n\t\treturn true;");
		sb.append("\r\n\treturn false;");
		sb.append("\r\n}");
	}
	private void renderCondition(StringBuilder sb, IRuleCondition condition, List<IRuleCondition> allConditions, boolean allConditionsMustBeMeet) 
	{		
		if(condition instanceof RuleLogicalCondition)
		{			
			renderLogicalCondition(sb, (RuleLogicalCondition)condition, ((RuleLogicalCondition)condition).getConditions(), ((RuleLogicalCondition)condition).isOfType(RuleLogicalConditionType.ALLTRUE));
		}
		else if(condition instanceof RuleValueCondition)
		{			
			RuleValueCondition valueCondition = (RuleValueCondition)condition;
			
			sb.append("(");
			if(!valueCondition.getEntry().containsCollectionComparison())
			{				
				renderValueCondition(sb, valueCondition);				
			}
			else
			{
				if(!skippedRules.contains(valueCondition))
				{
					renderValueConditionForCollection(sb, valueCondition, allConditions, allConditionsMustBeMeet);
				}
				else
				{
					sb.append("true");
				}
			}
			sb.append(")");
		}		
	}

	private void renderLogicalCondition(StringBuilder sb, RuleLogicalCondition condition, List<IRuleCondition> allConditions, boolean allConditionsMustBeMeet) 
	{
		sb.append("(");
		for(int x = 0; x < condition.getConditions().size(); x++)
		{			
			if(x > 0)
			{
				if(condition.equals(RuleLogicalCondition.getInstance(RuleLogicalConditionType.ALLTRUE)))
				{
					sb.append(" && ");
				}
				else if(condition.equals(RuleLogicalCondition.getInstance(RuleLogicalConditionType.ONETRUE)))
				{
					sb.append(" || ");
				}				
			}
						
			renderCondition(sb, condition.getConditions().get(x), allConditions, allConditionsMustBeMeet);			
		}
		sb.append(")");		
	}
	private void renderValueConditionForCollection(StringBuilder sb, RuleValueCondition condition, List<IRuleCondition> allConditions, boolean allConditionsMustBeMeet)
	{
		StringBuilder valueConditionString = new StringBuilder();
		
		if(condition.getComparator() != RuleValueComparator.EMPTY && condition.getComparator() != RuleValueComparator.NOTEMPTY)
		{
			String checkForNullMethodName = getCheckForNullMethodName(condition);
			valueConditionString.append(checkForNullMethodName + "(entity) && ");
			generateCheckForNullMethodForCollection(checkForNullMethodName, condition);
			
			renderCollectionCheckingValueCondition(valueConditionString, condition, allConditions, allConditionsMustBeMeet);
		}
		else 
		{
			boolean negate = condition.getComparator() == RuleValueComparator.EMPTY;
			String checkForNullMethodName = getCheckForNullMethodName(condition);
			if(negate)
			{
				valueConditionString.append("!");
			}
			valueConditionString.append(checkForNullMethodName + "(entity)");
			generateCheckForNullMethodForCollection(checkForNullMethodName, condition);			
		}
		
		sb.append(valueConditionString.toString());
	}
	private void renderCollectionCheckingValueCondition(StringBuilder sb, RuleValueCondition condition, List<IRuleCondition> allConditions, boolean allConditionsMustBeMeet) 
	{
		String containsMethodName = "contains" + StringUtils.generateUniqueString();		
		sb.append(containsMethodName + "(entity)");
		
		containsMethods.append("\r\nfunction boolean " + containsMethodName + "(" + rule.getRootEntity().getBusinessObjectName() + " entity)");
		containsMethods.append("\r\n{");
						
		containsMethods.append("\r\n\tfor(int x = 0; x < " + "entity" + condition.getEntry().getFullFieldGetterForCollection() + ".size(); x++)");
		containsMethods.append("\r\n\t{");
		
		
		RulesEngineFieldType type = condition.getEntry().getComparedField().getType();
		String collectionFieldType = condition.getEntry().getCollectionField().getTypeEntity().getBusinessObjectName();
		containsMethods.append("\r\n\t\tboolean found = false;");
		containsMethods.append("\r\n\t\t" + collectionFieldType + " item = (" + collectionFieldType + ")entity" + condition.getEntry().getFullFieldGetterForCollection() + ".get(x);");
		
		String lookupGetter = "";
		if(type == RulesEngineFieldType.LOOKUP)
		{
			lookupGetter = ".getId()";
		}		
		
		containsMethods.append("\r\n\t\tif(" + "item." + condition.getEntry().getComparedField().getFieldGetter() + "!= null && " + " item." + condition.getEntry().getComparedField().getFieldGetter() + lookupGetter + getRuleComparator(condition.getComparator(), condition.getComparedValue(), type) + ")");		
		containsMethods.append("\r\n\t\t{");
		containsMethods.append("\r\n\t\t\tfound = true;");
		containsMethods.append("\r\n\t\t}");		
		
		if(allConditionsMustBeMeet)
		{
			for(int x = 0; x < allConditions.size(); x++)
			{
				if(allConditions.get(x) instanceof RuleValueCondition)
				{
					RuleValueCondition otherCondition = (RuleValueCondition)allConditions.get(x);
					
					if(!otherCondition.equals(condition) && condition.getEntry().getCollectionField().equals(otherCondition.getEntry().getCollectionField()))
					{
						lookupGetter = "";
						type = otherCondition.getEntry().getComparedField().getType();
						collectionFieldType = otherCondition.getEntry().getCollectionField().getTypeEntity().getBusinessObjectName();
						if(type == RulesEngineFieldType.LOOKUP)
						{
							lookupGetter = ".getId()";
						}
						
						containsMethods.append("\r\n\t\tif(found && " + "item." + otherCondition.getEntry().getComparedField().getFieldGetter() + lookupGetter + getRuleComparator(otherCondition.getComparator(), otherCondition.getComparedValue(), type) + ")");		
						containsMethods.append("\r\n\t\t{");
						containsMethods.append("\r\n\t\t\tfound = true;");	
						containsMethods.append("\r\n\t\t}");
						containsMethods.append("\r\n\t\telse");
						containsMethods.append("\r\n\t\t{");
						containsMethods.append("\r\n\t\t\tfound = false;");
						containsMethods.append("\r\n\t\t}");
						
						skippedRules.add(otherCondition);
					}
				}
			}
		}
		containsMethods.append("\r\n\r\n\t\tif(found)");
		containsMethods.append("\r\n\t\t\treturn true;");
		containsMethods.append("\r\n\t}");		
		containsMethods.append("\r\n\treturn false;");
		containsMethods.append("\r\n}");
	}

	private void renderValueCondition(StringBuilder sb, RuleValueCondition condition) 
	{	
		StringBuilder valueConditionString = new StringBuilder();
		
		if(condition.getComparator() != RuleValueComparator.EMPTY && condition.getComparator() != RuleValueComparator.NOTEMPTY)
		{
			String checkForNullMethodName = getCheckForNullMethodName(condition);
			valueConditionString.append(checkForNullMethodName + "(entity) && ");
			generateCheckForNullMethod(checkForNullMethodName, condition);			
		}
		
		boolean negate = false;
		if(condition.getComparator() == RuleValueComparator.NOTEQUALS && condition.getEntry().getComparedFieldType().isComplexType()) 
		{
			negate = true;
		}
		
		if(condition.getEntry().getChild() != null && condition.getEntry().getChild().getField() != null && condition.getEntry().getChild().getField().getName() != null && condition.getEntry().getChild().getField().getName().toLowerCase().equals("code") && 
				(condition.getComparator() == RuleValueComparator.EMPTY || condition.getComparator() == RuleValueComparator.NOTEMPTY)) 
		{
			String comparatorString = "";
			if(condition.getComparator() == RuleValueComparator.EMPTY)
				comparatorString = "==";
			else if(condition.getComparator() == RuleValueComparator.NOTEMPTY)
				comparatorString = "!=";
			
			sb.append("engine.getRuntimeEntityId() " + comparatorString + " null");
		}
		else
		{
			RulesEngineFieldType fieldType = renderValueCondition(valueConditionString, condition.getEntry(), condition.getComparator(), true, negate);
		
			sb.append(valueConditionString.toString());
			sb.append(getRuleComparator(condition.getComparator(), condition.getComparedValue(), fieldType));
		}
	}

	private void generateCheckForNullMethodForCollection(String checkForNullMethodName, RuleValueCondition condition)
	{
		if(methodIsGenerated(checkForNullMethodName))
			return;
		markMethodAsGenerated(checkForNullMethodName);
		
		checkForNullMethods.append("\r\nfunction boolean " + checkForNullMethodName + "(" + rule.getRootEntity().getBusinessObjectName() + " entity)");
		checkForNullMethods.append("\r\n{");
		
		RuleValueConditionEntry entry = condition.getEntry(); 
		if(entry != null)
		{
			checkForNullMethods.append("\r\n\tif(entity == null)");
			checkForNullMethods.append("\r\n\t\treturn false;");
		
			String validation = "entity";
			while(entry != null)
			{
				entry = entry.getChild();			
				
				if((condition.getComparator() == RuleValueComparator.EMPTY || condition.getComparator() == RuleValueComparator.NOTEMPTY) && entry.getChild() == null)
					break;
				
				if(entry != null && entry.getField() != null)
				{
					validation += "." + entry.getField().getFieldGetter();
					checkForNullMethods.append("\r\n\tif(" + validation + " == null)");
					checkForNullMethods.append("\r\n\t\treturn false;");
					
					if(entry.getField().isCollection())
						break;
				}
			}
		}
		
		checkForNullMethods.append("\r\n\treturn true;");
		checkForNullMethods.append("\r\n}");
	}
	private void generateCheckForNullMethod(String checkForNullMethodName, RuleValueConditionEntry entry) 
	{
		if(methodIsGenerated(checkForNullMethodName))
			return;
		markMethodAsGenerated(checkForNullMethodName);
		
		checkForNullMethods.append("\r\nfunction boolean " + checkForNullMethodName + "(" + rule.getRootEntity().getBusinessObjectName() + " entity)");
		checkForNullMethods.append("\r\n{");
				
		if(entry != null)
		{
			checkForNullMethods.append("\r\n\tif(entity == null)");
			checkForNullMethods.append("\r\n\t\treturn false;");
		
			String validation = "entity";
			while(entry != null)
			{
				entry = entry.getChild();			
				
				if(entry != null && entry.getField() != null)
				{
					validation += "." + entry.getField().getFieldGetter();
					checkForNullMethods.append("\r\n\tif(" + validation + " == null)");
					checkForNullMethods.append("\r\n\t\treturn false;");
				}
			}
		}
		
		checkForNullMethods.append("\r\n\treturn true;");
		checkForNullMethods.append("\r\n}");
	}
	private void generateCheckForNullMethod(String checkForNullMethodName, RuleValueCondition condition) 
	{
		if(methodIsGenerated(checkForNullMethodName))
			return;
		markMethodAsGenerated(checkForNullMethodName);
		
		checkForNullMethods.append("\r\nfunction boolean " + checkForNullMethodName + "(" + rule.getRootEntity().getBusinessObjectName() + " entity)");
		checkForNullMethods.append("\r\n{");
		
		RuleValueConditionEntry entry = condition.getEntry(); 
		if(entry != null)
		{
			checkForNullMethods.append("\r\n\tif(entity == null)");
			checkForNullMethods.append("\r\n\t\treturn false;");
		
			String validation = "entity";
			while(entry != null)
			{
				entry = entry.getChild();			
				
				if((condition.getComparator() == RuleValueComparator.EMPTY || condition.getComparator() == RuleValueComparator.NOTEMPTY) && entry.getChild() == null)
					break;
				
				if(entry != null && entry.getField() != null)
				{
					validation += "." + entry.getField().getFieldGetter();
					checkForNullMethods.append("\r\n\tif(" + validation + " == null)");
					checkForNullMethods.append("\r\n\t\treturn false;");
				}
			}
		}
		
		checkForNullMethods.append("\r\n\treturn true;");
		checkForNullMethods.append("\r\n}");
	}

	private RulesEngineFieldType renderValueCondition(StringBuilder sb, RuleValueConditionEntry entry, RuleValueComparator comparator, boolean isRoot, boolean negate) 
	{		
		RulesEngineFieldType result = null;
		boolean closeEntity = false;
		if(isRoot)
		{
			if(negate)
			{
				sb.append("!");
			}
						
			if(comparator != RuleValueComparator.EMPTY && comparator != RuleValueComparator.NOTEMPTY)
			{
				if(entry.getComparedFieldType() == RulesEngineFieldType.DATE)
				{
					closeEntity = true;				
					sb.append("new ims.framework.utils.Date(");
				}
				else if(entry.getComparedFieldType() == RulesEngineFieldType.DATETIME)
				{
					closeEntity = true;				
					sb.append("new ims.framework.utils.DateTime(");
				}
				else if(entry.getComparedFieldType() == RulesEngineFieldType.TIME)
				{
					closeEntity = true;				
					sb.append("new ims.framework.utils.Time(");
				}
				else if(entry.getComparedFieldType() == RulesEngineFieldType.PARTIALDATE)
				{
					closeEntity = true;				
					sb.append("new ims.framework.utils.PartialDate(");
				}
			}
			sb.append("entity");
			isRoot = false;			
		}		
		
		if(entry.getField() != null)
		{	
			String fieldGetter = "";
			
			//Check if first char is number. DevEnv add _ in front of field name is first char is number.
			if (Character.isDigit(entry.getField().getFieldGetter().replace("get", "").charAt(0)))
			{
				//Replace get with get_
				fieldGetter = entry.getField().getFieldGetter().replace("get", "get_");
			}
			else
			{
				fieldGetter = entry.getField().getFieldGetter();
			}
			
			sb.append(fieldGetter);
		}
		
		if(entry.getChild() != null)
		{			
			sb.append(".");
			result =  renderValueCondition(sb, entry.getChild(), comparator, isRoot, false);
			
			if(closeEntity)
				sb.append(")");
			
			return result;
		}
		else
		{
			if(entry.getField().getType() == RulesEngineFieldType.LOOKUP && comparator != RuleValueComparator.EMPTY && comparator != RuleValueComparator.NOTEMPTY)
			{
				sb.append(".getId()");
			}
			
			result = entry.getField().getType();
			
			if(closeEntity)
				sb.append(")");
			
			return result;
		}	
	}

	private void renderActions(StringBuilder sb, List<IRuleAction> actions, IRule rule) 
	{
		for(int x = 0; x < actions.size(); x++)
		{
			renderAction(sb, actions.get(x), rule);
		}		
	}
	private void renderAction(StringBuilder sb, IRuleAction action, IRule rule) 
	{
		if(action instanceof RuleActionDataValidationError)
		{
			renderDataValidationErrorAction(sb, (RuleActionDataValidationError)action);						
		}		
		else if(action instanceof RuleActionTextOutput)
		{
			renderTextOutputAction(sb, (RuleActionTextOutput)action);						
		}
		else if(action instanceof RuleActionEntityMethod)
		{
			renderEntityMethodAction(sb, (RuleActionEntityMethod)action);
		}
		else if(action instanceof RuleActionUserNotification)
		{
			renderUserNotificationAction(sb, (RuleActionUserNotification)action, rule);
		}
	}
		private void renderDataValidationErrorAction(StringBuilder sb, RuleActionDataValidationError action) 
	{
		sb.append("\r\n");
		sb.append("\tengine.throwDataValidationException(\"" + action.getMessage() + "\");");
	}
	private void renderTextOutputAction(StringBuilder sb, RuleActionTextOutput action) 
	{
		sb.append("\r\n");
		sb.append("\tengine.println(\"" + action.getText() + "\");");
	}
	private void renderCreateSystemLogAction(StringBuilder sb, IRule rule)
	{
		sb.append("\r\n");
		sb.append("\tengine.createSystemLogEntry(ims.framework.enumerations.SystemLogLevel.INFORMATION, \"Rule '" + rule.getName() + "' was fired.\");");
	}
	private void renderEntityMethodAction(StringBuilder sb, RuleActionEntityMethod action) 
	{
		sb.append("\r\n");
		sb.append("\tentity.");
		sb.append(action.getMethod().getJavaName());
		sb.append("(factory, engine");
		
		for(int x = 0; x < action.getMethod().getArguments().size(); x++)
		{
			sb.append(", ");
			
			RulesEngineEntityMethodArgument argument = action.getMethod().getArguments().get(x);
			if(argument.getType() == RulesEngineMethodArgumentType.STRING)
			{
				sb.append("\"");
				sb.append(action.getArgumentValues().get(x));
				sb.append("\"");
			}
			else 
			{
				sb.append(action.getArgumentValues().get(x));
			}			
		}
		
		sb.append(");");
	}	
	private void renderUserNotificationAction(StringBuilder sb, RuleActionUserNotification action, IRule rule) 
	{
		int[] userIds = action.getUserIds();
		
		if(userIds.length == 0 && rule.getRootEntity().supportsNotifications())
		{
			userIds = new int[] { 0 };
		}
		
		for(int x = 0; x < userIds.length; x++)
		{			
			sb.append("\r\n");
			sb.append("\tengine.createNotification(");
			int userId = userIds[x];
			
			if(userId > 0)
			{
				sb.append(userId);
			}
			else				
			{
				sb.append("entity");
			}
			sb.append(", ");
			sb.append("ims.framework.enumerations.NotificationPriority.parse(" + action.getPriority().getId() + ")");
			sb.append(", ");
			
			renderUserNotificationActionText(sb, action.getText());
			
			sb.append(", ");
			sb.append("\"");
			sb.append("Rules Engine");
			sb.append("\"");			
			sb.append(", entity.getRealDomainClass().getName()");
			sb.append(", entity.getId()");
			sb.append(");");
		}		
	}
	private void renderUserNotificationActionText(StringBuilder sb, List<RuleActionNotificationElement> text)
	{
		if(text.size() == 0)
		{
			sb.append("\"No details.\"");
			return;
		}
		
		for(int x = 0; x < text.size(); x++)
		{
			boolean insertSpace = true;
			if(text.get(x).getType().equals(RuleActionNotificationElementType.TEXT))
			{
				sb.append("\"" + text.get(x).getText() + "\"");
			}
			else if(text.get(x).getType().equals(RuleActionNotificationElementType.LINE_SEPARATOR))
			{
				insertSpace = false;
				sb.append("\"\\r\\n\"");
			}
			else if(text.get(x).getType().equals(RuleActionNotificationElementType.ENTITY_FIELD))
			{
				String checkForNullMethodName = getCheckForNullMethodName(text.get(x).getEntityField());
				generateCheckForNullMethod(checkForNullMethodName, text.get(x).getEntityField());
				sb.append("(" + checkForNullMethodName + "(entity) ? entity" + text.get(x).getEntityField().getFullFieldGetter() + ".toString() : \"<???>\")");
			}
			
			if(x < text.size() - 1)
			{
				if(insertSpace)
				{
					sb.append(" + \" \" + ");
				}
				else
				{
					sb.append(" + ");
				}
			}
		}		
	}
	private String getRuleComparator(RuleValueComparator comparator, String comparedValue, RulesEngineFieldType fieldType) 
	{
		if(comparator == RuleValueComparator.EMPTY)
		{
			return "== null";
		}
		else if(comparator == RuleValueComparator.NOTEMPTY)
		{
			return " != null";
		}
		
		comparedValue = processComparedValue(comparedValue, fieldType);		
		
		if(comparator == RuleValueComparator.EQUALS || comparator == RuleValueComparator.NOTEQUALS)			
		{
			if(fieldType.isComplexType())
			{
				if(fieldType == RulesEngineFieldType.STRING)
				{
					return ".toLowerCase().equals(" + comparedValue.toLowerCase() + ")";
				}
				else
				{
					return ".equals(" + comparedValue + ")";
				}
			}			
			else
			{					
				if(comparator == RuleValueComparator.EQUALS)
				{
					return " == " + comparedValue;
				}
				else if(comparator == RuleValueComparator.NOTEQUALS)
				{
					return " != " + comparedValue;
				}
			}
		}
		else if(comparator == RuleValueComparator.GREATEREQUALSTHAN)
		{
			if(fieldType == RulesEngineFieldType.DATE || fieldType == RulesEngineFieldType.DATETIME || fieldType == RulesEngineFieldType.PARTIALDATE)
			{
				return ".isGreaterOrEqualThan(" + comparedValue + ")";
			}
			else
			{
				return " >= " + comparedValue;
			}
		}
		else if(comparator == RuleValueComparator.GREATERTHAN)
		{
			if(fieldType == RulesEngineFieldType.DATE || fieldType == RulesEngineFieldType.DATETIME || fieldType == RulesEngineFieldType.PARTIALDATE)
			{
				return ".isGreaterThan(" + comparedValue + ")";
			}
			else
			{
				return " > " + comparedValue;
			}			
		}
		else if(comparator == RuleValueComparator.LESSEQUALSTHAN)
		{
			if(fieldType == RulesEngineFieldType.DATE || fieldType == RulesEngineFieldType.DATETIME || fieldType == RulesEngineFieldType.PARTIALDATE)
			{
				return ".isLessOrEqualThan(" + comparedValue + ")";
			}
			else
			{
				return " <= " + comparedValue;
			}
		}
		else if(comparator == RuleValueComparator.LESSTHAN)
		{
			if(fieldType == RulesEngineFieldType.DATE || fieldType == RulesEngineFieldType.DATETIME || fieldType == RulesEngineFieldType.PARTIALDATE)
			{
				return ".isLessThan(" + comparedValue + ")";
			}
			else
			{
				return " < " + comparedValue;
			}
		}		
		else if(comparator == RuleValueComparator.CONTAINS)
		{
			if(fieldType == RulesEngineFieldType.STRING)
			{
				return ".toLowerCase().contains(" + comparedValue.toLowerCase() + ")";
			}
			else
			{
				return ".contains(" + comparedValue + ")";
			}
		}
		
		return "<ERROR: Rule comparator not supported: " + comparator.toString() + ">";
	}

	private String processComparedValue(String comparedValue, RulesEngineFieldType fieldType) 
	{
		if(fieldType == RulesEngineFieldType.BOOLEAN)
		{
			if(comparedValue.toLowerCase().equals("true"))
				return "Boolean.TRUE";
			else if(comparedValue.toLowerCase().equals("false"))
				return "Boolean.FALSE";
		}
		else if(fieldType == RulesEngineFieldType.DATE)
		{
			return "new ims.framework.utils.Date(\"" + comparedValue + "\", ims.framework.utils.DateFormat.ISO)";			
		}
		else if(fieldType == RulesEngineFieldType.TIME)
		{
			return "new ims.framework.utils.Time(\"" + comparedValue + "\", ims.framework.utils.TimeFormat.FLAT4)";			
		}
		else if(fieldType == RulesEngineFieldType.DATETIME)
		{
			return "new ims.framework.utils.DateTime(\"" + comparedValue + "\")";			
		}
		else if(fieldType == RulesEngineFieldType.PARTIALDATE)
		{
			return "new ims.framework.utils.PartialDate(\"" + comparedValue + "\")";			
		}
		else if(fieldType == RulesEngineFieldType.STRING)
		{
			return "\"" + comparedValue + "\"";			
		}
		
		return comparedValue;
	}	
	private String getCheckForNullMethodName(RuleValueConditionEntry entry)
	{		
		String key = entry.getComparedField().getId();		
		
		if(checkForNullMethodNames.containsKey(key))
			return checkForNullMethodNames.get(key);
		
		String value = "isNotNullField" + StringUtils.generateUniqueString();		
		checkForNullMethodNames.put(key, value);
		
		return value;		
	}
	private String getCheckForNullMethodName(RuleValueCondition condition)
	{		
		if(condition.getEntry() == null || condition.getEntry().getComparedField() == null)
		{
			return "isNotNull" + StringUtils.generateUniqueString();			
		}
		
		String key = "";
		if(condition.getEntry().containsCollectionComparison())
		{
			if(condition.getEntry().getCollectionField() == null)
			{
				return "isNotNull" + StringUtils.generateUniqueString();	
			}
			else
			{				
				key = condition.getEntry().getCollectionField().getId();
			}
		}
		else
		{
			key = condition.getEntry().getComparedField().getId();
		}		
		
		if(checkForNullMethodNames.containsKey(key))
			return checkForNullMethodNames.get(key);
		
		String value = "isNotNull" + StringUtils.generateUniqueString();		
		checkForNullMethodNames.put(key, value);
		
		return value;		
	}
	private boolean methodIsGenerated(String methodName)
	{
		return generatedMethodNames.contains(methodName);
	}
	private void markMethodAsGenerated(String methodName)
	{
		if(methodIsGenerated(methodName))
			return;
		generatedMethodNames.add(methodName);
	}
}
