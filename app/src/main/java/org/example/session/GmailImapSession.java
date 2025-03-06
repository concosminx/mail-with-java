package org.example.session;

import jakarta.mail.Session;
import java.util.Properties;

public class GmailImapSession implements MailSession {

  @Override
  public Session getSession() {

    Properties props = new Properties();
    props.put("mail.store.protocol", "imaps");
    props.put("mail.imaps.ssl.trust", "*");

    return Session.getInstance(props, null);
  }
}
