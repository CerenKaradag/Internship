package com.example.matine.user;

import com.example.matine.model.ReportUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @PostMapping("/process_login")
    public void login(@RequestBody LoginRequest loginRequest){
        userService.processLogin(loginRequest);
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
        userService.reportUser(userId,reportUser);
    }

    @DeleteMapping("/reports")
    public void deleteReport(@RequestBody ReportUser reportUser){
        userService.deleteReport(reportUser);
    }


    // kullanıcı uyarma ve silme
    @PostMapping("/users")
    public void warnUser(@RequestBody ReportUser reportUser) {
        userService.warnUser(reportUser);
    }

    @PutMapping("/users")
    public void unwarnUser(@RequestBody ReportUser reportUser) {
        userService.unwarnUser(reportUser);
    }

}
