package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Phone;

import java.util.List;

public interface PhoneService {
    Phone save(Phone phone);

    List<Phone> findAll();
}
