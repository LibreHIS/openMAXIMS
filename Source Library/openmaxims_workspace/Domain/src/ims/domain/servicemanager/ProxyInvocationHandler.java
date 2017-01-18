package ims.domain.servicemanager;

import ims.domain.impl.DomainImpl;

import java.lang.reflect.InvocationHandler;

public interface ProxyInvocationHandler extends InvocationHandler
{
	DomainImpl getDomainImpl();
}
