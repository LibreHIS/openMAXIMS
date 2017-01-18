package ims.framework.interfaces;

public interface IAppFormCtxVariable
{
	public ICtxVariable getCtxVariable();
	public boolean isMandatory();
	public boolean isReadOnly();
	public boolean isClearOnly();
}
