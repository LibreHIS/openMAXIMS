package ims.framework.interfaces;


public interface ISecurityTokenHandlerProvider
{
	public ISecurityTokenHandler[] getAllISecurityTokenParameterHandlers();
	public ISecurityTokenHandler getISecurityTokenParameterHandler(String systemNameCode);
}
