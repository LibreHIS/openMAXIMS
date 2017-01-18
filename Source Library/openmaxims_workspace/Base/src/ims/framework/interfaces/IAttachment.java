package ims.framework.interfaces;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public interface IAttachment
{
	public MimeBodyPart getBody() throws MessagingException;
}
