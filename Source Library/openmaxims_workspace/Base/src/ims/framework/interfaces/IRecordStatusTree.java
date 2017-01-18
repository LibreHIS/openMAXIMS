package ims.framework.interfaces;

import ims.framework.RecordStatus;

public interface IRecordStatusTree
{
	RecordStatus getIRecordStatusTreeStatus();
    boolean isNew();
    IRecordStatusTree[] getChildren(); 
    String getDomainObjectName(); 
    String getStatusMethodName();
}
