package server.boundary;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Emailer 
{
	public static void sendMail(String replyTo, String sendTo, String subject, String text) 
	{	
		final String smtpServer = "smtp.gmail.com";
		final String smtpUserName = "gruppe18DTU";
		final String smtpUserPassword = "Kode1234";
		
		text += "\n\nDenne email er automatisk genereret og kan ikke besvares.";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpServer);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
					return new PasswordAuthentication(smtpUserName,smtpUserPassword);
			}
		});
 
		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(replyTo));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sendTo));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			System.out.println("Email sent to " + sendTo);
		} 
		
		catch (MessagingException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}