package ims.configuration;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassConfig implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected Map nonAuditBos = new HashMap();
	protected String[] auditableBos;
	protected HashMap mappedRootClasses = new HashMap();
	
	public ClassConfig()
	{		
	}
	
	public boolean isBoAudited(String className)
	{
		if (className == null)
			return false;
		return (nonAuditBos.get(className) == null );
	}
	
	@SuppressWarnings("unchecked")
	public void loadAuditableClasses(List l)
	{
		auditableBos = new String[l.size()];
		l.toArray(auditableBos);
	}
	
	public String[] getAuditableClasses()
	{
		return auditableBos;
	}
	
	@SuppressWarnings("unchecked")
	public String[] getNonAuditBos()
	{
		String[] ret = new String[nonAuditBos.size()];
		Collection vals = nonAuditBos.values();
		vals.toArray(ret);
		return ret;		
	}
	
	public Map getNonAuditBosMap()
	{
		return nonAuditBos;
	}
	
	public void setNonAuditBosMap(Map map)
	{
		this.nonAuditBos = map;
	}
	
	@SuppressWarnings("unchecked")
	public void loadNonAuditBos(List names)
	{
		nonAuditBos.clear();
		for (int i = 0; i < names.size(); i++)
		{
			nonAuditBos.put(names.get(i), names.get(i));
		}
	}
	
	public void clearNonAuditBos()
	{
		nonAuditBos.clear();
	}

	public void makeObjectAudited(String className)
	{
		nonAuditBos.remove(className);		
	}
		
	
	@SuppressWarnings("unchecked")
	public void makeObjectNonAudited(String className)
	{
		nonAuditBos.put(className, className);
	}
	
	@SuppressWarnings("unchecked")
	public void addMappedRootClass(String className, String[] collFields)
	{
		mappedRootClasses.put(className, collFields);
	}
	
	public HashMap getMappedRootClasses()
	{
		return mappedRootClasses;
	}

}
