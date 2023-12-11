package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.UserDto;
import com.bezkoder.spring.hibernate.onetomany.model.Users;

public interface UserService {
    Users findByUserName(String username);
}
