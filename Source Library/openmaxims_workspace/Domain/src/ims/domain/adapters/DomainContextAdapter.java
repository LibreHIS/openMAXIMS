package ims.domain.adapters;

import ims.domain.DomainSession;
import java.util.Iterator;
import ims.framework.interfaces.IContextAdapter;

public class DomainContextAdapter implements IContextAdapter
{
	private transient DomainSession session;
	
	public DomainContextAdapter(DomainSession session)
	{
		this.session = session;
	}
	public Object getAttribute(String key)
	{
		return session.getAttribute(key);
	}
	public void setAttribute(String key, Object value)
	{
		session.setAttribute(key, value);
	}
	public void removeAttribute(String key)
	{
		session.removeAttribute(key);		
	}
	public Iterator<String> getAttributeNames()
	{
		return session.getAttributeNames();
	}
	public boolean isDomainOnlySession()
	{		
		return true;
	} 
}
