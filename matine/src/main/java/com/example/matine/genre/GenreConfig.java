package com.example.matine.genre;

import com.example.matine.content.Content;
import com.example.matine.content.ContentRepository;
import com.example.matine.content.ContentType;
import com.example.matine.model.Archive;
import com.example.matine.repository.ArchiveRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GenreConfig {

    @Bean
    CommandLineRunner commandLineRunnerGenre(GenreRepository genreRepository, ContentRepository contentRepository, ArchiveRepository archiveRepository) {
        return args -> {
            Genre korku = new Genre(1L,"KORKU");
            Genre aksiyon = new Genre(2L,"AKSİYON");
            Content content = new Content("oculus","KORKU FİLMİ","KORKU",ContentType.MOVİE,1L);
            Content content1 = new Content("dabbe","KORKU FİLMİ","KORKU",ContentType.MOVİE,1L);
            Content content2 = new Content("testere","KORKU FİLMİ","KORKU",ContentType.MOVİE,1L);

            Archive  archive1= new Archive(
                    1L,
                    1L
            );
            Archive  archive2= new Archive(
                    1L,
                    2L
            );
            Archive  archive3= new Archive(
                    1L,
                    3L
            );

            archiveRepository.saveAll(List.of(archive1,archive2,archive3));



            contentRepository.saveAll(List.of(content,content1,content2));
            genreRepository.saveAll(List.of(korku,aksiyon));

        };
    }
}
