package org.example.botoutlay.dbConfig.util;

import org.example.botoutlay.dbConfig.entityAndService.entity.User;
import org.example.botoutlay.dbConfig.entityAndService.mapper.UserMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    private final UserMapper userMapper;

    public UserSession(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public UserDto getUser(){
        UserDto userDto = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            userDto = userMapper.toDto((User) authentication.getPrincipal());
        }
        return userDto;
    }
}
