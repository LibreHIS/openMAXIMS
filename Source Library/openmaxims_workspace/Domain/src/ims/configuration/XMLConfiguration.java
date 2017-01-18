package ims.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.xml.sax.InputSource;

import ims.domain.DomainSession;
import ims.framework.interfaces.IAppUser;
import ims.framework.utils.Color;
import ims.framework.utils.DateFormat;
import ims.framework.utils.DateTime;

public class XMLConfiguration extends Configuration
{
	private static final long serialVersionUID = 1L;
	private static final HashMap<Integer, Role> roles = new HashMap<Integer, Role>();
	private final Navigation navigation = new Navigation();

	private int currRootId = 0;
	private int currSecId = 0;
	private int currFormId = 0;	
	private RootGroup currRoot;
	private SecondGroup currSec;

	public static Role getRole(int roleId)
	{
		return roles.get(new Integer(roleId));
	}
	
	protected Navigation getNavigation()
	{
		return navigation;
	}
	
	public XMLConfiguration(String url) throws Exception
	{				
		final HashMap<Object, Object> namespaces = new HashMap<Object, Object>();

		org.xml.sax.XMLReader reader = new org.apache.xerces.parsers.SAXParser();
		reader.setContentHandler(new org.xml.sax.helpers.DefaultHandler()
		{
			@SuppressWarnings("unchecked")
			public void startElement(String uri, String name, String prefix, org.xml.sax.Attributes atts)
			{
				if (name.equals("namespace"))
				{
					RegNamespace namespace = new RegNamespace();
					int id = Integer.parseInt(atts.getValue("id"));
					namespace.setName(atts.getValue("name"));
					namespace.setNamespaceID(id);
					namespace.setMap(atts.getValue("maxims"));
					namespaces.put(new Integer(id), namespace);
					registeredNamespaces.put(namespace.getMap(), namespace);
				}
				else if (name.equals("form"))
				{
					int id = Integer.parseInt(atts.getValue("id"));
					RegForm form = new RegForm();
					form.setCaption(atts.getValue("caption"));
					form.setDialog(atts.getValue("isDialog").equals("true"));
					//form.setFormID(id % 1000);
					form.setFormID(id);
					form.setName(atts.getValue("name"));
					form.setDescription(atts.getValue("description"));
					form.setNamespace((RegNamespace)namespaces.get(new Integer(id / 1000)));
					form.setLogic(atts.getValue("logic"));
					form.setDomainImplementation(atts.getValue("domainlogic"));
					XMLConfiguration.this.registeredForms.put(new Integer(form.getModuleID()), form);
				}				
				else if (name.equals("image"))
				{
					RegImage image = new RegImage(Integer.parseInt(atts.getValue("id")));
					image.setImagePath(atts.getValue("path"));
					image.setIsSystem(Boolean.TRUE);
					XMLConfiguration.this.registeredImages.put(new Integer(image.getId()), image);
				}
				else if (name.equals("navigation"))
				{
					navigation.setId(0);
					navigation.setNavigationName(atts.getValue("name"));
					navigation.setStartupForm((RegForm)XMLConfiguration.this.registeredForms.get(new Integer(atts.getValue("startUpForm"))));					
					navigation.setPatSearchForm((RegForm)XMLConfiguration.this.registeredForms.get(new Integer(atts.getValue("patientSearchForm"))));					
				}
				else if (name.equals("rootGroup"))
				{
					currSec = null;
					currRoot = new RootGroup();					
					currRoot.setId(currRootId);
					currRoot.setGroupName(atts.getValue("text"));
					navigation.addRootGroup(currRoot);
					currRootId++;
					currSecId = 0;
					currFormId = 0;
				}
				else if (name.equals("secGroup"))
				{
					currSec = new SecondGroup();
					currSec.setId(currSecId);
					currSec.setPositionIndex(currFormId);
					currSec.setGroupName(atts.getValue("text"));
					currRoot.addSecGroup(currSec);
					currSecId++;
					currFormId++;
				}
				else if (name.equals("navForm"))
				{
					NavForm navForm = new NavForm();
					navForm.setNodeText(atts.getValue("text"));
					navForm.setReadOnly(false);
					navForm.setPositionIndex(currFormId);
					navForm.setAppForm((RegForm)XMLConfiguration.this.registeredForms.get(new Integer(atts.getValue("form"))));
					if (currSec != null)
					{
						currSec.addNavForm(navForm);
					}
					else
					{
						currRoot.addNavForm(navForm);						
						currFormId++;
					}	
				}				
				else if (name.equals("role"))
				{
					Role role = new Role();
					role.setId(Integer.parseInt(atts.getValue("id")));
					role.setName(atts.getValue("name"));
					Integer startUpform = Integer.parseInt(atts.getValue("startUpForm"));
					Integer patientSearchForm = Integer.parseInt(atts.getValue("patientSearchForm"));
					role.setActive(true);	
					
					Navigation cloneNavigation = (Navigation)navigation.clone();
					
					cloneNavigation.setPatSearchForm((RegForm)XMLConfiguration.this.registeredForms.get(patientSearchForm));
					cloneNavigation.setStartupForm((RegForm)XMLConfiguration.this.registeredForms.get(startUpform));
					role.setNavigation(cloneNavigation);
					
					roles.put(new Integer(role.getId()), role);
				}
				else if (name.equals("user"))
				{
					User user = new User();
					String userId =atts.getValue("user_id");
					if (userId != null) 
						user.setUserId(Integer.parseInt(userId));
					user.setUsername(atts.getValue("login"));
					user.setPassword(atts.getValue("hash"));
					user.setClearPassword(atts.getValue("password"));
					user.setUserRealName(atts.getValue("realName"));
					user.setTheme(atts.getValue("theme"));
					String expiryDate = atts.getValue("passwordExiryDate");
					try
					{
						SimpleDateFormat sf = new SimpleDateFormat(DateFormat.STANDARD.toString());
						user.setPwdExpDate(expiryDate == null ? null : new DateTime(sf.parse(expiryDate)));
					}
					catch (ParseException e)
					{
						user.setPwdExpDate(null);
						e.printStackTrace();
					}
					user.setRoles(new HashSet());
					for(int x = 0; x < roles.size(); x++)
					{
						user.getRoles().add(roles.get(new Integer(x)));
					}
					XMLConfiguration.this.registeredUsers.put(user.getUsername(), user);
				}
				else if (name.equals("userRole"))
				{
					Role role = roles.get(atts.getValue("role"));
					if (role != null)
						((User)XMLConfiguration.this.registeredUsers.get(atts.getValue("user"))).getRoles().add(role);
				}
				else if (name.equals("layout"))
					XMLConfiguration.this.isTab = atts.getValue("tabs").equals("true");				
			}
			
			public void endElement(String uri, String name, String qName)
			{
				if (name.equals("secGroup"))
				{
					currSec = null;
				}
			}
		});
		
		InputSource in = new InputSource(getClass().getClassLoader().getResourceAsStream(url));
		reader.parse(in);
		Color.loadColourImages(this.registeredImages);
	}

	public HashMap getRegisteredNamespaces()
	{
		return registeredNamespaces;
	}
	
	public Map getRegisteredForms() 
	{
		return this.registeredForms;
	}

	public Map getRegisteredImages()
	{
		return this.registeredImages;
	}

	public boolean isTabLayout()
	{
		return this.isTab;
	}

	public IAppUser login(String name, String password, DomainSession session) throws Exception
	{
		User user = (User)this.registeredUsers.get(name);
		user = user.copy();
		if (user == null || !(user.getPassword().equals(super.getHash(password))))
			throw new Exception("Login failed, invalid username and/or password.");
			
		//Store the plain password in user so can be used later for onward logins to DTO
		user.setPassword(password);
		user.setLoginTime(new DateTime());
		user.setMosId(new Integer(1));
		return user;
	}	
	
	public  IAppUser login(String st, DomainSession session) throws Exception
	{
		User user = (User)this.registeredUsers.get(st);
		if (user == null)
			throw new Exception("Login failed");
		return user;
	}
	
	public IAppUser secureLogin(String name, String password, String passwordSalt, DomainSession session) throws Exception
	{
		User user = (User)this.registeredUsers.get(name);
		user = user.copy();
		if (user == null || !password.equals(super.getSHA1Hash(user.getClearPassword(), passwordSalt)))
			throw new Exception("Login failed, invalid username and/or password.");
			
		//Store the plain password in user so can be used later for onward logins to DTO
		user.setPassword(password);
		user.setLoginTime(new DateTime());
		user.setMosId(new Integer(1));
		return user;
	}
	
	public void newPassword(IAppUser user, String value, DomainSession session)
	{
	}

	public void lockAccount(IAppUser user, boolean value, DomainSession session)
	{
	}
	
	public String getDbuse() 
	{
		return null;
	}
		
	private HashMap<Object, Object> registeredForms = new HashMap<Object, Object>();
	private HashMap<Object, Object> registeredImages = new HashMap<Object, Object>();
	private HashMap<Object, Object> registeredUsers = new HashMap<Object, Object>();
	private static HashMap<Object, Object> registeredNamespaces = new HashMap<Object, Object>();
	private boolean isTab = false;

	public ClassConfig getClassConfig()
	{
		return null;
	}

	public void reload() throws Exception
	{
	}

	public static RegNamespace getRegisteredNamespace(String maxims)
	{
		return (RegNamespace) registeredNamespaces.get(maxims);
	}

	// WDEV-17644 - do nothing
	public java.util.List getPathwayEntities() 
	{
		return null;
	}

	public IAppUser getAppUser(String username, DomainSession session) throws Exception 
	{
		return null;
	}
}