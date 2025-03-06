package org.example.session;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GmailSmtpTLSSession implements MailSession {

  private static String GMAIL_USERNAME = System.getenv("GMAIL_USERNAME");
  private static String GMAIL_PASSWORD = System.getenv("GMAIL_PASSWORD");

  @Override
  public Session getSession() {

    Properties props = new Properties();

    props.put("mail.smtp.ssl.trust", "*");
    props.put("mail.smtp.starttls.enable", "true");

    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "587");

    props.put("mail.smtp.connectiontimeout", "" + TimeUnit.SECONDS.toMillis(30));
    props.put("mail.smtp.writetimeout", "" + TimeUnit.MINUTES.toMillis(30));
    props.put("mail.smtp.timeout", "" + TimeUnit.MINUTES.toMillis(5));
    props.put("mail.debug", "true");

    return Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(GMAIL_USERNAME, GMAIL_PASSWORD);
      }
    });

  }
}
