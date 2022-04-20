package com.example.clientsservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//
@Entity
@Table(name = "clients")
public class Client {
    public enum Gender {
        NONE, MALE, FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String surname;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String patronymic;
    @Column(nullable = false, length = 50)
    private String email;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private LocalDate birthDate;
    @Column
    private Gender gender;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Phone> phones;
    @ManyToMany
    @JoinTable(name = "clients_accounts",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id")
    )
    private Set<Account> accounts;

    public Client(Long id, String surname, String name, String patronymic, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
    }

    public Client(Long id, String surname, String name, String patronymic, String email,
                  LocalDate birthDate, Gender gender) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Client(Long id, String surname, String name, String patronymic, String email,
                  LocalDate birthDate, Gender gender, Set<Phone> phones) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(name, client.name) &&
                Objects.equals(patronymic, client.patronymic) &&
                Objects.equals(email, client.email) &&
                Objects.equals(birthDate, client.birthDate) &&
                gender == client.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, email, birthDate, gender);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}