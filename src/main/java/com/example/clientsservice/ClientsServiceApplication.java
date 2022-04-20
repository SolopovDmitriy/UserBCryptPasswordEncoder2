package com.example.clientsservice;

import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@SpringBootApplication
public class ClientsServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ClientsServiceApplication.class, args);
//        WebMvcConfig webMvcConfig = context.getBean(WebMvcConfig.class);
    }

    /*
    @Autowired
    private ClientRepository clientRepository;
    //    @Qualifier("clientServiceJson")
    @Autowired
    @ClientServiceQualifier
    private ClientService clientService;
    */
//    @ClientServiceQualifier
//    @Autowired
//    private ClientService clientService;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        /*
        Client c1 = new Client(0L, "S2", "N2", "P2",
                "111", "m1");
        c1 = clientRepository.save(c1);
        */
        /*
        List<Client> clientList =
                clientRepository.findAllBySurnameAndNameAndPatronymic(
                        "S2", "N2", "P2");
        for (Client client : clientList) {
            System.out.printf("Surname:%s Name:%s Phone:%s",
                    client.getSurname(), client.getName(), client.getPhone());
        }
        */
        /*
        Client c1 = new Client(0L, "S2", "N2", "P2","m1");
        clientService.save(c1);
        clientService.findAll().forEach(System.err::println);
        */

    }
}
