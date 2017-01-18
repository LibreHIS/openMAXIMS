package ims.framework.interfaces;

import java.io.Serializable;

public interface ISelectedPatient extends Serializable
{
	Integer getISelectedPatientID();
	String getISelectedPatientName(); 	
	String getISelectedPatientInterfaceID();
	Integer getISelectedPatientInterfaceIDType();
}
