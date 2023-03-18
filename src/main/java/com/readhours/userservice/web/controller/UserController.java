package com.readhours.userservice.web.controller;

import com.readhours.userservice.services.UserService;
import com.readhours.userservice.validations.ValidationGroups;
import com.readhours.userservice.web.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/api/v1/user")
@RestController
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> get(@NotNull @PathVariable("username") String username){
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Validated(ValidationGroups.Create.class) @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/change-password/{username}")
    public ResponseEntity<UserDto> changePassword(@NotNull @PathVariable("username") String username, @Validated(ValidationGroups.Update.class) @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.changePassword(username, userDto.getPassword()), HttpStatus.OK);
    }

    @PutMapping("/delete/{username}")
    public void delete(@NotNull @PathVariable("username") String username){
        userService.delete(username);
    }
}
