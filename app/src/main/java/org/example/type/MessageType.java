package org.example.type;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import org.example.objects.MessageObj;

import java.io.IOException;
import java.net.URISyntaxException;

public interface MessageType {

    Message create(Session session, MessageObj messageObj) throws MessagingException, URISyntaxException, IOException;

}
