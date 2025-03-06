package org.example;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLTest {

  public static void main(String[] args) throws Exception {
    SSLUtils.disableSSLValidation();

    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket) factory.createSocket("pop.gmail.com", 995);

    socket.startHandshake();
    System.out.println("Conexiune reusita!");
  }
}
