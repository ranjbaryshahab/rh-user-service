package com.readhours.userservice.services.impl;

import com.readhours.userservice.repositories.UserRepository;
import com.readhours.userservice.services.UserService;
import com.readhours.userservice.web.mappers.UserMapper;
import com.readhours.userservice.web.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto getUserByUsername(String username) {
        return userMapper.UserToUserDto(
                userRepository.findByUsernameAndDeletedAtIsNull(username)
        );
    }

    @Override
    @Transactional
    public UserDto register(UserDto userDto) {
        return userMapper.UserToUserDto(
                userRepository.save(userMapper.UserDtoToUser(userDto))
        );
    }

    @Override
    @Transactional
    public UserDto changePassword(String username, String password) {
        UserDto user = getUserByUsername(username);
        if (user != null) {
            user.setPassword(password);
            return register(user);
        }
        return null;
    }

    @Override
    @Transactional
    public Integer delete(String username) {
        UserDto user = getUserByUsername(username);
        if (user != null) {
            user.setDeletedAt(OffsetDateTime.now());
            register(user);
            return 1;
        }
        return 0;
    }
}