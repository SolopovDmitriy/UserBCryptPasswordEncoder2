package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.repositories.PhoneRepository;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class ClientServiceTest {
    @ClientServiceQualifier
    @Autowired
    private ClientService clientService;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private AccountService accountService;

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

    @Test
    void saveAll() {
        int count = 3;
        List<Client> clients = generateClients(count);
        clientService.saveAll(clients);
    }

    @Test
    void saveClientWithPhones() {
        Client client = new Client(0L, "S2", "N2", "P2", "m1",
                LocalDate.now(), Client.Gender.MALE, null);
        client = clientService.save(client);
        Phone p1 = new Phone(0L, "111", client);
        Phone p2 = new Phone(0L, "222", client);
        phoneRepository.saveAll(Arrays.asList(p1, p2));
    }

}










