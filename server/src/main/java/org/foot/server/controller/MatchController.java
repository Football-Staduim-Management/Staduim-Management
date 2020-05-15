package org.foot.server.controller;

import org.foot.server.model.DTO.MatchDto;
import org.foot.server.model.Match;
import org.foot.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController  {

    @Autowired
    MatchService matchService;

    @PostMapping
    public ResponseEntity<?> addMatch(@RequestBody MatchDto match){
        try {
            matchService.reserve(match);
            return ResponseEntity.ok(match);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
