package com.example.springbootthymeleaftw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public void setAuthToken(String username)
    {
        UserDetails userDetail = userService.loadUserByEmail(username);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
    }
}

//    UserDetails userDetail = userService.loadUserByUsername(username);
//
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));