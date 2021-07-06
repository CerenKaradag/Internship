package com.example.matine.actor;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    public void addNewActor(Actor actor) {
        Optional<Actor> actorOptional = actorRepository.findByActorName(actor.getActorName());
        if(actorOptional.isPresent()) {
            throw new ApiRequestException("Actor already exists!");
        }
        actorRepository.save(actor);
    }
}
