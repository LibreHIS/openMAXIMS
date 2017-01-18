package ims.framework.interfaces;

import ims.framework.Context;

public interface IContextSetter
{
	public void setContext(Context ctx);

	public void setPatient(int patId);
	public void setEpisodeOfCare(int eocId);
	public void setCareContext(int ctxId);
	public void setClinicalContact(int conId);
	public void setLoggedInUser();
	public void setLoggedInRsno(int userId);
}
