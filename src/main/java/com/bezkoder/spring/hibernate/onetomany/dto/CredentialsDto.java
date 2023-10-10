package com.bezkoder.spring.hibernate.onetomany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto {
    private String username;
    private char[] password;

}
