package uz.pdp.backend.service.gmailservice;

import lombok.SneakyThrows;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailServiceimp implements GmailService {

    public static String USERNAME = "dizzymansur@gmail.com";
    public  static  String PASSWORD = "pvicwvzxbvzbnfud";
    @Override
    @SneakyThrows
    public void registerMailService(String recipientEmail)  {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Verification code");
        message.setContent("<h2>Verification Code</h2>" +
                "           <p>Your verification code is: <strong>" + verificationCode() + "</strong></p>","text/html");
        Transport.send(message);
    }

    @Override
    public String verificationCode() {
        return String.valueOf((int)(Math.random() * 9000) + 1000);
    }
}
