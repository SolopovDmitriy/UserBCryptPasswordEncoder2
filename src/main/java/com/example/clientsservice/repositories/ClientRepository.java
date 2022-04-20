package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name,
                                                      String patronymic);
}
