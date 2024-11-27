package org.example.type;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.example.objects.MessageObj;
import java.io.IOException;
import java.net.URISyntaxException;


public class HtmlMailWithAttachment implements MessageType {

    @Override
    public Message create(Session session, MessageObj messageObj) throws MessagingException, URISyntaxException, IOException {


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageObj.getFrom()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(messageObj.getTo()));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(messageObj.getTo()));
        message.setSubject(messageObj.getSubject());

        Multipart multipart = new MimeMultipart();

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(messageObj.getBody(), "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);

        if (messageObj.getAttachment() != null) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(messageObj.getAttachment());
            multipart.addBodyPart(attachmentPart);
        }

        message.setContent(multipart);

        return message;
    }
}
