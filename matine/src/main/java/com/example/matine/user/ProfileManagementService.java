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

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı
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

    // Kulanıcı id bilgisi ile kullanıcıya erişilmesini sağlayan fonksiyon
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Kullanıcının id bilgisi ile kullanıcının arşivine eklediği içeriklere erişilmesini sağlayan fonksiyon
    public List<Content> getMyArchive(Long userId) {
        List<Archive> myArchive = archiveRepository.findArchiveByUserId(userId);
        List<Content> myContents = new ArrayList<>();
        for (int i = 0; i<myArchive.size();i++){
            Optional<Content> archivedContent = contentRepository.findById(myArchive.get(i).getContentId());
            myContents.add(archivedContent.get());
        }
        return myContents;
    }

    // Kullanıcı bilgilerinin güncellenmesini sağlayan fonksiyon
    @Transactional
    public void updateUser(Long userId, User userToBeUpdated){

        // Sistemde güncellenilmek istenilen kullanıcının var olup olmadığı kontrol edilir,
        // eğer yokse ilgili hata önyüze iletilir

        User user = userRepository.findById(userId)
                .orElseThrow( ()-> new ApiRequestException("Böyle bir kullanıcı bulunmamaktadır!"));

        // Kullanıcı adı güncellenmesi, kullanıcının kullanmak istediği kullanıcı adı varsa
        // ve başkası tarafından kullanılmamışsa güncellemesi yapılır, bu şartlara uymuyorsa
        // ilgili hatalar önyüze iletilir

        if(userId.equals(userToBeUpdated.getId())){
            if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length()>0) {
                user.setUserName(userToBeUpdated.getUserName());
            }
        }else{
            if( userRepository.findUserByUsernameOpt(userToBeUpdated.getUserName()).isPresent()){
                throw new ApiRequestException("Bu kullanıcı adı başka bir kullanıcıya ait!");
            } else if (userToBeUpdated.getUserName() != null && userToBeUpdated.getUserName().length()>0) {
                user.setUserName(userToBeUpdated.getUserName());
            }
        }


// K    // Email güncellenmesi, kullanıcının kullanmak istediği email varsa
        // ve başkası tarafından kullanılmamışsa güncellemesi yapılır, bu şartlara uymuyorsa
        // ilgili hatalar önyüze iletilir

        if(userId.equals(userToBeUpdated.getId())){
            if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }
        else{
            if( userRepository.findByUserEmailOpt(userToBeUpdated.getEmail()).isPresent()){
                throw new ApiRequestException("E-mail başkası tarafından kullanılmaktadır!");
            }  else if (userToBeUpdated.getEmail() != null && userToBeUpdated.getEmail().length()>0) {
                user.setEmail(userToBeUpdated.getEmail());
            }
        }



        // İsim boyutu varsa yani güncellenmek istenilen isim boş değilse kullanıcının ismi güncellenir
        if (userToBeUpdated.getFirstName() != null && userToBeUpdated.getFirstName().length() > 0){
            user.setFirstName(userToBeUpdated.getFirstName());
        }

        // Soyisim boyutu varsa yani güncellenmek istenilen soyisim boş değilse kullanıcının soyismi güncellenir
        if (userToBeUpdated.getLastName() != null && userToBeUpdated.getLastName().length() > 0){
            user.setLastName(userToBeUpdated.getLastName());
        }

        // Şifre boyutu varsa yani güncellenmek istenilen şifre boş değilse kullanıcının şifresi güncellenir
        if( userToBeUpdated.getPassword() != null && userToBeUpdated.getPassword().length()>=8 ){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String enteredPassword = userToBeUpdated.getPassword();
            String dbPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(userToBeUpdated.getPassword());
            user.setPassword(encodedPassword);

        }
        else{
            throw new ApiRequestException("Şifre uzunluğu yeterli değil!");
        }

        // Doğum tarihi boyutu varsa yani güncellenmek istenilen doğum tarihi boş değilse kullanıcının doğum tarihi güncellenir
        if (userToBeUpdated.getDateOfBirth() != null){
            user.setDateOfBirth(userToBeUpdated.getDateOfBirth());
        }

    }

}
