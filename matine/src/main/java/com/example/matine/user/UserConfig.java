package com.example.matine.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository) {
        return args -> {
           User systemAdmin =  new User(
                    1L,
                    "Cero",
                    "Ceren",
                    "KARADAĞ",
                    "ceren@gmail.com",
                    "aaaaaaaa",
                    LocalDate.of(1998, Month.JANUARY,26),
                    false,
                    false,
                    UserRole.SYSTEM_ADMIN
           );

           repository.saveAll(
                   List.of(systemAdmin)
           );
        };


    }
}
