package ims.domain;

import ims.framework.interfaces.IUserProvider;

public abstract class UserFactory
{
	public abstract boolean hasUserProvider();
	public abstract IUserProvider getUserProvider();	
}
