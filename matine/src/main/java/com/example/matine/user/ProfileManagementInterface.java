package com.example.matine.user;

import com.example.matine.content.Content;
import com.example.matine.exception.ApiRequestException;

import java.util.List;
import java.util.Optional;

public interface ProfileManagementInterface {

    Optional<User> getUserById(Long userId);

    List<Content> getMyArchive(Long userId);

    void updateUser(Long userId, User userToBeUpdated) throws ApiRequestException;
}
