package com.readhours.userservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Null
    private UUID id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotNull
    private String password;

    @Null
    private Timestamp createdAt;

    @Null
    private Timestamp updatedAt;

    @Null
    private Timestamp deletedAt;
}
