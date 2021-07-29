package com.example.matine.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm kullanıcıların içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf
@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    // Kullanıcı adı kullanılarak kullanıcının bulunması
    @Query("SELECT s FROM User s WHERE s.userName =?1")
    public User findUserByUsername(String username);

    // Kullanıcı adı kullanılarak kullanıcının bulunması
    @Query("SELECT s FROM User s WHERE s.userName =?1")
    Optional<User> findUserByUsernameOpt(String username);

    // Kullanıcının email bilgisi kullanılarak bulunması
    @Query("SELECT s FROM User s WHERE s.email =?1")
    public User findUserByEmail(String email);

    // Kullanıcının email bilgisi kullanılarak bulunması
    @Query("SELECT s FROM User s WHERE s.email =?1")
    Optional<User> findByUserEmailOpt(String email);

    // Kullanıcının id bilgisi kullanılarak bulunması
    @Query("SELECT s FROM User s WHERE s.id =?1")
    Optional<User> findUserByIdOpt(Long userId);
}
