package ims.domain.hibernate3;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.RootClass;

import ims.base.cache.ClusterCache;
import ims.configuration.ClassConfig;
import ims.configuration.ConfigFlag;
import ims.configuration.Configuration;
import ims.configuration.NonAuditObject;
import ims.configuration.PathwayEntityEvent;
import ims.domain.DomainSession;
import ims.domain.SessionData;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.factory.FormFactory;
import ims.domain.factory.ImageFactory;
import ims.domain.factory.UserFactory;
import ims.framework.SessionConstants;
import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.IAppUser;
import ims.framework.interfaces.ICache;
import ims.framework.interfaces.IImageProvider;
import ims.framework.interfaces.IUserProvider;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.framework.utils.Image;
import ims.framework.utils.ImageInfo;
import ims.utils.Logging;

public class HibernateConfiguration extends Configuration
{
	private static final Logger			LOG					= Logging.getLogger(HibernateConfiguration.class);

	private static final long			serialVersionUID	= 1L;
	private ClassConfig					classCfg			= null;
	private static transient Map<Integer, IAppForm> forms	= null;
	private static transient Map	images				= null;
	private static transient ArrayList<PathwayEntityEvent> pathwayEntities = null;  // WDEV-17644

	public HibernateConfiguration() throws Exception
	{
		Session session = getSession();
		getRegisteredForms();
		getRegisteredImages();
		loadClassConfig(session);
		getPathwayEntities(session); // WDEV-17644
		session.close();		
	}

	private Session getSession() throws Exception
	{
		return Registry.getInstance().getSessionFactory().openSession();
	}

	@SuppressWarnings("unchecked")
	private void loadClassConfig(Session session) throws Exception
	{
		if (classCfg == null)
		{
			classCfg = new ClassConfig();

			Iterator it = session.createQuery("from NonAuditObject").list().iterator();
			List nonAuditBos = new ArrayList();
			while (it.hasNext())
			{ 
				nonAuditBos.add(((NonAuditObject) it.next()).getBoClassName());
			}
			classCfg.loadNonAuditBos(nonAuditBos);
			List l = getDomainClasses();
			// TODO: Need to remove classes that are either component, or composite owned by another class.
			classCfg.loadAuditableClasses(l);
			
			Iterator iter = Registry.getInstance().getConfiguration().getClassMappings();
			while (iter.hasNext())
			{
				PersistentClass pc = (PersistentClass)iter.next();
				if (pc instanceof RootClass)
				{
					RootClass rc = (RootClass)pc;
					String className = rc.getClassName();
					Class cl = Class.forName(className);
					try
					{
						Method m = cl.getMethod("getCollectionFields", (Class[])null);
						String[] collFields = (String[])m.invoke((Object)null, (Object[])null);
						if (collFields == null)
							collFields = new String[0];
						classCfg.addMappedRootClass(className, collFields);
					}
					catch (NoSuchMethodException ex)
					{
						LOG.warn("Class " + className + " does not have method called getCollectionFields");
					}
				}
			}
			
			ICache cache = ClusterCache.getClusterCache();
			if (cache != null)
			{
				cache.put("configuration/nonauditbos",  classCfg.getNonAuditBosMap());
				classCfg.setNonAuditBosMap((Map)cache.get("configuration/nonauditbos"));
				LOG.warn("nonauditbos successfully loaded in Cluster Cache");
			}		
			LOG.warn("Class Configuration loaded successfully");
		}
	}
	
	public Map getRegisteredForms()
	{
		if (forms == null)
		{
			forms = new HashMap<Integer, IAppForm>();
			FormFactory factory = new FormFactory(new DomainSession());
			if (!factory.hasFormProvider())
			{
				throw new DomainRuntimeException("FormProvider class not available. Can't load Forms");
			}
 
			IAppForm[] allForms = factory.getFormProvider().getAllForms();
			for (int i = 0; i < allForms.length; i++)
			{
				IAppForm form = allForms[i];
				forms.put(new Integer(form.getFormId()), form);
			}
			LOG.warn("Registered Forms loaded successfully");
		}
		return forms;
	}

		@SuppressWarnings("unchecked")
	public Map getRegisteredImages()
	{
		if (images == null)
		{
			images = new HashMap();
			DomainSession domSess = new DomainSession();
			domSess.setAttribute(SessionConstants.SESSION_DATA, new SessionData());
			ImageFactory factory = new ImageFactory(domSess);
			if (!factory.hasImageProvider())
			{
				throw new DomainRuntimeException("ImageProvider class not available. Can't load Images");
			}
			IImageProvider provider = factory.getImageProvider();
			Image[] allImages = provider.getAllImages();
			for (int i = 0; i < allImages.length; i++)
			{
				Image image = allImages[i];
				if (image.getImageHeight() == -1 || image.getImageWidth() == -1)
				{
					ImageInfo info = image.getImageInfo();
					if (info != null)
					{
						provider.setImageWidthHeight(image, info.getWidth(), info.getHeight());
					}
				}
				images.put(new Integer(image.getImageId()), image);
			}
			Color.loadColourImages(images);
			LOG.warn("Registered Images loaded successfully");
			ICache cache = ClusterCache.getClusterCache();
			if (cache != null)
			{
				cache.put("configuration/images",  images);
				images = (Map)cache.get("configuration/images");
				LOG.warn("images successfully loaded in Cluster Cache");
			}
		}
		return images;
	}

	public IAppUser login(String name, String password, DomainSession session) throws Exception
	{
		UserFactory userFactory = new UserFactory(session);
		if (!userFactory.hasUserProvider())
			throw new Exception("No User Provider available. Unable to perform login.");

		IUserProvider provider = userFactory.getUserProvider();
		IAppUser user = null;
		
		// Clear Password is ALWAYS sent to the provider
		// It is up to the provider to decrypt the password 
		// MM @ 2009/10/16
		
		user = provider.getAppUser(name, password);		
		if (user == null)
			throw new Exception("Login failed");
		
		user.setUserRealName(provider.getUserRealName(user));
		user.setClearPassword(password);
		user.setLoginTime(new DateTime());
		return user;
	}

	public IAppUser login(String st, DomainSession session) throws Exception
	{
		// JME: Assuming for now that the st String will be in the format username,password
		// until such time as we have a place where tokens are dispensed.
		String[] parts = st.split(",");
		if (parts.length < 2)
			throw new RuntimeException("Security Token was invalid");
		return login(parts[0], parts[1], session);
	}
	
	public IAppUser secureLogin(String name, String hashedPassword, String passwordSalt, DomainSession session) throws Exception
	{
		UserFactory userFactory = new UserFactory(session);
		if (!userFactory.hasUserProvider())
			throw new Exception("No User Provider available. Unable to perform login.");

		IUserProvider provider = userFactory.getUserProvider();
		IAppUser user = provider.getAppUser(name);
		if (user == null)
			throw new Exception("Login failed, invalid username and/or password.");
		
		user.setClearPassword(Configuration.decryptPasswordWithCloak(user.getEncodedPassword()));
		
		if(!hashedPassword.equals(super.getSHA1Hash(user.getClearPassword(), passwordSalt)))
			throw new Exception("Login failed, invalid username and/or password.");

		user.setUserRealName(provider.getUserRealName(user));		
		user.setLoginTime(new DateTime());
		return user;
	}

	public boolean isTabLayout()
	{
		return false;
	}

	public void newPassword(IAppUser user, String newPassword, DomainSession session)
	{
		UserFactory userFactory = new UserFactory(session);
		if (!userFactory.hasUserProvider())
			throw new DomainRuntimeException("No User Provider available. Unable to perform login.");

		try
		{
			IUserProvider provider = userFactory.getUserProvider();
			provider.changePassword(user, newPassword);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to update User password. " + e.getMessage(), e);
		}
	}

	public void lockAccount(IAppUser user, boolean value, DomainSession session)
	{
		UserFactory userFactory = new UserFactory(session);
		if (!userFactory.hasUserProvider())
			throw new DomainRuntimeException("No User Provider available. Unable to perform login.");

		try
		{
			IUserProvider provider = userFactory.getUserProvider();
			provider.lockAccount(user, value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to lock/unlock User account. " + e.getMessage(), e);
		}
	}

	
	public String getDbuse()
	{
		return ConfigFlag.DBNAME.getValue();
	}

	@SuppressWarnings("unchecked")
	private List getDomainClasses()
	{
		try
		{
			java.util.Map allclassmetadata = Registry.getInstance().getSessionFactory().getAllClassMetadata();
			java.util.Set keySet = allclassmetadata.keySet();
			List domainClasses = new java.util.ArrayList(keySet.size());
			domainClasses.addAll(keySet);
			return domainClasses;
		}
		catch (HibernateException e)
		{
			throw new DomainRuntimeException(e);
		}
	}

	public ClassConfig getClassConfig()
	{
		return classCfg;
	}

	public void reload() throws Exception
	{
		Session session = getSession();
		forms = null;
		getRegisteredForms();

		images = null;
		getRegisteredImages();

		classCfg = null;
		loadClassConfig(session);

		pathwayEntities=null;
		getPathwayEntities(session); // WDEV-17644

		session.close();
	}
	
	// WDEV-17644
	public List getPathwayEntities(Session session) 
	{
		if (pathwayEntities == null)
		{
			pathwayEntities = new ArrayList<PathwayEntityEvent>();

			Iterator it = session.createQuery("from PathwayEntityEvent").list().iterator();
			while (it.hasNext())
			{ 
				pathwayEntities.add((PathwayEntityEvent) it.next());
			}
			LOG.warn("Pathway Entity Events loaded successfully");
		}
		return pathwayEntities;
	}

	// WDEV-17644
	public List getPathwayEntities()
	{
		if (pathwayEntities == null)
		{
			try {
				return (getPathwayEntities(getSession()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pathwayEntities; 
	}
	
	public IAppUser getAppUser(String username, DomainSession session) throws Exception 
	{
		UserFactory userFactory = new UserFactory(session);
		if (!userFactory.hasUserProvider())
			throw new Exception("No User Provider available. Unable to perform login.");

		IUserProvider provider = userFactory.getUserProvider();
		IAppUser user = provider.getAppUser(username);
		if (user == null)
			throw new Exception("Login failed, invalid username and/or password.");
		
		return user;
	}
}
