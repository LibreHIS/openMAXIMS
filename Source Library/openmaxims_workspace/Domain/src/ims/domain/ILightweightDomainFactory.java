package ims.domain;

import ims.domain.lookups.Lookup;
import ims.domain.lookups.LookupInstance;

import java.io.Serializable;
import java.util.List;

public interface ILightweightDomainFactory extends Serializable
{
	public List find(String hqlString);
	public List find(String query, String[] paramNames, Object[] paramValues);
	public int count(String hql, final String[] paramNames, final Object[] paramValues);
	public DomainObject getDomainObject(Class domainClass, int id);
	public Lookup getLookup(int id);
	public LookupInstance getLookupInstance(int instanceId);	
}
