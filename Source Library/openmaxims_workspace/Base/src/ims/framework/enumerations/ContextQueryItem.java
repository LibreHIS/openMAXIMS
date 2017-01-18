package ims.framework.enumerations;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ContextQueryItem implements Serializable
{
	private static final long serialVersionUID = 1L;	
	private static final org.apache.log4j.Logger LocalLogger = ims.utils.Logging.getLogger(ContextQueryItem.class);
	
	private String key;	
	private static List<ContextQueryItem> iClearInfoList = null;
	
	public static ContextQueryItem PATIENT = new ContextQueryItem("_cvp_Core.PatientShort");
	public static ContextQueryItem CLINICAL_CONTACT = new ContextQueryItem("_cvp_Core.CurrentClinicalContact");
	public static ContextQueryItem CURRENT_CARE_CONTEXT = new ContextQueryItem("_cvp_Core.CurrentCareContext");
	public static ContextQueryItem CURRENT_CARE_CONTEXT_TYPE = new ContextQueryItem("_cvp_Core.CurrentCareContext.ContextType");
	public static ContextQueryItem EPISODE_OF_CARE = new ContextQueryItem("_cvp_Core.EpisodeofCareShort");
	public static ContextQueryItem PATIENT_DETAILS = new ContextQueryItem("_cvp_CcoSched.PatientSearch.PatientDetails");
	public static ContextQueryItem CATS_REFERRAL = new ContextQueryItem("_cvp_CareUk.CatsReferral");
	public static ContextQueryItem PATIENT_ICP_RECORD = new ContextQueryItem("_cv_ICP.PatientICPRecord");
	public static ContextQueryItem LOGGEDIN_USER = new ContextQueryItem("_cvp_CcoSched.LoggedInUserId");	
	public static ContextQueryItem LOGGEDIN_USER_RSNO = new ContextQueryItem("_cvp_CcoSched.LoggedInRsno");
	
	private ContextQueryItem(String key)
	{
		this.key = key;
	}
	
	public static List<ContextQueryItem> getAll()
	{
		if(iClearInfoList == null)
		{
			loadIClearInfoList();
		}
		
		return iClearInfoList;
	}
	
	private synchronized static void loadIClearInfoList()
	{
		iClearInfoList = new ArrayList<ContextQueryItem>();
		
		DOMParser parser = new DOMParser();		
		
		InputStream init = Thread.currentThread().getContextClassLoader().getResourceAsStream("iClearInfo.xml");
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
					 iClearInfoList.add(new ContextQueryItem(node.getAttributes().getNamedItem("key").getNodeValue()));
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
		
		LocalLogger.warn("IClearInfo - " + iClearInfoList.size() + " context variable(s) loaded.");
	}

	public String getKey()
	{
		return key;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof ContextQueryItem)
			return key.equals(((ContextQueryItem)obj).key);
		return false;
	}
}
