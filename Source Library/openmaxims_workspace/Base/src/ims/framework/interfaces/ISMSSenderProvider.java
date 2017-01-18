package ims.framework.interfaces;

public interface ISMSSenderProvider
{
	void send(INotification notification);
	void send(INotification[] notifications);
}
