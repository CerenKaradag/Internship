package com.example.matine.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
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

    public User() {
        this.userName = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.userRole = UserRole.MEMBER;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
    }

    public Boolean getWarned() {
        return isWarned;
    }

    public void setWarned(Boolean warned) {
        isWarned = warned;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isReported=" + isReported +
                ", isWarned=" + isWarned +
                ", userRole=" + userRole +
                '}';
    }
}
