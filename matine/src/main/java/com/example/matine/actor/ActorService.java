package com.example.matine.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    // Sistemde kayıtlı olan bütün aktörlerin bulunduğu fonksiyon
    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    // Sisteme yeni aktör eklenmesini sağlayan fonksiyon
    public void addNewActor(Actor actor) {
        actorRepository.save(actor);
        System.out.println(actor);
    }

    // İlgili içeriğe ait aktörlerin bulunduğu fonksiyon
    public List<Actor> getActorsForContent(Long contentId) {

        ArrayList<Actor> actors = new ArrayList<>();

        for (int j = 0; j < actorRepository.findAll().size() ; j++){
            if(actorRepository.findAll().get(j).getContentId() == contentId){
                actors.add(actorRepository.findAll().get(j));
            }
        }

        return actors;
    }
}
