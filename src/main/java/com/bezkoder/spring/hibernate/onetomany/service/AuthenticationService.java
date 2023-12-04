package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.CredentialsDto;
import com.bezkoder.spring.hibernate.onetomany.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.CharBuffer;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto authenticate(CredentialsDto credentialsDto) {
        UserDto user = findByLogin(credentialsDto.getUsername());
        String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap("123456"));
        if (user != null && passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),
                encodedMasterPassword)) {
            return user;
        }
        throw new RuntimeException("Invalid loginId or password");
    }

    public UserDto findByLogin(String loginId) {
        if ("admin01".equals(loginId)) {
            return new UserDto(1L, "TMA User01", "ADMIN", "admin01", "token");
        }
        if ("user01".equals(loginId)) {
            return new UserDto(1L, "TMA User02", "USER", "user01", "token");
        }
        throw new RuntimeException("Invalid loginId");
    }
}
