package com.bezkoder.spring.hibernate.onetomany.repository;

import com.bezkoder.spring.hibernate.onetomany.model.Course;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
}
