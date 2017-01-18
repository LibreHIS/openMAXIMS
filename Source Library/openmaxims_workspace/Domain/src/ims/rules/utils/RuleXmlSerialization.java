package ims.rules.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ims.framework.enumerations.NotificationPriority;
import ims.framework.utils.Date;
import ims.framework.utils.DateFormat;
import ims.framework.utils.StringUtils;
import ims.rules.exceptions.RulesEngineDeserializationException;
import ims.rules.exceptions.RulesEngineSerializationException;
import ims.rules.interfaces.IRule;
import ims.rules.interfaces.IRuleAction;
import ims.rules.interfaces.IRuleCondition;
import ims.rules.interfaces.IRulesEngineEntitiesHelper;
import ims.rules.types.Rule;
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
import ims.rules.types.RulesEngineEntityMethod;
import ims.rules.types.RulesEngineField;

public class RuleXmlSerialization
{
	public static String serialize(IRule rule) throws RulesEngineSerializationException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<rule name=\"");
		sb.append(StringUtils.encodeXML(rule.getName()));
		
		sb.append("\" description=\"");
		sb.append(StringUtils.encodeXML(rule.getDescription()));
		
		sb.append("\" priority=\"");
		sb.append(rule.getPriority());
		
		sb.append("\" rootEntity=\"");
		sb.append(rule.getRootEntity().getId());
		
		if(rule.getValidFrom() != null)
		{
			sb.append("\" validFrom=\"");
			sb.append(rule.getValidFrom().toString(DateFormat.ISO));
		}
		
		if(rule.getValidTo() != null)
		{
			sb.append("\" validTo=\"");
			sb.append(rule.getValidTo().toString(DateFormat.ISO));
		}
		
		sb.append("\" >");
		
		sb.append("<conditions>");
		for (IRuleCondition condition : rule.getConditions())
		{
			sb.append(serializeToXml(condition));		
		}
		sb.append("</conditions>");
		
		sb.append("<actions>");
		for (IRuleAction action : rule.getActions())
		{
			sb.append(serializeToXml(action));
		}
		sb.append("</actions>");
		
		sb.append("</rule>");
		
		return sb.toString();
	}
	private static String serializeToXml(IRuleCondition condition)
	{		
		StringBuilder sb = new StringBuilder();
		
		if(condition instanceof RuleLogicalCondition)
		{
			RuleLogicalCondition typedCondition = (RuleLogicalCondition)condition;
			sb.append("<logicalCondition id=\"" + typedCondition.getId() + "\">");
			
			sb.append("<conditions>");
			for (IRuleCondition childCondition : typedCondition.getConditions())
			{
				sb.append(serializeToXml(childCondition));
			}			
			sb.append("</conditions>");
			
			sb.append("</logicalCondition>");
		}
		else if(condition instanceof RuleValueCondition)
		{
			RuleValueCondition typedCondition = (RuleValueCondition)condition;
			
			sb.append("<valueCondition comparator=\"");
			sb.append(typedCondition.getComparator().getId());
			
			sb.append("\" comparedValue=\"");
			sb.append(StringUtils.encodeXML(typedCondition.getComparedValue()));
			
			sb.append("\" >");
			
			sb.append(serializeToXml(typedCondition.getEntry()));
			
			sb.append("</valueCondition>");
		}
		
		return sb.toString();
	}
	private static String serializeToXml(RuleValueConditionEntry entry)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<conditionEntry entity=\"");
		sb.append(entry.getEntity().getId());
		
		if(entry.getField() != null)
		{
			sb.append("\" field=\"");
			sb.append(StringUtils.encodeXML(entry.getField().getId()));			
		}
		
		sb.append("\">");
		
		if(entry.getChild() != null)
		{
			sb.append(serializeToXml(entry.getChild()));
		}
		
		sb.append("</conditionEntry>");
		
		return sb.toString();
	}
	private static String serializeToXml(IRuleAction action) throws RulesEngineSerializationException
	{
		if(action instanceof RuleActionDataValidationError)
		{
			return serializeDataValidationErrorActionToXml((RuleActionDataValidationError)action);
		}
		else if(action instanceof RuleActionTextOutput)
		{
			return serializeTextOutputActionToXml((RuleActionTextOutput)action);
		}
		else if(action instanceof RuleActionEntityMethod)
		{
			return serializeTextOutputActionToXml((RuleActionEntityMethod)action);
		}
		else if(action instanceof RuleActionUserNotification)
		{
			return serializeUserNotificationActionToXml((RuleActionUserNotification)action);
		}
		
		throw new RulesEngineSerializationException("Unknown action to serialize: " + action.toString());
	}	
	private static String serializeUserNotificationActionToXml(RuleActionUserNotification action) 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<userNotificationAction ");		
		sb.append(" priority=\"");
		sb.append(action.getPriority().getId());
		sb.append("\"");
		sb.append(" sendEmail=\"");
		sb.append(action.getSendEmail() ? "1" : "0");
		sb.append("\"");
		sb.append(" sendSMS=\"");
		sb.append(action.getSendSMS() ? "1" : "0");
		sb.append("\" >");
		
		for(int x = 0; x < action.getUserIds().length; x++)
		{
			sb.append("<userId value=\"");
			sb.append(action.getUserIds()[x]);
			sb.append("\" />");
		}
		for(int x = 0; x < action.getText().size(); x++)
		{
			sb.append("<message type=\"");
			sb.append(action.getText().get(x).getType().getId());
			
			if(action.getText().get(x).getType().equals(RuleActionNotificationElementType.LINE_SEPARATOR))
			{
				sb.append("\" />");
			}
			else if(action.getText().get(x).getType().equals(RuleActionNotificationElementType.TEXT))
			{
				sb.append("\" text=\"");
				sb.append(StringUtils.encodeXML(action.getText().get(x).getText()));
				sb.append("\" />");
			}
			else if(action.getText().get(x).getType().equals(RuleActionNotificationElementType.ENTITY_FIELD))
			{
				sb.append("\" >");				
				sb.append(serializeToXml(action.getText().get(x).getEntityField()));				
				sb.append("</message>");
			}
		}
				
		sb.append("</userNotificationAction>");
		
		return sb.toString();
	}	
	private static String serializeDataValidationErrorActionToXml(RuleActionDataValidationError action) 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<dataValidationErrorAction message=\"");
		sb.append(StringUtils.encodeXML(action.getMessage()));
		sb.append("\" />");
		
		return sb.toString();
	}
	private static String serializeTextOutputActionToXml(RuleActionTextOutput action) 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<textOutputAction text=\"");
		sb.append(StringUtils.encodeXML(action.getText()));
		sb.append("\" />");
		
		return sb.toString();
	}
	private static String serializeTextOutputActionToXml(RuleActionEntityMethod action) 
	{		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<boMethodAction id=\"");
		sb.append(action.getMethod().getId());
		sb.append("\" >");
		sb.append("<arguments>");
		
		for(int x = 0; x < action.getArgumentValues().size(); x++)
		{
			sb.append("<argument value=\"");
			sb.append(StringUtils.encodeXML(action.getArgumentValues().get(x)));
			sb.append("\" />");
		}
		
		sb.append("</arguments>");		
		sb.append("</boMethodAction>");
		
		return sb.toString();
	}
	
	public static IRule deserializeFromXml(String xml, IRulesEngineEntitiesHelper entitiesHelper) throws SAXException, IOException, DOMException, ParseException, RulesEngineDeserializationException
	{
		Rule result = new Rule();
		
		DOMParser parser = new DOMParser();
		parser.parse(new org.xml.sax.InputSource(new java.io.ByteArrayInputStream(xml.getBytes())));
		Document doc = parser.getDocument();
		
		result.setName(doc.getDocumentElement().getAttributes().getNamedItem("name").getNodeValue());
		result.setDescription(doc.getDocumentElement().getAttributes().getNamedItem("description").getNodeValue());
		result.setPriority(Integer.parseInt(doc.getDocumentElement().getAttributes().getNamedItem("priority").getNodeValue()));
		result.setRootEntity(entitiesHelper.getEntityById(doc.getDocumentElement().getAttributes().getNamedItem("rootEntity").getNodeValue()));
		
		if(result.getRootEntity() == null)
			throw new RulesEngineDeserializationException("Unable to find the root entity");
		
		if(doc.getDocumentElement().getAttributes().getNamedItem("validFrom") != null)
		{
			result.setValidFrom(new Date(doc.getDocumentElement().getAttributes().getNamedItem("validFrom").getNodeValue(), DateFormat.ISO));
		}
		if(doc.getDocumentElement().getAttributes().getNamedItem("validTo") != null)
		{
			result.setValidTo(new Date(doc.getDocumentElement().getAttributes().getNamedItem("validTo").getNodeValue(), DateFormat.ISO));
		}		
		
		NodeList nodes = doc.getChildNodes().item(0).getChildNodes();
		for (int i = 0; i < nodes.getLength(); ++i)
		{
			Node node = nodes.item(i);
			String name = node.getNodeName();
			
			if(name.equals("conditions"))
			{
				result.setConditions(deserializeConditionsFromXml(node, entitiesHelper));
			}
			else if(name.equals("actions"))
			{
				result.setActions(deserializeActionsFromXml(node, result.getRootEntity(), entitiesHelper));
			}
		}			
		
		return result;
	}	
	private static List<IRuleCondition> deserializeConditionsFromXml(Node conditionsNode, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException
	{
		List<IRuleCondition> result = new ArrayList<IRuleCondition>();
		
		NodeList nodes = conditionsNode.getChildNodes();
		for (int i = 0; i < nodes.getLength(); ++i)
		{
			Node node = nodes.item(i);
			String name = node.getNodeName();
			
			if(name.equals("logicalCondition"))
			{
				result.add(deserializeRuleLogicalCondition(node, entitiesHelper));
			}
			else if(name.equals("valueCondition"))
			{
				result.add(deserializeRuleValueCondition(node, entitiesHelper));
			}
		}
		
		return result;
	}
	private static IRuleCondition deserializeRuleLogicalCondition(Node node, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException
	{
		RuleLogicalCondition condition = RuleLogicalCondition.parse(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));
		List<IRuleCondition> conditions = new ArrayList<IRuleCondition>();
		
		NodeList nodes = node.getChildNodes().item(0).getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Node childNode = nodes.item(i);
			String name = childNode.getNodeName();
			
			if(name.equals("logicalCondition"))
			{
				conditions.add(deserializeRuleLogicalCondition(childNode, entitiesHelper)); 									
			}
			else if(name.equals("valueCondition"))
			{
				conditions.add(deserializeRuleValueCondition(childNode, entitiesHelper));							
			}
		}
		
		condition.setConditions(conditions);
		
		return condition;
	}
	private static IRuleCondition deserializeRuleValueCondition(Node node, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException
	{
		RuleValueCondition condition = new RuleValueCondition();
		
		condition.setComparator(RuleValueComparator.parse(Integer.parseInt(node.getAttributes().getNamedItem("comparator").getNodeValue())));
		condition.setComparedValue(node.getAttributes().getNamedItem("comparedValue").getNodeValue());
		condition.setEntry(deserializeRuleValueConditionEntry(node.getFirstChild(), entitiesHelper));
		
		return condition;
	}
	private static RuleValueConditionEntry deserializeRuleValueConditionEntry(Node node, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException
	{
		RuleValueConditionEntry entry = new RuleValueConditionEntry();
		
		String entityId = node.getAttributes().getNamedItem("entity").getNodeValue(); 
		RulesEngineEntity entity = entitiesHelper.getEntityById(entityId);
		if(entity == null)
			throw new RulesEngineDeserializationException("Unable to find rules engine entity with ID: " + entityId);
			
		entry.setEntity(entity);
		if(node.getAttributes().getNamedItem("field") != null)
		{
			String fieldId = node.getAttributes().getNamedItem("field").getNodeValue();
			RulesEngineField field = entity.getFieldById(fieldId);
			if(field == null)
				throw new RulesEngineDeserializationException("Unable to find rules engine field from the '" + entity.getName() + "' entity with ID: " + fieldId);
			entry.setField(field);
		}
		
		if(node.getChildNodes().getLength() > 0)
		{
			entry.setChild(deserializeRuleValueConditionEntry(node.getFirstChild(), entitiesHelper));
		}
		
		return entry;
	}
	private static List<IRuleAction> deserializeActionsFromXml(Node actionsNode, RulesEngineEntity rootEntity, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException
	{
		List<IRuleAction> result = new ArrayList<IRuleAction>();
		
		if(actionsNode == null)
			return result;
		
		for(int x = 0; x < actionsNode.getChildNodes().getLength(); x++)
		{
			Node childNode = actionsNode.getChildNodes().item(x);
			String name = childNode.getNodeName();
			
			if(name == "dataValidationErrorAction")
			{
				result.add(deserializeDataValidationErrorAction(childNode));
			}
			else if(name == "textOutputAction")
			{
				result.add(deserializeTextOutputAction(childNode));
			}
			else if(name == "boMethodAction")
			{
				result.add(deserializeBOMethodAction(childNode, rootEntity));
			}
			else if(name == "userNotificationAction")
			{
				result.add(deserializeUserNotificationAction(childNode, entitiesHelper));
			}
		}
		
		return result;
	}	
	private static IRuleAction deserializeDataValidationErrorAction(Node node) 
	{
		RuleActionDataValidationError action = new RuleActionDataValidationError();
		action.setMessage(node.getAttributes().getNamedItem("message").getNodeValue());
		return action;
	}
	private static IRuleAction deserializeTextOutputAction(Node node) 
	{
		RuleActionTextOutput action = new RuleActionTextOutput();
		action.setText(node.getAttributes().getNamedItem("text").getNodeValue());
		return action;
	}
	private static IRuleAction deserializeBOMethodAction(Node node, RulesEngineEntity rootEntity) throws RulesEngineDeserializationException 
	{
		String methodId = node.getAttributes().getNamedItem("id").getNodeValue();
		RulesEngineEntityMethod method = rootEntity.getActionById(methodId);
		if(method == null)
			throw new RulesEngineDeserializationException("Unable to find the entity action method with id " + methodId + " for the entity " + rootEntity.getName()); 					

		List<String> argumentValues = new ArrayList<String>();
		for(int x = 0; x < node.getFirstChild().getChildNodes().getLength(); x++)
		{
			argumentValues.add(node.getFirstChild().getChildNodes().item(x).getAttributes().getNamedItem("value").getNodeValue());
		}
		
		return new RuleActionEntityMethod(method, argumentValues);
	}
	private static IRuleAction deserializeUserNotificationAction(Node node, IRulesEngineEntitiesHelper entitiesHelper) throws RulesEngineDeserializationException 
	{				
		NotificationPriority priority = NotificationPriority.parse(Integer.parseInt(node.getAttributes().getNamedItem("priority").getNodeValue()));
		
		boolean sendEmail = true;
		if(node.getAttributes().getNamedItem("sendEmail") != null && node.getAttributes().getNamedItem("sendEmail").getNodeValue() != null)
		{
			sendEmail = node.getAttributes().getNamedItem("sendEmail").getNodeValue().equals("1");
		}
		boolean sendSMS = false;
		if(node.getAttributes().getNamedItem("sendSMS") != null && node.getAttributes().getNamedItem("sendSMS").getNodeValue() != null)
		{
			sendSMS = node.getAttributes().getNamedItem("sendSMS").getNodeValue().equals("1");
		}		
		
		ArrayList<Integer> userIds = new ArrayList<Integer>();
		List<RuleActionNotificationElement> texts = new ArrayList<RuleActionNotificationElement>();
		
		for(int x = 0; x < node.getChildNodes().getLength(); x++)
		{
			if(node.getChildNodes().item(x).getNodeName().equals("userId"))
			{
				userIds.add(Integer.parseInt(node.getChildNodes().item(x).getAttributes().getNamedItem("value").getNodeValue()));
			}		
			else if(node.getChildNodes().item(x).getNodeName().equals("message"))
			{
				RuleActionNotificationElementType type = RuleActionNotificationElementType.parse(Integer.parseInt(node.getChildNodes().item(x).getAttributes().getNamedItem("type").getNodeValue()));
				
				if(type != null)
				{
					if(type.equals(RuleActionNotificationElementType.LINE_SEPARATOR))
					{
						texts.add(new RuleActionNotificationElement());
					}
					else if(type.equals(RuleActionNotificationElementType.TEXT))
					{						
						texts.add(new RuleActionNotificationElement(node.getChildNodes().item(x).getAttributes().getNamedItem("text").getNodeValue()));
					}
					else if(type.equals(RuleActionNotificationElementType.ENTITY_FIELD))
					{
						texts.add(new RuleActionNotificationElement(deserializeRuleValueConditionEntry(node.getChildNodes().item(x).getFirstChild(), entitiesHelper)));
					}
				}
			}
		}
		int[] users = new int[userIds.size()];
		for(int x = 0; x < userIds.size(); x++)
		{
			users[x] = userIds.get(x);
		}
		
		return new RuleActionUserNotification(users, texts, priority, sendEmail, sendSMS);
	}
}
