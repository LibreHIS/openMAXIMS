package ims.framework.interfaces;

public interface IEmailAuthentication
{
	 javax.mail.Authenticator getAuthentication();
	 public String getAuthenticationName();
	 public String getAuthenticationPassword();
	 public void setAutentication(String userName, String password); 
	 public String getSMTPServer();
	 public String getSMTPPort();
	 public String getPOPServer(); 
	 public String getPOPPort();
}
