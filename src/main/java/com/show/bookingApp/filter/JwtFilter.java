package com.show.bookingApp.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.show.bookingApp.dto.LoginRequest;
import com.show.bookingApp.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public JwtFilter(
            JwtService jwtService, AuthenticationManager authenticationManager
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain
    ) throws ServletException, IOException {
        if(!request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());

        Authentication authResult = authenticationManager.authenticate(authToken);

        if(authResult.isAuthenticated()){
            String token = jwtService.generateToken(authResult.getName(),15);
            response.setHeader("Authorization","Bearer "+token);
        }
    }

}
