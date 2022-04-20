package com.example.clientsservice.rest_controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    @ClientServiceQualifier
    private ClientService clientService;

    @PostMapping("/rest/clientForm")
    public ResponseEntity<?> clientForm(@RequestBody Client client){
        System.err.println("user = " + client);
        clientService.save(client);
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }
}
