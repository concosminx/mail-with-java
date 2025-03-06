package org.example.session;

import jakarta.mail.Session;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class NoAuthSession implements MailSession {


    @Override
    public Session getSession() {
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.host", "localhost"); // Mail Hog Host
        props.put("mail.smtp.port", "1025");      //Mail Hog port
        props.put("mail.debug", "true");

        props.put("mail.smtp.connectiontimeout", "" + TimeUnit.SECONDS.toMillis(30));
        props.put("mail.smtp.writetimeout", "" + TimeUnit.MINUTES.toMillis(30));
        props.put("mail.smtp.timeout", "" + TimeUnit.MINUTES.toMillis(5));

        return Session.getInstance(props);
    }
}
