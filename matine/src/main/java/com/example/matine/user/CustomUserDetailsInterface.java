package com.example.matine.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsInterface {
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
