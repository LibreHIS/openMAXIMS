package ims.dto;

import ims.configuration.Role;
import ims.configuration.User;

import ims.domain.DomainSession;
import ims.dto.client.App_role_user;
import ims.dto.client.Dbuse;
import ims.dto.client.App_users;
import ims.framework.Context;
import ims.configuration.XMLConfiguration;
import ims.crypto.Encryption;
import ims.crypto.EncryptionType;
import ims.framework.interfaces.IAppUser;

import ims.framework.utils.Date;
import ims.framework.utils.DateFormat;
import ims.framework.utils.DateTime;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;


public class DTOConfiguration extends XMLConfiguration
{
	private static final long	serialVersionUID	= 1L;

	
	private static HashMap  dtoRoles = new HashMap();
	
	
	/**
	 * @param url
	 * @throws Exception
	 */
	
	public DTOConfiguration(String url, HttpSession session) throws java.lang.Exception 
	{
		super(url);		
		this.session = session;
		
	}

	public static Role getRole(int roleId)
	{
		// Set the navigation, formaccess at this state
		Role role =(Role)dtoRoles.get(new Integer(roleId));
		
		
		
		return role;
	}
	
	public IAppUser login(String name, String password, DomainSession sess) throws Exception
	{
		// Create a User record and assign to domainSession
		// for the login process
		User user = new User();
		user.setUsername(name);
		user.setPassword(password);
		domainSession =ims.domain.DomainSession.getSession(this.session);
		domainSession.setUser(user);
		impl = new DTODomainImplementation(domainSession);
		
		loadDbuse();
		user = populateUserRecord(name, user);
		if (user == null)
			throw new Exception("Invalid User Specified");
		user.setRoles(populateUserRoles(user));
		return user;
	}

	public User login(String st) 
	{
		if (st == null)
			return null;
		return null;
	}
	
	public void logout()
	{
		Connection connection = null;
		try
		{
			connection =impl.getConnection(false);
		}
		catch (ResultException e)
		{
			//do nothing but try to logout and close anyway
		}
		
		if (connection != null)
		{
			connection.logout();
			connection.close();
		}
	}
	
	public void setHttpSession(javax.servlet.http.HttpSession session)
	{
		// Required on a logout and re-login as the previous session
		// had already been invalidated
		this.session = session;
	}

	private User populateUserRecord(String userName, User user) throws Exception
	{
		App_users dtoUser = (App_users)impl.getDTOInstance(App_users.class);
		dtoUser.Filter.clear();
		dtoUser.Filter.Uname = String.valueOf(userName);
		dtoUser.get();
		if(dtoUser.DataCollection.count() == 0)
			return null;
		App_users.App_usersRecord userRec = dtoUser.DataCollection.get(0);
		
		String active = userRec.Active;
		if (active != null && active.equals("A"))
			user.setActive(true);
		else
			user.setActive(false);
		String effr = userRec.Effr;
		if (effr != null && !effr.equals(""))
		{
			try {
				user.setEffectiveFrom(new DateTime(new Date(effr, DateFormat.ISO)));
			} catch (ParseException e) {
				throw new Exception("ParseException occurred setting effective from date " + e.getMessage());
			}
		}
		String efft =userRec.Efft;
		if (efft != null && !efft.equals(""))
		{
			try {
				user.setEffectiveTo(new DateTime(new Date(efft, DateFormat.ISO)));
			} catch (ParseException e) {
				throw new Exception("ParseException occurred setting effective to date " + e.getMessage());
			}
		}
		String id = userRec.User_id;
		user.setUserId(Integer.parseInt(id));
		user.setUserRealName(userRec.Realname);
		user.setUsername(userRec.Uname);
		user.setLoginTime(new DateTime());
		user.setTheme("blue");
		String pwdDate = userRec.Passwd_exp;
		if (pwdDate != null && !pwdDate.equals(""))
		{
			try {
				user.setPwdExpDate(new DateTime(new Date(pwdDate, DateFormat.ISO)));
			} catch (ParseException e) {
				throw new Exception("ParseException occurred setting password expiry date " + e.getMessage());
			}
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	private Set populateUserRoles(User user)
	{
		// Make a call to the App_user_cfg service to get the list of roles for the user
		App_role_user dtoRole = (App_role_user)impl.getDTOInstance(App_role_user.class);
		dtoRole.Filter.clear();
		dtoRole.Filter.User_id = String.valueOf(user.getUserId());
		Result res = dtoRole.list();
		if ((res != null && res.getId() < 0) || dtoRole.DataCollection.count() <= 0)
			return new HashSet();  // Return an empty hashset
		
		Set roleSet = new HashSet();
		for (int i=0; i<dtoRole.DataCollection.count(); i++)
		{
			App_role_user.App_role_userRecord roleDTO = dtoRole.DataCollection.get(i);
			Role role = new Role();
			String active = roleDTO.Active;
			if (active == null || active.equals("I"))
				continue;
			role.setActive(true);
			role.setId(Integer.parseInt(roleDTO.Role_id));
			role.setName(roleDTO.Role_name);
			role.setNavigation(super.getNavigation());
			
			roleSet.add(role);
			dtoRoles.put(new Integer(role.getId()), role);
		}
		
		return roleSet;
	}

	public Map getRegisteredForms()
	{
		return(super.getRegisteredForms());
	}

	public Map getRegisteredImages()
	{
		return (super.getRegisteredImages());
	}

	public boolean isTabLayout()
	{
		return super.isTabLayout();
	}

	public void newPassword(User user, String value) 
	{
		// Modify the current user's password with the app_user dto class
		App_users dtoUser = (App_users)impl.getDTOInstance(App_users.class);
		dtoUser.EditFilter.IncludeDbpass=false;
		dtoUser.Filter.clear();
		dtoUser.Filter.User_id = String.valueOf(user.getUserId());
		dtoUser.get();
		
		// Update the password and the expiry date
		App_users.App_usersRecord record = dtoUser.DataCollection.get(0);
		
		// Encrypt the password
		record.Upass = Encryption.encrypt(value, EncryptionType.PRIME);
		
		DateTime dt = user.getPwdExpDate();
		if (dt != null)
		{
			record.Passwd_exp = dt.getDate().toString(DateFormat.ISO);
		}
		dtoUser.update();	
	}

	public boolean isFormAccessible(Role role, int formId) 
	{
		//TODO
		//return role.isDTOFormAccessible(formId);
		if (role.getId() > 0)
		{
			//Simply here to avoid warnings
		}
		if (formId > 0)
		{
			//Simply here to avoid warnings			
		}
		return true;
	}

	public String getDbuse() 
	{
		return dbuse;
	}
	
	private void loadDbuse()
	{
		// NAS Call to dbuse server
		if (dbuse != null)
			return;
			
		Dbuse dbuseRec = (Dbuse)impl.getDTOInstance(Dbuse.class);
		Result res = dbuseRec.get();
		if ((res != null && res.getId() < 0) || dbuseRec.DataCollection.count() <= 0)
			return;

		Dbuse.DbuseRecord rec =  dbuseRec.DataCollection.get(0);
		dbuse=rec.Type;
	}

	public boolean isFormReadOnly(Role role, int formId, Context context) 
	{
		//TODO
		//return role.isDTOFormReadOnly(formId, context);
		if (role.getId() > 0)
		{
			//Simply here to avoid warnings
		}
		if (formId > 0)
		{
			//Simply here to avoid warnings			
		}
		if (context != null)
		{
			//Simply here to avoid warnings			
		}
		return false;
	}
		
	private DTODomainImplementation impl=null;
	private HttpSession session=null;
	private ims.domain.DomainSession domainSession=null;
	private String dbuse=null;
	
}
