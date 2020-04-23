package org.foot.server.model.mapper;

import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User UserDtotoUser(UserDto userDto);
    UserDto UsertoUserDto(User user);
}
