package com.example.matine.user;

import com.example.matine.model.ReportUser;
import com.example.matine.repository.ReportUserRepository;
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
            UserRepository repository, ReportUserRepository reportUserRepository) {
        return args -> {

            BCryptPasswordEncoder pencoder = new BCryptPasswordEncoder();
            String encodedPass = pencoder.encode("password");
            User user1 = new User("Cero","Ceren", "Karadağ", "ceren@gmail.com", encodedPass, LocalDate.of(1998,Month.JANUARY,26),false,false,UserRole.SİSTEM_YÖNETİCİSİ);
            User user2 = new User("Utk","Utku", "Karadağ", "utku@gmail.com", encodedPass, LocalDate.of(1991,Month.JULY,24),false,false,UserRole.SİSTEM_YÖNETİCİSİ);
            User user3 = new User("Arzu","Arzu", "Karadağ", "arzu@gmail.com", encodedPass, LocalDate.of(1968,Month.JUNE,10),false,false,UserRole.SİSTEM_YÖNETİCİSİ);
            User user4 = new User("Mj","Michael", "Jordan", "mj@gmail.com", encodedPass, LocalDate.of(1963,Month.FEBRUARY,17),true,false,UserRole.ÜYE);
            User user5 = new User("Jamess","James", "Nicholson", "jn@gmail.com", encodedPass, LocalDate.of(1992,Month.JUNE,22),false,true,UserRole.ÜYE);
            User user6 = new User("Pitt","Brad", "Pitt", "pitt@gmail.com", encodedPass, LocalDate.of(1963,Month.DECEMBER,18),true,false,UserRole.ÜYE);
            User user7 = new User("Jen","Jennifer", "Anniston", "jen@gmail.com", encodedPass, LocalDate.of(1969,Month.FEBRUARY,11),false,false,UserRole.ÜYE);
            User user8 = new User("Cc","Courteney", "Cox", "cox@gmail.com", encodedPass, LocalDate.of(196,Month.JUNE,15),false,false,UserRole.ÜYE);

            ReportUser reportUser1 = new ReportUser(1L,4L,"Amaç dışı kullanım.","Mj");
            ReportUser reportUser2 = new ReportUser(1L,6L,"Argo yorumlar.","Pitt");


            reportUserRepository.saveAll(List.of(reportUser1,reportUser2));
            repository.saveAll(List.of(user1, user2, user3,user4,user5,user6,user7,user8));
        };


    }
}
