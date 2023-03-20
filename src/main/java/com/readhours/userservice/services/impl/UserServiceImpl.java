package com.readhours.userservice.services.impl;

import com.readhours.userservice.domain.User;
import com.readhours.userservice.exceptions.UsernameNotFoundException;
import com.readhours.userservice.repositories.UserRepository;
import com.readhours.userservice.services.UserService;
import com.readhours.userservice.web.mappers.UserMapper;
import com.readhours.userservice.web.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsernameAndDeletedAtIsNull(username);

        if (user != null) {
            return userMapper.UserToUserDto(user);
        }

        throw new UsernameNotFoundException();
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
        throw new UsernameNotFoundException();
    }

    @Override
    @Transactional
    public void delete(String username) {
        UserDto user = getUserByUsername(username);
        if (user != null) {
            user.setDeletedAt(LocalDateTime.now());
            register(user);
        }
        throw new UsernameNotFoundException();
    }
}