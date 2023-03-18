package com.readhours.userservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readhours.userservice.services.UserService;
import com.readhours.userservice.web.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ComponentScan(basePackages = "com.readhours.userservice.web.mappers")
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    void givenUsername_whenGetUserByUsername_thenReturn200() throws Exception {
        given(userService.getUserByUsername(any())).willReturn(UserDto.builder().build());

        mockMvc
                .perform(get("/api/v1/user/shahab").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenUser_whenRegister_thenReturn201() throws Exception {
        UserDto userDto = getCreateUserDto();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        given(userService.register(any())).willReturn(getCreateUserDto());

        mockMvc
                .perform(post("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON).content(userDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void givenPassword_whenChangePassword_thenReturn200() throws Exception {
        given(userService.changePassword(any(), any())).willReturn(getUpdateUserDto());

        UserDto userDto = getUpdateUserDto();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc
                .perform(put("/api/v1/user/change-password/shahab").contentType(MediaType.APPLICATION_JSON).content(userDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void givenUsername_whenDelete_thenReturn200() throws Exception {
        willDoNothing().given(userService).delete("shahab");
        mockMvc
                .perform(put("/api/v1/user/delete/shahab"))
                .andExpect(status().isOk());
    }

    UserDto getCreateUserDto(){
        return UserDto.builder()
                .username("shahab")
                .email("shahab@gmail.com")
                .password("password")
                .build();
    }


    UserDto getUpdateUserDto(){
        return UserDto.builder()
                .password("password")
                .build();
    }
}