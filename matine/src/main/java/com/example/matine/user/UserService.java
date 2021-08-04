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

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı
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

    // Veri tabanına kayıtlı olan kullanıcılara erişilmesini sağlayan fonksiyon
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Sisteme yeni kullanıcı eklenmesini sağlayan fonksiyon
    public ResponseEntity<?> addNewUser(User user) {

        // Kullanıcının şifre uzunluğunun en az 8 karakter olup olmadığını kontrol eder,
        // eğer uygun uzunlukta değilse ilgili hatayı önyüze iletir
        if(user.getPassword().length() < 8){
            throw new ApiRequestException("Şifre uzunluğu yetersiz!");
        }

        // Şifre uzunluğu uygunsa güvenlik için bunu şifreler ve öyle kaydeder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.ÜYE);

        // Kullanıcın isim ve soyisim bilgilerini uygun uzunluk durumuna göre kontrol eder
        if (user.getFirstName().length() < 2 || user.getLastName().length() < 2) {
            throw new ApiRequestException("İsim/Soyisim geçersiz!");
        }

        // Kullanıcın kullanıcı adı bilgisini uygun uzunluk durumuna ve var olma durumuna göre kontrol eder
        if (user.getUserName().length() < 2 || userRepository.findUserByUsername(user.getUserName()) != null) {
            throw new ApiRequestException("Kullanıcı adı başkası tarafından kullanılmakta ya da geçersiz!");
        }

        // Email bilgisinin sistemde başka bir kullanıcıda kayıtlı olması durumunda ilgili hatayı önyüze iletir
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ApiRequestException("E-mail geçersiz!");
        }

        // Kayıt yapmak isteyen kullanıcının yaşının sisteme kayıt olmak için uygun olup olmadığını kontrol
        // eder ve eğer uygun değilse ilgili hatayı önyüze iletir
        if(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears() < 18){
            throw new ApiRequestException("Yaşınız kayıt için uygun değil!");

        }

        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }


    // Kullanıcının sisteme girişinin sağlandığı fonksiyondur
    public ResponseEntity<?> processLogin(LoginRequest loginRequest) {

        // Bu fonksiyon içerisinde girilen şifrenin doğruluğu ve emailin sistemde olup olmadığı kontrol edilir
        // Eğer hatalı bir durum varsa ilgili hatalar önyüze iletilir, hata yoksa kullanıcının girişi başarılı
        // bir şekilde sağlanır
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

    // Kullanıcının başka bir kullanıcıyı bildirmesini sağlayan fonksiyondur
    public void reportUser(Long userId, ReportUser reportUser) {

        // Bu fonksiyonun içinde bildirilmek istenilen kullanıcının daha önce bildirilip bildirilmediği denetlenir
        // eğer kullanıcı hali hazırda bildiriliyse bildirmek isteyen kullanıcıya bu durum belirtilir
        // fakat kullanıcı bildirili değilse bildirisi yapılır
        reportUser.setReportingId(userId);
        User reportedUser = userRepository.findUserByUsername(reportUser.getReportedUserName());
        reportUser.setReportedId(reportedUser.getId());

        Optional<ReportUser> reportUserOptional = reportUserRepository.findReportUserByReportedIdAndReportingId(reportUser.getReportingId(),
                reportUser.getReportedId());
        if (reportUserOptional.isPresent()) {
            throw new ApiRequestException("Bu kullanıcı daha önce bildirildi!");
        }

        reportedUser.setIsReported(true);
        reportUser.setReportedUserName(reportedUser.getUserName());
        reportUserRepository.save(reportUser);
    }

    // Sistem yöneticisinin bildirili kullanıcılarla ilgili işlemleri yapabilmesi için
    // gereken erişimi sağlayan fonksiyon
    public List<ReportUser> getReports() {
        return reportUserRepository.findAll();
    }

    // Bildiri silinmesini sağlayan fonksiyon
    public void deleteReport(ReportUser reportUser) {
        reportUserRepository.delete(reportUser);
    }

    // Kullanıcının uyarılmasını sağlayan fonksiyon
    public void warnUser( ReportUser reportUser) {

        // Bu fonksiyon içinde kullanıcının hali hazırda uyarılı olup olmadığı denetlenir
        // Eğer kullanıcı uyarılıysa tekrar uyarılamayacağı belirtilir
        User warnUser = userRepository.findUserByUsername(reportUser.getReportedUserName());
        if (warnUser.getIsWarned()){
            throw new ApiRequestException("Bu kullanıcı daha önce bildirildi!");
        }
        warnUser.setIsReported(false);
        warnUser.setIsWarned(true);
        reportUserRepository.delete(reportUser);

    }

    // Uyarısı olan kullanıcının uyarısının kaldırılmasını sağlayan fonksiyon
    public void unwarnUser(User warnedUser) {

        // Bu fonksiyon içinde kullanıcının hali hazırda uyarılı olup olmadığı denetlenir
        // Eğer kullanıcı uyarılı değilse belirtilir
        User warnUser = userRepository.findUserByUsername(warnedUser.getUserName());
        if (!warnUser.getIsWarned()){
            throw new ApiRequestException("Bu kullanıcı uyarı almamış!");
        }

        warnedUser.setIsWarned(false);
        warnUser.setIsWarned(false);
        userRepository.findUserByUsername(warnedUser.getUserName()).setIsWarned(false);
        userRepository.save(warnUser);

    }

    // Kullanıcının sistemden silinmesini sağlayan fonksiyon
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Sistemdeki uyarılı kullanıcılara erişilmesini sağlayan fonksiyon
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
