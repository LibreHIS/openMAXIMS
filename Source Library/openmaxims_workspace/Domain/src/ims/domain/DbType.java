package ims.domain;

public class DbType
{
	public static final DbType ORACLE = new DbType("Oracle", "org.hibernate.dialect.Oracle9Dialect");
	public static final DbType SYBASE = new DbType("Sybase","org.hibernate.dialect.SybaseDialect");
	public static final DbType SQLSERVER = new DbType("SQLServer","org.hibernate.dialect.SQLServerDialect");
	public static final DbType HSQL = new DbType("HSQL","org.hibernate.dialect.HSQLDialect");
    public static final DbType POSTGRESQL = new DbType("PostgreSQL", "org.hibernate.dialect.PostgreSQLDialect");
	public static final DbType UNKNOWN_DBTYPE = new DbType("UNKNOWN_DBTYPE","");

	private String name;
	private String dialectClass;

	public DbType(String name, String dialect)
	{
		this.name = name;
		this.dialectClass = dialect;
	}

	public String getDialectClass()
	{
		return dialectClass;
	}

	public String getName()
	{
		return name;
	}
	
	public boolean equals (Object o)
	{
		if (o == null) 
			return false;
		if (!(o instanceof DbType))
			return false;
		DbType dbt = (DbType)o;
		return dbt.getName().equals(this.getName());
	}
	
	public int hashCode()
	{
		return this.getName().hashCode();
	}
	
	public static DbType getDbType(String name)
	{
		if (name.equals("Oracle"))
			return ORACLE;
		else if (name.equals("Sybase"))
			return SYBASE;
		else if (name.equals("SQLServer"))
			return SQLSERVER;
		else if (name.equals("HSQL"))
			return HSQL;
		else if ("PosgreSQL".equals(name))
			return POSTGRESQL;
		else if (name.equals("UNKNOWN_DBTYPE"))
			return UNKNOWN_DBTYPE;
		else return null;
	}

}
