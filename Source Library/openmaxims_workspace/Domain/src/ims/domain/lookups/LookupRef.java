package ims.domain.lookups;

import ims.vo.LookupTypeVo;

import java.util.Set;

public class LookupRef extends Lookup 
{
	private static final long serialVersionUID = 1L;
	private Lookup type;
	
	public LookupRef() {
		
	}
	
	/**
	 * Constructor to set instance. Avoids need for setter
	 * @param lookupType
	 */
	public LookupRef(Lookup type) {
		this.type = type;
	}

	public boolean equals(Object obj) 
	{
		return this.type.equals(obj);
	}
	
	public int hashCode()
	{
		return this.type.hashCode();
	}

	public String getDescription() {
		return type.getDescription();
	}

	public int getId() {
		return type.getId();
	}

	public Set getInstances() {
		return type.getInstances();
	}
	
	public LookupInstance getInstance(int id) {
		return type.getInstance(id);
	}

	public String getName() {
		return type.getName();
	}
	
	public boolean getActive() {
		return type.getActive();
	}
	
	public boolean getHierarchical(){
		return type.getHierarchical();
	}
	
	public boolean getSystemType() {
		return type.getSystemType();
	}
	
	public void setDescription(String string) 
	{
		type.setDescription(string);
	}

	public void setName(String string) 
	{
		type.setName(string);
	}

	public void setActive(boolean active) 
	{
		type.setActive(active);
	}
	
	public void setHierarchical(boolean hierarchical){
		type.setHierarchical(hierarchical);
	}
	
	public void setSystemType(boolean systemType) {
		type.setSystemType(systemType);
	}
	
	/*
	public Set getForms() 
	{
		return type.getForms();
	}

	public void setForms(Set set) 
	{
		type.setForms(set);
	}
	*/

	public String toString() 
	{
		return type.toString();
	}
	
	public LookupTypeVo extractLookupTypeVo()
	{
		return type.extractLookupTypeVo();
	}
	
}
