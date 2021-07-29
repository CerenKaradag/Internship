package com.example.matine.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "matine/genre")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GenreController {

    // HTTP requestlerinin alındığı ve servis sınıfı
    // ile ilgili fonksiyonların çalışması için bağlantıya sahip controller sınıfı

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Bütün türlere ulaşılması
    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    // Yeni türlerin eklenmesi
    @PostMapping
    public void createNewGenre(@RequestBody Genre genre){
        genreService.addNewGenre(genre);
    }

}
