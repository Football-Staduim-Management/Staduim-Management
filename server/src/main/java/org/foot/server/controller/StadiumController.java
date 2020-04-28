package org.foot.server.controller;

import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.SearchInfo;
import org.foot.server.service.StadiumManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StadiumController {

    @Autowired
    StadiumManagementService stadiumManagementService;

    @GetMapping("/stadium/GET")
    public ResponseEntity<StadiumDto> getStadium(@RequestParam String name){
        return ResponseEntity.ok(stadiumManagementService.readStadium(name));
    }

    @GetMapping("/stadium/GETALL")
    public ResponseEntity<List<StadiumDto>> getAllStadium(){
        return ResponseEntity.ok(stadiumManagementService.readAll());
    }


    @PutMapping("/stadium/PUT")
    public ResponseEntity<?> putStadium(@RequestBody StadiumDto stadiumDto){
        StadiumDto stadiumDto1;
        try{
            stadiumDto1  = stadiumManagementService.creatStadium(stadiumDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return   ResponseEntity.status(HttpStatus.CREATED).body(stadiumDto1);
    }

    @PostMapping("/stadium/search")
    public ResponseEntity<?> getAvailableStadium(@RequestBody SearchInfo searchInfo){
        return ResponseEntity.ok(this.stadiumManagementService.search(searchInfo));
    }
}
