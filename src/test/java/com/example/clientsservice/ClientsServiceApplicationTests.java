package com.example.clientsservice;

import com.example.clientsservice.repositories.ClientRepository;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientsServiceApplicationTests {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    @ClientServiceQualifier
    ClientService clientService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(clientRepository);
        Assertions.assertNotNull(clientService, "SERVICE NULL!!!");
    }
}
