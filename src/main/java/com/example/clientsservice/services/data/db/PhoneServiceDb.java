package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Phone;
import com.example.clientsservice.repositories.PhoneRepository;
import com.example.clientsservice.services.data.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceDb implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }
}
