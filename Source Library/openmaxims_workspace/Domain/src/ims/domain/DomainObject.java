/*
 * Created on 12-Jan-04
 *
 */
package ims.domain;

/**
 * A DomainObject has a unique id. This can be used to provide an ordering and to make comparisons.
 * 
 * @author gcoghlan
 *  
 */
public abstract class DomainObject implements Comparable, java.io.Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private Integer			id;
	private Boolean			isRIE;
	protected boolean		isComponentClass;
	private boolean			includeRecord;
	private int				version;

	public abstract String toAuditString();
	public abstract int getClassId();
	public abstract String getClassVersion();
	public abstract String toXMLString(java.util.HashMap domMap);
	public abstract String toXMLString();
	public abstract Class getRealDomainClass();
	
	public boolean shouldCapQuery()
	{
		return true;
	}
	public boolean canNeverBeAudited()
	{
		return false;
	}
	public boolean shouldFireRules()
	{
		return true;
	}
	
	public static String toXMLString(java.util.Set domSet)
	{
		return toXMLString(domSet, new java.util.HashMap());
	}
	
	public static String toXMLString(java.util.Set domSet, java.util.HashMap domMap)
	{
		if (domSet == null)
			domSet = new java.util.HashSet();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<set>");
		java.util.Iterator iter = domSet.iterator();
		while (iter.hasNext())
		{
			DomainObject dom = (DomainObject)iter.next();
			if (dom != null) // FWD-231
				sb.append(dom.toXMLString(domMap));
		}
		sb.append("</set>");
		return sb.toString();
	}
	
	public static String toXMLString(java.util.List domList)
	{
		return toXMLString(domList, new java.util.HashMap());
	}

	public static String toXMLString(java.util.List domList, java.util.HashMap domMap)
	{
		if (domList == null)
			domList = new java.util.ArrayList();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<list>");
		java.util.Iterator iter = domList.iterator();
		while (iter.hasNext())
		{
			DomainObject dom = (DomainObject)iter.next();
			if (dom != null) // FWD-231
				sb.append(dom.toXMLString(domMap));
		}
		sb.append("</list>");
		return sb.toString();
	}

	public DomainObject()
	{

	}

	public DomainObject(Integer id, Boolean includeRecord)
	{
		this(id, 0, includeRecord);
	}
	
	public DomainObject(Integer id, int ver)
	{
		this(id, ver, Boolean.FALSE);
	}

	public DomainObject(Integer id, int ver, Boolean includeRecord)
	{
		this.id = id;
		if (id != null)
		{
			this.version = ver;
		}
		if (includeRecord != null)
		{
			this.includeRecord = includeRecord.booleanValue();			
		}
	}

	public Integer getId()
	{
		return this.id;
	}

	public int getVersion()
	{
		return this.version;
	}
	public void setVersion(int ver)
	{
		this.version = ver;
	}

	public boolean isIncludeRecord()
	{
		return includeRecord;
	}

	public void setIncludeRecord(boolean includeRecord)
	{
		this.includeRecord = includeRecord;
	}

	public Boolean getIsRIE()
	{
		return isRIE;
	}

	public void setIsRIE(Boolean isRIE)
	{
		this.isRIE = isRIE;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("id:").append(this.id).append(".");
		sb.append("timestamp:").append(this.version).append(".");
		return sb.toString();
	}

	/**
	 * This DomainObject is equal to another if the other is not null and it has the same class, and the id is the same.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (null == obj)
		{
			return false;
		}
		if (obj.getClass().equals(this.getClass()))
		{
			// JME: 20040902: Test to see if Object identity will do.
			// Hibernate documentation discourages equality by id
			//JME: 20060523: Re-instate equality by id, having problems now on just object identity.
			//return (this == obj);
			
			DomainObject domainObject = (DomainObject) obj; 
			Integer id = this.getId(); 
			if ( null == id ) // fall back on object equality 
			{  
				return (this == obj); 
			} 
			return (id.equals(domainObject.getId()));
		}
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		// JME: 20040902: Test to see if Object identity will do.
		// Hibernate documentation discourages equality by id
		//JME: 20060523: Re-instate equality by id, having problems now on just object identity.
		//return super.hashCode();
		if ( null != id )
		{ 
			return id.hashCode(); 
		} 
		 
		return super.hashCode(); 		
	}

	/**
	 * Comparision based on the id of this DomainObject and another.
	 * 
	 * @throws ClassCastException
	 *             if the object is null or not a DomainObject.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		if (null == o)
		{
			throw new ClassCastException("Can't compare with null.");
		}
		DomainObject domainObject = (DomainObject) o;
		Integer thisId = getId();
		if (null == thisId)
		{
			thisId = new Integer(System.identityHashCode(this));
		}
		Integer thatId = domainObject.getId();
		if (null == thatId)
		{
			thatId = new Integer(System.identityHashCode(domainObject));
		}
		return thisId.compareTo(thatId);
	}

	public boolean getIsComponentClass()
	{
		return isComponentClass;
	}

	public String toShortClassName(Object obj)
	{
		String shortClassName = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1);
		// May not yet be initialised by hibernate, this is appended to classname!
		int idx =shortClassName.indexOf("$$EnhancerByCGLIB$"); 
		if (idx != -1)
			return "(" + shortClassName.substring(0, idx) + ")";
		return "(" + shortClassName + ")";
	}
}
