package com.example.matine.user;

import com.example.matine.content.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileManagementController {

    private final ProfileManagementService profileManagementService;

    @Autowired
    public ProfileManagementController(ProfileManagementService profileManagementService) {
        super();
        this.profileManagementService = profileManagementService;
    }

    @GetMapping(path = "/matine/profile/{userId}")
    public Optional<User> profile(@PathVariable ("userId") Long userId){
        return profileManagementService.getUserById(userId);
    }

    @GetMapping(path = "/matine/profile/archive/{userId}")
    public List<Content> getMyArchive(@PathVariable ("userId") Long userId){
        return profileManagementService.getMyArchive(userId);
    }

    @PutMapping(path = "/matine/profile/edit/{userId}")
    public void updateProfile(@PathVariable ("userId") Long userId,
                              @RequestBody User user){
        profileManagementService.updateUser(userId,user);
    }
}
