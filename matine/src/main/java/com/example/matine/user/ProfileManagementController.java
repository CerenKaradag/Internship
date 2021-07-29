package com.example.matine.user;

import com.example.matine.content.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileManagementController {

    // HTTP requestlerinin alındığı ve servis sınıfı
    // ile ilgili fonksiyonların çalışması için bağlantıya sahip controller sınıfı

    private final ProfileManagementService profileManagementService;

    @Autowired
    public ProfileManagementController(ProfileManagementService profileManagementService) {
        super();
        this.profileManagementService = profileManagementService;
    }

    // Kullanıcının profili için bilgilerine ulaşılması
    @GetMapping(path = "/matine/profile/{userId}")
    public Optional<User> profile(@PathVariable ("userId") Long userId){
        return profileManagementService.getUserById(userId);
    }

    // Kullanıcının arşivine eklediği içeriklere ulaşması
    @GetMapping(path = "/matine/profile/archive/{userId}")
    public List<Content> getMyArchive(@PathVariable ("userId") Long userId){
        return profileManagementService.getMyArchive(userId);
    }

    // Kullanıcının bilgilerini güncellemesi
    @PutMapping(path = "/matine/profile/edit/{userId}")
    public void updateProfile(@PathVariable ("userId") Long userId,
                              @RequestBody User user){
        profileManagementService.updateUser(userId,user);
    }
}
