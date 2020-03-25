package org.foot.server.controller;

import org.foot.server.model.DTO.UserDto;
import org.foot.server.service.subscription.UserManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserManagementController {

    @Autowired
    UserManagmentService userManagmentService;

    @GetMapping("/user/get")
    public ResponseEntity<UserDto> getUser(@RequestBody  String email){
        return ResponseEntity.ok(userManagmentService.readUser(email));
    }

    @PostMapping("/user/post")
    public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto){
        return  ResponseEntity.ok(userManagmentService.updateUser(userDto));
    }

    @PutMapping("/user/put")
    public ResponseEntity<UserDto> putUser(@RequestBody UserDto userDto){
        return  ResponseEntity.ok(userManagmentService.creatUser(userDto));
    }

    @DeleteMapping("/user/delet")
    public void deleteUser(@RequestBody UserDto userDto){
        userManagmentService.deleteUser(userDto);
    }
}
