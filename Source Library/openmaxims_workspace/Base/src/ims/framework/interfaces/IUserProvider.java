package ims.framework.interfaces;

public interface IUserProvider
{	
	public IAppUser getAppUser(String username);
	public IAppUser getAppUser(String username, String password) throws Exception;
	public IAppUser getAppUser(int id);
	public String getUserRealName(IAppUser user);
	public IAppUser changePassword(IAppUser user, String newPassword) throws Exception;	
	public IAppUser lockAccount(IAppUser user, boolean value) throws Exception;
}
