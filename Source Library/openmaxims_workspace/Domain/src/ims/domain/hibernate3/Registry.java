package ims.domain.hibernate3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

import ims.configuration.AppServer;
import ims.configuration.ConfigFlag;
import ims.configuration.EnvironmentConfig;
import ims.configuration.delegates.EnvironmentConfigShowSQLValueChanged;
import ims.configuration.delegates.EnvironmentConfigHibernateUseSQLCommentsValueChanged;
import ims.domain.DbType;
import ims.domain.exceptions.DomainRuntimeException;

import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.RootClass;
import org.hibernate.util.NamingHelper;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Registry 
{
	private Configuration cfg;
	private SessionFactory sessionFactory;

	private static final class SingletonHolder
	{
		static final Registry Singleton = new Registry();
	}
	private Registry()
	{
		ConfigFlag.setValue(ConfigFlag.DBTYPE, DbType.UNKNOWN_DBTYPE.getName());
		try
		{
			cfg = new Configuration().configure();
			setAllHibernateConfigProperties();
			configureCacheUsage();				
			this.sessionFactory = cfg.buildSessionFactory();			
		}
		catch (HibernateException e)
		{
			throw new RuntimeException("Hibernate Exception occurred. " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		EnvironmentConfig.AddShowSQLDelegate(new EnvironmentConfigShowSQLValueChanged()
		{
			private static final long serialVersionUID = 1L;
			public void handle(boolean value) 
			{
				setShowSql(value);
			}
		});
		EnvironmentConfig.AddHibernateUseSQLCommentsDelegate(new EnvironmentConfigHibernateUseSQLCommentsValueChanged() 
		{
			private static final long serialVersionUID = 1L;
			public void handle(boolean value) 
			{
				setHibernateUseSQLComments(value);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void configureCacheUsage() throws Exception
	{
		if (cfg == null)
			return;

		HashMap cachedClasses = new HashMap();
		Properties props = new Properties();
		String jndiDatasourceName = getDatasourceJndiName(EnvironmentConfig.getMainDataSourceName());
		props.put(Environment.DATASOURCE, jndiDatasourceName);
		DataSource ds = (DataSource) NamingHelper.getInitialContext(props).lookup(jndiDatasourceName);
		Connection conn = ds.getConnection();
		/* TODO MSSQL case - sql = "select ac.class_name class_name, lku.text usage_text from appcache ac, applookup_instance lku where ac.is_collection = 0 and ac.cache_usage = lku.id"; */
		String sql = "SELECT ac.class_name class_name, lku.text usage_text FROM appcache ac, applookup_instance lku WHERE ac.is_collection = FALSE AND ac.cache_usage = lku.id";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			String className = rs.getString("class_name");
			String usage = rs.getString("usage_text");
			cachedClasses.put(className, usage);			
		}
		rs.close();
		ps.close();
		
		HashMap cachedCollections = new HashMap();

		/* TODO MSSQL case - select ac.class_name coll_role, lku.text usage_text from appcache ac, applookup_instance lku where ac.is_collection = 1 and ac.cache_usage = lku.id */
		sql = "SELECT ac.class_name coll_role, lku.text usage_text FROM appcache ac, applookup_instance lku WHERE ac.is_collection = TRUE AND ac.cache_usage = lku.id";

		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next())
		{
			String coll_role = rs.getString("coll_role");
			String usage = rs.getString("usage_text");
			cachedCollections.put(coll_role, usage);			
		}
		rs.close();
		ps.close();		
		conn.close();
		
		Iterator iter = cfg.getClassMappings();
		while (iter.hasNext())
		{
			PersistentClass pc = (PersistentClass)iter.next();
			if (pc instanceof RootClass)
			{
				RootClass rc = (RootClass)pc;
				String newStrategy = (String)cachedClasses.get(rc.getClassName());
				rc.setCacheConcurrencyStrategy(newStrategy);
			}
		}
		
		iter = cfg.getCollectionMappings();
		while (iter.hasNext())
		{
			Collection cm = (Collection)iter.next();
			String newStrategy = (String)cachedCollections.get(cm.getRole());
			cm.setCacheConcurrencyStrategy(newStrategy);
		}		
	}

	private void setAllHibernateConfigProperties()
	{
		setAppServerSpecificPropsInCfg();
		setDatasourceNameInCfg();			
		setCacheProviderInCfg();
		setShowSqlInCfg();			
		setFormatSqlInCfg();
		setHibernateStatisticsEnabledInCfg();
		setHibernateSqlCommentsEnabled();
	}

	private void setAppServerSpecificPropsInCfg()
	{
		AppServer appServer = AppServer.getAppServer(EnvironmentConfig.getApplicationServerType());
		if (!appServer.getJndiClassName().equals(""))
			cfg.getProperties().put(Environment.JNDI_CLASS, appServer.getJndiClassName());
		
		if (!appServer.getJndiUrl().equals(""))
			cfg.getProperties().put(Environment.JNDI_URL, appServer.getJndiUrl());
		
		if (!appServer.getTransFactoryClass().equals(""))
			cfg.getProperties().put(Environment.TRANSACTION_STRATEGY, appServer.getTransFactoryClass());
		
		if (!appServer.getTransMgrLookupClass().equals(""))
			cfg.getProperties().put(Environment.TRANSACTION_MANAGER_STRATEGY, appServer.getTransMgrLookupClass());
	}

	private void setFormatSqlInCfg()
	{
		cfg.getProperties().put(Environment.FORMAT_SQL, EnvironmentConfig.getFormatSQL());		
	}

	private void setShowSqlInCfg()
	{
		cfg.getProperties().put(Environment.SHOW_SQL, EnvironmentConfig.getShowSQL());		
	}
	private void setHibernateStatisticsEnabledInCfg()
	{
		cfg.getProperties().put(Environment.GENERATE_STATISTICS,""+EnvironmentConfig.getHibernateEnableStatistics());
	}
	private void setHibernateSqlCommentsEnabled()
	{
		cfg.getProperties().put(Environment.USE_SQL_COMMENTS, EnvironmentConfig.getHibernateUseSQLComments());
	}

	private void setDatasourceNameInCfg()
	{
		if (!EnvironmentConfig.getMainDataSourceName().equals(""))
		{
			cfg.getProperties().put(Environment.DATASOURCE, getDatasourceJndiName(EnvironmentConfig.getMainDataSourceName()));
			DbType dbType = this.findDbType(EnvironmentConfig.getMainDataSourceName());

			if (dbType.equals(DbType.UNKNOWN_DBTYPE))
				throw new DomainRuntimeException("Database type cannot be ascertained correctly for datasource " + EnvironmentConfig.getMainDataSourceName());

			cfg.getProperties().put(Environment.DIALECT, dbType.getDialectClass());
			ConfigFlag.setValue(ConfigFlag.DBTYPE, dbType.getName());
		}
	}

	private void setCacheProviderInCfg()
	{
		String cacheProvider = EnvironmentConfig.getCacheProvider();
		boolean useCache = false;
		String cacheProviderClassName = "";
		if (cacheProvider.equals("EHCACHE"))
		{
			cacheProviderClassName = "org.hibernate.cache.EhCacheProvider";
			useCache = true;
		}
		else if (cacheProvider.equals("TREECACHE"))
		{
			cacheProviderClassName = "org.hibernate.cache.TreeCacheProvider";
			useCache = true;
		}
		cfg.getProperties().put(Environment.CACHE_PROVIDER, cacheProviderClassName);
		cfg.getProperties().put(Environment.USE_SECOND_LEVEL_CACHE, "" + useCache);		
	}

	public static Registry getInstance()
	{
		return SingletonHolder.Singleton;
	}

	public SessionFactory getSessionFactory()
	{
		if (sessionFactory == null || sessionFactory.isClosed())
		{
			cfg = this.getConfiguration();
			this.sessionFactory = cfg.buildSessionFactory();
		}
		return this.sessionFactory;
	}
	
	public Configuration getConfiguration()
	{
		if (cfg == null)
		{
			cfg = new Configuration().configure();
			setAllHibernateConfigProperties();
			
		}
		return cfg;
	}
	
	public void setShowSql(boolean showSql)
	{
		getSessionFactory(); 
		if (sessionFactory instanceof SessionFactoryImplementor)
		{
			SessionFactoryImplementor impler = (SessionFactoryImplementor)sessionFactory;
			impler.getSettings().setShowSqlEnabled(showSql);	
		}
	}
	
	public void setHibernateUseSQLComments(boolean hibernateUseSQLComments)
	{
		getSessionFactory(); 
		if (sessionFactory instanceof SessionFactoryImplementor)
		{
			SessionFactoryImplementor impler = (SessionFactoryImplementor)sessionFactory;
			impler.getSettings().setCommentsEnabled(hibernateUseSQLComments);	
		}
	}
	
	public void setFormatSql(boolean formatSql)
	{
		getSessionFactory(); 
		if (sessionFactory instanceof SessionFactoryImplementor)
		{
			SessionFactoryImplementor impler = (SessionFactoryImplementor)sessionFactory;
			impler.getSettings().setFormatSqlEnabled(formatSql);	
		}
	}
	
	private DbType findDbType(String datasourceName)
	{
		String dbProduct = "";
		Connection conn = null;
		DbType ret = DbType.UNKNOWN_DBTYPE;
		try
		{
			Properties props = new Properties();
			String jndiDatasourceName = getDatasourceJndiName(datasourceName);
			props.put(Environment.DATASOURCE, jndiDatasourceName);
			DataSource ds = (DataSource) NamingHelper.getInitialContext(props).lookup(jndiDatasourceName);
			conn = ds.getConnection();
			dbProduct = conn.getMetaData().getDatabaseProductName();
			
			if (conn.getCatalog() != null && conn.getCatalog().length() > 0)
			{
				ConfigFlag.setValue(ConfigFlag.DBNAME, conn.getCatalog());
			}
			else if(conn.getMetaData().getUserName() != null && conn.getMetaData().getUserName().length() > 0)
			{
				ConfigFlag.setValue(ConfigFlag.DBNAME, conn.getMetaData().getUserName());
			}

			dbProduct = dbProduct.replaceAll(" ", "");
			
			if (dbProduct.toUpperCase().indexOf(DbType.SYBASE.getName().toUpperCase()) != -1)
			{
				ret = DbType.SYBASE;
			}
			else if (dbProduct.toUpperCase().indexOf(DbType.ORACLE.getName().toUpperCase()) != -1)
			{
				ret = DbType.ORACLE;
			}
			else if (dbProduct.toUpperCase().indexOf(DbType.SQLSERVER.getName().toUpperCase()) != -1)
			{
				ret = DbType.SQLSERVER;
			}
			else if (dbProduct.toUpperCase().indexOf(DbType.HSQL.getName().toUpperCase()) != -1)
			{
				ret = DbType.HSQL;
			}
			else if (dbProduct.toUpperCase().contains(DbType.POSTGRESQL.getName().toUpperCase())) {
			    ret = DbType.POSTGRESQL;
            }
			conn.close();
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException("Failed to get database type. " + e.getMessage(), e);
		}
		return ret;
	}
	
	private boolean setDbType(DbType dbType, String datasourceName)
	{
		if (dbType == null)
		{
			return false;
		}
		String dialect = (String)cfg.getProperties().get(Environment.DIALECT);
		if (dialect.equals(dbType.getDialectClass()))
			return false;
		
		cfg.getProperties().put(Environment.DIALECT, dbType.getDialectClass());
		cfg.getProperties().put(Environment.DATASOURCE, this.getDatasourceJndiName(datasourceName));
		setAppServerSpecificPropsInCfg();
		setCacheProviderInCfg();
		setShowSqlInCfg();			
		setFormatSqlInCfg();
		setHibernateStatisticsEnabledInCfg();
		
		closeSessionFactory();
		this.sessionFactory = cfg.buildSessionFactory();
		return true;
	}
	
	private String getDatasourceJndiName(String dsName)
	{
		AppServer appServer = AppServer.getAppServer(EnvironmentConfig.getApplicationServerType());
		return appServer.getJndiPrefix() + dsName;
	}
	
	public void setDatasourceName(String dataSourceName) 
	{
		if (dataSourceName == null || dataSourceName.equals(""))
		{
			return;
		}
		DbType dbType = this.findDbType(dataSourceName);
		if (dbType.equals(DbType.UNKNOWN_DBTYPE))
			throw new DomainRuntimeException("Database type cannot be ascertained correctly for datasource " + dataSourceName);
				
		boolean dialectChanged = setDbType(dbType, dataSourceName);
		if (!dialectChanged)
		{
			//Call this just to ensure all second level caches are cleared.
			getSessionFactory().close();
			getSessionFactory(); 
			if (sessionFactory instanceof SessionFactoryImplementor)
			{			
				String ds = getDatasourceJndiName(dataSourceName);
				SessionFactoryImplementor impler = (SessionFactoryImplementor)sessionFactory;
				Properties props = new Properties();
				props.put(Environment.DATASOURCE, ds);
				impler.getSettings().getConnectionProvider().configure(props);
			}
		}	
		ConfigFlag.setValue(ConfigFlag.DBTYPE, dbType.getName());
	}
	
	public void setCacheProvider()
	{
		closeSessionFactory();
		setCacheProviderInCfg();

		setAppServerSpecificPropsInCfg();
		setShowSqlInCfg();			
		setFormatSqlInCfg();
		setHibernateStatisticsEnabledInCfg();
	
		this.sessionFactory = cfg.buildSessionFactory();
	}
	
	public void closeSessionFactory()
	{
		if (sessionFactory != null)
		{
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}
