package com.example.matine.user;

import com.example.matine.content.Content;
import com.example.matine.content.ContentRepository;
import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Archive;
import com.example.matine.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void updateUser(Long userId, User userToBeUpdated) {

        User user = userRepository.findById(userId).orElseThrow( ()-> new ApiRequestException("Böyle bir kullanıcı yok!"));

        //kullanıcı adı güncelleme
        if(userId == userRepository.findUserByUsernameOpt(userToBeUpdated.getUserName()).get().getId()){
            if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length() > 0) {
                user.setUserName(userToBeUpdated.getUserName());
            }
        }
        else{
            if( userRepository.findUserByUsernameOpt(userToBeUpdated.getUserName()).isPresent()){
                throw new ApiRequestException("Bu kullanıcı adı başkası tarafından alınmış!");
            } else if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length()>0) {
                user.setUserName(userToBeUpdated.getUserName());
            }
        }

        // email güncelleme
        if(userId == userRepository.findByUserEmailOpt(userToBeUpdated.getEmail()).get().getId() ){
            if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }
        else{
            if( userRepository.findByUserEmailOpt(userToBeUpdated.getEmail()).isPresent()){
                throw new ApiRequestException("Kayıtlı bir email kullandınız.");
            }  else if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }



        // isim güncelleme
        if (userToBeUpdated.getFirstName() != null && userToBeUpdated.getFirstName().length() > 0){
            user.setFirstName(userToBeUpdated.getFirstName());
        }

        // soyisim güncelleme
        if (userToBeUpdated.getLastName() != null && userToBeUpdated.getLastName().length() > 0){
            user.setLastName(userToBeUpdated.getLastName());
        }

        // şifre güncelleme
        if( userToBeUpdated.getPassword() != null && userToBeUpdated.getPassword().length()>1 ){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String enteredPassword = userToBeUpdated.getPassword();
            String dbPassword = user.getPassword();
            if (passwordEncoder.matches(enteredPassword, dbPassword)) {
                throw new ApiRequestException("Şifreniz değişmedi.");
            } else {
                String encodedPassword = passwordEncoder.encode(userToBeUpdated.getPassword());
                user.setPassword(encodedPassword);
            }
        }

        // doğum tarihi güncelleme
        if (userToBeUpdated.getDateOfBirth() != null){
            user.setDateOfBirth(userToBeUpdated.getDateOfBirth());
        }
    }
}
