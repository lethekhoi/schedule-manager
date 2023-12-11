package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.CredentialsDto;
import com.bezkoder.spring.hibernate.onetomany.dto.UserDto;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.CharBuffer;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public UserDto authenticate(CredentialsDto credentialsDto) {
        Users user = userService.findByUserName(credentialsDto.getUsername());
        String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap(user.getPassword()));
        if (user != null && passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),
                encodedMasterPassword)) {
            UserDto userDto = new UserDto(1L, user.getContacts(), user.getRole(), user.getUserName(), "token");
            return userDto;
        }
        throw new RuntimeException("Invalid loginId or password");
    }

    public UserDto findByLogin(String loginId) {
        Users user = userService.findByUserName(loginId);
        if (user != null) {
            UserDto userDto = new UserDto(1L, user.getContacts(), user.getRole(), user.getUserName(), "token");
            return userDto;
        }
        throw new RuntimeException("Invalid loginId");
    }
}
