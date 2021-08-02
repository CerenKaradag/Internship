package com.example.matine.user;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.ReportUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {

    List<User> getUsers();

    ResponseEntity<?> addNewUser(User user) throws ApiRequestException;

    ResponseEntity<?> processLogin(LoginRequest loginRequest) throws ApiRequestException;

    void reportUser(Long userId, ReportUser reportUser) throws ApiRequestException;

    List<ReportUser> getReports();

    void deleteReport(ReportUser reportUser);

    void warnUser( ReportUser reportUser) throws ApiRequestException;

    void unwarnUser(User warnedUser) throws ApiRequestException;

    void deleteUser(User user);

    List<User> getWarnedUsers();
}
