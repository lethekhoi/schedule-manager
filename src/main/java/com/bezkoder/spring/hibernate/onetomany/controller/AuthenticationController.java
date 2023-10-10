package com.bezkoder.spring.hibernate.onetomany.controller;

import com.bezkoder.spring.hibernate.onetomany.config.UserAuthenticationProvider;
import com.bezkoder.spring.hibernate.onetomany.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user) {
        user.setToken(userAuthenticationProvider.createToken(user.getLoginId()));
        return ResponseEntity.ok(user);
    }
}
