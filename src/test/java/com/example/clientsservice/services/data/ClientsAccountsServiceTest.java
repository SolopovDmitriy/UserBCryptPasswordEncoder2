package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Transactional
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientsAccountsServiceTest {
    @Autowired
    @ClientServiceQualifier
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @Transactional
    @Order(1)
    @Test
    void saveClientsAccounts(){
        List<Client> clients = generateClients(2);
        clients = clientService.saveAll(clients);
        List<Account> accounts = Arrays.asList(
                new Account(0L, 1000),
                new Account(0L, 1000)
        );
        accounts = accountService.saveAll(accounts);
        for(Client client : clients){
            for(Account account : accounts){
                if(client.getAccounts() == null){
                    client.setAccounts(new HashSet<>());
                }
                if(account.getClients() == null){
                    account.setClients(new HashSet<>());
                }
                client.getAccounts().add(account);
                account.getClients().add(client);
            }
        }
//        clients.forEach(client -> System.err.println(client + " " + client.getAccounts()));
        clientService.saveAll(clients);

        clients=clientService.findAll();
        clients.get(0).getAccounts().forEach(System.err::println);
    }

    public List<Client> generateClients(int count) {
        String surnames = "Сыпченко\n" +
                "Красинец\n" +
                "Тарасов\n" +
                "Несвитайло\n" +
                "Борисенко\n" +
                "Сасько\n" +
                "Ткаченко\n" +
                "Ефимов\n" +
                "Повалий\n" +
                "Дементьев";
        String names = "Тарас\n" +
                "Денис\n" +
                "Эдуард\n" +
                "Харитон\n" +
                "Йоханес\n" +
                "Аркадий\n" +
                "Клаус\n" +
                "Чеслав\n" +
                "Еремей\n" +
                "Кирилл";
        String patronymics = "Анатолиевич\n" +
                "Борисович\n" +
                "Петрович\n" +
                "Андреевич\n" +
                "Максимович\n" +
                "Алексеевич\n" +
                "Борисович\n" +
                "Станиславович\n" +
                "Романович\n" +
                "Платонович";
        //
        Random rd = new Random();
        String dl = "\n";
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            clients.add(new Client(
                    0L,
                    surnames.split(dl)[rd.nextInt(surnames.split(dl).length)],
                    names.split(dl)[rd.nextInt(names.split(dl).length)],
                    patronymics.split(dl)[rd.nextInt(patronymics.split(dl).length)],
                    "#@test.com".replace("#", "mail" + rd.nextInt(100)),
                    LocalDate.now(),
                    Client.Gender.NONE
            ));
        }
        return clients;
    }

    @Order(2)
    @Test
    void deleteAccountsOfClient(){
        List<Client> clients = clientService.findAll();

    }
}
