package org.foot.server.controller;

import org.foot.server.DAL.UserRepository;
import org.foot.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/api/private")
    public User getMessagePrivate(){
        User user =(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

//    @GetMapping("/api/public")
//    public String getMessagePublic(@RequestBody User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user).toString();
//    }
}
