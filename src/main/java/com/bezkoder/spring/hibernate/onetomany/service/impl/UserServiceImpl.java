package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.dto.UserDto;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.repository.UserRepository;
import com.bezkoder.spring.hibernate.onetomany.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@SuppressWarnings("java:S1188")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Users findByUserName(String username) {
         Users user =userRepository.findByUserName(username);

        return user;
    }
}
