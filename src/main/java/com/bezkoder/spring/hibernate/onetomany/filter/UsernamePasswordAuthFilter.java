package com.bezkoder.spring.hibernate.onetomany.filter;

import com.bezkoder.spring.hibernate.onetomany.config.UserAuthenticationProvider;
import com.bezkoder.spring.hibernate.onetomany.dto.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        if ("/signIn".equals(httpServletRequest.getServletPath())
                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            CredentialsDto credentialsDto = mapper.readValue(httpServletRequest.getInputStream(),
                    CredentialsDto.class);

            try {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthenticationProvider.validateCredentials(credentialsDto));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

                String content = e.getMessage();
                byte[] bytes = content.getBytes();
                httpServletResponse.setContentLength(bytes.length);
                httpServletResponse.getWriter().write(content);
                httpServletResponse.getWriter().flush();
                httpServletResponse.getWriter().close();
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
