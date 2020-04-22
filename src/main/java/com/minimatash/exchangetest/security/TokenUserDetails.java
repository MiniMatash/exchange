package com.minimatash.exchangetest.security;

import com.minimatash.exchangetest.dto.UserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class TokenUserDetails extends User {

    private UserDto userDto;

    public TokenUserDetails(UserDto userDto, String token) {
        super(token, "password",  Collections.singletonList(new SimpleGrantedAuthority("user")));
        this.userDto = userDto;
    }
}
