// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.domain;

// Generated from form domain impl
public interface ERODDialog extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	public ims.RefMan.vo.CatsReferralERODVo getCatsReferral(ims.RefMan.vo.CatsReferralRefVo catsReferral);

	// Generated from form domain interface definition
	public ims.RefMan.vo.CatsReferralERODVo saveEROD(ims.RefMan.vo.lookups.ERODType erodType, ims.RefMan.vo.CatsReferralERODVo catsReferralErod, ims.RefMan.vo.ReferralERODVo referralEROD) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	public ims.RefMan.vo.ReferralERODVo getEROD(ims.RefMan.vo.ReferralERODRefVo erod);

	// Generated from form domain interface definition
	public Boolean isStale(ims.RefMan.vo.ReferralERODRefVo referralERODRef);
}