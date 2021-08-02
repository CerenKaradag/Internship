package com.example.matine.user;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;

@Entity
@Table(name = "users")
@Data
public class User {

    // Kullanıcı modeli için oluşturulmuş user sınıfı
    // Bir kullanıcının sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta kullanıcıların veri tabanına bağlantısı sağlanmaktadır.
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    // Kullanıcıların değişkenleri
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "isReported", nullable = false)
    private Boolean isReported;

    @Column(name = "isWarned", nullable = false)
    private Boolean isWarned;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    // Bu sınıfa ait constructorlar

    public User() {
        this.userName = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.userRole = UserRole.ÜYE;
        this.isReported = false;
        this.isWarned = false;
        this.dateOfBirth = LocalDate.of(1998, Month.JANUARY,26);

    }

    public User(Long id,
                String userName,
                String firstName,
                String lastName,
                String email,
                String password,
                LocalDate dateOfBirth,
                Boolean isReported,
                Boolean isWarned,
                UserRole userRole) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isReported = isReported;
        this.isWarned = isWarned;
        this.userRole = userRole;
    }

    public User(String userName,
                String firstName,
                String lastName,
                String email,
                String password,
                LocalDate dateOfBirth,
                Boolean isReported,
                Boolean isWarned,
                UserRole userRole) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isReported = isReported;
        this.isWarned = isWarned;
        this.userRole = userRole;
    }



}
