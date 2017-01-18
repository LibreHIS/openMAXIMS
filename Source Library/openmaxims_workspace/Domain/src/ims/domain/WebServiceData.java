package ims.domain;

import ims.framework.SessionConstants;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public final class WebServiceData implements Serializable
{	
	private static final long serialVersionUID = 1L;

	public WebServiceData(HttpSession httpSession)
	{	
		SessionData sessData = (SessionData) httpSession.getAttribute(SessionConstants.SESSION_DATA);
		HashMap<String, Object> session = new HashMap<String, Object>();
		this.appointmentStartDateTime = new GenericDataType<String>(session, "Appointment Start Date Time");
		this.appointmentStartDateTime.set(sessData.appointmentStartDateTime.get());
		this.appointmentEndDateTime = new GenericDataType<String>(session, "Appointment End Date Time");
		this.appointmentEndDateTime.set(sessData.appointmentEndDateTime.get());
		this.appointmentLocationCode = new GenericDataType<String>(session, "Appointment Location Code");
		this.appointmentLocationCode.set(sessData.appointmentLocationCode.get());
		this.appointmentConsultantCode = new GenericDataType<String>(session, "Appointment Consultant Code");
		this.appointmentConsultantCode.set(sessData.appointmentConsultantCode.get());
	}
	
	public GenericDataType<String> appointmentStartDateTime;
	public GenericDataType<String> appointmentEndDateTime;		
	public GenericDataType<String> appointmentLocationCode;	
	public GenericDataType<String> appointmentConsultantCode;
	
	public class GenericDataType<Type> implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		protected GenericDataType(HashMap<String, Object> session, String name)
		{
			this.session = session;
			this.name = name;
		}
		
		@SuppressWarnings("unchecked")
		public Type get()
		{
			return (Type)this.session.get(this.name);
		}
		private void set(Type value)
		{
			this.session.put(this.name, value);
		}
		
		private HashMap<String, Object> session;
		private String name;
	}
}