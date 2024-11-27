package org.example;

import javax.net.ssl.*;

public class SSLUtils {
  public static void disableSSLValidation() throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
          }
          public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
          public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
        }
    };

    SSLContext sc = SSLContext.getInstance("TLS");
    sc.init(null, trustAllCerts, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

    HostnameVerifier allHostsValid = (hostname, session) -> true;
    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
  }
}
