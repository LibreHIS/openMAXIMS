package ims.utils;

import ims.framework.exceptions.ConfigurationException;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender 
{
	private String server;
	private int port;
	private String username;
	private String password;	
	private String senderEmail;
	private String senderName;
	private boolean debug;
	
	public MailSender(String server, int port, String usernameAndPassword, String senderEmail) throws ConfigurationException
	{
		this(server, port, usernameAndPassword, senderEmail, null, false);
	}
	public MailSender(String server, int port, String usernameAndPassword, String senderEmail, boolean debug) throws ConfigurationException
	{
		this(server, port, usernameAndPassword, senderEmail, null, debug);
	}
	public MailSender(String server, int port, String usernameAndPassword, String senderEmail, String senderName) throws ConfigurationException
	{
		this(server, port, usernameAndPassword, senderEmail, senderName, false);
	}
	public MailSender(String server, int port, String usernameAndPassword, String senderEmail, String senderName, boolean debug) throws ConfigurationException
	{
		if(server == null || server.trim().length() == 0)
			throw new ConfigurationException("Invalid mail server");		
		this.server = server;
		
		if(port <= 0)
			throw new ConfigurationException("Invalid mail server port");		
		this.port = port;		
		
		if(usernameAndPassword == null || usernameAndPassword.trim().length() == 0)
			throw new ConfigurationException("Invalid mail username and password");
		
		String[] auth = usernameAndPassword.split(":");
		
		if(auth[0] == null || auth[0].trim().length() == 0)
			throw new ConfigurationException("Invalid mail username");
		
		this.username = auth[0];
		this.password = auth[1];		
		this.senderEmail = senderEmail;
		this.senderName = senderName;
		this.debug = debug;		
	}	
	
	public void sendMessage(String recipient, String subject, String message) throws MessagingException, UnsupportedEncodingException
	{
		sendMessage(new String[] { recipient }, subject, message, null);
	}
	public void sendMessage(String[] recipients, String subject, String message) throws MessagingException, UnsupportedEncodingException
	{
		sendMessage(recipients, subject, message, null);
	}
	public void sendMessage(String[] recipients, String subject, String message, String atach) throws MessagingException, UnsupportedEncodingException 
	{		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	
		Properties props = new Properties();
		props.put("mail.host", server);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", (debug ? "true" : "false"));
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.fallback", "false");
	
		Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{			
				return new PasswordAuthentication(username, password);
			}
		});
	
		session.setDebug(debug);
	
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(senderEmail);
		if(senderName != null)
		{
			addressFrom.setPersonal(senderName);
		}
		msg.setFrom(addressFrom);		
	
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) 
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
	
		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		//create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(subject);
	
		// create the second message part
		MimeBodyPart mbp2 = new MimeBodyPart();
	
	    // attach the file to the message
		if(atach != null)
		{
			FileDataSource fds = new FileDataSource(atach);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());
			
			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
		
			// add the Multipart to the message
			msg.setContent(mp);
		}
	
		// set the Date: header
		msg.setSentDate(new Date());
	
		Transport tr = session.getTransport("smtp");
		tr.connect(server, port, null, null);
		msg.saveChanges(); 
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}
}
