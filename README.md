# mail-with-java
Java mail usage for different providers | scenarios with Jakarta

1. Testing local with MailHog | Fake SMTP Server

Prerequisites:
- use docker for MailHog see - [docs](https://github.com/mailhog/MailHog?tab=readme-ov-file)

```shell
docker run -p 8025:8025 -p 1025:1025 mailhog/mailhog
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

2. Testing with Gmail SMTP Server

3. Testing with Microsoft 365 

