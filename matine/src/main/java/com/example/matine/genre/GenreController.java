package com.example.matine.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "matine/genre")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping
    public void createNewGenre(@RequestBody Genre genre){
        genreService.addNewGenre(genre);
    }

}
