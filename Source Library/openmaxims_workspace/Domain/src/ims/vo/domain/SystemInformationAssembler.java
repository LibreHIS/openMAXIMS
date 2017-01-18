/*
 * Created on Mar 26, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo.domain;

import ims.framework.utils.DateTime;

/**
 * @author gcoghlan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SystemInformationAssembler 
{
	public static ims.vo.SystemInformation create(ims.domain.SystemInformation systemInformation) 
	{
		DateTime cdat = null;
		if (systemInformation.getCreationDateTime() != null) cdat = new DateTime(systemInformation.getCreationDateTime());
		DateTime udat = null;
		if (systemInformation.getLastUpdateDateTime() != null) udat = new DateTime(systemInformation.getLastUpdateDateTime());
		return new ims.vo.SystemInformation(systemInformation.getCreationUser(), cdat, systemInformation.getLastUpdateUser(), udat);
	}
}
