package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.repositories.AccountRepository;
import com.example.clientsservice.services.data.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceDb implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> saveAll(List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }

    @Override
    public Account findByID(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Set<Account> accounts) {
        accountRepository.deleteAll(accounts);
    }
}
