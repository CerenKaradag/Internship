package com.example.matine.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    Optional<Actor> findByActorName(String actorName);

    Optional<Actor> findByContentId(Long contentId);

    Optional<Actor> findByActorId(Long actorId);
}
