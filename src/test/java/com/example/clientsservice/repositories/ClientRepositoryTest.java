package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Order(1)
    @Test
    void save(){
        Client c1 = new Client(0L, "S2", "N2", "P2","m1");
        Client c2 = clientRepository.save(c1);
        Assertions.assertNotNull(c2);
    }

    @Order(2)
    @Test
    void findAll(){
        List<Client> clients = clientRepository.findAll();
        Assertions.assertNotEquals(clients.size(), 0);
    }

    @Test
    void deleteAll(){
        clientRepository.deleteAll();
    }
}
