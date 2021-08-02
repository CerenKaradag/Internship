package com.example.matine.genre;

import com.example.matine.exception.ApiRequestException;
import java.util.List;

public interface GenreInterface {

    List<Genre> getGenres ();

    void addNewGenre (Genre genre) throws ApiRequestException;
}
