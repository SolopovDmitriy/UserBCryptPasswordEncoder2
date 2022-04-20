package com.example.clientsservice.services.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailingServiceImplementTest {
    @Autowired
    private MailingServiceImplement mailingServiceImpl;

    @Test
    void sendAccountInfo() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            mailingServiceImpl.sendAccountInfo(
                    "lusaglusenko@gmail.com",
                    "Are you using this email for this project nobody knows and never will???");
            Thread.sleep(5000);
        }
    }

    @Test
    void sendAccountsInfo() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            mailingServiceImpl.sendAccountsInfo(
                    new String[]{"email1@test.com", "email2@test.com"},
                    "Are you using this email for this project nobody knows and never will???");
            Thread.sleep(5000);
        }
    }

    //
}