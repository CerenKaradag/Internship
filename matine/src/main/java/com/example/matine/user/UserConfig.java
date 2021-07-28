package com.example.matine.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository) {
        return args -> {

            BCryptPasswordEncoder pencoder = new BCryptPasswordEncoder();
            String encodedPass = pencoder.encode("password");
            User user1 = new User("cero","Ceren", "Karadağ", "ceren@gmail.com", encodedPass, LocalDate.of(1998,Month.JANUARY,26),false,false,UserRole.SYSTEM_ADMIN);
            User user2 = new User("utk","Utku", "Karadağ", "utku@gmail.com", encodedPass, LocalDate.of(1991,Month.JULY,24),false,false,UserRole.SYSTEM_ADMIN);
            User user3 = new User("arzu","Arzu", "Karadağ", "arzu@gmail.com", encodedPass, LocalDate.of(1968,Month.JUNE,10),false,false,UserRole.SYSTEM_ADMIN);

            repository.saveAll(List.of(user1, user2, user3));
        };


    }
}
