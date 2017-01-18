/*
 * Created on 12-Feb-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;

import ims.configuration.NavForm;
import ims.configuration.Navigation;
import ims.configuration.RegNamespace;
import ims.configuration.Role;
import ims.configuration.RootGroup;
import ims.configuration.SecondGroup;
import ims.configuration.XMLConfiguration;
import ims.domain.DomainSession;
import ims.domain.impl.DomainImpl;
import ims.dto.client.App_user_cfg;
import ims.dto.client.App_users;
import ims.dto.client.App_users.App_usersRecord;
import ims.dto.client.Resource;
import ims.dto.client.Resource.ResourceRecord;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.INavigation;
import ims.framework.utils.Date;
import ims.framework.utils.DateTime;
import ims.framework.utils.Time;

/**
 * @author gcoghlan
 */
public class DTODomainImplementation extends DomainImpl implements DTODomain 
{
	private static final long	serialVersionUID	= 1L;
	
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(DTODomainImplementation.class);
	public static final String DTO_CONNECTION = "DTO_CONNECTION";
	private static final String READ_ONLY="R";
	
	/**
	 * 
	 */
	public DTODomainImplementation() {
		super();
		if (LOG.isDebugEnabled())
			LOG.debug("Constructor called : DTODomainImplementation()");
	}

	public DTODomainImplementation(DomainSession session) {
		super();
		setContext(session);
		if (LOG.isDebugEnabled())
			LOG.debug("Constructor called : DTODomainImplementation()");
	}

	/**
	 * @see ims.dto.DTOInterface#getDTOInstance(java.lang.Class)
	 */
	public Object getDTOInstance(Class clazz)
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">>getDTOInstance(Class="+clazz+")");
		return DTOFactory.getFactory().getDTOInstance(clazz, getConnection());
	}
	
	/**
	 * @see ims.dto.DTODomain#getDateTime()
	 */
	public DateTime getDateTime()
	{
		if (LOG.isDebugEnabled())
			LOG.debug(">>getDateTime()");
		java.util.Calendar serverTime = DTOFactory.getFactory().getDateTime(getConnection());
		Date date = new ims.framework.utils.Date(serverTime.getTime());
		Time time = new ims.framework.utils.Time();
		time.setHour(serverTime.get(Calendar.HOUR_OF_DAY));
		time.setMinute(serverTime.get(Calendar.MINUTE));
		DateTime dateTime = new DateTime(date, time);
		if (LOG.isDebugEnabled())
			LOG.debug("serverTime:"+serverTime+", dateTime:"+dateTime);
		return dateTime;
	}

	/**
	 * Returns false - DTO implementations do not support the
	 * domain LookupService.
	 * @see ims.domain.impl.DomainImpl#usesLookupService()
	 */
	protected boolean usesLookupService() {
		if (LOG.isDebugEnabled())
			LOG.debug("usesLookupService() returns true");
		return true;
	}

	/**
	 * @see ims.domain.impl.DomainImpl#free()
	 */
	public void free() {
		if (LOG.isDebugEnabled())
			LOG.debug("free()");

		
		Connection connection = getConnection(false);
		if ( null != connection && !this.dtoOnly()) {
			// SN 
			if (connection.getLastError() != null && checkDtoNasReturnCode(connection.getLastError().getId()))
			{			
				if (LOG.isDebugEnabled())
					LOG.debug("Closing connection.");
				connection.close();
			}
		}
	}

	/**
	 * cater for negative results that still have valid dto connections. In this case a close connection is not needed.
	 * @return if close connection is needed
	 */
	protected boolean checkDtoNasReturnCode(int f_ret)
	{
		switch (f_ret) {
			case -2:
				return false;
			case -3:
				return false;
		case -40:
			return false;
		case -5:
			return false;
		case -6:
			return false;
		case -7:
			return false;
		case -11:
			return false;
		case -12:
			return false;
		case -13:
			return false;
		case -16:
			return false;
		case -24:
			return false;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * @see ims.domain.impl.DomainImpl#setContext(ims.domain.DomainSession)
	 */
	protected void setContext(DomainSession domainSession) throws ResultException {
		if (LOG.isDebugEnabled())
			LOG.debug("setContext(...)");
		super.setContext(domainSession, getLookupServiceClass());

		Connection connection = getConnection(false);
		if ( null != connection ) {
				if (LOG.isDebugEnabled())
					LOG.debug("Opening connection.");
				connection.open(domainSession);
		}
	}
	
	/**
	 * Gets an existing connection from the domain session.
	 * If there is none already in the domain session, then a
	 * new connection is created + inserted in the domain session. 
	 * @return new or existing Connection
	 */
	protected Connection getConnection() throws ResultException {
		return getConnection(true);
	}

	/**
	 * Gets an existing connection from the domain session.
	 * If none exists in the session then one is created & added if
	 * <code>create</code> is true
	 * @param create - whether to create a new Connection if one is not
	 * already in the session.
	 * @return
	*/
	public final Connection getConnection(boolean create) throws ResultException {
		if (LOG.isDebugEnabled())
			LOG.debug("getConnection(create="+create+")");
		DomainSession domainSession = getSession();
		
		if (domainSession == null) // BW
			return null;
		
		Connection connection = (Connection) domainSession.getAttribute(DTO_CONNECTION);
		if (null == connection && create) {
			if (LOG.isInfoEnabled())
				LOG.info("Creating new connection, and inserting in domainSession.");
			IAppUser user = domainSession.getUser();
			connection = ConnectionFactoryManager.getConnection(user.getUsername(), user.getClearPassword(),domainSession);			
			// insert connection into domain session
			domainSession.setAttribute(DTO_CONNECTION, connection);
		}
		if (LOG.isDebugEnabled())
			LOG.debug("Got connection ="+connection);
		return connection;
	}
	
	public Class getLookupServiceClass()
	{
		return ims.dto.DTOLookupServiceImpl.class;
	}
	
	public boolean dtoOnly() {
		return true;
	}

	/**
	 * Returns a HCP VO with this user's username
	 * or null if this user is not a HCP
	 * @param username
	 * @return
	 */
	public Object getHcpUser() 
	{
		if (!this.dtoOnly()) {
			return super.getHcpUser();
		}
		IAppUser user = this.getSession().getUser(); 
		if (user == null) return null;

		if (user.isHcpChecked() == false) 
		{
			user.setHcpChecked(true);
			Result result = null;
			try
			{
				App_users appUsers = (App_users)getDTOInstance(App_users.class);
				appUsers.Filter.clear();
				appUsers.Filter.Uname = user.getUsername();
				result = appUsers.get();
				if (result != null)
				{
					LOG.warn("DTODomainImpl:getHcpUser failed. " + result.getMessage());
					return null;			
				}
				if (appUsers.DataCollection.count() == 0){
					LOG.warn("DTODomainImpl:getHcpUser failed. User not registered in APP_USERS.");
					return null;							
				}
				App_usersRecord arec = appUsers.DataCollection.get(0);
				user.setHcpId(new Integer(arec.User_id));
				
				
				Resource resource = (Resource)getDTOInstance(Resource.class);
				resource.Filter.clear();
				resource.Filter.Rsno = "" + arec.User_id;
				result = resource.get();
				if (result != null)
				{
					LOG.warn("DTODomainImpl:getHcpUser failed. " + result.getMessage());
					return null;			
				}
				if (resource.DataCollection.count() == 0){
					LOG.warn("DTODomainImpl:getHcpUser failed. User not registered in RESOURCE.");
					return null;							
				}
				ResourceRecord rec = resource.DataCollection.get(0);
				user.setHcpId(user.getHcpId());
				user.setUserRealName(rec.Name);
				
				Class c = Class.forName("ims.core.vo.Hcp");
				Method m = c.getMethod("extractHcpFromUser", new Class[]{user.getClass()});			
				this.getSession().setAttribute("AppUserHcpVo",m.invoke(null, new Object[]{user}));
			}			
			catch(Throwable e)
			{
				LOG.warn("getHcpUser failed." + e.getMessage(), e);
				return null;
			}
		}
		return this.getSession().getAttribute("AppUserHcpVo");
	}

	
	protected Navigation getRoleNavigation(Role role)
	{
		IAppUser user = this.getSession().getUser();
		// List all screens for this role
		HashMap roleScreens = populateRoleForms(role, user);
		if (roleScreens != null)
		{
			// Setup navigation based on results from roleScreens HashMap
			// Get all forms for the navigation and specify whether they are accessible or not
			Navigation newNav = new Navigation();
			INavigation nav = role.getRoleNavigation();

			newNav.setStartupForm(nav.getStartupForm());
			newNav.setNavigationName(nav.getNavigationName());
			newNav.setPatSearchForm(nav.getPatSearchForm());
			for (int k=0; k<nav.getNavRootGroups().length; k++)
			{
				RootGroup rootGroup = (RootGroup) nav.getNavRootGroups()[k];
				RootGroup newRootGroup = new RootGroup();
				for (int j=0; j<rootGroup.getNavForms().length; j++)
				{
					NavForm form = (NavForm) rootGroup.getNavForms()[j];
					String formAccess = (String) roleScreens.get(String.valueOf(form.getAppForm().getFormId()));
					if (formAccess == null)  // Form is not accessible
						continue;// Do not include in the navigation
					else if (formAccess.equals(READ_ONLY))
						form.setReadOnly(true);
					form.setPositionIndex(j);
					newRootGroup.addNavForm(form);
				}
				newRootGroup.setId(rootGroup.getId());
				newRootGroup.setGroupName(rootGroup.getGroupName());
				

				//INavSecondGroup
				for (int j=0; j<rootGroup.getNavGroups().length; j++)
				{
					SecondGroup secondGroup = (SecondGroup) rootGroup.getNavGroups()[j];
					SecondGroup newSecondGroup = new SecondGroup();
					for (int x=0; x<secondGroup.getNavForms().length; x++)
					{
						NavForm form = (NavForm) secondGroup.getNavForms()[x];
						String formAccess = (String) roleScreens.get(String.valueOf(form.getAppForm().getFormId()));
						if (formAccess == null)  // Form is not accessible
							continue;// Do not include in the navigation
						else if (formAccess.equals(READ_ONLY))
							form.setReadOnly(true);
						form.setPositionIndex(x);
						newSecondGroup.addNavForm(form);
					}
					newSecondGroup.setGroupName(secondGroup.getGroupName());
					newSecondGroup.setPositionIndex(j);
					newSecondGroup.setId(secondGroup.getId());
					newRootGroup.addSecGroup(newSecondGroup);
				}
				newNav.addRootGroup(newRootGroup);
			}
			return newNav;
		}
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	private HashMap populateRoleForms(Role role, IAppUser user)
	{
		// I checked with Rory, and apparently, they never user app_role_cfg,
		// so I am going to list off app_user_cfg for the role and user
		App_user_cfg dtoRole = (App_user_cfg)getDTOInstance(App_user_cfg.class);
		dtoRole.Filter.clear();
		dtoRole.Filter.Role_id = String.valueOf(role.getId());
		dtoRole.Filter.User_id = String.valueOf(user.getUserId());
		Result res = dtoRole.list();
		if ((res != null && res.getId() < 0) || dtoRole.DataCollection.count() <= 0)
			return null;
		
		HashMap formSet = new HashMap();
		for (int i=0; i<dtoRole.DataCollection.count(); i++)
		{
			App_user_cfg.App_user_cfgRecord screen = dtoRole.DataCollection.get(i);
			if (screen.Active != null && screen.Active.equals("I"))
				continue;
			
			// With the screen ID, we want to replace the
			// letters with the corresponding namespace numbers
			// and then convert this to an integer
			// Get the namespaces, and replace the maxims namespace with the local one
			String maximsNamespace = screen.Screen_id.substring(0, 2);
			RegNamespace ns = getFormNamespace(maximsNamespace);
			String formId=null;
			if (ns != null)
			{
				formId = screen.Screen_id.replaceAll(maximsNamespace + ".0", String.valueOf(ns.getNamespaceID()));
			}
			else
				continue; // Cannot set formID
			
			formSet.put(formId, screen.Read_write); 
		}
		
		return formSet;
	}
	
	/**
	 * Get the registered namespace for the given maxims value
	 * @param maxims
	 * @return namespace or null if not found
	 */
	private RegNamespace getFormNamespace(String maxims)
	{
		return XMLConfiguration.getRegisteredNamespace(maxims);
	}
	
}
