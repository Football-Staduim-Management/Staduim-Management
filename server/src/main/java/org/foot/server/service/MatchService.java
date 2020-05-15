package org.foot.server.service;

import org.foot.server.DAL.MatchsRepository;
import org.foot.server.model.DTO.MatchDto;
import org.foot.server.model.Match;
import org.foot.server.model.mapper.MatchMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    MatchsRepository matchsRepository;

    MatchMapper matchMapper = Mappers.getMapper(MatchMapper.class);

    public void reserve(MatchDto match) throws Exception{
        matchMapper.
        if (this.matchsRepository.findByDateAndTime(match.getDate(),match.getTime())!=null){
            throw new Exception("Time already reseverd");

        }else {
            this.matchsRepository.save(match);
        }
    }
}
