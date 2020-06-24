package org.foot.server.model.mapper;

import org.foot.server.model.DTO.MatchDto;
import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.Match;
import org.foot.server.model.Stadium;
import org.mapstruct.Mapper;

@Mapper
public interface MatchMapper {
    Match MatchDtotoMatch(MatchDto matchDto);
    MatchDto MatchtoMatchDto(Match match);
}
