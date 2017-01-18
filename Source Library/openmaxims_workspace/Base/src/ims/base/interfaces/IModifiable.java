package ims.base.interfaces;

public interface IModifiable 
{	
	boolean wasChanged();
	void markUnchanged();
}
