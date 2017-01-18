package ims.domain;

import ims.framework.interfaces.IRoleProvider;

public abstract class RoleFactory
{
	public abstract boolean hasRoleProvider();
	public abstract IRoleProvider getRoleProvider();	
}
