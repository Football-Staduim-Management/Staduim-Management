package org.foot.server.service;

import org.foot.server.DAL.StadiumRepository;
import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.Stadium;
import org.foot.server.model.mapper.StadiumMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StadiumManagementService {
    @Autowired
    StadiumRepository stadiumRepository;

    StadiumMapper stadiumMapper = Mappers.getMapper(StadiumMapper.class);

    public StadiumDto creatStadium(StadiumDto stadiumDto) throws Exception{
        if(stadiumRepository.findByName(stadiumDto.getName())!=null){
            throw new Exception("Stadium name already exist");
        }
        return stadiumMapper.StadiumtoStadiumDto(stadiumRepository.save(stadiumMapper.StadiumDtotoStadium(stadiumDto)));
    }
    public void deleteStadium(StadiumDto stadiumDto){
        stadiumRepository.delete(stadiumMapper.StadiumDtotoStadium(stadiumDto));
    }

    public StadiumDto readStadium(String name){
        return stadiumMapper.StadiumtoStadiumDto(stadiumRepository.findByName(name));
    }

    public List<StadiumDto> readAll(){
        List<Stadium> stadiumList = (List) stadiumRepository.findAll();
        return stadiumList
                .stream()
                .map((Stadium element) -> {
                    return stadiumMapper.StadiumtoStadiumDto(element);
                })
                .collect(Collectors.toList());
    }
}
