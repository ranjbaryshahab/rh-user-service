package com.readhours.userservice.services;

import com.readhours.userservice.web.model.UserDto;

public interface UserService {
    UserDto getUserByUsername(String username);
    UserDto register(UserDto userDto);
    UserDto changePassword(String username, String password);
    void delete(String username);
}
