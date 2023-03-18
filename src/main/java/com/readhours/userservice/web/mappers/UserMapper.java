package com.readhours.userservice.web.mappers;

import com.readhours.userservice.domain.User;
import com.readhours.userservice.web.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface UserMapper {
    UserDto UserToUserDto(User user);

    User UserDtoToUser(UserDto dto);
}
