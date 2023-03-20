package com.readhours.userservice.web.model;

import com.readhours.userservice.validations.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Null
    private UUID id;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Null(groups = ValidationGroups.Update.class)
    private String email;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Null(groups = ValidationGroups.Update.class)
    private String username;

    @NotNull
    @Size(min = 8, max = 36)
    private String password;

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime updatedAt;

    @Null
    private LocalDateTime deletedAt;
}
