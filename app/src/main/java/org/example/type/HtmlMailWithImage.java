package org.example.type;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URISyntaxException;
import org.example.objects.MessageObj;

public class HtmlMailWithImage implements MessageType {

    @Override
    public Message create(Session session, MessageObj messageObj) throws MessagingException, URISyntaxException, IOException {


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageObj.getFrom()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(messageObj.getTo()));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(messageObj.getTo()));
        message.setSubject(messageObj.getSubject());

        BodyPart htmlPart = new MimeBodyPart();
        String htmlContent = "<h1>Welcome to Jakarta Mail!</h1>"
            + "<p>This is an HTML email with an embedded image.</p>"
            + "<img src='cid:image_id'>";
        htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.attachFile(messageObj.getAttachment());
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.setContentID("<image_id>");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart);

        message.setContent(multipart);

        return message;
    }
}
