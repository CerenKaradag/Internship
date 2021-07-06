package com.example.matine.actor;

import com.example.matine.genre.Genre;
import com.example.matine.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "matine/actors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getActors() {
        return actorService.getActors();
    }

    @PostMapping
    public void createNewActor(@RequestBody Actor actor){
        actorService.addNewActor(actor);
    }
}
