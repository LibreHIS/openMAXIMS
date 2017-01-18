package ims.domain;

import java.util.StringTokenizer;

import ims.domain.factory.ContextSetterFactory;
import ims.framework.Context;
import ims.framework.interfaces.IContextSetter;

public class UrlParamParser
{
	private Context ctx;
	private SessionData sessData;
	private String urlString;
	
	public UrlParamParser(Context ctx, SessionData sessData, String urlString)
	{
		this.ctx = ctx;
		this.sessData = sessData;
		this.urlString = urlString;		
	}
	
	public void processParams()
	{
		int idx = urlString.indexOf("?");
		if (idx < 0 || idx == (urlString.length() - 1))
			return;

		ContextSetterFactory setf = new ContextSetterFactory(sessData.domainSession.get(), ctx);
		if (!setf.hasContextSetter())
			throw new RuntimeException("No Context Setter available. Cannot set Context.");
		
		IContextSetter setter = setf.getContextSetter();
		
		String params = urlString.substring(idx + 1);		
		StringTokenizer st = new StringTokenizer(params, "&");
		while (st.hasMoreTokens())
		{
			String param = st.nextToken();
			String[] nameVal = param.split("=");
			if (nameVal.length < 2)
				continue;
			
			String name = nameVal[0];
			String val = nameVal[1];
			
			if (name.equals("pkey"))
			{
				setter.setPatient(Integer.parseInt(val));				
			}
			else if (name.equals("eoc"))
			{
				setter.setEpisodeOfCare(Integer.parseInt(val));								
			}
			else if (name.equals("ctx"))
			{
				setter.setCareContext(Integer.parseInt(val));				
			}
			else if (name.equals("con"))
			{
				setter.setClinicalContact(Integer.parseInt(val));				
			}
		}		
	}	
}
