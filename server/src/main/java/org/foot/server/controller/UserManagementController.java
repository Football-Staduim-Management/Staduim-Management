package org.foot.server.controller;

import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.User;
import org.foot.server.service.subscription.UserManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserManagementController {

    @Autowired
    UserManagmentService userManagmentService;



    @GetMapping("/user/get")
    public ResponseEntity<UserDto> getUser(@RequestParam(required = false) String email){
        SecurityContext sc = SecurityContextHolder.getContext();
        User user = (User)sc.getAuthentication().getPrincipal();

        return ResponseEntity.ok(email == null ? userManagmentService.connectedUser(user):userManagmentService.readUser(email));
    }

    @PostMapping("/user/post")
    public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto){
        return  ResponseEntity.ok(userManagmentService.updateUser(userDto));
    }


    @PutMapping("/user/put")
    public ResponseEntity<?> putUser(@RequestBody UserDto userDto){

        UserDto userDto1= null;
        try {
            userDto1 = userManagmentService.creatUser(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return   ResponseEntity.status(HttpStatus.CREATED).body(userDto1);
    }

    @DeleteMapping("/user/delet")
    public void deleteUser(@RequestBody UserDto userDto){
        userManagmentService.deleteUser(userDto);
    }
}




