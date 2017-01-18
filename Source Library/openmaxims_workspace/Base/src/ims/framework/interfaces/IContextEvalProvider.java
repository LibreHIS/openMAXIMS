package ims.framework.interfaces;

import ims.vo.ValueObjectRef;

public interface IContextEvalProvider
{
	public boolean isPatientRip();
	public boolean isEpisodeEnded();
	public void setPatientInfoAndIcons(ValueObjectRef refVo);
	public void execute(ValueObjectRef patRef);
	
	public ValueObjectRef getPatientForEpisodeOfCare(ValueObjectRef refVo);	
	public ValueObjectRef getEpisodeOfCareForCareContext(ValueObjectRef refVo);
	public ValueObjectRef getCareContextForClinicalContact(ValueObjectRef refVo);
	public ValueObjectRef getReferralCareContextForCareContext(ValueObjectRef refVo);
	public ValueObjectRef getCatsReferralForCareContext(ValueObjectRef refVo); //WDEV-21070
	public void recordReadAudit(ValueObjectRef refVo, String action);

}
