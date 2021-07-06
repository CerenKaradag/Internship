package com.example.matine.genre;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.user.User;
import org.ietf.jgss.GSSName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public void addNewGenre(Genre genre) {
        Optional<Genre> genreOptional = genreRepository.findGenreByName(genre.getName());
        if(genreOptional.isPresent()) {
            throw new ApiRequestException("Genre already exists!");
        }
        genreRepository.save(genre);
    }
}
