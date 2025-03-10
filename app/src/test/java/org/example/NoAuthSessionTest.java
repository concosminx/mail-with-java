/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import jakarta.mail.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.example.objects.MessageObj;
import org.example.session.MailSession;
import org.example.session.NoAuthSession;
import org.example.type.HtmlMailWithAttachment;
import org.example.type.HtmlMailWithImage;
import org.junit.jupiter.api.Test;

class NoAuthSessionTest {
  @Test
  void testMailWithAttachment() throws MessagingException, URISyntaxException, IOException {
    String to = "your.recipient@email.com";
    String from = "john.doe@your.domain";

    NoAuthSession noAuthSession = new NoAuthSession();
    MessageObj messageObj =
        MessageObj.builder()
            .from(from)
            .to(to)
            .subject("test with attachment")
            .body("body with attachment")
            .attachment(
                new File(
                    Objects.requireNonNull(MessageObj.class.getResource("/pdf-sample.pdf"))
                        .toURI()))
            .build();

    Message message = new HtmlMailWithAttachment().create(noAuthSession.getSession(), messageObj);

    try {
      Transport.send(message);
    } catch (SendFailedException sfe) {
      Address[] invalidAddresses = sfe.getInvalidAddresses();
      Address[] validUnsentAddresses = sfe.getValidUnsentAddresses();
      StringBuilder err = new StringBuilder();
      if (invalidAddresses != null && invalidAddresses.length > 0) {
        for (Address a : invalidAddresses) {
          err.append("invalid address: ").append(a).append("\n");
        }
      }
      if (validUnsentAddresses != null && validUnsentAddresses.length > 0) {
        for (Address a : validUnsentAddresses) {
          err.append("cannot send mail: ").append(a).append("\n");
        }
      }

      if (err.length() != 0) {
        System.out.println(err);
      }

      throw new RuntimeException(sfe);
    }
  }

  @Test
  void testMailWithImage() throws MessagingException, URISyntaxException, IOException {
    String to = "your.recipient@email.com";
    String from = "john.doe@your.domain";

    MailSession noAuthSession = new NoAuthSession();

    MessageObj messageObj =
        MessageObj.builder()
            .from(from)
            .to(to)
            .subject("test with attachment")
            .body("body with attachment")
            .attachment(
                new File(
                    Objects.requireNonNull(MessageObj.class.getResource("/png-sample.png"))
                        .toURI()))
            .build();

    Message message = new HtmlMailWithImage().create(noAuthSession.getSession(), messageObj);
    Transport.send(message);
  }

  @Test
  void testMailAsync() {
    String to = "your.recipient@email.com";
    String from = "john.doe@your.domain";

    CompletableFuture.runAsync(
            () -> {
              MailSession noAuthSession = new NoAuthSession();
              try {
                MessageObj messageObj =
                    MessageObj.builder()
                        .from(from)
                        .to(to)
                        .subject("test simple email")
                        .body("test body")
                        .attachment(null)
                        .build();

                Message message =
                    new HtmlMailWithAttachment().create(noAuthSession.getSession(), messageObj);
                Transport.send(message);
              } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc);
              }
            })
        .exceptionally(
            ex -> {
              System.out.println("Failed to send email: " + ex.getMessage());
              return null;
            });

    System.out.println("Email task submitted for asynchronous execution!");

    // do some work ...
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
