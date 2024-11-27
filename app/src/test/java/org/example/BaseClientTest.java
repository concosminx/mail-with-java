package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class BaseClientTest {

  /**
   * Prints some info about the message
   *
   * @param msg
   */
  protected void printMsg(Message msg) {
    final StringBuilder sb = new StringBuilder();
    sb.append("Message info").append("\n");
    try {
      Address[] from = msg.getFrom();
      Arrays.stream(from).forEach(
          address -> sb.append("From: " + ((InternetAddress) address).getAddress())
      );
      sb.append("\n");
      sb.append("Sent date:" + msg.getSentDate()).append("\n");
      sb.append("Subject:" + msg.getSubject()).append("\n");

      Multipart mp = (Multipart) msg.getContent();
      int count = mp.getCount();
      sb.append("Body count: " + count).append("\n");

      for (int i = 0; i < count; i++) {
        sb.append("---------- Body no. " + i).append("\n");
        BodyPart bp = mp.getBodyPart(i);
        sb.append("Content: ").append(bp.getContent()).append("\n");
        sb.append("Content type: ").append(bp.getContentType()).append("\n");
        sb.append("Disposition: ").append(bp.getDisposition()).append("\n");
        sb.append("File name: ").append(bp.getFileName()).append("\n");
        sb.append("Size: ").append(bp.getSize()).append("\n");


        if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
          String file = bp.getFileName();

          File targetFile = new File(file);
          InputStream initialStream = bp.getInputStream();
          Files.copy(
              initialStream,
              targetFile.toPath(),
              StandardCopyOption.REPLACE_EXISTING
          );
          initialStream.close();
        }
      }

      System.out.println(sb);
    } catch (Exception ex) {
      throw new RuntimeException("Printing failed for msg " + msg);
    }
  }
}
