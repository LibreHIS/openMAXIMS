package ims.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ContextReferences implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static List<ContextReference> contextReferences;
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ContextReferences.class);

	public synchronized static void initialize()
	{
		getReferences();
	}
	public synchronized static List<ContextReference> getReferences()
	{
		if(contextReferences != null)
			return contextReferences;
		
		List<ContextReference> localContextReferences = new ArrayList<ContextReference>();
		
		DOMParser parser = new DOMParser();
		InputStream init = Thread.currentThread().getContextClassLoader().getResourceAsStream("ctx_ref.xml");
		
		try
		{
			parser.parse(new org.xml.sax.InputSource(init));
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		try
		{
			Document doc = parser.getDocument();
			for(int x = 0; x < doc.getDocumentElement().getChildNodes().getLength(); x++)
			{
				 Node node = doc.getChildNodes().item(0).getChildNodes().item(x);
				 if(node.getNodeName().equals("context"))
				 {
					 ContextReference contextRef = new ContextReference(node.getAttributes().getNamedItem("name").getNodeValue(), node.getAttributes().getNamedItem("key").getNodeValue());
					 
					 for(int y = 0; y < node.getChildNodes().getLength(); y++)
					 {
						 Node childNode = node.getChildNodes().item(y);						 
						 if(childNode.getNodeName().equals("reference"))
						 {
							 contextRef.addReference(new ContextReference(childNode.getAttributes().getNamedItem("name").getNodeValue(), childNode.getAttributes().getNamedItem("key").getNodeValue()));
						 }
					 }
					 
					 localContextReferences.add(contextRef);
				 }
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		try
		{
			init.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		LocalLogger.warn(localContextReferences.size() + " context reference(s) loaded.");
		contextReferences = localContextReferences;
		return contextReferences;
	}
}
