package com.example.matine.genre;

import com.example.matine.content.Content;
import com.example.matine.content.ContentRepository;
import com.example.matine.content.ContentType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GenreConfig {

    @Bean
    CommandLineRunner commandLineRunnerGenre(GenreRepository genreRepository,ContentRepository contentRepository) {
        return args -> {
            Genre korku = new Genre(1L,"KORKU");
            Genre aksiyon = new Genre(2L,"AKSİYON");
            Content content = new Content("OCULUS","KORKU FİLMİ","KORKU",ContentType.MOVİE,1L);


            contentRepository.saveAll(List.of(content));
            genreRepository.saveAll(List.of(korku,aksiyon));

        };
    }
}
