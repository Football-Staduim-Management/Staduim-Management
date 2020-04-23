package org.foot.server.service;

import org.foot.server.DAL.UserRepository;
import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.User;
import org.foot.server.model.mapper.UserMapper;
import org.foot.server.service.security.Filter.AdapterFilter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagmentService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserDto creatUser(UserDto userDto) throws Exception{
        if(userRepository.findByEmail(userDto.getEmail())!=null){
            throw new Exception("User already exist");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.UsertoUserDto(userRepository.save(userMapper.UserDtotoUser(userDto)));
    }

    public UserDto updateUser(UserDto userDto){
        User user = userMapper.UserDtotoUser(userDto);
        if(userDto.getId()==null){
            user = userRepository.findByEmail(userDto.getEmail());
        }
        return userMapper.UsertoUserDto(userRepository.save(user));
    }

    public void deleteUser(UserDto userDto){
        userRepository.delete(userMapper.UserDtotoUser(userDto));
    }

    public UserDto readUser(String email){
        return userMapper.UsertoUserDto(userRepository.findByEmail(email));
    }

    public UserDto connectedUser(User user){
        return userMapper.UsertoUserDto(user);
    }

}
