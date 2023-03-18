package com.readhours.userservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
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
    @Size(min = 8, max = 36)
    private String password;

    @Null
    private OffsetDateTime createdAt;

    @Null
    private OffsetDateTime updatedAt;

    @Null
    private OffsetDateTime deletedAt;
}
