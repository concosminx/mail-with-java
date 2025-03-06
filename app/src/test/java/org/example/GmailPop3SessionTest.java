package org.example;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Store;
import java.util.Arrays;
import javax.net.ssl.*;
import org.example.session.GmailPop3Session;
import org.example.session.MailSession;
import org.junit.jupiter.api.Test;

class GmailPop3SessionTest extends BaseClientTest {

  private static String GMAIL_USERNAME = System.getenv("GMAIL_USERNAME");
  private static String GMAIL_PASSWORD = System.getenv("GMAIL_PASSWORD");

  @Test
  void checkMails() throws Exception {


    MailSession ms = new GmailPop3Session();

    try (Store store = ms.getSession().getStore("pop3s")) {
      store.connect("pop.gmail.com", GMAIL_USERNAME, GMAIL_PASSWORD);

      try (Folder emailFolder = store.getFolder("INBOX")) {
        emailFolder.open(Folder.READ_ONLY);
        Message[] messages = emailFolder.getMessages();
        Arrays.stream(messages).forEach(this::printMsg);
      }
    }
  }



}