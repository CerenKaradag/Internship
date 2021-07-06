package com.example.matine.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User s WHERE s.userName =?1")
    public User findUserByUsername(String username);

    @Query("SELECT s FROM User s WHERE s.userName =?1")
    Optional<User> findUserByUsernameOpt(String username);

    @Query("SELECT s FROM User s WHERE s.email =?1")
    public User findUserByEmail(String email);

    @Query("SELECT s FROM User s WHERE s.email =?1")
    Optional<User> findByUserEmailOpt(String email);

    @Query("SELECT s FROM User s WHERE s.id =?1")
    Optional<User> findUserByIdOpt(Long userId);
}
