package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountService {
    Account save(Account account);

    List<Account> findAll();

    List<Account> saveAll(List<Account> accounts);

    Account findByID(long id);

    void deleteById(long id);

    void deleteAll(Set<Account> accounts);
}
