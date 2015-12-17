//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#############################################################################
//#EOH
/*
 * This code was generated
 * Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
 * IMS Development Environment (version 1.80 build 5007.25751)
 * WARNING: DO NOT MODIFY the content of this file
 * Generated: 16/04/2014, 12:34
 *
 */
package ims.oncology.configuration.domain.objects;

/**
 * 
 * @author Kevin O'Carroll
 * Generated.
 */


public class TumourCategory extends ims.domain.DomainObject implements ims.domain.SystemInformationRetainer, java.io.Serializable {
	public static final int CLASSID = 1074100008;
	private static final long serialVersionUID = 1074100008L;
	public static final String CLASSVERSION = "${ClassVersion}";

	@Override
	public boolean shouldCapQuery()
	{
		return true;
	}

	/** CategoryName */
	private String categoryName;
	/** CategoryDescription */
	private String categoryDescription;
	/** TaxonomyMap
	  * Collection of ims.core.clinical.domain.objects.TaxonomyMap.
	  */
	private java.util.List taxonomyMap;
	/** isActive */
	private Boolean isActive;
	/** Version Groups
	  * Collection of ims.oncology.configuration.domain.objects.TumourCategoryVersionGroups.
	  */
	private java.util.List versionGroups;
	/** SystemInformation */
	private ims.domain.SystemInformation systemInformation = new ims.domain.SystemInformation();
    public TumourCategory (Integer id, int ver)
    {
    	super(id, ver);
    }
    public TumourCategory ()
    {
    	super();
    }
    public TumourCategory (Integer id, int ver, Boolean includeRecord)
    {
    	super(id, ver, includeRecord);
    }
	public Class getRealDomainClass()
	{
		return ims.oncology.configuration.domain.objects.TumourCategory.class;
	}


	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		if ( null != categoryName && categoryName.length() > 255 ) {
			throw new ims.domain.exceptions.DomainRuntimeException("MaxLength ($MaxLength) exceeded for categoryName. Tried to set value: "+
				categoryName);
		}
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		if ( null != categoryDescription && categoryDescription.length() > 255 ) {
			throw new ims.domain.exceptions.DomainRuntimeException("MaxLength ($MaxLength) exceeded for categoryDescription. Tried to set value: "+
				categoryDescription);
		}
		this.categoryDescription = categoryDescription;
	}

	public java.util.List getTaxonomyMap() {
		if ( null == taxonomyMap ) {
			taxonomyMap = new java.util.ArrayList();
		}
		return taxonomyMap;
	}
	public void setTaxonomyMap(java.util.List paramValue) {
		this.taxonomyMap = paramValue;
	}

	public Boolean isIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public java.util.List getVersionGroups() {
		if ( null == versionGroups ) {
			versionGroups = new java.util.ArrayList();
		}
		return versionGroups;
	}
	public void setVersionGroups(java.util.List paramValue) {
		this.versionGroups = paramValue;
	}

	public ims.domain.SystemInformation getSystemInformation() {
		if (systemInformation == null) systemInformation = new ims.domain.SystemInformation();
		return systemInformation;
	}


	/**
	 * isConfigurationObject
	 * Taken from the Usage property of the business object, this method will return
	 * a boolean indicating whether this is a configuration object or not
	 * Configuration = true, Instantiation = false
	 */
	public static boolean isConfigurationObject()
	{
		if ( "Configuration".equals("Configuration") )
			return true;
		else
			return false;
	}





	public int getClassId() {
		return CLASSID;
	}

	public String getClassVersion()
	{
		return CLASSVERSION;
	}

	public String toAuditString()
	{
		StringBuffer auditStr = new StringBuffer();
		
		auditStr.append("\r\n*categoryName* :");
		auditStr.append(categoryName);
	    auditStr.append("; ");
		auditStr.append("\r\n*categoryDescription* :");
		auditStr.append(categoryDescription);
	    auditStr.append("; ");
		auditStr.append("\r\n*taxonomyMap* :");
		if (taxonomyMap != null)
		{
		int i3=0;
		for (i3=0; i3<taxonomyMap.size(); i3++)
		{
			if (i3 > 0)
				auditStr.append(",");
			ims.core.clinical.domain.objects.TaxonomyMap obj = (ims.core.clinical.domain.objects.TaxonomyMap)taxonomyMap.get(i3);
		    if (obj != null)
			{
				if (i3 == 0)
				{
				auditStr.append(toShortClassName(obj));
				auditStr.append("[");
				}
		        auditStr.append(obj.toString());
			}
		}
		if (i3 > 0)
			auditStr.append("] " + i3);
		}
	    auditStr.append("; ");
		auditStr.append("\r\n*isActive* :");
		auditStr.append(isActive);
	    auditStr.append("; ");
		auditStr.append("\r\n*versionGroups* :");
		if (versionGroups != null)
		{
		int i5=0;
		for (i5=0; i5<versionGroups.size(); i5++)
		{
			if (i5 > 0)
				auditStr.append(",");
			ims.oncology.configuration.domain.objects.TumourCategoryVersionGroups obj = (ims.oncology.configuration.domain.objects.TumourCategoryVersionGroups)versionGroups.get(i5);
		    if (obj != null)
			{
				if (i5 == 0)
				{
				auditStr.append(toShortClassName(obj));
				auditStr.append("[");
				}
		        auditStr.append(obj.getId());
			}
		}
		if (i5 > 0)
			auditStr.append("] " + i5);
		}
	    auditStr.append("; ");
		return auditStr.toString();
	}
	
	public String toXMLString()
	{
		return toXMLString(new java.util.HashMap());
	}
	
	public String toXMLString(java.util.HashMap domMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<class type=\"" + this.getClass().getName() + "\" ");		
		sb.append(" id=\"" + this.getId() + "\""); 
		sb.append(" source=\"" + ims.configuration.EnvironmentConfig.getImportExportSourceName() + "\" ");
		sb.append(" classVersion=\"" + this.getClassVersion() + "\" ");
		sb.append(" component=\"" + this.getIsComponentClass() + "\" >");
		
		if (domMap.get(this) == null)
		{
			domMap.put(this, this);
			sb.append(this.fieldsToXMLString(domMap));
		}
		sb.append("</class>");
		
		String keyClassName = "TumourCategory";
		String externalSource = ims.configuration.EnvironmentConfig.getImportExportSourceName();
		ims.configuration.ImportedObject impObj = (ims.configuration.ImportedObject)domMap.get(keyClassName + "_" + externalSource + "_" + this.getId());
		if (impObj == null)
		{
    		impObj = new ims.configuration.ImportedObject();
    		impObj.setExternalId(this.getId());
    		impObj.setExternalSource(externalSource);
    		impObj.setDomainObject(this);
			impObj.setLocalId(this.getId()); 
    		impObj.setClassName(keyClassName); 
			domMap.put(keyClassName + "_" + externalSource + "_" + this.getId(), impObj);
		}
		
		return sb.toString();
	}

	public String fieldsToXMLString(java.util.HashMap domMap)
	{
		StringBuffer sb = new StringBuffer();
		if (this.getCategoryName() != null)
		{
			sb.append("<categoryName>");
			sb.append(ims.framework.utils.StringUtils.encodeXML(this.getCategoryName().toString()));
			sb.append("</categoryName>");		
		}
		if (this.getCategoryDescription() != null)
		{
			sb.append("<categoryDescription>");
			sb.append(ims.framework.utils.StringUtils.encodeXML(this.getCategoryDescription().toString()));
			sb.append("</categoryDescription>");		
		}
		if (this.getTaxonomyMap() != null)
		{
			if (this.getTaxonomyMap().size() > 0 )
			{
			sb.append("<taxonomyMap>");
			sb.append(ims.domain.DomainObject.toXMLString(this.getTaxonomyMap(), domMap));
			sb.append("</taxonomyMap>");		
			}
		}
		if (this.isIsActive() != null)
		{
			sb.append("<isActive>");
			sb.append(ims.framework.utils.StringUtils.encodeXML(this.isIsActive().toString()));
			sb.append("</isActive>");		
		}
		if (this.getVersionGroups() != null)
		{
			if (this.getVersionGroups().size() > 0 )
			{
			sb.append("<versionGroups>");
			sb.append(ims.domain.DomainObject.toXMLString(this.getVersionGroups(), domMap));
			sb.append("</versionGroups>");		
			}
		}
		return sb.toString();
	}
		
	public static java.util.List fromListXMLString(org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.List list, java.util.HashMap domMap) throws Exception
	{
		if (list == null)
		 list = new java.util.ArrayList();
		fillListFromXMLString(list, el, factory, domMap);
		return list;
	}
	
	public static java.util.Set fromSetXMLString(org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.Set set, java.util.HashMap domMap) throws Exception
	{
		if (set == null)
			 set = new java.util.HashSet();
		fillSetFromXMLString(set, el, factory, domMap);
		return set;
	}
	
	private static void fillSetFromXMLString(java.util.Set set, org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.HashMap domMap) throws Exception
	{
		if (el == null)
			return;
		
		java.util.List cl = el.elements("class");
		int size = cl.size();
		
		java.util.Set newSet = new java.util.HashSet();
		for(int i=0; i<size; i++) 
		{
			org.dom4j.Element itemEl = (org.dom4j.Element)cl.get(i);
			TumourCategory domainObject = getTumourCategoryfromXML(itemEl, factory, domMap);

			if (domainObject == null)
			{
				continue;
			}
			
			//Trying to avoid the hibernate collection being marked as dirty via its public interface methods. (like add)
			if (!set.contains(domainObject)) 
				set.add(domainObject);
			newSet.add(domainObject);			
		}
		
		java.util.Set removedSet = new java.util.HashSet();
		java.util.Iterator iter = set.iterator();
		//Find out which objects need to be removed
		while (iter.hasNext())
		{
			ims.domain.DomainObject o = (ims.domain.DomainObject)iter.next();			
			if ((o == null || o.getIsRIE() == null || !o.getIsRIE().booleanValue()) && !newSet.contains(o))
			{
				removedSet.add(o);
			}
		}
		iter = removedSet.iterator();
		//Remove the unwanted objects
		while (iter.hasNext())
		{
			set.remove(iter.next());
		}		
	}
	
	private static void fillListFromXMLString(java.util.List list, org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.HashMap domMap) throws Exception
	{
		if (el == null)
			return;
		
		java.util.List cl = el.elements("class");
		int size = cl.size();
		
		for(int i=0; i<size; i++) 
		{
			org.dom4j.Element itemEl = (org.dom4j.Element)cl.get(i);
			TumourCategory domainObject = getTumourCategoryfromXML(itemEl, factory, domMap);

			if (domainObject == null)
			{
				continue;
			}

			int domIdx = list.indexOf(domainObject);
			if (domIdx == -1)
			{
				list.add(i, domainObject);
			}
			else if (i != domIdx && i < list.size())
			{
				Object tmp = list.get(i);
				list.set(i, list.get(domIdx));
				list.set(domIdx, tmp);
			}
		}		
		
		//Remove all ones in domList where index > voCollection.size() as these should
		//now represent the ones removed from the VO collection. No longer referenced.
		int i1=list.size();
		while (i1 > size)
		{
			list.remove(i1-1);
			i1=list.size();
		}
	}
		
	public static TumourCategory getTumourCategoryfromXML(String xml, ims.domain.DomainFactory factory, java.util.HashMap domMap) throws Exception
	{
		org.dom4j.Document doc = new org.dom4j.io.SAXReader().read(new org.xml.sax.InputSource(xml));
		return getTumourCategoryfromXML(doc.getRootElement(), factory, domMap);
	}
	
	public static TumourCategory getTumourCategoryfromXML(org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.HashMap domMap) throws Exception
	{
		if (el == null)
			return null;
		
		String className = el.attributeValue("type");
		if (!TumourCategory.class.getName().equals(className))
		{
			Class clz = Class.forName(className);
			if (!TumourCategory.class.isAssignableFrom(clz))
				throw new Exception("Element of type = " + className + " cannot be imported using the TumourCategory class");
			String shortClassName = className.substring(className.lastIndexOf(".")+1);
			String methodName = "get" + shortClassName + "fromXML";
			java.lang.reflect.Method m = clz.getMethod(methodName, new Class[]{org.dom4j.Element.class, ims.domain.DomainFactory.class, java.util.HashMap.class});
			return (TumourCategory)m.invoke(null, new Object[]{el, factory, domMap});
		}

		String impVersion = el.attributeValue("classVersion");
		if(!impVersion.equals(TumourCategory.CLASSVERSION))
		{
			throw new Exception("Incompatible class structure found. Cannot import instance.");
		}		
		
		TumourCategory ret = null;
		int extId = Integer.parseInt(el.attributeValue("id"));
		String externalSource = el.attributeValue("source");
		ret = (TumourCategory)factory.getImportedDomainObject(TumourCategory.class, externalSource, extId);	
		if (ret == null)
		{
			ret = new TumourCategory();
		}
		String keyClassName = "TumourCategory";

		ims.configuration.ImportedObject impObj = (ims.configuration.ImportedObject)domMap.get(keyClassName + "_" + externalSource + "_" + extId);
		if (impObj != null)
		{
			return (TumourCategory)impObj.getDomainObject();
		}
		else
		{
    		impObj = new ims.configuration.ImportedObject();
    		impObj.setExternalId(extId);
    		impObj.setExternalSource(externalSource);
    		impObj.setDomainObject(ret);
			domMap.put(keyClassName + "_" + externalSource + "_" + extId, impObj);
		}
		fillFieldsfromXML(el, factory, ret, domMap);
		return ret;
	}

	public static void fillFieldsfromXML(org.dom4j.Element el, ims.domain.DomainFactory factory, TumourCategory obj, java.util.HashMap domMap) throws Exception
	{
		org.dom4j.Element fldEl;
		fldEl = el.element("categoryName");
		if(fldEl != null)
		{	
    		obj.setCategoryName(new String(fldEl.getTextTrim()));	
		}
		fldEl = el.element("categoryDescription");
		if(fldEl != null)
		{	
    		obj.setCategoryDescription(new String(fldEl.getTextTrim()));	
		}
		fldEl = el.element("taxonomyMap");
		if(fldEl != null)
		{
			fldEl = fldEl.element("list");	
			obj.setTaxonomyMap(ims.core.clinical.domain.objects.TaxonomyMap.fromListXMLString(fldEl, factory, obj.getTaxonomyMap(), domMap));
		}
		fldEl = el.element("isActive");
		if(fldEl != null)
		{	
    		obj.setIsActive(new Boolean(fldEl.getTextTrim()));	
		}
		fldEl = el.element("versionGroups");
		if(fldEl != null)
		{
			fldEl = fldEl.element("list");	
			obj.setVersionGroups(ims.oncology.configuration.domain.objects.TumourCategoryVersionGroups.fromListXMLString(fldEl, factory, obj.getVersionGroups(), domMap));
		}
	}

	public static String[] getCollectionFields()
	{
		return new String[]{
		 "taxonomyMap"
		, "versionGroups"
		};
	}


	public static class FieldNames	
	{
	public static final String ID = "id";
		public static final String CategoryName = "categoryName";
		public static final String CategoryDescription = "categoryDescription";
		public static final String TaxonomyMap = "taxonomyMap";
		public static final String IsActive = "isActive";
		public static final String VersionGroups = "versionGroups";
	}
}
