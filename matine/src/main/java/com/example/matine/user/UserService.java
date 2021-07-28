package com.example.matine.user;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.ReportUser;
import com.example.matine.repository.ReportUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
            throw new ApiRequestException("İsim/Soyisim geçersiz!");
        }
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ApiRequestException("E-mail geçersiz!");
        }
        if(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears() < 18){
            throw new ApiRequestException("Yaşınız kayıt için uygun değil!");

        }

        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> processLogin(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user == null) {
            throw new ApiRequestException("Böyle bir email bulunamadı.");
        }

        if (!passwordEncoder.matches(loginRequest.password, user.getPassword())) {
            throw new ApiRequestException("Şifreniz yanlış.");
        }

        return ResponseEntity.ok().body(user);
    }

    public void reportUser(Long userId, ReportUser reportUser) {

        System.out.println(reportUser.getReportedUserName());
        reportUser.setReportingId(userId);
        User reportedUser = userRepository.findUserByUsername(reportUser.getReportedUserName());
        reportUser.setReportedId(reportedUser.getId());

        Optional<ReportUser> reportUserOptional = reportUserRepository.findReportUserByReportedIdAndReportingId(reportUser.getReportingId(),
                reportUser.getReportedId());
        if (reportUserOptional.isPresent()) {
            throw new ApiRequestException("Bu kullanıcıyı daha önce bildirdiniz!");
        }

        reportedUser.setIsReported(true);
        reportUser.setReportedUserName(reportedUser.getUserName());
        reportUserRepository.save(reportUser);
    }

    public List<ReportUser> getReports() {
        return reportUserRepository.findAll();
    }

    public void deleteReport(ReportUser reportUser) {
        reportUserRepository.delete(reportUser);
    }

    public void warnUser( ReportUser reportUser) {
        
        User warnUser = userRepository.findUserByUsername(reportUser.getReportedUserName());
        if (warnUser.getIsWarned()){
            throw new ApiRequestException("Bu kullanıcıyı daha önce uyardınız!");
        }
        warnUser.setIsReported(false);
        warnUser.setIsWarned(true);
        reportUserRepository.delete(reportUser);
        System.out.println(warnUser);
    }

    public void unwarnUser(User warnedUser) {

        User warnUser = userRepository.findUserByUsername(warnedUser.getUserName());
        if (!warnUser.getIsWarned()){
            throw new ApiRequestException("Bu kullanıcıyı uyarı almamış!");
        }

        warnedUser.setIsWarned(false);
        warnUser.setIsWarned(false);
        userRepository.findUserByUsername(warnedUser.getUserName()).setIsWarned(false);
        userRepository.save(warnUser);

    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getWarnedUsers() {

        ArrayList<User> warnedUser = new ArrayList<>();
        ArrayList<User> allUser = new ArrayList<>();
        allUser.addAll(userRepository.findAll()) ;

        for(int i = 0 ; i < allUser.size() ;i ++){
            if (allUser.get(i).getIsWarned()){
                warnedUser.add(allUser.get(i));
            }


        }

        return warnedUser;
    }
}
