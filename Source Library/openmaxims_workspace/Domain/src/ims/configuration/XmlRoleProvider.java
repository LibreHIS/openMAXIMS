package ims.configuration;

import ims.framework.interfaces.IAppRole;
import ims.framework.interfaces.IRoleProvider;

public class XmlRoleProvider implements IRoleProvider
{
	public IAppRole getRole(int roleId)
	{
		return XMLConfiguration.getRole(roleId);
	}
}
