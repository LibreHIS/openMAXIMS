package ims.vo;

import java.util.Iterator;

public class LookupTypeBean
{
	private int id;
	private String name;
	private String description;
	private boolean active = true;
	private boolean hierarchical = false;
	private boolean systemType = false;

	public LookupTypeBean()
	{
	}
	
	public LookupTypeBean(LookupTypeVo vo)
	{
		this.id = vo.getId();
		this.name = vo.getName();
		this.description = vo.getDescription();
		this.active = vo.isActive();
		this.hierarchical = vo.isHierarchical();
		this.systemType = vo.isSystemType();
	}
	
	public LookupTypeVo buildLookupTypeVo()
	{
		return new LookupTypeVo(this.id, this.name, this.description, this.active, this.hierarchical, this.systemType);
	}
	
	public static LookupTypeVo[] buildLookupTypeVoArray(LookupTypeBean[] beans)
	{
		LookupTypeVo[] ret = new LookupTypeVo[beans.length];
		for (int i = 0; i < beans.length; i++)
		{
			ret[i] = beans[i].buildLookupTypeVo();			
		}
		return ret;		
	}

	public static LookupTypeVoCollection buildLookupTypeVoCollection(java.util.Collection beans)
	{
		LookupTypeVoCollection ret = new LookupTypeVoCollection();
		Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			ret.add(((LookupTypeBean)iter.next()).buildLookupTypeVo());			
		}
		return ret;		
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean isHierarchical()
	{
		return hierarchical;
	}

	public void setHierarchical(boolean hierarchical)
	{
		this.hierarchical = hierarchical;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isSystemType()
	{
		return systemType;
	}

	public void setSystemType(boolean systemType)
	{
		this.systemType = systemType;
	}

}
