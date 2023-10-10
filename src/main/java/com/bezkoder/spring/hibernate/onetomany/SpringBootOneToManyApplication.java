package com.bezkoder.spring.hibernate.onetomany;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootOneToManyApplication {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
  public static void main(String[] args) {
    SpringApplication.run(SpringBootOneToManyApplication.class, args);
  }

}
