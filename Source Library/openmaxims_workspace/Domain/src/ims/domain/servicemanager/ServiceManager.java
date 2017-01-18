package ims.domain.servicemanager;

import ims.domain.DomainSession;

public interface ServiceManager
{
 Object getServiceObject(Class requiredType);
 Object getServiceObject(DomainSession domainSession,Class requiredType);
}
