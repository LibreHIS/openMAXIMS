/*
 * Created on 07-Jan-04
 *
 */
package ims.domain;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.lookups.Lookup;
import ims.domain.lookups.LookupInstance;

/**
 * A DomainFactory is used by one DomainImpl object, each DomainImpl instance
 * has its own DomainFactory instance.
 * It manages the implementation details of access to the domain persistence layer.
 * 
 * Some Database specific error situations are tabled below.
 * These are used in deciding when to throw FK and Unique Key Violation Exceptions
 * 
 *		Error condition		Hibernate Exception thrown		SQL State	Error Code	
 * -------------------------------------------------------------------------------			
 *	SQLServer (IMS/DataDirect JDBC Driver)
 *		FK Violation		ConstrainViolationException		23000		547
 *		Unique Violation	ConstrainViolationException		23000		2627
 *
 *	Oracle (IMS/DataDirect JDBC Driver)
 *		FK Violation		GenericJDBCException			HY000		2292
 *		Unique Violation	ConstrainViolationException		23000		1
 *		
 *	Oracle (Oracle JDBC Driver)
 *		FK Violation		ConstrainViolationException		23000		2292
 *		Unique Violation	ConstrainViolationException		23000		1
 *
 *	Sybase (Sybase JDBC Driver)
 *		FK Violation		ConstrainViolationException		23000		547
 *		Unique Violation	ConstrainViolationException		23000		2601
 *
 *
 * @author gcoghlan
 *
 */
public interface DomainFactory extends ILightweightDomainFactory
{	
	public static final int ORACLE_FK_VIOLATION_ERROR=2292;
	public static final int SYBASE_FK_VIOLATION_ERROR=547;
	public static final int SQLSERVER_FK_VIOLATION_ERROR=547;
	
	public static final int ORACLE_UNQ_VIOLATION_ERROR=1;
	public static final int SYBASE_UNQ_VIOLATION_ERROR=2601;
	public static final int SQLSERVER_UNQ_VIOLATION_ERROR=2627;
	
	public static final boolean RESULTSET_CAPPED = true; //Turns on/off the governing of result set sizes
//FWD-163 	public static final int RESULTSET_TOPMAX = 1000;
//FWD-163 	public static final int RESULTSET_DEFMAX = 200;
	public static final String OVERFLOW_MESSAGE = "More than $NUM$ records returned. Please refine your search criteria";
	public enum JOINMODE{INNER, LEFT, RIGHT};
	public enum ORDERMODE{ASC,DESC};
	
	
	
		
	/**
	 * Boolean, TYPE_BOOLEAN = 0
	 */
	public static final int TYPE_BOOLEAN = 0;
	/**
	 * Date, TYPE_DATE = 1
	 */
	public static final int TYPE_DATE = 1;
	/**
	 * DateTime, TYPE_DATETIME = 2
	 */
	public static final int TYPE_DATETIME = 2;
	/**
	 * Decimal, TYPE_DECIMAL = 3
	 */
	public static final int TYPE_DECIMAL = 3;
	/**
	 * Integer, TYPE_INTEGER = 4
	 */
	public static final int TYPE_INTEGER = 4; 
	/**
	 * String, TYPE_STRING = 5
	 */
	public static final int TYPE_STRING = 5;
	/**
	 * Time, TYPE_TIME = 6
	 */
	public static final int TYPE_TIME = 6;
	
	/**
	 * Get a Lookup instance by it's internal generated unique id
	 * @param instanceId
	 * @return
	 * @throws DomainRuntimeException
	 */
	public LookupInstance getLookupInstance(int instanceId);

	/**
	 * Get the particular instance in a Lookup set
	 * @param id - identifies the lookup set
	 * @param instanceId - identify the instance in the set
	 * @throws DomainRuntimeException
	 */
	public LookupInstance getLookupInstance(int id, int instanceId);
	
	/**
	 * Get the particular instance that corresponds (maps to) the given
	 * externalSystem name and code.
	 * The external name && code must match at most one LookupInstance
	 * in the selected Lookup type.
	 * If more than one LookupInstance is matched in that type a 
	 * DomainRuntimeException is raised.
	 * If no LookupInstance is matched, then null is returned.
	 * @param type
	 * @param externalSystem
	 * @param externalCode
	 * @return null or the only LookupInstance in the Lookup type that matches
	 * the provide externalSystem & externalCode values
	 * @throws DomainRuntimeException if more than one LookupInstance in the Lookup type
	 * matches the externalSystem && externalCode values.
	 */
	public LookupInstance getLookupInstance(Lookup type, String externalSystem, String externalCode);
	
	/**
	 * Get the particular instance that corresponds (maps to) the given
	 * externalSystem name and integer code.
	 * @see #getLookupInstance(Lookup, String, String)
	 * @param typeId
	 * @param externalSystem
	 * @param externalCode
	 */
	public LookupInstance getLookupInstance(int typeId, String externalSystem, String externalCode);
	
	/**
	 * Get the Lookup for a given id or null if no lookup for that
	 * id exists.
	 * @param id
	 * @throws DomainRuntimeException if problem occured retrieving from persistence#
	 * layer.
	 */
	public Lookup getLookup(int id);
	
	/**
	 * @see #getDomainObject(Class, Integer)	 
	 * @param domainClass
	 * @param id
	 * @throws DomainRuntimeException
	 */
	public DomainObject getDomainObject(Class domainClass, int id);
	
	/**
	 * Get the DomainObject of the specified class that has the corresponding id.
	 * Returns null if the object with that id does not exist.
	 * @param domainClass
	 * @param id
	 * @return null or DomainObject
	 * @throws DomainRuntimeException
	 */
	public DomainObject getDomainObject(Class domainClass, Integer id);

	public DomainObject getDomainObject(IDomainGetter getter);
	public List<DomainObject> getDomainObjects(IDomainCollectionGetter getter);
	
	/**
	 * Get the DomainObject of the specified class that has the corresponding imported id.
	 * Returns null if the object with that id does not exist.
	 * The ImportedObjects table mapping table is checked to see if there is a local id mapped to this supplied external id
	 * @param domainClass
	 * @param id
	 * @return
	 */
	public DomainObject getImportedDomainObject(Class domainClass, String externalSource, Integer externalId);
	public DomainObject getImportedDomainObject(Class domainClass, String externalSource, int externalId);

	public void saveImport(DomainObject obj, String externalSource, int externalId) throws StaleObjectException;

	/**
	 * Get a list of domain objects of a given Class that have the match the set of properties.
	 * @param domainClass
	 * @param properties
	 * @throws DomainException
	 */	 
	public List listDomainObjects(Class domainClass, Properties properties);
	
	/** 
	 * Get a list of domain objects of the given Class based on the criteria
	 * in the queryString.
	 * This query will allow usage of HQL (Hibernate Query Language).
	 * A queryString is formed like
	 * 'from [domainClass] '+queryString
	 * @see #find(String) 
	 * @param domainClass
	 * @param queryString
	 */
	public List listDomainObjects(Class domainClass, String queryString);
	
	/**
	 * Get a list of domain objects of the given Class based on the criteria
	 * in the queryString.
	 * Perform a query that uses '?' parameter placeholder.
	 * This is replaced with value of the type corresponding
	 * to the typeCode.
	 * @see #find(String, Object, int)
	 * @param domainClass
	 * @param queryString
	 * @param value
	 * @param type
	 */
	public List listDomainObjects(Class domainClass, String queryString, Object value, int type);
	
	/**
	 * Get a list of domain objects of the given Class based on the criteria
	 * in the queryString.
	 * Perform a query that multiple '?' parameter placeholders.
	 * This is replaced with corresponding value in the array values
	 * of the type corresponding in the typeCodes array.
	 * @see #find(String, Object[], int[])
	 * @param domainClass
	 * @param queryString
	 * @param values
	 * @param types
	 */
	public List listDomainObjects(Class domainClass, String queryString, Object[] values, int[] types);
	
	/**
	 * Return a list of domain objects of specified type with no filter applied
	 * @param domainClass
	 */
	public List listDomainObjects(Class domainClass);
	
	
	/**
	 * Gets a list of objects based on the HQL string
	 * This should only be used for simple unparameterized queries.
	 * @param hqlString - HQL (Hibernate Query Language).
	 */
	public List find(String hqlString);
	public DomainObject findFirst(String hqlString);
	
	/**
	 * Gets a list of objects based on the Criteria object.
	 * @param crit - Criteria Object.
	 */
	
	public List find(String hqlString, int maxRec);
	
	/**
	 * Perform a query that uses parameter placeholder.
	 * The parameter placeholder is '?'.
	 * This is replaced with value of the type corresponding
	 * to the typeCode.
	 * @param query
	 * @param value
	 * @param typeCode
	 */
	public List find(String query, Object value, int typeCode);
	public List find(String query, Object value, int typeCode, int maxRec);
	

	/**
	 * Perform a query that multiple parameter placeholders.
	 * The parameter placeholder is '?'.
	 * These are replaced with corresponding value in the array values
	 * of the type corresponding in the typeCodes array.
	 * @param query
	 * @param values
	 * @param typeCodes
	 */
	public List find(final String query, final Object[] values, final int[] typeCodes);
	public List find(final String query, final Object[] values, final int[] typeCodes, int maxRec);
	public DomainObject findFirst(final String query, final Object[] values, final int[] typeCodes);
	

	/**
	 * Perform a query that uses named parameter placeholder.
	 * This is replaced with value of the type corresponding
	 * to the typeClass.
	 * @param query
	 * @param value
	 * @param typeClass
	 */
	public List find(String query, String paramName, Object paramValue);
	public List find(String query, String paramName, Object paramValue, int maxRec);
	public DomainObject findFirst(String query, String paramName, Object paramValue);

	/**
	 * Perform a query that uses named parameter placeholder.
	 * This is replaced with value of the type corresponding
	 * to the typeClass.
	 * @param query
	 * @param value
	 * @param typeClass
	 */
	public List find(String query, LookupInstance paramValue);
	public List find(String query, LookupInstance paramValue, int maxRec);

	/**
	 * Perform a query with multiple named parameter placeholders.
	 * These are replaced with corresponding value in the array values
	 * of the type corresponding class in the typeClasses array.
	 * @param query
	 * @param values
	 * @param typeClasses
	 */
	public List find(final String query, final String[] paramNames, final Object[] paramValues);
	public List find(final String query, final String[] paramNames, final Object[] paramValues, int maxRec);
	public DomainObject findFirst(final String query, final String[] paramNames, final Object[] paramValues);
	
	/**
	 * Perform a query with multiple named parameter placeholders.
	 * These are replaced with corresponding value in the array values
	 * of the type corresponding class in the typeClasses array.
	 * @param query
	 * @param values
	 * @param typeClasses
	 */
	public List find(final String query, final ArrayList paramNames, final ArrayList paramValues);
	public List find(final String query, final ArrayList paramNames, final ArrayList paramValues, int maxRec);
	public DomainObject findFirst(final String query, final ArrayList paramNames, final ArrayList paramValues);
	
	/**
	 * Performs a query based on the collection passed in, filtering the collection based on the filterString, 
	 * and setting all the named parameter placeholders with the Object values provided. 
	 * @param collection
	 * @param filterString
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	public List find(final Set collection, final String filterString, final String[] paramNames, final Object[] paramValues);
	public List find(final Set collection, final String filterString, final String[] paramNames, final Object[] paramValues, int maxRec);
	public DomainObject findFirst(final Set collection, final String filterString, final String[] paramNames, final Object[] paramValues);

	/**
	 * Performs a query based on the collection passed in, filtering the collection based on the filterString, 
	 * and setting all the named parameter placeholders with the Object values provided. 
	 * @param collection
	 * @param filterString
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	public List find(final Set collection, final String filterString, final ArrayList paramNames, final ArrayList paramValues);
	public List find(final Set collection, final String filterString, final ArrayList paramNames, final ArrayList paramValues, int maxRec);
	public DomainObject findFirst(final Set collection, final String filterString, final ArrayList paramNames, final ArrayList paramValues);
	/**
	 * Performs a query based on the collection passed in, filtering the collection based on the filterString, 
	 * @param collection
	 * @param filterString
	 * @return
	 */
	public List find(final Set collection, final String filterString);
	public List find(final Set collection, final String filterString, int maxRec);
	public DomainObject findFirst(final Set collection, final String filterString);

	/**
	 * Saves the DomainObject to the persistence layer.
	 * This can be used for transient or persistent DomainObjects.
	 * @param obj
	 * @throws DomainException
	 */
	public void save(DomainObject obj) throws StaleObjectException;
	
	/**
	 * wdev-10417
	 * Hibernate will save this object, but will not check session for any other
	 * objects that need to be saved.  Must be used with care.
	 * @param domainObject
	 * @throws StaleObjectException
	 */
	public void saveNoFlush(DomainObject domainObject) throws StaleObjectException;
		
	/**
	 * Saves the Lookup type in the persistence layer.
	 * @param lookupType
	 */
	public void save(ims.domain.lookups.Lookup lookupType) throws StaleObjectException;
	
	/**
	 * Saves the Lookup type in the persistence layer.
	 * @param lookupType
	 */
	public void save(ims.domain.lookups.LookupInstance instance) throws StaleObjectException;

	/**
	 * Re-reads the DomainObject from the database.
	 * This updates the copy of the DomainObject held in the session cache.
	 * @param obj
	 * @throws DomainException
	 */
	public void refresh(DomainObject obj);
	
	/**
	 * Does the same as refresh but for a Lookup class instead
	 * @param lookup
	 * @throws DomainException
	 */
	public void refresh(Lookup lookup);
	
	public DomainObject loadDomainObject(Class domainClass, int id);
	
	/**
	 * Updates a DomainObject that already exists in the peristence layer.
	 * This can only be used for persistent DomainObjects.
	 * @param obj
	 * @throws DomainException
	 */
	public void update(DomainObject obj);
	
	/**
	 * Deletes the DomainObject from the persistence layer.
	 * @param obj
	 * @throws DomainRuntimeException
	 */
	public void delete(DomainObject obj) throws ForeignKeyViolationException;
	

	/**
	 * Deletes the Lookup from the persistence layer.
	 * @param lookupType
	 * @throws DomainRuntimeException
	 */
	public void delete(Lookup lookupType) throws ForeignKeyViolationException;
	
	/**
	 * Deletes the Objects matching the Query
	 * @param queryString
	 * @throws DomainRuntimeException
	 */
	public int delete(String queryString) throws ForeignKeyViolationException;

	/**
	 * Called when factory is first created for a DomainImpl 
	 * and then each time the context for that DomainImpl is updated.
	 * @param domainSession
	 */
	public void setDomainSession(DomainSession domainSession);
	
	public DomainSession getDomainSession();

	/**
	 * Get a List of the of Domain Classes as java.lang.Class objects
	 */
	public java.util.List getDomainClasses();
	
	/**
	 * Creates a Transaction object. This starts a database transaction in the session.
	 * @return The started Transaction 
	 */
	public Transaction beginTransaction();
	public Transaction getTransaction();
	public void setTransaction(Transaction trans);
		
	public void initialize(DomainObject obj);
	
	public void discardSession();
	public boolean isSessionDiscarded();
	
	public Connection getJdbcConnection();
	
	public void close();
	
	public void setShowSql(boolean showSql);
	public void setHibernateStatisticsEnabled (boolean value);
	public void setFormatSql(boolean formatSql);
	public void setDatasource(String  datasourceName);
	
	/**
	 * @see markAsRie(Class domainClass, int id, ims.framework.FormName form, Integer patId, Integer contactId, String comment) 	 
	 * @param domainClass
	 * @param id
	 * @param form
	 * @param patId
	 * @param contactId
	 * @param comment
	 * @throws DomainRuntimeException
	 */
	public void markAsRie(Class domainClass, int id, ims.framework.FormName form, Integer patId, Integer contactId, Integer careContextId, String comment) throws ims.domain.exceptions.StaleObjectException;
	
	/**
	 * 
	 * @param hql must be a count(x) type query
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	public long countWithHQL(String hql, String[] paramNames, Object[] paramValues);
	
	/**
	 * Returns a count of the records returned from the query in hql
	 * @param hql
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	
	 
	public int count(String hql, final String[] paramNames, final Object[] paramValues);
	
	/**
	 * Validates that the hql executes. No exceptions are handled at all
	 * @param hql
 	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	public void validateFind(final String hql,final String[] paramNames,final Object[] paramValues);
	
	public void validateFindSQL(final String sql, final String[] paramNames, final Object[] paramValues, int maxRec);
	

	public boolean doDirtyCheck();
	
	public void setDirtyCheck(boolean checkDirty);
	
	public ArrayList getDirtyProperties();
	
	public ArrayList getDirtyEntities();
	
	public boolean isDirty(Object o);
	
	public void resetDirtyProperties();
	
	public void evict(DomainObject o); // FWD-178
}
