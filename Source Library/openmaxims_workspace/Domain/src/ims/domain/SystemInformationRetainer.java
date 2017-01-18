/*
 * Created on 08-Mar-04
 *
 */
package ims.domain;

/**
 * Retains a SystemInformation object.
 * This interface is used to mark domain-objects that record
 * SystemInformation.
 * @author gcoghlan
 */
public interface SystemInformationRetainer
{
	public SystemInformation getSystemInformation();
}
