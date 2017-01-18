package ims.framework.interfaces;

import java.util.Iterator;

public interface IContextAdapter
{
	Object getAttribute(String key);
	void setAttribute(String key, Object value);
	void removeAttribute(String key);
	Iterator<String> getAttributeNames();
	boolean isDomainOnlySession();
}	
