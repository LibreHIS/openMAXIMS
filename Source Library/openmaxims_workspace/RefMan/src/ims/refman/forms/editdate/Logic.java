//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Catalin Tomozei using IMS Development Environment (version 1.71 build 3937.27059)
// Copyright (C) 1995-2010 IMS MAXIMS. All rights reserved.

package ims.RefMan.forms.editdate;

import ims.RefMan.vo.CatsReferralReportsVo;
import ims.framework.enumerations.DialogResult;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		CatsReferralReportsVo catsReferral = form.getGlobalContext().RefMan.getCatsReferralReports();
		if (catsReferral != null)
		{
			form.dtim1().setValue(catsReferral.getCATSReportSentDate());
		}
	}
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}
	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (form.dtim1().getValue() == null)
		{
			engine.showMessage("CATS Report document sent date is mandatory!");
			return;
		}
		
		CatsReferralReportsVo catsReferral = form.getGlobalContext().RefMan.getCatsReferralReports();
		catsReferral.setCATSReportSentDate(form.dtim1().getValue());
		form.getGlobalContext().RefMan.setCatsReferralReports(catsReferral);
		
		engine.close(DialogResult.OK);
	}
}
