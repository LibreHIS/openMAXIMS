package ims.framework.interfaces;

import ims.framework.utils.DateTime;

public interface ISecurityToken
{
	int getTokenId();
	DateTime getExpirationDateTime();
	ISecurityTokenParameter[] getParameters();
}
