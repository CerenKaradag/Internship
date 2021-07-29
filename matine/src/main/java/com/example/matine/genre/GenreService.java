package com.example.matine.genre;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.user.User;
import org.ietf.jgss.GSSName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    // Sistemdeki bütün türlere erişilen fonksiyon
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }


    // Sisteme yeni tür eklenmesini sağlayan fonksiyon
    public void addNewGenre(Genre genre) {

        // Sisteme eklenmek istenen türün sistemde bulunup bulunmadığı kontrol edilir,
        // eğer bulunuyorsa ilgili hata önyüze iletilir, bulunmuyorsa yeni tür sisteme eklenir
        Optional<Genre> genreOptional = genreRepository.findGenreByName(genre.getName());
        if(genreOptional.isPresent()) {
            throw new ApiRequestException("Bu tür sistemde bulunmakta!");
        }
        genreRepository.save(genre);
    }
}
