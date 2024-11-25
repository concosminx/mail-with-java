package org.example.session;

import jakarta.mail.Session;

import java.util.Properties;

public class NoAuthSession implements MailSession {


    @Override
    public Session getSession() {
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "1025"); //Mail Hog port
        props.put("mail.debug", "true");

        return Session.getInstance(props);
    }
}
