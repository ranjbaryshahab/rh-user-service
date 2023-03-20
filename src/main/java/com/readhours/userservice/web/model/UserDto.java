package com.readhours.userservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.readhours.userservice.utils.LocalDateSerializer;
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
    @JsonProperty("user_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime createdAt;

    @Null
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    @Null
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deletedAt;
}
