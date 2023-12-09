package com.bezkoder.spring.hibernate.onetomany.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "users")
@Generated
public class Users {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "role")
    private String role;
    private String contacts;
    @Column(name = "pass_word")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
