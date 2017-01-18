package ims.framework.interfaces;

public interface ILookupType
{
	public int getId();
	public String getName();
	public ILookupInstance[] getInstances();
	public boolean isSystem();
	public boolean isHierarchical();
	public boolean isActive();
}
