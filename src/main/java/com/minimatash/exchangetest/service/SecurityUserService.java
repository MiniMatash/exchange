package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.UserDto;
import com.minimatash.exchangetest.repository.UserRepository;
import com.minimatash.exchangetest.security.TokenUserDetails;
import com.minimatash.exchangetest.util.DtoConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class SecurityUserService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        try {
            UserDto userDto = DtoConverter.convert(new UserDto(), userRepository.findByToken(token));
            return new TokenUserDetails(userDto,token) ;
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Autowired
    public SecurityUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
