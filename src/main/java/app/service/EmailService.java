package app.service;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/*https://support.google.com/a/answer/176600?hl=en*/
@Component
public class EmailService {

	// Sender's email ID needs to be mentioned
	static String from = "vehicle.config@gmail.com";

	// Assuming you are sending email from localhost, 192.168.162.102 ,
	// 192.168.101.150
	static String host = "aspmx.l.google.com";

	String userName = "vehicle.config@gmail.com";
	String password = "vehicle123";
	Properties properties;

	@PostConstruct
	public void init() {
		properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "25");
		properties.setProperty("mail.user", userName);
		properties.setProperty("mail.password", password);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

	}

	public void sendEmail(String to, int verificationCode, String userId) {
		// Get system properties
		// Get the default Session object.
		Authenticator authenticator = new SMTPAuthenticator();
		sendEmail(Session.getDefaultInstance(properties, authenticator), null, to, verificationCode, userId);
	}

	private void sendEmail(Session session, InternetAddress[] cc, String to, int verificationCode, String userId) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// message.addRecipients(Message.RecipientType.CC, cc);
			message.setSubject("Email Verification");
			message.setText("Hello "+userId+"! \n Please click on the following link to verify your profile."
					+ "\n http://localhost:9008/users/"+userId+"/confirmation/"+verificationCode);
			Transport.send(message);
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
