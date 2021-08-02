package com.example.matine.actor;

import java.util.List;

public interface ActorInterface {

    List<Actor> getActors ();

    void addNewActor (Actor actor);

    List<Actor> getActorsForContent(Long contentId);
}
