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

    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/register")
    public void getRegistrationForm(Model model){
        model.addAttribute("user", new User());
    }

    @PostMapping("/process_register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @PostMapping("/process_login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return userService.processLogin(loginRequest);
    }

    @PostMapping("/login_failure")
    public void loginFailureHandler() {
        System.out.println("Login failure handler....");

    }

    @DeleteMapping("users")
    public void deleteUser(@RequestBody User user){
        userService.deleteUser(user);
    }

    // kullanıcı bildirileri
    @GetMapping("/reports")
    public List<ReportUser> getReports(){
       return userService.getReports();
    }

    @PostMapping("/add_report/{userId}")
    public void reportUser(@PathVariable ("userId") Long userId,
                           @RequestBody ReportUser reportUser) {
        System.out.println(reportUser);
        userService.reportUser(userId,reportUser);
    }

    @DeleteMapping("/reports")
    public void deleteReport(@RequestBody ReportUser reportUser){
        userService.deleteReport(reportUser);
    }


    // kullanıcı uyarma ve silme
    @GetMapping("warned_users")
    public List<User> getWarnedUsers() {
        return userService.getWarnedUsers();
    }
    @PostMapping("/warned_users")
    public void warnUser(@RequestBody ReportUser reportUser) {
        userService.warnUser(reportUser);
    }

    @PutMapping("/users")
    public void unwarnUser(@RequestBody User warnedUser) {
        userService.unwarnUser(warnedUser);
    }

}
