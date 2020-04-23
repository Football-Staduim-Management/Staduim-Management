package org.foot.server.model.mapper;

import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.Stadium;
import org.mapstruct.Mapper;

@Mapper
public interface StadiumMapper {
    Stadium StadiumDtotoStadium(StadiumDto stadiumDto);
    StadiumDto StadiumtoStadiumDto(Stadium stadium);
}
