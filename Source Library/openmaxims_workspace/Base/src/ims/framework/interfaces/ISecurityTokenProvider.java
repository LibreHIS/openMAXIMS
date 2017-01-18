package ims.framework.interfaces;

public interface ISecurityTokenProvider
{
	ISecurityToken getToken(String tokenId);
}
