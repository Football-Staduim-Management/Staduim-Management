package org.foot.server.DAL;

import org.foot.server.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface PositionRepository extends CrudRepository<Position, Long> {
    List<Position> findByAltLessThanEqualAndLngLessThanEqual(Float alt,Float lng);
}
