package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name,
                                                      String patronymic);

    Client save(Client client);

    List<Client> findAll();

    List<Client> saveAll(List<Client> clients);
}
