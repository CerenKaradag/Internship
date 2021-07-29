package com.example.matine.user;

import com.example.matine.model.ReportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "matine")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    // HTTP requestlerinin alındığı ve servis sınıfı
    // ile ilgili fonksiyonların çalışması için bağlantıya sahip controller sınıfı

    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Bütün kullanıcılara ulaşılması
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Kayıt formuna ulaşılması
    @GetMapping("/register")
    public void getRegistrationForm(Model model){
        model.addAttribute("user", new User());
    }

    // Yeni kullanıcı kaydının oluşturulması
    @PostMapping("/process_register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    // Sisteme giriş yapılması
    @PostMapping("/process_login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return userService.processLogin(loginRequest);
    }

    // Hatalı girişin denetlenmesi
    @PostMapping("/login_failure")
    public void loginFailureHandler() {
        System.out.println("Login failure handler....");

    }

    // Herhangi bir kullanıcının sistem yöneticisi tarafından sistemden silinmesi
    @DeleteMapping("users")
    public void deleteUser(@RequestBody User user){
        userService.deleteUser(user);
    }

    // KULLANICI BİLDİRİ İŞLEMLERİ

    // Bütün kullanıcı bildirilerine ulaşılması
    @GetMapping("/reports")
    public List<ReportUser> getReports(){
       return userService.getReports();
    }

    // Yeni bir kullanıcı bildirisi eklenmesi
    @PostMapping("/add_report/{userId}")
    public void reportUser(@PathVariable ("userId") Long userId,
                           @RequestBody ReportUser reportUser) {
        System.out.println(reportUser);
        userService.reportUser(userId,reportUser);
    }

    // Bildirilerin sistem yöneticisi tarafından silinmesi
    @DeleteMapping("/reports")
    public void deleteReport(@RequestBody ReportUser reportUser){
        userService.deleteReport(reportUser);
    }

    // KULLANICI UYARMA

    // Bütün uyarılı kullanıcılara erişilmesi
    @GetMapping("warned_users")
    public List<User> getWarnedUsers() {
        return userService.getWarnedUsers();
    }

    // Bildirili kullanıcıların uyarılması
    @PostMapping("/warned_users")
    public void warnUser(@RequestBody ReportUser reportUser) {
        userService.warnUser(reportUser);
    }

    // Uyarılı kullanıcıların uyarılarının kaldırılması
    @PutMapping("/users")
    public void unwarnUser(@RequestBody User warnedUser) {
        userService.unwarnUser(warnedUser);
    }

}
