package ims.framework.interfaces;

public interface IScreenHintProvider
{
	public IScreenHint[] getHintsForForm(IAppForm form);	
	public IScreenHint getHint(String hintId);
}
