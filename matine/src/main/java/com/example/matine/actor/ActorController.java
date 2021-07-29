package com.example.matine.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "matine/actors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActorController {

    // HTTP requestlerinin alındığı ve servis sınıfı
    // ile ilgili fonksiyonların çalışması için bağlantıya sahip controller sınıfı

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // Sistemde kayıtlı olan bütün aktörlere erişilmesi
    @GetMapping
    public List<Actor> getActors() {
        return actorService.getActors();
    }

    // İlgili içeriğe ait kayıtlı olan bütün aktörlerin bulunması
    @GetMapping
    public List<Actor> getActorsForContent(@RequestBody Long contentId) {
        return actorService.getActorsForContent(contentId);
    }

    // Sisteme yeni aktör eklenmesi
    @PostMapping
    public void createNewActor(@RequestBody Actor actor){
        actorService.addNewActor(actor);
    }
}
