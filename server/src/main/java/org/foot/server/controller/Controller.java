package org.foot.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/api/private")
    public String getMessagePrivate(){
        return "private";
    }

    @GetMapping("/api/public")
    public String getMessagePublic(@AuthenticationPrincipal Authentication authentication){
        System.out.println(authentication);
        return "public";
    }
}
