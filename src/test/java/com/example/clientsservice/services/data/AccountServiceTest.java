package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.services.data.qualifiers.AccountServiceQualifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTest {
    @Autowired
    @AccountServiceQualifier
    private AccountService accountService;

    @Order(1)
    @Test
    void save() {
        Account acc = new Account(0L, 1000);
        accountService.save(acc);
    }

    @Order(2)
    @Test
    void findAll() {
        accountService.findAll();
    }

    @Order(3)
    @Test
    void findByID(){
        Assertions.assertNotNull(accountService.findByID(1L));
    }

    @Test
    void deleteById(){
        accountService.deleteById(1L);
    }
}