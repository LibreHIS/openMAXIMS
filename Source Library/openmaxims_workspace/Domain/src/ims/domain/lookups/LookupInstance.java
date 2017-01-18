/*
 * Created on 06-Jan-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain.lookups;

import ims.configuration.EnvironmentConfig;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.utils.Color;
import ims.framework.utils.Image;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

/**
 * A LookupInstance is an instance of a lookup type.
 * Generally an instance of a LookupInstance is contained in the set of instances
 * held in a Lookup object.
 * A LookupInstance should not be used/created independently of a Lookup.
 * A LookupInstance has a set of LookupMapping objects.
 * This can be accessed in read-only view via the #getMappings method.
 * LookupMapping objects can only be added to the set via the #addMapping method.
 * 
 * @author gcoghlan
 */
public class LookupInstance implements Serializable {
	/* NB: when you add a method here also add to LookupInstanceRef */
    private static final long serialVersionUID = 1L;
	public static final String CLASSVERSION = "1";

	private int id;
	private boolean active;
	private String text;
	private Lookup type;
	private Image image;
	private Set mappings;
	private LookupInstance parent;
	private int order;
	private Color color;
	
	// returned by getMappings
	private transient Set unmodifiableMappings;
	
	
	public LookupInstance() {
	}
	
	public LookupInstance(int id, Lookup type, boolean active, String text) {
		this.id = id;
		this.type = type;
		this.active = active;
		this.text = text;
		this.image = null;
		this.parent = null;
		this.order = 0;
	}
	
	public LookupInstance(int id, Lookup type, boolean active, String text, Image image, LookupInstance parent, int order) {
		this(id,type,active,text);
		this.image = image;
		this.parent = parent;
		this.order = order;
	}
	
	public LookupInstance(int id, Lookup type, boolean active, String text, Image image, LookupInstance parent, int order, Color color) {
		this(id,type,active,text,image,parent,order);
		this.color = color;
	}

	public int getId()
	{
		return this.id;
	}
	
	public boolean isActive() 
	{
		return this.active;
	}

	public void setActive(boolean b) 
	{
		this.active = b;
	}

	public String getText() 
	{
		return this.text;
	}

	public void setText(String s) 
	{
		this.text = s;
	}

	public Lookup getType() 
	{
		return this.type;
	}
	
	/**
	 * @return read-only view of the Set of LookupMapping objects
	 */
	@SuppressWarnings("unchecked")
	public Set getMappings() 
	{
		if ( null == this.unmodifiableMappings) 
		{
			if ( null == this.mappings ) 
			{
				this.mappings = new HashSet();
			}
			//unmodifiableMappings = Collections.unmodifiableSet(mappings);
			this.unmodifiableMappings = Collections.synchronizedSet(this.mappings);
		}
		return this.unmodifiableMappings; 
	}
	
	public LookupMapping getMapping(String extSystem)
	{
		if (extSystem == null) return null;
		
		
		LookupMapping mapping = null;
		Iterator iter = getMappings().iterator();
		while (iter.hasNext())
		{
			mapping = (LookupMapping)iter.next();
			if (mapping.getExtSystem().equals(extSystem))
			{
				return mapping;
			}
		}
		return null;
	}
	
	/**
	 * Creates a new LookupMapping instance and adds it to the Set of mappings.
	 * This will fail if an existing LookupMapping in the mapping set is equals
	 * to the new one.
	 * @param systemName
	 * @param extCode
	 * @param term30
	 * @param term60
	 * @return the new LookupMapping
	 * @throws DomainException - if the new LookupMapping is equal to an existing
	 * entry in the mappings set.
	 */
	@SuppressWarnings("unchecked")
	public LookupMapping addMapping(String extSystem, String extCode) throws DomainException 
	{
		LookupMapping newMapping = 
			new LookupMapping(this, extSystem, extCode);
		Set s = this.type.getInstances();
		Iterator iter = s.iterator();
		while (iter.hasNext())
		{
			LookupInstance inst = (LookupInstance)iter.next();
			if ( inst.getMappings().contains(newMapping)) 
			{
				// An error - cannot add the same mapping twice
				StringBuffer message = new StringBuffer("Lookup instance '" + inst.getText() + "', ID=" + inst.getId() + " already has a mapping which equals: ");
				message.append(" systemName:").append(extSystem);
				message.append(", extCode:").append(extCode);
				message.append(". Duplicate mappings not permitted within the same Lookup type. ");
				throw new DomainException(message.toString());				
			}
			
		}		
		this.mappings.add(newMapping);
		return newMapping;
	}
	
	/**
	 * Remove the mapping from the Set of mappings.
	 * @param mapping
	 * @return true if the set contained the mapping
	 */
	public boolean removeMapping(LookupMapping mapping) 
	{
		return this.mappings.remove(mapping);
	}

	public Image getImage() 
	{
		return this.image;
	}

	public int getOrder() 
	{
		return this.order;
	}

	public LookupInstance getParent() 
	{
		return this.parent;
	}

	/**
	 * @param string
	 */
	public void setImage(Image image) 
	{
		this.image = image;
	}

	/**
	 * @param i
	 */
	public void setOrder(int i) 
	{
		this.order = i;
	}

	/**
	 * @param instance
	 */
	public void setParent(LookupInstance instance) 
	{
		this.parent = instance;
	}

	/**
	 * @return
	 */
	public Color getColor() 
	{
		return this.color;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) 
	{
		this.color = color;
	}

	/*
	Override equals method as component class
	*/
	public boolean equals(Object obj)
	{
		if (null == obj)
		{
			return false;
		}

		if (id == ((LookupInstance)obj).getId())
			return true;
		return false;
	}

	/**
	hashcode:
	*/
	public int hashCode()
	{
		if (this.getText() != null)
			return (10401 + this.getText().hashCode());
		else
			return (10401 + this.id);
	}
	
	public String getClassVersion()
	{
		return CLASSVERSION;
	}

	public String toXMLString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<lki id=\"" + this.getId() + "\" ");
		sb.append(" classVersion=\"" + this.getClassVersion() + "\" ");
		sb.append(" source=\"" + EnvironmentConfig.getImportExportSourceName() + "\" >");
		sb.append("<active>");
		sb.append(this.isActive());
		sb.append("</active>");
		sb.append("<text>");
		sb.append(this.getText());
		sb.append("</text>");
		sb.append("<type>");
		sb.append(this.getType().toXMLString());
		sb.append("</type>");
		sb.append("<order>");
		sb.append("" + this.getOrder());
		sb.append("</order>");
		if (this.getParent() != null)
		{
			sb.append("<parent>");
			sb.append(this.getParent().toXMLString());
			sb.append("</parent>");			
		}
		if (this.getImage() != null)
		{
			sb.append("<image>");
			sb.append(this.getImage().toXMLString());
			sb.append("</image>");			
		}
		if (this.getColor() != null)
		{
			sb.append("<color>");
			sb.append(this.getColor().getName());
			sb.append("</color>");			
		}
		if (this.getMappings() != null && this.getMappings().size() > 0)
		{
			sb.append("<mappings>");
			sb.append("<set>");
			Iterator iter = this.getMappings().iterator();
			while (iter.hasNext())
			{
				LookupMapping mapping = (LookupMapping)iter.next();
				sb.append(mapping.toXMLString());
			}
			
			sb.append("</set>");
			sb.append("</mappings>");			
		}
		sb.append("</lki>");
		return sb.toString();
	
	}
	
	public static String toXMLString(Collection coll)
	{
		if (coll == null)
			coll = new java.util.ArrayList();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<list>");
		java.util.Iterator iter = coll.iterator();
		while (iter.hasNext())
		{
			LookupInstance dom = (LookupInstance)iter.next();
			sb.append(dom.toXMLString());
		}
		sb.append("</list>");
		return sb.toString();		
	}

	@SuppressWarnings("unchecked")
	public static LookupInstance fromXMLString(Element el, DomainFactory factory)
	{
		if (el == null)
			return null;

		//JME: 20070108:
		//The code here to retrieve or create a LookupInstance from info in an XML element
		//assumes that an instance found locally for the same Lookup Type with the same text description
		//is in fact the instance required. This will prevent logical duplication caused by importing lookups
		//that have different source system id's but are in fact meant to be the same lookup.
		//For a negative id, no such assumption is required. The id should be on both systems.
		
		int extId = Integer.parseInt(el.attributeValue("id"));		
		if (extId < 0)
			return factory.getLookupInstance(extId);		
		
		Lookup type = Lookup.fromXMLString(el.element("type").element("lkt"), factory);
		String instText = el.element("text").getTextTrim();		
		List l = factory.find("from LookupInstance lki where lki.type = :type and upper(lki.text) = :text", new String[]{"type","text"}, new Object[]{type, instText.toUpperCase()});
		if (l != null && l.size() > 1)
			throw new DomainRuntimeException("More than 1 Lookup Instance found for Type = " + type.getId() + " and Text = " + instText);
		if (l != null && l.size() == 1)
			return (LookupInstance)l.get(0);
		else
		{
			//Need to add new local instance.
			boolean active = new Boolean(el.element("active").getTextTrim()).booleanValue();
			
			// Get the next val
			java.util.List lst = factory.find("from LookupInstance l where l.id = (select max(l1.id) from LookupInstance l1)");
			int nextVal = ((LookupInstance) lst.get(0)).getId();
			if (nextVal < 0)
				nextVal = 0;
			LookupInstance ret = new LookupInstance(nextVal + 1, type, active, instText);
			type.getInstances().add(ret);
			
			Element childEl = el.element("parent");
			if (childEl != null)
				ret.setParent(fromXMLString(childEl.element("lki"), factory));
			
			childEl = el.element("color");
			if (childEl != null)
				ret.setColor(Color.getColor(childEl.getTextTrim()));
			
			childEl = el.element("order");
			if (childEl != null)
				ret.setOrder(Integer.parseInt(childEl.getTextTrim()));
			
			childEl = el.element("image");
			if (childEl != null)
			{
				Class clz;
				try
				{
					clz = Class.forName("ims.core.configuration.domain.objects.AppImage");
					ret.setImage((Image)factory.getDomainObject(clz, Integer.parseInt(childEl.element("class").attributeValue("id"))));
				}
				catch (ClassNotFoundException e)
				{
				}
			}
			
			childEl = el.element("mappings");
			if (childEl != null)
			{
				ret.getMappings().clear();
				Element setEl = childEl.element("set");
				Iterator iter = setEl.elements("mapping").iterator();
				while (iter.hasNext())
				{
					Element mappingEl = (Element)iter.next();
					try
					{
						ret.addMapping(mappingEl.element("extSystem").getTextTrim(), mappingEl.element("extCode").getTextTrim());
					}
					catch (DomainException e)
					{
						//DO Nothing
					}
				}
			}
			try
			{
				factory.save(ret);
			}
			catch (StaleObjectException e)
			{
				throw new DomainRuntimeException(e);
			}
			return ret;
		}
	}
	
	public static java.util.List fromListXMLString(org.dom4j.Element el, ims.domain.DomainFactory factory, java.util.List list) throws Exception
	{
		if (list == null)
			 list = new java.util.ArrayList();
		fillListFromXMLString(list, el, factory);
		return list;
	}

	public static java.util.List fromListXMLString(String xml, ims.domain.DomainFactory factory, java.util.List list) throws Exception
	{
		if (list == null)
			 list = new java.util.ArrayList();
		fillListFromXMLString(list, xml, factory);
		return list;
	}
		
	private static void fillListFromXMLString(java.util.List list, String xml, ims.domain.DomainFactory factory) throws Exception
	{
		if (list == null)
			 list = new java.util.ArrayList();
		org.dom4j.Document doc = new org.dom4j.io.SAXReader().read(new org.xml.sax.InputSource(xml));
		fillListFromXMLString(list, doc.getRootElement(), factory);
	}

	@SuppressWarnings("unchecked")
	private static void fillListFromXMLString(java.util.List list, org.dom4j.Element el, ims.domain.DomainFactory factory) throws Exception
	{

		if (el == null)
			return;
		
		java.util.List cl = el.elements("lki");
		int size = cl.size();
		
		for(int i=0; i<size; i++) 
		{
			org.dom4j.Element itemEl = (org.dom4j.Element)cl.get(i);
			LookupInstance domainObject = fromXMLString(itemEl, factory);

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
	public static String[] getCollectionFields()
	{
		return new String[]{"mappings"};
	}
	
	
	public String toAuditString()
	{
		StringBuffer auditStr = new StringBuffer();

		auditStr.append("\r\n*text* :");
		if (text != null)
			auditStr.append(text);
	    auditStr.append("; ");
	    auditStr.append("\r\n*type* :").append(type.getName());
	    auditStr.append("; ");
	    if (parent != null)
	    	auditStr.append("\r\n*parent* :").append(parent.getText());
	    auditStr.append("; ");
		auditStr.append("\r\n*mappings* :");
		if (mappings != null)
		{
			java.util.Iterator it2 = mappings.iterator();
			int i2=0;
			while (it2.hasNext())
			{
				if (i2 > 0)
					auditStr.append(",");
				LookupMapping obj = (LookupMapping)it2.next();
				if (obj != null)
				{
					auditStr.append(obj.toString());
				}
				i2++;
			}
		}
	    auditStr.append("; ");
		return auditStr.toString();
	}


}
