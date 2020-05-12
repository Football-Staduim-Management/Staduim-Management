package org.foot.server.DAL;

import org.foot.server.model.Match;
import org.foot.server.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MatchsRepository extends CrudRepository<Match, Long> {
    List<Match> findByStadium_id(Long stadium_id);
}
