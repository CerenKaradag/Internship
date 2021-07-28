package com.example.matine.user;

import com.example.matine.content.Content;
import com.example.matine.content.ContentRepository;
import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Archive;
import com.example.matine.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileManagementService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final ArchiveRepository archiveRepository;

    @Autowired
    public ProfileManagementService(UserRepository userRepository,ContentRepository contentRepository,ArchiveRepository archiveRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.archiveRepository = archiveRepository;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<Content> getMyArchive(Long userId) {
        List<Archive> myArchive = archiveRepository.findArchiveByUserId(userId);
        List<Content> myContents = new ArrayList<>();
        for (int i = 0; i<myArchive.size();i++){
            Optional<Content> archivedContent = contentRepository.findById(myArchive.get(i).getContentId());
            myContents.add(archivedContent.get());
        }
        return myContents;
    }

    @Transactional
    public void updateUser(Long userId, User userToBeUpdated){
        User user = userRepository.findById(userId)
                .orElseThrow( ()-> new ApiRequestException("This user does not exist."));

        // username update
        if(userId.equals(userToBeUpdated.getId())){
            if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length()>0) {
                user.setUserName(userToBeUpdated.getUserName());
            }
        }else{
            System.out.println("aaaaaaaaaaa");
            if( userRepository.findUserByUsernameOpt(userToBeUpdated.getUserName()).isPresent()){
                throw new ApiRequestException("Username is already taken.");
            } else if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length()>0) {
                System.out.println("bbbbbbbbbbbbbb");
                user.setUserName(userToBeUpdated.getUserName());
            }
        }


        // email update
        if(userId.equals(userToBeUpdated.getId())){
            if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }
        else{
            if( userRepository.findByUserEmailOpt(userToBeUpdated.getEmail()).isPresent()){
                throw new ApiRequestException("E-mail is already taken.");
            }  else if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }



        // firstname update
        if (userToBeUpdated.getFirstName() != null && userToBeUpdated.getFirstName().length() > 0){
            user.setFirstName(userToBeUpdated.getFirstName());
        }

        // lastname update
        if (userToBeUpdated.getLastName() != null && userToBeUpdated.getLastName().length() > 0){
            user.setLastName(userToBeUpdated.getLastName());
        }

        // password update
        if( userToBeUpdated.getPassword() != null && userToBeUpdated.getPassword().length()>1 ){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String enteredPassword = userToBeUpdated.getPassword();
            String dbPassword = user.getPassword();

            String encodedPassword = passwordEncoder.encode(userToBeUpdated.getPassword());
            user.setPassword(encodedPassword);

        }

        // date of birth updated
        if (userToBeUpdated.getDateOfBirth() != null){
            user.setDateOfBirth(userToBeUpdated.getDateOfBirth());
        }

    }

}
