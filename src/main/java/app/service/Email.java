package app.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*https://support.google.com/a/answer/176600?hl=en*/
public class Email {

	// Recipient's email ID needs to be mentioned. local@smartstream-stp.cmm , helpdesk_rdu@smartstream-stp.com
	static String to = "dsouza.ansel92@gmail.com";

	// Sender's email ID needs to be mentioned
	static String from = "vehicle.config@gmail.com";

	// Assuming you are sending email from localhost, 192.168.162.102 , 192.168.101.150
	static String host = "aspmx.l.google.com";
	
	String userName = "vehicle.config@gmail.com";
	String password = "vehicle123";

	public void config(){
		// Get system properties
				Properties properties = System.getProperties();

				// Setup mail server
				properties.setProperty("mail.smtp.host", host);
				properties.setProperty("mail.smtp.port", "25");
				properties.setProperty("mail.user", userName);
				properties.setProperty("mail.password", password);
				properties.setProperty("mail.smtp.auth", "true");
				properties.setProperty("mail.smtp.starttls.enable", "true");

				// Get the default Session object.
				Authenticator authenticator = new SMTPAuthenticator();
				sendEmail(Session.getDefaultInstance(properties, authenticator), null);
	}
	
	public void sendEmail(Session session, InternetAddress[] cc) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//message.addRecipients(Message.RecipientType.CC, cc);
			for (int i = 0; i <1; i++) {
				message.setSubject("New mail " + i);
				message.setText("S " + i);
				Transport.send(message);
				System.out.println("Sent message successfully...."+i);
			}
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	 public class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	           return new PasswordAuthentication(userName, password);
	        }
	    }

}
