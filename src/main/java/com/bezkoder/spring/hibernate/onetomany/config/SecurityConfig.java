package com.bezkoder.spring.hibernate.onetomany.config;

import com.bezkoder.spring.hibernate.onetomany.filter.JwtAuthFilter;
import com.bezkoder.spring.hibernate.onetomany.filter.UsernamePasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;


    @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AntPathRequestMatcher signInMatcher = new AntPathRequestMatcher("/signIn");
        AntPathRequestMatcher adminMatcher = new AntPathRequestMatcher("/admin/**");
        AntPathRequestMatcher userMatcher = new AntPathRequestMatcher("/user/**");
        http.addFilterBefore(usernamePasswordAuthFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthFilter.class)

                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers(signInMatcher)
                        .permitAll().requestMatchers(adminMatcher).hasAuthority("ADMIN")
                        .requestMatchers(userMatcher).hasAnyAuthority("ADMIN", "USER").anyRequest()
                        .authenticated());
        return http.build();
    }
}
