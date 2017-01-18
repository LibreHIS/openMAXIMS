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

package ims.RefMan.forms.daycaseadmissiondialog;

import java.io.Serializable;

public final class GlobalContext extends ims.framework.FormContext implements Serializable
{
	private static final long serialVersionUID = 1L;

	public GlobalContext(ims.framework.Context context)
	{
		super(context);

		Scheduling = new SchedulingContext(context);
		Core = new CoreContext(context);
		RefMan = new RefManContext(context);
	}
	public final class SchedulingContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private SchedulingContext(ims.framework.Context context)
		{
			this.context = context;

		}

		public boolean getBookingAppointmentRefIsNotNull()
		{
			return !cx_SchedulingBookingAppointmentRef.getValueIsNull(context);
		}
		public ims.scheduling.vo.Booking_AppointmentRefVo getBookingAppointmentRef()
		{
			return (ims.scheduling.vo.Booking_AppointmentRefVo)cx_SchedulingBookingAppointmentRef.getValue(context);
		}
		public void setBookingAppointmentRef(ims.scheduling.vo.Booking_AppointmentRefVo value)
		{
			cx_SchedulingBookingAppointmentRef.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_SchedulingBookingAppointmentRef = new ims.framework.ContextVariable("Scheduling.BookingAppointmentRef", "_cv_Scheduling.BookingAppointmentRef");

		private ims.framework.Context context;
	}
	public final class CoreContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private CoreContext(ims.framework.Context context)
		{
			this.context = context;

		}

		public boolean getHospitalLocIsNotNull()
		{
			return !cx_CoreHospitalLoc.getValueIsNull(context);
		}
		public ims.core.vo.LocShortVo getHospitalLoc()
		{
			return (ims.core.vo.LocShortVo)cx_CoreHospitalLoc.getValue(context);
		}
		public void setHospitalLoc(ims.core.vo.LocShortVo value)
		{
			cx_CoreHospitalLoc.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_CoreHospitalLoc = new ims.framework.ContextVariable("Core.HospitalLoc", "_cv_Core.HospitalLoc");
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

		public boolean getDayCaseAdmApptTimeIsNotNull()
		{
			return !cx_RefManDayCaseAdmApptTime.getValueIsNull(context);
		}
		public String getDayCaseAdmApptTime()
		{
			return (String)cx_RefManDayCaseAdmApptTime.getValue(context);
		}
		public void setDayCaseAdmApptTime(String value)
		{
			cx_RefManDayCaseAdmApptTime.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManDayCaseAdmApptTime = new ims.framework.ContextVariable("RefMan.DayCaseAdmApptTime", "_cv_RefMan.DayCaseAdmApptTime");
		public boolean getDayCaseAdmClinicNameIsNotNull()
		{
			return !cx_RefManDayCaseAdmClinicName.getValueIsNull(context);
		}
		public String getDayCaseAdmClinicName()
		{
			return (String)cx_RefManDayCaseAdmClinicName.getValue(context);
		}
		public void setDayCaseAdmClinicName(String value)
		{
			cx_RefManDayCaseAdmClinicName.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManDayCaseAdmClinicName = new ims.framework.ContextVariable("RefMan.DayCaseAdmClinicName", "_cv_RefMan.DayCaseAdmClinicName");
		public boolean getDayCaseAdmApptDateIsNotNull()
		{
			return !cx_RefManDayCaseAdmApptDate.getValueIsNull(context);
		}
		public ims.framework.utils.Date getDayCaseAdmApptDate()
		{
			return (ims.framework.utils.Date)cx_RefManDayCaseAdmApptDate.getValue(context);
		}
		public void setDayCaseAdmApptDate(ims.framework.utils.Date value)
		{
			cx_RefManDayCaseAdmApptDate.setValue(context, value);
		}

		private ims.framework.ContextVariable cx_RefManDayCaseAdmApptDate = new ims.framework.ContextVariable("RefMan.DayCaseAdmApptDate", "_cv_RefMan.DayCaseAdmApptDate");

		private ims.framework.Context context;
	}

	public SchedulingContext Scheduling;
	public CoreContext Core;
	public RefManContext RefMan;
}
