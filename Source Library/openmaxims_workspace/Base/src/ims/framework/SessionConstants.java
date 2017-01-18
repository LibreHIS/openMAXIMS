/*
 * Created on 16-Jan-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework;

import java.io.Serializable;

/**
 * An abstract class to hold static final Strings used as attribute
 * names in HttpSession.
 * @author gcoghlan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class SessionConstants implements Serializable
{
	private static final long serialVersionUID = 1L;	
	
	public static final String REMOTE_HOST = "REMOTE_HOST";	
	public static final String REMOTE_ADDR = "REMOTE_ADDR";		
	public static final String DOMAIN_SESSION = "DomainSession";
	public static final String USER = "User";
	public static final String APPLICATION_CONTEXT = "ApplicationContext";
	public static final String SESSION_DATA = "SessionData";
	public static final String HL7_INTERFACE_ENGINE = "HL7_INTERFACE_ENGINE";
	//public static final String CONFIGURATION = "Configuration";
	//public static final String MESSAGES = "Messages";	
	//public static final String SESSION_LOGGER = "SESSION_LOGGER";
	//public static final String INCLUDE_RIE = "INCLUDE_RIE";
	//public static final String RIE_BO_CLASS_NAME = "RIE_BO_CLASS_NAME";
	//public static final String LIST_RECORD_INFO = "LIST_RECORD_INFO";
}
