package ims.framework.interfaces;

public interface ICtxVariable
{
	public int getId();
	public String getVariableName();
	public String getVariableKey();
	public boolean isPersistent();
	public boolean isCollection();
}
