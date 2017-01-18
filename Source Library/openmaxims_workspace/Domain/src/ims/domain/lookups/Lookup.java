/*
 * Created on 06-Jan-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain.lookups;

import ims.domain.DomainFactory;
import ims.vo.LookupTypeVo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Element;

/**
 * @author gcoghlan
 *
 */
public class Lookup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    private int id;
	private String name;
	private String description;
	private Set instances;
	private boolean active = true;
	private boolean hierarchical = false;
	private boolean systemType = false;
	//private Set forms;
	
	public Lookup() {
	}
	
	public Lookup(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;	
	}
	
	public Lookup(int id, String name, String description, boolean active, boolean hierarchical, boolean systemType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.hierarchical = hierarchical;
		this.systemType = systemType;
	
	}
		
	public String getDescription() {
		return this.description;
	}

	public int getId() {
		return this.id;
	}

	public Set getInstances() {
		if ( null == this.instances ) {
			this.instances = new HashSet();
		}
		return this.instances;
	}
	
	/**
	 * Looks for the instance with the particular id in this set.
	 * @param id
	 * @return null if the id is not in this set.
	 */
	public LookupInstance getInstance(int id) {
		Iterator iterator = this.instances.iterator();
		while (iterator.hasNext())
		{
			LookupInstance element = (LookupInstance) iterator.next();
			if (id == element.getId()) {
				return element;
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public boolean getHierarchical(){
		return this.hierarchical;
	}
	
	public boolean getSystemType() {
		return this.systemType;
	}
	

	/**
	 * @param string
	 */
	public void setDescription(String string) 
	{
		this.description = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) 
	{
		this.name = string;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}
	
	public void setHierarchical(boolean hierarchical){
		this.hierarchical = hierarchical;
	}
	
	public void setSystemType(boolean systemType) {
		this.systemType = systemType;
	}
	
	/*
	public Set getForms() 
	{
		return this.forms;
	}

	public void setForms(Set set) 
	{
		this.forms = set;
	}
	*/
	
	public String toString() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("LookupType: id:").append(this.id);
		sb.append("; name:").append(this.name);
		sb.append("; description:").append(this.description);
		return sb.toString();
	}
	
	public LookupTypeVo extractLookupTypeVo()
	{
		return new LookupTypeVo(this.getId(), this.getName(), this.getDescription(), this.getActive(), this.getHierarchical(), this.getSystemType());
	}

	public static Lookup extractDomainLookup(LookupTypeVo vo)
	{
		return new Lookup(vo.getId(), vo.getName(), vo.getDescription(), vo.isActive(), vo.isHierarchical(), vo.isSystemType());		
	}
	
	public static Lookup fromXMLString(Element el, DomainFactory factory)
	{
		if (el == null)
			return null;
		
		// Lookup Type has no attributes at this level, we need to step down further within lkt
		if (el.attributeValue("id") == null)
		{
			Element el1 = el.element("lkt");
			return factory.getLookup(Integer.parseInt(el1.attributeValue("id")));
		}
		else
			return factory.getLookup(Integer.parseInt(el.attributeValue("id")));
	}
	public String toXMLString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<lkt id=\"" + this.getId() + "\" >");
		sb.append("<active>");
		sb.append(this.getActive());
		sb.append("</active>");
		sb.append("<name>");
		sb.append(this.getName());
		sb.append("</name>");
		sb.append("</lkt>");
		return sb.toString();
	
	}

	public static String[] getCollectionFields()
	{
		return new String[]{"instances"};
	}
/*
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof Lookup)) return false;
		Lookup tmp = (Lookup)obj;
		return this.id == tmp.id;
	}
*/

}
