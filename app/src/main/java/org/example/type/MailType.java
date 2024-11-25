package org.example.type;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;

import java.io.IOException;
import java.net.URISyntaxException;

public interface MailType {

    Message create(Session session, String from, String to, String subject, String body) throws MessagingException, URISyntaxException, IOException;

}
