package org.foot.server.DAL;

import org.foot.server.model.Position;
import org.foot.server.model.Stadium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StadiumRepository extends CrudRepository<Stadium, Long> {
    Stadium findByName(String name);
    List<Stadium> findByPositionIn(List<Position> position);

}
