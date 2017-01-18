package ims.domain.hibernate3;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import ims.domain.DomainFactory.ORDERMODE;
import ims.domain.DomainFactory.JOINMODE;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.type.Type;

public class IMSCriteria {

	private Class boClass;
	private boolean conjunction, disjunction=false;
	private List <String> objects;
	private List <ims.domain.DomainFactory.JOINMODE> joinModes;
	private Criteria crit;
	private Criteria auxCrit;
	private ArrayList<Criterion> criterions = new ArrayList <Criterion>();
	private ArrayList<String> joins=new ArrayList<String>();
	private ims.domain.hibernate3.DomainFactory fac;
	 
	public IMSCriteria(Class boClass, ims.domain.DomainFactory factory)
	{
		fac=(ims.domain.hibernate3.DomainFactory)factory;
		this.boClass=boClass;
		this.setCriteria(fac.getSession(true));
	}
	public IMSCriteria(Class boClass, List <String> objects, ims.domain.DomainFactory factory)
	{
		fac=(ims.domain.hibernate3.DomainFactory)factory;
		this.boClass=boClass;
		this.objects=objects;
		this.setCriteria(fac.getSession(true));
	}
	public IMSCriteria(Class boClass, List <String> objects, List <ims.domain.DomainFactory.JOINMODE> joinModes, ims.domain.DomainFactory factory)
	{
		fac=(ims.domain.hibernate3.DomainFactory)factory;
		this.boClass=boClass;
		this.objects=objects;
		this.joinModes=joinModes;
		this.setCriteria(fac.getSession(true));
	}
	/**
	 * Get the objects that are going to be joined. 
	 * @return List
	 */
	public List getObjects()
	{
		return this.objects;
	}
	/**
	 * Set the BO you want to join
	 * @param objects
	 */
	public void setObjects(List <String> objects)
	{
		this.objects=objects;
	}
	/**
	 * Get the JOINMODE List
	 * @return List 
	 */
	public List <ims.domain.DomainFactory.JOINMODE> getjoinModes()
	{
		return this.getjoinModes();
	}
	/**
	 * Set the JoinModes
	 * @param joinModes
	 */
	public void setjoinModes(List <ims.domain.DomainFactory.JOINMODE> joinModes)
	{
		this.joinModes=joinModes;
	}
	public boolean getConjunction()
	{
		return this.conjunction;
	}
	public void setConjunction(boolean conjunction)
	{
		this.conjunction=conjunction;
		if (!conjunction)
		this.criterions=null;
	}
	public boolean getDisjunction()
	{
		return this.disjunction;
	}
	public void setDisjunction(boolean disjunction)
	{
		this.disjunction=disjunction;
		if (!disjunction)
			this.criterions=null;
		
	}
	private void setCriteria(Session factory)
	{
		Session session=factory;
	
		this.crit=session.createCriteria(this.boClass);
		
		if(this.objects!=null && this.joinModes==null)
		{
			String object="";
			String aliass="";
			String secondRoot="";
			String firstToken="";
			
			int length=0;
			for (int i=0;i<objects.size();i++)
			{	
				if (object.equals(""))
				{
					object=objects.get(i);
				}
				else if (objects.get(i).substring(0, 1).equals("+"))
				{
					object=objects.get(i);
				}
				else if (!secondRoot.equals(""))
				{
					object=secondRoot.concat("." + objects.get(i));
				}
				else
					object=aliass.concat("." + objects.get(i));
				
				if (object.substring(0,1).equals("+"))
				{
					length=objects.get(i).length();
					StringTokenizer st=new StringTokenizer(objects.get(i).substring(1, length),".");
					firstToken=st.nextToken();
					if (firstToken.equals("this"))
						secondRoot=st.nextToken();
					else
					{
						secondRoot=firstToken;
					}
					
				}
				else
				{
					aliass=objects.get(i);
					secondRoot="";
				}
				
				if (object.substring(0, 1).equals("+"))
				{
					object=object.substring(1,object.length());
					object="this."+object;
				}
				if (!secondRoot.equals(""))
					crit.createAlias(object,secondRoot);
				else
				{
					crit.createAlias(object, aliass);
					secondRoot=firstToken;
				}
				
			}
		}
		else if(this.objects!=null && this.joinModes!=null)
		{
			String object="";
			String aliass="";
			int join=0;
		
		
			for (int i=0;i<objects.size();i++)
			{	
				if (object.equals(""))
				{
					object=objects.get(i);
				}
				else
				{
					object=aliass.concat("." + objects.get(i));
				}
				aliass=objects.get(i);
				
				
				if (joinModes.get(i).equals(JOINMODE.INNER))
					join=0;
				else if (joinModes.get(i).equals(JOINMODE.LEFT))
					join=1;
				else if (joinModes.get(i).equals(JOINMODE.RIGHT))
					join=4;
				
				crit.createAlias(object, aliass, join);
			}
			
		}
		
		auxCrit=this.copy(crit);
		
	}
	public void join(String path,String alias)
	{
		crit.createAlias(path, alias);	
	}
	////////////////////////////////////////////////////////////////////////////
	///////////////////////////////RESTRICTIONS/////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/**
	 * Apply a "between" constraint to the named property.
	 * @param propertyNameValues
	 */
	public void between(String propertyName, Object lo, Object hi)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.between(propertyName, lo, hi));
		else
			auxCrit.add(Restrictions.between(propertyName, lo, hi));
	}
	/**
	 * Apply an "equals" constraint to each property in the key set of a Map
	 * @param propertyNameValues
	 */
	public void allEqual(Map propertyNameValues)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.allEq(propertyNameValues));
		else 
			auxCrit.add(Restrictions.allEq(propertyNameValues));
	}
	/**
	 * Apply an "equal" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void equal(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			//criterions.add(Restrictions.eq(propertyName, value));
			criterions.add(Restrictions.eq(propertyName, value));
		
		else
			auxCrit.add(Restrictions.eq(propertyName, value));
	}
	/**
	 * Apply an "equal" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void equalProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.eqProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.eqProperty(propertyName, otherPropertyName));

	}
	/**
	 * Apply a "greater than or equal" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void greaterOrEqualThan(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.ge(propertyName, value));
		else
			auxCrit.add(Restrictions.ge(propertyName, value));
	}
	/**
	 * Apply a "greater than or equal" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void greaterOrEqualThanProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.geProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.geProperty(propertyName, otherPropertyName));
	}
	/**
	 * Apply a "greater than" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void greaterThan(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.gt(propertyName, value));
		else
			auxCrit.add(Restrictions.gt(propertyName, value));
	}
	/**
	 * Apply a "greater than" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void greaterThanProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.gtProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.gtProperty(propertyName, otherPropertyName));
	}	
	/**
	 * Apply an "equal" constraint to the identifier property
	 * @param value
	 */
	public void idEqual(Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.idEq(value));
		else
			auxCrit.add(Restrictions.idEq(value));
	}
	/**
	 * A case-insensitive "like", similar to Postgres ilike operator
	 * @param propertyName
	 * @param value
	 */
	public void ilike(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.ilike(propertyName, value));
		else
			auxCrit.add(Restrictions.ilike(propertyName, value));
	}
	/**
	 * Apply an "in" constraint to the named property
	 * @param propertyName
	 * @param values
	 */
	public void in(String propertyName, Collection values)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.in(propertyName, values));
		else
			auxCrit.add(Restrictions.in(propertyName, values));
	}
	/**
	 * Apply an "in" constraint to the named property
	 * @param propertyName
	 * @param values
	 */
	public void in(String propertyName, Object[] values)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.in(propertyName, values));
		else
			auxCrit.add(Restrictions.in(propertyName, values));
	}
	/**
	 * Constrain a collection valued property to be empty
	 * @param propertyName
	 */
	public void isEmpty(String propertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.isEmpty(propertyName));
		else
			auxCrit.add(Restrictions.isEmpty(propertyName));
	}
	/**
	 * Constrain a collection valued property to be non-empty
	 * @param propertyName
	 */
	public void isNotEmpty(String propertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.isNotEmpty(propertyName));
		else
			auxCrit.add(Restrictions.isNotEmpty(propertyName));
	}
	/**
	 * Apply an "is not null" constraint to the named property
	 * @param propertyName
	 */
	public void isNotNull(String propertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.isNotNull(propertyName));
		else
			crit.add(Restrictions.isNotNull(propertyName));
	}
	/**
	 * Apply an "is null" constraint to the named property
	 * @param propertyName
	 */
	public  void isNull(String propertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.isNull(propertyName));
		else
			auxCrit.add(Restrictions.isNull(propertyName));
	}
	/**
	 * Apply a "less than or equal" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void lessOrEqualThan(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.le(propertyName, value));
		else
			auxCrit.add(Restrictions.le(propertyName, value));
	}
	/**
	 * Apply a "less than or equal" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void lessOrEquealsThanProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.leProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.leProperty(propertyName, otherPropertyName));
	}
	/**
	 * Apply a "like" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void like(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.like(propertyName, value));
		else
			auxCrit.add(Restrictions.like(propertyName, value));
		
		
	}
	/**
	 * Apply a "less than" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void lessThan(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.lt(propertyName, value));
		else
			auxCrit.add(Restrictions.lt(propertyName, value));
	}
	/**
	 * Apply a "less than" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void lessThanProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.ltProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.ltProperty(propertyName, otherPropertyName));
	}
	/**
	 * Apply a "not equal" constraint to the named property
	 * @param propertyName
	 * @param value
	 */
	public void notEqual(String propertyName, Object value)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.ne(propertyName, value));
		
		else
		{
			auxCrit.add(Restrictions.ne(propertyName, value));
			
		}
	}
	/**
	 * Apply a "not equal" constraint to two properties
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public void notEqualProperty(String propertyName, String otherPropertyName)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.neProperty(propertyName, otherPropertyName));
		else
			auxCrit.add(Restrictions.neProperty(propertyName, otherPropertyName));
	}
	/**
	 * Apply an "not in" constraint to the named property
	 * @param propertyName
	 * @param values
	 */
	public void notIn(String propertyName, Collection values)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.not(Restrictions.in(propertyName, values)));
		else
			auxCrit.add(Restrictions.not(Restrictions.in(propertyName, values)));
	}
	/**
	 * Apply an "not in" constraint to the named property
	 * @param propertyName
	 * @param values
	 */
	public void notIn(String propertyName, Object [] values)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.not(Restrictions.in(propertyName, values)));
		else
			auxCrit.add(Restrictions.not(Restrictions.in(propertyName, values)));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeEqual(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeEq(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeEq(propertyName, size));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeGreaterOrEqualThan(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeGe(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeGe(propertyName, size));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeGreaterThan(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeGt(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeGt(propertyName, size));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeLessOrEqualThan(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeLe(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeLe(propertyName, size));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeLessThan(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeLt(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeLt(propertyName, size));
	}
	/**
	 * Constrain a collection valued property by size
	 * @param propertyName
	 * @param size
	 */
	public void sizeNotEqual(String propertyName, int size)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sizeNe(propertyName, size));
		else
			auxCrit.add(Restrictions.sizeNe(propertyName, size));
	}
	/**
	 * Apply a constraint expressed in SQL
	 * @param sql
	 */
	public void sqlRestriction(String sql)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sqlRestriction(sql));
		else
			auxCrit.add(Restrictions.sqlRestriction(sql));
	}
	/**
	 * Apply a constraint expressed in SQL, with the given JDBC parameters
	 * @param sql
	 * @param values
	 * @param types
	 */
	public void sqlRestriction(String sql, Object[] values, Type[] types)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sqlRestriction(sql, values, types));
		else
			auxCrit.add(Restrictions.sqlRestriction(sql, values, types));
	}
	/**
	 * Apply a constraint expressed in SQL, with the given JDBC parameter
	 * @param sql
	 * @param value
	 * @param type
	 */
	public void sqlRestriction(String sql, Object value, Type type)
	{
		if (!conjunction && !disjunction)
			criterions.add(Restrictions.sqlRestriction(sql, value, type));
		else
			auxCrit.add(Restrictions.sqlRestriction(sql, value, type));
		
	}
	/**
	 * Add an ordering to the result
	 * @param order
	 */
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	public void addOrder(String property,ORDERMODE order)
	{
		if (!conjunction && !disjunction)
		{
			if (order.equals(ORDERMODE.ASC))
				crit.addOrder(Order.asc(property));
			else if (order.equals(ORDERMODE.DESC))
				crit.addOrder(Order.desc(property));
		}
		else
		{
			if (order.equals(ORDERMODE.ASC))
				auxCrit.addOrder(Order.asc(property));
			else if (order.equals(ORDERMODE.DESC))
				auxCrit.addOrder(Order.desc(property));
		}
	}
	/**
	 * Set a limit upon the number of objects to be retrieved.
	 * @param maxResults
	 */
	public void setMaxResults(int maxResults)
	{
		if (!conjunction && !disjunction)
			crit.setMaxResults(maxResults);
		else
			auxCrit.setMaxResults(maxResults);
	}
	////////////////////////////////////////////////////////////////////////////
	///////////////////////////////PROJECTIONS//////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/**
	 * A property average value
	 * @param propertyName
	 */
	public void setProjectionAvg(String propertyName)
	{
		crit.setProjection(Projections.avg(propertyName));
	}
	/**
	 * A property value count
	 * @param propertyName
	 */
	public void setProjectionCount(String propertyName)
	{
		crit.setProjection(Projections.count(propertyName));
	}
	/**
	 * A distinct property value count
	 * @param propertyName
	 */
	public void setProjectionCountDistinct(String propertyName)
	{
		crit.setProjection(Projections.countDistinct(propertyName));
	}
	/**
	 * A property maximum value
	 * @param propertyName
	 */
	public void setProjectionMax(String propertyName)
	{
		crit.setProjection(Projections.max(propertyName));
	}
	/**
	 * A property minimum value
	 * @param propertyName
	 */
	public void setProjectionMin(String propertyName)
	{
		crit.setProjection(Projections.min(propertyName));
	}
	/**
	 * The query row count, ie.
	 *
	 */
	public void setProjectionRowCount()
	{
		crit.setProjection(Projections.rowCount());
	}
	/**
	 * A property value sum
	 * @param propertyName
	 */
	public void setProjectionSum(String propertyName)
	{
		crit.setProjection(Projections.sum(propertyName));
	}
	/**
	 * Set a timeout for the underlying JDBC query
	 * @param timeout
	 */
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	public void setTimeOut(int timeout)
	{
		crit.setTimeout(timeout);
	}
	/**
	 * Convenience method to return a single instance that matches
	 * the query, or null if the query returns no results.
	 *
	 */
	public void uniqueResult()
	{
		crit.uniqueResult();
	}
	/**
	 * Execute the Criteria. 
	 * @return List of the Criteria Selection result.
	 */
	public List find()
	{
		String object="";
		//String bo="";
		String father2="";
		//String father="";
		//String son="";
		boolean continues=true;
		String son2="";
		//String path2="";
		String rootObject="";
		String criton="";
		int parameter=0;
		String parameter2="";
		
		try
		{
			for (int i=0;i<criterions.size();i++)
			{
				crit.add(criterions.get(i));
			}
			for (int j=0;j<joins.size();j++)
			{
				this.join(joins.get(j), joins.get(j+1));
				j++;
			}
			return crit.list();
		}
		catch (org.hibernate.QueryException e)
		{
			this.setCriteria(fac.getSession(true));
			StringTokenizer st=new StringTokenizer(e.getMessage()," ");
			
			for (int i=0;i<5;i++)
			{
				object=st.nextToken();
			}
			StringTokenizer st2=new StringTokenizer(object,".");
			while (st2.hasMoreTokens())
			{
				rootObject=st2.nextToken();
				break;
			}
			//Hasta aqu� hemos conseguido el hijo
			for (int i=0;i<criterions.size();i++)
			{
				if (continues)
					{
					Criterion crito=criterions.get(i);
					criton=crito.toString();
					StringTokenizer stro=new StringTokenizer(criton,".");
					while (stro.hasMoreTokens())
					{
						son2=stro.nextToken();
						
						if (son2.equals(rootObject))
						{
							StringTokenizer fatherCalculator=new StringTokenizer(father2," ");
							while (fatherCalculator.hasMoreTokens())
							{
								//path2=fatherCalculator.nextToken();
								continues=false;
							}
							
							StringTokenizer equals=new StringTokenizer(criton,"=");
							if (equals.countTokens()>1)
							{
								while (equals.hasMoreTokens())
								{
									parameter2=equals.nextToken();
									
								}
								parameter=Integer.parseInt(parameter2);
								this.equal(object, parameter);
								criterions.remove(i);
							}
							StringTokenizer lessThan=new StringTokenizer(criton,"<");
							if (equals.countTokens()>1)
							{
								while (lessThan.hasMoreTokens())
								{
									parameter2=equals.nextToken();
									
								}
								parameter=Integer.parseInt(parameter2);
								this.lessThan(object, parameter);
								criterions.remove(i);
							}
							StringTokenizer greaterThan=new StringTokenizer(criton,">");
							if (equals.countTokens()>1)
							{
								while (greaterThan.hasMoreTokens())
								{
									parameter2=equals.nextToken();
									
								}
								parameter=Integer.parseInt(parameter2);
								this.greaterThan(object, parameter);
								criterions.remove(i);
							}
							StringTokenizer greaterOrEqualsThan=new StringTokenizer(criton,">=");
							if (equals.countTokens()>1)
							{
								while (greaterOrEqualsThan.hasMoreTokens())
								{
									parameter2=equals.nextToken();
									
								}
								parameter=Integer.parseInt(parameter2);
								this.greaterOrEqualThan(object, parameter);
								criterions.remove(i);
							}
							StringTokenizer lessOrEqualsThan=new StringTokenizer(criton,"<=");
							if (equals.countTokens()>1)
							{
								while (lessOrEqualsThan.hasMoreTokens())
								{
									parameter2=equals.nextToken();
									
								}
								parameter=Integer.parseInt(parameter2);
								this.lessOrEqualThan(object, parameter);
								criterions.remove(i);
							}
							
							

							break;
						}
						else
							father2=son2;
					}
				}
			}

			joins.add(father2 + "." + son2);
			joins.add(rootObject);
			}
			
			return this.find();
		}
		
	
	
	private ArrayList<Criterion> getCriterions()
	{
		
		CriteriaImpl crit2=(CriteriaImpl)auxCrit;
		Iterator i=crit2.iterateExpressionEntries();
		while (i.hasNext())
		{
			CriteriaImpl.CriterionEntry criterion = (CriteriaImpl.CriterionEntry)i.next();
			Criterion o=criterion.getCriterion();
			criterions.add(o);
			
		}
		return criterions;
	}
	/**
	 * Add to the Criteria an AND clause with the restrictions desired.
	 */
	public void conjunction()
	{
		criterions=this.getCriterions();
		if (criterions.size()==2)
		{
			crit.add(Restrictions.conjunction().add(criterions.get(0)).add(criterions.get(1)));
		}
		if (criterions.size()==3)
		{
			crit.add(Restrictions.conjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)));
		}
		if (criterions.size()==4)
		{
			crit.add(Restrictions.conjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)).add(criterions.get(3)));
		}
		if (criterions.size()==5)
		{
			crit.add(Restrictions.conjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)).add(criterions.get(3)).add(criterions.get(4)));
		}
		
	}
	/**
	 * Add to the Criteria an OR clause with the restrictions desired.
	 *
	 */
	public void disjunction()
	{
		criterions=this.getCriterions();
		if (criterions.size()==2)
		{
			crit.add(Restrictions.disjunction().add(criterions.get(0)).add(criterions.get(1)));
		
		}
		if (criterions.size()==3)
		{
			crit.add(Restrictions.disjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)));
		}
		if (criterions.size()==4)
		{
			crit.add(Restrictions.disjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)).add(criterions.get(3)));
		}
		if (criterions.size()==5)
		{
			crit.add(Restrictions.disjunction().add(criterions.get(0)).add(criterions.get(1)).add(criterions.get(2)).add(criterions.get(3)).add(criterions.get(4)));
			
		}
		
	
	}
	/**
	 * Select only distinct registries of the root entity
	 *
	 */
	public void selectDistinctRootEntity()
	{
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	}
	protected Criteria copy(Criteria criteria) { 
        try { 
            ByteArrayOutputStream baostream = new ByteArrayOutputStream(); 
            ObjectOutputStream oostream = new ObjectOutputStream(baostream); 
            oostream.writeObject(criteria); 
            oostream.flush(); 
            oostream.close(); 
            ByteArrayInputStream baistream = new ByteArrayInputStream(baostream.toByteArray()); 
            ObjectInputStream oistream = new ObjectInputStream(baistream); 
            Criteria auxCrit = (Criteria)oistream.readObject(); 
            oistream.close();            
            return auxCrit; 
        } catch(Throwable t) { 
            throw new HibernateException(t); 
        } 
    } 
}
