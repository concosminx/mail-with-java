# mail-with-java
Java mail usage for different providers | scenarios with Jakarta

1. Testing local with MailHog | Fake SMTP Server

Prerequisites:
- use docker for MailHog see - [docs](https://github.com/mailhog/MailHog?tab=readme-ov-file)

```shell
docker run -p 8025:8025 -p 1025:1025 -d mailhog/mailhog
```

or docker compose

```yaml
services:
  mailhog:
    image: mailhog/mailhog
    logging:
      driver: 'none'  # disable saving logs
    ports:
      - 1025:1025 # smtp server
      - 8025:8025 # web ui
```

- check the emails on [http://localhost:8025](http://localhost:8025)

- for Fake SMTP Server you can download the .jar file from [site](https://nilhcem.com/FakeSMTP/)



2. Testing with Gmail SMTP Server (no Oauth2, just user and app password)

You will need an app password from your Google Account (can be generated only if 2FA is active). [see docs](https://support.google.com/accounts/answer/185833?hl=en)
TLS or SSL can be used [see docs](https://support.google.com/a/answer/176600?hl=en)

Extra guides [here](https://mailtrap.io/blog/gmail-smtp/)

3. Testing with Microsoft 365

For this you need to configure some stuff. 
Follow the official [guide](https://learn.microsoft.com/en-us/exchange/client-developer/legacy-protocols/how-to-authenticate-an-imap-pop-smtp-application-by-using-oauth) and configure the app in MS365 for client credential flow. 
Don't forget to do [this](https://learn.microsoft.com/en-us/exchange/client-developer/legacy-protocols/how-to-authenticate-an-imap-pop-smtp-application-by-using-oauth#register-service-principals-in-exchange).
Turn on IMAP / POP3 if needed (Gmail / Settings / Forwarding and POP/IMAP)

You will need an access token from the token endpoint for your app based on: 
- a client secret
- the client id (the app id)
- the scope `https://outlook.office365.com/.default`
- grant_type: `client_credentials`

Use the token as a password in code.