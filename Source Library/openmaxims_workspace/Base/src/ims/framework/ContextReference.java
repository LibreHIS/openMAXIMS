package ims.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContextReference implements Serializable
{
	private static final long serialVersionUID = 1L;	
	private String name;
	private String key;		
	private List<ContextReference> references = new ArrayList<ContextReference>();
	
	public String getName()
	{
		return name;
	}
	public String getKey()
	{
		return key;
	}
	public List<ContextReference> getReferences()
	{
		return references;
	}
	
	public ContextReference(String name, String key)
	{
		this.name = name;
		this.key = key;
	}
	
	public void addReference(ContextReference reference)
	{
		this.references.add(reference);
	}
	public void clearReferences()
	{
		this.references.clear();
	}
}
