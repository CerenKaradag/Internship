package com.example.matine.user;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.ReportUser;
import com.example.matine.repository.ReportUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReportUserRepository reportUserRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ReportUserRepository reportUserRepository) {
        this.userRepository = userRepository;
        this.reportUserRepository = reportUserRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> addNewUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.MEMBER);

        if (user.getFirstName().length() < 2 && user.getLastName().length() < 2) {
            throw new ApiRequestException("Name/Lastname is not valid.");
        }
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ApiRequestException("E-mail is not valid.");
        }

        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> processLogin(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user == null) {
            throw new ApiRequestException("E-mail not found.");
        }

        if (!passwordEncoder.matches(loginRequest.password, user.getPassword())) {
            throw new ApiRequestException("Wrong password.");
        }
        System.out.println(ResponseEntity.ok().body(user));
        return ResponseEntity.ok().body(user);
    }

    public void reportUser(Long userId, ReportUser reportUser) {

        reportUser.setReportingId(userId);
        reportUser.setReportedId(reportUser.getReportedId());
        User reportedUser = userRepository.findUserByUsername(reportUser.getReportedUserName());
        reportedUser.setReported(true);

        Optional<ReportUser> reportUserOptional = reportUserRepository.findReportUserByReportedIdAndReportingId(reportUser.getReportingId(),
                reportUser.getReportedId());
        if (reportUserOptional.isPresent()) {
            throw new ApiRequestException("Bu kullanıcıyı daha önce bildirdiniz");
        }

        reportUserRepository.save(reportUser);
    }

    public List<ReportUser> getReports() {
        return reportUserRepository.findAll();
    }

    public void deleteReport(ReportUser reportUser) {
        reportUserRepository.delete(reportUser);
    }

    public void warnUser( ReportUser reportUser) {
        
        User warnUser = userRepository.findById(reportUser.getReportedId()).get();
        if (warnUser.getWarned()){
            throw new ApiRequestException("Bu kullanıcıyı daha önce uyardınız!");
        }
        warnUser.setReported(false);
        warnUser.setWarned(true);
    }

    public void unwarnUser(ReportUser reportUser) {

        User warnUser = userRepository.findById(reportUser.getReportedId()).get();
        if (!warnUser.getWarned()){
            throw new ApiRequestException("Bu kullanıcıyı uyarı almamış!");
        }
        warnUser.setWarned(false);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
