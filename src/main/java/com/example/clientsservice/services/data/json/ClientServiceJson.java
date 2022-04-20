package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceJson implements ClientService {
    public final String FILE_NAME = "clients.json";

    @Override
    public List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name,
                                                             String patronymic) {
        return null;
    }

    @Override
    public Client save(Client client) {
        List<Client> clients = findAll();
        if (clients == null)
            clients = new ArrayList<>();
        clients.add(client);
        Gson gson = new Gson();
        String json = gson.toJson(clients);
        try {
            FileUtils.write(new File(FILE_NAME), json, StandardCharsets.UTF_8);
        }
        catch (IOException ignored) {
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        Gson gson = new Gson();
        try {
            String json = FileUtils.readFileToString(new File(FILE_NAME), StandardCharsets.UTF_8);
            return gson.fromJson(json, new TypeToken<List<Client>>() {
            }.getType());
        }
        catch (IOException ignored) {
        }
        return null;
    }

    @Override
    public List<Client> saveAll(List<Client> clients) {
        return null;
    }
}
