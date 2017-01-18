package ims.vo;

import java.io.Serializable;

public class LookupTypeVo implements ims.vo.ImsCloneable, Serializable
{
    private static final long serialVersionUID = 1L;
    
	private int id;
	private String name;
	private String description;
	private boolean active = true;
	private boolean hierarchical = false;
	private boolean systemType = false;

	public LookupTypeVo()
	{
		
	}

	public LookupTypeVo(int id)
	{
		this.id = id;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof LookupTypeVo)
			return ((LookupTypeVo)obj).id == id;
		return false;
	}
	public int hashCode()
	{
		return id;
	}

	public LookupTypeVo(int id, String name, String description, boolean active, boolean hierarchical, boolean systemType) 
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.hierarchical = hierarchical;
		this.systemType = systemType;	
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHierarchical() {
		return hierarchical;
	}

	public void setHierarchical(boolean hierarchical) {
		this.hierarchical = hierarchical;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSystemType() {
		return systemType;
	}

	public void setSystemType(boolean systemType) {
		this.systemType = systemType;
	}
	
	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		if (!(obj instanceof LookupTypeVo))
		{
			throw new ClassCastException("A LookupTypeVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		LookupTypeVo compareObj = (LookupTypeVo)obj;
		if(this.name.equals(compareObj.name))
			return 0;
		
		return this.name.compareTo(compareObj.name) ;		
	}
	
	public Object clone()
	{
		return new LookupTypeVo(this.getId(), this.getName(), this.getDescription(), this.isActive(), this.isHierarchical(), this.isSystemType());		
	}

	public LookupTypeBean getBean()
	{
		return new LookupTypeBean(this);
	}

	public static LookupTypeBean[] getBeanArray(LookupTypeVo[] val)
	{
		LookupTypeBean[] beans = new LookupTypeBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}

}
