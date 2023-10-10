package com.bezkoder.spring.hibernate.onetomany.filter;

import com.bezkoder.spring.hibernate.onetomany.config.UserAuthenticationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

                    String content = e.getMessage();
                    byte[] bytes = content.getBytes();
                    httpServletResponse.setContentLength(bytes.length);
                    httpServletResponse.getWriter().write(content);
                    httpServletResponse.getWriter().flush();
                    httpServletResponse.getWriter().close();
                    return;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
