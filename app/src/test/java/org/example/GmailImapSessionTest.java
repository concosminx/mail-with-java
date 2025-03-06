package org.example;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.BodyTerm;
import jakarta.mail.search.SearchTerm;
import jakarta.mail.search.SubjectTerm;
import java.util.Arrays;
import org.example.session.GmailImapSession;
import org.example.session.MailSession;
import org.junit.jupiter.api.Test;

class GmailImapSessionTest extends BaseClientTest {

  private static String GMAIL_USERNAME = System.getenv("GMAIL_USERNAME");
  private static String GMAIL_PASSWORD = System.getenv("GMAIL_PASSWORD");

  @Test
  void getImapSession() throws MessagingException {


    MailSession ms = new GmailImapSession();
    try (Store store = ms.getSession().getStore()) {
      store.connect("imap.gmail.com", GMAIL_USERNAME, GMAIL_PASSWORD);

      try (Folder emailFolder = store.getFolder("INBOX")) {
        emailFolder.open(Folder.READ_ONLY);
        SearchTerm searchTerm = new AndTerm(new SubjectTerm("test1"), new BodyTerm(""));
        Message[] messages = emailFolder.search(searchTerm);
        Arrays.stream(messages).forEach(this::printMsg);
      }
    }

  }
}