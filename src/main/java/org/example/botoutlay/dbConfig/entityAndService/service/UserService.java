package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.User;
import org.example.botoutlay.dbConfig.entityAndService.mapper.UserMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.UserCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.UserRepository;
import org.example.botoutlay.dbConfig.exception.AlreadyExistException;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseData<?> creatUser(UserCreatDto userCreateDto) {
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new AlreadyExistException("User with username " + userCreateDto.getUsername() + " already exists");
        }
        User user = this.userMapper.toEntity(userCreateDto);
        this.userRepository.save(user);
        return ResponseData.successResponse(this.userMapper.toDto(user));
    }

    public ResponseData<?> updateUser(Long userId, UserCreatDto userCreateDto) {
        Optional<User> optionalUser = this.userRepository.findByIdAndDeletedFalse(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " not found");
        }
        if (!optionalUser.get().getUsername().equals(userCreateDto.getUsername())) {
            if (userRepository.existsByUsername(userCreateDto.getUsername())) {
                throw new AlreadyExistException("User with username " + userCreateDto.getUsername() + " already exists");
            }
        }
        User user = this.userMapper.toEntity(userCreateDto);
        this.userRepository.save(user);
        return ResponseData.successResponse(this.userMapper.toDto(user));
    }

    public ResponseData<?> getUser(Long userId) {
        Optional<User> optionalUser = this.userRepository.findByIdAndDeletedFalse(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " not found");
        }
        return ResponseData.successResponse(this.userMapper.toDto(optionalUser.get()));
    }

    public ResponseData<?> deleteUser(Long userId) {
        Optional<User> optionalUser = this.userRepository.findByIdAndDeletedFalse(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " not found");
        }
        User user = optionalUser.get();
        user.setDeleted(true);
        this.userRepository.save(user);
        return ResponseData.successResponse("success");
    }

    public ResponseData<?> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = this.userRepository.findAllByDeletedFalse(pageable);
        if (users.isEmpty()) {
            throw new NotFoundException("Users not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", userMapper.toDto(users.toList()));
        response.put("total", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());

        return ResponseData.successResponse(response);
    }
}
