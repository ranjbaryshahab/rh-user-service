package com.readhours.userservice.services.impl;

import com.readhours.userservice.domain.User;
import com.readhours.userservice.exceptions.UsernameNotFoundException;
import com.readhours.userservice.repositories.UserRepository;
import com.readhours.userservice.services.UserService;
import com.readhours.userservice.web.mappers.UserMapper;
import com.readhours.userservice.web.model.UserDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.UserToUserDto(
                userRepository.findByUsernameAndDeletedAtIsNull(username).orElseThrow(UsernameNotFoundException::new)
        );
    }

    @Override
    public UserDto register(UserDto userDto) {
        return userMapper.UserToUserDto(
                userRepository.save(userMapper.UserDtoToUser(userDto))
        );
    }

    @Override
    public UserDto changePassword(String username, String password) {
        User user = userRepository.findByUsernameAndDeletedAtIsNull(username).orElseThrow(UsernameNotFoundException::new);
        user.setPassword(password);
        return userMapper.UserToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsernameAndDeletedAtIsNull(username).orElseThrow(UsernameNotFoundException::new);
        user.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        userMapper.UserToUserDto(userRepository.save(user));
    }
}