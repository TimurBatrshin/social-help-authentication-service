package com.example.socialhelp.security.filter;

import com.example.socialhelp.security.authentication.JwtAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(token);

        if (token != null) {
            JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

