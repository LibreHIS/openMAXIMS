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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.referralwizard;

import java.io.Serializable;

public final class GlobalContext extends ims.framework.FormContext implements Serializable
{
	private static final long serialVersionUID = 1L;

	public GlobalContext(ims.framework.Context context)
	{
		super(context);

		Core = new CoreContext(context);
		RefMan = new RefManContext(context);
		Admin = new AdminContext(context);
	}
	public final class CoreContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private CoreContext(ims.framework.Context context)
		{
			this.context = context;

		}

		public boolean getPatientShortIsNotNull()
		{
			return !cx_CorePatientShort.getValueIsNull(context);
		}
		public ims.core.vo.PatientShort getPatientShort()
		{
			return (ims.core.vo.PatientShort)cx_CorePatientShort.getValue(context);
		}
		public void setPatientShort(ims.core.vo.PatientShort value)
		{
			cx_CorePatientShort.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CorePatientShort = new ims.framework.ContextVariable("Core.PatientShort", "_cvp_Core.PatientShort");
		public boolean getGPDetailsIsNotNull()
		{
			return !cx_CoreGPDetails.getValueIsNull(context);
		}
		public ims.core.vo.GpShortVo getGPDetails()
		{
			return (ims.core.vo.GpShortVo)cx_CoreGPDetails.getValue(context);
		}

		private ims.framework.ContextVariable cx_CoreGPDetails = new ims.framework.ContextVariable("Core.GPDetails", "_cv_Core.GPDetails");
		public boolean getPatientFilterIsNotNull()
		{
			return !cx_CorePatientFilter.getValueIsNull(context);
		}
		public ims.core.vo.PatientFilter getPatientFilter()
		{
			return (ims.core.vo.PatientFilter)cx_CorePatientFilter.getValue(context);
		}
		public void setPatientFilter(ims.core.vo.PatientFilter value)
		{
			cx_CorePatientFilter.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CorePatientFilter = new ims.framework.ContextVariable("Core.PatientFilter", "_cvp_Core.PatientFilter");
		public boolean getPatientToBeDisplayedIsNotNull()
		{
			return !cx_CorePatientToBeDisplayed.getValueIsNull(context);
		}
		public ims.core.vo.PatientShort getPatientToBeDisplayed()
		{
			return (ims.core.vo.PatientShort)cx_CorePatientToBeDisplayed.getValue(context);
		}
		public void setPatientToBeDisplayed(ims.core.vo.PatientShort value)
		{
			cx_CorePatientToBeDisplayed.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CorePatientToBeDisplayed = new ims.framework.ContextVariable("Core.PatientToBeDisplayed", "_cv_Core.PatientToBeDisplayed");
		public boolean getCurrentCareContextIsNotNull()
		{
			return !cx_CoreCurrentCareContext.getValueIsNull(context);
		}
		public ims.core.vo.CareContextShortVo getCurrentCareContext()
		{
			return (ims.core.vo.CareContextShortVo)cx_CoreCurrentCareContext.getValue(context);
		}
		public void setCurrentCareContext(ims.core.vo.CareContextShortVo value)
		{
			cx_CoreCurrentCareContext.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CoreCurrentCareContext = new ims.framework.ContextVariable("Core.CurrentCareContext", "_cvp_Core.CurrentCareContext");

		private ims.framework.Context context;
	}
	public final class RefManContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private RefManContext(ims.framework.Context context)
		{
			this.context = context;

		}

		public boolean getCatsReferralIsNotNull()
		{
			return !cx_RefManCatsReferral.getValueIsNull(context);
		}
		public ims.RefMan.vo.CatsReferralRefVo getCatsReferral()
		{
			return (ims.RefMan.vo.CatsReferralRefVo)cx_RefManCatsReferral.getValue(context);
		}
		public void setCatsReferral(ims.RefMan.vo.CatsReferralRefVo value)
		{
			cx_RefManCatsReferral.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManCatsReferral = new ims.framework.ContextVariable("RefMan.CatsReferral", "_cvp_RefMan.CatsReferral");
		public boolean getIsProviderCancellationIsNotNull()
		{
			return !cx_RefManIsProviderCancellation.getValueIsNull(context);
		}
		public Boolean getIsProviderCancellation()
		{
			return (Boolean)cx_RefManIsProviderCancellation.getValue(context);
		}
		public void setIsProviderCancellation(Boolean value)
		{
			cx_RefManIsProviderCancellation.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManIsProviderCancellation = new ims.framework.ContextVariable("RefMan.IsProviderCancellation", "_cvp_RefMan.IsProviderCancellation");
		public boolean getReferralContractTypeForPatientIsNotNull()
		{
			return !cx_RefManReferralContractTypeForPatient.getValueIsNull(context);
		}
		public ims.core.vo.lookups.ReferralManagementContractType getReferralContractTypeForPatient()
		{
			return (ims.core.vo.lookups.ReferralManagementContractType)cx_RefManReferralContractTypeForPatient.getValue(context);
		}
		public void setReferralContractTypeForPatient(ims.core.vo.lookups.ReferralManagementContractType value)
		{
			cx_RefManReferralContractTypeForPatient.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManReferralContractTypeForPatient = new ims.framework.ContextVariable("RefMan.ReferralContractTypeForPatient", "_cvp_RefMan.ReferralContractTypeForPatient");
		public boolean getCatsReferralWizardIsNotNull()
		{
			return !cx_RefManCatsReferralWizard.getValueIsNull(context);
		}
		public ims.RefMan.vo.CatsReferralWizardVo getCatsReferralWizard()
		{
			return (ims.RefMan.vo.CatsReferralWizardVo)cx_RefManCatsReferralWizard.getValue(context);
		}
		public void setCatsReferralWizard(ims.RefMan.vo.CatsReferralWizardVo value)
		{
			cx_RefManCatsReferralWizard.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManCatsReferralWizard = new ims.framework.ContextVariable("RefMan.CatsReferralWizard", "_cv_RefMan.CatsReferralWizard");
		public boolean getReferralTransferIsNotNull()
		{
			return !cx_RefManReferralTransfer.getValueIsNull(context);
		}
		public ims.RefMan.vo.ReferralTransferVo getReferralTransfer()
		{
			return (ims.RefMan.vo.ReferralTransferVo)cx_RefManReferralTransfer.getValue(context);
		}
		public void setReferralTransfer(ims.RefMan.vo.ReferralTransferVo value)
		{
			cx_RefManReferralTransfer.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManReferralTransfer = new ims.framework.ContextVariable("RefMan.ReferralTransfer", "_cv_RefMan.ReferralTransfer");
		public boolean getDiagnosticReferralForApplicationIsNotNull()
		{
			return !cx_RefManDiagnosticReferralForApplication.getValueIsNull(context);
		}
		public Boolean getDiagnosticReferralForApplication()
		{
			return (Boolean)cx_RefManDiagnosticReferralForApplication.getValue(context);
		}
		public void setDiagnosticReferralForApplication(Boolean value)
		{
			cx_RefManDiagnosticReferralForApplication.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManDiagnosticReferralForApplication = new ims.framework.ContextVariable("RefMan.DiagnosticReferralForApplication", "_cvp_RefMan.DiagnosticReferralForApplication");
		public boolean getPatientsGPIsNotNull()
		{
			return !cx_RefManPatientsGP.getValueIsNull(context);
		}
		public ims.core.vo.GpShortVo getPatientsGP()
		{
			return (ims.core.vo.GpShortVo)cx_RefManPatientsGP.getValue(context);
		}
		public void setPatientsGP(ims.core.vo.GpShortVo value)
		{
			cx_RefManPatientsGP.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManPatientsGP = new ims.framework.ContextVariable("RefMan.PatientsGP", "_cv_RefMan.PatientsGP");

		private ims.framework.Context context;
	}
	public final class AdminContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private AdminContext(ims.framework.Context context)
		{
			this.context = context;

		}

		public boolean getPracticeIsNotNull()
		{
			return !cx_AdminPractice.getValueIsNull(context);
		}
		public ims.core.vo.OrganisationWithSitesVo getPractice()
		{
			return (ims.core.vo.OrganisationWithSitesVo)cx_AdminPractice.getValue(context);
		}

		private ims.framework.ContextVariable cx_AdminPractice = new ims.framework.ContextVariable("Admin.Practice", "_cv_Admin.Practice");

		private ims.framework.Context context;
	}

	public CoreContext Core;
	public RefManContext RefMan;
	public AdminContext Admin;
}
