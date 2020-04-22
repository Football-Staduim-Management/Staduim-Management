package org.foot.server.service.subscription;

import org.foot.server.DAL.UserRepository;
import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.User;
import org.foot.server.model.mapper.UserToUserDtoMapper;
import org.foot.server.service.security.Filter.AdapterFilter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class UserManagmentService extends AdapterFilter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    UserToUserDtoMapper userToUserDtoMapper = Mappers.getMapper(UserToUserDtoMapper.class);

    public UserDto creatUser(UserDto userDto) throws Exception{
        if(userRepository.findByEmail(userDto.getEmail())!=null){
            throw new Exception("User already exist");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userToUserDtoMapper.UsertoUserDto(userRepository.save(userToUserDtoMapper.UserDtotoUser(userDto)));
    }

    public UserDto updateUser(UserDto userDto){
        User user = userToUserDtoMapper.UserDtotoUser(userDto);
        if(userDto.getId()==null){
            user = userRepository.findByEmail(userDto.getEmail());
        }
        return userToUserDtoMapper.UsertoUserDto(userRepository.save(user));
    }

    public void deleteUser(UserDto userDto){
        userRepository.delete(userToUserDtoMapper.UserDtotoUser(userDto));
    }

    public UserDto readUser(String email){
        return userToUserDtoMapper.UsertoUserDto(userRepository.findByEmail(email));
    }

    public UserDto connectedUser(User user){
        return userToUserDtoMapper.UsertoUserDto(user);
    }




}
