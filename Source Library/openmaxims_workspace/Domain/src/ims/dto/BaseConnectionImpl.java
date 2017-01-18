package ims.dto;

import java.util.HashMap;

public abstract class BaseConnectionImpl 
{

	public String[] splitResponseItem(String message)
	{
		message = message.substring(0, message.length());
		return message.split(String.valueOf(NASMessageCodes.PAIRSEPARATOR));
	}
	@SuppressWarnings("unchecked")
	public HashMap splitResponseItemToMap(String message)
	{
		message = message.substring(0, message.length());
		String splitMsg[] = message.split(String.valueOf(NASMessageCodes.PAIRSEPARATOR));
		if (splitMsg == null)
			return new HashMap();
		HashMap map = new HashMap();
		for (int i=0; i<splitMsg.length; i++)
		{
			String split = splitMsg[i];
			int sepIndex = split.indexOf(NASMessageCodes.ATTRIBUTEVALUESEPARATOR);
			// wdev-4204 - We need to check that the attribute value separator was also included in the message!
			if (sepIndex == -1)
				continue;
			
			String key = split.substring(0, sepIndex);
			String value = null;
			if (sepIndex + 1 < split.length())
			{
				value = split.substring(sepIndex + 1);
			} 
			map.put(key, value);
		}
		return map;
		
		
	}
	
	public String getValueFor(HashMap items, String attributeName)
	{
		String value = (String) items.get(attributeName);
		if (value == null)
			return "";
		return value;
	}
}
