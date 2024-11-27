package org.example.session;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MS365Xoauth2Session implements MailSession {
  private static String MS365_USERNAME = System.getenv("MS365_USERNAME");
  private static String MS365_PASSWORD = System.getenv("MS365_PASSWORD");

  @Override
  public Session getSession() {

    Properties props = new Properties();

    props.put("mail.smtp.ssl.trust", "*");
    props.put("mail.smtp.auth.xoauth2.disable", "false");
    props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.transport.protocol", "smtp");

    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.auth.login.disable", "true");
    props.put("mail.smtp.auth.plain.disable", "true");

    props.put("mail.smtp.connectiontimeout", "" + TimeUnit.SECONDS.toMillis(30));
    props.put("mail.smtp.writetimeout", "" + TimeUnit.MINUTES.toMillis(30));
    props.put("mail.smtp.timeout", "" + TimeUnit.MINUTES.toMillis(5));
    props.put("mail.debug", "true");
    props.put("mail.debug.auth", "true");

    return Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(MS365_USERNAME, MS365_PASSWORD);
      }
    });

  }
}