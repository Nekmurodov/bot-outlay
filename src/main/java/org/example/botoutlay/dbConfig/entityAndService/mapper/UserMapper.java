package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.User;
import org.example.botoutlay.dbConfig.entityAndService.payload.UserCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.payload.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserCreatDto userCreatDto) {
        User user = new User();
        user.setUsername(userCreatDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreatDto.getPassword()));
        user.setFirstName(userCreatDto.getFirstName());
        user.setLastName(userCreatDto.getLastName());
        user.setRole(userCreatDto.getRole());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public List<UserDto> toDto(List<User> users) {
        List<UserDto> userDto = new ArrayList<>();
        for (User user : users) {
            userDto.add(toDto(user));
        }
        return userDto;
    }
}
