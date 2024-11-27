package org.example.session;

import jakarta.mail.Session;

import java.util.Properties;

public class GmailPop3Session implements MailSession {



  @Override
  public Session getSession() {

    //se also https://javaee.github.io/javamail/docs/api/com/sun/mail/pop3/package-summary.html
    //if you're using the "pop3s" protocol to access POP3 over SSL, all the properties would be named "mail.pop3s.*"

    Properties properties = new Properties();
    properties.put("mail.pop3s.host", "pop.gmail.com");
    properties.put("mail.pop3s.port", "995");
    properties.put("mail.pop3s.starttls.enable", "true");
    properties.put("mail.pop3s.ssl.trust", "*");
    properties.put("mail.pop3s.ssl.enable", "true");

    properties.put("mail.debug", "true");
    properties.put("mail.debug.auth", "true");


    //String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    //properties.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
    //properties.setProperty("mail.pop3.socketFactory.fallback", "false");
    //properties.setProperty("mail.pop3.socketFactory.port", "995");

    return Session.getDefaultInstance(properties, null);
  }
}
