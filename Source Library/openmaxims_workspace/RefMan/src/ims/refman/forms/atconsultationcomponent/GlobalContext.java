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

package ims.RefMan.forms.atconsultationcomponent;

import java.io.Serializable;

public final class GlobalContext extends ims.framework.FormContext implements Serializable
{
	private static final long serialVersionUID = 1L;

	public GlobalContext(ims.framework.Context context)
	{
		super(context);

		RefMan = new RefManContext(context);
		Core = new CoreContext(context);
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

		private ims.framework.ContextVariable cx_RefManCatsReferral = new ims.framework.ContextVariable("RefMan.CatsReferral", "_cvp_RefMan.CatsReferral");
		public boolean getSelectedSpecialtyIsNotNull()
		{
			return !cx_RefManSelectedSpecialty.getValueIsNull(context);
		}
		public ims.core.vo.lookups.Specialty getSelectedSpecialty()
		{
			return (ims.core.vo.lookups.Specialty)cx_RefManSelectedSpecialty.getValue(context);
		}
		public void setSelectedSpecialty(ims.core.vo.lookups.Specialty value)
		{
			cx_RefManSelectedSpecialty.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManSelectedSpecialty = new ims.framework.ContextVariable("RefMan.SelectedSpecialty", "_cv_RefMan.SelectedSpecialty");
		public boolean getIsComponentInEditModeIsNotNull()
		{
			return !cx_RefManIsComponentInEditMode.getValueIsNull(context);
		}
		public ims.framework.enumerations.FormMode getIsComponentInEditMode()
		{
			return (ims.framework.enumerations.FormMode)cx_RefManIsComponentInEditMode.getValue(context);
		}
		public void setIsComponentInEditMode(ims.framework.enumerations.FormMode value)
		{
			cx_RefManIsComponentInEditMode.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManIsComponentInEditMode = new ims.framework.ContextVariable("RefMan.IsComponentInEditMode", "_cv_RefMan.IsComponentInEditMode");
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

		private ims.framework.Context context;
	}
	public final class CoreContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private CoreContext(ims.framework.Context context)
		{
			this.context = context;

			SelectMedication = new SelectMedicationContext(context);
		}
		public final class SelectMedicationContext implements Serializable
		{
			private static final long serialVersionUID = 1L;

			private SelectMedicationContext(ims.framework.Context context)
			{
				this.context = context;
			}
			public boolean getSelectedMedicationIsNotNull()
			{
				return !cx_CoreSelectMedicationSelectedMedication.getValueIsNull(context);
			}
			public ims.core.vo.MedicationVo getSelectedMedication()
			{
				return (ims.core.vo.MedicationVo)cx_CoreSelectMedicationSelectedMedication.getValue(context);
			}
		public void setSelectedMedication(ims.core.vo.MedicationVo value)
		{
				cx_CoreSelectMedicationSelectedMedication.setValue(context, value);
		}

			private ims.framework.ContextVariable cx_CoreSelectMedicationSelectedMedication = new ims.framework.ContextVariable("Core.SelectMedication.SelectedMedication", "_cv_Core.SelectMedication.SelectedMedication");
			private ims.framework.Context context;
		}

		public boolean getPatientShortIsNotNull()
		{
			return !cx_CorePatientShort.getValueIsNull(context);
		}
		public ims.core.vo.PatientShort getPatientShort()
		{
			return (ims.core.vo.PatientShort)cx_CorePatientShort.getValue(context);
		}

		private ims.framework.ContextVariable cx_CorePatientShort = new ims.framework.ContextVariable("Core.PatientShort", "_cvp_Core.PatientShort");
		public boolean getEpisodeofCareShortIsNotNull()
		{
			return !cx_CoreEpisodeofCareShort.getValueIsNull(context);
		}
		public ims.core.vo.EpisodeofCareShortVo getEpisodeofCareShort()
		{
			return (ims.core.vo.EpisodeofCareShortVo)cx_CoreEpisodeofCareShort.getValue(context);
		}

		private ims.framework.ContextVariable cx_CoreEpisodeofCareShort = new ims.framework.ContextVariable("Core.EpisodeofCareShort", "_cvp_Core.EpisodeofCareShort");
		public boolean getPatientDiagnosisAtConsultationIsNotNull()
		{
			return !cx_CorePatientDiagnosisAtConsultation.getValueIsNull(context);
		}
		public ims.core.vo.PatientDiagnosisAtConsultationVo getPatientDiagnosisAtConsultation()
		{
			return (ims.core.vo.PatientDiagnosisAtConsultationVo)cx_CorePatientDiagnosisAtConsultation.getValue(context);
		}
		public void setPatientDiagnosisAtConsultation(ims.core.vo.PatientDiagnosisAtConsultationVo value)
		{
			cx_CorePatientDiagnosisAtConsultation.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CorePatientDiagnosisAtConsultation = new ims.framework.ContextVariable("Core.PatientDiagnosisAtConsultation", "_cv_Core.PatientDiagnosisAtConsultation");
		public boolean getPatientDiagnosisAtConsultationListIsNotNull()
		{
			return !cx_CorePatientDiagnosisAtConsultationList.getValueIsNull(context);
		}
		public ims.core.vo.PatientDiagnosisAtConsultationVoCollection getPatientDiagnosisAtConsultationList()
		{
			return (ims.core.vo.PatientDiagnosisAtConsultationVoCollection)cx_CorePatientDiagnosisAtConsultationList.getValue(context);
		}
		public void setPatientDiagnosisAtConsultationList(ims.core.vo.PatientDiagnosisAtConsultationVoCollection value)
		{
			cx_CorePatientDiagnosisAtConsultationList.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CorePatientDiagnosisAtConsultationList = new ims.framework.ContextVariable("Core.PatientDiagnosisAtConsultationList", "_cv_Core.PatientDiagnosisAtConsultationList");
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

		public SelectMedicationContext SelectMedication;
		private ims.framework.Context context;
	}

	public RefManContext RefMan;
	public CoreContext Core;
}
